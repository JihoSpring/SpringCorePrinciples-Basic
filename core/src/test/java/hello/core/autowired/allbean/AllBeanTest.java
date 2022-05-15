package hello.core.autowired.allbean;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;

class AllBeanTest {

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        public DiscountService(Map<String,DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + this.policyMap);
            System.out.println("policyList = " + this.policyList);
        }

        public int discount(Member member, int price, String policyName) {
            return policyMap.get(policyName).discount(member, price);
        }
    }

    ApplicationContext context;

    @Test
    void findAllBean() {
        context = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService service = context.getBean(DiscountService.class);
        Member member = new Member(1L, "홍길동", Grade.VIP);
        int fixedDiscountPrice = service.discount(member, 20000, "fixDiscountPolicy");
        int ratedDiscountPrice = service.discount(member, 20000, "rateDiscountPolicy");
        assertThat(fixedDiscountPrice).isEqualTo(1000);
        assertThat(ratedDiscountPrice).isEqualTo(2000);
    }

}
