package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class StatefulServiceTest {

    @Configuration
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

    final ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    void should_stateful() {
        // given
        StatefulService serviceA = context.getBean(StatefulService.class);
        StatefulService serviceB = context.getBean(StatefulService.class);
        // when
        serviceA.order("A", 1000);
        serviceB.order("B", 2000);
        // then
        assertThat(serviceA.getPrice()).isEqualTo(2000);
    }

}
