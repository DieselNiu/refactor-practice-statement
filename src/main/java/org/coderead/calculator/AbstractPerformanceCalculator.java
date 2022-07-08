package org.coderead.calculator;

import com.sun.xml.internal.ws.util.StringUtils;
import org.coderead.model.Performance;

public abstract class AbstractPerformanceCalculator {
	public static AbstractPerformanceCalculator getPerformanceCalculator(String type) {
		try {
			return (AbstractPerformanceCalculator) Class.forName(getPackageName() +
				"." +
				getName(type)).newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid type: " + type);
		}
	}

	private static String getPackageName() {
		return AbstractPerformanceCalculator.class.getPackage().getName();
	}

	private static String getName(String tragedy) {
		return StringUtils.capitalize(tragedy) +
			"Calculator";
	}

	public abstract double getVolumeCredits(Performance performance);

	public abstract double getAmount(Performance performance);
}
