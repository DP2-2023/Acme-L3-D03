package forms;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDashboard extends AbstractForm{
	
	protected static final long serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------

	Integer	totalNumberOfTheoryWorbook;
	Integer	totalNumberOfHandsOnWorbook;
	Double	averagePeriodOfTheActivitiesPerWorbook;
	Double	minimunPeriodOfTheActivitiesPerWorbook;
	Double	maxumumPeriodOfTheActivitiesPerHerWorbook;
	Double	desviationOfPeriodOfTheActivitiesPerWorbook;	
	Double	averageLearningTimePerCourse;
	Double	desviationLearningTimePerCourse;
	Double	minimunLearningTimePerCourse;
	Double	maximumLearningTimePerCourse;


}
