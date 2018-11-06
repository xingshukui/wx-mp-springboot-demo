package com.xing.wx.mp.common;

/**
 * @author : xingshukui
 * @email : xingshukui@890media.com
 * @date : 2018/11/6 1:56 PM
 * @desc :
 */
public class Result {

    private Integer code;
    private String msg;
    private Object o;

    private static final Integer successCode = 200;
    private static final String successMsg = "success";
    public Result() {
        this.code = successCode;
        this.msg = successMsg;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Object o) {
        this.code = successCode;
        this.msg = successMsg;
        this.o = o;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }
}
