	$ ->
	$.get "/trips", (trips) ->
	  $.each trips, (index, tripInfo) ->
	    $('#tripInfos').append $('<a href="/tripcreate" class="button">').text "Trip ID: " + tripInfo.tripId + " - Start Location: " + tripInfo.startLocation + " - End Location: " + tripInfo.endLocation
