package ua.eshepelyuk.blog

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import ua.eshepelyuk.blog.JavaScriptRunner

class Jasmine2Specification extends Specification {
  @Shared def jasmineResults

  def setupSpec() {
    def scriptParams = [
        "__jasmineSpec__"    : getMetaClass().getMetaProperty("SPEC").getProperty(null),
        "__jasmineSpecName__": "${this.class.simpleName}.groovy"
    ]
    jasmineResults = JavaScriptRunner.run("/jasmine/jasmine2-bootstrap.js", scriptParams)
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
