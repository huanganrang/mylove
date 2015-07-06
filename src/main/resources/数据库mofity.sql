ALTER TABLE `lv_account`
	ALTER `vipLevel` DROP DEFAULT;
ALTER TABLE `lv_account`
	CHANGE COLUMN `vipLevel` `vipLevel` VARCHAR(20) NULL COMMENT 'VIP等级' AFTER `latitude`;