package com.power4j.kit.printing.escpos.options;

import com.power4j.kit.printing.escpos.style.Color;
import com.power4j.kit.printing.escpos.style.FontSize;
import com.power4j.kit.printing.escpos.style.FontType;
import com.power4j.kit.printing.escpos.style.Underline;
import lombok.Data;

import java.io.Serializable;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
@Data
public class TextOpt extends Opt implements Serializable {

	private static final long serialVersionUID = 1L;

	private Color color = Color.BG_WHITE;

	private Boolean bold = Boolean.FALSE;

	private FontType fontType = FontType.FONT_A;

	private FontSize fontWidth = FontSize.SIZE_1;

	private FontSize fontHeight = FontSize.SIZE_1;

	private Underline underline = Underline.NONE;

	/**
	 * null means use default value
	 */
	private Integer lineSpacing;

}
