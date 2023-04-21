/*
 * AuthenticatedCompanyRepository.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.company.practicumSession;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.practicumSessions.PracticumSession;
import acme.entities.practicums.Practicum;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Company;

@Repository
public interface CompanyPracticumSessionRepository extends AbstractRepository {

	@Query("select ps from PracticumSession ps where ps.id = :id")
	PracticumSession findOnePracticumSessionById(int id);

	@Query("select c from Company c where c.id = :id")
	Company findOneCompanyById(int id);

	@Query("select p from Practicum p where p.id = :id")
	Practicum findOnePracticumById(int id);

	@Query("select p from Practicum p where p.company.id = :companyId")
	Collection<Practicum> findManyPracticumByCompanyId(int companyId);

	@Query("select ps from PracticumSession ps where ps.isPublished = true")
	Collection<PracticumSession> findAllPracticumSession();

	@Query("select ps from PracticumSession ps where ps.isAddendum = true")
	Collection<PracticumSession> findPracticumAddendumSession();

}
