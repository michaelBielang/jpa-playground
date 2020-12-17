package codemerger.transactional.hib.demoexecutor;

import codemerger.transactional.hib.events.TriggerStateDemoEvent;
import codemerger.transactional.hib.events.TriggerTransactionalDemoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Organisation: Codemerger Ldt.
 * Project: JPA-Practising
 * Package: codemerger.transactional.hib.demoexecutor
 * Date: 17.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Component
public class DemoExecutor {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @EventListener(ApplicationReadyEvent.class)
    public void triggerDemoExecutors() {
        applicationEventPublisher.publishEvent(new TriggerStateDemoEvent(this));
        applicationEventPublisher.publishEvent(new TriggerTransactionalDemoEvent(this));
    }
}
