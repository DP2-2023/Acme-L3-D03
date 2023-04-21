/*
 * CompanyPracticumCreateService.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.company.practicum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.practicums.Practicum;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticumCreateService extends AbstractService<Company, Practicum> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Practicum object;
		Company Company;

		final int courseId;
		final Course course;

		Company = this.repository.findOneCompanyById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new Practicum();
		object.setPublished(false);

		object.setCompany(Company);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Practicum object) {
		assert object != null;

		super.bind(object, "code", "title", "abstract$", "goals", "estimatedTotalTime");
		object.setPublished(false);
	}

	@Override
	public void validate(final Practicum object) {
		assert object != null;

	}

	@Override
	public void perform(final Practicum object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Practicum object) {
		assert object != null;

		SelectChoices choices;
		Tuple tuple;

		Collection<Course> courses;

		courses = this.repository.findAllCourses();

		choices = SelectChoices.from(courses, "title", object.getCourse());

		tuple = super.unbind(object, "code", "title", "abstract$", "goals", "estimatedTotalTime", "isPublished");
		tuple.put("course", choices.getSelected().getKey());
		tuple.put("courses", choices);

		super.getResponse().setData(tuple);
	}
}
