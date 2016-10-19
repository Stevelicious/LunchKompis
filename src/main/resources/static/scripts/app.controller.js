if (!awa)
    var awa = {};

if (!awa.controllers)
     awa.controllers = {};

awa.controllers.AppController = function(repoService) {

    var self = this;
    var dataSet = repoService.getLunches(); // awa.repo.getLunches();
    this.search = function () {
    return "Works!"
    }

    self.message = "Kalle";
 }