package com.indi.spring.use.action;

import com.indi.spring.framework.annotation.MyAutowired;
import com.indi.spring.framework.annotation.MyController;
import com.indi.spring.framework.annotation.MyRequestMapping;
import com.indi.spring.framework.annotation.MyRequestParam;
import com.indi.spring.use.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2019/3/27.
 * 虽然用法一样，但是没有功能
 */
@MyController
@MyRequestMapping("/demo")
public class DemoAction {
    @MyAutowired
    private IDemoService demoService;

//    localhost:8080/-common-gzy/demo/dosomething
    @MyRequestMapping("/dosomething")
    public void dosomething(HttpServletRequest req, HttpServletResponse res,
                            @MyRequestParam("name") String name){
        System.out.println(">>>>>>>>>>>>>DemoAction.dosomething");
        demoService.dosomething();
    }
}
