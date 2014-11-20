package ua.eshepelyuk.blog.nashorn

import org.intellij.lang.annotations.Language

class MathUtilsTest extends Jasmine2Specification {
  //language="JavaScript 1.8"
  static def SPEC = """
  loadFromClassPath("/js/mathUtils.js");
  describe("suite 1", function() {
    it("should pass", function() {
      expect(add(1, 2)).toBe(3);
    });
    it("should fail", function() {
      expect(add(1, 2)).toBe(3);
      expect(add(1, 2)).toBe(0);
    });
    xit("should be ignored", function() {
      expect(add(1, 2)).toBe(3);
    });
  })
"""
}
