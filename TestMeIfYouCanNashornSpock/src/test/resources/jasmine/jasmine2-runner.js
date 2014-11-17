var window = this;

load(__scriptBaseDir__ + "/jasmine/jasmine-2.1.2/jasmine.js");
load(__scriptBaseDir__ + "/jasmine/jasmine-html-stub.js");
load(__scriptBaseDir__ + "/jasmine/jasmine-2.1.2/boot.js");
load({script: __jasmineSpec__, name: __jasmineSpecName__});

onload();

jsApiReporter.specs();