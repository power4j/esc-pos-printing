package com.power4j.kit.printing.escpos;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
@Data
public class Line implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 类型
	 */
	private ContextType type;
	/**
	 * 内容
	 */
	private String context;
	/**
	 * 选项
	 */
	private Map<String, String> opt;
}
