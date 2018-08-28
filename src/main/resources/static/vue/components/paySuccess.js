/*默认主页*/
define(function(require, exports, module) {
    var paySuccess = require("templates/paySuccess.html");
    var VueComponent = Vue.extend({
        template: paySuccess,
    });
    module.exports = VueComponent;
});