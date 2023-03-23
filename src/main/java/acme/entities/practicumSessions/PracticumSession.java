
package acme.entities.practicumSessions;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.practicums.Practicum;
import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PracticumSession extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 100)
	protected String			abstract$;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date				sessionStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date				sessionEndDate;

	@URL
	protected String			link;

	// Derived attributes -----------------------------------------------------


	@Transient
	public Double getPeriod() {

		final Double period;

		//en milisegundos
		final long diferencia = this.sessionEndDate.getTime() - this.sessionStartDate.getTime();

		//en dias 
		period = (double) (diferencia / (1000 * 60 * 60 * 24));

		return period;
	}

	// Relationships ----------------------------------------------------------


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	protected Practicum practicum;
}
