package hello.core.beanfind;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;

class ApplicationContextFindExtendBeansTest {

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    void should_throw_exception_when_find_by_parent_type() {
        assertThrows(
            NoUniqueBeanDefinitionException.class,
            () -> context.getBean(DiscountPolicy.class));
    }

    @Test
    void should_find_by_name_when_were_duplicated_type() {
        assertThat(context.getBean("rateDiscountPolicy", DiscountPolicy.class))
            .isInstanceOf(RateDiscountPolicy.class);
        assertThat(context.getBean("fixDiscountPolicy", DiscountPolicy.class))
            .isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    @Deprecated
    void should_find_by_child_type() {
        assertThat(context.getBean(RateDiscountPolicy.class))
            .isInstanceOf(DiscountPolicy.class);
        assertThat(context.getBean(FixDiscountPolicy.class))
            .isInstanceOf(DiscountPolicy.class);
    }

    @Test
    void should_find_all_duplicated_beans() {
        Map<String, DiscountPolicy> beans = context.getBeansOfType(DiscountPolicy.class);
        beans.forEach((name, bean) -> System.out.println(name + " / " + bean));
        assertThat(beans.size()).isEqualTo(2);
    }

    @Test
    void find_by_Object_type() {
        context.getBeansOfType(Object.class)
            .forEach((name, bean) -> System.out.println(name + " / " + bean));
    }

}
