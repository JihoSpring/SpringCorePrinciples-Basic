package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemeberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemeberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemeberRepository(), new FixDiscountPolicy());
    }

}
