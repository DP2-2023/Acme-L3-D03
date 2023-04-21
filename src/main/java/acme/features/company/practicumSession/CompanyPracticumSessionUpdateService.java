/*
 * PracticumPracticumSessionUpdateService.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.practicumSessions.PracticumSession;
import acme.entities.practicums.Practicum;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Company;

@Service
public class CompanyPracticumSessionUpdateService extends AbstractService<Company, PracticumSession> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected CompanyPracticumSessionRepository repository;

	// AbstractService<Practicum, PracticumSession> -------------------------------------


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
		PracticumSession practicumSession;
		final Practicum practicum;

		practicum = this.repository.findOnePracticumById(super.getRequest().getPrincipal().getActiveRoleId());

		masterId = super.getRequest().getData("id", int.class);
		practicumSession = this.repository.findOnePracticumSessionById(masterId);

		status = practicumSession != null && !practicumSession.isPublished() && practicum != null;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		PracticumSession object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOnePracticumSessionById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final PracticumSession object) {
		assert object != null;

		super.bind(object, "title", "abstract$", "price", "furtherInformation");
	}

	@Override
	public void validate(final PracticumSession object) {
		assert object != null;

	}

	@Override
	public void perform(final PracticumSession object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final PracticumSession object) {
		assert object != null;

		final int companyId;
		Collection<Practicum> practicums;
		SelectChoices choices;

		Tuple tuple;

		companyId = super.getRequest().getPrincipal().getActiveRoleId();
		practicums = this.repository.findManyPracticumByCompanyId(companyId);

		choices = SelectChoices.from(practicums, "title", null);

		tuple = super.unbind(object, "title", "abstract$", "sessionStartDate", "sessionEndDate", "link", "isPublished", "isAddendum");
		tuple.put("practicum", choices.getSelected().getKey());
		tuple.put("practicums", choices);

		super.getResponse().setData(tuple);
	}
}
