package com.catkatpowered.katserver.event.interfaces;

import com.catkatpowered.katserver.event.EventPriority;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@SuppressWarnings("unused")
public @interface EventHandler {
  EventPriority priority() default EventPriority.NORMAL;

  boolean ignoreCancelled() default false;
}
