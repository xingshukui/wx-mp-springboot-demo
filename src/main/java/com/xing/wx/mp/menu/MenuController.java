package com.xing.wx.mp.menu;

import com.xing.wx.mp.common.Result;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : xingshukui
 * @email : xingshukui@890media.com
 * @date : 2018/11/6 1:34 PM
 * @desc :
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private WxMpService wxMpService;


    @RequestMapping("/create")
    @ResponseBody
    public Result createMenu() {
        try {
            wxMpService.getMenuService().menuCreate("{\n" +
                    "    \"button\": [\n" +
                    "        {\n" +
                    "            \"type\": \"click\", \n" +
                    "            \"name\": \"菜单click\", \n" +
                    "            \"key\": \"V1001_TODAY_MUSIC\"\n" +
                    "        }, \n" +
                    "        {\n" +
                    "            \"name\": \"菜单url\", \n" +
                    "            \"sub_button\": [\n" +
                    "                {\n" +
                    "                    \"type\": \"view\", \n" +
                    "                    \"name\": \"搜索\", \n" +
                    "                    \"url\": \"http://www.soso.com/\"\n" +
                    "                }, \n" +
                    "                {\n" +
                    "                    \"type\": \"click\", \n" +
                    "                    \"name\": \"赞一下我们\", \n" +
                    "                    \"key\": \"V1001_GOOD\"\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}");
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return new Result();
    }

    @RequestMapping("/query")
    @ResponseBody
    public Result queryMenu() {
        WxMpMenu wxMpMenu = null;
        try {
            wxMpMenu = wxMpService.getMenuService().menuGet();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return new Result(wxMpMenu);
    }
}
