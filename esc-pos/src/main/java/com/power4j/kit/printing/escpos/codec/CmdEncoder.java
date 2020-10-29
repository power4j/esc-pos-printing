package com.power4j.kit.printing.escpos.codec;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;
import com.power4j.kit.printing.escpos.ContextType;
import com.power4j.kit.printing.escpos.Doc;
import com.power4j.kit.printing.escpos.Line;
import com.power4j.kit.printing.escpos.codec.cofee.Converter;
import com.power4j.kit.printing.escpos.options.TextOpt;
import com.power4j.kit.printing.escpos.utils.OptUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
public class CmdEncoder {
	public void write(Doc doc, OutputStream stream) throws IOException {
		EscPos escPos = new EscPos(stream);
		Style globalStyle = getStyle(doc.getOpt(), null);
		if(globalStyle != null){
			escPos.setStyle(globalStyle);
		}
		String charsetName = doc.getCharsetName();
		if(charsetName == null || charsetName.isEmpty()) {
			charsetName = StandardCharsets.UTF_8.name();
		}
		escPos.setCharsetName(charsetName);
		for(Line line : doc.getLines()) {
			handleLine(escPos,line,stream);
		}
	}

	private void handleLine(EscPos escPos, Line line, OutputStream stream) throws IOException {
		final ContextType type = line.getType();
		switch (type){
			case TEXT:
				Style style = getStyle(line.getOpt(), null);
				if(style != null){
					escPos.write(style,line.getContext());
				} else {
					escPos.write(line.getContext());
				}
			case BIT_IMAGE:
				break;
			case QR_CODE:
				break;
		}
	}

	/**
	 * Style convert
	 * @param textOpt
	 * @return
	 */
	public Style getStyle(TextOpt textOpt,Style defaultStyle) {
		if(textOpt == null){
			return defaultStyle;
		}

		// @formatter:off

		EscPosConst.Justification justification = Converter.toJustification(textOpt.getAlign())
				.orElse(EscPosConst.Justification.Left_Default);
		Style.FontSize fontWidth = Converter.toFontSize(textOpt.getFontWidth())
				.orElse(Style.FontSize._1);
		Style.FontSize fontHeight = Converter.toFontSize(textOpt.getFontHeight())
				.orElse(Style.FontSize._1);
		Style.FontName fontName = Converter.toFontName(textOpt.getFontType())
				.orElse(Style.FontName.Font_A_Default);
		Style.ColorMode colorMode = Converter.toColorMode(textOpt.getColor())
				.orElse(Style.ColorMode.BlackOnWhite_Default);
		Style.Underline underline = Converter.toUnderline(textOpt.getUnderline())
				.orElse(Style.Underline.None_Default);
		boolean bold = textOpt.getBold() == null ? false : textOpt.getBold();

		// @formatter:on

		Style style =  new Style();
		style.setBold(bold);
		style.setUnderline(underline);
		style.setColorMode(colorMode);
		style.setFontName(fontName);
		style.setFontSize(fontWidth,fontHeight);
		style.setJustification(justification);
		if(textOpt.getLineSpacing() == null) {
			style.resetLineSpacing();
		} else {
			style.setLineSpacing(textOpt.getLineSpacing());
		}
		return style;
	}

	/**
	 * Style convert
	 * @param defaultStyle
	 * @return
	 */
	public Style getStyle(Map<String, String> map, Style defaultStyle) {
		return getStyle(OptUtils.getTextOpt(map),defaultStyle);
	}
}
