package com.power4j.kit.printing.escpos.utils;

import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.*;

public class ImgUtilsTest {

	@Test
	public void toBase64() throws IOException {
		BufferedImage image = ImgUtils.read(getClass().getClassLoader().getResource("images/github45.png"));
		String imgStr = ImgUtils.toBase64(image,"png");
		System.out.println(imgStr);
	}
}