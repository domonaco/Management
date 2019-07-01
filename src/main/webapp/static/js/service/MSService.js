'use strict';

app.factory('QRService', ['$http', '$q', function ($http, $q) {

    // var channel = "<%=channel%>";
    var channel = "WEB_SITE";
    // var host = "<%=host%>";
    var host = "CASUAL";
    // var session = "<%=sessionId%>";
    var session = "deuywg";

    //TODO non sarà più questa URI - si fa riferimento al servizio che Verti utilizza per fare la login ai servizi
    var REST_SERVICE_URI = CONTEXT_ROOT + '/manual-entry';

    var searhResult = null;

    var factory = {
        login: login,
        vehicleMakers: vehicleMakers,
        vehicleModels: vehicleModels,
        vehicleSearch: vehicleSearch,
        vehicleInsert: vehicleInsert,
        getNewVehicleId: getNewVehicleId
    };

    return factory;

    function login(request) {
        var deferred = $q.defer();
        var REST_URI;

        REST_URI = REST_SERVICE_URI + "/login?username=" + request.username + "&password=" + request.password;

        $http.get(REST_URI)
            .then(
                function (response) {
                    window.location.href = CONTEXT_ROOT + '/home';
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.log("Error ", errResponse)
                }
            );
        return deferred.promise;
    }

    function vehicleMakers() {
        var deferred = $q.defer();
        var REST_URI;

        REST_URI = REST_SERVICE_URI + "/all-vehicle-makers?channel=" + channel + "&host=" + host + "&session=" + session;

        $http.get(REST_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.log("Error ", errResponse)
                }
            );
        return deferred.promise;
    }

    function vehicleModels(makeCode, isInsert) {
        var deferred = $q.defer();
        var REST_URI_PARTIAL;
        var REST_URI;

        REST_URI_PARTIAL = REST_SERVICE_URI + "/vehicle-models";

        if (isInsert) {
            REST_URI_PARTIAL = REST_URI_PARTIAL + "/isInsert";
        }

        REST_URI = REST_URI_PARTIAL + "?channel=" + channel + "&host=" + host + "&session=" + session + "&makeCode=" + makeCode;

        $http.get(REST_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.log("Error ", errResponse)
                }
            );
        return deferred.promise;
    }

    function vehicleSearch(manualEntryVehicleReqDTO) {
        var deferred = $q.defer();
        var REST_URI;

        REST_URI = REST_SERVICE_URI + "/search-vehicle?channel=" + channel + "&host=" + host + "&session=" + session;

        $http.post(REST_URI, manualEntryVehicleReqDTO)
            .then(
                function (response) {
                    self.searchResult = response.data;
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.log("Error during search operation.\n", errResponse);
                }
            );
        return deferred.promise;
    }

    function vehicleInsert(manualEntryVehicleInsertDTO) {

        var deferred = $q.defer();
        var REST_URI;

        REST_URI = REST_SERVICE_URI + "/insert-new-vehicle?channel=" + channel + "&host=" + host + "&session=" + session;

        $http.post(REST_URI,manualEntryVehicleInsertDTO)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.log("Error during insert operation.\n", errResponse);
                }
            );
        return deferred.promise;
    }

    function getNewVehicleId() {
        var deferred = $q.defer();
        var REST_URI;

        REST_URI = REST_SERVICE_URI + "/get-new-vehicle-id";

        $http.get(REST_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.log("Error ", errResponse)
                }
            );

        return deferred.promise;

    }


}]);