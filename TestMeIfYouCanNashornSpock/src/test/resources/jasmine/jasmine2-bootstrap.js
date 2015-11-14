var loadFromClassPath = function(path) {
  load(Java.type("ua.eshepelyuk.blog.Jasmine2Specification").class.getResource(path).toExternalForm());
};

var window = this;

loadFromClassPath("/META-INF/resources/webjars/jasmine/2.1.3/jasmine.js");
loadFromClassPath("/jasmine/jasmine2-html-stub.js");
loadFromClassPath("/META-INF/resources/webjars/jasmine/2.1.3/boot.js");
load({script: __jasmineSpec__, name: __jasmineSpecName__});

onload();

jsApiReporter.specs();