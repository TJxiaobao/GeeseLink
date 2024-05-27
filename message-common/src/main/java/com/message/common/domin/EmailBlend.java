package com.message.common.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailBlend {

    private String configId;

    private JavaMailSenderImpl javaMailSender;
}
