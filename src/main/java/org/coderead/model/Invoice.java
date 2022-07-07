package org.coderead.model;

import org.coderead.AbstractPerformanceCalculator;

import java.util.List;
import java.util.Map;

/**
 * 发票
 *
 * @author kendoziyu
 * @since 2020/10/11 0011
 */
public class Invoice {

    private String customer;

    private List<Performance> performances;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

	public int getVolumeCredits(Map<String, Play> plays1) {
		int volumeCredits = 0;
		for (Performance performance : getPerformances()) {
			Play play = plays1.get(performance.getPlayId());
			volumeCredits += AbstractPerformanceCalculator.getPerformanceCalculator(play.getType()).getVolumeCredits(performance);
		}
		return volumeCredits;
	}

	public int getTotalAmount(Map<String, Play> plays1) {
		int totalAmount = 0;
		for (Performance performance : getPerformances()) {
			Play play = plays1.get(performance.getPlayId());
			totalAmount += AbstractPerformanceCalculator.getPerformanceCalculator(play.getType()).getAmount(performance);
		}
		return totalAmount;
	}
}
