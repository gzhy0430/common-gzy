package com.indi.design.proxy.homework.myprxy;

import java.lang.reflect.Method;

public interface MyInvocationhandler{
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}