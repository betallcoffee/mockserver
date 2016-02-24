package org.mockserver.web.controller.googleclient;

import org.junit.runner.RunWith;
import org.mockserver.configuration.RootConfiguration;
import org.mockserver.web.configuration.WebMvcConfiguration;
import org.mockserver.web.controller.BooksPageIntegrationTest;
import org.mockserver.web.controller.PropertyMockingApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author jamesdbloom
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(
                classes = {
                        RootConfiguration.class
                },
                initializers = PropertyMockingApplicationContextInitializer.class
        ),
        @ContextConfiguration(
                classes = {
                        WebMvcConfiguration.class
                }
        )
})
@ActiveProfiles(profiles = {"googleClient"})
public class BooksPageGoogleClientIntegrationTest extends BooksPageIntegrationTest {

}
