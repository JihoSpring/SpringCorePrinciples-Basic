package hello.core.discount;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    void should_apply_10_percent_discount_to_VIPs() {
        // given
        Member member = new Member(1L, "VIP", Grade.VIP);
        // when
        int discountedPrice = discountPolicy.discount(member, 10_000);
        // then
        assertThat(discountedPrice).isEqualTo(1_000);
    }
    
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void should_not_apply_discount_to_BASICs() {
        // given
        Member member = new Member(2L, "BASIC", Grade.BASIC);
        // when
        int discountedPrice = discountPolicy.discount(member, 10_000);
        // then
        assertThat(discountedPrice).isEqualTo(0);
    }

}
