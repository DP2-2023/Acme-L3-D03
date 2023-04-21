
package acme.features.authenticated.notes;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notes.Note;
import acme.framework.components.accounts.Authenticated;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;

@Service
public class AuthenticatedNotesCreateService extends AbstractService<Authenticated, Note> {

	@Autowired
	protected AuthenticatedNotesRepository notesRepository;


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
		Note object;
		Date moment;

		moment = MomentHelper.getCurrentMoment();

		object = new Note();
		object.setTitle("");
		object.setMoment(moment);
		object.setAuthor("");
		object.setMessage("");
		object.setEmail("");
		object.setLink("");

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Note object) {
		assert object != null;

		super.bind(object, "moment", "title", "author", "message", "email", "link");
	}

	@Override
	public void validate(final Note object) {
		assert object != null;
		boolean confirmation;

		confirmation = super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");

	}

	@Override
	public void perform(final Note object) {
		assert object != null;

		this.notesRepository.save(object);
	}

	@Override
	public void unbind(final Note object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "moment", "title", "author", "message", "email", "link");

		tuple.put("confirmation", true);

		super.getResponse().setData(tuple);
	}

}
