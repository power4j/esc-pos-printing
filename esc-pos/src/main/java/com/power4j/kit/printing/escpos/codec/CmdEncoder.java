package com.power4j.kit.printing.escpos.codec;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.util.HexUtil;
import com.power4j.kit.printing.escpos.Doc;
import com.power4j.kit.printing.escpos.exceptions.EscPosException;

/**
 * 指令编码器
 * @author CJ (power4j@outlook.com)
 * @date 2020/11/5
 * @since 1.0
 */
public interface CmdEncoder {

	/**
	 * encode
	 * @param doc
	 * @return
	 * @throws EscPosException
	 */
	byte[] encode(Doc doc);

	/**
	 * encode to hex
	 * @param doc
	 * @return
	 * @throws EscPosException
	 */
	default String encodeBase64(Doc doc) {
		return Base64Encoder.encode(encode(doc));
	}

	/**
	 * encode to hex
	 * @param doc
	 * @return
	 * @throws EscPosException
	 */
	default String encodeHex(Doc doc) {
		return HexUtil.encodeHexStr(encode(doc));
	}
}
