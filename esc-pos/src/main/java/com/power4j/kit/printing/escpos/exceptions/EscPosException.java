package com.power4j.kit.printing.escpos.exceptions;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/11/5
 * @since 1.0
 */
public class EscPosException extends RuntimeException{
	public EscPosException(String message) {
		super(message);
	}

	public EscPosException(String message, Throwable cause) {
		super(message, cause);
	}

	public EscPosException(Throwable cause) {
		super(cause);
	}
}
