# Compass Web Content Analyzer
This is a simple Android application that fetches and analyzes content from the Compass website.

# Features

* Every10thCharacterRequest: Fetches content and extracts every 10th character.

* WordCounterRequest: Fetches content and counts the occurrence of each unique word.

# Architecture
This project follows a basic MVC (Model-View-Controller) architecture:

* Model: Handles data operations, such as fetching and processing web content.
* View: Displays results to the user.
* Controller: Coordinates the flow of data between the Model and the View.
* Git Flow: A basic git flow structure was used, you can find the branch history [here](https://github.com/ederdoski/CompassTest/branches) and the pull request history [here](https://github.com/ederdoski/CompassTest/pulls?q=is%3Apr+is%3Aclosed) 

Note:
Given the project's simplicity, MVC was chosen. For more complex scenarios, MVVM with data binding and dependency injection would be preferred for better separation of concerns and testability.

# Usage
Upon launching the app, click the "Fetch Data" button to initiate the requests.
Results from Every10thCharacterRequest and WordCounterRequest will be displayed as soon as they are processed.
Data is cached locally for offline viewing after the initial fetch.

* If you have problems running the app, you will get a compilation in the following path [app --> app-debug.apk](https://github.com/ederdoski/CompassTest/app)

# Improvements
To optimize performance and reduce redundant network calls:

Implement a single network request to fetch data.
Once data is retrieved, pass it to separate processes (character extraction and word counting).
This approach minimizes network overhead and speeds up data processing.

# Testing
The application includes unit tests to verify:

* Accurate extraction of every 10th character.
*Proper counting of words, case insensitivity, and handling of whitespace.

# Screen Capture

![image](https://github.com/ederdoski/CompassTest/assets/39970879/91d6814e-25d1-4845-b6df-3dfa4188b616)







