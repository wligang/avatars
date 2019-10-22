angular.module('activitiModeler').controller('KisBpmSelectComplexPropertyCtrl', ['$scope', '$http', function ($scope, $http) {
  var tenantId = -1
  if (sessionStorage.getItem('pigx-tenantId')) {
    tenantId = JSON.parse(sessionStorage.getItem('pigx-tenantId')).content
  }
  var token = JSON.parse(sessionStorage.getItem('pigx-access_token')).content
  var modelId = JSON.parse(sessionStorage.getItem('pigx-tag')).content.params.id
  $http({
    method: 'GET',
    url: "/act/acttransition/list?modelId=" + modelId,
    headers: {
      'TENANT-ID': tenantId,
      "Authorization": "Bearer " + token
    }
  }).success(function (data, status, headers, config) {
    data = data.data
    options = []
    for (var i = 0; i < data.length; i++) {
      options.push({"name": data[i].name, "value": data[i].value})
    }
    $scope.property.options = options
  })


  if ($scope.property.value == undefined && $scope.property.value == null) {
    console.log('$scope.property value is null ');

  }

  $scope.multiInstanceSelectChanged = function () {

    console.log('$scope.property multiInstanceSelectChanged :' + $scope.property);
    $scope.updatePropertyInModel($scope.property);
  };
}]);
