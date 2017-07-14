package com.mvp.base;

/**
 * 作者：李飞 on 2017/6/21 20:06
 * 类的用途： base Bean
 */

public class BaseBean<T> {

    private int code;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
