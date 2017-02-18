/*
Navicat MySQL Data Transfer

Source Server         : docker-mysql
Source Server Version : 50717
Source Host           : 192.168.99.100:3306
Source Database       : acorcs

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-02-18 19:03:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for custom_restricted_area
-- ----------------------------
DROP TABLE IF EXISTS `custom_restricted_area`;
CREATE TABLE `custom_restricted_area` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(32) DEFAULT NULL,
  `level` smallint(6) DEFAULT NULL,
  `basetime` timestamp NULL DEFAULT NULL,
  `validtime` timestamp NULL DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
