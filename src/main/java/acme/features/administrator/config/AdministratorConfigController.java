
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
	protected AdministratorConfigUpdateService	updateService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("list", this.listService);
	}

}
