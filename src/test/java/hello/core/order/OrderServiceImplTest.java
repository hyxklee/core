package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "memberA", Grade.VIP));


        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());//생성자 호출 시 파라미터가 생략됨.
        Order order = orderService.createOrder(1L, "memberA", 10000);//NullPointerException
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}