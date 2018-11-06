package com.xing.wx.mp.message;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : xingshukui
 * @email : xingshukui@890media.com
 * @date : 2018/11/6 11:09 AM
 * @desc :
 */
@Slf4j
@Component
public class MyMessageHandler implements WxMpMessageHandler {
    private static final String SUBSCRIBE_SUCCESS = "欢迎关注";
    private static final String CLICK_SUCCESS = "别点了...";
    private static final String CLICK2_SUCCESS = "继续点...";



    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        WxMpXmlOutTextMessage wxMpXmlOutTextMessage = new WxMpXmlOutTextMessage();
        if (wxMessage.getMsgType().equalsIgnoreCase(WxConsts.XmlMsgType.TEXT)) {
            wxMpXmlOutTextMessage.setContent(AI.instance().aiMsg(wxMessage.getContent()));
        }else if (wxMessage.getMsgType().equalsIgnoreCase(WxConsts.XmlMsgType.EVENT)) {
            if (wxMessage.getEvent().equalsIgnoreCase(WxConsts.EventType.SUBSCRIBE)) {
                wxMpXmlOutTextMessage.setContent(SUBSCRIBE_SUCCESS);
            }else if (wxMessage.getEvent().equalsIgnoreCase(WxConsts.EventType.CLICK) && "V1001_TODAY_MUSIC".equalsIgnoreCase(wxMessage.getEventKey())) {
                wxMpXmlOutTextMessage.setContent(CLICK_SUCCESS);
            }else if (wxMessage.getEvent().equalsIgnoreCase(WxConsts.EventType.CLICK) && "V1001_GOOD".equalsIgnoreCase(wxMessage.getEventKey())) {
                wxMpXmlOutTextMessage.setContent(CLICK2_SUCCESS);
            }else {
                wxMpXmlOutTextMessage.setContent("弄啥呢...");
            }
        }
        wxMpXmlOutTextMessage.setFromUserName(wxMessage.getToUser());
        wxMpXmlOutTextMessage.setToUserName(wxMessage.getFromUser());
        wxMpXmlOutTextMessage.setMsgType("text");
        wxMpXmlOutTextMessage.setCreateTime(System.currentTimeMillis());
        return wxMpXmlOutTextMessage;

    }
}
