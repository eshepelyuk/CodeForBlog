package ua.eshepelyuk.blog.nashorn

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class Jasmine2Specification extends Specification {

    @Shared def jasmineReport

    def setupSpec() {
        def specText = getMetaClass().getMetaProperty("SPEC").getProperty(null)
        jasmineReport = JavaScriptRunner.run("/jasmine-runner.js"
                , ["__jasmineSpec__": specText, "__jasmineSpecName__": "${this.class.simpleName}.groovy",
                   "__scriptBaseDir__": Jasmine2Specification.class.getResource("/jasmine-2.0.2").toExternalForm()])
    }

    @Unroll def '#specName'() {
        expect:
        assert item.status == "passed" || item.status == "pending", item.failedExpectations.collect {it.value}.collect{it.stack}.join("\n\n\n")
        where:
        item << jasmineReport.collect { it.value }
        specName =(item.status != "pending" ? item.fullName : "IGNORED: $item.fullName")
    }
}
