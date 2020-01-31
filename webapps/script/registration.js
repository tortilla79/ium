angular.module('registration', [])
    .controller('regCtrl', function ($scope, $http) {

        $scope.sendData = function() {
	    let data = {
		username: $scope.username,
		email: $scope.email,
		password: $scope.password,
		passwordrip: $scope.passwordrip
	    };
	    $http.post("Registration", data)
		.then(function (response) {
		    let msg = response.data;
		    switch(msg) {
		    case "ok":
			$scope.requestans = "success";
			break;
		    case "uname":
			$scope.requestans = "username gia in uso";
			break;
		    case "psw":
			$scope.requestans = "conferma password errata";
			break;
		    default:
			$scope.requestans = msg;
		    }
		    if ($scope.requestans === "username gia in uso")
			document.getElementById("username").focus();
		    else if($scope.requestans === "conferma password errata") {
			$scope.password = "";
			$scope.passwordrip = "";
			document.getElementById("password").focus();
		    }
		    else {
			$scope.username = "";
			$scope.email = "";
			$scope.password = "";
			$scope.passwordrip = "";
		    }
		});
	    
	};
	
    });
