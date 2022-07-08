package org.coderead;

import org.coderead.calculator.TragedyCalculator;
import org.coderead.model.Performance;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TragedyCalculatorTest {
	@Test
	public void should_get_credits(){
		TragedyCalculator tragedyCalculator = new TragedyCalculator();
		Performance performance = new Performance();
		performance.setAudience(40);
		double volumeCredits = tragedyCalculator.getVolumeCredits(performance);
		assertThat(volumeCredits).isEqualTo(10.0);
	}

}