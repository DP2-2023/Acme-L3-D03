
package acme.features.any.peep;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.peeps.Peep;
import acme.framework.helpers.MomentHelper;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyPeepRepository extends AbstractRepository {

	@Query("select count(p) from Peep p")
	int countPeeps();

	@Query("select p from Peep p")
	List<Peep> findManyPeeps(Date currentMoment, Pageable pageRequest);

	@Query("select p from Peep p")
	Collection<Peep> findPeep();

	@Query("select p from Peep p where p.id = :id")
	Peep findOnePeepById(int id);

	default Peep findRandomPeep() {
		Peep result;
		int count;
		int index;
		ThreadLocalRandom random;
		Pageable page;
		List<Peep> list;
		Date currentMoment;
		currentMoment = MomentHelper.getCurrentMoment();

		count = this.countPeeps();
		if (count == 0)
			result = null;
		else {
			random = ThreadLocalRandom.current();
			index = random.nextInt(0, count);

			page = PageRequest.of(index, 1);
			list = this.findManyPeeps(currentMoment, page);
			result = list.isEmpty() ? null : list.get(0);
		}

		return result;
	}

}
