package inflearn.advanced.aop.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;

@Slf4j
@Aspect
public class TraceAspect {

    @Before("@annotation(inflearn.advanced.aop.exam.annotation.Trace)")
    public void doTrace(JoinPoint joinpoint) {
        Object[] args = joinpoint.getArgs();
        log.info("[trace] {} args = {}", joinpoint.getSignature(), args);
    }
}
