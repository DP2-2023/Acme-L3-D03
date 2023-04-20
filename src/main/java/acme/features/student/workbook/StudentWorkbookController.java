/*
 * StudentWorkbookController.java
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

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.workbooks.Workbook;
import acme.framework.controllers.AbstractController;
import acme.roles.Student;

@Controller
public class StudentWorkbookController extends AbstractController<Student, Workbook> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected StudentWorkbookShowService		showService;

	@Autowired
	protected StudentWorkbookCreateService		createService;

	@Autowired
	protected StudentWorkbookUpdateService		updateService;

	@Autowired
	protected StudentWorkbookDeleteService		deleteService;

	@Autowired
	protected StudentWorkbookListMineService	listMineService;

	@Autowired
	protected StudentWorkbookPublishService		publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);

		super.addCustomCommand("list-mine", "list", this.listMineService);
		super.addCustomCommand("publish", "update", this.publishService);
	}

}
