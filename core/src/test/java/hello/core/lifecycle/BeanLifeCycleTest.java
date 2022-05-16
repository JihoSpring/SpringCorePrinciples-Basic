package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class BeanLifeCycleTest {

    @Configuration
    static class Config {
        @Bean
        NetworkClient networkClient() {
            NetworkClient client = new NetworkClient();
            client.setUrl("http://localhost:8080/network");
            return client;
        }
        @Bean
        NetworkClient networkClientA() {
            NetworkClient client = new NetworkClientA();
            client.setUrl("http://localhost:8080/AAA");
            return client;
        }
        @Bean(initMethod = "init", destroyMethod = "close")
        NetworkClient networkClientB() {
            NetworkClient client = new NetworkClientB();
            client.setUrl("http://localhost:8080/BBB");
            return client;
        }
        @Bean
        NetworkClient networkClientC() {
            NetworkClient client = new NetworkClientC();
            client.setUrl("http://localhost:8080/CCC");
            return client;
        }
    }

    static class NetworkClientA extends NetworkClient implements InitializingBean, DisposableBean {
        @Override
        public void afterPropertiesSet() throws Exception {
            System.out.println("NetworkClientA.afterPropertiesSet()");
            connect();
            call("initializing");
        }
        @Override
        public void destroy() throws Exception {
            System.out.println("NetworkClientA.destroy()");
            disconnect();
        }
    }

    static class NetworkClientB extends NetworkClient {
        public void init() {
            System.out.println("NetworkClientB.init()");
            connect();
            call("initializing");
        }
        public void close() {
            System.out.println("NetworkClientB.close()");
            disconnect();
        }
    }

    static class NetworkClientC extends NetworkClient {
        @PostConstruct
        public void init() {
            System.out.println("NetworkClientC.init()");
            connect();
            call("initializing");
        }
        @PreDestroy
        public void close() {
            System.out.println("NetworkClientC.close()");
            disconnect();
        }
    }

    ConfigurableApplicationContext context;

    @Test
    void testLifeCycleOfBeanByImplementInterface() {
        context = new AnnotationConfigApplicationContext(Config.class);
        context.getBean(NetworkClientA.class);
        context.close();
    }

    @Test
    void testLifeCycleOfBeanByInstanceMethod() {
        context = new AnnotationConfigApplicationContext(Config.class);
        context.getBean(NetworkClientB.class);
        context.close();
    }

    @Test
    void testLifeCycleOfBeanByAnnotation() {
        context = new AnnotationConfigApplicationContext(Config.class);
        context.getBean(NetworkClientC.class);
        context.close();
    }

}
