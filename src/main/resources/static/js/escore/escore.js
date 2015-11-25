angular.module('vvuqApp').controller('EscoreCtrl',['$scope','$http',function($scope,$http) {


	/*
  $scope.test_type = '';
  $scope.test_type_done = 'false';
  $scope.test_type_time = '';
  */
	
  
  $scope.available_datasets = VVUQ.available_datasets;
  $scope.keyword_sets = VVUQ.keyword_set_list;
  

  //$scope.keywords_test3 = VVUQ.keyword_list;
  
  
  
  //----test 3
  
  //reveals the result set for test 3
  $scope.show_results_test3 = false;
  
  //array of selected keyword sets
  $scope.selection_test3 = [];
  

  //boolean representing if test passed or failed
  $scope.test_result_test3 = false;
  
  //returns a result set 
  $scope.test3 = function(dataset,query){
		  //console.log('test23');
			
			$http.post('/test3',{
				'dataset' : dataset,//$scope.dbName,
				'query' : query
			}).success(function(data){
				console.log('success ' + data.length);
				var arr = new Array();
				for(var i=0;i<VVUQ.num_listings;i++) {
					arr.push(data[i]);
				}
				$scope.results_test3 = data;
				$scope.results_test3_10 = arr;//data;
				for(var key in data) {
					console.log('key: ' + key);
				}
			}).error(function(data){
				console.log('error');
			});
			
	};
  
	// toggle selection for a given fruit by name
	  $scope.toggleSelection = function toggleSelection(keyword) {
		  console.log('keyword ' + keyword);
	    var idx = $scope.selection_test3.indexOf(keyword);

	    $scope.show_test3_results = false;
	    
	    // is currently selected
	    if (idx > -1) {
	      $scope.selection_test3.splice(idx, 1);
	    }

	    // is newly selected
	    else {
	      $scope.selection_test3.push(keyword);
	    }
	    
	  };
	

  
  //----end test 3
  
  
  
  //----test 4

  //reveals the result set for test 3
  $scope.show_results_test4 = false;

  //boolean representing if test passed or failed
  $scope.test_result_test4 = false;
  
  //weighted array
  $scope.weights = [1,1,1,1,1,1,1,1];
  
  //returns a result set 
  $scope.test4 = function(dataset,query){
	  
			$http.post('/test4',{
				'dataset' : dataset,//$scope.dbName,
				'query' : query
			}).success(function(data){
				console.log('success ' + data.length);
				var arr = new Array();
				for(var i=0;i<VVUQ.num_listings;i++) {
					arr.push(data[i]);
				}
				$scope.results_test4 = data;
				$scope.results_test4_10 = arr;//data;
				for(var key in data) {
					console.log('key: ' + key);
				}
				$scope.show_results_test4 = true;
				
			}).error(function(data){
				console.log('error');
			});
			
	};
  

  
  
  //-----end test 4
  
  
  
  

  //-----test 5
	$scope.test5 = function(){
		console.log('test5');
	}

	$scope.show_results_test5 = false;
	
	$scope.test5dataset = { name : 'pubmedCentral_001'};
	$scope.test5numruns = { name : '1'};
	$scope.test5numrecords = { name : '1'};
	
	$scope.numiterations5 = $scope.test5numruns.name;
	
	$scope.range_test5 = 0;
	$scope.mean = 0;
	
	//returns a result set 
	  $scope.test5 = function(dataset,query){
		  
				$http.post('/test5',{
					'dataset' : dataset,//$scope.dbName,
					'query' : query
				}).success(function(data){
					console.log('success ' + data.length);
					var arr1 = new Array();
					arr1.push(data[0]);

					var arr10 = new Array();
					for(var i=0;i<VVUQ.num_listings;i++) {
						arr10.push(data[i]);
					}
					
					$scope.results_test5 = data;
					$scope.results_test5_1 = arr1;//data;
					$scope.results_test5_10 = arr10;//data;
					
					$scope.mean = $scope.results_test5_1[0].total_score;
					$scope.max = $scope.results_test5_1[0].total_score;
					$scope.min = $scope.results_test5_1[0].total_score;
					$scope.variance = 0;
					
					$scope.show_results_test5 = true;
					
				}).error(function(data){
					console.log('error');
				});
				
		};
	
	
  //---end test 5
  

		
		
		
		
		
		
		
		
	//-----test 20

	$scope.show_results_test20 = false;
	
	$scope.inputFile_test20 = '';

	$scope.test20dataset = { name : 'pubmedCentral_001'};

	$scope.test20results = '';
	
	$scope.test20 = function(){
		
		$http.post('/test20',{
			'inputFile' : $scope.test20dataset.name
		}).success(function(data){
			console.log('success');
			for(var key in data) {
				console.log('key: ' + key + " data: " + data[key]);
			}
			
			$scope.results_test20 = data;
			$scope.show_results_test20 = true;
		}).error(function(data){
			console.log('error');
			$scope.test_type_done = 'true';	
		});
		
	};
	
	
	
	
	      
	//-----end test20
	      
	
	
	
	
	
	
	
	
	
	
	
	
	

	//-----test 21
	
	$scope.show_results_test21 = false;
	
	$scope.inputFile_test21 = '';

	$scope.test21dataset = { name : 'pubmedCentral_001'};

	$scope.test21results = '';
	
	$scope.test21 = function(){
		
		$http.post('/test21',{
			'inputFile' : $scope.test21dataset.name
		}).success(function(data){
			console.log('success');
			for(var key in data) {
				console.log('key: ' + key + " data: " + data[key]);
			}
			
			$scope.results_test21 = data;
			$scope.show_results_test21 = true;
		}).error(function(data){
			console.log('error');
			$scope.test_type_done = 'true';	
		});
		
	};
	
	
	
	
	
	/*
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
	      
	*/
	
	//-----end test21
	      
	      
	      
//----test 23
	

	
	/*
	$scope.test23results10 = '';
	$scope.test23results = '';
	$scope.test23LLNLresults = VVUQ.test23LLNLresults;
	$scope.test23HSARPAresults = VVUQ.test23HSARPAresults;
	
	$scope.test23details = 'false';
	
	
	*/
	//details about ornl specs
	$scope.ornl_details = VVUQ.ornl_details;
	
	//details about llnl specs
	$scope.llnl_details = VVUQ.llnl_details;
	
	//details about hsarpa specs
	$scope.hsarpa_details = VVUQ.hsarpa_details;
	
	//results from other two
	$scope.test23LLNLresults = VVUQ.test23LLNLresults;
	$scope.test23HSARPAresults = VVUQ.test23HSARPAresults;
	
	//reveals the result set for test 3
	$scope.show_results_test23 = false;
	  
	//array of selected keyword sets
	$scope.selection_test3 = [];
	  

	//boolean representing if test passed or failed
	$scope.test_result_test23 = false;
	
	
	
	//returns a result set 
	  $scope.test23 = function(dataset,query){
			  //console.log('test23');
				
				$http.post('/test23',{
					'dataset' : dataset,//$scope.dbName,
					'query' : query
				}).success(function(data){
					console.log('success ' + data.length);
					var arr = new Array();
					for(var i=0;i<VVUQ.num_listings;i++) {
						arr.push(data[i]);
					}
					$scope.results_test23 = data;
					$scope.results_test23_10 = arr;//data;
					for(var key in data) {
						console.log('key: ' + key);
					}

					$scope.show_results_test23 = true;
					
				}).error(function(data){
					console.log('error');
				});
				
		};
	  
	/*
	$scope.test23 = function(dataset,query){
		  console.log('test23');
			
			$http.post('/test23',{
				'dataset' : dataset,//$scope.dbName,
				'query' : query
			}).success(function(data){
				console.log('success ' + data.length);
				var arr = new Array();
				for(var i=0;i<VVUQ.num_listings;i++) {
					arr.push(data[i]);
				}
				$scope.test23results = data;
				$scope.test23results10 = arr;//data;
				for(var key in data) {
					console.log('key: ' + key);
				}
			}).error(function(data){
				console.log('error');
			});
			
	};
	*/
	
	
	// at the bottom of your controller
	var init = function () {
		/*
	   // check if there is query in url
	   // and fire search in case its value is not empty
		console.log('in init');
		
		$http.get('/keywordset', {
			
		}).success(function(data){
			console.log('success');
			for(var key in data) {
				console.log('key: ' + key + " data: " + data[key]);
			}
			
		}).error(function(data){
			console.log('error');
			//$scope.test_type_done = 'true';	
		});
		*/
		
	};
	// and fire it after definition
	init();
	
  
}]);



/*
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
*/




/*
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
      */
