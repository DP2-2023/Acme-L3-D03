
package acme.utils;

import java.util.HashMap;
import java.util.Map;

import acme.framework.components.datatypes.Money;

public class CurrencyExchange {

	private final Map<String, Double>	rates;
	private final String				systemCurrency;


	public CurrencyExchange(final String ratesString, final String systemCurrency) {
		super();

		final Map<String, Double> rates = new HashMap<>();
		final String[] ratePairs = ratesString.split(",");
		for (final String ratePair : ratePairs) {
			final String[] rateParts = ratePair.split(":");
			final String currency = rateParts[0];
			final double rate = Double.parseDouble(rateParts[1]);
			rates.put(currency, rate);
		}
		this.rates = rates;
		this.systemCurrency = systemCurrency;
	}

	public Money exchange(final Money source) {
		if (!this.rates.containsKey(source.getCurrency()))
			return null;
		final Money target = new Money();
		target.setAmount(source.getAmount() * this.rates.get(source.getCurrency()));
		target.setCurrency(this.systemCurrency);
		return target;
	}

	public boolean isAcceptedCurrency(final Money source) {
		return this.rates.containsKey(source.getCurrency());
	}

	public Map<String, Double> getRates() {
		return this.rates;
	}

	public String getSystemCurrency() {
		return this.systemCurrency;
	}

}
