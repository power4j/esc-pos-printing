package com.power4j.kit.printing.escpos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.function.Function;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
public enum ContextType {

	/**
	 * Text
	 */
	TEXT("text"),
	/**
	 * Bit image
	 */
	BIT_IMAGE("bmp"),
	/**
	 * QRCode
	 */
	QR_CODE("qrc"),
	/**
	 * 走纸命令
	 */
	CMD_FEED("feed"),
	/**
	 * 切纸命令
	 */
	CMD_CUT("cut");

	private final String value;

	ContextType(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	/**
	 * 解析
	 * @param value 被解析的数据,可以是null
	 * @param defValue 默认值
	 * @return 如果解析失败返回默认值
	 */
	public static ContextType parseOrDefault(final String value, final ContextType defValue) {
		if (value == null) {
			return defValue;
		}
		for (ContextType o : ContextType.values()) {
			if (o.getValue().equals(value)) {
				return o;
			}
		}
		return defValue;
	}

	/**
	 * 解析
	 * @param value 被解析的数据
	 * @return 如果解析失败返回 null
	 */
	@JsonCreator
	public static ContextType parseOrNull(final String value) {
		return parseOrDefault(value, null);
	}

	/**
	 * 解析
	 * @param value 被解析的数据
	 * @param thrower 异常抛出器
	 * @return 如果解析失败抛出异常
	 */
	public static ContextType parseOrThrow(final String value, Function<String, RuntimeException> thrower) {
		ContextType o = parseOrDefault(value, null);
		if (o == null) {
			throw thrower.apply(value);
		}
		return o;
	}

	/**
	 * 解析
	 * @param value 被解析的数据
	 * @return 如果解析失败抛出 IllegalArgumentException
	 */
	public static ContextType parse(final String value) throws IllegalArgumentException {
		return parseOrThrow(value, (v) -> new IllegalArgumentException("Invalid value : " + v));
	}

}
