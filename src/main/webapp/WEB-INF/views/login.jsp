<!DOCTYPE html>
<%@ page import="it.monaco.medical.service.model.enums.EnumChannel" %>

<html ng-app="app">

<head>
    <meta charset="utf-8"/>
    <title>Medical Service</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</head>

<body ng-controller="QRController as ctrl" style="font-size: small">
    <div class="panel panel-default">
        <!-- TODO inserire label -->
        <div class="panel-heading" style="background: orange; margin: 5px; font-size: xx-large"><b>MEDICAL SERVICE |</b> Login</div>

        Inserire le proprie credenziali.
        <div class="formcontainer">
            <form ng-submit="ctrl.login()" name="myForm" class="form-horizontal">
                <div class="form-group col-md-5">
                    <label class="col-md-5 control-lable">Username:</label>
                    <div class="col-md-7">
                        <input type="text" ng-model="ctrl.self.request.username" name="uname"
                               class="username form-control" placeholder="Enter yout username" ng-required/>
                    </div>
                </div>
                <div class="form-group col-md-5">
                    <label class="col-md-5 control-lable">Password:</label>
                    <div class="col-md-7">
                        <input type="password" ng-model="ctrl.self.request.password"
                               placeholder="Enter your password (8 characters minimum)"
                               class="form-control" ng-minlenght="8"/>
                    </div>
                </div>
                <div class="form-actions col-md-12">
                    <input type="submit" value="Login" class="btn btn-primary" ng-disabled="myForm.$invalid">
                </div>
            </form>
        </div>
    </div>

    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-animate.js"></script>
    <script> var CONTEXT_ROOT = '${pageContext.request.contextPath}';</script>

    <script type="text/javascript" src="static/js/app.js"></script>
    <script type="text/javascript" src="static/js/controller/QRController.js"></script>
    <script type="text/javascript" src="static/js/service/QRService.js"></script>

</body>
</html>
