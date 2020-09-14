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

@TableName("t_m_code")
public class TMCodeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * code分组
	 */
	private String codeGroup;

	/**
	 * code的名字
	 */
	private String codeName;

	/**
	 * code的值
	 */
	@TableId(type = IdType.INPUT)
	private String codeValue;

	/**
	 * 排序
	 */
	private String codeOrder;

	/**
	 * 
	 */
	private String codeDelFlg;

	/**
	 * 创建时间
	 */
	private Date codeCreateTime;

	/**
	 * 创建人
	 */
	private String codeCreateUserId;

	/**
	 * 修改时间
	 */
	private Date codeModifyTime;

	/**
	 * 修改人
	 */
	private String codeModifyUserId;


	/**
	 * 设置 code分组
	 * @param CodeGroup code分组
	 */
	public void setCodeGroup(String codeGroup){
		this.codeGroup = codeGroup;
	}

	/**
	 * 获取 code分组
	 * @return code分组
	 */
	public String getCodeGroup(){
		return this.codeGroup;
	}

	/**
	 * 设置 code的名字
	 * @param CodeName code的名字
	 */
	public void setCodeName(String codeName){
		this.codeName = codeName;
	}

	/**
	 * 获取 code的名字
	 * @return code的名字
	 */
	public String getCodeName(){
		return this.codeName;
	}

	/**
	 * 设置 code的值
	 * @param CodeValue code的值
	 */
	public void setCodeValue(String codeValue){
		this.codeValue = codeValue;
	}

	/**
	 * 获取 code的值
	 * @return code的值
	 */
	public String getCodeValue(){
		return this.codeValue;
	}

	/**
	 * 设置 排序
	 * @param CodeOrder 排序
	 */
	public void setCodeOrder(String codeOrder){
		this.codeOrder = codeOrder;
	}

	/**
	 * 获取 排序
	 * @return 排序
	 */
	public String getCodeOrder(){
		return this.codeOrder;
	}

	/**
	 * 设置 
	 * @param CodeDelFlg 
	 */
	public void setCodeDelFlg(String codeDelFlg){
		this.codeDelFlg = codeDelFlg;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getCodeDelFlg(){
		return this.codeDelFlg;
	}

	/**
	 * 设置 创建时间
	 * @param CodeCreateTime 创建时间
	 */
	public void setCodeCreateTime(Date codeCreateTime){
		this.codeCreateTime = codeCreateTime;
	}

	/**
	 * 获取 创建时间
	 * @return 创建时间
	 */
	public Date getCodeCreateTime(){
		return this.codeCreateTime;
	}

	/**
	 * 设置 创建人
	 * @param CodeCreateUserId 创建人
	 */
	public void setCodeCreateUserId(String codeCreateUserId){
		this.codeCreateUserId = codeCreateUserId;
	}

	/**
	 * 获取 创建人
	 * @return 创建人
	 */
	public String getCodeCreateUserId(){
		return this.codeCreateUserId;
	}

	/**
	 * 设置 修改时间
	 * @param CodeModifyTime 修改时间
	 */
	public void setCodeModifyTime(Date codeModifyTime){
		this.codeModifyTime = codeModifyTime;
	}

	/**
	 * 获取 修改时间
	 * @return 修改时间
	 */
	public Date getCodeModifyTime(){
		return this.codeModifyTime;
	}

	/**
	 * 设置 修改人
	 * @param CodeModifyUserId 修改人
	 */
	public void setCodeModifyUserId(String codeModifyUserId){
		this.codeModifyUserId = codeModifyUserId;
	}

	/**
	 * 获取 修改人
	 * @return 修改人
	 */
	public String getCodeModifyUserId(){
		return this.codeModifyUserId;
	}


}
