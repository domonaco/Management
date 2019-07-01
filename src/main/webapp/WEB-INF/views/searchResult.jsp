<!DOCTYPE HTML>
<!-- <html xmlns:th="http://www.thymeleaf.org"> -->
<html ng-app="app">
<head>
    <meta charset="utf-8"/>
    <title>QuattroRuote Manual Entry</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</head>

<body ng-controller="QRController as ctrl" style="font-size: small">
<div class="panel panel-default">
    <div class="panel-heading" style="background: orange; margin: 5px; font-size: xx-large"><b>QUATTRORUOTE |</b>
        Risultato ricerca
    </div>
    <p style="alignment: right"><b>User: {{user}}</b></p>

    <div class="table-responsive text-nowrap">
            <table id="cdpdByVehicleTable" name="cdpdTable" class="table table-hover">
                <thead>
                <tr style="background:#ff5853; font-size: medium">
                    <th><label>COD. INFOCAR</label></th>
                    <th><label>PRIMA IMM.</label></th>
                    <th><label>MARCA</label></th>
                    <th><label>MODELLO</label></th>
                    <th><label>VERSIONE</label></th>
                    <th><label>TIPO CARB.</label></th>
                    <th><label>CV</label></th>
                    <th><label>CILINDRATA</label></th>
                    <th><label>KW</label></th>
                    <th><label>TIPO AUTO</label></th>
                    <th><label>DATA INIZIO VAL.</label></th>
                    <th><label>DATA FINE VAL.</label></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="u in searchResult">
                    <td ng-if="$odd" style="background-color:#f1f1f1">{{u.infocarCode}}</td>
                    <td ng-if="$even">{{u.infocarCode}}</td>
                    <td ng-if="$odd" style="background-color:#f1f1f1">{{u.firstMatriculationDate}}</td>
                    <td ng-if="$even">{{u.firstMatriculationDate}}</td>
                    <td ng-if="$odd" style="background-color:#f1f1f1">{{u.vehicleMaker}}</td>
                    <td ng-if="$even">{{u.vehicleMaker}}</td>
                    <td ng-if="$odd" style="background-color:#f1f1f1">{{u.vehicleModel}}</td>
                    <td ng-if="$even">{{u.vehicleModel}}</td>
                    <td ng-if="$odd" style="background-color:#f1f1f1">{{u.vehicleVersion}}</td>
                    <td ng-if="$even">{{u.vehicleVersion}}</td>
                    <td ng-if="$odd" style="background-color:#f1f1f1">{{u.tipoCarb}}</td>
                    <td ng-if="$even">{{u.tipoCarb}}</td>
                    <td ng-if="$odd" style="background-color:#f1f1f1">{{u.taxableHorsePower}}</td>
                    <td ng-if="$even">{{u.taxableHorsePower}}</td>
                    <td ng-if="$odd" style="background-color:#f1f1f1">{{u.supplyCode}}</td>
                    <td ng-if="$even">{{u.supplyCode}}</td>
                    <td ng-if="$odd" style="background-color:#f1f1f1">{{u.kw}}</td>
                    <td ng-if="$even">{{u.kw}}</td>
                    <td ng-if="$odd" style="background-color:#f1f1f1">{{u.carType}}</td>
                    <td ng-if="$even">{{u.carType}}</td>
                    <td ng-if="$odd" style="background-color:#f1f1f1">{{u.dataDiInizioValidita}}</td>
                    <td ng-if="$even">{{u.dataDiInizioValidita}}</td>
                    <td ng-if="$odd" style="background-color:#f1f1f1">{{u.dataDiFineValidita}}</td>
                    <td ng-if="$even">{{u.dataDiFineValidita}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="btn-group mr-2" role="group" aria-label="First group">
        <div class="form-actions">
            <button type="submit" ng-click="ctrl.backToSearch()" class="btn btn-primary">Back</button>
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