
package acme.features.any.banner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.BannerRepository;
import acme.entities.banner.Banner;
import acme.framework.components.accounts.Any;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;

@Service
public class AnyBannerListService extends AbstractService<Any, Banner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected BannerRepository repository;


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}
	// AbstractService interface ----------------------------------------------
	@Override
	public void load() {
		Collection<Banner> objects;

		objects = this.repository.findBanner();

		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "slogan", "moment");

		super.getResponse().setData(tuple);
	}

}
