package com.power4j.kit.printing.escpos.cmd;

/**
 * @author CJ (power4j@outlook.com)
 * @date 2020/10/31
 * @since 1.0
 */
public class Cut {
	public static final int MODE_PART = 0;
	public static final int MODE_FULL = 1;

	/**
	 * 切纸模式 0 半切 1 全切
	 */
	private int mode;

	public static Cut parse(String mode){
		int val = Integer.parseInt(mode);
		if(val != MODE_PART && val != MODE_FULL){
			throw new IllegalArgumentException("Invalid mode value : "+mode);
		}
		return new Cut(val);
	}

	public Cut(int mode) {
		this.mode = mode;
	}

	public Cut(){
		this(MODE_PART);
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
	public boolean isFullMode(){
		return mode == MODE_FULL;
	}
}
