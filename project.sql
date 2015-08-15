/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 50617
Source Host           : 127.0.0.1:3306
Source Database       : project

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-08-08 11:22:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cn_activity
-- ----------------------------
DROP TABLE IF EXISTS `cn_activity`;
CREATE TABLE `cn_activity` (
  `activity_id` varchar(50) NOT NULL,
  `activity_title` varchar(50) NOT NULL,
  `activity_body` text NOT NULL,
  `activity_time` timestamp NOT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_activity
-- ----------------------------
INSERT INTO `cn_activity` VALUES ('8c335e74-3aaf-4b3a-8cd2-63109a9c2b83', '降价啦', '大促销 不敢想象的低价', '2015-08-06 20:47:14');
INSERT INTO `cn_activity` VALUES ('be5b0564-eac7-4921-8ecb-4a2660f503a6', '好礼大放送', '买100包邮  特价商品买一送一', '2015-08-06 20:46:32');

-- ----------------------------
-- Table structure for cn_address
-- ----------------------------
DROP TABLE IF EXISTS `cn_address`;
CREATE TABLE `cn_address` (
  `address_id` varchar(36) NOT NULL,
  `user_id` varchar(36) NOT NULL,
  `address_name` varchar(100) NOT NULL,
  `address_person` varchar(20) NOT NULL,
  `address_phone` varchar(18) NOT NULL,
  PRIMARY KEY (`address_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `cn_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `cn_user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_address
-- ----------------------------

-- ----------------------------
-- Table structure for cn_admin
-- ----------------------------
DROP TABLE IF EXISTS `cn_admin`;
CREATE TABLE `cn_admin` (
  `admin_id` varchar(36) NOT NULL,
  `admin_name` varchar(18) NOT NULL,
  `admin_password` varchar(18) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_admin
-- ----------------------------
INSERT INTO `cn_admin` VALUES ('neuadmin', 'neuadmin', 'neuadmin');

-- ----------------------------
-- Table structure for cn_goods
-- ----------------------------
DROP TABLE IF EXISTS `cn_goods`;
CREATE TABLE `cn_goods` (
  `goods_id` varchar(36) NOT NULL,
  `type_id` varchar(36) NOT NULL,
  `goods_name` varchar(100) NOT NULL,
  `goods_pic` varchar(100) NOT NULL,
  `goods_price` double NOT NULL,
  `goods_creatime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `cn_goods_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `cn_type` (`type_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_goods
-- ----------------------------
INSERT INTO `cn_goods` VALUES ('127ebff0-899f-4d1d-8384-46d6ed6a9df6', 'cf431e6c-9e11-4e91-ba90-48339bfaee2b', '泡椒竹笋', '/images/泡椒竹笋.310x310.jpg', '3', '2015-08-07 22:12:33');
INSERT INTO `cn_goods` VALUES ('24616d8b-9b73-4f74-9a76-bb324ff8d506', '4805625c-a896-4457-a0b3-cbfdd41046ad', '一号食客话梅肉', '/images/一号食客话梅肉.310x310.jpg', '12', '2015-08-07 22:11:35');
INSERT INTO `cn_goods` VALUES ('2bd8d2bd-6c50-4a38-ac06-847cded5e968', 'cf431e6c-9e11-4e91-ba90-48339bfaee2b', '卤蛋', '/images/卤蛋.310x310.jpg', '1', '2015-08-07 22:11:00');
INSERT INTO `cn_goods` VALUES ('5a9a8741-8724-48e4-9812-aeb8aedbb430', 'aee59344-3b37-4225-ae23-d558db273f3e', '激活', '/images/激活.310x310.jpg', '4', '2015-08-07 22:20:46');
INSERT INTO `cn_goods` VALUES ('6267b568-57b6-43df-9888-de649172ced7', 'aee59344-3b37-4225-ae23-d558db273f3e', '水果绿茶', '/images/水果绿茶.310x310.jpg', '4', '2015-08-07 22:21:02');
INSERT INTO `cn_goods` VALUES ('823bba25-03c3-409d-8370-c13e0efeda88', '66d5e395-5e30-4389-b4c3-4a9f31f35319', '娃哈哈椰子奶', '/images/娃哈哈椰子奶.310x310.jpg', '3', '2015-08-07 22:19:32');
INSERT INTO `cn_goods` VALUES ('8f584dfc-94c0-42ef-84c7-cfb807f99bb1', 'aee59344-3b37-4225-ae23-d558db273f3e', '冰红茶', '/images/冰红茶.310x310.jpg', '4', '2015-08-07 22:20:06');
INSERT INTO `cn_goods` VALUES ('a8926853-4ef7-4ebd-9df4-347fd74c15e5', 'cf431e6c-9e11-4e91-ba90-48339bfaee2b', '风干牛肉', '/images/风干牛肉.310x310.jpg', '6', '2015-08-07 22:10:32');
INSERT INTO `cn_goods` VALUES ('b0c111bd-f110-4441-9390-71617e0ad697', 'aee59344-3b37-4225-ae23-d558db273f3e', '百事可乐', '/images/百事可乐.310x310.jpg', '3', '2015-08-07 22:20:29');
INSERT INTO `cn_goods` VALUES ('d8063bdf-1b7d-438d-8d15-9c0f90511b56', 'cf431e6c-9e11-4e91-ba90-48339bfaee2b', '香辣凤爪', '/images/香辣凤爪.310x310.jpg', '3', '2015-08-07 22:12:18');
INSERT INTO `cn_goods` VALUES ('e3f4e5bf-fbfd-485d-9b2f-2172a9e5a8b6', 'aee59344-3b37-4225-ae23-d558db273f3e', '娃哈哈矿泉水', '/images/娃哈哈矿泉水.310x310.jpg', '1.5', '2015-08-07 22:21:31');
INSERT INTO `cn_goods` VALUES ('efb04cc9-3db6-40f9-8582-867690d789c1', '4805625c-a896-4457-a0b3-cbfdd41046ad', 'ssadas', '/images/ssadas.310x310.jpg', '12', '2015-08-08 10:08:21');
INSERT INTO `cn_goods` VALUES ('f19101af-88ef-470c-a767-0374eec497dc', '66d5e395-5e30-4389-b4c3-4a9f31f35319', '核桃奶', '/images/核桃奶.310x310.jpg', '32', '2015-08-07 22:19:01');
INSERT INTO `cn_goods` VALUES ('f7398402-1108-49c1-af58-211a8c3c2cee', '66d5e395-5e30-4389-b4c3-4a9f31f35319', '爽歪歪饮品', '/images/爽歪歪饮品.310x310.jpg', '3', '2015-08-07 22:19:48');

-- ----------------------------
-- Table structure for cn_order
-- ----------------------------
DROP TABLE IF EXISTS `cn_order`;
CREATE TABLE `cn_order` (
  `order_id` varchar(36) NOT NULL,
  `address` varchar(50) NOT NULL,
  `order_status` int(2) NOT NULL,
  `pay_way` varchar(36) NOT NULL,
  `order_time` timestamp NOT NULL,
  `receive_time` timestamp NOT NULL,
  `address_person` varchar(36) NOT NULL,
  `address_phone` varchar(36) NOT NULL,
  `order_price` double NOT NULL,
  `order_mark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_order
-- ----------------------------

-- ----------------------------
-- Table structure for cn_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `cn_order_goods`;
CREATE TABLE `cn_order_goods` (
  `order_goods_id` varchar(36) NOT NULL,
  `order_id` varchar(36) NOT NULL,
  `goods_name` varchar(36) NOT NULL,
  `good_price` double NOT NULL,
  `order_goods_num` int(10) NOT NULL,
  PRIMARY KEY (`order_goods_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `cn_order_goods_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `cn_order` (`order_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_order_goods
-- ----------------------------

-- ----------------------------
-- Table structure for cn_pay_way
-- ----------------------------
DROP TABLE IF EXISTS `cn_pay_way`;
CREATE TABLE `cn_pay_way` (
  `pay_way_id` varchar(36) NOT NULL,
  `pay_way_name` varchar(20) NOT NULL,
  PRIMARY KEY (`pay_way_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_pay_way
-- ----------------------------
INSERT INTO `cn_pay_way` VALUES ('neu6001', '货到付款');

-- ----------------------------
-- Table structure for cn_problem
-- ----------------------------
DROP TABLE IF EXISTS `cn_problem`;
CREATE TABLE `cn_problem` (
  `problem_id` varchar(50) NOT NULL,
  `problem_title` varchar(50) NOT NULL,
  `problem_body` text NOT NULL,
  `problem_time` timestamp NOT NULL,
  PRIMARY KEY (`problem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_problem
-- ----------------------------
INSERT INTO `cn_problem` VALUES ('08076058-b62a-471f-8fdb-be85f08bf755', '发票问题', '如需发票 请备注说明', '2015-08-06 20:47:53');
INSERT INTO `cn_problem` VALUES ('b9f97664-116f-4c0f-a581-2c1ae5ba4491', '运费问题', '满100包邮', '2015-08-06 20:47:31');

-- ----------------------------
-- Table structure for cn_type
-- ----------------------------
DROP TABLE IF EXISTS `cn_type`;
CREATE TABLE `cn_type` (
  `type_id` varchar(36) NOT NULL,
  `type_name` varchar(20) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_type
-- ----------------------------
INSERT INTO `cn_type` VALUES ('4805625c-a896-4457-a0b3-cbfdd41046ad', '果干蜜饯');
INSERT INTO `cn_type` VALUES ('66d5e395-5e30-4389-b4c3-4a9f31f35319', '牛奶饮品');
INSERT INTO `cn_type` VALUES ('aee59344-3b37-4225-ae23-d558db273f3e', '动感酷饮');
INSERT INTO `cn_type` VALUES ('cf431e6c-9e11-4e91-ba90-48339bfaee2b', '零食饼干');

-- ----------------------------
-- Table structure for cn_user
-- ----------------------------
DROP TABLE IF EXISTS `cn_user`;
CREATE TABLE `cn_user` (
  `user_id` varchar(36) NOT NULL,
  `user_name` varchar(18) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_email` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_user
-- ----------------------------
INSERT INTO `cn_user` VALUES ('5e520af7-0734-4dc2-a1ea-199349065d18', 'caocao', '6fa9dbb0ae67c2459198e7d5ad3c7c1a', '1352149308@qq.com');
INSERT INTO `cn_user` VALUES ('d7e4f9ca-5f3b-4eca-9f2d-91a4ac5d19ea', 'liuxi', '6fa9dbb0ae67c2459198e7d5ad3c7c1a', '1352149308@qq.com');

-- ----------------------------
-- Table structure for order_user
-- ----------------------------
DROP TABLE IF EXISTS `order_user`;
CREATE TABLE `order_user` (
  `order_user_id` varchar(36) NOT NULL,
  `order_id` varchar(36) DEFAULT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`order_user_id`),
  KEY `order_id` (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `order_user_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `cn_order` (`order_id`) ON DELETE CASCADE,
  CONSTRAINT `order_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `cn_user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_user
-- ----------------------------

-- ----------------------------
-- Table structure for web_info
-- ----------------------------
DROP TABLE IF EXISTS `web_info`;
CREATE TABLE `web_info` (
  `web_id` varchar(50) NOT NULL,
  `web_title` varchar(50) NOT NULL,
  `web_words` varchar(50) NOT NULL,
  `web_nav1` varchar(10) NOT NULL,
  `web_nav2` varchar(10) NOT NULL,
  `web_nav3` varchar(10) NOT NULL,
  `web_nav4` varchar(10) NOT NULL,
  `web_footer` varchar(50) NOT NULL,
  `web_maker` varchar(50) NOT NULL,
  PRIMARY KEY (`web_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of web_info
-- ----------------------------
INSERT INTO `web_info` VALUES ('neuaaaaaaa', '东北大学生活超市', '欢迎光临本系统。', '首页', '优惠活动', '我的订单', '帮助中心', '订餐系统 ICP备', '程序由超级外卖提供');
INSERT INTO `web_info` VALUES ('neubbbbbb', '东北大学生活超市', '您好，欢迎光临本系统。', '前台首页', '优惠活动', '我的订单', '帮助中心', '© 2014 超级外卖微信订餐系统 ICP备19042558号', '程序由超级外卖提供');
INSERT INTO `web_info` VALUES ('neubbbbbbb', '东北大学生活超市', '您好，欢迎光临本系统。', '首 页', '优惠活动', '我的订单', '帮助中心', '© 2014 超级外卖微信订餐系统 ICP备19042558号', '程序由超级外卖提供');
INSERT INTO `web_info` VALUES ('neucccccc', '东北大学生活超市', '您好，欢迎光临本系统。', '首  页', '优惠活动', '我的订单', '帮助中心', '© 2014 超级外卖微信订餐系统 ICP备19042558号', '程序由超级外卖提供');

-- ----------------------------
-- Table structure for web_info_single
-- ----------------------------
DROP TABLE IF EXISTS `web_info_single`;
CREATE TABLE `web_info_single` (
  `web_id` varchar(50) NOT NULL,
  `web_title` varchar(50) NOT NULL,
  `web_words` varchar(50) NOT NULL,
  `web_nav1` varchar(10) NOT NULL,
  `web_nav2` varchar(10) NOT NULL,
  `web_nav3` varchar(10) NOT NULL,
  `web_nav4` varchar(10) NOT NULL,
  `web_footer` varchar(50) NOT NULL,
  `web_maker` varchar(50) NOT NULL,
  PRIMARY KEY (`web_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of web_info_single
-- ----------------------------
INSERT INTO `web_info_single` VALUES ('neuaaaaaaa', '东北大学生活超市', '欢迎光临本系统。', '首页', '优惠活动', '我的订单', '帮助中心', '订餐系统 ICP备', '程序由超级外卖提供');
