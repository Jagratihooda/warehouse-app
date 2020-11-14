angular.module('productApp', [])
.controller('product', function($scope, $http) {
	$scope.warnVar=true;
	$scope.tableVar=true;
	var req = {
			method: 'GET',
			url: 'http://localhost:8081/warehouse/products',
	}
	$http(req).
	then(function mySuccess(response) {
		$scope.warnVar=false;
		$scope.tableVar=true;
		$scope.products = response.data;
	},function myError(response) {
		$scope.warnVar=true;
		$scope.tableVar=false;
	});
});

