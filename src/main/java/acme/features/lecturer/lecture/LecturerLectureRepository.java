/*
 * LecturerLectureRepository.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.lecturer.lecture;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.courses.Course;
import acme.entities.lectures.Lecture;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Lecturer;

@Repository
public interface LecturerLectureRepository extends AbstractRepository {

	@Query("select l from Lecture l where l.id = :id")
	Lecture findOneLectureById(int id);

	@Query("select l from Lecturer l where l.id = :id")
	Lecturer findOneLecturerById(int id);

	@Query("select l from Lecture l where l.lecturer.id = :lecturerId")
	Collection<Lecture> findManyLecturesByLecturerId(int lecturerId);

	@Query("select c from Course c where c.id = :id")
	Course findOneCourseById(int id);

	@Query("select c from CourseLecture cl join cl.course c where cl.lecture.id = :lectureId")
	Collection<Course> findManyCoursesByLectureId(int lectureId);

	@Query("select l from CourseLecture cl join cl.lecture l where cl.course.id = :courseId and l.lecturer.id = :lecturerId")
	Collection<Lecture> findManyLecturesByCourseIdAndLecturerId(int courseId, int lecturerId);

	@Query("select count(cl) from CourseLecture cl where cl.lecture.id = :lectureId")
	Integer numberOfCoursesOfLecture(int lectureId);

	@Query("select c.value from Config c where c.configKey = :key")
	String findOneConfigByKey(String key);

}
