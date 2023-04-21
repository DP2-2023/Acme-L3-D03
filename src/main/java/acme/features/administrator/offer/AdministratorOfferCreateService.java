
package acme.features.administrator.offer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.helpers.MomentHelper;
import acme.framework.services.AbstractService;

@Service
public class AdministratorOfferCreateService extends AbstractService<Administrator, Offer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorOfferRepository repository;

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
		Offer object;
		Date moment;

		moment = MomentHelper.getCurrentMoment();

		object = new Offer();
		//object.setMoment(moment);
		object.setPublished(false);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Offer object) {
		assert object != null;

		super.bind(object, "moment", "heading", "summary", "offerStartDate", "offerEndDate", "link");
	}

	@Override
	public void validate(final Offer object) {
		assert object != null;

		boolean confirmation;

		confirmation = super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	}

	@Override
	public void perform(final Offer object) {
		assert object != null;

		Date moment;

		moment = MomentHelper.getCurrentMoment();
		object.setMoment(moment);
		this.repository.save(object);
	}

	@Override
	public void unbind(final Offer object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "moment", "heading", "summary", "offerStartDate", "offerEndDate", "price", "link");
		tuple.put("confirmation", true);
		tuple.put("readonly", false);

		super.getResponse().setData(tuple);
	}
}
