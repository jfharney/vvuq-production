angular.module('vvuqApp').controller('EscoreCtrl',['$scope','$http',function($scope,$http) {

  console.log('in escore controller');
  

  $scope.test_type = '';
  $scope.test_type_done = 'false';
  $scope.test_type_time = '';
  
  $scope.available_datasets = VVUQ.available_datasets;
  
  //----test 3
  
  $scope.keywords = VVUQ.keyword_list;

  $scope.selection = ['keyword4', 'keyword5'];
  
  $scope.unselection = [];
  
  $scope.unselected = function () {
	  
	  console.log('in unselected keywords')
	  var arr = new Array();
	  
	  for(var i=0;i<$scope.keywords.length;i++) {
		  var isSelected = false;
		  for(var j=0;j<$scope.selection.length;j++) {
			  if($scope.selection[j] == $scope.keywords[i]) {
				  isSelected = true;
			  }
		  }
		  if(!isSelected) {
			  arr.push($scope.keywords[i]);
		  }
		  
	  }
	  
	  
	  return arr;
  };
  
  $scope.r_selected = '';
  $scope.r_unselected = '';
  $scope.r_all = '';
  $scope.test3 = '';

  // toggle selection for a given fruit by name
  $scope.toggleSelection = function toggleSelection(keyword) {
    var idx = $scope.selection.indexOf(keyword);

    // is currently selected
    if (idx > -1) {
      $scope.selection.splice(idx, 1);
    }

    // is newly selected
    else {
      $scope.selection.push(keyword);
    }
    
    var arr = new Array();
	  
	  for(var i=0;i<$scope.keywords.length;i++) {
		  var isSelected = false;
		  for(var j=0;j<$scope.selection.length;j++) {
			  if($scope.selection[j] == $scope.keywords[i]) {
				  isSelected = true;
			  }
		  }
		  if(!isSelected) {
			  arr.push($scope.keywords[i]);
		  }
		  
	  }
	  
	$scope.unselection = arr;
    
    console.log("selected keywords: " + $scope.selection);
    console.log("unselected keywords: " + $scope.unselection);
    
  };
  
  $scope.test3results = '';
  
  
  
  
  $scope.test3 = function(type){
	  console.log('test 3 click ' + type);
	  $scope.test_type = type;
	  	
	  $http.post('/test3',{
			'name' : 'pubmed',//$scope.dbName,
			'selected_keywords' : $scope.selection,
			'all_keywords' : $scope.keywords
		}).success(function(data){
			console.log('success');
			$scope.test3results = data;
		}).error(function(data){
			console.log('error');
		});
  };		
  
  //----end test 3
  
  

  //-----test 5
  
  $scope.test5dataset = { name : 'all'};
	
	$scope.test5results = '';
	
	$scope.test5 = function(){
		
		console.log('test5');
		
		var results_arr = new Array();
		
		if($scope.test5dataset.name != 'all') {
			
			var obj = {
					'input_file' : $scope.test20dataset.name,
					'numrows' : '?',
					'total_time' : 'total_time'
			};
			
			results_arr.push(obj);
			
		} else {
			
			for(var i=0;i<4;i++) {
				var obj = {
						'input_file' : $scope.test5dataset.name,
						'numrows' : '?',
						'total_time' : 'total_time' + i
				};
				results_arr.push(obj);
			}
			
		}
		
		$scope.test5results = results_arr;
	}
  
  //---end test 5
  

	//-----test 20
  
  $scope.test20 = function(type){
	  console.log('click ' + type);
	  $scope.test_type = type;
	  	
		$http.get('/test20',{
		}).success(function(data){
			console.log('success');
			for(var key in data) {
				console.log('key: ' + key + " data: " + data[key]);
			}
			$scope.test_type_done = 'true';
			$scope.test_type_time = data['text'];
		}).error(function(data){
			console.log('error');
			$scope.test_type_done = 'true';	
		});
		
	};
	
	
	
	$scope.test20dataset = { name : 'all'};
	
	$scope.test20results = '';
	
	$scope.test20 = function(){
		
		console.log('test20');
		
		var results_arr = new Array();
		
		if($scope.test20dataset.name != 'all') {
			
			var obj = {
					'input_file' : $scope.test20dataset.name,
					'numrows' : '?',
					'total_time' : 'total_time'
			};
			
			results_arr.push(obj);
			
		} else {
			
			for(var i=0;i<4;i++) {
				var obj = {
						'input_file' : $scope.test20dataset.name,
						'numrows' : '?',
						'total_time' : 'total_time' + i
				};
				results_arr.push(obj);
			}
			
		}
		
		$scope.test20results = results_arr;
	}
	      
	      
	//-----end test21
	      
	

	//-----test 21
	
	$scope.test21dataset = { name : 'all'};
	
	$scope.test21results = '';
	
	$scope.test21 = function(){
		
		console.log('test21');
		
		var results_arr = new Array();
		
		if($scope.test21dataset.name != 'all') {
			
			var obj = {
					'input_file' : $scope.test21dataset.name,
					'numrows' : '?',
					'total_time' : 'total_time'
			};
			
			results_arr.push(obj);
			
		} else {
			
			for(var i=0;i<4;i++) {
				var obj = {
						'input_file' : $scope.test21dataset.name,
						'numrows' : '?',
						'total_time' : 'total_time' + i
				};
				results_arr.push(obj);
			}
			
		}
		
		$scope.test21results = results_arr;
	}
	      
	      
	//-----end test21
	      
	      
	      
//----test 23
	
	
	$scope.test23results = '';
	
	$scope.test23LLNLresults = VVUQ.test23LLNLresults;
	$scope.test23HSARPAresults = VVUQ.test23HSARPAresults;
	
	$scope.test23details = 'false';
	
	$scope.ornl_details = 'ornl_details';
	$scope.llnl_details = 'llnl_details';
	$scope.hsarpa_details = 'hsarpa_details';
	
	
	$scope.test23 = function(dataset,query){
		  console.log('test23');
			
			$http.post('/test23',{
				'dataset' : dataset,//$scope.dbName,
				'query' : query
			}).success(function(data){
				console.log('success');
				$scope.test23results = data;
				for(var key in data) {
					console.log('key: ' + key);
				}
			}).error(function(data){
				console.log('error');
			});
			
	};
	
	
	
  
}]);



/*
$scope.test5 = function(type){
	  console.log('click ' + type);
	  $scope.test_type = type;
	  	
		$http.get('/test5',{
		}).success(function(data){
			console.log('success');
			for(var key in data) {
				console.log('key: ' + key + " data: " + data[key]);
			}
			$scope.test_type_done = 'true';
			$scope.test_type_time = data['text'];
		}).error(function(data){
			console.log('error');
			$scope.test_type_done = 'true';	
		});
		
};


$scope.test4 = function(type){
  console.log('click ' + type);
  $scope.test_type = type;
  	
	$http.get('/test4',{
	}).success(function(data){
		console.log('success');
		for(var key in data) {
			console.log('key: ' + key + " data: " + data[key]);
		}
		$scope.test_type_done = 'true';
		$scope.test_type_time = data['text'];
	}).error(function(data){
		console.log('error');
		$scope.test_type_done = 'true';	
	});
	
};
	
*/
