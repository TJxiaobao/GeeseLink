package com.message.job.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.message.common.domin.EmailBlend;
import com.message.common.domin.EmailConfig;
import com.message.common.mapper.EmailConfigMapper;
import com.message.job.factory.EmailFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.List;

@Configuration
@Slf4j
public class MailSenderConfig implements ApplicationRunner {

    private final EmailConfigMapper mailMapper;

    public MailSenderConfig(EmailConfigMapper mailMapper) {
        this.mailMapper = mailMapper;
    }


    public void buildMailSender() {
        log.info("初始化mailSender");
//        QueryWrapper<EmailConfig> state = new QueryWrapper<EmailConfig>().eq("state", 1);
        QueryWrapper<EmailConfig> state = new QueryWrapper<EmailConfig>();
        List<EmailConfig> mails = mailMapper.selectList(state);

        if (mails != null && !mails.isEmpty()) {
            mails.forEach(mail -> {
                JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//                javaMailSender.setDefaultEncoding(mail.getDefaultEncoding());
                javaMailSender.setHost(mail.getHost());
                javaMailSender.setPort(mail.getPort());
                javaMailSender.setProtocol(mail.getProtocol());
                javaMailSender.setUsername(mail.getUsername());
                javaMailSender.setPassword(mail.getPassword());
                // 添加数据
                EmailBlend emailBlend = new EmailBlend();
                emailBlend.setConfigId(mail.getConfigId());
                emailBlend.setJavaMailSender(javaMailSender);
                EmailFactory.register(emailBlend);
            });
        } else {
            log.error("数据库无可用的email配置");
        }

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        buildMailSender();
    }
}