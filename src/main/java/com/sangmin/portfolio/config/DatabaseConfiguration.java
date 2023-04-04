package com.sangmin.portfolio.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

//이거슨 환경 설정을 하기위한 파일이에요
@Configuration

//application.properties라는 파일에 key : value로 정의한 값 가져다 쓸꺼에요
@PropertySource("classpath:/application.properties")

//com.example.demo.dao 는 DB를 다룰 Mapper 입니다

//트랜잭션 관리를 활성화 할게요
@EnableTransactionManagement
public class DatabaseConfiguration {

//	@Autowired 
//	private ApplicationContext applicationContext;
//
//	//히카리라는 DB 커넥션 풀 라이브러리를 사용해요
//	@Bean
//	@ConfigurationProperties(prefix = "spring.datasource.hikari")
//	public HikariConfig hikariConfig() {
//		return new HikariConfig();
//	}
//
//	//Database 기본 설정은 이제 히카리가 관리합니다
//	@Bean
//	public DataSource dataSorce() throws Exception {
//		DataSource dataSource = new HikariDataSource(hikariConfig());
//		return dataSource;
//	}
//
//	@Bean
//	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//		return new SqlSessionTemplate(sqlSessionFactory);
//	}
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public HikariDataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

	
//	@Bean
//	@ConfigurationProperties(prefix = "mybatis.configuration")
//	public org.apache.ibatis.session.Configuration mybatisConfig() {
//		return new org.apache.ibatis.session.Configuration();
//	}
}