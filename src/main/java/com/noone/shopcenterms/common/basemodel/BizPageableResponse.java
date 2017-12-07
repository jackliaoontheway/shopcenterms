package com.noone.shopcenterms.common.basemodel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BizPageableResponse<T> extends BaseResponse<T>
{

	private Long totalCount;
	
    public ErrorInfo getErrorInfo()
    {
        return (getErrors() == null || getErrors().size() == 0) ? null : getErrors().get(0);
    }

}
