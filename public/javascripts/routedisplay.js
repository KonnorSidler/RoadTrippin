function displayTrips {
  $.get("@routes.HomeController.getRoutes()", function(data, status) {
    for (i = 0; i < data.length; i++) {
      console.log(data[i]);
    }
    
  });
}
