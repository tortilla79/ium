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
		    let field = msg.split(" ");
		    switch(field[0]) {
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
			document.location.href = field[1];
		    } else {
			$scope.username = "";
			$scope.password = "";
		    }

		});
	    
	}
    });
		

