
package acme.forms;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class assistantDashboards extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Double						totalNumberOfTutorialsRegardingTheoryOrHandsonCourses;
	Double						averageTimeOfHisSessions;
	Double						deviationTimeOfHisSessions;
	Double						minimumTimeOfHisSessions;
	Double						maximumTimeOfHisSessions;
	Double						averageTimeOfHisTutorials;
	Double						deviationTimeOfHisTutorials;
	Double						minimumTimeOfHisTutorials;
	Double						maximumTimeOfHisTutorials;

}
