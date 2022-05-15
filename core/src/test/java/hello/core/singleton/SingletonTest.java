package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;

class SingletonTest {

    @Test
    void test_pure_java_dependency_injection_container() {
        AppConfig appConfig = new AppConfig();
        MemberService serviceA = appConfig.memberService();
        MemberService serviceB = appConfig.memberService();
        assertThat(serviceA).isNotSameAs(serviceB);
    }

    @Test
    void should_use_only_single_instance() {
        SingletonService serviceA = SingletonService.getInstance();
        SingletonService serviceB = SingletonService.getInstance();
        assertThat(serviceA).isSameAs(serviceB);
    }

    @Test
    void test_spring_singleton_container() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService serviceA = context.getBean(MemberService.class);
        MemberService serviceB = context.getBean(MemberService.class);
        assertThat(serviceA).isSameAs(serviceB);
    }

}
