/*
 * StudentCourseRepository.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.student.enrolment;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.courses.Course;
import acme.entities.courses.CourseLecture;
import acme.entities.enrolments.Enrolment;
import acme.entities.lectures.Lecture;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Lecturer;

@Repository
public interface StudentEnrolmentRepository extends AbstractRepository {

	@Query("select j from Course j where j.id = :id")
	Course findOneCourseById(int id);

	@Query("select j from Enrolment j where j.student.id = :id")
	Collection<Enrolment> findAllEnrolments(int id);

	@Query("select cl.lecture from CourseLecture cl where cl.course=:id")
	Lecture findOneLectureByCourseId(int id);

	@Query("select cl.lecture.lecturer from CourseLecture cl where cl.course=:id")
	Lecturer findOneLecturerByCourseId(int id);

	@Query("select cl from CourseLecture cl where cl.course.id=:id")
	Collection<CourseLecture> findOneCourseLectureByCourseId(int id);

}
