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
import acme.entities.courses.CourseLecture;
import acme.entities.courses.CourseType;
import acme.entities.lectures.Lecture;
import acme.entities.lectures.LectureType;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Lecturer;

@Service
public class LecturerCourseAddLectureService extends AbstractService<Lecturer, Course> {

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

		final int lectureId = super.getRequest().getData("lecture", int.class);
		final Lecture lecture = this.repository.findOneLectureById(lectureId);
		final Collection<Lecture> lectures = this.repository.findManyLecturesByCourseId(object.getId());
		if (lecture != null) {
			long handsOnLectures = lectures.stream().filter(x -> x.getType().equals(LectureType.HANDS_ON)).count();
			final int totalLectures = lectures.size() + 1;
			if (lecture.getType().equals(LectureType.HANDS_ON))
				handsOnLectures += 1;

			final float ratio = (float) handsOnLectures / totalLectures;
			if (ratio == 0.5)
				object.setType(CourseType.BALANCED);
			else if (ratio < 0.5)
				object.setType(CourseType.THEORETICAL);
			else if (ratio > 0.5)
				object.setType(CourseType.HANDS_ON);
		}
	}

	@Override
	public void validate(final Course object) {
		assert object != null;

		final int lectureId = super.getRequest().getData("lecture", int.class);
		final Lecture lecture = this.repository.findOneLectureById(lectureId);

		if (!super.getBuffer().getErrors().hasErrors("lecture"))
			super.state(lecture != null, "lecture", "lecturer.course.form.error.lecture");

	}

	@Override
	public void perform(final Course object) {
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
		int lecturerId;
		Collection<Lecture> lectures, lecturesInCourse;
		SelectChoices choices;

		Tuple tuple;

		lecturerId = super.getRequest().getPrincipal().getActiveRoleId();
		lectures = this.repository.findManyLecturesByLecturerId(lecturerId);
		lecturesInCourse = this.repository.findManyLecturesByCourseId(object.getId());
		lectures.removeAll(lecturesInCourse);

		choices = SelectChoices.from(lectures, "title", null);

		tuple = super.unbind(object, "code", "title", "isPublished");
		tuple.put("lectures", choices.getSelected().getKey());
		tuple.put("lectures", choices);

		super.getResponse().setData(tuple);
	}

}
