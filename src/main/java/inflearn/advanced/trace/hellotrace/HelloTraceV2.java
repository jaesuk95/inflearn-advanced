package inflearn.advanced.trace.hellotrace;

import inflearn.advanced.trace.TraceId;
import inflearn.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV2 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    public TraceStatus begin(String message){
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}",traceId.getId(),addSpace(START_PREFIX,traceId.getLevel()),message);
        return new TraceStatus(traceId,startTimeMs,message);
    }

    // V2 에서 추가
    // 기존 traceId 에서 createNextId 를 통해 다음 아이디를 구한다. 
    public TraceStatus beginSync(TraceId beforeTraceId, String message){
        TraceId nextId = beforeTraceId.createNextId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}",nextId.getId(),addSpace(START_PREFIX,nextId.getLevel()),message);
        return new TraceStatus(nextId,startTimeMs,message);
    }


    public void end(TraceStatus status){
        complete(status,null);
    }
    public void exception(TraceStatus status, Exception e){
        complete(status,e);
    }

    private void complete(TraceStatus status, Exception e){
        Long stopTimeMS = System.currentTimeMillis();
        long resultTimeMs = stopTimeMS - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null){
            log.info("[{}] {}{} time={}ms",traceId.getId(),addSpace(COMPLETE_PREFIX,
                    traceId.getLevel()),status.getMessage(),resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}",traceId.getId(),addSpace(EX_PREFIX,
                    traceId.getLevel()),status.getMessage(),resultTimeMs,e.toString());
        }
    }

    // level = 0
    // level = 1 |-->
    // level = 2 |   |-->

    private static String addSpace(String prefix, int level){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
             sb.append((i == level - 1) ? "|" + prefix : "|  ");
        }
        return sb.toString();
    }
}
