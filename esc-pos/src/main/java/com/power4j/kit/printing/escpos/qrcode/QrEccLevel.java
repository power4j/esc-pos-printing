package com.power4j.kit.printing.escpos.qrcode;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.function.Function;

/**
 * 纠错等级
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
public enum QrEccLevel {

	/**
	 * ECC_L 7%
	 */
	ECC_L("l"),
	/**
	 * ECC_M 15%
	 */
	ECC_M("m"),
	/**
	 * ECC_Q 25%
	 */
	ECC_Q("q"),
	/**
	 * ECC_H 30%
	 */
	ECC_H("h");

	private final String value;

	QrEccLevel(String value) {
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
	public static QrEccLevel parseOrDefault(final String value, final QrEccLevel defValue) {
		if (value == null) {
			return defValue;
		}
		for (QrEccLevel o : QrEccLevel.values()) {
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
	public static QrEccLevel parseOrNull(final String value) {
		return parseOrDefault(value, null);
	}

	/**
	 * 解析
	 *
	 * @param value   被解析的数据
	 * @param thrower 异常抛出器
	 * @return 如果解析失败抛出异常
	 */
	public static QrEccLevel parseOrThrow(final String value, Function<String, RuntimeException> thrower) {
		QrEccLevel o = parseOrDefault(value, null);
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
	public static QrEccLevel parse(final String value) throws IllegalArgumentException {
		return parseOrThrow(value, (v) -> new IllegalArgumentException("Invalid value : " + v));
	}

}
