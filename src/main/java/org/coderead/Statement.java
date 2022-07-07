package org.coderead;

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

	private final IPerformanceCalculator tragedyCalculator = new TragedyCalculator();
	private final IPerformanceCalculator comedyCalculator = new ComedyCalculator();
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
		stringBuilder.append(String.format("Amount owed is %s\n", formatUSD(getTotalAmount())));
		stringBuilder.append(String.format("You earned %s credits\n", getVolumeCredits()));
		return stringBuilder.toString();
	}

	private StringBuilder getStringBuilder() {
		StringBuilder builder = new StringBuilder();
		for (Performance performance : invoice.getPerformances()) {
			Play play = plays.get(performance.getPlayId());
			builder.append(String.format(" %s: %s (%d seats)\n", play.getName(), formatUSD(getThisAmount(performance, play)), performance.getAudience()));
		}
		return builder;
	}

	private int getVolumeCredits() {
		int volumeCredits = 0;
		for (Performance performance : invoice.getPerformances()) {
			Play play = plays.get(performance.getPlayId());
			volumeCredits += getVolumeCredits(performance, play);
		}
		return volumeCredits;
	}

	private int getTotalAmount() {
		int totalAmount = 0;
		for (Performance performance : invoice.getPerformances()) {
			Play play = plays.get(performance.getPlayId());
			totalAmount += getThisAmount(performance, play);
		}
		return totalAmount;
	}

	private double getVolumeCredits(Performance performance, Play play) {
		return getPerformanceCalculator(play).getVolumeCredits(performance);
	}

	private IPerformanceCalculator getPerformanceCalculator(Play play) {
		IPerformanceCalculator calculator = null;
		if ("tragedy".equals(play.getType())) {
			calculator = tragedyCalculator;
		}
		if ("comedy".equals(play.getType())) {
			calculator = comedyCalculator;
		}
		return calculator;
	}

	private double getThisAmount(Performance performance, Play play) {
		return getPerformanceCalculator(play).getAmount(performance);
	}

	private String formatUSD(double thisAmount) {
		return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(thisAmount / 100);
	}

}
