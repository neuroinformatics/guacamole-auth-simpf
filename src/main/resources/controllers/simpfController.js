angular.module('simpf').controller('simpfController', ['$scope', '$injector',
   function ($scope, $injector) {

      var $interval = $injector.get('$interval');
      var simpfService = $injector.get('simpfService');

      var stop;
      var times = 0;

      $scope.startWatchDogTimer = function () {
         if (angular.isDefined(stop)) {
            return;
         }
         stop = $interval(function () {
            times++;
            if (!simpfService.isActive()) {
               return;
            }
            simpfService.ping()
               .then(function checkResult(data) {
                  $scope.times = times * 5;
                  $scope.result = data.result;
                  if (data.result == 'FAILED') {
                     $scope.stopWatchDogTimer();
                     simpfService.logout();
                  }
               })
            ['catch'](function () {
               $scope.stopWatchDogTimer();
            });
         }, 5000);
      };

      $scope.stopWatchDogTimer = function () {
         if (angular.isDefined(stop)) {
            $interval.cancel(stop);
            stop = undefined;
         }
      };

      $scope.$on('$destroy', function () {
         $scope.stopWatchDogTimer();
      });

      $scope.startWatchDogTimer();
   }]);
