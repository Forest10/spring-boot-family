define(function (require, exports, module) {

    var payEntry = require("components/payEntry.js");
    var payAbnormal = require("components/payAbnormal.js");
    var payOverdue = require("components/payOverdue.js");
    var paySuccess = require("components/paySuccess.js");

    // 路由器需要一个根组件。
    var App = Vue.extend({});
    Vue.http.options.emulateJSON = true;
    Vue.http.options.headers = {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    };

    var routes = [
        {
            path: '/wxpay/:id',
            meta: {auth: false},
            component: payEntry
        },
        {
            path: '/pay-overdue',
            meta: {auth: false},
            component: payOverdue
        },
        {
            path: '/pay-success',
            meta: {auth: false},
            component: paySuccess
        },
        {
            path: '/pay-abnormal',
            meta: {auth: false},
            component: payAbnormal
        }
    ];
    routes.push({
        path: '*',
        redirect: '/pay-abnormal'
    });

    var router = new VueRouter({
        mode: 'history',
        routes: routes,
        scrollBehavior: function (to, from, savedPosition) {
            return savedPosition || {x: 0, y: 0};
        }
    });

    new Vue({
        el: '#wxpay',
        router: router
    });
});
