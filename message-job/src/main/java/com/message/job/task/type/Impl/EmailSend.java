package com.message.job.task.type.Impl;

import com.message.common.domin.EmailBlend;
import com.message.common.domin.MessageTaskInfo;
import com.message.common.enums.MessageTaskInfoStatusEnum;
import com.message.common.enums.MessageTypeEnum;
import com.message.job.factory.EmailFactory;
import com.message.job.task.type.AbstractSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Slf4j
public class EmailSend extends AbstractSend {

    @Override
    public MessageTaskInfo send(MessageTaskInfo messageTaskInfo, String configId) {
        int crtRetryNum = messageTaskInfo.getCrtRetryNum();
        for (int i = 1; i <= messageTaskInfo.getCrtRetryNum(); i++) {
            Boolean status = sendEmail(messageTaskInfo);
            crtRetryNum++;
            //2.通过实例进行消息发送
            if (status) {
                messageTaskInfo.setCrtRetryNum(crtRetryNum);
                messageTaskInfo.setStatus(MessageTaskInfoStatusEnum.STATUS_ENUM_SEND_SUCCESS.getStatusCode());
                return messageTaskInfo;
            }
        }
        messageTaskInfo.setCrtRetryNum(crtRetryNum);
        messageTaskInfo.setStatus(MessageTaskInfoStatusEnum.STATUS_ENUM_SEND_FAIL.getStatusCode());
        return messageTaskInfo;
    }

    @Override
    public String supportType() {
        return MessageTypeEnum.EMAIL.getStatusName();
    }

    public Boolean sendEmail(MessageTaskInfo messageTaskInfo) {
        EmailBlend emailBlend = EmailFactory.getEmailBlend(messageTaskInfo.getConfigId());

        JavaMailSenderImpl mailSender = emailBlend.getJavaMailSender();
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(Objects.requireNonNull(mailSender.getUsername()));
            helper.setTo(messageTaskInfo.getReceiver().split(","));
//            helper.setFrom("");
            helper.setSubject(messageTaskInfo.getTitle());
            helper.setText(messageTaskInfo.getContent(), true);
            mailSender.send(mimeMessage);
            return true;
        } catch (javax.mail.MessagingException e) {
            log.error("send email error: {}", e.getMessage());
        }
        return false;
    }
}
