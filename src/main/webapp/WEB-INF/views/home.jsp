<!DOCTYPE html>

<html ng-app="app">
<head>
    <meta charset="utf-8"/>
    <title>QuattroRuote Manual Entry</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</head>

<body ng-controller="QRController as ctrl" style="font-size: small">
    <div class="panel panel-default">
        <div class="panel-heading" style="background: orange; margin: 5px; font-size: xx-large"><b>QUATTRORUOTE |</b> DB</div>

        <div class="card-body">
            <div class="panel panel-default">
                <div class="btn-group-vertical">

                    <button class="btn btn-primary btn-lg" ng-click="ctrl.btnSearch()">Ricerca</button>
                    <br>
                    <button class="btn btn-primary btn-lg" ng-click="ctrl.btnInsert()">Inserimento</button>
                </div>
            </div>
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
