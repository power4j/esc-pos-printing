package com.power4j.kit.printing.escpos.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.power4j.kit.printing.escpos.ContextType;
import com.power4j.kit.printing.escpos.Doc;
import com.power4j.kit.printing.escpos.Line;
import com.power4j.kit.printing.escpos.constants.Keys;
import com.power4j.kit.printing.escpos.style.Alignment;
import com.power4j.kit.printing.escpos.style.FontSize;
import com.power4j.kit.printing.escpos.style.Underline;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CmdEncoderTest {

	private static final Map<String, String> centerAlign = new HashMap<>();

	private static final Map<String, String> rightAlign = new HashMap<>();

	static {
		centerAlign.put(Keys.KEY_ALIGN, Alignment.CENTER.getValue());
		rightAlign.put(Keys.KEY_ALIGN, Alignment.RIGHT.getValue());
	}

	@Test
	public void testWrite() throws IOException {
		Doc doc = new Doc();
		doc.setLines(new ArrayList<>());

		Map<String, String> titleTextOpt = new HashMap<>();
		titleTextOpt.put(Keys.KEY_ALIGN, Alignment.CENTER.getValue());
		titleTextOpt.put(Keys.KEY_BOLD, "true");
		titleTextOpt.put(Keys.KEY_FONT_HEIGHT, FontSize.SIZE_2.getValue());
		titleTextOpt.put(Keys.KEY_FONT_WIDTH, FontSize.SIZE_2.getValue());

		doc.getLines().add(new Line(ContextType.TEXT, "我是凭条标题", titleTextOpt));
		doc.getLines().add(new Line(ContextType.TEXT, "this is a test msg", titleTextOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "10", centerAlign));
		doc.getLines().add(new Line(ContextType.CMD_CUT, "0"));

		System.out.println(CmdEncoder.encodeHex(doc));
	}

	@Test
	public void testCenterText() throws IOException {
		Doc doc = new Doc();
		doc.setLines(new ArrayList<>());
		Map<String, String> titleTextOpt = new HashMap<>();
		titleTextOpt.put(Keys.KEY_ALIGN, Alignment.CENTER.getValue());
		doc.getLines().add(new Line(ContextType.TEXT, "this-is-a-test-msg", null));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "10", centerAlign));
		doc.getLines().add(new Line(ContextType.CMD_CUT, "0"));
		System.out.println(CmdEncoder.encodeHex(doc));
	}

	@Test
	public void testRightText() throws IOException {
		Doc doc = new Doc();
		doc.setLines(new ArrayList<>());
		doc.getLines().add(new Line(ContextType.TEXT, "this-is-a-test-msg", rightAlign));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "10"));
		doc.getLines().add(new Line(ContextType.CMD_CUT, "0"));
		System.out.println(CmdEncoder.encodeHex(doc));
	}

	@Test
	public void testStyles() throws IOException {
		Doc doc = new Doc();
		doc.setCharsetName("GB2312");
		doc.setLines(new ArrayList<>());
		Map<String, String> titleTextOpt = new HashMap<>();
		titleTextOpt.put(Keys.KEY_ALIGN, Alignment.CENTER.getValue());
		titleTextOpt.put(Keys.KEY_BOLD, "true");
		titleTextOpt.put(Keys.KEY_UNDERLINE, Underline.TWO_DOT.getValue());

		doc.getLines().add(new Line(ContextType.TEXT, "我是凭条标题", titleTextOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "2"));
		doc.getLines().add(new Line(ContextType.TEXT, "我在左边"));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "2"));
		doc.getLines().add(new Line(ContextType.TEXT, "我在右边", rightAlign));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "5"));

		Map<String, String> qrOpt = new HashMap<>();
		qrOpt.put(Keys.KEY_ALIGN, Alignment.CENTER.getValue());
		qrOpt.put(Keys.KEY_QR_SIZE, "5");

		doc.getLines().add(new Line(ContextType.QR_CODE, "12345678", qrOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "8"));

		doc.getLines().add(new Line(ContextType.CMD_CUT, "0"));
		System.out.println(CmdEncoder.encodeHex(doc));
	}

	@Test
	public void testBiz() throws IOException {
		Doc doc = new Doc();
		doc.setCharsetName("GB2312");
		doc.setLines(new ArrayList<>());
		Map<String, String> titleTextOpt = new HashMap<>();
		titleTextOpt.put(Keys.KEY_ALIGN, Alignment.CENTER.getValue());
		titleTextOpt.put(Keys.KEY_BOLD, "true");
		titleTextOpt.put(Keys.KEY_FONT_HEIGHT, FontSize.SIZE_3.getValue());
		titleTextOpt.put(Keys.KEY_FONT_WIDTH, FontSize.SIZE_3.getValue());

		Map<String, String> bodyOpt = new HashMap<>();
		bodyOpt.put(Keys.KEY_ALIGN, Alignment.LEFT.getValue());

		doc.getLines().add(new Line(ContextType.TEXT, "凭  条", titleTextOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "1"));
		doc.getLines().add(new Line(ContextType.TEXT, "凭条编号     : 20200101000123", bodyOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "1"));
		doc.getLines().add(new Line(ContextType.TEXT, "申请人 : 20200101000123", bodyOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "1"));
		doc.getLines().add(new Line(ContextType.TEXT, "申请人身份证号码 : ******19910719**46", bodyOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "1"));
		doc.getLines().add(new Line(ContextType.TEXT, "申请业务 : 临时身份证明", bodyOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "1"));
		doc.getLines().add(new Line(ContextType.TEXT, "申请时间 : 2020-10-30", bodyOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "1"));
		doc.getLines().add(new Line(ContextType.TEXT, "所属机构 : 大竹县公安局", bodyOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "2"));

		Map<String, String> borderOpt = new HashMap<>();
		borderOpt.put(Keys.KEY_ALIGN, Alignment.CENTER.getValue());

		doc.getLines().add(new Line(ContextType.TEXT, "----------------------------------", borderOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "1"));

		doc.getLines().add(new Line(ContextType.TEXT, "领取凭条后，请耐心在大厅等候通知，如需查询进度，请在终端机上扫描下方二维码", bodyOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "5"));

		Map<String, String> qrOpt = new HashMap<>();
		qrOpt.put(Keys.KEY_ALIGN, Alignment.CENTER.getValue());
		qrOpt.put(Keys.KEY_QR_SIZE, "10");

		doc.getLines().add(new Line(ContextType.QR_CODE, "12345678", qrOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "10"));

		doc.getLines().add(new Line(ContextType.CMD_CUT, "0"));
		System.out.println(CmdEncoder.encodeHex(doc));

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(doc);
		System.out.println(json);
	}

}