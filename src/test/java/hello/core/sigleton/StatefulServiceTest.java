package hello.core.sigleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulSingletonService() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //TreadA: A사용자 10000원 주문
        int userAprice = statefulService1.order("userA", 10000);

        //TreadB: B사용자 20000원 주문
        int userBprice = statefulService2.order("userB", 20000);

        //ThreadA:사용자 A가 주문 금액 조회
//        int price1 = statefulService1.getPrice();
        System.out.println("price = "+userAprice);//하나의 인스턴스를 공유하기 때문에 최신화된 값으로 출력됨
//        assertThat((statefulService1.getPrice())).isEqualTo(20000);
        assertThat(userAprice).isEqualTo(10000);
    }
        static class TestConfig {
            @Bean
            public StatefulService statefulService() {
                return new StatefulService();
            }
        }


}