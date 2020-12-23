package codemerger.transactional.hib.service;

import codemerger.transactional.hib.TestApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

/**
 * Organisation: Codemerger Ldt.
 * Project: jpa
 * Package: codemerger.transactional.hib.service
 * Date: 21.12.2020
 *
 * @author: Michael Bielang, b137ang@codemerger.com.
 * @version: java version "14" 2020-03-17
 */

@SpringBootTest(classes = TestApplication.class)
// alternative: @TestPropertySource(locations = "classpath:test.properties")
@TestPropertySource(
        properties = {
                "spring.datasource.initialization-mode=always",
                "spring.jpa.hibernate.ddl-auto=validate"})
@Transactional
class DataManagerServiceTest {

    // TODO - michael.bielang: 22.12.2020
}
