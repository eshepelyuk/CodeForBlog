package ua.eshepelyuk.blog.nashorn

class JavaScriptTest extends Jasmine2Specification {
    static def SPEC = """

load(__scriptBaseDir__ + "/math.js");

describe("suite 1", function() {
  it("success spec", function() {
      expect(add(1, 2)).toBe(3);
  })
  it("failed spec", function() {
      expect(add(1, 2)).toBe(3);
      expect(add(1, 2)).toBe(0);
  })
})

describe("suite 2", function() {
  xit("ignored spec", function() {
      expect(add(1, 2)).toBe(3);
  })
})

xdescribe("suite 3", function() {
  it("spec31", function() {
      expect(add(1, 2)).toBe(3);
  })
})
"""
}
