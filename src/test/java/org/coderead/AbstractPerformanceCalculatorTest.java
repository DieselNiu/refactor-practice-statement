package org.coderead;

import org.coderead.calculator.AbstractPerformanceCalculator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AbstractPerformanceCalculatorTest {
	@Test
	public void should_throw_exception_when_type_not_existed() {
		IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> AbstractPerformanceCalculator.getPerformanceCalculator("not-existed-type"));
		assertThat(illegalArgumentException.getMessage()).isEqualTo("Invalid type: not-existed-type");
	}

}