package inflearn.advanced.app.v2;

import inflearn.advanced.trace.TraceId;
import inflearn.advanced.trace.TraceStatus;
import inflearn.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;
    // 상품 저장하는데 1초 걸림
    public void save(TraceId traceId, String itemId){

        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId,"OrderRepositoryV1.save()");

            // 저장 로직
            if (itemId.equals("ex")){
                throw new IllegalStateException("exception");
            }
            sleep(1000);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 한다
        }
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
