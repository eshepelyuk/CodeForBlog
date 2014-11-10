package ua.eshepelyuk.blog.nashorn

class JavaScriptTest extends Jasmine2Specification {
    static def SPEC = """
describe("suite 1", function() {
  it("success spec", function() {
      expect(1).toBe(1);
  })
  it("failed spec", function() {
      expect(1).toBe(1);
      expect(1).toBe(2);
  })
})

describe("suite 2", function() {
  xit("ignored spec", function() {
      expect(1).toBe(1);
  })
})

xdescribe("suite 3", function() {
  it("spec31", function() {
      expect(1).toBe(1);
  })
})
"""
}
