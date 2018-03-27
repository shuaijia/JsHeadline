package com.jia.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 是否绑定 EventBus 注解
 * Created by jia on 2018/3/27.
 * 人之所以能，是相信能。
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindEventBus {
}
