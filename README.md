
# Upcoming Movies

Android App that allows users to set reminders for the upcoming movies they do not want to miss.

## Project milestones

1.  Firebase Authentication with email and Google account methods.

2.  Request the list of upcoming movies from The Movie Database API using Retrofit.

3.  Use Room to store the watchlist.

4.  Show a notification when a saved movie is released.

5.  Show the details of a movie using coordinator layout.


# Features

## Authentication

-   The first screen of the app asks the users to login using email/password or their Google account.

-   If a user does not have an account, it can register and create a new account.

-   The login screen should have a custom layout that matches the style of the app.


## Watchlist

-   If the login is successful the app opens a MainActivity that hosts a navigation graph and the users are prompted to the Watchlist Fragment.

-   When no movies are displayed, the app should show an image that invites the users to add a new movie.

-   The app shows the movies stored in the Room database using RecycleView and ListAdapter.

-   Each item of the list should show the movie name, release date, and poster image in a constraint layout.

-   Allow the user to add a new movie to the watchlist using a floating button.

-   When the users click a movie, it is prompted to the Movie Details Fragment using Navigation Components.


## Upcoming Movies Screen

-   The app should show the list of upcoming movies from The Movie Database API using Retrofit.

-   The app should map the response from the API to a data class model using Gson converter.

-   When the users click a movie, it is prompted to the movie details screen using a Navigation Component action.


## Movie Details Screen

-   The screen should show the title, overview, release date, poster image and popularity in a constraint layout.

-   A button for adding the movie to the watchlist should be shown.


## Notifications

-   Create an alarm for movies added to the watchlist with the time set to the release date.

-   When the alarm is reached the app sends a notification.

-   The notification should use BigPictureStyle and show the movie’s poster image.

-   When the notification is clicked the movie details screen is displayed.
