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

@TableName("t_m_format")
public class TMFormatEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.UUID)
	private String formatGroup;

	/**
	 * 
	 */
	private String formatRegex;

	/**
	 * 
	 */
	private String formatRegexValue;

	/**
	 * 
	 */
	private String formatDelFlg;

	/**
	 * 创建时间
	 */
	private Date formatCreateTime;

	/**
	 * 创建人
	 */
	private String formatCreateUserId;

	/**
	 * 修改时间
	 */
	private Date formatModifyTime;

	/**
	 * 修改人
	 */
	private String formatModifyUserId;


	/**
	 * 设置 
	 * @param FormatGroup 
	 */
	public void setFormatGroup(String formatGroup){
		this.formatGroup = formatGroup;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getFormatGroup(){
		return this.formatGroup;
	}

	/**
	 * 设置 
	 * @param FormatRegex 
	 */
	public void setFormatRegex(String formatRegex){
		this.formatRegex = formatRegex;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getFormatRegex(){
		return this.formatRegex;
	}

	/**
	 * 设置 
	 * @param FormatRegexValue 
	 */
	public void setFormatRegexValue(String formatRegexValue){
		this.formatRegexValue = formatRegexValue;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getFormatRegexValue(){
		return this.formatRegexValue;
	}

	/**
	 * 设置 
	 * @param FormatDelFlg 
	 */
	public void setFormatDelFlg(String formatDelFlg){
		this.formatDelFlg = formatDelFlg;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getFormatDelFlg(){
		return this.formatDelFlg;
	}

	/**
	 * 设置 创建时间
	 * @param FormatCreateTime 创建时间
	 */
	public void setFormatCreateTime(Date formatCreateTime){
		this.formatCreateTime = formatCreateTime;
	}

	/**
	 * 获取 创建时间
	 * @return 创建时间
	 */
	public Date getFormatCreateTime(){
		return this.formatCreateTime;
	}

	/**
	 * 设置 创建人
	 * @param FormatCreateUserId 创建人
	 */
	public void setFormatCreateUserId(String formatCreateUserId){
		this.formatCreateUserId = formatCreateUserId;
	}

	/**
	 * 获取 创建人
	 * @return 创建人
	 */
	public String getFormatCreateUserId(){
		return this.formatCreateUserId;
	}

	/**
	 * 设置 修改时间
	 * @param FormatModifyTime 修改时间
	 */
	public void setFormatModifyTime(Date formatModifyTime){
		this.formatModifyTime = formatModifyTime;
	}

	/**
	 * 获取 修改时间
	 * @return 修改时间
	 */
	public Date getFormatModifyTime(){
		return this.formatModifyTime;
	}

	/**
	 * 设置 修改人
	 * @param FormatModifyUserId 修改人
	 */
	public void setFormatModifyUserId(String formatModifyUserId){
		this.formatModifyUserId = formatModifyUserId;
	}

	/**
	 * 获取 修改人
	 * @return 修改人
	 */
	public String getFormatModifyUserId(){
		return this.formatModifyUserId;
	}


}
