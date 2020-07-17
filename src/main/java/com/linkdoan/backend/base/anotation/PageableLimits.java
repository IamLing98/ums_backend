package com.linkdoan.backend.base.anotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface  PageableLimits {
    int maxSize() default Integer.MAX_VALUE;

    int minSize() default 0;
}
