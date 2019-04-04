package com.indi.design.adapter;

/**
 * Created by Administrator on 2019/3/22.
 */

public class LoginAdapterTestX {
    public static void main(String[] args) {

    }
}

class SiginForThirdService extends SiginService{

}

class SiginService{
    ResultMsg regist(String username, String password){
        return new ResultMsg(200, "注册成功", new Member());
    }
    ResultMsg login(String username, String password){
        return null;
    }
}

class ResultMsg{
    private int code;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public ResultMsg(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
