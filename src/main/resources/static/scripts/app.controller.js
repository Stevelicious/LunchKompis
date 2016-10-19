if (!awa)
    var awa = {};

if (!awa.controllers)
     awa.controllers = {};

awa.controllers.AppController = function() {

    var self = this;

    var dummyData = {
        "lunches": [
            {
                "title": "dejt",
                "date": "10/11",
                "time": "11:30",
                "isPublic": false,
                "place": "dovas",
                "users": [
                    "user1", "user2"
                ]
            },
            {
                "title": "business",
                "date": "02/12",
                "time": "12:00",
                "isPublic": true,
                "place": "pong",
                "users": [
                    "user9", "user2"
                ]
            },
            {
                "title": "breakup",
                "date": "08/11",
                "time": "11:00",
                "isPublic": true,
                "place": "mcdonalds",
                "users": [
                    "user1", "user6"
                ]
            }
        ]
    }
    // var dataSet = repoService.getLunches(); // awa.repo.getLunches();
    this.search = function () {
    return "Works!"
    }


    self.data = dummyData;
    self.message = "Kalle";
 }