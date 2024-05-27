CREATE DATABASE message_job;
use message_job;
-- 创建email_config表
CREATE TABLE email_config
(
    id               BIGINT AUTO_INCREMENT COMMENT '配置ID',
    config_id        VARCHAR(255) COMMENT '配置ID',
    username         VARCHAR(255) COMMENT '用户名',
    password         VARCHAR(255) COMMENT '授权码',
    host             VARCHAR(255) COMMENT 'host',
    port             INT COMMENT '端口',
    protocol         VARCHAR(255) COMMENT '协议',
    default_encoding VARCHAR(255) COMMENT '默认编码',
    created_at       DATETIME COMMENT '创建时间',
    updated_at       DATETIME COMMENT '更新时间',
    is_deleted       TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标识，0-未删除，1-已删除',
    PRIMARY KEY (id),
    INDEX idx_config_id (config_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='Email配置表';

-- 创建sms_config表
CREATE TABLE sms_config
(
    id                BIGINT AUTO_INCREMENT COMMENT '配置ID',
    config_id         VARCHAR(255) COMMENT '配置ID',
    supplier          VARCHAR(255) COMMENT '运营商',
    access_key_id     VARCHAR(255) COMMENT 'accessKeyId',
    access_key_secret VARCHAR(255) COMMENT 'accessKeySecret',
    signature         VARCHAR(255) COMMENT '签名',
    template_id       VARCHAR(255) COMMENT '模版ID',
    created_at        DATETIME COMMENT '创建时间',
    updated_at        DATETIME COMMENT '更新时间',
    is_deleted        TINYINT(1) DEFAULT 0 COMMENT '逻辑删除标识，0-未删除，1-已删除',
    PRIMARY KEY (id),
    UNIQUE INDEX idx_config_id (config_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='短信配置表';

-- 创建message_task_schedule_config表
CREATE TABLE message_task_schedule_config
(
    message_schedule_limit INT COMMENT '每次拉取任务数量',
    max_retry_num          INT COMMENT '最大重试次数',
    retry_interval         INT COMMENT '重试间隔',
    create_time            BIGINT COMMENT '创建时间',
    update_time            BIGINT COMMENT '更新时间',
    PRIMARY KEY (message_schedule_limit)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='消息任务调度配置表';


# CREATE DATABASE message_task;
# use message_task;
-- 创建message_template表
CREATE TABLE message_template
(
    id            BIGINT AUTO_INCREMENT COMMENT '模版ID',
    template_name VARCHAR(255) COMMENT '模版名称',
    content       TEXT COMMENT '模版配置',
    status        INT COMMENT '状态 0 启用 2 禁用',
    type          INT COMMENT '模版类型',
    description   VARCHAR(255) COMMENT '说明',
    PRIMARY KEY (id),
    INDEX idx_template_name (template_name),
    INDEX idx_status (status),
    INDEX idx_type (type)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='消息模版表';

-- 创建message_task_info表
CREATE TABLE message_task_info
(
    id             BIGINT AUTO_INCREMENT COMMENT '消息任务ID',
    biz_id         VARCHAR(255) COMMENT '业务ID',
    msg_task_type  INT COMMENT '消息类型 0 邮件, 1 阿里云短信, 2 腾讯云短信.....',
    biz_type       INT COMMENT '业务类型',
    status         INT COMMENT '任务状态 0 未发送, 1 发送中, 2 发送成功, 3 发送失败',
    max_retry_num  INT COMMENT '最大重试次数',
    crt_retry_num  INT COMMENT '已经重试次数',
    retry_interval INT COMMENT '重试间隔',
    title          VARCHAR(255) COMMENT 'message标题',
    content        TEXT COMMENT 'message内容',
    receiver       VARCHAR(255) COMMENT 'message接收人',
    config_id      VARCHAR(255) COMMENT '配置ID',
    create_time    BIGINT COMMENT '创建时间',
    update_time    BIGINT COMMENT '更新时间',
    PRIMARY KEY (id),
    INDEX idx_biz_id (biz_id),
    INDEX idx_status (status)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='消息任务信息表';

-- 创建message_record表
CREATE TABLE message_record
(
    id           BIGINT AUTO_INCREMENT COMMENT '消息记录ID',
    biz_id       VARCHAR(255) COMMENT '业务ID',
    biz_type     INT COMMENT '业务类型',
    msg_type     INT COMMENT '消息类型 0 邮件, 1 阿里云短信, 2 腾讯云短信.....',
    title        VARCHAR(255) COMMENT 'message标题',
    content      TEXT COMMENT 'message内容',
    status       INT COMMENT 'message状态',
    fail_context VARCHAR(255) COMMENT '失败消息',
    create_time  BIGINT COMMENT '创建时间',
    update_time  BIGINT COMMENT '更新时间',
    PRIMARY KEY (id),
    INDEX idx_biz_id (biz_id),
    INDEX idx_status (status)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='消息记录表';