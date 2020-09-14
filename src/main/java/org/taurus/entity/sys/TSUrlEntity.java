package org.taurus.entity.sys;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 
 * 系统请求
 * 
 **/

@TableName("t_s_url")
public class TSUrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.UUID)
	private String urlId;

	/**
	 * 请求路径
	 */
	private String urlPath;

	/**
	 * 请求方法post,get
	 */
	private String urlMethod;

	/**
	 * 备注
	 */
	private String urlRemarks;

	/**
	 * 
	 */
	private String urlDelFlg;

	/**
	 * 创建时间
	 */
	private Date urlCreateTime;

	/**
	 * 创建人
	 */
	private String urlCreateUserId;

	/**
	 * 修改时间
	 */
	private Date urlModifyTime;

	/**
	 * 修改人
	 */
	private String urlModifyUserId;


	/**
	 * 设置 
	 * @param UrlId 
	 */
	public void setUrlId(String urlId){
		this.urlId = urlId;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getUrlId(){
		return this.urlId;
	}

	/**
	 * 设置 请求路径
	 * @param UrlPath 请求路径
	 */
	public void setUrlPath(String urlPath){
		this.urlPath = urlPath;
	}

	/**
	 * 获取 请求路径
	 * @return 请求路径
	 */
	public String getUrlPath(){
		return this.urlPath;
	}

	/**
	 * 设置 请求方法post,get
	 * @param UrlMethod 请求方法post,get
	 */
	public void setUrlMethod(String urlMethod){
		this.urlMethod = urlMethod;
	}

	/**
	 * 获取 请求方法post,get
	 * @return 请求方法post,get
	 */
	public String getUrlMethod(){
		return this.urlMethod;
	}

	/**
	 * 设置 备注
	 * @param UrlRemarks 备注
	 */
	public void setUrlRemarks(String urlRemarks){
		this.urlRemarks = urlRemarks;
	}

	/**
	 * 获取 备注
	 * @return 备注
	 */
	public String getUrlRemarks(){
		return this.urlRemarks;
	}

	/**
	 * 设置 
	 * @param UrlDelFlg 
	 */
	public void setUrlDelFlg(String urlDelFlg){
		this.urlDelFlg = urlDelFlg;
	}

	/**
	 * 获取 
	 * @return 
	 */
	public String getUrlDelFlg(){
		return this.urlDelFlg;
	}

	/**
	 * 设置 创建时间
	 * @param UrlCreateTime 创建时间
	 */
	public void setUrlCreateTime(Date urlCreateTime){
		this.urlCreateTime = urlCreateTime;
	}

	/**
	 * 获取 创建时间
	 * @return 创建时间
	 */
	public Date getUrlCreateTime(){
		return this.urlCreateTime;
	}

	/**
	 * 设置 创建人
	 * @param UrlCreateUserId 创建人
	 */
	public void setUrlCreateUserId(String urlCreateUserId){
		this.urlCreateUserId = urlCreateUserId;
	}

	/**
	 * 获取 创建人
	 * @return 创建人
	 */
	public String getUrlCreateUserId(){
		return this.urlCreateUserId;
	}

	/**
	 * 设置 修改时间
	 * @param UrlModifyTime 修改时间
	 */
	public void setUrlModifyTime(Date urlModifyTime){
		this.urlModifyTime = urlModifyTime;
	}

	/**
	 * 获取 修改时间
	 * @return 修改时间
	 */
	public Date getUrlModifyTime(){
		return this.urlModifyTime;
	}

	/**
	 * 设置 修改人
	 * @param UrlModifyUserId 修改人
	 */
	public void setUrlModifyUserId(String urlModifyUserId){
		this.urlModifyUserId = urlModifyUserId;
	}

	/**
	 * 获取 修改人
	 * @return 修改人
	 */
	public String getUrlModifyUserId(){
		return this.urlModifyUserId;
	}


}
