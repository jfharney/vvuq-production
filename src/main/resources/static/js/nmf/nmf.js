angular.module('vvuqApp').controller('NMFCtrl',['$scope','$http',function($scope,$http) {

	console.log('in nmf controller');

	$scope.data_sources = VVUQ.data_sources_list;
	$scope.alg_types_list = VVUQ.alg_types_list;
	
	for (var key in $scope.alg_types_list) {
		console.log('key : ' + key + ' value: ' + $scope.alg_types_list[key]);
	}
	  
	$scope.selection_data_sources = [];
	
	
	$scope.nmf_dir = '/Users/8xo/software/nimfa/nimfa/nimfa/examples';
	
	
	$scope.toggle_data_sources_selection = function toggle_data_sources_selection(keyword) {
		
		var idx = $scope.selection_data_sources.indexOf(keyword);

	    // is currently selected
	    if (idx > -1) {
	      $scope.selection_data_sources.splice(idx, 1);
	    }

	    // is newly selected
	    else {
	      $scope.selection_data_sources.push(keyword);
	    }
	    
	}
	
	$scope.selection_algorithms = [];
	  
	// toggle selection for a given fruit by name
	$scope.toggle_algorithm_selection = function toggle_algorithm_selection(keyword) {
		
	    var idx = $scope.selection_algorithms.indexOf(keyword);

	    // is currently selected
	    if (idx > -1) {
	      $scope.selection_algorithms.splice(idx, 1);
	    }

	    // is newly selected
	    else {
	      $scope.selection_algorithms.push(keyword);
	    }
	    
	};
	

	
	
	$scope.factorize = function() {
		
		console.log('in factorize');
		console.log('$scope.selection_algorithms: ' + $scope.selection_algorithms);
		var type = "factorize";
		
		var names = [];
		if ($scope.selection_algorithms.length == 0) {
			names = ["nimfa_nmf","nimfa_icm"];
		} else {
			names = $scope.selection_algorithms;
		}
		var ranks = [1,2,3,4,5];
		var dir = $scope.nmf_dir;
		
		console.log('$scope.nmf_dir ' + $scope.nmf_dir);
		
		//console.log('scope.selection_data_sources');
		for(var key in $scope.selection_data_sources) {
			//console.log('key: ' + $scope.selection_data_sources[key]);
		}
		
		$http.post('/nmf',{
			'text' : type,
			'dir' : dir,
			'names' : $scope.selection_algorithms,
			'ranks' : ranks
		}).success(function(data){
			console.log('success ' + data.length);
			
			//for(var key in data) {
				//console.log('key: ' + key + ' value: ' + data[key]);
			//}
		}).error(function(data){
			console.log('error');
		});
		
		
	}
	
	
	$scope.d3_figure = function(){

		
		/*
		var xtitle = 'Life Expectancy';
		var ytitle = 'GDP per Capita';
		
		var type = "compare";
		
		
		$http.post('/nmf',{
			'text' : type
		}).success(function(data){
			console.log('success ' + data.length);
			
			for(var key in data) {
				console.log('key: ' + key + ' value: ' + data[key]);
			}
		}).error(function(data){
			console.log('error');
		});
		*/
		
		/*
		var xAxisText = 'S';
		var yAxisText = 'S';
		
		
		
		var margin = {top: 20, right: 90, bottom: 30, left: 50},
	    width = 960 - margin.left - margin.right,
	    height = 500 - margin.top - margin.bottom;

	var parseDate = d3.time.format("%Y-%m-%d").parse,
	    formatDate = d3.time.format("%b %d");

	var x = d3.scale.linear().range([0, width]),//d3.time.scale().range([0, width]),
	    y = d3.scale.linear().range([height, 0]),
	    z = d3.scale.linear().range(["white", "steelblue"]);

	alert('x: ' + d3.scale.linear().range([0, width]));
	
	// The size of the buckets in the CSV data file.
	// This could be inferred from the data if it weren't sparse.
	var xStep = 864e5,
	    yStep = 100;

	
	
	var svg = d3.select("#chart").append("svg")//d3.select("body").append("svg")
	    .attr("width", width + margin.left + margin.right)
	    .attr("height", height + margin.top + margin.bottom)
	  .append("g")
	    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

	//d3.csv("data/MAT-compare-nmf-icm-5.csv", function(error, buckets) {
	d3.csv('data/default.csv', function(error, buckets) {
	  if (error) throw error;

	  //alert('buckets: ' + buckets);
	  
	  // Coerce the CSV data to the appropriate types.
	  buckets.forEach(function(d) {
		  console.log('in bucket: ' + d);
		  for(var key in d) {
			  console.log('key: ' + key);
		  }
	    d.date = +d.date;//parseDate(d.date);
	    d.bucket = +d.bucket;
	    d.count = +d.count;
	  });

	  
	  
	  // Compute the scale domains.
	  x.domain(d3.extent(buckets, function(d) { return d.date; }));
	  
	  
	  y.domain(d3.extent(buckets, function(d) { return d.bucket; }));
	  z.domain([0, d3.max(buckets, function(d) { return d.count; })]);

	  console.log('xStep: ' + xStep);
	  xStep = 1;
	  yStep = 1;
	  
	  // Extend the x- and y-domain to fit the last bucket.
	  // For example, the y-bucket 3200 corresponds to values [3200, 3300].
	  x.domain([x.domain()[0], +x.domain()[1] + xStep]);
	  y.domain([y.domain()[0], y.domain()[1] + yStep]);

	  // Display the tiles for each non-zero bucket.
	  // See http://bl.ocks.org/3074470 for an alternative implementation.
	  svg.selectAll(".tile")
	      .data(buckets)
	    .enter().append("rect")
	      .attr("class", "tile")
	      .attr("x", function(d) { return x(d.date + xStep); })
	      .attr("y", function(d) { return y(d.bucket + yStep); })
	      .attr("width", x(xStep) - x(0))
	      .attr("height",  y(0) - y(yStep))
	      .style("fill", function(d) { return z(d.count); });

	  
	  
	  // Add a legend for the color values.
	  var legend = svg.selectAll(".legend")
	      .data(z.ticks(6).slice(1).reverse())
	    .enter().append("g")
	      .attr("class", "legend")
	      .attr("transform", function(d, i) { return "translate(" + (width + 20) + "," + (20 + i * 20) + ")"; });

	  legend.append("rect")
	      .attr("width", 20)
	      .attr("height", 20)
	      .style("fill", z);

	  legend.append("text")
	      .attr("x", 26)
	      .attr("y", 10)
	      .attr("dy", ".35em")
	      .text(String);
		
	  
	  
	  svg.append("text")
	      .attr("class", "label")
	      .attr("x", width + 20)
	      .attr("y", 10)
	      .attr("dy", ".35em")
	      .text("Count");

	  
	  // Add an x-axis with label.
	  svg.append("g")
	      .attr("class", "x axis")
	      .attr("transform", "translate(0," + height + ")")
	      .call(d3.svg.axis().scale(x).ticks(d3.time.days).tickFormat(formatDate).orient("bottom"))
	    .append("text")
	      .attr("class", "label")
	      .attr("x", width)
	      .attr("y", -6)
	      .attr("text-anchor", "end")
	      .text(xAxisText);
	  

	  // Add a y-axis with label.
	  svg.append("g")
	      .attr("class", "y axis")
	      .call(d3.svg.axis().scale(y).orient("left"))
	    .append("text")
	      .attr("class", "label")
	      .attr("y", 6)
	      .attr("dy", ".71em")
	      .attr("text-anchor", "end")
	      .attr("transform", "rotate(-90)")
	      .text(yAxisText);
	      
	      
	});
		*/
		
	};
	
	
}]);



