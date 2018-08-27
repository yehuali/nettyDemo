package org.netty.SPI.dubbo;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Adaptive {
    String[] value() default {};
}
