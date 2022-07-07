package org.coderead;

import org.coderead.model.Performance;

public abstract class AbstractPerformanceCalculator {
	public static AbstractPerformanceCalculator getPerformanceCalculator(String type) {
		AbstractPerformanceCalculator calculator = null;
		if ("tragedy".equals(type)) {
			calculator = new TragedyCalculator();
		}
		if ("comedy".equals(type)) {
			calculator = new ComedyCalculator();
		}
		return calculator;
	}

	public abstract double getVolumeCredits(Performance performance);

	public abstract double getAmount(Performance performance);
}
