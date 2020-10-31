package com.power4j.kit.printing.escpos;

import com.power4j.kit.printing.escpos.options.TextOpt;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
@Data
public class Doc implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 字符集，可选,默认 UTF-8
	 */
	private String charsetName;

	/**
	 * 文本设置,可选，默认 null
	 */
	private TextOpt opt;

	/**
	 * 行
	 */
	private List<Line> lines;

}
