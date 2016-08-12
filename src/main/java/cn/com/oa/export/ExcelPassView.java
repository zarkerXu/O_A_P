package cn.com.oa.export;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelPassView extends AbstractExcelView {

	private String title;
	
	private String name;
	

	private String[] headers = new String[] { "序号", "单位", "名称", "性别", "职位",
			"电话", "备注", "审批状态" };

	private String[] tableTitle = new String[] { "参会人员", "听会人员", "请假人员" };

	private List<List<List<String>>> dataset;

	public ExcelPassView() {
		super();
		setContentType("application/vnd.ms-excel");
	}

	public ExcelPassView(String title,String name, List<List<List<String>>> dataset) {
		super();
		setContentType("application/vnd.ms-excel");
		this.title = title;
		this.name = name;
		this.dataset = dataset;
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 声明一个工作薄
		// 生成一个表格
		if (title.contains("/")) {
			title = title.replace("/", " ");
		}
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((int) 15);
		
		HSSFCellStyle style1 = workbook.createCellStyle();
		// 设置这些样式
		style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style1.setFillBackgroundColor(HSSFColor.WHITE.index);
		style1.setFillForegroundColor(HSSFColor.WHITE.index);
		// 生成一个字体
		HSSFFont font1 = workbook.createFont();
		font1.setFontHeightInPoints((short) 15);
		font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style1.setFont(font1);
		
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setFillBackgroundColor(HSSFColor.WHITE.index);
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style2.setFillBackgroundColor(HSSFColor.WHITE.index);
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		Integer rownum = 0;
		HSSFCell cell0 = sheet.createRow(rownum).createCell(0);
		cell0.setCellValue(new HSSFRichTextString(name));
		cell0.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 7));
		rownum++;
		for (Integer j = 0; j < 3; j++) {
			HSSFRow row1 = sheet.createRow(rownum);
			HSSFCell cell1 = row1.createCell(0);
			HSSFRichTextString text1 = new HSSFRichTextString(tableTitle[j]);
			cell1.setCellValue(text1);
			cell1.setCellStyle(style1);
			sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 7));
			rownum++;
			HSSFRow row = sheet.createRow(rownum++);
			for (int i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
			// 遍历集合数据，产生数据行
			Iterator<List<String>> it = dataset.get(j).iterator();
			while (it.hasNext()) {
				row = sheet.createRow(rownum++);
				List<String> t = (List<String>) it.next();
				int i = 0;
				for (String value : t) {
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(style2);
					try {
						// 判断值的类型后进行强制类型转换
						String textValue = null;
						textValue = value;
						if (textValue != null) {
							Pattern p = Pattern.compile("^//d+(//.//d+)?$");
							Matcher matcher = p.matcher(textValue);
							if (matcher.matches()) {
								// 是数字当作double处理
								cell.setCellValue(Double.parseDouble(textValue));
							} else {
								HSSFRichTextString richString = new HSSFRichTextString(
										textValue);
								HSSFFont font3 = workbook.createFont();
								richString.applyFont(font3);
								cell.setCellValue(richString);
							}
						}
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} finally {
						// 清理资源
					}
					i++;
				}
			}
		}
		title = new String(title.getBytes("gb2312"), "ISO8859-1")
				+ new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		response.setHeader("Content-Disposition", "attachment;filename="
				+ title + ".xls");
		response.setContentType(getContentType());
		OutputStream out = response.getOutputStream();
		try {
			workbook.write(out);
			workbook.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	public List<List<List<String>>> getDataset() {
		return dataset;
	}

	public void setDataset(List<List<List<String>>> dataset) {
		this.dataset = dataset;
	}

}
