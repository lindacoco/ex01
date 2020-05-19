<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>AdminLTE 2 | Dashboard</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.4 -->
    <link href="${pageContext.request.contextPath }/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath }/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="${pageContext.request.contextPath }/resources/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
  </head>
      <!-- jQuery 2.1.4 -->
    <script src="${pageContext.request.contextPath }/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    
  <body class="login-page">
     <div class="login-box"">
       <div class="login-logo">
          <a href="#"><B>코코리의 코코몽</B> PROJECT</a>
       </div>
       <div class="login-box-body">
          <p class="login-box-msg">Sign in to start your session</p>
          
          <form action="loginForm" method="post">
             <div class="form-group has-feedback">
                <input type="text" name ="userid" class="form-control" placeholder="User Id">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
               
             </div>
             <div class="form-group has-feedback">
                <input type="password" name ="userpw" class="form-control" placeholder="User pwd">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
               
             </div>
             <div class="row">
                <div class="col-xs-5">
                   <input type="submit" class="btn btn-primary btn-block btn-flat" value="Sign In">
                </div>
                <div class="col-xs-15">
                   <c:if test="${error != null }">
	                 <span class="error" style="width:200px;">${error }</span> 
	               </c:if>
                </div>
             </div>
          </form>
          <script>
             $("form input").change(function(){
            	 $(".error").css("display","none");
             })
          
          </script>
          
       </div>
     </div>

    <!-- Bootstrap 3.3.2 JS -->
    <script src="${pageContext.request.contextPath }/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- FastClick -->
    <script src='${pageContext.request.contextPath }/resources/plugins/fastclick/fastclick.min.js'></script>
    <!-- AdminLTE App -->
    <script src="${pageContext.request.contextPath }/resources/dist/js/app.min.js" type="text/javascript"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="${pageContext.request.contextPath }/resources/dist/js/demo.js" type="text/javascript"></script>
    
    
    </body>
    </html>