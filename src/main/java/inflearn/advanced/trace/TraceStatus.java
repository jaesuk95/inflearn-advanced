package inflearn.advanced.trace;

public class TraceStatus {

    private TraceId traceId;    // 내부에 트랜잭션 아이디 와 레벨을 가지고 있다
    private Long startTimeMs;   // 로그 시작시간이다. 로그 종료시 이 시작 시간을 기준으로 시작~ 종료까지 전체 수행 시간을 구할 수 있다
    private String message;     // 시작시 사용한 메시지이다. 이후 로그 종료시에도 이 메시지를 사용해서 출력한다

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) { // 를 사용해서 실제 로그를 생성하고 처리하는 기능
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
