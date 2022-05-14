package hello.core.beandefinition;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import hello.core.AppConfig;

class BeanDefinitionTest {

    AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext xmlContext = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    void print_definition_of_beans() {
        String[] names = annotationContext.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition definition = annotationContext.getBeanDefinition(name);

            if (definition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println(definition);
            }
        }
    }

    @Test
    void print_definition_of_beans_from_XML_context() {
        String[] names = xmlContext.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition definition = xmlContext.getBeanDefinition(name);

            if (definition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println(definition);
            }
        }
    }

}
