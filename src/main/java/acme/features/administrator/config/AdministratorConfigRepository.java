
package acme.features.administrator.config;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configs.Config;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorConfigRepository extends AbstractRepository {

	@Query("select a from Config a where a.id = :id")
	Config findOneConfigById(int id);

	@Query("select a from Config a")
	Collection<Config> findAllConfigs();

}
