package com.liqinrui.springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.liqinrui.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handleException(BlockException exception){
        return new CommonResult(444,"按客户自定义,global handleException ------------1");
    }

    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(4444,"按客户自定义,global handleException ===========2");
    }
}
