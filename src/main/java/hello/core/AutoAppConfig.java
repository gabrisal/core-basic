package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 이 패키지 부터 스캔 시작 - 이 패키지 포함 하위 패키지 스캔(default: @ComponentScan 붙은 클래스 패키지 부터)
        // 보통 프로젝트 시작 루트 위치에 설정클래스를 두고, 디폴트 값을 이용한다.
        // 요즘 흔히 쓰이는 스프링부트의 @SpringBootApplication 안에 @ComponentScan 이 포함되어있고,
        // 이 클래스는 프로젝트 시작 루트에 위치해 있다.
        basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
