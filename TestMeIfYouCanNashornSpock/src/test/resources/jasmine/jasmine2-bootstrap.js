var loadFromClassPath = function(path) {
  load(Java.type("ua.eshepelyuk.blog.Jasmine2Specification").class.getResource(path).toExternalForm());
};

var window = this;

loadFromClassPath("/jasmine/jasmine-2.1.2/jasmine.js");
loadFromClassPath("/jasmine/jasmine2-html-stub.js");
loadFromClassPath("/jasmine/jasmine-2.1.2/boot.js");
load({script: __jasmineSpec__, name: __jasmineSpecName__});

onload();

jsApiReporter.specs();