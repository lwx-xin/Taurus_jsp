package org.taurus.entity.sys;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 
 * 
 * 
 **/

@TableName("t_auth_user")
public class TAuthUserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String authUserId;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 权限id
	 */
	private String authId;

	/**
	 * 
	 */
	private String authUserDelFlg;

	/**
	 * 创建时间
	 */
	private Date authUserCreateTime;

	/**
	 * 创建人
	 */
	private String authUserCreateUserId;

	/**
	 * 修改时间
	 */
	private Date authUserModifyTime;

	/**
	 * 修改人
	 */
	private String authUserModifyUserId;


	/**
	 * 设置 
	 * @param AuthUserId 
	 */
	public void setAuthUserId(String authUserId){
		this.authUserId = authUserId;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getAuthUserId(){
		return this.authUserId;
	}

	/**
	 * 设置 用户id
	 * @param UserId 用户id
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}

	/**
	 * 获取 用户id
	 * @return 用户id
	 */
	public String getUserId(){
		return this.userId;
	}

	/**
	 * 设置 权限id
	 * @param AuthId 权限id
	 */
	public void setAuthId(String authId){
		this.authId = authId;
	}

	/**
	 * 获取 权限id
	 * @return 权限id
	 */
	public String getAuthId(){
		return this.authId;
	}

	/**
	 * 设置 
	 * @param AuthUserDelFlg 
	 */
	public void setAuthUserDelFlg(String authUserDelFlg){
		this.authUserDelFlg = authUserDelFlg;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getAuthUserDelFlg(){
		return this.authUserDelFlg;
	}

	/**
	 * 设置 创建时间
	 * @param AuthUserCreateTime 创建时间
	 */
	public void setAuthUserCreateTime(Date authUserCreateTime){
		this.authUserCreateTime = authUserCreateTime;
	}

	/**
	 * 获取 创建时间
	 * @return 创建时间
	 */
	public Date getAuthUserCreateTime(){
		return this.authUserCreateTime;
	}

	/**
	 * 设置 创建人
	 * @param AuthUserCreateUserId 创建人
	 */
	public void setAuthUserCreateUserId(String authUserCreateUserId){
		this.authUserCreateUserId = authUserCreateUserId;
	}

	/**
	 * 获取 创建人
	 * @return 创建人
	 */
	public String getAuthUserCreateUserId(){
		return this.authUserCreateUserId;
	}

	/**
	 * 设置 修改时间
	 * @param AuthUserModifyTime 修改时间
	 */
	public void setAuthUserModifyTime(Date authUserModifyTime){
		this.authUserModifyTime = authUserModifyTime;
	}

	/**
	 * 获取 修改时间
	 * @return 修改时间
	 */
	public Date getAuthUserModifyTime(){
		return this.authUserModifyTime;
	}

	/**
	 * 设置 修改人
	 * @param AuthUserModifyUserId 修改人
	 */
	public void setAuthUserModifyUserId(String authUserModifyUserId){
		this.authUserModifyUserId = authUserModifyUserId;
	}

	/**
	 * 获取 修改人
	 * @return 修改人
	 */
	public String getAuthUserModifyUserId(){
		return this.authUserModifyUserId;
	}

	@Override
	public String toString() {
		return "TAuthUserEntity [authUserId=" + authUserId + ", userId=" + userId + ", authId=" + authId
				+ ", authUserDelFlg=" + authUserDelFlg + ", authUserCreateTime=" + authUserCreateTime
				+ ", authUserCreateUserId=" + authUserCreateUserId + ", authUserModifyTime=" + authUserModifyTime
				+ ", authUserModifyUserId=" + authUserModifyUserId + "]";
	}

}
