package org.taurus.config.util.entity;

import java.util.Set;

public class JwtTokenEntity {
    private String userId;
    private Set<String> authLevel;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Set<String> getAuthLevel() {
		return authLevel;
	}
	public void setAuthLevel(Set<String> authLevel) {
		this.authLevel = authLevel;
	}
	public JwtTokenEntity(String userId, Set<String> authLevel) {
		super();
		this.userId = userId;
		this.authLevel = authLevel;
	}
    

}
