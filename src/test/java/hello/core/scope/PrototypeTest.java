package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(prototype.class);
//        AnnotationConfigApplicationContext의 ()안에 클래스를 작성하면 ComponentScan처럼 작동해서 자도으로 빈으로 등록하게됨

        System.out.println("find prototypeBean1");
        prototype bean1 = ac.getBean(prototype.class);
        System.out.println("find prototypeBean2");
        prototype bean2 = ac.getBean(prototype.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);

        assertThat(bean1).isNotSameAs(bean2);//빈이 개별로 생성되기 떄문에 다른 빈임
        ac.close();//책임을 클라이언트로 넘기기 떄문에 @PreDestroy는 작동하지 않음
        bean1.destroy();
        bean2.destroy();
    }

    @Scope("prototype")
    static class prototype{
        @PostConstruct
        public void init(){
            System.out.println("prototypeBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("prototypeBean.destroy");
        }
    }
}
