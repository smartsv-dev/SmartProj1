<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>Login Page | Amaze UI Example</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="alternate icon" type="image/png" href="${pageContext.request.contextPath}/image/favicon.png">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/amazeui.min.css"/>
  <style>
    .header {
      text-align: center;
    }
    .header h1 {
      font-size: 200%;
      color: #333;
      margin-top: 30px;
    }
    .header p {
      font-size: 14px;
    }
  </style>
</head>
<body>
<div class="header" style="height:150px">
<!--
  <div class="am-g">
    <h1>Web ide</h1>
    <p>Integrated Development Environment<br/>代码编辑，代码生成，界面设计，调试，编译</p>
  </div>
  <hr />
-->
</div>
<div class="am-g">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
    <h3>登录</h3>
    <hr>
    <div class="am-btn-group" style="display: none">
      <a href="#" class="am-btn am-btn-secondary am-btn-sm"><i class="am-icon-github am-icon-sm"></i> Github</a>
      <a href="#" class="am-btn am-btn-success am-btn-sm"><i class="am-icon-google-plus-square am-icon-sm"></i> Google+</a>
      <a href="#" class="am-btn am-btn-primary am-btn-sm"><i class="am-icon-stack-overflow am-icon-sm"></i> stackOverflow</a>
    </div>
    <html:messages id="message" message="false">
      <p><bean:write name="message" ignore="true" /></p>
    </html:messages>
    <html:form styleId="mainForm" method="post" styleClass="am-form" action="/BA01S0101DSP.do">
      <label for="username">用户名:</label>
      <html:text property="username" styleId="username" />
      <br>
      <label for="password">密码:</label>
      <html:password property="password" styleId="password" />
      <br>
      <label for="remember-me">
        <input id="remember-me" type="checkbox">
        记住密码
      </label>
      <br />
      <div class="am-cf">
        <input type="submit" name="forward_login" value="登 录" class="am-btn am-btn-primary am-btn-sm am-fl">
        <input type="submit" name="forward_passwd" value="忘记密码 ^_^? " class="am-btn am-btn-default am-btn-sm am-fr">
      </div>
    </html:form>
    <hr>
    <p>© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
  </div>
</div>
</body>
</html>