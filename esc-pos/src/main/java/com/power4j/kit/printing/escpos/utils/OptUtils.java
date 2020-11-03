package com.power4j.kit.printing.escpos.utils;

import com.power4j.kit.printing.escpos.options.Opt;
import com.power4j.kit.printing.escpos.options.QrCodeOpt;
import com.power4j.kit.printing.escpos.options.TextOpt;
import com.power4j.kit.printing.escpos.qrcode.QrEccLevel;
import com.power4j.kit.printing.escpos.qrcode.QrModel;
import com.power4j.kit.printing.escpos.style.*;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.Optional;

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
		if (map != null) {
			Optional.ofNullable(map.get(KEY_ALIGN))
					.ifPresent(val -> opt.setAlign(Alignment.parseOrDefault(val, Alignment.LEFT)));
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

		// @formatter:off

		Optional.ofNullable(map.get(KEY_ALIGN))
				.ifPresent(val -> opt.setAlign(Alignment.parseOrDefault(val, Alignment.LEFT)));

		Optional.ofNullable(map.get(KEY_COLOR))
				.ifPresent(val -> opt.setColor(Color.parseOrDefault(val, Color.BG_WHITE)));

		Optional.ofNullable(map.get(KEY_BOLD))
				.ifPresent(val -> opt.setBold(Boolean.valueOf(val)));

		Optional.ofNullable(map.get(KEY_FONT_TYPE))
				.ifPresent(val -> opt.setFontType(FontType.parseOrDefault(val, FontType.FONT_A)));

		Optional.ofNullable(map.get(KEY_FONT_WIDTH))
				.ifPresent(val -> opt.setFontWidth(FontSize.parseOrDefault(val, FontSize.SIZE_1)));

		Optional.ofNullable(map.get(KEY_FONT_HEIGHT))
				.ifPresent(val -> opt.setFontWidth(FontSize.parseOrDefault(val, FontSize.SIZE_1)));

		Optional.ofNullable(map.get(KEY_UNDERLINE))
				.ifPresent(val -> opt.setUnderline(Underline.parseOrDefault(val, Underline.NONE)));

		Optional.ofNullable(map.get(KEY_LINE_SPACING))
				.ifPresent(val -> opt.setLineSpacing(Integer.parseInt(val)));
		return opt;

		// @formatter:on
	}

	/**
	 * extract QrCodeOpt
	 * @param map
	 * @return
	 */
	public QrCodeOpt getQrCodeOpt(Map<String, String> map) {
		QrCodeOpt qrCodeOpt = new QrCodeOpt();
		if (map == null) {
			return qrCodeOpt;
		}

		// @formatter:off

		Optional.ofNullable(map.get(KEY_ALIGN))
				.ifPresent(val -> qrCodeOpt.setAlign(Alignment.parseOrDefault(val, Alignment.LEFT)));

		Optional.ofNullable(map.get(KEY_QR_MODEL))
				.ifPresent(val -> qrCodeOpt.setModel(QrModel.parseOrDefault(val, QrModel.MODEL_48)));

		Optional.ofNullable(map.get(KEY_QR_ECC_LEVEL))
				.ifPresent(val -> qrCodeOpt.setLevel(QrEccLevel.parseOrDefault(val, QrEccLevel.ECC_M)));

		Optional.ofNullable(map.get(KEY_QR_SIZE))
				.ifPresent(val -> qrCodeOpt.setSize(Integer.parseInt(val)));
		return qrCodeOpt;

		// @formatter:on
	}

}
