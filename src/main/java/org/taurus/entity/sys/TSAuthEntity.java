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

@TableName("t_s_auth")
public class TSAuthEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.UUID)
	private String authId;

	/**
	 * 名称
	 */
	private String authName;

	/**
	 * 权限级别
	 */
	private String authLevel;

	/**
	 * 
	 */
	private String authDelFlg;

	/**
	 * 创建时间
	 */
	private Date authCreateTime;

	/**
	 * 创建人
	 */
	private String authCreateUserId;

	/**
	 * 修改时间
	 */
	private Date authModifyTime;

	/**
	 * 修改人
	 */
	private String authModifyUserId;


	/**
	 * 设置 
	 * @param AuthId 
	 */
	public void setAuthId(String authId){
		this.authId = authId;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getAuthId(){
		return this.authId;
	}

	/**
	 * 设置 名称
	 * @param AuthName 名称
	 */
	public void setAuthName(String authName){
		this.authName = authName;
	}

	/**
	 * 获取 名称
	 * @return 名称
	 */
	public String getAuthName(){
		return this.authName;
	}

	/**
	 * 设置 权限级别
	 * @param AuthLevel 权限级别
	 */
	public void setAuthLevel(String authLevel){
		this.authLevel = authLevel;
	}

	/**
	 * 获取 权限级别
	 * @return 权限级别
	 */
	public String getAuthLevel(){
		return this.authLevel;
	}

	/**
	 * 设置 
	 * @param AuthDelFlg 
	 */
	public void setAuthDelFlg(String authDelFlg){
		this.authDelFlg = authDelFlg;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getAuthDelFlg(){
		return this.authDelFlg;
	}

	/**
	 * 设置 创建时间
	 * @param AuthCreateTime 创建时间
	 */
	public void setAuthCreateTime(Date authCreateTime){
		this.authCreateTime = authCreateTime;
	}

	/**
	 * 获取 创建时间
	 * @return 创建时间
	 */
	public Date getAuthCreateTime(){
		return this.authCreateTime;
	}

	/**
	 * 设置 创建人
	 * @param AuthCreateUserId 创建人
	 */
	public void setAuthCreateUserId(String authCreateUserId){
		this.authCreateUserId = authCreateUserId;
	}

	/**
	 * 获取 创建人
	 * @return 创建人
	 */
	public String getAuthCreateUserId(){
		return this.authCreateUserId;
	}

	/**
	 * 设置 修改时间
	 * @param AuthModifyTime 修改时间
	 */
	public void setAuthModifyTime(Date authModifyTime){
		this.authModifyTime = authModifyTime;
	}

	/**
	 * 获取 修改时间
	 * @return 修改时间
	 */
	public Date getAuthModifyTime(){
		return this.authModifyTime;
	}

	/**
	 * 设置 修改人
	 * @param AuthModifyUserId 修改人
	 */
	public void setAuthModifyUserId(String authModifyUserId){
		this.authModifyUserId = authModifyUserId;
	}

	/**
	 * 获取 修改人
	 * @return 修改人
	 */
	public String getAuthModifyUserId(){
		return this.authModifyUserId;
	}

	@Override
	public String toString() {
		return "TSAuthEntity [authId=" + authId + ", authName=" + authName + ", authLevel=" + authLevel
				+ ", authDelFlg=" + authDelFlg + ", authCreateTime=" + authCreateTime + ", authCreateUserId="
				+ authCreateUserId + ", authModifyTime=" + authModifyTime + ", authModifyUserId=" + authModifyUserId
				+ "]";
	}

}
