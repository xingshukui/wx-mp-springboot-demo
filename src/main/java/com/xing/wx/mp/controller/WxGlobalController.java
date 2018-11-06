package com.xing.wx.mp.controller;

import com.xing.wx.mp.message.MyMessageHandler;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author : xingshukui
 * @email : xingshukui@890media.com
 * @date : 2018/11/6 10:00 AM
 * @desc :
 */
@Slf4j
@Controller
@RequestMapping("/wechat")
public class WxGlobalController {
    private static final String SUCCESS = "success";

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private MyMessageHandler myMessageHandler;

    @RequestMapping(value = "/access", method = RequestMethod.GET)
    @ResponseBody
    public String access(HttpServletRequest request) {
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            return SUCCESS;
        }
        return null;
    }


    @RequestMapping(value = "/access", method = RequestMethod.POST)
    @ResponseBody
    public String accessPost(HttpServletRequest request) throws IOException, WxErrorException {
        WxMpXmlMessage wxMpXmlMessage = WxMpXmlMessage.fromXml(request.getInputStream());
        if (wxMpXmlMessage != null) {
            log.info("接受微信消息： " + wxMpXmlMessage.toString());
            return myMessageHandler.handle(wxMpXmlMessage, null, null, null).toXml();
        }else {
            return null;
        }
    }
}
