package com.power4j.kit.printing.escpos.cmd;

import java.util.Map;

/**
 * 走纸
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/31
 * @since 1.0
 */
public class Feed {
	private int line;
	/**
	 * 选项
	 */
	private Map<String, String> opt;

	public static Feed parse(String line){
		int nLine = Integer.parseInt(line);
		return new Feed(nLine);
	}

	public Feed(int line) {
		this.line = line;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public Map<String, String> getOpt() {
		return opt;
	}

	public void setOpt(Map<String, String> opt) {
		this.opt = opt;
	}
}
