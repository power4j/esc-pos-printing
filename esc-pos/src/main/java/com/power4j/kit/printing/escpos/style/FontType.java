package com.power4j.kit.printing.escpos.style;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.function.Function;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
public enum FontType {

	/**
	 * FONT_A
	 */
	FONT_A("a"),
	/**
	 * FONT_B
	 */
	FONT_B("b"),
	/**
	 * FONT_C
	 */
	FONT_C("c");

	private final String value;

	FontType(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	/**
	 * 解析
	 *
	 * @param value    被解析的数据,可以是null
	 * @param defValue 默认值
	 * @return 如果解析失败返回默认值
	 */
	public static FontType parseOrDefault(final String value, final FontType defValue) {
		if (value == null) {
			return defValue;
		}
		for (FontType o : FontType.values()) {
			if (o.getValue().equals(value)) {
				return o;
			}
		}
		return defValue;
	}

	/**
	 * 解析
	 *
	 * @param value 被解析的数据
	 * @return 如果解析失败返回 null
	 */
	@JsonCreator
	public static FontType parseOrNull(final String value) {
		return parseOrDefault(value, null);
	}

	/**
	 * 解析
	 *
	 * @param value   被解析的数据
	 * @param thrower 异常抛出器
	 * @return 如果解析失败抛出异常
	 */
	public static FontType parseOrThrow(final String value, Function<String, RuntimeException> thrower) {
		FontType o = parseOrDefault(value, null);
		if (o == null) {
			throw thrower.apply(value);
		}
		return o;
	}

	/**
	 * 解析
	 *
	 * @param value 被解析的数据
	 * @return 如果解析失败抛出 IllegalArgumentException
	 */
	public static FontType parse(final String value) throws IllegalArgumentException {
		return parseOrThrow(value, (v) -> new IllegalArgumentException("Invalid value : " + v));
	}

}
