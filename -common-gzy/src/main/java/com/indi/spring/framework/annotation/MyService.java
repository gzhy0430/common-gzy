package com.indi.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2019/3/27.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyService {
    String value() default "";
}
