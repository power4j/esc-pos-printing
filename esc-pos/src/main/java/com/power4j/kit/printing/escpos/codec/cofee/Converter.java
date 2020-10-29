package com.power4j.kit.printing.escpos.codec.cofee;

import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;
import com.power4j.kit.printing.escpos.style.*;
import lombok.experimental.UtilityClass;

import java.util.Optional;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
@UtilityClass
public class Converter {

	/**
	 * Alignment convert
	 * @param alignment
	 * @return
	 */
	public Optional<EscPosConst.Justification> toJustification(Alignment alignment) {
		switch (alignment){
			case LEFT:
				return Optional.of(EscPosConst.Justification.Left_Default);
			case RIGHT:
				return Optional.of(EscPosConst.Justification.Right);
			case CENTER:
				return Optional.of(EscPosConst.Justification.Center);
			default:
				return Optional.empty();
		}
	}

	/**
	 * Underline convert
	 * @param underline
	 * @return
	 */
	public Optional<Style.Underline> toUnderline(Underline underline){
		switch (underline){
			case NONE:
				return Optional.of(Style.Underline.None_Default);
			case ONE_DOT:
				return Optional.of(Style.Underline.OneDotThick);
			case TWO_DOT:
				return Optional.of(Style.Underline.TwoDotThick);
			default:
				return Optional.empty();
		}
	}

	/**
	 * FontName convert
	 * @param fontType
	 * @return
	 */
	public Optional<Style.FontName> toFontName(FontType fontType) {
		switch (fontType){
			case FONT_A:
				return Optional.of(Style.FontName.Font_A_Default);
			case FONT_B:
				return Optional.of(Style.FontName.Font_B);
			case FONT_C:
				return Optional.of(Style.FontName.Font_C);
			default:
				return Optional.empty();
		}
	}


	/**
	 * FontSize convert
	 * @param fontSize
	 * @return
	 */
	public Optional<Style.FontSize> toFontSize(FontSize fontSize){
		switch (fontSize){
			case SIZE_1:
				return Optional.of(Style.FontSize._1);
			case SIZE_2:
				return Optional.of(Style.FontSize._2);
			case SIZE_3:
				return Optional.of(Style.FontSize._3);
			case SIZE_4:
				return Optional.of(Style.FontSize._4);
			case SIZE_5:
				return Optional.of(Style.FontSize._5);
			case SIZE_6:
				return Optional.of(Style.FontSize._6);
			case SIZE_7:
				return Optional.of(Style.FontSize._7);
			case SIZE_8:
				return Optional.of(Style.FontSize._8);
			default:
				return Optional.empty();
		}
	}

	/**
	 * ColorMode convert
	 * @param color
	 * @return
	 */
	public Optional<Style.ColorMode> toColorMode(Color color){
		switch (color){
			case BG_WHITE:
				return Optional.of(Style.ColorMode.BlackOnWhite_Default);
			case BG_BLACK:
				return Optional.of(Style.ColorMode.WhiteOnBlack);
			default:
				return Optional.empty();
		}
	}
}
