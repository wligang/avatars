package com.wlgdo.avatar.activiti.common;

import lombok.Data;

@Data
public class Resp {
    public Resp() {
        this.code = RespCode.SERVER_ERROR.code;
        this.msg = RespCode.SERVER_ERROR.msg;
    }

    ;

    public Resp(RespCode respCode, Object data) {
        this.code = respCode.code;
        this.msg = respCode.msg;
        this.data = data;
    }

    ;

    public Resp(RespCode respCode) {
        this.code = respCode.code;
        this.msg = respCode.msg;
    }

    ;

    public Resp(Object data) {
        this(RespCode.SUCCESS, data);
    }

    ;

    public Resp(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    String code;
    String msg;
    Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public enum RespCode {
        INVALIDATE_PARAMS("-010", "参数错误"),
        INVALIDATE_REQUEST("-020", "非法请求"),
        SERVER_ERROR("-1", "服务错误"),
        SUCCESS("0", "请求成功");

        RespCode(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        String code;
        String msg;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

    }
}
