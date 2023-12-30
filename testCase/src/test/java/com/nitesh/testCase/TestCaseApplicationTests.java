package com.nitesh.testCase;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestCaseApplicationTests {

	Calculator cal = new Calculator();
	
	@Test
	void contextLoads() {
	}

	@Test
	void testAddNumber() {
		int num1 = 20;
		int num2 = 25;
		int result = cal.add(num1, num2);
		assertThat(result).isEqualTo(45);
	}

	class Calculator {
		public int add(int a, int b) {
			return a + b;
		}
	}
}
