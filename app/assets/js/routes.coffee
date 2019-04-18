	$ ->
	$.get "/trips", (trips) ->
	  $.each trips, (index, tripInfo) ->
	    $('#tripInfos').append $("<li>").text "Trip ID: " + tripInfo.tripId + " - Start Location: " + tripInfo.startLocation + " - End Location: " + tripInfo.endLocation
