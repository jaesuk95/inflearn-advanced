package inflearn.advanced.trace.template;

import inflearn.advanced.trace.template.code.AbstractTemplate;
import inflearn.advanced.trace.template.code.SubClassLogic1;
import inflearn.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0(){
        logic1();
        logic2();
    }
    private void logic1() {
        long startTime = System.currentTimeMillis();
        // business logic start
        log.info("비즈니스 로직 1 실행");
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("result Time ={}", resultTime);
    }
    private void logic2() {
        long startTime = System.currentTimeMillis();
        // business logic start
        log.info("비즈니스 로직 2 실행");
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("result Time ={}", resultTime);
    }

    /**
     * 템플릿 메서드 패턴 적용
     * */
    @Test
    void templateMethodV1(){
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }
}
