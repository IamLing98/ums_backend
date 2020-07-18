package com.linkdoan.backend.base.anotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface AdjHistory {
    String field() default "";
}

