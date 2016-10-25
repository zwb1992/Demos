package com.zwb.volley.net;

/**
 * Created by zwb
 * Description 最终得到的结果
 * Date 16/10/22.
 */
public class NetEvent {
    private int code;
    private String msg;
    private Object what;
    private NetRequest.RESPONSE_STATUS status;
    private Object result;

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

    public Object getWhat() {
        return what;
    }

    public void setWhat(Object what) {
        this.what = what;
    }

    public boolean whatEquals(Object what){
        if(what == null){
            return false;
        }
        return this.what.equals(what);
    }
    public NetRequest.RESPONSE_STATUS getStatus() {
        return status;
    }

    public void setStatus(NetRequest.RESPONSE_STATUS status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
