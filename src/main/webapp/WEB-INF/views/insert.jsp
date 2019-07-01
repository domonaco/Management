<!DOCTYPE html>

<html ng-app="app">
<head>
    <meta charset="utf-8"/>
    <title>Medical Service</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</head>

<body ng-controller="QRController as ctrl" ng-init="ctrl.init()" style="font-size: small">
    <div class="panel panel-default">
        <div class="panel-heading" style="background: orange; margin: 5px; font-size: xx-large"><b>Medical Service |</b> Inserimento Manuale</div>

        <div class="formcontainer">
            <form ng-submit="ctrl.insert()" name="myInsertForm" class="form-horizontal">

                <div class="form-group">
                    <label class="control-lable">COD. INFOCAR</label>
                    <div class="col-md-5">
                        <input id="infocarCode" ng-readonly="true" ng-model="ctrl.insertRequest.infocarCode"
                               type="text" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">DATA TRIM.</label>
                    <div class="col-md-5">
                        <input id="trimDate" ng-model="insertRequest.trimDate"
                               type="text" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">PRIMA IMM.</label>
                    <div class="col-md-5">
                        <input id="firstMatriculationDate" ng-model="insertRequest.firstMatriculationDate"
                               type="text" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">MARCA</label>
                    <div class="col-md-5">
                        <select class="form-control" id="vehicleMakers" name="vehicleMakers"
                        ng-required ng-model="insertRequest.vehicleMakers" ng-change="ctrl.vehicleModels(true)">
                            <option ng-repeat="u in makerList" value="{{u.description}}">{{u.description}}</option>
                            <option value="Marca Non Censita">Marca Non Censita</option>
                        </select>
                    </div>
                    <div class="form-group" id="newBrandDiv" hidden="true">
                        <label class="control-lable">NUOVA MARCA:</label>
                        <div class="col-md-5">
                            <input type="text" class="form-control" id="vehicleNewMakers"
                                   ng-model="insertRequest.vehicleNewMakers" name="vehicleNewMakers"
                                   class="form-control">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">MODELLO</label>
                    <div class="col-md-5">
                        <select class="form-control" id="vehicleModels" name="vehicleModels "
                                ng-model="insertRequest.vehicleModels" ng-change="ctrl.checkModels()">
                            <option ng-repeat="u in modelList" value="{{u.description}}">{{u.description}}</option>
                            <option value="Modello Non Censito">Modello Non Censito</option>
                        </select>
                    </div>
                    <div class="form-group" id="newModelDiv" hidden="true">
                        <label class="control-lable">NUOVO MODELLO:</label>
                        <div class="col-md-5">
                            <input type="text" class="form-control" id="vehicleNewModels"
                                   ng-model="insertRequest.vehicleNewModels" name="vehicleNewModels" class="form-control">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">VERSIONE</label>
                    <div class="col-md-5">
                        <input id="vehicleVersion" ng-model="insertRequest.vehicleVersion"
                               type="text" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">TIPO AUTO</label>
                    <div class="col-md-5">
                        <input id="carType" ng-model="insertRequest.carType"
                               type="text" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">TIPO CARB.</label>
                    <div class="col-md-5">
                        <input id="tipoCarb" ng-model="insertRequest.tipoCarb"
                               type="text" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">CLASSE INQUINAMENTO</label>
                    <div class="col-md-5">
                        <input id="classeInq" ng-model="insertRequest.classeInq"
                               type="text" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">VELOCITA' MASSIMA</label>
                    <div class="col-md-5">
                        <input id="maxSpeed" ng-model="insertRequest.maxSpeed"
                               type="number" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">CILINDRATA</label>
                    <div class="col-md-5">
                        <input id="supplyCode" ng-model="insertRequest.supplyCode"
                               type="number" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">KW</label>
                    <div class="col-md-5">
                        <input id="kw" ng-model="insertRequest.kw"
                               type="number" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">CV</label>
                    <div class="col-md-5">
                        <input id="taxableHorsePower" ng-model="insertRequest.taxableHorsePower"
                               type="number"  ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">PESO</label>
                    <div class="col-md-5">
                        <input id="weight" ng-model="insertRequest.weight"
                               type="number"  ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">VALORE</label>
                    <div class="col-md-5 input-icon input-icon-right">
                        <input id="vehicleValue" ng-model="insertRequest.vehicleValue"
                               type="number"  ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">INDICATORE STR/F</label>
                    <div class="col-md-5">
                        <input id="indicatore" ng-model="insertRequest.indicatore"
                               type="text" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">VEHICLE INDICATOR</label>
                    <div class="col-md-5">
                        <input id="vehicleIndicator" ng-model="insertRequest.vehicleIndicator"
                               type="text" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">INDIC IN PROD.</label>
                    <div class="col-md-5">
                        <input id="indicInProd" ng-model="insertRequest.indicInProd"
                               type="text" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">ANTIFURTO</label>
                    <div class="col-md-5">
                        <input id="antifurto" ng-model="insertRequest.antifurto"
                               type="text" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">LARGHEZZA</label>
                    <div class="col-md-5">
                        <input id="width" ng-model="insertRequest.width"
                               type="number" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-lable">LUNGHEZZA</label>
                    <div class="col-md-5">
                        <input id="lenght" ng-model="insertRequest.lenght"
                               type="number" ng-required class="form-control"/>
                    </div>
                </div>

                <div class="form-actions" style="alignment: right">
                    <input type="submit" value="Save" class="btn btn-primary" ng-disabled="myInsertForm.$invalid" />
                    <button ng-click="ctrl.backToHome()">Back</button>
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
