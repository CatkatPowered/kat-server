package com.catkatpowered.katserver.event.interfaces;

import com.catkatpowered.katserver.event.EventPriority;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@SuppressWarnings("unused")
public @interface EventHandler {
    EventPriority priority() default EventPriority.NORMAL;
    boolean ignoreCancelled() default false;
}
