/*
 * StudentWorkbookCreateService.java
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
public class StudentWorkbookCreateService extends AbstractService<Student, Workbook> {

	@Autowired
	protected StudentWorkbookRepository repository;

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
		Workbook object;
		Student Student;

		Student = this.repository.findOneStudentById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new Workbook();
		object.setPublished(false);

		object.setStudent(Student);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Workbook object) {
		assert object != null;

		super.bind(object, "title", "abstract$", "type", "timePeriod", "furtherInformation");
		object.setPublished(false);
		//object.setLectureType(LectureType.BALANCED);
	}

	@Override
	public void validate(final Workbook object) {
		assert object != null;

	}

	@Override
	public void perform(final Workbook object) {
		assert object != null;

		this.repository.save(object);
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
