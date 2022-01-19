# Asteroid Radar

Asteroid Radar is an Android application that allows its users to view asteroids that are near the earth. The application contains a single activity with multiple fragments.

## Built With
The application has been built with the following tools:

* [Kotlin](https://www.kotlinlang.org/)
* [Android Studio](https://www.developer.android.com/)
* [XML](https://en.wikipedia.org/wiki/XML)
* [Material Icons](https://fonts.google.com/icons)
* [Retrofit](https://square.github.io/retrofit/)

The application utilized the Android Jetpack Components for its working. These components include:

* [Data Binding Library](https://developer.android.com/topic/libraries/data-binding)
* [Lifecycle Components](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Navigation Component](https://developer.android.com/guide/navigation)
* [Room Library](https://developer.android.com/training/data-storage/room)
* [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager?gclsrc=ds&gclsrc=ds)


# Getting Started
Feel like this is something that interests you, let us get you up and running.

## Prerequisites
In order to run this project you need to have the following installed:

* Java 1.8+ (Required to run Kotlin applications)

    [JDK Install](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)

* Android Studio 4.0+

    [Download Android Studio](https://developer.android.com/studio?gclid=Cj0KCQiAw9qOBhC-ARIsAG-rdn7VX4kjwjz9K8jzhfx3e8zA05HB1xKz0LEcIaYrq8KCwyAHfpIOfl4aAjikEALw_wcB&gclsrc=aw.ds)

* Android Device (Physical or Emulator available on Android Studio)

## Installation

To install the application you will need to do the following:

* Clone the repository
    
        git clone https://github.com/otsembo/UND-Projects.git

* Launch Android Studio
* Open the project folder from android studio

        File > Open Project > Select Asteroid Radar

* Get an api key from [NASA API](https://api.nasa.gov/)
* Wait for the project to build and index.
* Create a file in the ```common``` package called ```ApiKeys.kt```
* Add an object

        object ApiKeys { 
                const val NASA_API = "KEY HERE"
        }

* Rebuild the project
* Run the project from the Android Studio menu

        Build > Run 'app'

NB: There is an alternative ```README_.md``` file that contains the requirements for this project.

# Usage

The application is simplistic and not to be used for production level. It is a simple approach to building lifecycle aware applications.

Check out some snaps of the application

 Home | Detail hazardous | Detail non-hazardous | Dialog |
 ----- | ------- | ----------- | -------- |
 <img src="screenshots/screen_1.png" width="150"> | <img src="screenshots/screen_2.png" width="150"> | <img src="screenshots/screen_3.png" width="150"> | <img src="screenshots/screen_4.png" width="150"> |

