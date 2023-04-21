/*
 * StudentCourseShowService.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.student.course;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.courses.Course;
import acme.entities.courses.CourseLecture;
import acme.entities.lectures.Lecture;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;
import acme.roles.Student;

@Service
public class StudentCourseShowService extends AbstractService<Student, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentCourseRepository repository;

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
		int id;
		Course Course;

		id = super.getRequest().getData("id", int.class);
		Course = this.repository.findOneCourseById(id);
		status = Course != null && Course.isPublished();

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
	public void unbind(final Course object) {
		assert object != null;

		Tuple tuple;

		// Obtener el objeto CourseLecture asociado al Course correspondiente
		final int courseId = super.getRequest().getData("id", int.class);
		final Collection<CourseLecture> courseLectures = this.repository.findOneCourseLectureByCourseId(courseId);

		if (courseLectures != null) {
			// Obtener el objeto Lecture y Lecturer a través del objeto CourseLecture
			final CourseLecture courseLecture = courseLectures.iterator().next();
			final Lecture lecture = courseLecture.getLecture();
			final Lecturer lecturer = lecture.getLecturer();

			// Agregar los objetos Lecture y Lecturer al Tuple
			tuple = super.unbind(object, "code", "title", "abstract$", "courseType", "price", "furtherInformation");
			tuple.put("lecture", lecture);
			tuple.put("lecturer", lecturer);
		} else
			// Si no se encuentra el objeto CourseLecture, devolver el Tuple sin agregar los objetos Lecture y Lecturer
			tuple = super.unbind(object, "code", "title", "abstract$", "courseType", "price", "furtherInformation");

		super.getResponse().setData(tuple);
	}

}
