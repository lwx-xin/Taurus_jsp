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

@TableName("t_s_log")
public class TSLogEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.UUID)
	private String logId;

	/**
	 * 
	 */
	private String exceptionType;

	/**
	 * 
	 */
	private String exceptionMessage;

	/**
	 * 
	 */
	private String exceptionInfo;

	/**
	 * 
	 */
	private String exceptionStatus;

	/**
	 * 
	 */
	private String logDelFlg;

	/**
	 * 
	 */
	private Date logCreateTime;

	/**
	 * 
	 */
	private String logCreateUser;

	/**
	 * 
	 */
	private Date logModifyTime;

	/**
	 * 
	 */
	private String logModifyUser;


	/**
	 * 设置 
	 * @param LogId 
	 */
	public void setLogId(String logId){
		this.logId = logId;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getLogId(){
		return this.logId;
	}

	/**
	 * 设置 
	 * @param ExceptionType 
	 */
	public void setExceptionType(String exceptionType){
		this.exceptionType = exceptionType;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getExceptionType(){
		return this.exceptionType;
	}

	/**
	 * 设置 
	 * @param ExceptionMessage 
	 */
	public void setExceptionMessage(String exceptionMessage){
		this.exceptionMessage = exceptionMessage;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getExceptionMessage(){
		return this.exceptionMessage;
	}

	/**
	 * 设置 
	 * @param ExceptionInfo 
	 */
	public void setExceptionInfo(String exceptionInfo){
		this.exceptionInfo = exceptionInfo;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getExceptionInfo(){
		return this.exceptionInfo;
	}

	/**
	 * 设置 
	 * @param ExceptionStatus 
	 */
	public void setExceptionStatus(String exceptionStatus){
		this.exceptionStatus = exceptionStatus;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getExceptionStatus(){
		return this.exceptionStatus;
	}

	/**
	 * 设置 
	 * @param LogDelFlg 
	 */
	public void setLogDelFlg(String logDelFlg){
		this.logDelFlg = logDelFlg;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getLogDelFlg(){
		return this.logDelFlg;
	}

	/**
	 * 设置 
	 * @param LogCreateTime 
	 */
	public void setLogCreateTime(Date logCreateTime){
		this.logCreateTime = logCreateTime;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public Date getLogCreateTime(){
		return this.logCreateTime;
	}

	/**
	 * 设置 
	 * @param LogCreateUser 
	 */
	public void setLogCreateUser(String logCreateUser){
		this.logCreateUser = logCreateUser;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getLogCreateUser(){
		return this.logCreateUser;
	}

	/**
	 * 设置 
	 * @param LogModifyTime 
	 */
	public void setLogModifyTime(Date logModifyTime){
		this.logModifyTime = logModifyTime;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public Date getLogModifyTime(){
		return this.logModifyTime;
	}

	/**
	 * 设置 
	 * @param LogModifyUser 
	 */
	public void setLogModifyUser(String logModifyUser){
		this.logModifyUser = logModifyUser;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getLogModifyUser(){
		return this.logModifyUser;
	}


}
