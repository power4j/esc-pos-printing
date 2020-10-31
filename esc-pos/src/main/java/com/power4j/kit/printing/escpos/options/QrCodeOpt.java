package com.power4j.kit.printing.escpos.options;

import com.power4j.kit.printing.escpos.qrcode.QrEccLevel;
import com.power4j.kit.printing.escpos.qrcode.QrModel;
import lombok.Data;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/29
 * @since 1.0
 */
@Data
public class QrCodeOpt extends Opt {

	private static final long serialVersionUID = 1L;

	private QrModel model = QrModel.MODEL_48;

	private QrEccLevel level = QrEccLevel.ECC_M;

	private Integer size = 3;

}
