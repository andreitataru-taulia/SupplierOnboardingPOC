package com.taulia.supplier.onboarding;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

//TODO groovy interface does not support static methods
@Testcontainers
public interface MysqlContainerInitializer {

  DockerImageName myImage =
          DockerImageName.parse("arm64v8/mysql:oracle")
                         .asCompatibleSubstituteFor("mysql");

  @Container
  MySQLContainer<?> mySQLContainer =
          new MySQLContainer<>(myImage)
                  .withCommand("--character-set-server=utf8mb4", "--collation-server=utf8mb4_bin");

  @DynamicPropertySource
  static void registerPgProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", mySQLContainer::getUsername);
    registry.add("spring.datasource.password", mySQLContainer::getPassword);
  }
}
