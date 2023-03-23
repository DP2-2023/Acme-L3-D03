
package forms;

import java.util.Map;

import acme.framework.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard extends AbstractForm {

	protected static final long	serialVersionUID	= 1L;

	Map<String, Integer>		totalNumberOfPrincipalsPerRole;
	Double						ratioOfPeepsWithEmailAndLink;
	Double						ratioOfCriticalAndNonCriticalBulletins;
	Map<String, Double>			averageBudgetPerOfferPerCurrency;
	Map<String, Double>			minimumBudgetPerOfferPerCurrency;
	Map<String, Double>			maximumBudgetPerOfferPerCurrency;
	Map<String, Double>			desviationBudgetPerOfferPerCurrency;
	Double						averageNumberOfNotesLastTenWeeks;
	Double						minimumNumberOfNotesLastTenWeeks;
	Double						maximumNumberOfNotesLastTenWeeks;
	Double						desviationNumberOfNotesLastTenWeeks;

}
