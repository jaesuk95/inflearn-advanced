package inflearn.advanced.trace.template;

import inflearn.advanced.trace.strategy.code.strategy.ContextV1;
import inflearn.advanced.trace.strategy.code.strategy.StrategyLogic1;
import inflearn.advanced.trace.strategy.code.strategy.StrategyLogic2;
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
     * 전략 패턴 사용
     */

    @Test
    void strategyV1(){
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }


}
