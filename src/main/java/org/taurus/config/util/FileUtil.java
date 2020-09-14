package org.taurus.config.util;

import java.io.File;
import java.text.DecimalFormat;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	/**
	 * 获取文件大小KB
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileSize(File file) {
		if (file != null && file.exists() && file.isFile()) {
			DecimalFormat df = new DecimalFormat("#.##");
			return df.format((double) ((double) file.length() / 1024));
		}
		return "0";
	}

	/**
	 * 获取文件大小KB
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileSize(MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			DecimalFormat df = new DecimalFormat("#.##");
			return df.format((double) ((double) file.getSize() / 1024));
		}
		return "0";
	}

	/**
	 * 文件夹不存在就创建
	 * @param path
	 */
	public static void createDir(String path) {
		File file = new File(path);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}
	
	/**
	 * 根据文件后缀返回文件分组
	 * @param suffix
	 * @return
	 */
	public static String getFileType(String suffix) {
		String fileGroup = CodeKeyValue.FILE_TYPE_OTHER.value();
		switch (suffix.toUpperCase()) {
			case "MPEG":
			case "AVI":
			case "NAVI":
			case "ASF":
			case "WMV":
			case "MOV":
			case "3GP":
			case "MP4":
				fileGroup = CodeKeyValue.FILE_TYPE_VIDEO.value();
				break;
			case "MP3":
				fileGroup = CodeKeyValue.FILE_TYPE_AUDIO.value();
				break;
			case "IMG":
			case "PNG":
			case "JPG":
				fileGroup = CodeKeyValue.FILE_TYPE_PICTURE.value();
				break;
			case "TXT":
				fileGroup = CodeKeyValue.FILE_TYPE_TXT.value();
				break;
			case "LOG":
				fileGroup = CodeKeyValue.FILE_TYPE_LOG.value();
				break;
			case "XLS":
			case "XLSX":
				fileGroup = CodeKeyValue.FILE_TYPE_EXCEL.value();
				break;
			case "PDF":
				fileGroup = CodeKeyValue.FILE_TYPE_PDF.value();
				break;
			case "RAR":
			case "7Z":
			case "ZIP":
				fileGroup = CodeKeyValue.FILE_TYPE_RAR.value();
				break;
			case "DOC":
			case "DOCX":
				fileGroup = CodeKeyValue.FILE_TYPE_WORD.value();
				break;
			default:
				fileGroup = CodeKeyValue.FILE_TYPE_OTHER.value();
				break;
		}
		return fileGroup;
	}

}
