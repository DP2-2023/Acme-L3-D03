
package acme.entities.workbooks;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.enrolments.Enrolment;
import acme.framework.data.AbstractEntity;
import acme.roles.Student;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Workbook extends AbstractEntity {

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max = 75)
	protected String			title;

	@NotBlank
	@Length(max = 75)
	protected String			abstract$;

	protected WorkbookType		type;

	protected Double			timePeriod;

	@URL
	protected String			furtherInformation;

	protected boolean			published;

	// Relationships ----------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Enrolment			enrolment;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Student			student;

}
