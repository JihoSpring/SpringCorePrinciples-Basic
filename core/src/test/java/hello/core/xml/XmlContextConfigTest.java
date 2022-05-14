package hello.core.xml;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

class XmlContextConfigTest {

    @Test
    void should_config_by_xml_context() {
        ApplicationContext context = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = context.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

}
