package codemerger.transactional.hib.events;

import org.springframework.context.ApplicationEvent;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa
 * Package: codemerger.transactional.hib.demoexecutor
 * Date: 22.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

public class TriggerIsolationDemoEvent extends ApplicationEvent {

    public TriggerIsolationDemoEvent(Object source) {
        super(source);
    }
}
