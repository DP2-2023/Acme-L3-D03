/*
 * AdministratorDashboardRepository.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.lecturer.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface LecturerDashboardRepository extends AbstractRepository {

	@Query("select count(l) from Lecture l where l.type = 'THEORETICAL' and l.lecturer.id = :lecturerId")
	Integer totalNumberOfTheoryLectures(int lecturerId);

	@Query("select count(l) from Lecture l where l.type = 'HANDS_ON' and l.lecturer.id = :lecturerId")
	Integer totalNumberOfHandsOnLectures(int lecturerId);

	@Query("select avg(l.learningTime) from Lecture l where l.lecturer.id = :lecturerId")
	Double averageLearningTimePerLecture(int lecturerId);

	@Query("select stddev(l.learningTime) from Lecture l where l.lecturer.id = :lecturerId")
	Double deviationLearningTimePerLecture(int lecturerId);

	@Query("select min(l.learningTime) from Lecture l where l.lecturer.id = :lecturerId")
	Double minimumLearningTimePerLecture(int lecturerId);

	@Query("select max(l.learningTime) from Lecture l where l.lecturer.id = :lecturerId")
	Double maximumLearningTimePerLecture(int lecturerId);

	@Query("select avg(l.learningTime) from CourseLecture cl join cl.lecture l join cl.course c where l.lecturer.id = :lecturerId")
	Double averageLearningTimePerCourse(int lecturerId);

	@Query("select stddev(l.learningTime) from CourseLecture cl join cl.lecture l join cl.course c where l.lecturer.id = :lecturerId")
	Double deviationLearningTimePerCourse(int lecturerId);

	@Query("select min(l.learningTime) from CourseLecture cl join cl.lecture l join cl.course c where l.lecturer.id = :lecturerId")
	Double minimumLearningTimePerCourse(int lecturerId);

	@Query("select max(l.learningTime) from CourseLecture cl join cl.lecture l join cl.course c where l.lecturer.id = :lecturerId")
	Double maximumLearningTimePerCourse(int lecturerId);

}
