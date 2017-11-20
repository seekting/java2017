package com.seekting.libanno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/11/20.
 */


@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface MyAnno {
    int layoutId() default 0;
    int viewType() default 0;
    String viewHolder();
}

