package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

@Configuration
@ComponentScan( // 사실 이 어노테이션 자체가 @SpringBootApplication에 붙어있기 때문에 사실상 지정할 일은 거의 없다
    // 탐색 시작 지점을 지정할 수 있음
    // basePackages = { "hello.core.member", "hello.core.order" },
    basePackages = "hello.core", // default - 현재 configuration 클래스부터 스캔함
    // 특정 클래스 위치의 패키지부터 스캔할 수 있음
    basePackageClasses = AutoAppConfig.class, // default - 현재 configuration 클래스부터 스캔함
    // 수동으로 설정한 AppConfig.java를 제외하기 위함
    // 또한 Test에서 생성한 임시 Configuration 클래스도 제외
    // 예제를 안전하게 하기 위해 충돌을 방지하는 부분
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 수동으로 빈을 등록할 경우 자동으로 생성된 Bean을 오버라이딩해서 수동 Bean이 우선권을 가진다.
    // 그러나 복잡한 설정 속에서 찾기 어려운 애매한 버그가 될 수 있기 때문에 테스트 말고 스프링 부트 실행 시에는 에러가 발생한다.
    // 하지만 다음 설정을 application.properties에 추가하면 수동으로 설정된 Bean을 오버라이딩할 수 있다.
    // `spring.main.allow-bean-definition-overriding=true` 기본값은 false이기 때문에 에러가 발생함
    @Bean("memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
