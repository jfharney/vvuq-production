console.log("loadding app.js");

//var vvuqApp = angular.module('vvuqApp',['ngRoute','ui.bootstrap',
//	'ngFileUpload']);
var vvuqApp = angular.module('vvuqApp',['ngRoute']);


//Set up the route provider for
vvuqApp.config(['$routeProvider','$httpProvider',
	function($routeProvider,$httpProvider){
	

		$routeProvider.
			when('/main',{
				'templateUrl' : '/templates/main.html',
				'controller' : 'MainCtrl'
			}).
			when('/ingest',{
				'templateUrl' : '/templates/ingest.html',
				'controller' : 'IngestCtrl'
			}).
			when('/escore',{
				'templateUrl' : '/templates/escore.html',
				'controller' : 'EscoreCtrl'
			}).
			when('/other',{
				'templateUrl' : '/templates/other.html',
				'controller' : 'EscoreCtrl'
			}).
			when('/help',{
				'templateUrl' : '/templates/escore/help.html',
				'controller' : 'EscoreCtrl'
			}).
			when('/nmf',{
				'templateUrl' : '/templates/nmf/nmf.html',
				'controller' : 'NMFCtrl'
			}).
			otherwise({
				'redirectTo' : '/main',
				'controller' : 'ContentCtrl'
			});
		$httpProvider.defaults.headers.common["X-Requested-With"] = 
			'XMLHttpRequest';
	}]);
