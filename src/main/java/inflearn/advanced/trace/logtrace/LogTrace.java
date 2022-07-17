package inflearn.advanced.trace.logtrace;

import inflearn.advanced.trace.TraceStatus;

/**
 * LogTrace 인터페이스에는 로그 추적기를 위한 최소한의 기능인 begin, end, exeception 를 정의했다.
 * */
public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
