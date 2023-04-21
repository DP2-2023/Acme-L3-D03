/*
 * StudentWorkbookRepository.java
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

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workbooks.Workbook;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Student;

@Repository
public interface StudentWorkbookRepository extends AbstractRepository {

	@Query("select w from Workbook w where w.id = :id")
	Workbook findOneWorkbookById(int id);

	@Query("select s from Student s where s.id = :id")
	Student findOneStudentById(int id);

	@Query("select w from Workbook w where w.student.id = :studentId")
	Collection<Workbook> findManyWorkbooksByStudentId(int studentId);

}
