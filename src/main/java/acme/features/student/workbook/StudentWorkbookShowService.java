/*
 * StudentWorkbookShowService.java
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
import acme.entities.workbooks.WorkbookType;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Student;

@Service
public class StudentWorkbookShowService extends AbstractService<Student, Workbook> {

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
		Workbook Workbook;
		Student Student;

		masterId = super.getRequest().getData("id", int.class);
		Workbook = this.repository.findOneWorkbookById(masterId);

		Student = Workbook == null ? null : Workbook.getStudent();
		status = super.getRequest().getPrincipal().hasRole(Student) || Workbook != null;

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
	public void unbind(final Workbook object) {
		assert object != null;

		SelectChoices choices;
		Tuple tuple;

		choices = SelectChoices.from(WorkbookType.class, object.getType());

		tuple = super.unbind(object, "title", "abstract$", "type", "timePeriod", "furtherInformation", "published");
		tuple.put("types", choices);

		super.getResponse().setData(tuple);
	}

}
