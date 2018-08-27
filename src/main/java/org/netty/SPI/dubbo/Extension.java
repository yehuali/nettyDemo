package org.netty.SPI.dubbo;

import java.lang.annotation.*;

@Deprecated
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Extension {

    /**
     * @deprecated
     */
    @Deprecated
    String value() default "";

}

