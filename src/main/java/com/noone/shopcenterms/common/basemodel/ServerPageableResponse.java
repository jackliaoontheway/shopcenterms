package com.noone.shopcenterms.common.basemodel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ServerPageableResponse<T> extends BaseResponse<T>
{
    private String nonceToken;
    
    private Long totalCount;
}
