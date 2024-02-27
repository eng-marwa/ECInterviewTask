# Football League Application

Football League Application is a simple app consisting of 2 screens that show a list of competitions and their details.

## Screenshots

Include screenshots of your application here.

## APIs Used

- APIs are provided by the public service [Football Data](https://www.football-data.org/).
- Create an account and generate your API token.
- The generated API token is saved in secrets.properties which didn't upload in GitHub
- Use the Competition API: [http://api.football-data.org/v4/competitions/](http://api.football-data.org/v4/competitions/).

## Features

-  This app uses the MVI Architecture Pattern
-  Get data from APIs and cache retrieved data in EncryptedSharedPreferences.
-  Show cached data and work offline when there's no internet connection.
-  Prevent users from taking screenshots or recording the screen while opening the app.
-  Prevent MITM cyberattacks.
-  Prevent common reverse engineering and app hooking techniques.
-  The app screens support multiple screen sizes
- Unit testing
