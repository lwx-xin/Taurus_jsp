package org.taurus;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.taurus.config.TaurusConfig;
import org.taurus.service.sys.TSFolderService;

@SpringBootTest
class TaurusApplicationTests {
	
	@Resource
	TSFolderService folderService;
	
	@Resource
	TaurusConfig config;
	
	@Test
	void contextLoads() throws Exception {
		
	}


}
