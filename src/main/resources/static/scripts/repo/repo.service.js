if (!awa)
    var awa = {};

if (!awa.controllers)
    awa.repo = {};

awa.repo.repoService = function ($http) {
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

this.getLunches = function() {
    return dummyData;
}
}


/*     this.searchArtist = function(query) {
     var deferral = $q.defer();
     $http.get('https://api.repo.com/v1/search?q=' + encodeURI(query) + '&type=artist')
        .then(function(response) {
            deferral.resolve(response.data.artists);
            }, function(error) {
            deferral.reject({message: "Oops, något gick fel", error: error});
            });
     return deferral.promise;
     }
} */