/*
 * LecturerCourseRepository.java
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

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.courses.Course;
import acme.entities.courses.CourseLecture;
import acme.entities.lectures.Lecture;
import acme.entities.lectures.LectureType;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Lecturer;

@Repository
public interface LecturerCourseRepository extends AbstractRepository {

	@Query("select c from Course c where c.id = :id")
	Course findOneCourseById(int id);

	@Query("select c from Course c where c.code = :code")
	Course findOneCourseByCode(String code);

	@Query("select l from Lecturer l where l.id = :id")
	Lecturer findOneLecturerById(int id);

	@Query("select l from Lecture l where l.id = :id")
	Lecture findOneLectureById(int id);

	@Query("select c from CourseLecture cl join cl.lecture l join cl.course c where l.lecturer.id = :lecturerId")
	Collection<Course> findManyCoursesByLecturerId(int lecturerId);

	@Query("select l from CourseLecture cl join cl.lecture l where cl.course.id = :courseId")
	Collection<Lecture> findManyLecturesByCourseId(int courseId);

	@Query("select l from Lecture l where l.lecturer.id = :lecturerId")
	Collection<Lecture> findManyLecturesByLecturerId(int lecturerId);

	@Query("select l from CourseLecture cl join cl.lecture l where cl.course.id = :courseId and l.type = :lectureType")
	Collection<Lecture> findManyLecturesByCourseIdAndLectureType(int courseId, LectureType lectureType);

	@Query("select l from CourseLecture cl join cl.lecture l where cl.course.id = :courseId and l.isPublished = :isPublished")
	Collection<Lecture> findManyLecturesByCourseIdAndIsPublished(int courseId, boolean isPublished);

	@Query("select cl from CourseLecture cl where cl.course.id = :courseId and cl.lecture.id = :lectureId")
	CourseLecture findOneCourseLectureByCourseIdAndLectureId(int courseId, int lectureId);

	@Query("select count(p) from Practicum p join p.course c where c.id = :courseId")
	Integer numberOfPracticaOfCourse(int courseId);

	@Query("select c.value from Config c where c.configKey = :key")
	String findOneConfigByKey(String key);
}
