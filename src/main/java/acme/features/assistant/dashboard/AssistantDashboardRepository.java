
package acme.features.assistant.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AssistantDashboardRepository extends AbstractRepository {

	@Query("select avg(t.estimatedTotalTime) from Tutorial t where t.assistant.id = :assistantId")
	Double averageTimeOfHisTutorials(int assistantId);

	@Query("select stddev(t.estimatedTotalTime) from Tutorial t where t.assistant.id = :assistantId")
	Double deviationTimeOfHisTutorials(int assistantId);

	@Query("select min(t.estimatedTotalTime) from Tutorial t where t.assistant.id = :assistantId")
	Double minimumTimeOfHisTutorials(int assistantId);

	@Query("select max(t.estimatedTotalTime) from Tutorial t where t.assistant.id = :assistantId")
	Double maximumTimeOfHisTutorials(int assistantId);

	@Query("select avg(s.periodEnd - s.periodStart) from Session s where s.tutorial.assistant.id = :assistantId")
	Double averageTimeOfHisSessions(int assistantId);

	@Query("select stddev(s.periodEnd - s.periodStart) from Session s where s.tutorial.assistant.id = :assistantId")
	Double deviationTimeOfHisSessions(int assistantId);

	@Query("select min(s.periodEnd - s.periodStart) from Session s where s.tutorial.assistant.id = :assistantId")
	Double minimumTimeOfHisSessions(int assistantId);

	@Query("select max(s.periodEnd - s.periodStart) from Session s where s.tutorial.assistant.id = :assistantId")
	Double maximumTimeOfHisSessions(int assistantId);

	@Query("select count(t) from Tutorial t where t.assistant.id = :assistantId and t.course.type in ('THEORY', 'HANDS_ON')")
	Double totalNumberOfTutorialsRegardingTheoryOrHandsonCourses(int assistantId);

}
