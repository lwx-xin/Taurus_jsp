package org.taurus.entity.sys;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 
 * 
 * 
 **/
@TableName("t_s_file")
public class TSFileEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件id
	 */
	@TableId(type = IdType.UUID)
	private String fileId;

	/**
	 * 文件类型
	 */
	private String fileType;

	/**
	 * 文件名-未处理
	 */
	private String fileNameRealy;

	/**
	 * 文件名-处理后
	 */
	private String fileName;

	/**
	 * 文件大小，单位kb
	 */
	private String fileSize;

	/**
	 * 文件所属文件夹id
	 */
	private String fileFolderId;

	/**
	 * 
	 */
	private String fileDelFlg;

	/**
	 * 
	 */
	private Date fileCreateTime;

	/**
	 * 
	 */
	private String fileCreateUser;

	/**
	 * 
	 */
	private Date fileModifyTime;

	/**
	 * 
	 */
	private String fileModifyUser;


	/**
	 * 设置 文件id
	 * @param FileId 文件id
	 */
	public void setFileId(String fileId){
		this.fileId = fileId;
	}

	/**
	 * 获取 文件id
	 * @return 文件id
	 */
	public String getFileId(){
		return this.fileId;
	}

	/**
	 * 设置 文件类型
	 * @param FileType 文件类型
	 */
	public void setFileType(String fileType){
		this.fileType = fileType;
	}

	/**
	 * 获取 文件类型
	 * @return 文件类型
	 */
	public String getFileType(){
		return this.fileType;
	}

	/**
	 * 设置 文件名-未处理
	 * @param FileNameRealy 文件名-未处理
	 */
	public void setFileNameRealy(String fileNameRealy){
		this.fileNameRealy = fileNameRealy;
	}

	/**
	 * 获取 文件名-未处理
	 * @return 文件名-未处理
	 */
	public String getFileNameRealy(){
		return this.fileNameRealy;
	}

	/**
	 * 设置 文件名-处理后
	 * @param FileName 文件名-处理后
	 */
	public void setFileName(String fileName){
		this.fileName = fileName;
	}

	/**
	 * 获取 文件名-处理后
	 * @return 文件名-处理后
	 */
	public String getFileName(){
		return this.fileName;
	}

	/**
	 * 设置 文件大小，单位kb
	 * @param FileSize 文件大小，单位kb
	 */
	public void setFileSize(String fileSize){
		this.fileSize = fileSize;
	}

	/**
	 * 获取 文件大小，单位kb
	 * @return 文件大小，单位kb
	 */
	public String getFileSize(){
		return this.fileSize;
	}

	/**
	 * 设置 文件所属文件夹id
	 * @param FileFolderId 文件所属文件夹id
	 */
	public void setFileFolderId(String fileFolderId){
		this.fileFolderId = fileFolderId;
	}

	/**
	 * 获取 文件所属文件夹id
	 * @return 文件所属文件夹id
	 */
	public String getFileFolderId(){
		return this.fileFolderId;
	}

	/**
	 * 设置 
	 * @param FileDelFlg 
	 */
	public void setFileDelFlg(String fileDelFlg){
		this.fileDelFlg = fileDelFlg;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getFileDelFlg(){
		return this.fileDelFlg;
	}

	/**
	 * 设置 
	 * @param FileCreateTime 
	 */
	public void setFileCreateTime(Date fileCreateTime){
		this.fileCreateTime = fileCreateTime;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public Date getFileCreateTime(){
		return this.fileCreateTime;
	}

	/**
	 * 设置 
	 * @param FileCreateUser 
	 */
	public void setFileCreateUser(String fileCreateUser){
		this.fileCreateUser = fileCreateUser;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getFileCreateUser(){
		return this.fileCreateUser;
	}

	/**
	 * 设置 
	 * @param FileModifyTime 
	 */
	public void setFileModifyTime(Date fileModifyTime){
		this.fileModifyTime = fileModifyTime;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public Date getFileModifyTime(){
		return this.fileModifyTime;
	}

	/**
	 * 设置 
	 * @param FileModifyUser 
	 */
	public void setFileModifyUser(String fileModifyUser){
		this.fileModifyUser = fileModifyUser;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getFileModifyUser(){
		return this.fileModifyUser;
	}


}
