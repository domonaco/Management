<!DOCTYPE html>

<html ng-app="app">
<head>
    <meta charset="utf-8"/>
    <title>QuattroRuote Manual Entry</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</head>

<body ng-controller="QRController as ctrl" ng-init="ctrl.vehicleMakersList()" style="font-size: small">
<div class="panel panel-default">
    <div class="panel-heading" style="background: orange; margin: 5px; font-size: xx-large"><b>QUATTRORUOTE |</b>
        Ricerca
    </div>
    <div class="col-sm-5">
        <div class="card">
            <div class="card-header" style="background: #ffa420; margin: 3px; font-size: small">
                Inserire la chiave di ricerca e premere il tasto SEARCH
            </div>
            <div class="formcontainer">
                <form ng-submit="ctrl.search()" name="mySearchForm" class="form-horizontal">

                    <div class="form-group">
                        <label class="control-lable">MARCA</label>
                        <div class="col-md-5">
                            <select id="vehicleMakers" name="vehicleMakers"
                                    ng-model="searchRequest.vehicleMakers" required
                                    ng-change="ctrl.vehicleModels(false)"
                                    class="form-control">

                                <option ng-repeat="u in makerList" value="{{u.code.toString()}}">{{u.description}}
                                </option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-lable">MODELLO</label>
                        <div class="col-md-5">
                            <select id="vehicleModels" name="vehicleModels "
                                    ng-model="searchRequest.vehicleModels" class="form-control">
                                <option ng-repeat="u in modelList"
                                        value="{{u.code.toString()}}">{{u.code.toString()}} - {{u.description}}
                                </option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-lable">CILINDRATA</label>
                        <div class="col-md-5">
                            <input id="supplyCode" ng-model="searchRequest.supplyCode" type="number"
                                   class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-lable">KW</label>
                        <div class="col-md-5">
                            <input id="kw" ng-model="searchRequest.kw" type="number" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-lable">CV</label>
                        <div class="col-md-5">
                            <input id="taxableHorsePower" ng-model="searchRequest.taxableHorsePower" type="number "
                                   class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-lable">PRIMA IMM.</label>
                        <div class="col-md-5">
                            <input id="firstMatriculationDate" ng-model="searchRequest.firstMatriculationDate"
                                   type="text" placeholder="MM/YYYY" class="form-control"/>
                        </div>
                    </div>

                    <div class="btn-group mr-2" role="group" aria-label="First group">
                        <div class="form-actions">
                            <input type="submit" value="Search" class="btn btn-primary"
                                   ng-disabled="mySearchForm.$invalid">
                            <button type="submit" ng-click="ctrl.backToHome()" class="btn btn-primary">Back</button>
                        </div>

                    </div>
                </form>
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
