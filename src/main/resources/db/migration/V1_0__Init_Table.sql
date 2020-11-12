CREATE TABLE `role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uk_role_name`(`name`)
);

CREATE TABLE `role_auth` (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `role_id` bigint(20) UNSIGNED NOT NULL,
    `auth_type` varchar(100) NOT NULL,
    `resource_type` varchar(100) NOT NULL,
    `resource_key` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
);


CREATE TABLE `member` (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_type` varchar(100) NOT NULL,
    `org_member_id` bigint(20) UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `member_role` (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_id` bigint(20) UNSIGNED NOT NULL,
    `role_id` bigint(20) UNSIGNED NOT NULL,
    PRIMARY KEY (`id`)
);