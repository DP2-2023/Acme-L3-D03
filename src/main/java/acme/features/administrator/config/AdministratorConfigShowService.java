
package acme.features.administrator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configs.Config;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AdministratorConfigShowService extends AbstractService<Administrator, Config> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorConfigRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Config object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneConfigById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Config object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "configKey", "value");

		super.getResponse().setData(tuple);
	}

}
