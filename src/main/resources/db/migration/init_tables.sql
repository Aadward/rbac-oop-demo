CREATE TABLE IF NOT EXISTS `role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `company_id` varchar(100) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uk_company_id_role_name`(`company_id`, `name`)
);

CREATE TABLE IF NOT EXISTS `role_auth` (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `role_id` bigint(20) UNSIGNED NOT NULL,
    `auth_type` varchar(100) NOT NULL,
    `resource_type` varchar(100) NOT NULL,
    `resource_key` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `member` (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `company_id` varchar(100) NOT NULL,
    `member_type` varchar(100) NOT NULL,
    `org_member_id` bigint(20) UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uk_id_type_org_id` (`company_id`, `member_type`, `org_member_id`)
);

CREATE TABLE IF NOT EXISTS `member_role` (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_id` bigint(20) UNSIGNED NOT NULL,
    `role_id` bigint(20) UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
);