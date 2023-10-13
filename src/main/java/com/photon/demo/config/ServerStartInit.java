package com.photon.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Description:
 *
 * @author quantao
 * @Date: 2023/5/4
 */
@Component
public class ServerStartInit implements InitializingBean {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("second")
	private DataSource dataSource;

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void afterPropertiesSet() throws Exception {
		Resource resource = applicationContext.getResource("classpath:sql/InitTable.sql");
		InputStream inputStream = resource.getInputStream();

		try (Scanner scanner = new Scanner(inputStream, "UTF-8")) {
			scanner.useDelimiter(";"); // 使用分号作为语句分隔符
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();

			while (scanner.hasNext()) {
				String sqlStatement = scanner.next().trim();
				if (!sqlStatement.isEmpty()) {
					// 执行每个 SQL 语句
					try {
						statement.execute(sqlStatement);
						logger.info("执行 SQL 语句: {}", sqlStatement);
					} catch (Exception e) {
						logger.error("执行 SQL 语句失败: {}", sqlStatement, e);
					}
				}
			}

			statement.close();
			connection.close();
		} catch (Exception e) {
			throw new Exception(String.format("执行建表 SQL 失败[%s]", e.getLocalizedMessage()));
		}
	}
}


