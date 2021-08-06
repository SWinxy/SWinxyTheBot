package me.swinxy;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

import static me.swinxy.RemoveEnglishCiteParameter.apply;

public class TestENUSRemoval {

	/**
	 * Each item here <b>must</b> not match the regex,
	 * and should be returned exactly.
	 */
	private static final String[] americanEquals = new String[]{
			"{{}}",
			"{{language=en}}",
			"language=enUS"
	};

	/**
	 * Test strings that are the same inputted as they are outputted.
	 */
	@Test
	public void testAmericanEnglish() {
		for (String test : americanEquals) {
			Assertions.assertEquals(test, apply(test));
		}
	}

	private static final Map<String, String> amNotEq = Map.of(
			"{{|language = en}}", "{{}}",
			"|   \n\tlang = enUS", ""
	);

	@Test
	public void testAmEng2() {
		amNotEq.forEach((input, expected) -> Assertions.assertEquals(expected, apply(input)));
	}
}
