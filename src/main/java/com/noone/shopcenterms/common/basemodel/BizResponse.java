package com.noone.shopcenterms.common.basemodel;

public class BizResponse<T> extends BaseResponse<T>
{

    public ErrorInfo getErrorInfo()
    {
        return (getErrors() == null || getErrors().size() == 0) ? null : getErrors().get(0);
    }

}
