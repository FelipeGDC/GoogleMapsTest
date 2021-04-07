
# Google Maps Test application:

Architecture approach inspired by DDD and CLEAN concepts.

## Screens 📱
The app currently consists of 1 screen:
- A map screen, in which a Google map is loaded, and after receiveng a series of positions from the service, they are marked in the map. The markers and places change as you move in the map.

## Libraries 🛠️:
 - MVVM
 - Flow
 - Dagger
 - Ktlint
 - Android Jetpack
 - Moshi
 - Retrofit

## Structure 🎨

- __Data Layer__: Contains the repositories Implementations and one or multiple Data Sources.
  - __Datasource__: In which we have the source of the data we are going to work with, let it be the API implementation and abstraction, and/or the database. In this case, the API call.
   - __Repositories__: Repositories are responsible to coordinate data from the different Data Sources. A sort of abstraction for the data sources in order to avoid working directly with them. We make calls to them and we can ignore whether the data comes from the network or a local database.
- __DI__: the dependency injector package, where the modules and components are created.
- __Domain__: Collection of entity objects and related business logic that is designed to represent the enterprise business model.
  - __Models__: An abstraction of the objects that represent the logic of the project.
  -  __Use cases__: The interactors that define be the business logic of the application.
 - __UI__: With an MVVM pattern, everything is separated as features, the screens and logic behind them are found here.
- __Utils__: A variety of classes, extensions, and helpers to help and use across the application, that not necessarily have anything to do with the logic of the same.

## Testing 🧰
#### (There could be more tests, like the UI ones, or for the mappers in each model, but because of the type of project, I haven't tested every part of the application. I decided to test the repository, the usecase and viewmodel since I felt it was representative enough. At least for the time being.)
- JUnit
- Mockito-Kotlin
- Kluent3

## Screenshots

![](https://github.com/FelipeGDC/GoogleMapsTest/blob/master/images/imagen-1.png)
![](https://github.com/FelipeGDC/GoogleMapsTest/blob/master/images/imagen-2.png)


