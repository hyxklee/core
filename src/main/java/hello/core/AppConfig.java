package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {//외부에서 구현체의 생성과 관리를 담당. 수정사항이 발생할 때 여기만 수정하면 됨
    @Bean
    public MemberRepository memberRepository(){//코드의 명확성이 분명함
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }


    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
//      return new FixDiscountPolicy();
    }

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());//생성자 주입
    }

    @Bean
    public OrderService orderService(){

        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
