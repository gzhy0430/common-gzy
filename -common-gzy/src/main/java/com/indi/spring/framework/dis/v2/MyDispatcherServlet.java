package com.indi.spring.framework.dis.v2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by Administrator on 2019/3/27.
 * 定位-加载-注册
 */
public class MyDispatcherServlet extends HttpServlet{

    /**
     * 晕车时候就不看源码啦
     * init方法干得初始化工作
     * init首先初始化所有相关的类，IOC容器，servletBean
     * IOC容器，
     */
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
