package codemerger.transactional.hib.demoexecutor;

import codemerger.transactional.hib.components.ComponentOne;
import codemerger.transactional.hib.components.ComponentTwo;
import codemerger.transactional.hib.events.TriggerIsolationDemoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa
 * Package: codemerger.transactional.hib.demoexecutor
 * Date: 22.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@Component
public class IsolationDemoExecutor implements ApplicationListener<TriggerIsolationDemoEvent> {

    @Autowired
    private ComponentOne componentOne;

    @Autowired
    private ComponentTwo componentTwo;

    @Override
    public void onApplicationEvent(TriggerIsolationDemoEvent event) {
        componentOne.startTransactionOne();
        componentTwo.startTransactionTwo();
    }
}
