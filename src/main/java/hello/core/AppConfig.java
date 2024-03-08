package hello.core;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {//외부에서 구현체의 생성과 관리를 담당. 수정사항이 발생할 때 여기만 수정하면 됨
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());//생성자 주입
    }
    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
    }
}
