package com.power4j.kit.printing.coffee;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.util.HexUtil;
import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.Style;
import com.github.anastaciocintra.escpos.image.*;
import com.power4j.kit.printing.escpos.codec.SampleData;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

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
		System.out.println("bmpTest");
		bmpTest();
	}

	@SneakyThrows
	public static void testCenterText(String text, Charset charset) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		EscPos escPos = new EscPos(byteArrayOutputStream);
		escPos.setCharsetName(charset.name());
		Style style = new Style();
		style.setJustification(EscPosConst.Justification.Center);
		escPos.writeLF(style, text);
		escPos.feed(10);
		escPos.cut(EscPos.CutMode.PART);
		escPos.close();
		byte[] data = byteArrayOutputStream.toByteArray();
		String hex = HexUtil.encodeHexStr(data);
		System.out.println(hex);
	}

	@SneakyThrows
	public static void testRightText(String text, Charset charset) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		EscPos escPos = new EscPos(byteArrayOutputStream);
		escPos.setCharsetName(charset.name());
		Style style = new Style();
		style.setJustification(EscPosConst.Justification.Right);
		escPos.writeLF(style, text);
		escPos.feed(10);
		escPos.cut(EscPos.CutMode.PART);
		escPos.close();
		byte[] data = byteArrayOutputStream.toByteArray();
		String hex = HexUtil.encodeHexStr(data);
		System.out.println(hex);
	}

	@SneakyThrows
	public static void simpleTest() {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		EscPos escPos = new EscPos(byteArrayOutputStream);
		escPos.initializePrinter();
		escPos.setCharsetName("GBK");

		Style title = new Style();
		title.setBold(true);
		title.setFontSize(Style.FontSize._2, Style.FontSize._2);
		title.setJustification(EscPosConst.Justification.Center);

		Style normal = new Style();
		normal.setFontSize(Style.FontSize._2, Style.FontSize._2);
		normal.setJustification(EscPosConst.Justification.Left_Default);

		Style right = new Style();
		right.setFontSize(Style.FontSize._2, Style.FontSize._2);
		right.setJustification(EscPosConst.Justification.Right);

		escPos.writeLF(title, "hello,world!");
		escPos.feed(2);

		escPos.writeLF(normal, "hey!");
		escPos.feed(2);

		escPos.writeLF(right, "你好世界");
		escPos.feed(5);
		escPos.cut(EscPos.CutMode.PART);
		escPos.close();
		byte[] data = byteArrayOutputStream.toByteArray();
		String hex = HexUtil.encodeHexStr(data);
		System.out.println(hex);
	}

	@SneakyThrows
	public static void simpleTest2() {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		EscPos escpos = new EscPos(byteArrayOutputStream);

		Style title = new Style().setFontSize(Style.FontSize._2, Style.FontSize._2)
				.setJustification(EscPosConst.Justification.Center);

		escpos.writeLF(title, "Code Table");
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

	@SneakyThrows
	public static void bmpTest() {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		EscPos escpos = new EscPos(byteArrayOutputStream);
		/*
		 * to print one image we need to have: - one BufferedImage. - one bitonal
		 * algorithm to define what and how print on image. - one image wrapper to
		 * determine the command set to be used on image printing and how to customize it.
		 */

		// specify the algorithm that defines what and how "print or not print" on each
		// coordinate of the BufferedImage.
		// in this case, threshold 127
		Bitonal algorithm = new BitonalThreshold(127);
		// creating the EscPosImage, need buffered image and algorithm.

		BufferedImage githubBufferedImage = ImageIO
				.read(new ByteArrayInputStream(Base64Decoder.decode(SampleData.imagePower4j)));
		EscPosImage escposImage = new EscPosImage(new CoffeeImageImpl(githubBufferedImage), algorithm);

		// this wrapper uses esc/pos sequence: "ESC '*'"
		BitImageWrapper imageWrapper = new BitImageWrapper();

		escpos.writeLF(new Style().setFontSize(Style.FontSize._2, Style.FontSize._2), "BitImageWrapper");

		escpos.writeLF("default size");
		escpos.write(imageWrapper, escposImage);

		escpos.feed(5);
		escpos.writeLF("Double Height");
		imageWrapper.setMode(BitImageWrapper.BitImageMode._8DotDoubleDensity);
		escpos.write(imageWrapper, escposImage);

		escpos.feed(5);
		escpos.writeLF("Double Width");
		imageWrapper.setMode(BitImageWrapper.BitImageMode._24DotSingleDensity);
		escpos.write(imageWrapper, escposImage);

		escpos.feed(5);
		escpos.writeLF("Quadruple size");
		imageWrapper.setMode(BitImageWrapper.BitImageMode._8DotSingleDensity);
		escpos.write(imageWrapper, escposImage);

		escpos.feed(5);
		escpos.writeLF("print on Left");
		imageWrapper.setMode(BitImageWrapper.BitImageMode._24DotDoubleDensity_Default);
		imageWrapper.setJustification(EscPosConst.Justification.Left_Default);
		escpos.write(imageWrapper, escposImage);
		escpos.feed(5);
		escpos.writeLF("print on Right");
		imageWrapper.setJustification(EscPosConst.Justification.Right);
		escpos.write(imageWrapper, escposImage);
		escpos.feed(5);
		escpos.writeLF("print on Center");
		imageWrapper.setJustification(EscPosConst.Justification.Center);
		escpos.write(imageWrapper, escposImage);

		escpos.feed(5);
		escpos.cut(EscPos.CutMode.FULL);

		escpos.close();

		byte[] data = byteArrayOutputStream.toByteArray();
		String hex = HexUtil.encodeHexStr(data);
		System.out.println(hex);

	}

}
