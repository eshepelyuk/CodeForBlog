package ua.eshepelyuk.blog.nashorn

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class Jasmine2Specification extends Specification {

    @Shared
    def jasmineReport

    @Shared
    def specFile

    def setupSpec() {
        def specText = getMetaClass().getMetaProperty("SPEC").getProperty(null)

        specFile = File.createTempFile("jasmineSpec", ".js")
        specFile.withWriter { it << specText }

        jasmineReport = JavaScriptRunner.run("/jasmine-runner.js", ["__script": specFile.canonicalPath])
    }

    def cleanupSpec() {
        specFile.delete()
    }

    @Unroll
    def '#item.fullName'() {
        expect:
        assert item.status == "passed" || item.status == "pending" , item.failedExpectations[0]?.stack
        where:
        item << jasmineReport.collect {it.value}
    }
}
