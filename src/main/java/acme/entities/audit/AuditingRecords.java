
package acme.entities.audit;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditingRecords extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 75)
	protected String			subject;

	@NotBlank
	@Length(max = 100)
	protected String			assessment;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				firstDate;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				lastDate;

	protected Mark				mark;
	@URL
	protected String			link;

	// Derived attributes -----------------------------------------------------


	@Transient
	public Double periodBetween(final Date d1, final Date d2) {
		final LocalDateTime ldt1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		final LocalDateTime ldt2 = d2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		final Duration d = Duration.between(ldt1, ldt2);
		return 1.0 * d.toHours() + d.toMinutes() % 60;
	}


	// Relationships ----------------------------------------------------------
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Audit audit;
}
