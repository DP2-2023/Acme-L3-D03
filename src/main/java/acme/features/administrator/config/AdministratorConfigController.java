
package acme.features.administrator.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.configs.Config;
import acme.framework.components.accounts.Administrator;
import acme.framework.controllers.AbstractController;

@Controller
public class AdministratorConfigController extends AbstractController<Administrator, Config> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorConfigListService	listService;

	@Autowired
	protected AdministratorConfigShowService	showService;

	@Autowired
	protected AdministratorConfigCreateService	createService;

	@Autowired
	protected AdministratorConfigUpdateService	updateService;

	@Autowired
	protected AdministratorConfigDeleteService	deleteService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("list", this.listService);
	}

}
