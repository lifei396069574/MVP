package com.mvp.base;

/**
 * 作者：李飞 on 2017/6/21 20:06
 * 类的用途： base Bean
 */

public class BaseBean<T> {

    private String errMessage;
    private String msg;
    private String traceAsString;
    private int code;
    private int status;
    private T data;

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTraceAsString() {
        return traceAsString;
    }

    public void setTraceAsString(String traceAsString) {
        this.traceAsString = traceAsString;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "errMessage='" + errMessage + '\'' +
                ", msg='" + msg + '\'' +
                ", traceAsString='" + traceAsString + '\'' +
                ", code=" + code +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
