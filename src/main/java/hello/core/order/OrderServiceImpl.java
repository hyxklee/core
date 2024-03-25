package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();//DIP 위반 코드

    private final MemberRepository memberRepository;//회원 조회를 통해 주문을 만들기 때문에 의존
    private final DiscountPolicy discountPolicy;//구현체에 의존하지 않도록 변경
    //할인 정책을 적용해야하기 때문에 의존

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,  @MainDiscountPolicy DiscountPolicy DiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = DiscountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //bugTest 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }


}
