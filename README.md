
# Upcoming Movies
Android App that allows users to set reminders for the upcoming movies they do not want to miss.

## Project milestones
1.  Firebase Authentication with email and Google account methods (complete).
2.  Request the list of upcoming movies from The Movie Database API using Retrofit (complete).
3.  Use Room to store the watchlist (complete).
4.  Show a notification in the date when a movie from the watchlist is released (complete).
5.  Show the details of a movie using coordinator layout (complete).
6.  Allow the user to remove movies from the watchlist.

## Web API Key configuration
The app is connected with the Movies Databse API using a `web_api_key` located in `local.properties` file.

### Get a new API key
To create a new `web_api_key` follow the steps of this link: https://developers.themoviedb.org/3/getting-started/authentication.

### Add an API key to the project
Add the key in the `local.properties` file like this:
```
web_api_key = yourApiKey
```

# Features

## Authentication
- Login using email/password.
- Login with Google account.
- Register new users.
<img src="https://user-images.githubusercontent.com/34697638/131877168-c9930b94-8f35-4e8c-9a4e-4f6502d6d1ea.png" width="200">

## Watchlist
- Show watchlist of movies added by the user.
- Store watchlist locally.
<img src="https://user-images.githubusercontent.com/34697638/131878482-50331805-c950-465a-af50-216a1b3e1c48.png" width="200">

## Upcoming Movies Screen
- Show the list of upcoming movies from The Movie Database API.
<img src="https://user-images.githubusercontent.com/34697638/131878819-1a0b818c-4459-483e-b3e4-19273f5ea2ec.png" width="200">

## Movie Details Screen
- Show the title, overview, release date, poster image and popularity.
- Add the movie to the watchlist.
<img src="https://user-images.githubusercontent.com/34697638/131880026-524791bf-2702-4f89-86e0-16345046085d.gif" width="200">

## Notifications
- Send a notification when movies added to the watchlist reach the release date.
- When the notification is clicked the movie details screen is displayed.
<img src="https://user-images.githubusercontent.com/34697638/131879433-1527a57d-2ba8-449c-9471-f92b0ccb4469.gif" width="200">
