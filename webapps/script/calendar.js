angular.module('calendar', [])
    .controller('calendarCtrl', function ($scope, $http) {

	$http.get("Calendar")
	    .then(function (response) {
		let msg = response.data;
		tabledata = document.querySelectorAll('td');
		for (let i=0; i<tabledata.length; i++)
		    tabledata[i] = msg;
	    });
    });
