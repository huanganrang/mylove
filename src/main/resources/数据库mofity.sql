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
-- 2015-07-07 xuwm end --
	