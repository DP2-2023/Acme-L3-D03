
package acme.features.any.peep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.peeps.Peep;
import acme.framework.components.accounts.Any;
import acme.framework.services.AbstractService;

@Service
public class AnyPeepPublishService extends AbstractService<Any, Peep> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyPeepRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		boolean status;
		int peepId;
		Peep peep;

		peepId = super.getRequest().getData("id", int.class);
		peep = this.repository.findOnePeepById(peepId);

		status = peep != null;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Peep object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOnePeepById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Peep object) {
		assert object != null;

		// Bind the text field
		super.bind(object, "moment", "title", "messages", "email", "link");

		// Set the nick based on the principal's authentication status
		if (super.getRequest().getPrincipal().isAuthenticated())
			object.setNick(super.getRequest().getPrincipal().getUsername());
		else
			object.setNick("");
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
		super.getResponse().setData(super.unbind(object, "moment", "title", "nick", "messages", "email", "link"));
	}
}
