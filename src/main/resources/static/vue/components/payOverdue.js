/*默认主页*/
define(function(require, exports, module) {
    var payOverdue = require("templates/payOverdue.html");
    var VueComponent = Vue.extend({
        template: payOverdue,
    });
    module.exports = VueComponent;
});