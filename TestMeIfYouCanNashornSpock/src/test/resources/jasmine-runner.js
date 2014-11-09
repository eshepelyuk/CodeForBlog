var window = this;

load(__scriptBaseDir__ + "/jasmine.js");
load(__scriptBaseDir__ + "/jasmine-html-stub.js");
load(__scriptBaseDir__ + "/boot.js");
load({script: __jasmineSpec__, name: __jasmineSpecName__});

onload();

jsApiReporter.specs();