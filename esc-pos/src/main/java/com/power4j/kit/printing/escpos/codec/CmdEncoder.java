package com.power4j.kit.printing.escpos.codec;

import cn.hutool.core.util.HexUtil;
import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;
import com.github.anastaciocintra.escpos.barcode.QRCode;
import com.power4j.kit.printing.escpos.ContextType;
import com.power4j.kit.printing.escpos.Doc;
import com.power4j.kit.printing.escpos.Line;
import com.power4j.kit.printing.escpos.cmd.Cut;
import com.power4j.kit.printing.escpos.cmd.Feed;
import com.power4j.kit.printing.escpos.codec.cofee.Converter;
import com.power4j.kit.printing.escpos.options.QrCodeOpt;
import com.power4j.kit.printing.escpos.options.TextOpt;
import com.power4j.kit.printing.escpos.utils.OptUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
public class CmdEncoder {
	@Getter
	@Setter
	private Charset defaultCharset = Charset.forName("GB2312");

	/**
	 * encode to hex
	 * @param doc
	 * @return
	 * @throws IOException
	 */
	public static String encodeHex(Doc doc) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		CmdEncoder encoder = new CmdEncoder();
		encoder.write(doc,byteArrayOutputStream);
		byte[] data = byteArrayOutputStream.toByteArray();
		return HexUtil.encodeHexStr(data);
	}

	public void write(Doc doc, OutputStream stream) throws IOException {
		EscPos escPos = new EscPos(stream);
		Style globalStyle = getStyle(doc.getOpt(), null);
		if(globalStyle != null){
			escPos.setStyle(globalStyle);
		}
		String charsetName = doc.getCharsetName();
		if(charsetName == null || charsetName.isEmpty()) {
			charsetName = defaultCharset.name();
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
				Style textStyle = getStyle(line.getOpt(), null);
				if(textStyle != null){
					escPos.writeLF(textStyle,line.getData());
				} else {
					escPos.writeLF(line.getData());
				}
				break;
			case BIT_IMAGE:
				break;
			case QR_CODE:
				escPos.write(getQRCode(line.getOpt()),line.getData());
				break;
			case CMD_FEED:
				Style feedStyle = getStyle(line.getOpt(), null);
				escPos.feed(feedStyle,Feed.parse(line.getData()).getLine());
				break;
			case CMD_CUT:
				Cut cut = Cut.parse(line.getData());
				escPos.cut(Converter.toCutMode(cut));
				break;
			default:
				throw new IllegalArgumentException("Unkonwn ContextType :" + type.name());
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

	/**
	 * QRCode convert
	 * @param map
	 * @return
	 */
	public QRCode getQRCode( Map<String, String> map){
		QrCodeOpt qrCodeOpt = OptUtils.getQrCodeOpt(map);
		QRCode qrCode = new QRCode();
		qrCode.setModel(Converter.toQRModel(qrCodeOpt.getModel()).orElse(QRCode.QRModel._1_Default));
		qrCode.setErrorCorrectionLevel(Converter.toQRErrorCorrectionLevel(qrCodeOpt.getLevel()).orElse(QRCode.QRErrorCorrectionLevel.QR_ECLEVEL_M_Default));
		qrCode.setJustification(Converter.toJustification(qrCodeOpt.getAlign()).orElse(EscPosConst.Justification.Left_Default));
		qrCode.setSize(qrCodeOpt.getSize());
		return qrCode;
	}
}
