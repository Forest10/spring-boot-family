define(function (require, exports, module) {
    // 注册组件，传入一个扩展的构造器
    var payEntry = require("templates/payEntry.html");

    var payEntryComponent = Vue.extend({
        template: payEntry,
        data: function () {
            return {
                viewInfo: [
                ],
                deptId: '',
                totalFee: '',
                outTradeNo: '',
                preConfig: '',
                define: null,
                require: null,
                readyConfig: '',
            };
        },
        beforeCreate: function () {
        },
        methods: {
            payTo: function () {
                var _self = this;
                var preConfigParams = {
                    orderId: clientOrderId,
                };
                var url = '/security/wxpay/preConfig';
                _self.$http.post(url,preConfigParams).then(function (response) {
                    var body = response.data;
                    if (body.status == 200) {
                        var data = body.data;
                        _self.preConfig = data;
                        wx.config({
                            debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                            appId: _self.preConfig.appId, // 必填，公众号的唯一标识
                            timestamp: _self.preConfig.timestamp, // 必填，生成签名的时间戳
                            nonceStr: _self.preConfig.nonceStr, // 必填，生成签名的随机串
                            signature: _self.preConfig.signature,// 必填，签名，见附录1
                            jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                        });

                        // config wx
                        wx.ready(function () {
                            console.info("good! start pay!");
                        });
                        // 异步回调之后调用
                        _self.pay();
                        wx.error(function (res) {
                            console.info('res', res)
                            // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
                        });
                    } else {
                        console.info(body);
                        alert(body.msg);
                    }
                });
            },

            pay: function () {
                var _self = this;
                var readyPayParams = {
                    orderId: clientOrderId,
                };
                var readyUrl = '/security/wxpay/pay';

                _self.$http.post(readyUrl, readyPayParams).then(function (response) {
                        var body = response.body;
                        if (body.status == 200) {
                            var data = body.data;
                            _self.readyConfig = data;
                            console.info(" _self.readyConfig.packageStr---->>>>"+ _self.readyConfig.packageStr);
                            wx.chooseWXPay({
                                timestamp: _self.readyConfig.timeStamp,
                                nonceStr: _self.readyConfig.nonceStr,
                                package: _self.readyConfig.packageStr,
                                signType: _self.readyConfig.signType, // 注意：新版支付接口使用 MD5 加密
                                paySign: _self.readyConfig.paySign,
                                success: function (res) {
                                    // 支付成功后的回调函数
                                    alert('res=' + res);
                                    console.info('res', res);
                                    alert(JSON.stringify(res));
                                    _self.$router.push({path: '/pay-success'});
                                },
                                cancel: function (res) {// 支付取消回调函数
                                    console.info('res', res);
                                    alert('cancel pay');
                                    alert(JSON.stringify(res));
                                },
                                fail: function (res) {// 支付失败回调函数
                                    _self.$router.push({path: '/pay-overdue'});
                                    alert('pay fail');
                                    alert(JSON.stringify(res));
                                }
                            });
                        } else {
                        }
                    }
                );
            },
        },

    });

    module.exports = payEntryComponent;
});