package org.taurus.entity.sys;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 
 * 权限-请求
 * 
 **/

@TableName("t_auth_url")
public class TAuthUrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.UUID)
	private String authUrlId;

	/**
	 * 权限id
	 */
	private String authId;

	/**
	 * 请求id
	 */
	private String urlId;

	/**
	 * 
	 */
	private String authUrlDelFlg;

	/**
	 * 创建时间
	 */
	private Date authUrlCreateTime;

	/**
	 * 创建人
	 */
	private String authUrlCreateUserId;

	/**
	 * 修改时间
	 */
	private Date authUrlModifyTime;

	/**
	 * 修改人
	 */
	private String authUrlModifyUserId;


	/**
	 * 设置 
	 * @param AuthUrlId 
	 */
	public void setAuthUrlId(String authUrlId){
		this.authUrlId = authUrlId;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getAuthUrlId(){
		return this.authUrlId;
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
	 * 设置 请求id
	 * @param UrlId 请求id
	 */
	public void setUrlId(String urlId){
		this.urlId = urlId;
	}

	/**
	 * 获取 请求id
	 * @return 请求id
	 */
	public String getUrlId(){
		return this.urlId;
	}

	/**
	 * 设置 
	 * @param AuthUrlDelFlg 
	 */
	public void setAuthUrlDelFlg(String authUrlDelFlg){
		this.authUrlDelFlg = authUrlDelFlg;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getAuthUrlDelFlg(){
		return this.authUrlDelFlg;
	}

	/**
	 * 设置 创建时间
	 * @param AuthUrlCreateTime 创建时间
	 */
	public void setAuthUrlCreateTime(Date authUrlCreateTime){
		this.authUrlCreateTime = authUrlCreateTime;
	}

	/**
	 * 获取 创建时间
	 * @return 创建时间
	 */
	public Date getAuthUrlCreateTime(){
		return this.authUrlCreateTime;
	}

	/**
	 * 设置 创建人
	 * @param AuthUrlCreateUserId 创建人
	 */
	public void setAuthUrlCreateUserId(String authUrlCreateUserId){
		this.authUrlCreateUserId = authUrlCreateUserId;
	}

	/**
	 * 获取 创建人
	 * @return 创建人
	 */
	public String getAuthUrlCreateUserId(){
		return this.authUrlCreateUserId;
	}

	/**
	 * 设置 修改时间
	 * @param AuthUrlModifyTime 修改时间
	 */
	public void setAuthUrlModifyTime(Date authUrlModifyTime){
		this.authUrlModifyTime = authUrlModifyTime;
	}

	/**
	 * 获取 修改时间
	 * @return 修改时间
	 */
	public Date getAuthUrlModifyTime(){
		return this.authUrlModifyTime;
	}

	/**
	 * 设置 修改人
	 * @param AuthUrlModifyUserId 修改人
	 */
	public void setAuthUrlModifyUserId(String authUrlModifyUserId){
		this.authUrlModifyUserId = authUrlModifyUserId;
	}

	/**
	 * 获取 修改人
	 * @return 修改人
	 */
	public String getAuthUrlModifyUserId(){
		return this.authUrlModifyUserId;
	}


}
