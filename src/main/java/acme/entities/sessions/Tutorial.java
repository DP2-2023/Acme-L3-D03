
package acme.entities.sessions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.framework.data.AbstractEntity;
import acme.roles.Assistant;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tutorial extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "[A-Z]{1,3}[0-9][0-9]{3}", message = "{validation.tutorial.code}")
	protected String			code;

	@NotBlank
	@Length(min = 0, max = 75)
	protected String			title;

	@NotBlank
	@Length(min = 0, max = 100)
	protected String			resume;

	@NotBlank
	@Length(min = 0, max = 100)
	protected String			goals;

	protected Double			estimatedTotalTime;

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Assistant			assistant;

	//@NotNull
	//@Valid
	//@ManyToOne(optional = false)
	//protected course			course;

}
