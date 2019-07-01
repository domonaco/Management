<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>


<!DOCTYPE HTML>
<!-- <html xmlns:th="http://www.thymeleaf.org"> -->
<html ng-app="app">
<head>
    <meta charset="utf-8"/>
    <title>Medical Service</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</head>

<body ng-controller="QRController as ctrl" style="font-size: small">
<div class="panel panel-default">
    <div class="panel-heading" style="background: orange; margin: 5px; font-size: xx-large"><b>Medical Service |</b>
        Ricerca
    </div>
    <p style="alignment: right"><b>User: {{user}}</b></p>

    Risultato ricerca.

    <h3>La ricerca non ha prodotto risultati. Clicca su "Torna alla ricerca" per effettuarne una nuova</h3>

    <div class="form-actions">
        <button type="submit" ng-click="ctrl.backToSearch()">Torna alla ricerca</button>
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