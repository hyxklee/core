package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;
    @BeforeEach//매 테스트 시작 전 돌아가는 코드
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    @DisplayName("OrderService의 createOrder 메서드 테스트")
    void createOrder(){
        long memberId = 1l;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        //order를 creteOrder로 생성하면 DiscountPolicy에 의해 1000원이 할인 금액으로 들어가서 order 인스턴스가 생성됨
        //그걸 테스트
    }
}
