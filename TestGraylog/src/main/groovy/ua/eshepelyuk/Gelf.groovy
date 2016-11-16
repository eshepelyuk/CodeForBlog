package ua.eshepelyuk

import groovy.util.logging.Slf4j

@Slf4j
class Gelf {
    static def execute(def num) {
        try {
            log.info("start $num")
            throw new RuntimeException("runtime exception")
        } catch (Exception e) {
            log.error("execute error ($num)", e)
        }
    }

    public static void main(String[] args) {
        5.times { execute(it) }

        Thread.sleep(10000)
    }
}