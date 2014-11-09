jasmineRequire.html = function(j$) {
    j$.ResultsNode = {};
    j$.HtmlReporter = new function() {
        function HtmlReporter(options) {
            this.initialize = function() {
            }
            return this;
        }
        return HtmlReporter;
    };
    j$.QueryString = new function() {
        function QueryString(options) {
            this.setParam = function(key, value) {
            };
            this.getParam = function(key) {
            };
            return this;
        }
        return QueryString;
    };
    j$.HtmlSpecFilter = new function() {
        function HtmlSpecFilter(options) {
            this.matches = function(specName) {
                return true;
            };
            return this;
        }
        return HtmlSpecFilter;
    };
};

setTimeout = function (fn, delay) {
    fn();
    return 0;
};

clearInterval = clearTimeout = function (id) {};

setInterval = function (fn, delay) {};
