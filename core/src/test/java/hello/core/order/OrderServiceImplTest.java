package hello.core.order;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImplTest {

    @Test
    void testCreateOrder() {
        // given
        MemberRepository memberRepository = new MemoryMemberRepository();
        DiscountPolicy discountPolicy = new FixDiscountPolicy();
        OrderService service = new OrderServiceImpl(memberRepository, discountPolicy);
        // when
        memberRepository.save(new Member(1L, "홍길동", Grade.VIP));
        Order order = service.createOrder(1L, "itemName", 10000);
        // then
        assertThat(order.calculatedPrice()).isEqualTo(9000);
    }

}
