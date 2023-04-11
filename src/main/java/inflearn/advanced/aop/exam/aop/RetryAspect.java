package inflearn.advanced.aop.exam.aop;

import inflearn.advanced.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspect {

    // Around 언제 내가 다시 재시도할지
    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[Custom retry] {} args = {}", joinPoint.getSignature(), retry);

        int maxRetry = retry.value();
        Exception exceptionHolder = null;

        // maxTry 만큼 못하면 예외가 터진다.
        for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
            try {
                log.info("[Custom retry] try count ={}/{}", retryCount,maxRetry);
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
            }
        };

        throw exceptionHolder;
    }
}

