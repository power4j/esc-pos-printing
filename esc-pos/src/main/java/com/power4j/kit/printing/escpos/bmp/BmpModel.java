package com.power4j.kit.printing.escpos.bmp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.function.Function;

/**
 * 位图在打印机缓存中的存储模式
 * @author CJ (power4j@outlook.com)
 * @date 2020/11/5
 * @since 1.0
 */
public enum BmpModel {
	/**
	 * 8-dot single-density
	 */
	M0("m0",0),
	/**
	 * 8-dot double-density
	 */
	M1("m1",1),
	/**
	 * 24-dot single-density
	 */
	M32("m32",32),
	/**
	 * 24-dot double-density
	 */
	M33("m33",33);

	private final String value;
	private final int modelValue;

	BmpModel(String value,int modelValue) {
		this.value = value;
		this.modelValue = modelValue;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	public int getModelValue() {
		return modelValue;
	}

	/**
	 * 解析
	 *
	 * @param value    被解析的数据,可以是null
	 * @param defValue 默认值
	 * @return 如果解析失败返回默认值
	 */
	public static BmpModel parseOrDefault(final String value, final BmpModel defValue) {
		if (value == null) {
			return defValue;
		}
		for (BmpModel o : BmpModel.values()) {
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
	public static BmpModel parseOrNull(final String value) {
		return parseOrDefault(value, null);
	}

	/**
	 * 解析
	 *
	 * @param value   被解析的数据
	 * @param thrower 异常抛出器
	 * @return 如果解析失败抛出异常
	 */
	public static BmpModel parseOrThrow(final String value, Function<String, RuntimeException> thrower) {
		BmpModel o = parseOrDefault(value, null);
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
	public static BmpModel parse(final String value) throws IllegalArgumentException {
		return parseOrThrow(value, (v) -> new IllegalArgumentException("Invalid value : " + v));
	}

}
