
package acme.forms;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LecturerDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberOfTheoryLectures;
	Integer						totalNumberOfHandsOnLectures;
	Double						averageLearningTimePerLecture;
	Double						deviationLearningTimePerLecture;
	Double						minimumLearningTimePerLecture;
	Double						maximumLearningTimePerLecture;
	Double						averageLearningTimePerCourse;
	Double						deviationLearningTimePerCourse;
	Double						minimumLearningTimePerCourse;
	Double						maximumLearningTimePerCourse;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
