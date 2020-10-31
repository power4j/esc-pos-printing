package com.power4j.kit.printing.escpos.utils;

import com.power4j.kit.printing.escpos.options.Opt;
import com.power4j.kit.printing.escpos.options.QrCodeOpt;
import com.power4j.kit.printing.escpos.options.TextOpt;
import com.power4j.kit.printing.escpos.qrcode.QrEccLevel;
import com.power4j.kit.printing.escpos.qrcode.QrModel;
import com.power4j.kit.printing.escpos.style.Alignment;
import com.power4j.kit.printing.escpos.style.Color;
import com.power4j.kit.printing.escpos.style.FontSize;
import com.power4j.kit.printing.escpos.style.FontType;
import com.power4j.kit.printing.escpos.style.Underline;
import lombok.experimental.UtilityClass;

import java.util.Map;

import static com.power4j.kit.printing.escpos.constants.Keys.KEY_ALIGN;
import static com.power4j.kit.printing.escpos.constants.Keys.KEY_BOLD;
import static com.power4j.kit.printing.escpos.constants.Keys.KEY_COLOR;
import static com.power4j.kit.printing.escpos.constants.Keys.KEY_FONT_HEIGHT;
import static com.power4j.kit.printing.escpos.constants.Keys.KEY_FONT_TYPE;
import static com.power4j.kit.printing.escpos.constants.Keys.KEY_FONT_WIDTH;
import static com.power4j.kit.printing.escpos.constants.Keys.KEY_LINE_SPACING;
import static com.power4j.kit.printing.escpos.constants.Keys.KEY_QR_ECC_LEVEL;
import static com.power4j.kit.printing.escpos.constants.Keys.KEY_QR_MODEL;
import static com.power4j.kit.printing.escpos.constants.Keys.KEY_QR_SIZE;
import static com.power4j.kit.printing.escpos.constants.Keys.KEY_UNDERLINE;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
@UtilityClass
public class OptUtils {

	/**
	 * exact opt
	 * @param map
	 * @return
	 */
	public Opt getOpt(Map<String, String> map) {
		Opt opt = new Opt();
		if (map != null && map.containsKey(KEY_ALIGN)) {
			Alignment alignment = Alignment.parseOrDefault(map.get(KEY_ALIGN), Alignment.LEFT);
			opt.setAlign(alignment);
		}
		return opt;
	}

	/**
	 * extract text opt
	 * @param map
	 * @return
	 */
	public TextOpt getTextOpt(Map<String, String> map) {
		TextOpt opt = new TextOpt();
		if (map == null) {
			return opt;
		}
		if (map.containsKey(KEY_ALIGN)) {
			Alignment alignment = Alignment.parseOrDefault(map.get(KEY_ALIGN), Alignment.LEFT);
			opt.setAlign(alignment);
		}
		if (map.containsKey(KEY_COLOR)) {
			Color color = Color.parseOrDefault(map.get(KEY_COLOR), Color.BG_WHITE);
			opt.setColor(color);
		}
		if (map.containsKey(KEY_BOLD)) {
			opt.setBold(Boolean.valueOf(map.get(KEY_BOLD)));
		}
		if (map.containsKey(KEY_FONT_TYPE)) {
			FontType fontType = FontType.parseOrDefault(map.get(KEY_FONT_TYPE), FontType.FONT_A);
			opt.setFontType(fontType);
		}
		if (map.containsKey(KEY_FONT_WIDTH)) {
			FontSize fontSize = FontSize.parseOrDefault(map.get(KEY_FONT_WIDTH), FontSize.SIZE_1);
			opt.setFontWidth(fontSize);
		}
		if (map.containsKey(KEY_FONT_HEIGHT)) {
			FontSize fontSize = FontSize.parseOrDefault(map.get(KEY_FONT_HEIGHT), FontSize.SIZE_1);
			opt.setFontHeight(fontSize);
		}
		if (map.containsKey(KEY_UNDERLINE)) {
			Underline underline = Underline.parseOrDefault(map.get(KEY_UNDERLINE), Underline.NONE);
			opt.setUnderline(underline);
		}
		if (map.containsKey(KEY_LINE_SPACING)) {
			opt.setLineSpacing(Integer.parseInt(map.get(KEY_LINE_SPACING)));
		}
		return opt;
	}

	/**
	 * extract QrCodeOpt
	 * @param map
	 * @return
	 */
	public QrCodeOpt getQrCodeOpt(Map<String, String> map) {
		QrCodeOpt qrCodeOpt = new QrCodeOpt();
		if (map != null && map.containsKey(KEY_ALIGN)) {
			Alignment alignment = Alignment.parseOrDefault(map.get(KEY_ALIGN), Alignment.LEFT);
			qrCodeOpt.setAlign(alignment);
		}
		if (map.containsKey(KEY_QR_MODEL)) {
			QrModel qrModel = QrModel.parseOrDefault(map.get(KEY_QR_MODEL), QrModel.MODEL_48);
			qrCodeOpt.setModel(qrModel);
		}
		if (map.containsKey(KEY_QR_ECC_LEVEL)) {
			QrEccLevel qrEccLevel = QrEccLevel.parseOrDefault(map.get(KEY_QR_ECC_LEVEL), QrEccLevel.ECC_M);
			qrCodeOpt.setLevel(qrEccLevel);
		}
		if (map.containsKey(KEY_QR_SIZE)) {
			qrCodeOpt.setSize(Integer.parseInt(map.get(KEY_QR_SIZE)));
		}
		return qrCodeOpt;
	}

}
