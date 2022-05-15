package hello.core.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

class SingletonWithPrototypeTestA {

    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("SingletonWithPrototypeTestA.PrototypeBean.init() " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy() - 직접 호출하지 않는 이상 자동으로 실행되지 않음");
        }

    }

    // @Scope("singleton") - default
    static class ClientBean {

        private final PrototypeBean prototypeBean;

        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int login() {
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

    }

    ConfigurableApplicationContext context;

    @Test
    void find_prototype() {
        context = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singleton_bean_has_a_prototype_bean() {
        context = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean client1 = context.getBean(ClientBean.class);
        assertThat(client1.login()).isEqualTo(1);
        ClientBean client2 = context.getBean(ClientBean.class);
        assertThat(client2.login()).isEqualTo(2);
    }

}
