package com.power4j.kit.printing.coffee;

import cn.hutool.core.util.HexUtil;
import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/30
 * @since 1.0
 */
public class CoffeeTest {
	@SneakyThrows
	public static void main(String[] args) {
		System.out.println("testCenterText");
		testCenterText("this-is-a-test-msg", Charset.forName("GB2312"));
		System.out.println("testRightText");
		testRightText("this-is-a-test-msg", Charset.forName("GB2312"));
		System.out.println("simpleTest");
		simpleTest();
		System.out.println("simpleTest2");
		simpleTest2();
	}

	@SneakyThrows
	public static void testCenterText(String text, Charset charset){

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		EscPos escPos = new EscPos(byteArrayOutputStream);
		escPos.setCharsetName(charset.name());
		Style style = new Style();
		style.setJustification(EscPosConst.Justification.Center);
		escPos.writeLF(style,text);
		//escPos.feed(style,10);
		escPos.feed(10);
		escPos.cut(EscPos.CutMode.PART);
		escPos.close();
		byte[] data = byteArrayOutputStream.toByteArray();
		String hex = HexUtil.encodeHexStr(data);
		System.out.println(hex);
	}

	@SneakyThrows
	public static void testRightText(String text, Charset charset){

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		EscPos escPos = new EscPos(byteArrayOutputStream);
		escPos.setCharsetName(charset.name());
		Style style = new Style();
		style.setJustification(EscPosConst.Justification.Right);
		escPos.writeLF(style,text);
		escPos.feed(10);
		escPos.cut(EscPos.CutMode.PART);
		escPos.close();
		byte[] data = byteArrayOutputStream.toByteArray();
		String hex = HexUtil.encodeHexStr(data);
		System.out.println(hex);
	}

	@SneakyThrows
	public static void simpleTest(){
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		EscPos escPos = new EscPos(byteArrayOutputStream);
		escPos.initializePrinter();
		escPos.setCharsetName("GBK");
		/*escPos.write(0x1C);
		escPos.write(0x26);*/

		Style title = new Style();
		title.setBold(true);
		//style.setFontName(Style.FontName.Font_B);
		title.setFontSize(Style.FontSize._2, Style.FontSize._2);
		title.setJustification(EscPosConst.Justification.Center);


		Style normal = new Style();
		//style.setFontName(Style.FontName.Font_B);
		normal.setFontSize(Style.FontSize._2, Style.FontSize._2);
		normal.setJustification(EscPosConst.Justification.Left_Default);

		Style right = new Style();
		//style.setFontName(Style.FontName.Font_B);
		right.setFontSize(Style.FontSize._2, Style.FontSize._2);
		right.setJustification(EscPosConst.Justification.Right);

		escPos.writeLF(title,"hello,world!");
		escPos.feed(2);

		escPos.writeLF(normal,"hey!");
		escPos.feed(2);

		escPos.writeLF(right,"你好世界");
		//escPos.feed(style,10);
		escPos.feed(5);
		escPos.cut(EscPos.CutMode.PART);
		escPos.close();
		byte[] data = byteArrayOutputStream.toByteArray();
		String hex = HexUtil.encodeHexStr(data);
		System.out.println(hex);
	}

	@SneakyThrows
	public static void simpleTest2(){
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		EscPos escpos = new EscPos(byteArrayOutputStream);

		Style title = new Style()
				.setFontSize(Style.FontSize._2, Style.FontSize._2)
				.setJustification(EscPosConst.Justification.Center);

		escpos.writeLF(title,"Code Table");
		escpos.feed(2);

		escpos.writeLF("Using code table of the France");
		escpos.setCharacterCodeTable(EscPos.CharacterCodeTable.CP863_Canadian_French);
		escpos.feed(2);
		escpos.writeLF("Liberté et Fraternité.");
		escpos.feed(3);


		escpos.writeLF("Using Portuguese code table");
		escpos.setCharacterCodeTable(EscPos.CharacterCodeTable.CP860_Portuguese);
		escpos.writeLF("Programação java.");

		escpos.feed(5);
		escpos.cut(EscPos.CutMode.FULL);

		escpos.close();
		byte[] data = byteArrayOutputStream.toByteArray();
		String hex = HexUtil.encodeHexStr(data);
		System.out.println(hex);
	}
}
