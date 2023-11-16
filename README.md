<img src="https://d3qrl2xm10iebd.cloudfront.net/img/style-guide/branding/emeals-logo-v3.svg" alt="eMeals"  width="300" height="120"/>

# Overview: eMeals (Android app)!
Welcome to eMeals, an Android application that provides a delightful culinary experience. This app shows delectable recipes, ensuring you have a diverse selection at your fingertips.

## What's included

A Java Android app with _modular architecture_, clean architecture, SOLID principles and _MVVM_.

![app_architectural_pattern](docs/app_architectural_pattern.png)

Furthermore, some libraries and frameworks such as:

* _Network connectivity interceptor_ for HTTP requests.
* _Hilt_ for dependencies injection.
* _Room_ for local storage.
* _Navigation Component_ as app navigation framework.
* _Fresco_ for loading and caching images.
* _Retrofit2_ for API requests.
* _ViewPager2_ for fragments navigation between tabs.
* _RxJava_ for Reactive Functional Programming.
* _LiveData_ for observing and updating data in the UI.
* _ViewBinding_ for activities and fragments.
* _Timber_ for debug logging purposes.
* Android Studio _EditorConfig_ file to maintain consistent coding styles.
* _jUnit_, _Mockito_ and _RxJava_ for unit testing.

## Installation

Clone this repository and import it into **Android Studio**

```bash  
git clone https://github.com/JorgeDiazz/eMealsTest.git
```  

## Build variants

Herein you can find multiple targets that the app takes into account:

|          |Staging    |Production  |
|----------|-----------|------------|  
|`Internal`|Debug      |Debug       |
|`External`|Release     |Release    |

Where the following formed variants are built for staging purposes:

- stagingInternalDebug
- stagingInternalRelease

And these ones for production purposes:

- productionInternalDebug
- productionInternalRelease
- productionExternalDebug
- productionExternalRelease

**_Sidenote:_** choose productionExternalDebug before executing the app

## Mockups

<img src="docs/1_mockups.png"/>

<img src="docs/2_mockups.png"/>

## Final result

### Launching the app

<img src="docs/1_using_the_app.gif" width="350" height="600"/>

### Scrolling the recipes list horizontally

<img src="docs/2_navigating.gif" width="350" height="600"/>

### Opening recipe details screen

<img src="docs/3_recipe_details.gif" width="350" height="600"/>

### Editing recipe title

<img src="docs/4_editing_recipe_title.gif" width="350" height="600"/>

### Opening side dish details screen

<img src="docs/5_side_dish_details.gif" width="350" height="600"/>

### Using the app in offline mode

<img src="docs/6_app_offline.gif" width="350" height="600"/>
