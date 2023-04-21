
package acme.entities.configs;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Config extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 100)
	protected String			configKey;

	@NotBlank
	@Length(max = 2000)
	protected String			value;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
