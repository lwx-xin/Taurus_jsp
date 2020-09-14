package org.taurus.controller.err;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.taurus.config.util.SessionUtil;
import org.taurus.service.sys.TSLogService;

@ControllerAdvice
public class ExceptionController {
	
	@Resource
	private TSLogService logService;
	
	@ExceptionHandler(value = Exception.class)
    public String errorHandler(Exception ex,HttpSession session) {
    	System.err.println(ex.getMessage());
//		ex.printStackTrace();
		
    	String userId = SessionUtil.getUserId(session);
    	try {
			logService.saveLogInfo(ex, userId);
		} catch (Exception e) {
			System.err.println(e);
		}
		
        return "err/500";
    }

}
