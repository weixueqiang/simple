package com.jo.dy.ot.controller;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.util.Result;

@RestController
@RequestMapping("/excel")
public class ExcelController {

	private final static String xls = "xls";
	private final static String xlsx = "xlsx";

	@RequestMapping("/export")
	public Result export(HttpServletResponse response) {
		Result res = new Result();
		XSSFWorkbook workbook = getXSSFWorkbook(users);
		if (workbook != null) {
			response.addHeader("Content-Disposition", "attachment;filename=" + new String("users.xlsx".getBytes()));
			response.setContentType("application/octet-stream");
			try {
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				workbook.write(toClient);
				toClient.flush();
				toClient.close();
			} catch (IOException e) {
			}
		}
		return res;
	}

	@RequestMapping("/import")
	public Result importExcel(MultipartFile file) {
		Result res = new Result();
		try {
			List<User> users = readExcel(file);
			users.forEach((e)->System.out.println(e.getUsername()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return res;
	}

	private List<User> readExcel(MultipartFile file) throws IOException {
		checkFile(file);
		// 获得Workbook工作薄对象
		Workbook workbook = getWorkBook(file);
		List<User> list = new ArrayList<User>();
		if (workbook == null) {
			return null;
		}
		//工作表
		for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
			// 获得当前sheet工作表
			Sheet sheet = workbook.getSheetAt(sheetNum);
			if (sheet == null) {
				continue;
			}
			// 获得当前sheet的开始行
			int firstRowNum = 1;
			// 获得当前sheet的结束行
			int lastRowNum = sheet.getLastRowNum();
			// 循环除了行的所有行
			for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
				// 获得当前行
				Row row = sheet.getRow(rowNum);
				// 获得当前行的开始列
				int firstCellNum = row.getFirstCellNum();
				// 获得当前行的列数
				int lastCellNum = row.getPhysicalNumberOfCells();
				User user = new User();
				user.setId(Integer.parseInt(getCellValue(row.getCell(0))));
				user.setUsername(getCellValue(row.getCell(1)));
				user.setPassword(getCellValue(row.getCell(2)));	
				list.add(user);
			}
		}
		return list;
	}

	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 把数字当成String来读，避免出现1读成1.0的情况
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		// 判断数据的类型
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // 数字
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING: // 字符串
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA: // 公式
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_BLANK: // 空值
			cellValue = "";
			break;
		case Cell.CELL_TYPE_ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;
	}
	
	public static Workbook getWorkBook(MultipartFile file) {
		// 获得文件名
		String fileName = file.getOriginalFilename();
		// 创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		InputStream is = null;
		try {
			// 获取excel文件的io流
			is = file.getInputStream();
			// 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
			if (fileName.endsWith(xls)) {
				// 2003
				workbook = new HSSFWorkbook(is);
			} else if (fileName.endsWith(xlsx)) {
				// 2007
				workbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return workbook;

	}

	public static void checkFile(MultipartFile file) throws IOException {
		// 判断文件是否存在
		if (null == file) {
			throw new FileNotFoundException("文件不存在！");
		}
		// 获得文件名
		String fileName = file.getOriginalFilename();
		// 判断文件是否是excel文件
		if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
			throw new IOException(fileName + "不是excel文件");
		}
	}

	private final static String TITLE = "用户列表";
	private final static String[] KEYS = new String[] { "编号", "姓名", "密码" };

	private XSSFWorkbook getXSSFWorkbook(List<User> datas) {
		Queue<List<String>> queue = new LinkedBlockingDeque<List<String>>();
		for (User data : datas) {
			try {
				List<String> msg = new ArrayList<String>();
				msg.add(data.getId() + "");
				msg.add(data.getUsername());
				msg.add(data.getPassword());
				queue.add(msg);
			} catch (Exception e) {
				// e.printStackTrace();出错忽略这条数据

			}
		}

		return getExcel(queue);
	}

	private XSSFWorkbook getExcel(Queue<List<String>> queue) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		// 合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, KEYS.length - 1));
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		// 设置单元格居中
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		// 创建第一行
		XSSFRow row1 = sheet.createRow(0);
		XSSFCell cell00 = row1.createCell(0);
		cell00.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell00.setCellStyle(cellStyle);
		cell00.setCellValue(TITLE);
		// 编写第一行菜单
		XSSFRow row2 = sheet.createRow(1);
		for (int j = 0; j < KEYS.length; j++) {
			XSSFCell cell11 = row2.createCell(j);
			cell11.setCellType(XSSFCell.CELL_TYPE_STRING);
			cell11.setCellValue(KEYS[j]);
			cell11.setCellStyle(cellStyle);
		}
		int i = 2;
		while (!queue.isEmpty()) {
			List<String> elements = queue.poll();
			XSSFRow rowVal = sheet.createRow(i++);
			for (int j = 0; j < elements.size(); j++) {
				XSSFCell cell = rowVal.createCell(j);
				cell.setCellType(XSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(elements.get(j));
				cell.setCellStyle(cellStyle);
			}
		}
		return workbook;

	}

	private static List<User> users = new ArrayList<>();

	static {
		String username = "zhangsan";
		users.add(new User(1, username, "3f503bde9ea1d0acb3bfc98e4b58c97c149b3fa6", "salt"));
		username = "lisi";
		users.add(new User(2, username, "18d9baadcddac71fefb5ce20a5cbfb281fcc71e", "salt"));
		username = "wangwu";
		users.add(new User(3, username, "331043e9c170b0a3dc7c98cd169ba28a1b8296c3", "salt"));

	}

}
