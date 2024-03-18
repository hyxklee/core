package hello.core.sigleton;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();//스프링 컨테이너 없이 생성
        //1 . 조회: 호춣할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른지 확인
        System.out.println("memberService1: "+ memberService1);
        System.out.println("memberService2: "+memberService2);
        //각각 다른 인스턴스로 생성

        //m1 != m2면 통과
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();//구현 클래스에 의존하게
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1: " + singletonService1);
        System.out.println("singletonService2: " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
        //same as "=="
        //equals "자바의 equals 상속"
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
//        AppConfig appConfig = new AppConfig();
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);//스프링 컨테이너 사용
        //1 . 조회: 호춣할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 같은지 확인
        System.out.println("memberService1: "+ memberService1);
        System.out.println("memberService2: "+memberService2);
        //각각 다른 인스턴스로 생성

        //m1 == m2면 통과
        assertThat(memberService1).isSameAs(memberService2);
    }


}
