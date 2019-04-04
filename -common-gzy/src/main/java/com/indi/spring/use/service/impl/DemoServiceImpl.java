package com.indi.spring.use.service.impl;

import com.indi.spring.framework.annotation.MyService;
import com.indi.spring.use.service.IDemoService;

/**
 * Created by Administrator on 2019/3/27.
 */
@MyService
public class DemoServiceImpl implements IDemoService {
    @Override
    public void dosomething() {
        System.out.println(">>>>>>>>>DemoServiceImpl.dosomething");
    }
}
