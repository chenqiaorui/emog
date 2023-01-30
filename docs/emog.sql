/*
 Navicat Premium Data Transfer

 Source Server         : mac-local
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : emog

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 31/01/2023 00:34:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for baby
-- ----------------------------
DROP TABLE IF EXISTS `baby`;
CREATE TABLE `baby` (
  `id` int NOT NULL,
  `name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'baby名称',
  `age` int DEFAULT NULL COMMENT 'baby岁数',
  `weight` double(3,0) DEFAULT NULL COMMENT '重量',
  `note` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `birthday` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生日',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of baby
-- ----------------------------
BEGIN;
INSERT INTO `baby` VALUES (1, '孙悟空', 1000, 200, '^_^这是一个200斤的孙悟空', '2022-12-12', '2023-01-19 15:30:32');
COMMIT;

-- ----------------------------
-- Table structure for baby_food
-- ----------------------------
DROP TABLE IF EXISTS `baby_food`;
CREATE TABLE `baby_food` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '食品名称',
  `sale` int DEFAULT NULL COMMENT '销量',
  `create_time` datetime DEFAULT NULL,
  `note` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for formula
-- ----------------------------
DROP TABLE IF EXISTS `formula`;
CREATE TABLE `formula` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '奶粉名称',
  `create_time` datetime DEFAULT NULL,
  `brand_id` int DEFAULT NULL COMMENT '奶粉品牌',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of formula
-- ----------------------------
BEGIN;
INSERT INTO `formula` VALUES (1, '惠氏有机奶粉', '2023-01-19 17:37:40', 1);
INSERT INTO `formula` VALUES (2, '惠氏甄选奶粉', '2023-01-19 17:38:07', 1);
COMMIT;

-- ----------------------------
-- Table structure for formula_brand
-- ----------------------------
DROP TABLE IF EXISTS `formula_brand`;
CREATE TABLE `formula_brand` (
  `id` int NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '奶粉品牌名称',
  `create_time` datetime DEFAULT NULL,
  `note` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='奶粉品牌表';

-- ----------------------------
-- Records of formula_brand
-- ----------------------------
BEGIN;
INSERT INTO `formula_brand` VALUES (1, '惠氏', '2023-01-19 17:40:08', NULL);
INSERT INTO `formula_brand` VALUES (2, '合生元', '2023-01-19 17:40:27', NULL);
COMMIT;

-- ----------------------------
-- Table structure for fund
-- ----------------------------
DROP TABLE IF EXISTS `fund`;
CREATE TABLE `fund` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '基金类型',
  `level` int DEFAULT NULL COMMENT '风险等级',
  `profit` int DEFAULT NULL COMMENT '收益',
  `describe` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '基金描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='基金表';

-- ----------------------------
-- Records of fund
-- ----------------------------
BEGIN;
INSERT INTO `fund` VALUES (1, '货币市场基金', 1, 1, NULL, NULL);
INSERT INTO `fund` VALUES (2, '债券型基金', 2, 2, NULL, NULL);
INSERT INTO `fund` VALUES (3, '混合型基金', 3, 3, NULL, NULL);
INSERT INTO `fund` VALUES (4, '股票型基金', 4, 4, '风险收益都高', '2023-01-25 00:26:33');
COMMIT;

-- ----------------------------
-- Table structure for fund_product
-- ----------------------------
DROP TABLE IF EXISTS `fund_product`;
CREATE TABLE `fund_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fund_id` int DEFAULT NULL COMMENT '基金id',
  `name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '基金产品名称',
  `describe` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产品描述',
  `target` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '收益指标',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='基金产品表';

-- ----------------------------
-- Records of fund_product
-- ----------------------------
BEGIN;
INSERT INTO `fund_product` VALUES (1, 1, '国库券', '极其低收益', '万份收益', '2023-01-25 01:15:44');
INSERT INTO `fund_product` VALUES (2, 1, '政府短期债券', NULL, NULL, '2023-01-25 01:16:57');
INSERT INTO `fund_product` VALUES (3, 2, '国债', '收益稳定', '债收益率', '2023-01-25 01:20:11');
INSERT INTO `fund_product` VALUES (4, 2, '金融债', '收益稳定', '债收益率', '2023-01-25 01:20:15');
INSERT INTO `fund_product` VALUES (5, 3, '股票债券', '基金经理操盘', NULL, NULL);
INSERT INTO `fund_product` VALUES (6, 4, '指数基金', '含跟踪系数低被动型基金', NULL, NULL);
INSERT INTO `fund_product` VALUES (7, 1, '余额宝', NULL, '低利息', NULL);
INSERT INTO `fund_product` VALUES (8, 1, '养老金', NULL, NULL, NULL);
INSERT INTO `fund_product` VALUES (9, 1, '社保基金', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` int NOT NULL,
  `picture` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片',
  `title` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题',
  `main` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '正文',
  `location` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='发布笔记表';

-- ----------------------------
-- Records of note
-- ----------------------------
BEGIN;
INSERT INTO `note` VALUES (1, NULL, '小情歌', '这是一首简单的小情歌', '深圳');
COMMIT;

-- ----------------------------
-- Table structure for oms_cart_item
-- ----------------------------
DROP TABLE IF EXISTS `oms_cart_item`;
CREATE TABLE `oms_cart_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint DEFAULT NULL COMMENT '商品id',
  `product_sku_id` bigint DEFAULT NULL COMMENT '商品sku的ID',
  `member_id` int DEFAULT NULL COMMENT '会员id',
  `quantity` int DEFAULT NULL COMMENT '购买数量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '添加到购物车的价格',
  `product_pic` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品主图',
  `product_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品名称',
  `product_sub_title` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品副标题（卖点）',
  `product_sku_code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品sku码',
  `member_nickname` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '会员昵称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_status` int DEFAULT NULL COMMENT '是否删除',
  `product_category_id` bigint DEFAULT NULL COMMENT '商品分类',
  `product_brand` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `prosuct_sn` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `product_attr` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='购物车表';

-- ----------------------------
-- Records of oms_cart_item
-- ----------------------------
BEGIN;
INSERT INTO `oms_cart_item` VALUES (12, 26, 90, 1, 1, 3788.00, NULL, '华为 HUAWEI P20', 'AI智慧全面屏 6GB +64GB 亮黑色 全网通版 移动联通电信4G手机 双卡双待手机 双卡双待', '201806070026001', 'windir', '2018-08-27 16:53:44', NULL, 1, 19, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for oms_order
-- ----------------------------
DROP TABLE IF EXISTS `oms_order`;
CREATE TABLE `oms_order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `member_id` bigint NOT NULL,
  `coupon_id` bigint DEFAULT NULL,
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `create_time` datetime DEFAULT NULL COMMENT '提交时间',
  `member_username` varchar(64) DEFAULT NULL COMMENT '用户帐号',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '应付金额（实际支付金额）',
  `freight_amount` decimal(10,2) DEFAULT NULL COMMENT '运费金额',
  `promotion_amount` decimal(10,2) DEFAULT NULL COMMENT '促销优化金额（促销价、满减、阶梯价）',
  `integration_amount` decimal(10,2) DEFAULT NULL COMMENT '积分抵扣金额',
  `coupon_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠券抵扣金额',
  `discount_amount` decimal(10,2) DEFAULT NULL COMMENT '管理员后台调整订单使用的折扣金额',
  `pay_type` int DEFAULT NULL COMMENT '支付方式：0->未支付；1->支付宝；2->微信',
  `source_type` int DEFAULT NULL COMMENT '订单来源：0->PC订单；1->app订单',
  `status` int DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
  `order_type` int DEFAULT NULL COMMENT '订单类型：0->正常订单；1->秒杀订单',
  `delivery_company` varchar(64) DEFAULT NULL COMMENT '物流公司(配送方式)',
  `delivery_sn` varchar(64) DEFAULT NULL COMMENT '物流单号',
  `auto_confirm_day` int DEFAULT NULL COMMENT '自动确认时间（天）',
  `integration` int DEFAULT NULL COMMENT '可以获得的积分',
  `growth` int DEFAULT NULL COMMENT '可以活动的成长值',
  `promotion_info` varchar(100) DEFAULT NULL COMMENT '活动信息',
  `bill_type` int DEFAULT NULL COMMENT '发票类型：0->不开发票；1->电子发票；2->纸质发票',
  `bill_header` varchar(200) DEFAULT NULL COMMENT '发票抬头',
  `bill_content` varchar(200) DEFAULT NULL COMMENT '发票内容',
  `bill_receiver_phone` varchar(32) DEFAULT NULL COMMENT '收票人电话',
  `bill_receiver_email` varchar(64) DEFAULT NULL COMMENT '收票人邮箱',
  `receiver_name` varchar(100) NOT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(32) NOT NULL COMMENT '收货人电话',
  `receiver_post_code` varchar(32) DEFAULT NULL COMMENT '收货人邮编',
  `receiver_province` varchar(32) DEFAULT NULL COMMENT '省份/直辖市',
  `receiver_city` varchar(32) DEFAULT NULL COMMENT '城市',
  `receiver_region` varchar(32) DEFAULT NULL COMMENT '区',
  `receiver_detail_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `note` varchar(500) DEFAULT NULL COMMENT '订单备注',
  `confirm_status` int DEFAULT NULL COMMENT '确认收货状态：0->未确认；1->已确认',
  `delete_status` int NOT NULL DEFAULT '0' COMMENT '删除状态：0->未删除；1->已删除',
  `use_integration` int DEFAULT NULL COMMENT '下单时使用的积分',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime DEFAULT NULL COMMENT '确认收货时间',
  `comment_time` datetime DEFAULT NULL COMMENT '评价时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of oms_order
-- ----------------------------
BEGIN;
INSERT INTO `oms_order` VALUES (12, 1, 2, '201809150101000001', '2018-09-15 12:24:27', 'test', 187322.06, 16377.75, 20.00, 2344.25, 0.00, 10.00, 10.00, 0, 1, 4, 0, '', '', 15, 13284, 13284, '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '江苏省', '常州市', '天宁区', '东晓街道', '111', 0, 0, NULL, NULL, NULL, NULL, NULL, '2019-11-09 16:50:28');
INSERT INTO `oms_order` VALUES (13, 1, 2, '201809150102000002', '2018-09-15 14:24:29', 'test', 18732.00, 16377.75, 0.00, 2344.25, 0.00, 10.00, 0.00, 1, 1, 1, 0, '', '', 15, 13284, 13284, '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', NULL, 0, 0, 1000, '2018-10-11 14:04:19', NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (14, 1, 2, '201809130101000001', '2018-09-13 16:57:40', 'test', 18732.00, 16377.75, 0.00, 2344.25, 0.00, 10.00, 0.00, 2, 1, 2, 0, '顺丰快递', '201707196398345', 15, 13284, 13284, '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', NULL, 0, 0, NULL, '2018-10-13 13:44:04', '2018-10-16 13:43:41', NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (15, 1, 2, '201809130102000002', '2018-09-13 17:03:00', 'test', 18732.00, 16377.75, 0.00, 2344.25, 0.00, 10.00, 0.00, 1, 1, 3, 0, '顺丰快递', '201707196398346', 15, 13284, 13284, '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', NULL, 1, 0, NULL, '2018-10-13 13:44:54', '2018-10-16 13:45:01', '2018-10-18 14:05:31', NULL, NULL);
INSERT INTO `oms_order` VALUES (16, 1, 2, '201809140101000001', '2018-09-14 16:16:16', 'test', 18732.00, 16377.75, 0.00, 2344.25, 0.00, 10.00, 0.00, 2, 1, 4, 0, NULL, NULL, 15, 13284, 13284, '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (17, 1, 2, '201809150101000003', '2018-09-15 12:24:27', 'test', 18732.00, 16377.75, 0.00, 2344.25, 0.00, 10.00, 0.00, 0, 1, 4, 0, '顺丰快递', '201707196398345', 15, NULL, NULL, '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', NULL, 0, 0, NULL, NULL, '2018-10-12 14:01:28', NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (18, 1, 2, '201809150102000004', '2018-09-15 14:24:29', 'test', 18732.00, 16377.75, 0.00, 2344.25, 0.00, 10.00, 0.00, 1, 1, 1, 0, '圆通快递', 'xx', 15, NULL, NULL, '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', NULL, 0, 0, 1000, NULL, '2018-10-16 14:42:17', NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (19, 1, 2, '201809130101000003', '2018-09-13 16:57:40', 'test', 18732.00, 16377.75, 0.00, 2344.25, 0.00, 10.00, 0.00, 2, 1, 2, 0, NULL, NULL, 15, NULL, NULL, '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (20, 1, 2, '201809130102000004', '2018-09-13 17:03:00', 'test', 18732.00, 16377.75, 0.00, 2344.25, 0.00, 10.00, 0.00, 1, 1, 3, 0, NULL, NULL, 15, NULL, NULL, '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (21, 1, 2, '201809140101000002', '2018-09-14 16:16:16', 'test', 18732.00, 16377.75, 0.00, 2344.25, 0.00, 10.00, 0.00, 2, 1, 4, 0, NULL, NULL, 15, 18682, 18682, '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (22, 1, 2, '201809150101000005', '2018-09-15 12:24:27', 'test', 18732.00, 16377.75, 0.00, 2344.25, 0.00, 10.00, 0.00, 0, 1, 4, 0, '顺丰快递', '201707196398345', 15, 0, 0, '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', NULL, 0, 0, NULL, NULL, '2018-10-12 14:01:28', NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (23, 1, 2, '201809150102000006', '2018-09-15 14:24:29', 'test', 18732.00, 16377.75, 0.00, 2344.25, 0.00, 10.00, 0.00, 1, 1, 1, 0, '顺丰快递', 'xxx', 15, 0, 0, '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', NULL, 0, 0, 1000, NULL, '2018-10-16 14:41:28', NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (24, 1, 2, '201809130101000005', '2018-09-13 16:57:40', 'test', 18732.00, 16377.75, 0.00, 2344.25, 0.00, 10.00, 0.00, 2, 1, 2, 0, NULL, NULL, 15, 18682, 18682, '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (25, 1, 2, '201809130102000006', '2018-09-13 17:03:00', 'test', 18732.00, 16377.75, 10.00, 2344.25, 0.00, 10.00, 5.00, 1, 1, 4, 0, NULL, NULL, 15, 18682, 18682, '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨22', '18033441849', '518000', '北京市', '北京城区', '东城区', '东城街道', 'xxx', 0, 0, NULL, NULL, NULL, NULL, NULL, '2018-10-30 15:08:31');
INSERT INTO `oms_order` VALUES (26, 1, 2, '201809140101000003', '2018-09-14 16:16:16', 'test', 18732.00, 16377.75, 0.00, 2344.25, 0.00, 10.00, 0.00, 2, 1, 4, 0, NULL, NULL, 15, 18682, 18682, '单品促销,打折优惠：满3件，打7.50折,满减优惠：满1000.00元，减120.00元,满减优惠：满1000.00元，减120.00元,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '福田区', '东晓街道', NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (27, 1, NULL, '202002250100000001', '2020-02-25 15:59:20', 'test', 540.00, 540.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 1, 0, 0, NULL, NULL, NULL, 0, 0, '无优惠,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '南山区', '科兴科学园', NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (28, 1, NULL, '202002250100000002', '2020-02-25 16:05:47', 'test', 540.00, 540.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 1, 0, 0, NULL, NULL, NULL, 0, 0, '无优惠,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '南山区', '科兴科学园', NULL, 0, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (29, 1, NULL, '202002250100000003', '2020-02-25 16:07:58', 'test', 540.00, 540.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0, 1, 0, 0, NULL, NULL, NULL, 0, 0, '无优惠,无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '南山区', '科兴科学园', NULL, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oms_order` VALUES (30, 1, NULL, '202002250100000004', '2020-02-25 16:50:13', 'test', 240.00, 240.00, 20.00, 0.00, 0.00, 0.00, 10.00, 0, 1, 2, 0, '顺丰快递', '12333333', NULL, 0, 0, '无优惠', NULL, NULL, NULL, NULL, NULL, '大梨', '18033441849', '518000', '广东省', '深圳市', '南山区', '科兴科学园', NULL, 0, 0, NULL, '2020-02-25 16:53:29', '2020-02-25 16:54:03', NULL, NULL, '2020-02-25 16:52:51');
COMMIT;

-- ----------------------------
-- Table structure for oms_order_operate_history
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_operate_history`;
CREATE TABLE `oms_order_operate_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint DEFAULT NULL COMMENT '订单id',
  `operate_man` varchar(100) DEFAULT NULL COMMENT '操作人：用户；系统；后台管理员',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `order_status` int DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='订单操作历史记录';

-- ----------------------------
-- Records of oms_order_operate_history
-- ----------------------------
BEGIN;
INSERT INTO `oms_order_operate_history` VALUES (5, 12, '后台管理员', '2018-10-12 14:01:29', 2, '完成发货');
INSERT INTO `oms_order_operate_history` VALUES (6, 13, '后台管理员', '2018-10-12 14:01:29', 2, '完成发货');
INSERT INTO `oms_order_operate_history` VALUES (7, 12, '后台管理员', '2018-10-12 14:13:10', 4, '订单关闭:买家退货');
INSERT INTO `oms_order_operate_history` VALUES (8, 13, '后台管理员', '2018-10-12 14:13:10', 4, '订单关闭:买家退货');
INSERT INTO `oms_order_operate_history` VALUES (9, 22, '后台管理员', '2018-10-15 16:31:48', 4, '订单关闭:xxx');
INSERT INTO `oms_order_operate_history` VALUES (10, 22, '后台管理员', '2018-10-15 16:35:08', 4, '订单关闭:xxx');
INSERT INTO `oms_order_operate_history` VALUES (11, 22, '后台管理员', '2018-10-15 16:35:59', 4, '订单关闭:xxx');
INSERT INTO `oms_order_operate_history` VALUES (12, 17, '后台管理员', '2018-10-15 16:43:40', 4, '订单关闭:xxx');
INSERT INTO `oms_order_operate_history` VALUES (13, 25, '后台管理员', '2018-10-15 16:52:14', 4, '订单关闭:xxx');
INSERT INTO `oms_order_operate_history` VALUES (14, 26, '后台管理员', '2018-10-15 16:52:14', 4, '订单关闭:xxx');
INSERT INTO `oms_order_operate_history` VALUES (15, 23, '后台管理员', '2018-10-16 14:41:28', 2, '完成发货');
INSERT INTO `oms_order_operate_history` VALUES (16, 13, '后台管理员', '2018-10-16 14:42:17', 2, '完成发货');
INSERT INTO `oms_order_operate_history` VALUES (17, 18, '后台管理员', '2018-10-16 14:42:17', 2, '完成发货');
INSERT INTO `oms_order_operate_history` VALUES (18, 26, '后台管理员', '2018-10-30 14:37:44', 4, '订单关闭:关闭订单');
INSERT INTO `oms_order_operate_history` VALUES (19, 25, '后台管理员', '2018-10-30 15:07:01', 0, '修改收货人信息');
INSERT INTO `oms_order_operate_history` VALUES (20, 25, '后台管理员', '2018-10-30 15:08:13', 0, '修改费用信息');
INSERT INTO `oms_order_operate_history` VALUES (21, 25, '后台管理员', '2018-10-30 15:08:31', 0, '修改备注信息：xxx');
INSERT INTO `oms_order_operate_history` VALUES (22, 25, '后台管理员', '2018-10-30 15:08:39', 4, '订单关闭:2222');
INSERT INTO `oms_order_operate_history` VALUES (23, 12, '后台管理员', '2019-11-09 16:50:28', 4, '修改备注信息：111');
INSERT INTO `oms_order_operate_history` VALUES (24, 30, '后台管理员', '2020-02-25 16:52:37', 0, '修改费用信息');
INSERT INTO `oms_order_operate_history` VALUES (25, 30, '后台管理员', '2020-02-25 16:52:51', 0, '修改费用信息');
INSERT INTO `oms_order_operate_history` VALUES (26, 30, '后台管理员', '2020-02-25 16:54:03', 2, '完成发货');
COMMIT;

-- ----------------------------
-- Table structure for pms_product
-- ----------------------------
DROP TABLE IF EXISTS `pms_product`;
CREATE TABLE `pms_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片',
  `delete_status` int DEFAULT '0' COMMENT '删除状态：0->未删除；1->已删除',
  `sale` int DEFAULT NULL COMMENT '销量',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `stock` int DEFAULT NULL COMMENT '商品库存',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '商品描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of pms_product
-- ----------------------------
BEGIN;
INSERT INTO `pms_product` VALUES (1, '棒棒糖', 'https://images.pexels.com/photos/4202963/pexels-photo-4202963.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500', 1, 5, 2.00, 10, '又甜又软');
INSERT INTO `pms_product` VALUES (2, '欧文5代篮球鞋', 'https://img1.baidu.com/it/u=274984980,2421985085&fm=26&fmt=auto', 1, 2, 1000.00, 5, '假鞋');
INSERT INTO `pms_product` VALUES (3, '可乐', 'https://img1.baidu.com/it/u=274984980,2421985085&fm=26&fmt=auto', 0, 4, 3.44, 30, '猴甜');
INSERT INTO `pms_product` VALUES (4, 'arickya', 'https://www.baidu.com/link?url=SZ962XJLlR8yDvmNm5m5r_948EBG', 0, 90, 2.51, 2000, '软甜腻update');
COMMIT;

-- ----------------------------
-- Table structure for sms_advertise
-- ----------------------------
DROP TABLE IF EXISTS `sms_advertise`;
CREATE TABLE `sms_advertise` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '广告名称',
  `type` int DEFAULT NULL COMMENT '0->pc首页轮播；1->app首页轮播',
  `pic` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `click_count` int DEFAULT NULL COMMENT '点击数',
  `note` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for sms_coupon
-- ----------------------------
DROP TABLE IF EXISTS `sms_coupon`;
CREATE TABLE `sms_coupon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` int DEFAULT NULL COMMENT '优惠券类型：0->购物赠券',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `min_point` int DEFAULT NULL COMMENT '使用门槛：0->无门槛',
  `note` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='后台用户表';

-- ----------------------------
-- Records of ums_admin
-- ----------------------------
BEGIN;
INSERT INTO `ums_admin` VALUES (9, 'admin', '$2a$10$bczPdgwPbZkmHp7I8x0UmevPIxzWa3Yx37.AMPXktX.YjMvBG7Hlq', NULL, NULL, NULL, NULL, '2022-11-03 18:16:34', NULL, 1);
INSERT INTO `ums_admin` VALUES (11, 'test', '$2a$10$AqFoWdUsTkaNCACg.2UoHuwEqKdfM2C3wFMdXqdkmPwVPy1AKN9e2', NULL, '809@qq.com', 'test', NULL, '2022-11-03 18:23:21', NULL, 1);
INSERT INTO `ums_admin` VALUES (12, 'hanhan', '$2a$10$jFea.8wTAruORLM7SYUda.LSc6ICdQCwhnCGVf3ZdkpRAXuvaH3a2', NULL, NULL, NULL, NULL, '2022-11-04 01:40:26', NULL, 1);
INSERT INTO `ums_admin` VALUES (13, 'qiao', '$2a$10$AHWMC7SaNsLSLFjRmtVnieka/y/f4RAJUcchn4.vb/aeG3bDuKv8W', NULL, NULL, NULL, NULL, '2022-11-05 00:45:58', NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for ums_admin_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_permission_relation`;
CREATE TABLE `ums_admin_permission_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_id` bigint DEFAULT NULL,
  `permission_id` bigint DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户和权限关系表(除角色中定义的权限以外的加减权限)';

-- ----------------------------
-- Table structure for ums_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role_relation`;
CREATE TABLE `ums_admin_role_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_id` bigint DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='后台用户和角色关系表';

-- ----------------------------
-- Table structure for ums_permission
-- ----------------------------
DROP TABLE IF EXISTS `ums_permission`;
CREATE TABLE `ums_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pid` bigint DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `type` int DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前端资源路径',
  `status` int DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `sort` int DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='后台用户权限表';

-- ----------------------------
-- Table structure for ums_resource
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource`;
CREATE TABLE `ums_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源URL',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `category_id` bigint DEFAULT NULL COMMENT '资源分类ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='后台资源表';

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `admin_count` int DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='后台用户角色表';

-- ----------------------------
-- Table structure for ums_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_resource_relation`;
CREATE TABLE `ums_role_resource_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8 COMMENT='后台角色资源关系表';

-- ----------------------------
-- Table structure for user_follow
-- ----------------------------
DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow` (
  `id` int NOT NULL,
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `follow_id` int NOT NULL COMMENT '关注者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户关注表';

-- ----------------------------
-- Records of user_follow
-- ----------------------------
BEGIN;
INSERT INTO `user_follow` VALUES (1, 1, 2);
INSERT INTO `user_follow` VALUES (2, 1, 3);
INSERT INTO `user_follow` VALUES (3, 2, 1);
INSERT INTO `user_follow` VALUES (4, 2, 3);
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `number` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '大红书号',
  `avator` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `introduction` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '简介',
  `sex` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '性别',
  `birthday` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生日',
  `area` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地区',
  `job` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '职业',
  `school` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学校',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, '等布拉多', '15627364', 'https://avatars.githubusercontent.com/u/28795155?s=40&v=4', 'nothing', 'male', '1996-11-10', '中国-广东-深圳', '无业游民', '野鸡大学');
INSERT INTO `users` VALUES (2, '你瞧月亮注定是要陈女士', '12223456', NULL, '一个啥都分享的美少女', 'female', '1997-11-23', '中国-湖南-湘潭', '财务', '湖南大学');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
