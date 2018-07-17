drop table if exists timed_task;

-- ----------------------------
-- timed_task 定时任务表
-- ----------------------------
CREATE TABLE `timed_task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '定时任务ID',
  `task_name` varchar(50) NOT NULL COMMENT '定时任务名称',
  `execute_time` varchar(20) NOT NULL COMMENT '执行时间',
  `is_start` int(11) DEFAULT '1' COMMENT '是否启动1启动0停止',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `name` varchar(50) DEFAULT NULL COMMENT '定时器名称',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;