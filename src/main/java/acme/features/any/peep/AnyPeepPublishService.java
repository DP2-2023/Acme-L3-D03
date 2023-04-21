
package acme.features.any.peep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.peeps.Peep;
import acme.framework.components.accounts.Any;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;

@Service
public class AnyPeepPublishService extends AbstractService<Any, Peep> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyPeepRepository repository;

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
		final Peep object = new Peep();

		if (super.getRequest().getPrincipal().isAuthenticated())
			object.setNick(super.getRequest().getPrincipal().getUsername());

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Peep object) {
		assert object != null;

		// Bind the text field
		super.bind(object, "title", "nick", "message", "email", "link");
		object.setMoment(MomentHelper.getCurrentMoment());

	}

	@Override
	public void validate(final Peep object) {
		// No need to validate anything for publishing a peep
	}

	@Override
	public void perform(final Peep object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Peep object) {
		assert object != null;

		// Return the peep object with the updated nick and text fields
		super.getResponse().setData(super.unbind(object, "moment", "title", "nick", "message", "email", "link"));
	}
}
