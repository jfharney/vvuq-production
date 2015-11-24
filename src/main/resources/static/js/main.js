var mainCtrl = function($scope) {
	
  $scope.username = 'user';
  $scope.password = '';
  
 
  $scope.isActive = function(viewLocation){
		
		console.log('in isActive');
		return viewLocation === $location.path();
	}
	
}

angular.module('vvuqApp').controller('MainCtrl',mainCtrl);

