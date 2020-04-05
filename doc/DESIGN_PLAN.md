# Introduction

For this project we have decided to create our own version of Super Smash Bros the video game. We will simplify portions of it in early Sprint and add new features as time allows. The most important areas for flexibility will be in adding new characters for the user to choose and new stages for the player to play on. For this project we are going to use an MVC design pattern.The major parts of our design will be the model and the view. The view will handle the screens that are displayed to the user for selection of Stages and Characters while the Model will contain classes for the Characters and DetectingCollisions and creating the Stage that the player uses.

# Overview

The two main components of our design is the model and view with the controller acting as an intermediary between the two. We will have a Player, CollisionDetector, Stage, and Menu Screen class. The player class will include methods for each of the actions the player can perform such as downSmash(), forwardSmash(), and jump(). This abstract Player class will be extended for each of the characters. Within the CollisionDetector class, there will be 3 main types of collisions to detect. These are Player vs Player collisions (this will need to be able to differentiate who is attacking), Player vs Foreground (platforms), and Player vs Boundary. Each type of collision will have its own method internal to the CollisionDetector class. The Collision Detector will also be responsible for checking to see if the game is over using the isGameOver() method. Our abstract Stage class is the type of object that holds all the information of a specific game. This will include the boundaries of screen as well as the background for the screen. The methods getBackground() and setBackground() will allow the controller to access this ImageView so that it can alter it. There will also be ImageViews that represent the platforms for the levels. These will be linked with the Collision Detector so that players will correctly land and hit them. Finally there is the general abstract SelectScreen class. This will be implemented in a general fashion by allowing it to have various buttons with text and actions. This class will be extended by CharacterSelectionScreen and StageSelectionScreen which will have setCharacter() and setStage() methods respectively. In addition we will have a general Game Screen class that is displayed for each Game the user creates. This will have methods such as displayScore() and resetGame() both of which would be called by the Controller when the CollisionDetector decides a game is over.

# Design Details

APIS
* ModelInternal
    * The ModelInternal API handles mapping all keyboard button presses to each character’s moves. We can also interface with the key info about a player’s avatar in game: stocks & stamina.
* ModelExternal
    * The ModelExternal retrieves information from the front end to set up the selection screens and player information.
* ViewInternal
    * The ViewInternal recognizes when selections are made or changes the game status when users progress through a game’s stages.
* ViewExternal
    * The ViewExternal visualizes the final score of the bout between two players.
* ControllerInternal()
    * The ControllerInternal checks the state of the game and calls the appropriate functions when needed.
* ControllerExternal()
    * N/a



# Example games

We are considering this a complicated game to implement and therefore only planning on implementing the initial version however it has three different types of gameplay. These are described below:

* Smash with Stocks - The winner is determined by using lives 
* Shasm with Time - The game is times so that it can not go on for a long time
* Smash with Health - The game is played by players losing health by getting attacked by other players

# Design Considerations

One of the main things we discussed at length was the general design pattern we wanted to use. The two main possibilities were a MVC model with a central controller that exchanged information and a model that eliminated the need for a controller as much as possible and instead used binding. For now we have decided to use an MVC model because we figured it would be more efficient for the controller to do central tasks like ending the game or starting a new game.


Two main components

* Player class
    * Moves
        * eventHandlers for all moves
    * Stocks (lives)
        * getStocks(), setStocks()   
    * Stamina
        * getStamina(), setStamina() 
    * Abstract Character class
        * Has basic moves: (Up, down side) attacks, side-to-side movement, and jumping
        * jab()
        * upSmash()
        * downSmash()
        * forwardSmash()
        * jump()
        * down()
        * left()
        * right() 
        * special()
         
* CollsionDetector class
    * Player vs Player collisions (differentiate who is attacking)
    * Player vs Foreground (platforms)
    * Player vs Boundary
        * checkWithinBoundaries() 
    * isGameOver()
        
* Abstract Stage class
    * Boundaries
    * Background
        * Static or dynamic image 
        * getBackground(), setBackground() 
    * Foreground class
        * Platforms (could be an arraylist of bounded imageviews)
            * getImagesViews() 
        
* Menu Screen
    * Character Selection screen
        * eventHandler that detects when character is chosen
        * setCharacter()
    * Stage Selection Screen
        * eventHandler that detects when stage is chosen
        * setStage()
    * Game Screen
        * Results screen
            * displayScore() 
        * Once finished go back to character selection screen
            * resetGame()
        
* Overall UI flow
    * SelectionScreen class (Abstract)
        * CharacterScreen class
        * StageScreen class
    * GameScreenVis class (bound)
    * GameScreenModel class (bound)
    
* APIS
    * ModelInternal
        * eventHandlers for all moves
        * getStocks()
        * getStamina()
        * jump()
        * upTilt()
        * downTilt()
        * forwardTilt()
        * down()
        * left()
        * right() 
        * setBackground()
    * ModelExternal
        * setStock()  
        * setStamina()
        * getImagesViews()
        * setCharacter()
        * setStage()
        * resetGame()
    * ViewInternal
        * eventHandler that detects when character is chosen
        * eventHandler that detects when stage is chosen
        * displayScore()
        * resetGame()
    * ViewExternal
        * displayScore() 
    * ControllerInternal()
        * checkWithinBoundaries()
        * isGameOver()
    * ControllerExternal()
        * 
        
        
# Presentation Info
* What should be open?
    * Ability to add new characters and stages. Add new preferences. Add more than two players. Ability to add more UI components.
* What should be closed?
    * Networked players. Close the ability to add more movement options. Collision detection logic.
* Controller API
    * Collision Detector - Detect attacks between sprites in the game to determine if the attacks connected.
* Model external API
    * Sets stage, character, and stamina in the backend so the front end may reflect that backend for the player to see. 
* Alternative designs
    * We considered having all our classes in their own topic packages like an engine or a player package. The reason we aren't doing this is that we're still going to use this idea but separate those topical packages into the MVC. This will provide two layers of organization instead of just one.