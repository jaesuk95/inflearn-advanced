package inflearn.advanced;

import inflearn.advanced.trace.logtrace.FieldLogTrace;
import inflearn.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    /**
     * Bean 이 자동으로 스프링 Bean 으로 등록이 된다 왜냐하면 Configuration 안에는 Component 가 있다
     * 그리고 @Bean 이 있으면 스프링 컨테이너가 자동으로 스프링 bean 으로 등록한다 그리고 싱글톤으로 등록이 된다
     * 싱글톤,, 하나만 인스턴스 등록이 된다
     * */
    @Bean
    public LogTrace logTrace(){
        return new FieldLogTrace();
    }
}
