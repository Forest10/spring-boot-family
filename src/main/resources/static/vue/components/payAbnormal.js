/*默认主页*/
define(function(require, exports, module) {
    var payAbnormal = require("templates/payAbnormal.html");
    var VueComponent = Vue.extend({
        template: payAbnormal,
    });
    module.exports = VueComponent;
});