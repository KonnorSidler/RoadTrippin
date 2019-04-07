//Main script
//No filtering is added right now, so final waypoints could end up being gas stations/reststops and unwanted locations

function calculateRoute(directionsService, directionsDisplay) {
    return new Promise(function(resolve, reject) {
        var numberOfWaypoints = parseInt(document.getElementById("number_of_waypoints").value);
        var increment = (100/ (numberOfWaypoints+1))/100;
        var decimal = (100/ (numberOfWaypoints+1))/100;

        var start = document.getElementById("start_input_form").value;
        var end = document.getElementById("end_input_form").value;
        var directionRoute;
        var waypnts = [];
        var waypntName = [];
        var offSet = 0;

        var request = {
            origin: start,
            destination: end,
            travelMode: "DRIVING",
            provideRouteAlternatives: false
        };

        //Initial request for information between start and end variables
        directionsService.route(request, function(initial_result, status) {
            if (status === 'OK') {
                directionRoute = initial_result.routes[0].overview_path;
                for(var i = 0; i <directionRoute.length; i++) {
                    if(i == parseInt(directionRoute.length*decimal)) {
                        if(i != directionRoute.length) {

                            //Logic for randomly picking waypoint type
                            var waypointType;
                            if (document.getElementById("trip_type").value == "Food") {
                                var waypointType;
                                var randomType = Math.floor(Math.random() * 3);
                                if (randomType == 1) {
                                    waypointType = 'bar';
                                } else if (randomType == 2) {
                                    waypointType = 'cafe';
                                } else {
                                    waypointType = 'restaurant';
                                }
                            }else {
                                var randomType = Math.floor(Math.random() * 3);
                                if (randomType == 1) {
                                    waypointType = 'amusement_park';
                                } else if (randomType == 2) {
                                    waypointType = 'museum';
                                } else {
                                    waypointType = 'park';
                                }
                            }
                            //Request for Google Places, finds a place and adds to an array to be used with final MAPS api call
                            placeRequest = {
                                location: new google.maps.LatLng(directionRoute[i].lat(), directionRoute[i].lng()),
                                radius: '50000',
                                type: waypointType
                            };
                            service.nearbySearch(placeRequest, function(placeResults, status) {
                                var randomNumber = Math.floor(Math.random() * placeResults.length);

                                //Logic for API not finding a place
                                if (placeResults.length == 0) {
                                    console.log("Cannot place waypoint because no waypoints found");
                                    offSet += 1;
                                }
                                else {
                                    waypntName.push(placeResults[randomNumber].name);
                                    waypnts.push({
                                        location: new google.maps.LatLng(placeResults[randomNumber].geometry.location.lat(), placeResults[randomNumber].geometry.location.lng()),
                                        stopover: true
                                    });

                                    if(i == directionRoute.length) {
                                        var newRequest = {
                                            origin: start,
                                            destination: end,
                                            travelMode: "DRIVING",
                                            waypoints: waypnts,
                                            optimizeWaypoints: true
                                        };

                                        //Final request with waypoints array
                                        directionsService.route(newRequest, function(final_result, final_status) {
                                            if (status ==='OK') {
                                                if(final_result.geocoded_waypoints.length == ((numberOfWaypoints - offSet) + 2)) {
                                                    if(final_result.geocoded_waypoints.length != numberOfWaypoints +2) {
                                                        window.alert("Some points removed due to lack of available location.");
                                                    }
                                                    directionsDisplay.setDirections(final_result);
                                                    console.log(final_result);

                                                    /*
                                                    var data = JSON.stringify(final_result);
                                                    $.ajax({
                                                        type : 'POST',
                                                        url : '@routes.HomeController.testLink',
                                                        data : data,
                                                        dataType: 'json',
                                                        contentType: 'application/json',
                                                        success : function(data) {
                                                            console.log("Success");
                                                            console.log(data);
                                                        },
                                                        error : function(data) {
                                                            console.log("Fail");
                                                            console.log(data);
                                                        }
                                                    });
                                                    */


                                                    resolve(final_result);

                                                }
                                            }
                                        });
                                    }
                                }



                            });



                            decimal += increment;
                        }

                    }
                }
            }
            else {
                window.alert("Cannot route");
                reject();
            }
        });
    });


}

