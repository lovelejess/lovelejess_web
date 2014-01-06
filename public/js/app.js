angular.module("getbookmarks.services", ["ngResource"]).
    factory('Eat', function ($resource) {
        var Eat = $resource('/archivedEats/:eatID ', {eatId: '@id'});
        Eat.prototype.isNew = function(){
            return (typeof(this.id) === 'undefined');
        }
        return Eat;
    });

angular.module("getbookmarks", ["getbookmarks.services"]).
    config(function ($routeProvider) {
        $routeProvider
            .when('/', {templateUrl: '/assets/views/stories/list.html', controller: EatListController})
            .when('/eat/new', {templateUrl: '/assets/views/stories/create.html', controller: EatCreateController})
            .when('/eat/:eatId', {templateUrl: '/assets/views/stories/detail.html', controller: EatDetailController});
    });

function EatListController($scope, Eat) {
    $scope.stories = Eat.query();
    
}

function EatCreateController($scope, $routeParams, $location, Eat) {

    $scope.eat = new Eat();

    $scope.save = function () {
    	$scope.eat.$save(function (eat, headers) {
    		toastr.success("Submitted New Eat");
            $location.path('/');
        });
    };
}


function EatDetailController($scope, $routeParams, $location, Eat) {
    var eatId = $routeParams.eatId;
    
    $scope.eat = Eat.get({eatId: eatId});

}
