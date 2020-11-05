package com.power4j.kit.printing.escpos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.power4j.kit.printing.escpos.codec.CmdEncoder;
import com.power4j.kit.printing.escpos.codec.DefaultCmdEncoder;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
@UtilityClass
public class DocProcessor {

	private final static ObjectMapper mapper = new ObjectMapper();

	@SneakyThrows
	public Doc parseFromJson(String json) {
		return mapper.readValue(json, Doc.class);
	}

	public CmdEncoder getCmdEncoder() {
		return new DefaultCmdEncoder();
	}

}
