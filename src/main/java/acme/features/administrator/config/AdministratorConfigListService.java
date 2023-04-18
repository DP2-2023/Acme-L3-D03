
package acme.features.administrator.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configs.Config;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AdministratorConfigListService extends AbstractService<Administrator, Config> {

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
		Collection<Config> objects;

		objects = this.repository.findAllConfigs();

		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final Config object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "configKey", "value");

		super.getResponse().setData(tuple);
	}

}
