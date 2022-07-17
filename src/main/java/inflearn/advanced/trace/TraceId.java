package inflearn.advanced.trace;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TraceId {

    private String id;  // transactionId (http 의 트랜잭션 Id)
    private int level;


    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        // ab99316f-3cde-4d24 .... 생성된 UUID
        // 앞 8자기만 사용
        return UUID.randomUUID().toString().substring(0,8);
    }

    public TraceId createNextId(){
        return new TraceId(id,level + 1);
    }
    public TraceId createPreviousId(){
        return new TraceId(id,level - 1);
    }

    public boolean isFirstLevel(){
        return level == 0;
    }

}
