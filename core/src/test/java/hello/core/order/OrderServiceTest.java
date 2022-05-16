package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        // given
        Long memberId = 1L;
        Member member = new Member(memberId, "alpha", Grade.VIP);
        memberService.join(member);
        // when
        Order order = orderService.createOrder(memberId, "itemName", 10000);
        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        Assertions.assertThat(order.calculatedPrice()).isEqualTo(9000);
    }

}
