package com.power4j.kit.printing.escpos.style;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.function.Function;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
public enum FontSize {
	/**
	 * SIZE_1
	 */
	SIZE_1("1"),
	/**
	 * SIZE_2
	 */
	SIZE_2("2"),
	/**
	 * SIZE_3
	 */
	SIZE_3("3"),
	/**
	 * SIZE_4
	 */
	SIZE_4("4"),
	/**
	 * SIZE_5
	 */
	SIZE_5("5"),
	/**
	 * SIZE_6
	 */
	SIZE_6("6"),
	/**
	 * SIZE_7
	 */
	SIZE_7("7"),
	/**
	 * SIZE_8
	 */
	SIZE_8("8");

	private final String value;

	FontSize(String value) {
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
	public static FontSize parseOrDefault(final String value, final FontSize defValue) {
		if (value == null) {
			return defValue;
		}
		for (FontSize o : FontSize.values()) {
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
	public static FontSize parseOrNull(final String value) {
		return parseOrDefault(value, null);
	}

	/**
	 * 解析
	 *
	 * @param value   被解析的数据
	 * @param thrower 异常抛出器
	 * @return 如果解析失败抛出异常
	 */
	public static FontSize parseOrThrow(final String value, Function<String, RuntimeException> thrower) {
		FontSize o = parseOrDefault(value, null);
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
	public static FontSize parse(final String value) throws IllegalArgumentException {
		return parseOrThrow(value, (v) -> new IllegalArgumentException("Invalid value : " + v));
	}

}
