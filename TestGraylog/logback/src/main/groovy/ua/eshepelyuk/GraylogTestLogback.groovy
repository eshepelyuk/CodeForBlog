package ua.eshepelyuk

import groovy.util.logging.Slf4j

@Slf4j
class GraylogTestLogback {
    public static void main(String[] args) {
        GraylogTest.execute(log, 5)
    }
}