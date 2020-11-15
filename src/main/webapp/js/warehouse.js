var warehouseModule = angular.module('warehouseModule', []);

warehouseModule.controller('articleController',  ['$rootScope', '$scope', '$http', '$location',
    function (rootScope, scope, http, location) {
	    rootScope.loadArticles = function () {
	scope.warnVar=true;
	scope.tableVar=true;
	var req = {
			method: 'GET',
			url: 'http://localhost:8081/warehouse/articles',
	}
	http(req).
	then(function mySuccess(response) {
		scope.warnVar=false;
		scope.tableVar=true;
		scope.articles = response.data;
	},function myError(response) {
		scope.warnVar=true;
		scope.tableVar=false;
	})};

rootScope.loadArticles();

	 rootScope.checkProductDetails = function () {
     	scope.warnVar=true;
     	scope.tableVar=true;
     	var req = {
         			method: 'GET',
         			url: 'http://localhost:8081/warehouse/products',
         	}
     	http(req).
     	then(function mySuccess(response) {
     		scope.warnVar=false;
     		scope.tableVar=true;
     		scope.products = response.data;
     		location.path('/warehouse/products');

     		//rootScope.loadProducts();
     	},function myError(response) {
     		scope.warnVar=true;
     		scope.tableVar=false;
    })};


    }]);

warehouseModule.controller('productController', ['$rootScope', '$scope', '$http', '$location',
    function (rootScope, scope, http, location) {
    rootScope.loadProducts = function () {

	scope.warnVar=true;
	scope.tableVar=true;
	var req = {
			method: 'GET',
			url: 'http://localhost:8081/warehouse/products',
	}
	http(req).
	then(function mySuccess(response) {
		scope.warnVar=false;
		scope.tableVar=true;
		scope.products = response.data;
	},function myError(response) {
		scope.warnVar=true;
		scope.tableVar=false;
	})};

    rootScope.loadProducts();

    rootScope.sellProduct = function (id) {
	scope.warnVar=true;
	scope.tableVar=true;
	var req = {
			method: 'POST',
			url: 'http://localhost:8081/warehouse/sell-product?id=' + id,
	}
	http(req).
	then(function mySuccess(response) {
		scope.warnVar=false;
		scope.tableVar=true;
		//scope.products = response.data;
		//location.path('/warehouse/products');

		rootScope.loadProducts();
	},function myError(response) {
		scope.warnVar=true;
		scope.tableVar=false;
	})};

    rootScope.checkInventory = function () {
	scope.warnVar=true;
	scope.tableVar=true;
	var req = {
    			method: 'GET',
    			url: 'http://localhost:8081/warehouse/articles',
    	}
	http(req).
	then(function mySuccess(response) {
		scope.warnVar=false;
		scope.tableVar=true;
		scope.articles = response.data;
		location.path('/warehouse/articles');

		//rootScope.loadProducts();
	},function myError(response) {
		scope.warnVar=true;
		scope.tableVar=false;
	})};


}]);



var warehouseApp = angular.module('warehouseApp', ['ngRoute', 'warehouseModule']);

warehouseApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider.
    when('/warehouse/articles', {
        templateUrl: 'templates/articles.html',
        controller: 'articleController'
    }).
     otherwise({
             templateUrl: 'templates/products.html',
             controller: 'productController'
          });

}]);