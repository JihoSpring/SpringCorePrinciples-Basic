package hello.core.beanfind;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

class ApplicationContextFindSameTypeBeansTest {

    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository alpha() {
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository beta() {
            return new MemoryMemberRepository();
        }
    }

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    void should_throw_exception_when_find_duplicated_bean() {
        assertThrows(NoUniqueBeanDefinitionException.class,
            () -> context.getBean(MemberRepository.class)
        );
    }

    @Test
    void should_define_name_to_beans() {
        assertThat(context.getBean("alpha", MemberRepository.class)).isInstanceOf(MemberRepository.class);
        assertThat(context.getBean("beta", MemberRepository.class)).isInstanceOf(MemberRepository.class);
    }

    @Test
    void find_all_beans_of_same_type() {
        Map<String, MemberRepository> beans = context.getBeansOfType(MemberRepository.class);
        beans.forEach((name, bean) -> System.out.println("name: " + name + " / bean: " + bean));
        assertThat(beans.size()).isEqualTo(2);
    }

}