/*heatmap		
		var width = 960,
	    height = 500;

	d3.json("data/heatmap2.json", function(error, heatmap) {
		
		
	  if (error) throw error;

	  var dx = heatmap[0].length,
	      dy = heatmap.length;

	  alert('dx: ' + dx + " dy: " + dy);
	  
	  
	  // Fix the aspect ratio.
	  // var ka = dy / dx, kb = height / width;
	  // if (ka < kb) height = width * ka;
	  // else width = height / ka;

	  var x = d3.scale.linear()
	      .domain([0, dx])
	      .range([0, width]);

	  var y = d3.scale.linear()
	      .domain([0, dy])
	      .range([height, 0]);

	  var color = d3.scale.linear()
	      .domain([95, 115, 135, 155, 175, 195])
	      .range(["#0a0", "#6c0", "#ee0", "#eb4", "#eb9", "#fff"]);

	  var xAxis = d3.svg.axis()
	      .scale(x)
	      .orient("top")
	      .ticks(20);

	  var yAxis = d3.svg.axis()
	      .scale(y)
	      .orient("right");

	  d3.select("#chart").append("canvas")
	      .attr("width", dx)
	      .attr("height", dy)
	      .style("width", width + "px")
	      .style("height", height + "px")
	      .call(drawImage);

	  var svg = d3.select("body").append("svg")
	      .attr("width", width)
	      .attr("height", height);

	  svg.append("g")
	      .attr("class", "x axis")
	      .attr("transform", "translate(0," + height + ")")
	      .call(xAxis)
	      .call(removeZero);

	  svg.append("g")
	      .attr("class", "y axis")
	      .call(yAxis)
	      .call(removeZero);

	  // Compute the pixel colors; scaled by CSS.
	  function drawImage(canvas) {
	    var context = canvas.node().getContext("2d"),
	        image = context.createImageData(dx, dy);

	    for (var y = 0, p = -1; y < dy; ++y) {
	      for (var x = 0; x < dx; ++x) {
	        var c = d3.rgb(color(heatmap[y][x]));
	        image.data[++p] = c.r;
	        image.data[++p] = c.g;
	        image.data[++p] = c.b;
	        image.data[++p] = 255;
	      }
	    }

	    context.putImageData(image, 0, 0);
	  }

	  function removeZero(axis) {
	    axis.selectAll("g").filter(function(d) { return !d; }).remove();
	  }
	});
*/		
		










/*
$scope.figure = function(){

	var x = [];
		var y = [];
		for (var i = 0; i < 50; i ++) {
			x[i] = Math.random();
			y[i] = Math.random()+1;//Math.random();
			console.log('x: ' + x[i] + ' y: ' + y[i]);
		}
		
	
	
	var layout = {
	          xaxis: {title: xtitle},
	          yaxis: {title: ytitle},//, type: 'log'},
	          margin: {t: 20},
	          hovermode: 'closest'
	      };
	
	var data = [
	  {
	    x: x,
	    y: y,
	    type: 'histogram2d'
	  }
	];
	Plotly.newPlot('chart', data);//, layout);
};
*/



