* Names of all people who worked on the project
    * Thomas Chemmanoor, Benjamin Lu, Sebastian Williams, Achintya Kumar, Michael Dodd

* Each person's role in developing the project
    * Thomas Chemmanoor
        * Did the UI design for the home, stage selection, character selection screens.
        * Created the settings button and did the json editing and UI needed for the button configuration screen.
        * Made tests for the stage selection screen
        * Helped debug collision logic
    * Benjamin Lu
        * Player - Player interaction
            * Hitbox/HurtBox generation
            * Detecting when a player is damaged
        * Player - Stage interaction
            * Allow players to stand on platforms
            * Differentiate how players interact with hollow and non-hollow stages
            * Apply gravity to players when in the air
    * Sebastian Williams
        * Basic implementation for stages
        * Added lives gamemode
        * Added game over functionality
        * Added parts of animations via sprite sheets
        * Added dark mode
        * Refactored the controller to implement the API
        * Worked on some player stage interaction
    * Achintya Kumar
        * Figured out how to do sprite animations from sprite sheets. 
        * Worked on keybindings - getting multiple keys working on the same keyboard for multiplayer and also creating custom keybinding to save and load from JSON files. 
        * Converted stage information to JSON format and changed the parser to read the json data. 
        * Did all of the networking to get networked games working using the KryoNet package. Added the background and hit music. 
    * Michael Dodd
        * Outlined basic structure for the AbstractCharacter class and worked on determining the best implementation. This included finding ways to abstract the Bunny class into the AbstractCharacter class to make the addition of players easier. For stages, I outlined the general structure for our abstract Stage class and worked on the parser for our stages JSON files. Also helped with debugging and testing networked games.

* What are the project's design goals, specifically what kinds of new features did you want to make easy to add
    * We wanted to make the stage selection screen and the character selection screen to be extensible. Specifically, we wanted to make it easy to add new characters and new stages to the project. Additionally, we wanted these added stages to automatically show up on the selection screens. We were successful in doing this since all that is required to add a new stage is to create a JSON file in the correct format and add the image to the correct package.
    * We wanted to focus on information hiding, making sure that classes only have access to code elements that they require.
    * We also wanted to make our project more flexible by providing abstract classes for new classes to extend from. For example, we wanted to be able to add other selection screens as well as more characters and so we made an abstract class for each.

* Describe the high-level design of your project, focusing on the purpose and interaction of the core classes
    * Our design is modeled after MVC. The model contained everything related to characters and their movements. The view contained all UI elements. The controller contained all collision logic as well as game over checks. Finally, we organized all resources into the data folder. This contained the images we used for stages, sprite sheet images, and all json files.
    * We used a data-driven approach for 2 aspects of the projects - stages and keybindings. JSON files were used to store and create custom keybindings for players to choose what keyboard keys map to the movement and attack controls. For stages data was used to store the stage’s background image, the rectangle dimensions to create platforms, the character spawn points, and the name for playing to correct music.

* What assumptions or decisions were made to simplify your project's design, especially those that affected adding required features
    * We assumed that all stages will be created using json files and all stages will be added to the same project directory. We have also assumed that stages have to follow the same format for our program to parse. Each file starts with the background image path and is followed by the transparent rectangle coordinates that correspond with the stage’s platforms. By abiding by these rules, as long as a json stage file is stored in the correct directory and follows the same file format, the stage will automatically be added to the stage selection screen as a playable stage.

* Describe, in detail, how to add new features to your project, especially ones you were not able to complete by the deadline
    * To add a new stage to the program, one simply has to create a new json file following the same file formats as the previous stages and add the file to the correct directory in the data folder. This will automatically add it to the stage selection screen.
    * To add a new character to the program, one must add the correct spritesheets to the data directory, create a new character file that extends the abstract character class, and then add the character to the character selection screen.
    * To add a new set of keybindings, one simply has to create a json file in the same json keybinding file format as player1.json and save it somewhere on their machine. When they play the game, they can click settings, button configuration and load that keybinding data file from a file chooser.
    * To add new junit tests, one just has to create a junit test file in the test directory and run it.

