package inflearn.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

    // ThreadLocal 사용법
    // 값 저장 : ThreadLocal.set(x)
    // 값 조회 : ThreadLocal.get(x)
    // 값 제거 : ThreadLocal.remove()
    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name){
        log.info("저장 name={} -> nameStore", name, nameStore.get());
        nameStore.set(name);
        sleep(1000);
        log.info("조회 nameStore={}",nameStore);
        return nameStore.get();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
