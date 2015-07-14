-- 2015-07-06 xuwm start --
DROP TABLE IF EXISTS `lv_account`;
CREATE TABLE IF NOT EXISTS `lv_account` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `openId` int(11) NOT NULL AUTO_INCREMENT COMMENT '注册时生成的唯一ID',
  `loginName` varchar(20) DEFAULT NULL COMMENT '登录账号',
  `nickName` varchar(20) DEFAULT NULL COMMENT '昵称',
  `password` varchar(50) NOT NULL COMMENT '登录密码',
  `sex` varchar(20) NOT NULL DEFAULT 'SX01' COMMENT '性别（1、男；2、女）',
  `birthday` date NOT NULL COMMENT '生日',
  `height` int(11) DEFAULT NULL COMMENT '身高（cm）',
  `weight` int(11) DEFAULT NULL COMMENT '体重（kg）',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ号',
  `address` varchar(100) DEFAULT NULL COMMENT '居住地',
  `education` varchar(20) DEFAULT NULL COMMENT '学历',
  `profession` varchar(20) DEFAULT NULL COMMENT '职业',
  `monthIncome` varchar(20) DEFAULT NULL COMMENT '月收入',
  `marryStatus` varchar(20) DEFAULT NULL COMMENT '婚姻状态',
  `constellation` varchar(20) DEFAULT NULL COMMENT '星座',
  `personDesc` text COMMENT '个性签名',
  `createTime` datetime NOT NULL COMMENT '注册时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `auditStatus` varchar(20) DEFAULT '1' COMMENT '头像审核状态',
  `headImg` varchar(100) DEFAULT NULL COMMENT '头像图片路径',
  `longitude` decimal(10,6) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10,6) DEFAULT NULL COMMENT '纬度',
  `vipLevel` varchar(20) DEFAULT NULL COMMENT 'VIP等级',
  `vipOpenTime` datetime DEFAULT NULL COMMENT 'VIP开通时间',
  `online` varchar(20) DEFAULT '1' COMMENT '在线状态',
  `lastLoginTime` datetime NOT NULL COMMENT '最近在线时间',
  `visitNum` int(11) DEFAULT '0' COMMENT '来访数量',
  PRIMARY KEY (`id`),
  KEY `openId` (`openId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户信息表';

DROP TABLE IF EXISTS `lv_account_photo`;
CREATE TABLE IF NOT EXISTS `lv_account_photo` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `openId` int(11) NOT NULL COMMENT '用户openId',
  `photoImg` varchar(100) NOT NULL COMMENT '照片路径',
  `createTime` datetime NOT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`),
  KEY `FK_lv_account_photo_lv_account` (`openId`),
  CONSTRAINT `FK_lv_account_photo_lv_account` FOREIGN KEY (`openId`) REFERENCES `lv_account` (`openId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人相册表';

DROP TABLE IF EXISTS `lv_feedback`;
CREATE TABLE IF NOT EXISTS `lv_feedback` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `openId` int(11) NOT NULL COMMENT '用户openId',
  `contactWay` varchar(50) NOT NULL COMMENT '联系方式',
  `content` text NOT NULL COMMENT '反馈内容',
  `createTime` datetime NOT NULL COMMENT '反馈时间',
  PRIMARY KEY (`id`),
  KEY `FK_lv_feedback_lv_account` (`openId`),
  CONSTRAINT `FK_lv_feedback_lv_account` FOREIGN KEY (`openId`) REFERENCES `lv_account` (`openId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='意见反馈信息表';

DROP TABLE IF EXISTS `lv_follow`;
CREATE TABLE IF NOT EXISTS `lv_follow` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `fromOpenId` int(11) NOT NULL COMMENT '关注人openId',
  `toOpenId` int(11) NOT NULL COMMENT '被关注人openId',
  `createTime` datetime NOT NULL COMMENT '关注时间',
  PRIMARY KEY (`id`),
  KEY `FK_lv_follow_lv_account` (`fromOpenId`),
  KEY `FK_lv_follow_lv_account_2` (`toOpenId`),
  CONSTRAINT `FK_lv_follow_lv_account` FOREIGN KEY (`fromOpenId`) REFERENCES `lv_account` (`openId`),
  CONSTRAINT `FK_lv_follow_lv_account_2` FOREIGN KEY (`toOpenId`) REFERENCES `lv_account` (`openId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注信息表';

DROP TABLE IF EXISTS `lv_partner_condition`;
CREATE TABLE IF NOT EXISTS `lv_partner_condition` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `openId` int(11) NOT NULL COMMENT '用户openId',
  `age` varchar(50) DEFAULT NULL COMMENT '年龄',
  `address` varchar(50) DEFAULT NULL COMMENT '居住地',
  `height` varchar(50) DEFAULT NULL COMMENT '身高',
  `weight` varchar(50) DEFAULT NULL COMMENT '体重',
  `education` varchar(50) DEFAULT NULL COMMENT '学历',
  `monthIncome` varchar(50) DEFAULT NULL COMMENT '月收入',
  PRIMARY KEY (`id`),
  KEY `FK_lv_partner_condition_lv_account` (`openId`),
  CONSTRAINT `FK_lv_partner_condition_lv_account` FOREIGN KEY (`openId`) REFERENCES `lv_account` (`openId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='征友条件信息表';

DROP TABLE IF EXISTS `lv_visit`;
CREATE TABLE IF NOT EXISTS `lv_visit` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `openId` int(11) NOT NULL COMMENT '用户openId',
  `visitOpenId` int(11) NOT NULL COMMENT '来访用户openId',
  `createTime` datetime NOT NULL COMMENT '来访时间',
  PRIMARY KEY (`id`),
  KEY `FK_lv_visit_lv_account` (`openId`),
  KEY `FK_lv_visit_lv_account_2` (`visitOpenId`),
  CONSTRAINT `FK_lv_visit_lv_account` FOREIGN KEY (`openId`) REFERENCES `lv_account` (`openId`),
  CONSTRAINT `FK_lv_visit_lv_account_2` FOREIGN KEY (`visitOpenId`) REFERENCES `lv_account` (`openId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='来访信息表';
-- 2015-07-06 xuwm end --

-- 2015-07-07 xuwm start --
ALTER TABLE `lv_account`
	ADD COLUMN `followNum` INT(11) NULL DEFAULT '0' COMMENT '关注我的用户数量' AFTER `visitNum`;
ALTER TABLE `lv_account`
	ADD COLUMN `qqSecret` INT(1) NULL DEFAULT '0' COMMENT 'QQ公开度（0:对VIP用户公开；1：保密）' AFTER `visitNum`,
	ADD COLUMN `mobileSecret` INT(1) NULL DEFAULT '0' COMMENT '手机号公开度(0:对VIP用户公开；1：保密)' AFTER `qqSecret`;
ALTER TABLE `lv_partner_condition`
	CHANGE COLUMN `age` `age` VARCHAR(50) NULL DEFAULT '不限' COMMENT '年龄' AFTER `openId`,
	CHANGE COLUMN `address` `address` VARCHAR(50) NULL DEFAULT '不限' COMMENT '居住地' AFTER `age`,
	CHANGE COLUMN `height` `height` VARCHAR(50) NULL DEFAULT '不限' COMMENT '身高' AFTER `address`,
	CHANGE COLUMN `weight` `weight` VARCHAR(50) NULL DEFAULT '不限' COMMENT '体重' AFTER `height`,
	CHANGE COLUMN `education` `education` VARCHAR(50) NULL DEFAULT '不限' COMMENT '学历' AFTER `weight`,
	CHANGE COLUMN `monthIncome` `monthIncome` VARCHAR(50) NULL DEFAULT '不限' COMMENT '月收入' AFTER `education`;
-- 2015-07-07 xuwm end --

-- 2015-07-09 xuwm start --
CREATE TABLE `lv_boost_activty` (
	`id` VARCHAR(36) NOT NULL COMMENT '主键',
	`goods_name` VARCHAR(100) NULL COMMENT '活动商品名称',
	`goods_price` DOUBLE NULL COMMENT '市场价值',
	`goods_img` VARCHAR(100) NULL COMMENT '商品图片',
	`assist_num` INT(1) NULL DEFAULT '1' COMMENT '所需助力点数',
	`startTime` TIME NULL COMMENT '开始时间',
	`endTime` TIME NULL COMMENT '结束时间',
	`goods_detail_img` VARCHAR(100) NULL COMMENT '商品详情图片',
	PRIMARY KEY (`id`)
)
COMMENT='挖宝活动信息表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `lv_boost_record` (
	`id` VARCHAR(36) NOT NULL COMMENT '主键',
	`openId` INT NOT NULL COMMENT '用户openId',
	`activtyId` VARCHAR(36) NOT NULL COMMENT '挖宝活动ID',
	`assist_num` INT(1) NULL DEFAULT '0' COMMENT '助力数',
	`visit_num` INT(1) NULL DEFAULT '0' COMMENT '查看数',
	`boostTime` DATETIME NOT NULL COMMENT '征集时间',
	`openTime` DATETIME NULL COMMENT '宝箱开启时间',
	`openStatus` INT(1) NULL DEFAULT '2' COMMENT '是否开启成功(1:成功；2：失败)',
	PRIMARY KEY (`id`),
	CONSTRAINT `FK__lv_boost_activty` FOREIGN KEY (`activtyId`) REFERENCES `lv_boost_activty` (`id`)
)
COMMENT='挖宝记录信息表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `lv_address` (
	`id` VARCHAR(36) NOT NULL COMMENT '主键',
	`openId` INT NOT NULL COMMENT '用户openId',
	`consignee` VARCHAR(20) NULL COMMENT '收货人姓名',
	`mobile` VARCHAR(20) NULL COMMENT '手机号',
	`province` VARCHAR(6) NULL COMMENT '所在省code',
	`city` VARCHAR(6) NULL COMMENT '所在市code',
	`district` VARCHAR(6) NULL COMMENT '所在区code',
	`address` VARCHAR(100) NULL COMMENT '详细地址',
	`createTime` DATETIME NULL COMMENT '创建时间',
	`updateTime` DATETIME NULL COMMENT '更新时间',
	PRIMARY KEY (`id`)
)
COMMENT='收货地址信息表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
-- 2015-07-09 xuwm end --

-- 2015-07-11 xuwm start --
CREATE TABLE `lv_assist_log` (
	`id` VARCHAR(36) NOT NULL COMMENT '主键',
	`boostRecordId` VARCHAR(36) NOT NULL COMMENT '挖宝记录ID',
	`openId` INT NOT NULL COMMENT '助力人',
	`assistTime` DATETIME NOT NULL COMMENT '助力时间',
	PRIMARY KEY (`id`),
	CONSTRAINT `FK__lv_boost_record` FOREIGN KEY (`boostRecordId`) REFERENCES `lv_boost_record` (`id`)
)
COMMENT='助力记录日志表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

ALTER TABLE `lv_boost_activty`
	ADD COLUMN `status` INT(1) NULL DEFAULT '1' COMMENT '状态（1：启用；2：停用）' AFTER `goods_detail_img`,
	ADD COLUMN `hourOfDay` INT(2) NULL DEFAULT '-1' COMMENT '时间标记' AFTER `status`,
	DROP COLUMN `startTime`,
	DROP COLUMN `endTime`;
	
ALTER TABLE `lv_account`
	ADD COLUMN `vipEndTime` DATETIME NULL DEFAULT NULL COMMENT 'VIP结束时间' AFTER `vipOpenTime`;
	
ALTER TABLE `lv_account`
	CHANGE COLUMN `vipOpenTime` `vipOpenTime` DATE NULL DEFAULT NULL COMMENT 'VIP开通时间' AFTER `vipLevel`,
	CHANGE COLUMN `vipEndTime` `vipEndTime` DATE NULL DEFAULT NULL COMMENT 'VIP结束时间' AFTER `vipOpenTime`;
-- 2015-07-11 xuwm end --

-- 2015-07-14 xuwm start --
ALTER TABLE `lv_account`
	ADD COLUMN `hxPassword` VARCHAR(50) NULL DEFAULT '0' COMMENT '环信登录密码' AFTER `mobileSecret`,
	ADD COLUMN `hxStatus` INT(1) NULL DEFAULT '2' COMMENT '环信注册状态（1：成功；2：失败））' AFTER `hxPassword`;
-- 2015-07-14 xuwm end --
	