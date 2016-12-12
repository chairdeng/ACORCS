/*
Navicat MySQL Data Transfer

Source Server         : docker-mysql
Source Server Version : 50716
Source Host           : 192.168.99.100:3306
Source Database       : acorcs

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2016-12-11 14:38:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for storm_center
-- ----------------------------
DROP TABLE IF EXISTS `wni_storm_center`;
CREATE TABLE `wni_storm_center` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `notice_id` bigint(20) DEFAULT NULL,
  `header` varchar(16) DEFAULT NULL,
  `storm_name` varchar(32) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  `geographic` point DEFAULT NULL,
  `original` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wni_cloud
-- ----------------------------
DROP TABLE IF EXISTS `wni_cloud`;
CREATE TABLE `wni_cloud` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `notice_id` bigint(20) DEFAULT NULL,
  `header` varchar(16) DEFAULT NULL,
  `cloud_distribution` varchar(32) DEFAULT NULL,
  `cloud_type` varchar(32) DEFAULT NULL,
  `altitudes` int(20) DEFAULT NULL,
  `airframe_icing` varchar(64) DEFAULT NULL,
  `extended_degree` int(11) DEFAULT NULL,
  `geographic` polygon DEFAULT NULL,
  `original` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wni_jet_stream
-- ----------------------------
DROP TABLE IF EXISTS `wni_jet_stream`;
CREATE TABLE `wni_jet_stream` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `notice_id` bigint(20) DEFAULT NULL,
  `header` varchar(16) DEFAULT NULL,
  `geographic` linestring DEFAULT NULL,
  `original` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wni_notice
-- ----------------------------
DROP TABLE IF EXISTS `wni_notice`;
CREATE TABLE `wni_notice` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(16) DEFAULT NULL,
  `elem` varchar(32) DEFAULT NULL,
  `dataname` varchar(16) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `basetime` datetime DEFAULT NULL,
  `validtime` datetime DEFAULT NULL,
  `json` longtext,
  PRIMARY KEY (`id`),
  KEY `time_index` (`updated`,`basetime`,`validtime`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wni_tropopause
-- ----------------------------
DROP TABLE IF EXISTS `wni_tropopause`;
CREATE TABLE `wni_tropopause` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `notice_id` bigint(20) DEFAULT NULL,
  `header` varchar(16) DEFAULT NULL,
  `significance` varchar(64) DEFAULT NULL,
  `geographic` multipoint DEFAULT NULL,
  `original` varchar(4096) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wni_turbulence
-- ----------------------------
DROP TABLE IF EXISTS `wni_turbulence`;
CREATE TABLE `wni_turbulence` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `notice_id` bigint(20) DEFAULT NULL,
  `header` varchar(16) DEFAULT NULL,
  `extended_degree` int(11) DEFAULT NULL,
  `altitudes` varchar(128) DEFAULT NULL,
  `geographic` polygon DEFAULT NULL,
  `original` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wni_volcano
-- ----------------------------
DROP TABLE IF EXISTS `wni_volcano`;
CREATE TABLE `wni_volcano` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `notice_id` bigint(20) DEFAULT NULL,
  `header` varchar(16) DEFAULT NULL,
  `feature_name` varchar(32) DEFAULT NULL,
  `time_significance` varchar(64) DEFAULT NULL,
  `special_clouds` varchar(32) DEFAULT NULL,
  `geographic` point DEFAULT NULL,
  `original` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
