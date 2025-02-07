package dev.TTs.lang;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * If a class with @Provider is created in dev.TTs.* it will automatically be executed when running datagen.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value=TYPE_USE)
public @interface Provider {
    String name();
    boolean run() default true;
}
