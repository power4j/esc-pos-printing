package com.power4j.kit.printing.escpos.options;

import com.power4j.kit.printing.escpos.bmp.BmpModel;
import lombok.Data;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/11/5
 * @since 1.0
 */
@Data
public class BmpOpt extends Opt {

	private static final long serialVersionUID = 1L;

	private BmpModel model = BmpModel.M32;

}
