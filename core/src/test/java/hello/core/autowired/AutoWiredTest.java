package hello.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import hello.core.member.Member;

class AutoWiredTest {

    static class Bean {

        @Autowired(required = false)
        public void setNoBeanA(Member member) {
            // member는 Spring Container가 관리하는 빈이 아니므로 자동으로 주입받을 수 없다.
            // 하지만 required 옵션이 꺼져 있으므로 에러가 발생하지 않는다.
            System.out.println("setNoBeanA -> " + member);
        }

        @Autowired
        public void setNoBeanB(@Nullable Member member) {
            // required 옵션이 켜져있지만, Member가 Nullable이기 때문에 에러가 발생하지 않는다.
            System.out.println("setNoBeanB -> " + member);
        }

        @Autowired
        public void setNoBeanC(Optional<Member> member) {
            // required 옵션이 켜져있지만, Optional로 감싸졌기 때문에 Optional.empty가 입력되어 에러가 발생하지 않는다.
            System.out.println("setNoBeanC -> " + member);
        }

    }

    ApplicationContext context;

    @Test
    void should_run_Autowired_options() {
        context = new AnnotationConfigApplicationContext(Bean.class);
    }

}
