var window = this;

load("/d:/projects/CodeForBlog/TestMeIfYouCanNashornSpock/build/classes/main/jasmine-2.0.2/jasmine.js");
load("/d:/projects/CodeForBlog/TestMeIfYouCanNashornSpock/build/classes/main/jasmine-2.0.2/jasmine-html-stub.js");
load("/d:/projects/CodeForBlog/TestMeIfYouCanNashornSpock/build/classes/main/jasmine-2.0.2/boot.js");

print("@@@@" + __script);
load(__script);

try {
    onload();
} catch (e) {
    print("@@@@@@@@@@@@");
    print(e);
}

for each (var v in jsApiReporter.suites()) {
    print(JSON.stringify(v));
}
jsApiReporter.specs().forEach(function(it) print(JSON.stringify(it)));

jsApiReporter.specs();