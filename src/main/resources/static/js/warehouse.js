angular.module('warehouseApp', [])
.controller('article', function($scope, $http) {
	$scope.warnVar=true;
	$scope.tableVar=true;
	var req = {
			method: 'GET',
			url: 'http://localhost:8081/warehouse/articles',
	}
	$http(req).
	then(function mySuccess(response) {
		$scope.warnVar=false;
		$scope.tableVar=true;
		$scope.articles = response.data;
	},function myError(response) {
		$scope.warnVar=true;
		$scope.tableVar=false;
	});
});

