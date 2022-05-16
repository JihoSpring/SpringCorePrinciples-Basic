package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderService;

class ConfigurationSingletonTest {

    static class ServiceA implements MemberService {
        private final MemberRepository repository;
        public ServiceA(MemberRepository repository) { this.repository = repository; }
        @Override public void join(Member member) {}
        @Override public Member findMember(Long memberId) { return null; }
        public MemberRepository getRepository() { return repository; }
    }

    static class ServiceB implements OrderService {
        private final MemberRepository repository;
        public ServiceB(MemberRepository repository) { this.repository = repository; }
        @Override public Order createOrder(Long memberId, String itemName, int itemPrice) { return null; }
        public MemberRepository getRepository() { return repository; }
    }

    @Configuration
    static class TestConfig {
        static int count;
        @Bean
        public MemberRepository memberRepository() {
            System.out.println("REPOSITORY CREATED " + (++count) + " TIMES");
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberService memberService() {
            return new ServiceA(memberRepository());
        }
        @Bean
        public OrderService orderService() {
            return new ServiceB(memberRepository());
        }
    }

    // @Configuration
    static class NonConfigured {
        static int count;
        @Bean
        public MemberRepository memberRepository() {
            System.out.println("REPOSITORY CREATED " + (++count) + " TIMES");
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberService memberService() {
            return new ServiceA(memberRepository());
        }
        @Bean
        public OrderService orderService() {
            return new ServiceB(memberRepository());
        }
    }

    ApplicationContext context;

    @Test
    void should_construct_same_repository() {
        context = new AnnotationConfigApplicationContext(TestConfig.class);

        MemberRepository memberRepository = context.getBean(MemberRepository.class);
        ServiceA memberService = context.getBean(ServiceA.class);
        ServiceB orderService = context.getBean(ServiceB.class);

        assertThat(memberRepository).isSameAs(memberService.getRepository());
        assertThat(memberRepository).isSameAs(orderService.getRepository());
    }

    @Test
    void should_not_construct_same_repository() {
        context = new AnnotationConfigApplicationContext(NonConfigured.class);

        MemberRepository memberRepository = context.getBean(MemberRepository.class);
        ServiceA memberService = context.getBean(ServiceA.class);
        ServiceB orderService = context.getBean(ServiceB.class);

        assertThat(memberRepository).isNotSameAs(memberService.getRepository());
        assertThat(memberRepository).isNotSameAs(orderService.getRepository());
    }

}
