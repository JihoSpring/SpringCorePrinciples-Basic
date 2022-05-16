package hello.core.beanfind;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void print_all_beans() {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            Object bean = context.getBean(name);
            System.out.println("name = " + name + " / bean = " + bean);
        }
    }

    @Test
    void print_application_beans() {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            BeanDefinition beanDefinition = context.getBeanDefinition(beanName);

            // BeanDefinition.ROLE_APPLICATION : 직접 생성한 빈
            // BeanDefinition.ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = context.getBean(beanName);
                System.out.println("name = " + beanName + " / bean = " + bean);
            }
        }
    }

}
