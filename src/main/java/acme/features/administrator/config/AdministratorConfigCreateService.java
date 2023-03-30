
package acme.features.administrator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configs.Config;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AdministratorConfigCreateService extends AbstractService<Administrator, Config> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorConfigRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Config object;

		object = new Config();
		object.setConfigKey("");
		object.setValue("");

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Config object) {
		assert object != null;

		super.bind(object, "title", "status", "text", "moreInfo");
	}

	@Override
	public void validate(final Config object) {
		assert object != null;

		boolean confirmation;

		confirmation = super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	}

	@Override
	public void perform(final Config object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Config object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "configKey", "value");

		super.getResponse().setData(tuple);
	}

}
