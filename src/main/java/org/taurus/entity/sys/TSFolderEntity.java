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
@TableName("t_s_folder")
public class TSFolderEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件夹id
	 */
	@TableId(type = IdType.UUID)
	private String folderId;

	/**
	 * 文件夹名字
	 */
	private String folderName;

	/**
	 * 上级文件夹id
	 */
	private String parentFolderId;

	/**
	 * 所属者id
	 */
	private String folderOwner;

	/**
	 * 删除标志
	 */
	private String folderDelFlg;

	/**
	 * 创建时间
	 */
	private Date folderCreateTime;

	/**
	 * 创建者
	 */
	private String folderCreateUser;

	/**
	 * 修改时间
	 */
	private Date folderModifyTime;

	/**
	 * 修改者
	 */
	private String folderModifyUser;


	/**
	 * 设置 文件夹id
	 * @param FolderId 文件夹id
	 */
	public void setFolderId(String folderId){
		this.folderId = folderId;
	}

	/**
	 * 获取 文件夹id
	 * @return 文件夹id
	 */
	public String getFolderId(){
		return this.folderId;
	}

	/**
	 * 设置 文件夹名字
	 * @param FolderName 文件夹名字
	 */
	public void setFolderName(String folderName){
		this.folderName = folderName;
	}

	/**
	 * 获取 文件夹名字
	 * @return 文件夹名字
	 */
	public String getFolderName(){
		return this.folderName;
	}

	/**
	 * 设置 上级文件夹id
	 * @param ParentFolderId 上级文件夹id
	 */
	public void setParentFolderId(String parentFolderId){
		this.parentFolderId = parentFolderId;
	}

	/**
	 * 获取 上级文件夹id
	 * @return 上级文件夹id
	 */
	public String getParentFolderId(){
		return this.parentFolderId;
	}

	/**
	 * 设置 所属者id
	 * @param FolderOwner 所属者id
	 */
	public void setFolderOwner(String folderOwner){
		this.folderOwner = folderOwner;
	}

	/**
	 * 获取 所属者id
	 * @return 所属者id
	 */
	public String getFolderOwner(){
		return this.folderOwner;
	}

	/**
	 * 设置 删除标志
	 * @param FolderDelFlg 删除标志
	 */
	public void setFolderDelFlg(String folderDelFlg){
		this.folderDelFlg = folderDelFlg;
	}

	/**
	 * 获取 删除标志
	 * @return 删除标志
	 */
	public String getFolderDelFlg(){
		return this.folderDelFlg;
	}

	/**
	 * 设置 创建时间
	 * @param FolderCreateTime 创建时间
	 */
	public void setFolderCreateTime(Date folderCreateTime){
		this.folderCreateTime = folderCreateTime;
	}

	/**
	 * 获取 创建时间
	 * @return 创建时间
	 */
	public Date getFolderCreateTime(){
		return this.folderCreateTime;
	}

	/**
	 * 设置 创建者
	 * @param FolderCreateUser 创建者
	 */
	public void setFolderCreateUser(String folderCreateUser){
		this.folderCreateUser = folderCreateUser;
	}

	/**
	 * 获取 创建者
	 * @return 创建者
	 */
	public String getFolderCreateUser(){
		return this.folderCreateUser;
	}

	/**
	 * 设置 修改时间
	 * @param FolderModifyTime 修改时间
	 */
	public void setFolderModifyTime(Date folderModifyTime){
		this.folderModifyTime = folderModifyTime;
	}

	/**
	 * 获取 修改时间
	 * @return 修改时间
	 */
	public Date getFolderModifyTime(){
		return this.folderModifyTime;
	}

	/**
	 * 设置 修改者
	 * @param FolderModifyUser 修改者
	 */
	public void setFolderModifyUser(String folderModifyUser){
		this.folderModifyUser = folderModifyUser;
	}

	/**
	 * 获取 修改者
	 * @return 修改者
	 */
	public String getFolderModifyUser(){
		return this.folderModifyUser;
	}


}
