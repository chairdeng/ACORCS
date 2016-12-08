/*
Navicat MySQL Data Transfer

Source Server         : 本机Mysql
Source Server Version : 50632
Source Host           : localhost:3306
Source Database       : acorcs

Target Server Type    : MYSQL
Target Server Version : 50632
File Encoding         : 65001

Date: 2016-12-08 16:10:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wni_cloud_jube99
-- ----------------------------
DROP TABLE IF EXISTS `wni_cloud_jube99`;
CREATE TABLE `wni_cloud_jube99` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `notice_id` bigint(20) DEFAULT NULL,
  `cloud_distribution` varchar(32) DEFAULT NULL,
  `cloud_type` varchar(32) DEFAULT NULL,
  `altitudes` int(20) DEFAULT NULL,
  `geographic` polygon DEFAULT NULL,
  `wkt` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wni_cloud_june00
-- ----------------------------
DROP TABLE IF EXISTS `wni_cloud_june00`;
CREATE TABLE `wni_cloud_june00` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `notice_id` bigint(20) DEFAULT NULL,
  `cloud_distribution` varchar(32) DEFAULT NULL,
  `cloud_type` varchar(32) DEFAULT NULL,
  `altitudes` int(20) DEFAULT NULL,
  `airframe_icing` varchar(64) DEFAULT NULL,
  `extended_degree` int(11) DEFAULT NULL,
  `geographic` polygon DEFAULT NULL,
  `wkt` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wni_jet_stream_jute00
-- ----------------------------
DROP TABLE IF EXISTS `wni_jet_stream_jute00`;
CREATE TABLE `wni_jet_stream_jute00` (
  `id` bigint(20) NOT NULL,
  `notice_id` bigint(20) DEFAULT NULL,
  `geographic` linestring DEFAULT NULL,
  `wkt` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wni_jet_stream_juwe96
-- ----------------------------
DROP TABLE IF EXISTS `wni_jet_stream_juwe96`;
CREATE TABLE `wni_jet_stream_juwe96` (
  `id` bigint(20) NOT NULL,
  `notice_id` bigint(20) DEFAULT NULL,
  `geographic` linestring DEFAULT NULL,
  `wkt` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wni_notice
-- ----------------------------
DROP TABLE IF EXISTS `wni_notice`;
CREATE TABLE `wni_notice` (
  `id` bigint(20) NOT NULL,
  `type` varchar(16) DEFAULT NULL,
  `elem` varchar(32) DEFAULT NULL,
  `dataname` varchar(16) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `basetime` datetime DEFAULT NULL,
  `validtime` datetime DEFAULT NULL,
  `json` text,
  PRIMARY KEY (`id`),
  KEY `time_index` (`updated`,`basetime`,`validtime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wni_tropopause_juoe00
-- ----------------------------
DROP TABLE IF EXISTS `wni_tropopause_juoe00`;
CREATE TABLE `wni_tropopause_juoe00` (
  `id` bigint(20) NOT NULL,
  `notice_id` bigint(20) DEFAULT NULL,
  `significance` varchar(64) DEFAULT NULL,
  `geographic` multipoint DEFAULT NULL,
  `wkt` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wni_tropopause_jute97
-- ----------------------------
DROP TABLE IF EXISTS `wni_tropopause_jute97`;
CREATE TABLE `wni_tropopause_jute97` (
  `id` bigint(20) NOT NULL,
  `notice_id` bigint(20) DEFAULT NULL,
  `significance` varchar(64) DEFAULT NULL,
  `geographic` multipoint DEFAULT NULL,
  `wkt` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wni_turbulence_juce00
-- ----------------------------
DROP TABLE IF EXISTS `wni_turbulence_juce00`;
CREATE TABLE `wni_turbulence_juce00` (
  `id` bigint(20) NOT NULL,
  `notice_id` bigint(20) DEFAULT NULL,
  `extended_degree` int(11) DEFAULT NULL,
  `altitudes` varchar(128) DEFAULT NULL,
  `geographic` polygon DEFAULT NULL,
  `wkt` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wni_turbulence_jume00
-- ----------------------------
DROP TABLE IF EXISTS `wni_turbulence_jume00`;
CREATE TABLE `wni_turbulence_jume00` (
  `id` bigint(20) NOT NULL,
  `notice_id` bigint(20) DEFAULT NULL,
  `extended_degree` int(11) DEFAULT NULL,
  `altitudes` varchar(128) DEFAULT NULL,
  `geographic` polygon DEFAULT NULL,
  `wkt` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `notice_id` (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wni_volcano_juve00
-- ----------------------------
DROP TABLE IF EXISTS `wni_volcano_juve00`;
CREATE TABLE `wni_volcano_juve00` (
  `id` bigint(20) NOT NULL,
  `notice_id` bigint(20) DEFAULT NULL,
  `feature_name` varchar(32) DEFAULT NULL,
  `time_significance` varchar(64) DEFAULT NULL,
  `special_clouds` varchar(32) DEFAULT NULL,
  `geographic` point DEFAULT NULL,
  `wkt` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
