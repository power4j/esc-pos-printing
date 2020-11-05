package com.power4j.kit.printing.escpos.codec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.power4j.kit.printing.escpos.ContextType;
import com.power4j.kit.printing.escpos.Doc;
import com.power4j.kit.printing.escpos.DocProcessor;
import com.power4j.kit.printing.escpos.Line;
import com.power4j.kit.printing.escpos.bmp.BmpModel;
import com.power4j.kit.printing.escpos.constants.Keys;
import com.power4j.kit.printing.escpos.style.Alignment;
import com.power4j.kit.printing.escpos.style.FontSize;
import com.power4j.kit.printing.escpos.style.Underline;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DefaultCmdEncoderTest {

	private static final Map<String, String> centerAlign = new HashMap<>();

	private static final Map<String, String> rightAlign = new HashMap<>();

	static {
		centerAlign.put(Keys.KEY_ALIGN, Alignment.CENTER.getValue());
		rightAlign.put(Keys.KEY_ALIGN, Alignment.RIGHT.getValue());
	}

	@Test
	public void testWrite() {
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

		System.out.println(DocProcessor.getCmdEncoder().encodeHex(doc));
	}

	@Test
	public void testCenterText() {
		Doc doc = new Doc();
		doc.setLines(new ArrayList<>());
		Map<String, String> titleTextOpt = new HashMap<>();
		titleTextOpt.put(Keys.KEY_ALIGN, Alignment.CENTER.getValue());
		doc.getLines().add(new Line(ContextType.TEXT, "this-is-a-test-msg", null));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "10", centerAlign));
		doc.getLines().add(new Line(ContextType.CMD_CUT, "0"));
		System.out.println(DocProcessor.getCmdEncoder().encodeHex(doc));
	}

	@Test
	public void testRightText() {
		Doc doc = new Doc();
		doc.setLines(new ArrayList<>());
		doc.getLines().add(new Line(ContextType.TEXT, "this-is-a-test-msg", rightAlign));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "10"));
		doc.getLines().add(new Line(ContextType.CMD_CUT, "0"));
		System.out.println(DocProcessor.getCmdEncoder().encodeHex(doc));
	}

	@Test
	public void testStyles() {
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
		System.out.println(DocProcessor.getCmdEncoder().encodeHex(doc));
	}

	@Test
	public void testBiz() throws JsonProcessingException {
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
		doc.getLines().add(new Line(ContextType.TEXT, "凭条编号 : 20200101000123", bodyOpt));
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
		System.out.println("doc: encodeHex");
		System.out.println(DocProcessor.getCmdEncoder().encodeHex(doc));

		System.out.println("doc: encodeBase64");
		System.out.println(DocProcessor.getCmdEncoder().encodeBase64(doc));

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(doc);
		System.out.println("doc: json");
		System.out.println(json);
	}

	@Test
	public void testBmp() {
		String testImg = SampleData.imageGitHub;

		Doc doc = new Doc();
		doc.setCharsetName("GB2312");
		doc.setLines(new ArrayList<>());
		Map<String, String> titleTextOpt = new HashMap<>();
		titleTextOpt.put(Keys.KEY_ALIGN, Alignment.CENTER.getValue());
		titleTextOpt.put(Keys.KEY_BOLD, "true");

		Map<String, String> bmpOpt1 = new HashMap<>();
		bmpOpt1.put(Keys.KEY_ALIGN, Alignment.CENTER.getValue());
		bmpOpt1.put(Keys.KEY_BMP_MODEL, BmpModel.M0.getValue());
		doc.getLines().add(new Line(ContextType.TEXT, "8-dot single-density", titleTextOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "1"));
		doc.getLines().add(new Line(ContextType.BMP, testImg, bmpOpt1));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "5"));

		Map<String, String> bmpOpt2 = new HashMap<>();
		bmpOpt2.put(Keys.KEY_ALIGN, Alignment.CENTER.getValue());
		bmpOpt2.put(Keys.KEY_BMP_MODEL, BmpModel.M1.getValue());
		doc.getLines().add(new Line(ContextType.TEXT, "8-dot double-density", titleTextOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "1"));
		doc.getLines().add(new Line(ContextType.BMP, testImg, bmpOpt2));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "2"));

		Map<String, String> bmpOpt3 = new HashMap<>();
		bmpOpt3.put(Keys.KEY_ALIGN, Alignment.CENTER.getValue());
		bmpOpt3.put(Keys.KEY_BMP_MODEL, BmpModel.M32.getValue());
		doc.getLines().add(new Line(ContextType.TEXT, "24-dot single-density", titleTextOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "1"));
		doc.getLines().add(new Line(ContextType.BMP, testImg, bmpOpt3));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "2"));

		Map<String, String> bmpOpt4 = new HashMap<>();
		bmpOpt4.put(Keys.KEY_ALIGN, Alignment.CENTER.getValue());
		bmpOpt4.put(Keys.KEY_BMP_MODEL, BmpModel.M33.getValue());
		doc.getLines().add(new Line(ContextType.TEXT, "24-dot double-density", titleTextOpt));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "1"));
		doc.getLines().add(new Line(ContextType.BMP, testImg, bmpOpt4));
		doc.getLines().add(new Line(ContextType.CMD_FEED, "5"));

		doc.getLines().add(new Line(ContextType.CMD_CUT, "0"));

		System.out.println(DocProcessor.getCmdEncoder().encodeHex(doc));

	}

}