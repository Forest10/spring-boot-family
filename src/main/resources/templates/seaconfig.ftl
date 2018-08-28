    var gMain = {
        version: "1.1.0",
    };

    seajs.config({
        base: "/vue",
        charset: 'utf-8',
        map: [

        ],
        paths: {}
    });
    $(function () {
        seajs.use(["src/wxpay-app.js"]);
    });
