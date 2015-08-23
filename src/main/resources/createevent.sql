-- 开启event_scheduler：
SET GLOBAL event_scheduler = ON;

-- 查看event_scheduler开启状态：
SELECT @@event_scheduler;

-- 创建event_scheduler：
-- 从2015-08-01开始，每天对表在00:00:00进行一次update操作
create event resetFreeNum_event
	on schedule every 1 day
	starts  '2015-08-01 00:00:00'
	on completion not preserve 
	do update lv_free_config set used_num = 0;