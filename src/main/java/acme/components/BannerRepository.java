
package acme.components;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banner.Banner;
import acme.framework.helpers.MomentHelper;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BannerRepository extends AbstractRepository {

	@Query("select count(b) from Banner b")
	int countBanners();

	@Query("select b from Banner b where b.displayPeriodStart < :currentMoment and :currentMoment < b.displayPeriodEnd")
	List<Banner> findManyBanners(Date currentMoment, Pageable pageRequest);

	@Query("select b from Banner b")
	Collection<Banner> findBanner();

	@Query("select b from Banner b where b.id = :id")
	Banner findOneBannerById(int id);

	default Banner findRandomBanner() {
		Banner result;
		int count, index;
		ThreadLocalRandom random;
		Pageable page;
		List<Banner> list;
		Date currentMoment;
		currentMoment = MomentHelper.getCurrentMoment();

		count = this.countBanners();
		if (count == 0)
			result = null;
		else {
			random = ThreadLocalRandom.current();
			index = random.nextInt(0, count);

			page = PageRequest.of(index, 1);
			list = this.findManyBanners(currentMoment, page);
			result = list.isEmpty() ? null : list.get(0);
		}

		return result;
	}

}
