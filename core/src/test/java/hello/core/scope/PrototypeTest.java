package hello.core.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

class PrototypeTest {

    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE) // @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("prototype bean은 찾을 때마다 새롭게 생성");
            System.out.println("SingletonTest.SingletonBean.init()");
        }
        @PreDestroy
        public void destroy() {
            System.out.println("Spring Bean LifeCylce을 통해 호출되지 않음");
        }
    }

    ConfigurableApplicationContext context;

    @Test
    void should_different_prototype_beans() {
        context = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("Find Bean 1...");
        PrototypeBean bean1 = context.getBean(PrototypeBean.class);
        System.out.println("Find Bean 2...");
        PrototypeBean bean2 = context.getBean(PrototypeBean.class);
        assertThat(bean1).isNotSameAs(bean2);
        context.close();
    }
}
