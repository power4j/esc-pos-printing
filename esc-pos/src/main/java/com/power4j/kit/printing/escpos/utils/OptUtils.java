package com.power4j.kit.printing.escpos.utils;

import com.power4j.kit.printing.escpos.options.Opt;
import com.power4j.kit.printing.escpos.options.TextOpt;
import com.power4j.kit.printing.escpos.style.*;
import lombok.experimental.UtilityClass;

import java.util.Map;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
@UtilityClass
public class OptUtils {
	public static final String KEY_ALIGN = "align";
	public static final String KEY_COLOR = "color";
	public static final String KEY_BOLD = "bold";
	public static final String KEY_FONT_TYPE = "fontType";
	public static final String KEY_FONT_WIDTH = "fontWidth";
	public static final String KEY_FONT_HEIGHT = "fontHeight";
	public static final String KEY_UNDERLINE = "underline";
	public static final String KEY_LINE_SPACING = "lineSpacing";


	/**
	 * exact opt
	 * @param map
	 * @return
	 */
	public Opt getOpt(Map<String, String> map) {
		Opt opt = new Opt();
		if(map != null &&  map.containsKey(KEY_ALIGN)){
			Alignment alignment = Alignment.parseOrDefault(map.get(KEY_ALIGN),Alignment.LEFT);
			opt.setAlign(alignment);
		}
		return opt;
	}

	public TextOpt getTextOpt(Map<String, String> map) {
		TextOpt opt = new TextOpt();
		if(map == null){
			return opt;
		}
		if(map.containsKey(KEY_ALIGN)){
			Alignment alignment = Alignment.parseOrDefault(map.get(KEY_ALIGN),Alignment.LEFT);
			opt.setAlign(alignment);
		}
		if(map.containsKey(KEY_COLOR)){
			Color color = Color.parseOrDefault(map.get(KEY_COLOR),Color.BG_WHITE);
			opt.setColor(color);
		}
		// TODO : MORE ...
		return opt;
	}
}
