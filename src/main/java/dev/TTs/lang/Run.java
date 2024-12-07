package dev.TTs.lang;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Run {
    String name();
}
