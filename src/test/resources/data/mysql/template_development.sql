/*
Navicat MySQL Data Transfer

Source Server         : Local_MySQL
Source Server Version : 50522
Source Host           : localhost:3306
Source Database       : template_development

Target Server Type    : MYSQL
Target Server Version : 50522
File Encoding         : 65001

Date: 2014-05-05 22:38:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_department
-- ----------------------------
DROP TABLE IF EXISTS `tb_department`;
CREATE TABLE `tb_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `higher_department_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_id9eubd0ikv9whim66pe7380b` (`higher_department_id`),
  CONSTRAINT `FK_id9eubd0ikv9whim66pe7380b` FOREIGN KEY (`higher_department_id`) REFERENCES `tb_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_department
-- ----------------------------
INSERT INTO `tb_department` VALUES ('1', null, null, '公司总部', '羽科技有限公司', null);
INSERT INTO `tb_department` VALUES ('2', null, null, '搞软件开发的', '软件中心', '1');
INSERT INTO `tb_department` VALUES ('3', null, null, '负责发钱的！', '财务中心', '1');
INSERT INTO `tb_department` VALUES ('4', null, null, 'JAVA开发', 'JAVA开发一组', '2');
INSERT INTO `tb_department` VALUES ('5', null, null, '测试', '测试组', '2');
INSERT INTO `tb_department` VALUES ('6', null, null, 'JAVA开发', 'JAVA开发二组', '2');

-- ----------------------------
-- Table structure for tb_project
-- ----------------------------
DROP TABLE IF EXISTS `tb_project`;
CREATE TABLE `tb_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `finish_task` int(11) DEFAULT NULL,
  `is_place_on_file` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `percent` double DEFAULT NULL,
  `total_task` int(11) DEFAULT NULL,
  `director` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_muw3a8w31hw00a06fhp91xy52` (`director`),
  CONSTRAINT `FK_muw3a8w31hw00a06fhp91xy52` FOREIGN KEY (`director`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_project
-- ----------------------------
INSERT INTO `tb_project` VALUES ('1', '2014-04-27 16:29:10', '2014-04-27 16:29:10', '自己搭建一个符合自己使用习惯的项目管理系统。\r\n1、第一步先完成项目管理\r\n2、第二步是项目成员管理\r\n3、第三步是任务列表', '0', '0', '项目管理系统', '0', '0', '1');
INSERT INTO `tb_project` VALUES ('2', '2014-04-27 16:33:41', '2014-04-27 16:33:41', '学习使用\r\n目标是：请假流程', '0', '0', '工作流引擎', '0', '0', '1');
INSERT INTO `tb_project` VALUES ('3', '2014-04-27 16:36:12', '2014-04-27 16:36:12', '学习Spring的相关内容', '0', '0', '加强学习-Spring的正式使用', '0', '0', '1');
INSERT INTO `tb_project` VALUES ('4', '2014-04-27 16:36:39', '2014-04-27 16:36:39', '培养自己的工作习惯', '0', '0', '习惯养成', '0', '0', '1');

-- ----------------------------
-- Table structure for tb_task
-- ----------------------------
DROP TABLE IF EXISTS `tb_task`;
CREATE TABLE `tb_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_elk6mlelf430j9ip1dka77avv` (`user_id`),
  CONSTRAINT `FK_elk6mlelf430j9ip1dka77avv` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_task
-- ----------------------------
INSERT INTO `tb_task` VALUES ('1', 'http://www.playframework.org/', 'Study PlayFramework 2.0', '2', null, null);
INSERT INTO `tb_task` VALUES ('2', 'http://www.grails.org/', 'Study Grails 2.0', '2', null, null);
INSERT INTO `tb_task` VALUES ('3', 'http://www.springfuse.com/', 'Try SpringFuse', '2', null, null);
INSERT INTO `tb_task` VALUES ('4', 'http://www.springsource.org/spring-roo', 'Try Spring Roo', '2', null, null);
INSERT INTO `tb_task` VALUES ('5', 'As soon as posibble.', 'Release SpringSide 4.0', '2', null, null);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin@163.com', 'admin', '梁仲荣', '691b14d79bf0fa2215f155235df5e670b64394cc', '2012-06-04 01:00:00', 'admin', '7efbd59d9741d34f', '2014-04-27 16:45:46', '2014-04-27 16:45:49', 'libra.jpg');
INSERT INTO `tb_user` VALUES ('2', 'user@163.com', 'user', '成员一', '2488aa0c31c624687bd9928e0a5d29e7d1ed520b', '2012-06-04 02:00:00', 'user', '6d65d24122c30500', '2014-04-27 16:45:52', '2014-04-27 16:45:54', 'avatar1.jpg');
