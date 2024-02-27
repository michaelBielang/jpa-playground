package codemerger.transactional.hib.events;

import org.springframework.context.ApplicationEvent;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa
 * Package: codemerger.transactional.hib.events
 * Date: 22.12.2020
 *

 * @version: java version "14" 2020-03-17
 */
public class ComponentTwoEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public ComponentTwoEvent(Object source) {
        super(source);
    }
}
