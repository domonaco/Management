'use strict';

app.controller('QRController', function ($rootScope, $scope, QRService, $window, $http) {

    var self = this;
    self.request = {username: '', password: ''};
    self.searchRequest =
        {
            vehicleMakers: '',
            vehicleModels: '',
            supplyCode: '',
            kw: '',
            taxableHorsePower: '',
            firstMatriculationDate: '',
            dataDiInizioValidita: '',
            dataDiFineValidita: ''
        };

    self.insertRequest =
        {
            infocarCode: '', trimDate: '', firstMatriculationDate: '', vehicleMakers: '', vehicleNewMakers: '',
            vehicleModels: '', vehicleNewModels: '', vehicleVersion: '', carType: '', tipoCarb: '', classeInq: '',
            maxSpeed: '', supplyCode: '', kw: '', taxableHorsePower: '', weight: '', vehicleValue: '',
            indicatore: '', vehicleIndicator: '', indicInProd: '', antifurto: '', width: '', lenght: ''
        };

    self.user = null;

    self.makers = null;
    self.models = null;
    self.manualEntryVehicleList = null;
    self.newVehicleId = null;

    self.selectedValue = '';

    self.login = login;
    self.btnSearch = btnSearch;
    self.btnInsert = btnInsert;
    self.vehicleMakersList = vehicleMakersList;
    self.vehicleModels = vehicleModels;
    self.backToHome = backToHome;
    self.backToSearch = backToSearch;
    self.search = search;
    self.insert = insert;
    self.checkModels = checkModels;
    self.init = init;

    if (sessionStorage.getItem('searchResult') !== "undefined") {
        $scope.searchResult = JSON.parse(sessionStorage.getItem('searchResult'));
    }

    function login() {

        console.log('Login operation start...', self.self.request);
        QRService.login(self.self.request)
            .then(
                function (response) {
                    console.log("Logged in. Welcome " + response.username);
                    $scope.user = response.username;
                },
                function (error) {
                    console.log("Error: ", error);
                }
            );
        reset();
    }

    function reset() {
        self.request = {plate: '', policyNumber: ''};
        $scope.myForm.$setPristine(); //reset Form
    }

    function btnSearch() {
        window.location.href = CONTEXT_ROOT + '/search';
    }

    function btnInsert() {
        window.location.href = CONTEXT_ROOT + '/insert';

    }

    function backToHome() {
        sessionStorage.clear();
        window.location.href = CONTEXT_ROOT + '/home';
    }

    function backToSearch() {
        sessionStorage.clear();
        window.location.href = CONTEXT_ROOT + '/search';
    }

    function vehicleMakersList() {
        sessionStorage.clear();
        QRService.vehicleMakers()
            .then(
                function (response) {
                    console.log("Makers retrieved.");
                    $rootScope.makerList = response.makers;
                },
                function (error) {
                    console.log("Error in makers retrieve operation.", error);
                }
            );
    }

    function vehicleModels(isInsert) {

        var makerSelected = document.getElementById("vehicleMakers");
        var toSend;

        if (isInsert) {
            self.insertRequest.vehicleMakers = makerSelected.options[makerSelected.selectedIndex].value;
            toSend = self.insertRequest.vehicleMakers;
        } else {
            self.searchRequest.vehicleMakers = makerSelected.options[makerSelected.selectedIndex].value;
            toSend = self.searchRequest.vehicleMakers;
        }

        if (isInsert && self.insertRequest.vehicleMakers === 'Marca Non Censita') {
            document.getElementById("newBrandDiv").hidden = false;
            document.getElementById("vehicleModels").hidden = true;
            document.getElementById("newModelDiv").hidden = false;
            return;
        } else if (isInsert && self.insertRequest.vehicleMakers !== 'Marca Non Censita') {
            document.getElementById("newBrandDiv").hidden = true;
            document.getElementById("vehicleModels").hidden = false;
            document.getElementById("newModelDiv").hidden = true;
        }

        console.log("Maker code: " + self.searchRequest.vehicleMakers);

        QRService.vehicleModels(toSend,isInsert)
            .then(
                function (response) {
                    console.log("Models retrieved.");
                    $scope.modelList = response.models;
                },
                function (error) {
                    console.log("Error in models retrieve operation.", error);
                }
            );
    }

    function checkModels() {

        var modelSelected = document.getElementById("vehicleModels");

        self.insertRequest.vehicleModels = modelSelected.options[modelSelected.selectedIndex].value;

        if (self.insertRequest.vehicleModels === 'Modello Non Censito') {
            document.getElementById("newModelDiv").hidden = false;
        } else {
            document.getElementById("newModelDiv").hidden = true;
        }
    }

    function search() {

        fillSearchRequest();

        QRService.vehicleSearch(self.searchRequest)
            .then(
                function (response) {
                    sessionStorage.setItem("searchResult", JSON.stringify(response.manualEntryVehicleList));
                    $window.location.href = CONTEXT_ROOT + '/searchResult';
                },
                function (error) {
                    alert("Errore durante la ricerca. Si prega di riprovare piu' tardi." + error);
                }
            ).catch(
            function (fallback) {
                $window.location.href = CONTEXT_ROOT + '/searchResultError';
            });
    }

    function insert() {

        fillInsertRequest();

        QRService.vehicleInsert(self.insertRequest)
            .then(
                function (response) {
                    alert("Inserimento avvenuto con successo!");
                    window.location.href = CONTEXT_ROOT + '/home';
                },
                function (error) {
                    alert("Errore durante l'inserimento. Si prega di riprovare più tardi.");
                }
            );
    }

    function fillSearchRequest() {
        var modelSelected = document.getElementById("vehicleModels");
        self.searchRequest.vehicleModels = modelSelected.options[modelSelected.selectedIndex].value;
        self.searchRequest.supplyCode = document.getElementById("supplyCode").value;
        self.searchRequest.kw = document.getElementById("kw").value;
        self.searchRequest.taxableHorsePower = document.getElementById("taxableHorsePower").value;
        self.searchRequest.firstMatriculationDate = document.getElementById("firstMatriculationDate").value;
    }

    function fillInsertRequest() {
        self.insertRequest.infocarCode = document.getElementById("infocarCode").value;
        self.insertRequest.trimDate = document.getElementById("trimDate").value;
        self.insertRequest.firstMatriculationDate = document.getElementById("firstMatriculationDate").value;
        self.insertRequest.vehicleMakers = document.getElementById("vehicleMakers").value;
        self.insertRequest.vehicleNewMakers = document.getElementById("vehicleNewMakers").value;
        self.insertRequest.vehicleModels = document.getElementById("vehicleModels").value;
        self.insertRequest.vehicleNewModels = document.getElementById("vehicleNewModels").value;
        self.insertRequest.vehicleVersion = document.getElementById("vehicleVersion").value;
        self.insertRequest.carType = document.getElementById("carType").value;
        self.insertRequest.tipoCarb = document.getElementById("tipoCarb").value;
        self.insertRequest.classeInq = document.getElementById("classeInq").value;
        self.insertRequest.maxSpeed = document.getElementById("maxSpeed").value;
        self.insertRequest.supplyCode = document.getElementById("supplyCode").value;
        self.insertRequest.kw = document.getElementById("kw").value;
        self.insertRequest.taxableHorsePower = document.getElementById("taxableHorsePower").value;
        self.insertRequest.weight = document.getElementById("weight").value;
        self.insertRequest.vehicleValue = document.getElementById("vehicleValue").value;
        self.insertRequest.indicatore = document.getElementById("indicatore").value;
        self.insertRequest.vehicleIndicator = document.getElementById("vehicleIndicator").value;
        self.insertRequest.indicInProd = document.getElementById("indicInProd").value;
        self.insertRequest.antifurto = document.getElementById("antifurto").value;
        self.insertRequest.width = document.getElementById("width").value;
        self.insertRequest.lenght = document.getElementById("lenght").value;
    }

    function getNewVehicleId() {
        QRService.getNewVehicleId()
            .then(
                function (response) {
                    self.insertRequest.infocarCode = response.newVehicleId;
                },
                function (error) {
                    alert("Errore durante la ricerca. Si prega di riprovare piu' tardi." + error);
                }
            )
    }

    function init() {
        vehicleMakersList();
        getNewVehicleId();
    }


})
;