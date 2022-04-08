CREATE DATABASE IF NOT EXISTS taulia_setting CHARACTER set = utf8mb4 COLLATE utf8mb4_bin;
CREATE DATABASE IF NOT EXISTS `taulia_auth` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
CREATE DATABASE IF NOT EXISTS taulia_company CHARACTER set = utf8mb4 COLLATE utf8mb4_bin;

USE backend;

CREATE TABLE tab_supplier (
                           id BINARY(16) PRIMARY KEY,
                           name text,
                           version long,
                           custom_attributes text
);