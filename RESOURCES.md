# Information regarding Resources

**(requires Java coding)** means that you have to add Java code so that you can see your created thing

## Images (Textures)

 - The supported Image Formats are:
   - .png
   - .jpg
   - .jpeg
   - .bmp
   - .gif
   - .wbmp
   - .tiff
   - .tif
 - The image paths are defined in [TTsGames.json](src/main/resources/TTsGames.json) in the section "textures"

#### Create an Image (requires Java coding)

1. Go in [TTsGames.json](src/main/resources/TTsGames.json) to the section "textures" and add the path to your image
2. Create the image

### Animated Images

#### Steps to create an animated image:
1. Pick an Image you want to make animated
2. Create a file named <picture name (leave .png/.jpg...)>.json (for example, [Wache_0.png.json](src/main/resources/assets/textures/detective_thunder/locations/Wache_0.png.json))
3. Create an element called "base_path" (requires a String)
   - It is put before every main Image Path
4. Create an element called "file_format" (requires a String)
   - It is put after every main Image Path
5. Create an element called "paths" (requires Multiple Strings)
   - The main Image Path
6. Create an element called "delay" (requires an int/Number)
   - This value is how long it takes until the next image is displayed
7. Create an element called "repeat" (requires a boolean (true/false))
   - If set to true, it will repeat infinitely
   - If set to false, it will only do the animation once

## Sounds

 - The supported Sound Formats are:
   - .wav
   - .au
   - .aif
   - .aiff
 - The sound paths are defined in [TTsGames.json](src/main/resources/TTsGames.json) in the section "sounds"

#### Create a sound (requires Java coding):
1. Go in [TTsGames.json](src/main/resources/TTsGames.json) to the section "sounds" and add the path to your sound
2. Create the sound file
3. Add a JSON File which is named like the sound file (for example, [001.json](src/main/resources/assets/sounds/detective_thunder/phrases/001.json))
4. Create an element called "base_dictionary" (requires a String)
   - It is put before every main File Path
5. Create an element called "files" (requires a Map of two Strings)
   - The first String is the Language like "English"
   - The second String is the main File Path

## Languages

### Change Translations

1. Go to the Language File (for example, [en_us.json](src/main/resources/assets/lang/en_us.json))
2. Change the second String of a line and don't change the first String

### Implement a new Language
1. Got to [TTsGames.json](src/main/resources/TTsGames.json)
2. Search for the field languages
3. Add your language to languages
4. Search for the field language_files
5. Add the name of your language and the location of the json/json5 file of your language
6. Create this json/json5 file
7. Get the translation keys of another language (for example, [en_us.json](src/main/resources/assets/lang/en_us.json))
8. add to every Sound JSON File your new Language (explained in 5. of Create a Sound)

## Data

 - Uses .json files
 - If commands need to be used, it uses .json5 files

### Translation Keys

 - Translation Keys are defined in src/main/resources/data/<name of the game>/translation_keys.json
 - They are split into multiple different sections

### Other Data

 - Mostly is self-explanatory by reading the file name
 - The most important of those files is [unallowed_usernames.json5](src/main/resources/data/tts_games/unallowed_usernames.json5)