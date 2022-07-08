package org.coderead;

import org.coderead.calculator.AbstractPerformanceCalculator;
import org.coderead.model.Invoice;
import org.coderead.model.Performance;
import org.coderead.model.Play;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * 客户服务类
 *
 * @author kendoziyu
 * @since 2020/10/11 0011
 */
public class Statement {

	private Invoice invoice;
	private Map<String, Play> plays;

	public Statement(Invoice invoice, Map<String, Play> plays) {
		this.invoice = invoice;
		this.plays = plays;
	}

	public String show() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format("Statement for %s", invoice.getCustomer()));
		stringBuilder.append(getStringBuilder());
		stringBuilder.append(String.format("Amount owed is %s\n", formatUSD(invoice.getTotalAmount(plays))));
		stringBuilder.append(String.format("You earned %s credits\n", invoice.getVolumeCredits(plays)));
		return stringBuilder.toString();
	}

	private StringBuilder getStringBuilder() {
		StringBuilder builder = new StringBuilder();
		for (Performance performance : invoice.getPerformances()) {
			Play play = plays.get(performance.getPlayId());
			builder.append(String.format(" %s: %s (%d seats)\n", play.getName(), formatUSD(AbstractPerformanceCalculator.getPerformanceCalculator(play.getType()).getAmount(performance)), performance.getAudience()));
		}
		return builder;
	}

	private String formatUSD(double thisAmount) {
		return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(thisAmount / 100);
	}

}
