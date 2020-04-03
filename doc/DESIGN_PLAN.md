Two main components

* Player class
    * Moves
        * eventHandlers for all moves
    * Stocks (lives)
        * getStocks(), setStocks()   
    * Stamina
        * getStamina(), setStamina() 
    * abstract Character class
        * Has basic moves: (Up, down side) attacks, side-to-side movement, and jumping
        * jump()
        * upTilt()
        * downTilt()
        * forwardTilt()
        * down()
        * left()
        * right() 
        * 
* CollsionDetector class
    * Player vs Player collisions (differentiate who is attacking)
    * Player vs Foreground (platforms)
        
* Stage class
    * Boundaries
        * checkWithinBoundaries()
    * Background
        * Static or dynamic image 
        * getBackground(), setBackground() 
    * Foreground class
        * Platforms
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
        *    
    * ModelExternal
    * ViewInternal
    * ViewExternal