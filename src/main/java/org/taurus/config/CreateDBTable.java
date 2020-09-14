package org.taurus.config;

import javax.sql.DataSource;

import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * 创建表(不存在就创建)
 * @author 祈
 *
 */
//@Order(2)//value越小越先加载
//@Configuration
public class CreateDBTable {
	
//	@Value("classpath:dataSourceInit/create_table.sql")
	private Resource sqlResource;
	
//    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        // 设置数据源
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(sqlResource);
        populator.setSeparator("$$");
        return populator;
    }

}
