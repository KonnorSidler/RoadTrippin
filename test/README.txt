Components being tested:
*Note: Our code currently does not include data passing methods within our app yet. Because of this the functionality of the tests is slightly off. The 3 tests written are relatively simple as a result.*

spyRoutes(): This function serves to validate that the routes have been created. The getRoutes method lies within the HomeController and sshould return all information related to a specific trip.

spySetRating(): This function serves to validate that the a particular trip can be assigned a rating from the user. The setRating method lies within teh RatingsController.

spyListUsers(): This function serves to valudate that the lists of users in the system exists. the listUsers method is located in the UserAccountController.