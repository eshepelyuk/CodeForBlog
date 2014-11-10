package ua.eshepelyuk.blog.nashorn

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class Jasmine2Specification extends Specification {

  @Shared def jasmineResults

  def setupSpec() {
    def scriptParams = [
        "__jasmineSpec__"    : getMetaClass().getMetaProperty("SPEC").getProperty(null),
        "__jasmineSpecName__": "${this.class.simpleName}.groovy",
        "__scriptBaseDir__"  : Jasmine2Specification.class.getResource("/jasmine-2.0.2").toExternalForm()
    ]
    jasmineResults = JavaScriptRunner.run("/jasmine2-runner.js", scriptParams)
  }

  def isPassed(def specRes) {specRes.status == "passed" || specRes.status == "pending"}

  def specErrorMsg(def specResult) {
    specResult.failedExpectations.collect { it.value }
      .collect {it.stack}.join("\n\n\n")
  }

  @Unroll def '#specName'() {
    expect:
      assert isPassed(item), specErrorMsg(item)
    where:
      item << jasmineResults.collect { it.value }
      specName = (item.status != "pending" ? item.fullName : "IGNORED: $item.fullName")
  }
}
