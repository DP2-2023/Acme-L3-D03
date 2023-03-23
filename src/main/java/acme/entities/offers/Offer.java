
package acme.entities.offers;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Offer extends AbstractEntity {

	//Serialisation identifier-----------------------------------------

	protected static final long	serialVersionUID	= 1L;

	//Attributes----------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date				moment;

	@NotBlank
	@Length(max = 75)
	protected String			heading;

	@NotBlank
	@Length(max = 75)
	protected String			summary;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date				offerStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date				offerEndDate;

	@Min(0)
	protected Double			price;

	@URL
	protected String			link;

	// Derived attributes -----------------------------------------------------


	@Transient
	public Double getPeriod() {

		final Double period;

		//en milisegundos
		final long diferencia = this.offerEndDate.getTime() - this.offerStartDate.getTime();

		//en dias 
		period = (double) (diferencia / (1000 * 60 * 60 * 24));

		return period;
	}

	// Relationships ----------------------------------------------------------

}
