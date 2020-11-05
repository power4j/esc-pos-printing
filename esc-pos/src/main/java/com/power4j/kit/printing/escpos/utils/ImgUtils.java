package com.power4j.kit.printing.escpos.utils;

import cn.hutool.core.codec.Base64Encoder;
import lombok.experimental.UtilityClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/11/5
 * @since 1.0
 */
@UtilityClass
public class ImgUtils {

	/**
	 * Read image
	 * @param imgUrl
	 * @return
	 * @throws IOException
	 */
	public BufferedImage read(URL imgUrl) throws IOException {
		return ImageIO.read(imgUrl);
	}

	/**
	 * BufferedImage to base64 string
	 * @param image
	 * @param format image format type,e.g. png
	 * @return
	 */
	public String toBase64(BufferedImage image,String format) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, format, bos);
		return Base64Encoder.encode(bos.toByteArray());
	}
}
