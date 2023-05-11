## POC-New York Times Articles API
This is the sample project for new your times api integration 

## TECH STACK

- Architecture MVI which extension of MVVM
- Language KOTLIN
- UI build In XML
- Data Binding Implemented
- Dependency Injection is done by HILT
- IDE(Android Studio Electric Eel | 2022.1.1 Patch 2)
- Network Call Retrofit
- Convertor GSON
- Background Work using Coroutines
- Extra Layer of UseCase for better Architecture
- Navigation using NavEditor
- 
## OnBoarding

- Install JDK
- Install Android Studio Electric Eel
- Open Project in Android Studio Electric Eel
- Create Emulator or connect Real device 
- Build project using IDK 
- Run Project on Phone Or Emulator 
- For Logs Open Logcat network logging is implemented

## Basic Flow

- Run the application Main Activity will star
- Activity and nav editor with destination of MostPopularListFragment
- MostPopularListFragment created when its ViewModel is create load the funtion of articles 
- Articles function calls usescase of getting articles 
- UseCase will verify any business conditions and get data from repository 
- Repository is interface which return list of articles main object 
- Repository and repoImp class which do the network call and return data from network or it could just get data from local db
- List is shown using recycler View 
- Click on its item event triggered 
- Using Navigation Editor navigated to details screen and send click data object to that screen 
- Using Navigation Editor navigated to details screen and send click data object to that screen 
- Detail Screen will collect data given by previous screen and load into the UI 
