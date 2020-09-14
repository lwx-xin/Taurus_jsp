package org.taurus.entity.sys;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 
 * 系统用户
 * 
 **/

@TableName("t_s_user")
public class TSUserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.UUID)
	private String userId;

	/**
	 * 昵称
	 */
	private String userName;

	/**
	 * 账号
	 */
	private String userNumber;

	/**
	 * 密码
	 */
	private String userPwd;

	/**
	 * 头像路径
	 */
	private String userHeadPortrait;

	/**
	 * 
	 */
	private String userDelFlg;

	/**
	 * 创建时间
	 */
	private Date userCreateTime;

	/**
	 * 创建人
	 */
	private String userCreateUserId;

	/**
	 * 修改时间
	 */
	private Date userModifyTime;

	/**
	 * 修改人
	 */
	private String userModifyUserId;


	/**
	 * 设置 
	 * @param UserId 
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getUserId(){
		return this.userId;
	}

	/**
	 * 设置 昵称
	 * @param UserName 昵称
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}

	/**
	 * 获取 昵称
	 * @return 昵称
	 */
	public String getUserName(){
		return this.userName;
	}

	/**
	 * 设置 账号
	 * @param UserNumber 账号
	 */
	public void setUserNumber(String userNumber){
		this.userNumber = userNumber;
	}

	/**
	 * 获取 账号
	 * @return 账号
	 */
	public String getUserNumber(){
		return this.userNumber;
	}

	/**
	 * 设置 密码
	 * @param UserPwd 密码
	 */
	public void setUserPwd(String userPwd){
		this.userPwd = userPwd;
	}

	/**
	 * 获取 密码
	 * @return 密码
	 */
	public String getUserPwd(){
		return this.userPwd;
	}

	/**
	 * 设置 头像路径
	 * @param UserHeadPortrait 头像路径
	 */
	public void setUserHeadPortrait(String userHeadPortrait){
		this.userHeadPortrait = userHeadPortrait;
	}

	/**
	 * 获取 头像路径
	 * @return 头像路径
	 */
	public String getUserHeadPortrait(){
		return this.userHeadPortrait;
	}

	/**
	 * 设置 
	 * @param UserDelFlg 
	 */
	public void setUserDelFlg(String userDelFlg){
		this.userDelFlg = userDelFlg;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getUserDelFlg(){
		return this.userDelFlg;
	}

	/**
	 * 设置 创建时间
	 * @param UserCreateTime 创建时间
	 */
	public void setUserCreateTime(Date userCreateTime){
		this.userCreateTime = userCreateTime;
	}

	/**
	 * 获取 创建时间
	 * @return 创建时间
	 */
	public Date getUserCreateTime(){
		return this.userCreateTime;
	}

	/**
	 * 设置 创建人
	 * @param UserCreateUserId 创建人
	 */
	public void setUserCreateUserId(String userCreateUserId){
		this.userCreateUserId = userCreateUserId;
	}

	/**
	 * 获取 创建人
	 * @return 创建人
	 */
	public String getUserCreateUserId(){
		return this.userCreateUserId;
	}

	/**
	 * 设置 修改时间
	 * @param UserModifyTime 修改时间
	 */
	public void setUserModifyTime(Date userModifyTime){
		this.userModifyTime = userModifyTime;
	}

	/**
	 * 获取 修改时间
	 * @return 修改时间
	 */
	public Date getUserModifyTime(){
		return this.userModifyTime;
	}

	/**
	 * 设置 修改人
	 * @param UserModifyUserId 修改人
	 */
	public void setUserModifyUserId(String userModifyUserId){
		this.userModifyUserId = userModifyUserId;
	}

	/**
	 * 获取 修改人
	 * @return 修改人
	 */
	public String getUserModifyUserId(){
		return this.userModifyUserId;
	}


}
