
package acme.entities.offers;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.components.datatypes.Money;
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

	@NotNull
	@Valid
	protected Money				price;

	@URL
	protected String			link;

	protected boolean			published;

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
