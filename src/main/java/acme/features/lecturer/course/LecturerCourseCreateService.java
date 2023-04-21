/*
 * LecturerCourseCreateService.java
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
import acme.entities.courses.CourseLecture;
import acme.entities.courses.CourseType;
import acme.entities.lectures.Lecture;
import acme.entities.lectures.LectureType;
import acme.framework.components.datatypes.Money;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;
import acme.utils.CurrencyExchange;
import spamfilter.SpamFilter;

@Service
public class LecturerCourseCreateService extends AbstractService<Lecturer, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LecturerCourseRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		final Lecturer lecturer;
		final boolean status;

		lecturer = this.repository.findOneLecturerById(super.getRequest().getPrincipal().getActiveRoleId());
		status = lecturer != null;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Course object;

		object = new Course();
		object.setPublished(false);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Course object) {
		assert object != null;

		super.bind(object, "code", "title", "abstract$", "price", "furtherInformation");

		final int lectureId = super.getRequest().getData("lecture", int.class);
		final Lecture lecture = this.repository.findOneLectureById(lectureId);

		if (lecture != null)
			if (lecture.getType() == LectureType.THEORETICAL)
				object.setType(CourseType.THEORETICAL);
			else
				object.setType(CourseType.HANDS_ON);

		object.setPublished(false);

		// Currency conversion
		final Money sourcePrice = super.getRequest().getData("price", Money.class);
		if (sourcePrice != null) {
			final String ratesString = this.repository.findOneConfigByKey("currencyExchangeRates");
			final String systemCurrency = this.repository.findOneConfigByKey("systemCurrency");

			final CurrencyExchange currencyExchange = new CurrencyExchange(ratesString, systemCurrency);
			final Money targetPrice = currencyExchange.exchange(sourcePrice);
			if (targetPrice != null)
				object.setPrice(targetPrice);
		}

	}

	@Override
	public void validate(final Course object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			final Course existing = this.repository.findOneCourseByCode(object.getCode());
			super.state(existing == null, "code", "lecturer.course.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("price"))
			super.state(object.getPrice().getAmount() >= 0, "price", "lecturer.course.form.error.negative-price");

		final int lectureId = super.getRequest().getData("lecture", int.class);
		final Lecture lecture = this.repository.findOneLectureById(lectureId);
		if (!super.getBuffer().getErrors().hasErrors("lecture"))
			super.state(lecture != null, "lecture", "lecturer.course.form.error.lecture");

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
			if (object.getPrice() != null)
				super.state(currencyExchange.isAcceptedCurrency(object.getPrice()), "price", "lecturer.course.form.error.not-accepted-currency");
			else
				super.state(object.getPrice() != null, "price", "lecturer.course.form.error.not-accepted-currency");
	}

	@Override
	public void perform(final Course object) {
		assert object != null;

		int lectureId;
		Lecture lecture;
		lectureId = super.getRequest().getData("lecture", int.class);
		lecture = this.repository.findOneLectureById(lectureId);

		final CourseLecture relation = new CourseLecture();
		relation.setCourse(object);
		relation.setLecture(lecture);

		this.repository.save(object);
		this.repository.save(relation);
	}

	@Override
	public void unbind(final Course object) {
		assert object != null;

		int lecturerId;
		Collection<Lecture> lectures;
		SelectChoices choices;

		Tuple tuple;

		lecturerId = super.getRequest().getPrincipal().getActiveRoleId();
		lectures = this.repository.findManyLecturesByLecturerId(lecturerId);

		choices = SelectChoices.from(lectures, "title", null);

		tuple = super.unbind(object, "code", "title", "abstract$", "type", "price", "furtherInformation", "isPublished");
		tuple.put("lectures", choices);

		super.getResponse().setData(tuple);
	}

}
