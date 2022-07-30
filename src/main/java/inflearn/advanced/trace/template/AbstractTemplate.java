package inflearn.advanced.trace.template;

import inflearn.advanced.trace.TraceStatus;
import inflearn.advanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> { // T = 반환 타입 (어떤 것은 String 이고 어디는 void 이기 때문에)

    private final LogTrace trace;


    protected AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public T execute(String message){   // T -> 반환 타입이 String 이면 String 으로 맞춰준다
        TraceStatus status = null;
        try {
            status = trace.begin(message);  // 로그에 출력할 메시지 --> , 외부에서 파라미터로 전달 받는다
            // 로직 호출
            T result = call();
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 한다
        }
    }

    protected abstract T call();
}
