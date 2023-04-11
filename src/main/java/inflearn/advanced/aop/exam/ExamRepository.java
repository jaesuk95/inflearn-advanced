package inflearn.advanced.aop.exam;

import inflearn.advanced.aop.exam.annotation.Trace;
import org.springframework.stereotype.Repository;

@Repository
public class ExamRepository {
    private static int seq = 0;

    /**
     * 5번에 1번 실패하는 요청
     */
    @Trace
    public String save(String item) {
        seq++;
        if (seq % 5 == 0) {
            throw new IllegalStateException("5th try");
        }
        return "ok";
    }
}
