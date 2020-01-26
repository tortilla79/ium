angular.module('ripcalendar-filter', [])
    .controller('filter-ctrl', function($scope, $http){
	$http.get("FilterServlet", {params: {subject: $scope.subject, teacher: $scope.teacher}})
	    .then(function(response)) {
		let tdArray = document.querySelectorAll('td');
		for(let i=0; i<tdArray.length; i++)
		    tdArray[i].innerHtml = response.data;
	    }, function(response) {
		$scope.search-error.textContent = response.status + " " + response.statusText;
	    }
	
    })
