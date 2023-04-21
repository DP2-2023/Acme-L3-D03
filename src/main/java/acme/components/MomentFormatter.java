
package acme.components;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

public class MomentFormatter implements Formatter<Date> {

	// Formatter<Money> interface ---------------------------------------------

	@Override
	public String print(final Date object, final Locale locale) {
		assert object != null;
		assert locale != null;

		final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);

		return dateFormat.format(object);
	}

	@Override
	public Date parse(final String text, final Locale locale) {
		final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
		Date date = null;
		try {
			date = dateFormat.parse(text);
		} catch (final ParseException | java.text.ParseException e) {
			System.out.println("Error parsing date: " + e.getMessage());
		}

		return date;
	}
}
