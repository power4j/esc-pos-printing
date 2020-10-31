package com.power4j.kit.printing.escpos.options;

import com.power4j.kit.printing.escpos.style.Alignment;
import lombok.Data;

import java.io.Serializable;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
@Data
public class Opt implements Serializable {
	private static final long serialVersionUID = 1L;

	private Alignment align = Alignment.LEFT;
}
