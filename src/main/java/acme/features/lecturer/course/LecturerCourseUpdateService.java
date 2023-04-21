/*
 * LecturerCourseUpdateService.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.lecturer.course;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.framework.components.datatypes.Money;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;
import acme.utils.CurrencyExchange;
import spamfilter.SpamFilter;

@Service
public class LecturerCourseUpdateService extends AbstractService<Lecturer, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerCourseRepository repository;

	// AbstractService<Lecturer, Course> -------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Course course;
		final Lecturer lecturer;

		lecturer = this.repository.findOneLecturerById(super.getRequest().getPrincipal().getActiveRoleId());

		masterId = super.getRequest().getData("id", int.class);
		course = this.repository.findOneCourseById(masterId);

		status = course != null && !course.isPublished() && lecturer != null;

		// Check course is of lecturer
		if (status) {
			final Collection<Course> lecturerCourses = this.repository.findManyCoursesByLecturerId(lecturer.getId());
			status = lecturerCourses.contains(course);
		}

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Course object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneCourseById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Course object) {
		assert object != null;

		super.bind(object, "title", "abstract$", "price", "furtherInformation");

		// Currency conversion
		final Money sourcePrice = super.getRequest().getData("price", Money.class);
		final String ratesString = this.repository.findOneConfigByKey("currencyExchangeRates");
		final String systemCurrency = this.repository.findOneConfigByKey("systemCurrency");

		final CurrencyExchange currencyExchange = new CurrencyExchange(ratesString, systemCurrency);
		final Money targetPrice = currencyExchange.exchange(sourcePrice);
		if (targetPrice != null)
			object.setPrice(targetPrice);

	}

	@Override
	public void validate(final Course object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("price"))
			super.state(object.getPrice().getAmount() >= 0, "price", "lecturer.course.form.error.negative-price");

		// Spam filter
		String spamTerms = null;
		final String spamTermsES = this.repository.findOneConfigByKey("spamTermsES");
		final String spamTermsEN = this.repository.findOneConfigByKey("spamTermsEN");
		final Float threshold = Float.valueOf(this.repository.findOneConfigByKey("spamThreshold"));

		if (spamTermsES != null && !spamTermsES.trim().isEmpty()) {
			spamTerms = spamTermsES;
			if (spamTermsEN != null && !spamTermsEN.trim().isEmpty())
				spamTerms = spamTerms + "," + spamTermsEN;
		} else if (spamTermsEN != null && !spamTermsEN.trim().isEmpty())
			spamTerms = spamTermsEN;

		if (spamTerms != null && threshold != null) {
			final SpamFilter spamFilter = new SpamFilter(spamTerms, threshold);

			if (!super.getBuffer().getErrors().hasErrors("title"))
				super.state(!spamFilter.isSpam(object.getTitle()), "title", "lecturer.course.form.error.spam");

			if (!super.getBuffer().getErrors().hasErrors("abstract$"))
				super.state(!spamFilter.isSpam(object.getAbstract$()), "abstract$", "lecturer.course.form.error.spam");

			if (!super.getBuffer().getErrors().hasErrors("furtherInformation"))
				super.state(!spamFilter.isSpam(object.getFurtherInformation()), "furtherInformation", "lecturer.course.form.error.spam");
		}

		// Currency conversion
		final String ratesString = this.repository.findOneConfigByKey("currencyExchangeRates");
		final String systemCurrency = this.repository.findOneConfigByKey("systemCurrency");
		final CurrencyExchange currencyExchange = new CurrencyExchange(ratesString, systemCurrency);

		if (!super.getBuffer().getErrors().hasErrors("price"))
			super.state(currencyExchange.isAcceptedCurrency(object.getPrice()), "price", "lecturer.course.form.error.not-accepted-currency");

	}

	@Override
	public void perform(final Course object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Course object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "code", "title", "abstract$", "type", "price", "furtherInformation", "isPublished");

		super.getResponse().setData(tuple);
	}

}
