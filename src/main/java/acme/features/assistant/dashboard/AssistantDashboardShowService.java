
package acme.features.assistant.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.AssistantDashboard;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Assistant;

@Service
public class AssistantDashboardShowService extends AbstractService<Assistant, AssistantDashboard> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AssistantDashboardRepository repository;

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
		AssistantDashboard assistantDashboards;

		Double totalNumberOfTutorialsRegardingTheoryOrHandsonCourses;
		Double averageTimeOfHisSessions;
		Double deviationTimeOfHisSessions;
		Double minimumTimeOfHisSessions;
		Double maximumTimeOfHisSessions;
		Double averageTimeOfHisTutorials;
		Double deviationTimeOfHisTutorials;
		Double minimumTimeOfHisTutorials;
		Double maximumTimeOfHisTutorials;
		int assistantId;

		assistantId = super.getRequest().getPrincipal().getActiveRoleId();

		totalNumberOfTutorialsRegardingTheoryOrHandsonCourses = this.repository.totalNumberOfTutorialsRegardingTheoryOrHandsonCourses(assistantId);
		averageTimeOfHisSessions = this.repository.averageTimeOfHisSessions(assistantId);
		deviationTimeOfHisSessions = this.repository.deviationTimeOfHisSessions(assistantId);
		minimumTimeOfHisSessions = this.repository.minimumTimeOfHisSessions(assistantId);
		maximumTimeOfHisSessions = this.repository.maximumTimeOfHisSessions(assistantId);
		averageTimeOfHisTutorials = this.repository.averageTimeOfHisTutorials(assistantId);
		deviationTimeOfHisTutorials = this.repository.deviationTimeOfHisTutorials(assistantId);
		minimumTimeOfHisTutorials = this.repository.minimumTimeOfHisTutorials(assistantId);
		maximumTimeOfHisTutorials = this.repository.maximumTimeOfHisTutorials(assistantId);

		assistantDashboards = new AssistantDashboard();
		assistantDashboards.setTotalNumberOfTutorialsRegardingTheoryOrHandsonCourses(totalNumberOfTutorialsRegardingTheoryOrHandsonCourses);
		assistantDashboards.setAverageTimeOfHisSessions(averageTimeOfHisSessions);
		assistantDashboards.setDeviationTimeOfHisSessions(deviationTimeOfHisSessions);
		assistantDashboards.setMinimumTimeOfHisSessions(minimumTimeOfHisSessions);
		assistantDashboards.setMaximumTimeOfHisSessions(maximumTimeOfHisSessions);
		assistantDashboards.setAverageTimeOfHisTutorials(averageTimeOfHisTutorials);
		assistantDashboards.setDeviationTimeOfHisTutorials(deviationTimeOfHisTutorials);
		assistantDashboards.setMinimumTimeOfHisTutorials(minimumTimeOfHisTutorials);
		assistantDashboards.setMaximumTimeOfHisTutorials(maximumTimeOfHisTutorials);

		super.getBuffer().setData(assistantDashboards);
	}

	@Override
	public void unbind(final AssistantDashboard object) {
		Tuple tuple;

		tuple = super.unbind(object, //
			"totalNumberOfTutorialsRegardingTheoryOrHandsonCourses", "averageTimeOfHisSessions", // 
			"deviationTimeOfHisSessions", "minimumTimeOfHisSessions", //
			"maximumTimeOfHisSessions", "averageTimeOfHisTutorials", "deviationTimeOfHisTutorials", //
			"minimumTimeOfHisTutorials", "maximumTimeOfHisTutorials");

		super.getResponse().setData(tuple);
	}

}
