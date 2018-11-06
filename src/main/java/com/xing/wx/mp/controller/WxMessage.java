package com.xing.wx.mp.controller;

import com.xing.message.WxMessageService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : xingshukui
 * @email : xingshukui@890media.com
 * @date : 2018/11/5 6:02 PM
 * @desc :
 */
@Controller
@RequestMapping("/test")
public class WxMessage {

    @Autowired
    private WxMessageService wxMessageService;

    @Value("${wechat.msg.template_id}")
    private String templateId;

    @RequestMapping("/send")
    public void sendMsg() {
        WxMpTemplateMessage mpTemplateMessage = new WxMpTemplateMessage();
        mpTemplateMessage.setToUser("oRyiHw_aEQz4__mtBW0bsCE-teHU");
        mpTemplateMessage.setTemplateId(templateId);
        wxMessageService.sendTemplateMsg(mpTemplateMessage);
    }
}
