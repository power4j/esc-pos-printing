package com.power4j.kit.printing.escpos;

import java.io.Serializable;
import java.util.Map;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
public class Line implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 类型
	 */
	private ContextType type;

	/**
	 * 内容
	 */
	private String data;

	/**
	 * 选项
	 */
	private Map<String, String> opt;

	public Line() {
	}

	public Line(ContextType type, String data) {
		this(type, data, null);
	}

	public Line(ContextType type, String data, Map<String, String> opt) {
		this.type = type;
		this.data = data;
		this.opt = opt;
	}

	public ContextType getType() {
		return type;
	}

	public void setType(ContextType type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Map<String, String> getOpt() {
		return opt;
	}

	public void setOpt(Map<String, String> opt) {
		this.opt = opt;
	}

}
