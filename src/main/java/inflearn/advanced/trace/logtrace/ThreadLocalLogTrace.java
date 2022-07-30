package inflearn.advanced.trace.logtrace;

import inflearn.advanced.app.v3.DTO;
import inflearn.advanced.trace.TraceId;
import inflearn.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>(); // traceId 동기화, 동시성 이슈 발생
    private ThreadLocal<DTO> testDTO = new ThreadLocal<>();

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();      // creates a new transaction id or pushes to next level
        TraceId traceId = traceIdHolder.get();  // find the transaction Id
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}",traceId.getId(),
                addSpace(START_PREFIX,traceId.getLevel()),message);
        return new TraceStatus(traceId,startTimeMs,message);
    }

    private void syncTraceId(){
        TraceId traceId = traceIdHolder.get();
        if (traceId == null){
            traceIdHolder.set(new TraceId());
            testDTO.set(new DTO());
        } else {
            traceIdHolder.set(traceId.createNextId());
        }
    }

    @Override
    public void end(TraceStatus status) {
        complete(status,null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
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

        releaseTraceId();
    }

    private void releaseTraceId() {
        TraceId traceId = traceIdHolder.get();
        if (traceId.isFirstLevel()){
            traceIdHolder.remove();   // destroy
        } else {
            traceIdHolder.set(traceId.createPreviousId());
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
