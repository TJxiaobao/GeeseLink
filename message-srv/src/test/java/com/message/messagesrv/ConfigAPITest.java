package com.message.messagesrv;

import cn.hutool.json.JSONObject;
import com.message.common.domin.bo.EmailConfigInsertBo;
import com.message.common.domin.bo.EmailConfigUpdateBo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class ConfigAPITest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    void insertEmail() throws Exception {
        //初始化MockMvc
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        EmailConfigInsertBo emailConfigInsertBo = new EmailConfigInsertBo();
        emailConfigInsertBo.setConfigId("test");
        emailConfigInsertBo.setHost("127.0.0.1");
        emailConfigInsertBo.setPort(25);
        emailConfigInsertBo.setUsername("test");
        emailConfigInsertBo.setPassword("test");
        emailConfigInsertBo.setDefaultEncoding("UTF-8");
        emailConfigInsertBo.setProtocol("smtp");


        JSONObject entries = new JSONObject(emailConfigInsertBo);
        //调用接口
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/system/config/add/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(entries.toString()))
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(result);
    }

    @Test
    void updateEmail() throws Exception {
        //初始化MockMvc
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        EmailConfigUpdateBo emailConfigUpdateBo = new EmailConfigUpdateBo();
        emailConfigUpdateBo.setId(1789301379970482178L);
        emailConfigUpdateBo.setConfigId("test");
        emailConfigUpdateBo.setHost("127.0.0.1");
        emailConfigUpdateBo.setPort(25);
        emailConfigUpdateBo.setUsername("update");
        emailConfigUpdateBo.setPassword("update");
        emailConfigUpdateBo.setDefaultEncoding("UTF-8");
        emailConfigUpdateBo.setProtocol("smtp");


        JSONObject entries = new JSONObject(emailConfigUpdateBo);
        //调用接口
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/system/config/update/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(entries.toString()))
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(result);
    }

    @Test
    void delEmail() throws Exception {
        //初始化MockMvc
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


        //调用接口
        String result = mockMvc.perform(MockMvcRequestBuilders.delete("/system/config/del/email")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .param("id", "1789305474675662850"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(result);
    }

    @Test
    void exportEmail() throws Exception {
        //初始化MockMvc
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


        //调用接口
        String result = mockMvc.perform(MockMvcRequestBuilders.delete("/system/config/export/email")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .param("id", "1789305474675662850"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(result);
    }
}