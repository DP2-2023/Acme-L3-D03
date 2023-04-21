/*
 * MoneyFormatter.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.components;

import java.util.Locale;

import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

public class BooleanFormatter implements Formatter<Boolean> {

	@Override
	public String print(final Boolean object, final Locale locale) {
		assert object != null;
		assert locale != null;
		String result;
		if (locale.getLanguage().equals("es"))
			result = object ? "Verdadero" : "Falso";
		else
			result = object ? "True" : "False";

		return result;
	}

	@Override
	public Boolean parse(final String text, final Locale locale) throws ParseException {

		return Boolean.valueOf(text);
	}
}
