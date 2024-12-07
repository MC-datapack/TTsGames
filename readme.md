# TTs Games

## Important Information

- Most of the code is not very optimized
- [**AnimalMaster.java**](src/main/java/dev/TTs/TTsGames/Games/AnimalMaster/AnimalMaster.java) is aimed to be as short as possible and **isn't aimed to be readable**
- In the code I often use shorter names (AnimalMaster → AM)
- I do not use any of the classical loggers because I created my own
- I use **Arrays** or **Arrays of Arrays** way too much
- Pretty much everything would not work without [TTsGames.json](src/main/resources/TTsGames.json)

## Goals

 - No real goals
 - Thought as a project to train to code using **Java** (and maybe **Kotlin**)

## Dependencies 

- I try to use only a few dependencies and not thousands like many people
- The only dependencies I use are [Gson](https://github.com/google/gson) and [Kotlin](https://github.com/JetBrains/kotlin)

## Running TTs Games

### As Jar File

1. Download the sources
2. Download Java 21 if not already installed
3. Run the command ```gradlew build``` to build the jar file
4. Go into the build/libs/, and you will find a jar file

### Using an IDE like [IntelliJ IDEA](https://www.jetbrains.com/idea/)

1. Download the Sources
2. Import the project into your IDE
3. Go to the file Main in src/main/java/dev/TTs/TTsGames
4. Run this file