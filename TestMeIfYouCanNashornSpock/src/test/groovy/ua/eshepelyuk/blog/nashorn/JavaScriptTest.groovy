package ua.eshepelyuk.blog.nashorn

class JavaScriptTest extends Jasmine2Specification {
    static def SPEC = """
describe("suite1", function() {
    it("spec11", function() {
        print("spec11");
        expect(1).toBe(1);
    })
    it("spec12", function() {
        print("spec12");
        expect(1).toBe(2);
    })
})

describe("suite2", function() {
    it("spec21", function() {
        print("spec21");
        expect(1).toBe(1);
    })
    xit("spec22", function() {
        print("spec22");
        expect(1).toBe(1);
    })
})

xdescribe("suite3", function() {
    it("spec31", function() {
        print("spec31");
        expect(1).toBe(1);
    })
})

"""
}
