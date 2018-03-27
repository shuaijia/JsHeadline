package com.jia.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 路由跳转界面  注解
 * Created by jia on 2018/1/10.
 * 人之所以能，是相信能
 */
@Target(ElementType.TYPE) //注解作用于类型（类，接口，注解，枚举）
@Retention(RetentionPolicy.RUNTIME) //运行时保留，运行中可以处理
@Documented // 生成javadoc文件
public @interface Action {

    String DEFAULT = "js";

    String value() default DEFAULT;

}
