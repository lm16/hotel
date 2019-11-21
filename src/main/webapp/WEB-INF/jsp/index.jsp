<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/json/orderForm/finishOrderForm" method="post">
    订单名称：<input type="text" name="WIDsubject" required><br/>
    付款金额：<input type="text" name="foMonetary" required><br/>
    foDescription：<input type="text" name="foDescription"><br/>
    <input type="submit" value="下单"> <input type="reset" value="重置">
</form>

</body>
</html>
