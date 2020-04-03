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
        * jump()
        * upTilt()
        * downTilt()
        * forwardTilt()
        * down()
        * left()
        * right() 
         
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