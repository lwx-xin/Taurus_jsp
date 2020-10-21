package org.taurus.config.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.taurus.config.util.SessionUtil;

/**
 * 监听session的创建，销毁
 * @author 祈
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		System.err.println("创建Session:"+session.getId());
		HttpSessionListener.super.sessionCreated(se);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.err.println("销毁Session:"+se.getSession().getId());
		SessionUtil.delSession(se.getSession());
		HttpSessionListener.super.sessionDestroyed(se);
	}

}
