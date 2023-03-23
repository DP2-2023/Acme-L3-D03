
package acme.entities.banner;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banner extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@PastOrPresent
	@NotNull
	protected Date				moment;

	@NotNull
	protected Date				displayPeriodStart;

	@NotNull
	protected Date				displayPeriodEnd;

	@NotBlank
	@URL
	protected String			linkPicture;

	@NotBlank
	@Length(min = 0, max = 75)
	protected String			slogan;

	@NotBlank
	@URL
	protected String			linkTarget;
}
