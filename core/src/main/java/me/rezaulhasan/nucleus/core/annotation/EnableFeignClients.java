package me.rezaulhasan.nucleus.core.annotation;

import java.lang.annotation.*;

/**
 * Date of creation 10-May-2020
 *
 * @author Md. Rezaul Hasan
 * @since 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Documented
@Inherited
public @interface EnableFeignClients {}
