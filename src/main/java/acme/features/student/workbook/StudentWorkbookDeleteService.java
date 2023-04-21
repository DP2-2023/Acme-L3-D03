/*
 * StudentWorkbookDeleteService.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.student.workbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workbooks.Workbook;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentWorkbookDeleteService extends AbstractService<Student, Workbook> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentWorkbookRepository repository;

	// AbstractService interface ----------------------------------------------


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
		final Workbook Workbook;
		final Student Student;

		masterId = super.getRequest().getData("id", int.class);
		Workbook = this.repository.findOneWorkbookById(masterId);

		Student = Workbook == null ? null : Workbook.getStudent();
		status = Workbook != null && !Workbook.isPublished() && super.getRequest().getPrincipal().hasRole(Student);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Workbook object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneWorkbookById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Workbook object) {
		assert object != null;

		super.bind(object, "title", "abstract$", "type", "timePeriod", "furtherInformation");

	}

	@Override
	public void validate(final Workbook object) {
		assert object != null;
	}

	@Override
	public void perform(final Workbook object) {
		assert object != null;

		this.repository.delete(object);
	}

	@Override
	public void unbind(final Workbook object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "title", "abstract$", "type", "timePeriod", "furtherInformation");

		super.getResponse().setData(tuple);
	}

}
