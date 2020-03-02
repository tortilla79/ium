angular.module('login', [])
    .controller('loginCtrl', function ($scope, $http) {

        $scope.sendData = function() {
	    let data = {
		username: $scope.username,
		password: $scope.password
	    };
	    $http.post("Login", data)
		.then(function (response) {
		    let msg = response.data;
		    switch(msg) {
		    case "ok":
			$scope.requestans = "success";
			break;
		    case "usr":
			$scope.requestans = "username o password errato";
			break;
		    default:
			$scope.requestans = msg;
		    }
		    
		    if ($scope.requestans === "username o password errato") {
			$scope.username = "";
			$scope.password = "";
			document.getElementById("username").focus();
		    } else if($scope.requestans === "success") {
			document.location.href = 'user.html';
		    } else {
			$scope.username = "";
			$scope.password = "";
		    }

		});
	    
	}
    });
		

