package ua.eshepelyuk

import org.slf4j.Logger

class GraylogTest {
    static def execute(Logger log, Integer num) {
        log.warn("started logging cnt: $num")
        num.times {
            try {
                log.debug("logging #$num")
                throw new RuntimeException("runtime exception")
            } catch (Exception e) {
                log.error("logging error #$num", e)
            }
        }
        Thread.sleep(10000)
        log.info("finished logging cnt: $num")
    }
}