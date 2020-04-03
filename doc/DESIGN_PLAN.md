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
* Stage class
    * Boundaries
        * checkWithinBoundaries()
    * Background
        * Static or dynamic image 
    * Foreground class
        * Platforms  
        
* Menu Screen
    * Character Selection screen
    * Stage Selection Screen
    * Game Screen
        * Results screen
        * Once finished go back to character selection screen
        
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