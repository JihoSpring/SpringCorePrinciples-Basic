package hello.core.scan;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

class AutoAppConfigTest {

    ApplicationContext context = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    @Test
    void should_work_component_scan() {
        MemberService service = context.getBean(MemberService.class);
        assertThat(service).isInstanceOf(MemberServiceImpl.class);
    }

}
