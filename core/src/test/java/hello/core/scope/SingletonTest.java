package hello.core.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

class SingletonTest {

    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON) // default - @Scope("singleton")와 동일함
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonTest.SingletonBean.init()");
        }
        @PreDestroy
        public void destroy() {
            System.out.println("SingletonTest.SingletonBean.destroy()");
        }
    }

    ConfigurableApplicationContext context;

    @Test
    void should_exist_singleton_bean() {
        context = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean bean1 = context.getBean(SingletonBean.class);
        SingletonBean bean2 = context.getBean(SingletonBean.class);
        assertThat(bean1).isSameAs(bean2);
        context.close();
    }

}
