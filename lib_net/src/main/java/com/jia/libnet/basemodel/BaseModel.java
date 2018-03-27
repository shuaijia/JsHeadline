package com.jia.libnet.basemodel;

import java.io.Serializable;

/**
 * Description:
 * Created by jia on 2017/12/20.
 * 人之所以能，是相信能
 */
public class BaseModel implements Serializable{

    private String errorCode;

    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
