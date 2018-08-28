<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Forest10 Test</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/css/pay-entry.css"></link>
</head>

<body>
<div class="wrapper" id="wxpay">

    <router-view></router-view>
</div>
<script src="/js/jquery-2.2.3.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="/vue/lib/sea.js"></script>
<script src="/vue/lib/vue.js"></script>
<script src="/vue/lib/vue-router.js"></script>
<script src="/vue/lib/vue-resource.js"></script>
<script src="/seaconfig"></script>

<script>
    var clientOrderId = '${orderId}';
</script>
</body>
</html>