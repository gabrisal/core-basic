package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    /**
     * 스프링 없는 순수한 DI컨테이너인 AppConfig는 요청을 할 때 마다 객체를 새로 생성한다.
     * 객체가 생성되고 소멸되고 생성되고 소멸되고... => 메모리 낭비가 심하다!
     * 
     * 해결 방안은 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다. => 싱글톤 패턴
     */
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    /**
     * 싱글톤 패턴을 적용하면,
     * 요청이 올 때마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유헤서 효율적으로 사용할 수 있다.
     *
     * [문제점]
     * 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
     * 클라이언트가 구체 클래스에 의존해서 DIP를 위반한다.
     * OCP를 위반할 가능성이 높다.
     * 테스트하기가 어렵다.
     * 내부 속성을 변경하거나 초기화 하기 어렵다.
     * 자식 클래스를 만들기 어렵다.
     * 유연성이 떨어진다.
     * 안티패턴으로 불리기도 한다.
     */
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singleTonService1 = " + singletonService1);
        System.out.println("singleTonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
        // sameAs = equals
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
//        AppConfig appConfig = new AppConfig();
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
