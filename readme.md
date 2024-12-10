![Icon](src/main/resources/assets/textures/TTsGames.jpg)
# TTs Games

### [Information Regarding Resources](RESOURCES.md)

TTs Games is a small collection of self-made simple games without 3D rendering.

3D rendering may be included in a future release.

## Important Information

### Java Version: 21

- Most of the code is not very optimized
- [**AnimalMaster.java**](src/main/java/dev/TTs/TTsGames/Games/AnimalMaster/AnimalMaster.java) is **aimed to be as short as possible** and **isn't aimed to be readable**
- In the code I often use shorter names (AnimalMaster → AM)
- I do not use any of the classical loggers because I created my own
- I use **Arrays** or **Arrays of Arrays** way too much
- Nothing would work without [TTsGames.json](src/main/resources/TTsGames.json)
- All Images and Sounds are created by AI 
  - Images: [Copilot](https://copilot.microsoft.com/)
  - Sounds: [Elevenlabs](https://elevenlabs.io/)

## Goals

 - Leave more Commands so that more people can understand my code
 - Thought as a project to train to code using **Java** (and maybe **Kotlin**)

## Why Java?

- Java has very optimized Extensions like [Gson](https://github.com/google/gson)
- Java is a fast interpretive programming language
  - I do not want to compile everything each time I want to test something (which can take very long)
  - Interpretive programming languages like Python are much slower
- Java is getting many updates over time
  - The versions I am using are LTS versions [```Wikipedia Article```](https://en.wikipedia.org/wiki/Java_version_history#Release_table)
- I have already been using Java in Minecraft Mods

## Dependencies 

- I try to use only a few dependencies and not thousands like many people
- The only dependencies I use are [Gson](https://github.com/google/gson) and [Kotlin](https://github.com/JetBrains/kotlin)
- **Dependencies are automatically included in the jar file**

## Running TTs Games

### As Jar File

TTs Games is a normal Gradle Project using Gradle 8.10

1. Download the sources
2. Install Java 21 if not already installed
3. Run the command ```gradlew build``` to build the jar file
4. Go into the build/libs/, and you will find a jar file
5. Run the jar file

### Using an IDE like [IntelliJ IDEA](https://www.jetbrains.com/idea)

1. Download the Sources
2. Import the project into your IDE
3. Go to the file Main in src/main/java/dev/TTs/TTsGames
4. Run this file