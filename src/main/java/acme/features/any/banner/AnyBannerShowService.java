
package acme.features.any.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.BannerRepository;
import acme.entities.banner.Banner;
import acme.framework.components.accounts.Any;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AnyBannerShowService extends AbstractService<Any, Banner> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected BannerRepository repository;

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
		int id;
		Banner banner;

		id = super.getRequest().getData("id", int.class);
		banner = this.repository.findOneBannerById(id);
		status = banner != null;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Banner object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneBannerById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "moment", "displayPeriodStart", "displayPeriodEnd", "linkPicture", "slogan", "linkTarget");

		super.getResponse().setData(tuple);
	}

}
