package org.taurus.config.util;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.taurus.config.util.entity.JwtTokenEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil {
	
	/**
	 * 秘钥
	 */
	private static final String SECRET = "taurus";
	
	/**
	 * 加密方式
	 */
	private static final SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
    
    /**
     * 过期时间-单位分钟
     */
    private static final int calendarField = Calendar.HOUR;
    /**
     * 过期时间
     */
    private static final int calendarAmount = 24;
	
	private static Key getKeyInstance(String secret) {
		
		byte[] parseBase64Binary = DatatypeConverter.parseBase64Binary(secret);
		
		SecretKeySpec secretKeySpec = new SecretKeySpec(parseBase64Binary, algorithm.getJcaName());
		return secretKeySpec;
	}
	
	/**
	 * 创建Token
	 * @param claims 保存的参数
	 * @return Token字符串
	 */
	public static String createToken(JwtTokenEntity tokenEntity) {
		
		Key secretKey = getKeyInstance(SECRET);
		
		//令牌签发时间
        Date now = new Date();
        
        //令牌过期时间
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(now);
        nowCalendar.add(calendarField, calendarAmount);
        Date expiresDate = nowCalendar.getTime();
        
        //令牌签发者
        String issuer = "xin";
        
        //令牌id
        String id = StrUtil.getUUID();
        
		JwtBuilder jwtBuilder = Jwts.builder()
				
				//Header
				.setHeaderParam("typ", "JWT")//声明类型
				
				//Payload 主体部分(标准声明)
//				.setSubject("")//JWT所面向的用户
//				.setAudience("")//接收JWT的一方
//				.setNotBefore(null)//在xxx日期之间，该jwt都是可用的
				.setId(id)//设置令牌id
				.setIssuer(issuer)//设置JWT签发者
				.setIssuedAt(now)//设置JWT签发时间
				.setExpiration(expiresDate);//设置JWT过期时间
		
		//Payload 主体部分(公共声明，私有声明)
		Map<String, Object> payloadMap = JsonUtil.entityToMap(tokenEntity);
		if (payloadMap!=null) {
			payloadMap.forEach((k,v)->{
				jwtBuilder.claim(k, v);
			});
		}
		
		//Signature-签证信息 = 加密算法(Header + "." + Payload, 秘钥)
		jwtBuilder.signWith(algorithm, secretKey);
		
		return jwtBuilder.compact();
	}
	
	/**
	 * 验证Token
	 * @param token
	 * @return 主体部分(Payload)
	 */
	public static Claims verifyToken(String token) {
		
		Key secretKey = getKeyInstance(SECRET);
		Claims body = null;
		try {
			//解析Token
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			//获取Token的主体部分(Payload)
			body = claimsJws.getBody();
		} catch (Exception e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return body;
	}
	

	/**
	 * 解析并验证令牌
	 * 
	 * @param tokenJson  令牌
	 * @param userNumber 用户账号
	 * @return 异常信息
	 */
	public static String decryptToken(String tokenJson) {
		// 无验证令牌
		if (StrUtil.isEmpty(tokenJson)) {
			return "无验证令牌";
		}

		// 解密令牌
		Claims token = verifyToken(tokenJson);
		if (token==null) {
			return "令牌过期";
		}
		
		//验证过期时间
		Date expiration = token.getExpiration();
		if (expiration.getTime() < DateUtil.getNowDateLong()) {
			return "令牌过期";
		}
		
		return "";
	}

//	public static void main(String[] args) {
//		JwtTokenEntity tokenEntity = new JwtTokenEntity("userId", "authLevel");
//		
//		String token = createToken(tokenEntity);
//		System.err.println("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhNTE0NTM3YWU2MjE0NjQwYTMwYTc3ODliNTA2N2RmYSIsImlzcyI6InhpbiIsImlhdCI6MTU5NzM5NDA5MCwiZXhwIjoxNTk3Mzk0NjkwLCJVSUQiOiIxMjMiLCJpZCI6IjIyMiJ9.bErs5hKBm_LjRDQQE2EytzX_Qn4bVKwd8gwC37KXQmw");
//		String verifyToken = decryptToken(token);
//		System.err.println(verifyToken);
//	}

}
