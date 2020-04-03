*  Overview
    *  Sebastian and Thomas are going to be working on the front end parts of the game
    *  Achintya, Ben, and Michael will be working on backend and game logic
*  APIs:
    *  Model Internal: 
        *  The movement controls along with getStocks() and getStamina() will be worked
          on by Ben, Achintya, and Michael. However, in terms of the animation,
          Thomas and Sebastian will also help. Our goal is to get these done by
          Sprint 1 as they are critical to our game functioning. Since this API
          is arguably one of the most critical to our game, it is likely that
          everyone will have a hand in helping in order to get it done by Sprint 1.
    *  Model External:
        *   Sebastian and Achintya will work on implementing this API. This API
          deals with the basic game controls, like setting stamina and lives. This
          will require interfacing between the model and view. Our goal is to get this
          done by Sprint 1, as again, it is a critical feature in making our game work
          as the game can't know when to be done if it not accuratley representing
          how many lives a player has left, for example.
    *  View Internal:
        *   Sebastian will be the one primarily working on this API, although if it
          proves difficult others may help. This API deals with things like determining
          if a stage/character has been chosen along with displaying the score
          and reseting the game. These are useful, however not critical to the game
          functioning as in the early days the project can be restarted manually
          and the data chosen from a properties file. Because of that, our
          hard deadline for this is Sprint 2, although ideally it is completed by
          Sprint 1, if time permits.
    *  View External:
        *  Thomas and Sebastian will work on this as it is critically front end.
          Our goal is to get this done by Sprint 1, which seems very likely as it
          is only one method, displayScore(). This needs to get done by Sprint 1
          as knowing the score is critical to any game. Moreover, completing this
          by Sprint 1 wont be particularly challenging as the score logic will be
          seperate from the view - the view just needs to display it.
    *  Controller Internal:
        *  Michael and Ben will work on the Controller Internal API, which
          contains methods that deal with basic game logic, like whether the player
          is out of bound and whether the game is over. Our goal is to get at least
          the isGameOver() method by Sprint 1 as that is critical to any ruleset
          we implement. As the boundry method is mostly useful for the Super
          Smash Bros varient, the deadline for that will by Sprint 2, although
          if things goal well in the first week it is likely it will be done by
          Sprint 1 as the SSB variant is the team's favorite.
*   General Timeline:
    *   Sprint 1:
        *  For the first sprint, the team aims to have a funtioning fighting game
           that includes basic collision, player moves, and general game rules.
           As collision will likely be the hardest part of this project, all team
          memebers will help out with a lot of core features of the game during 
           this week
    *   Sprint 2:
        *   For the second sprint, the team aims to refine the features from the
           first week, aiming to perfect the basic GUI and game rules. The team
           will also aim to refine the multiplayer aspect of this along with cleaning
           up any collision or animation issues from the first sprint.
    *   Complete:
        *   For complete, the team will aim to build off the original implementation
           by adding to characters, potentially new rules, and clean up any major bugs
           that remain. This will be the week where extensions are added that 
           fit with the game's API/design from the first two sprints. By this week
           the team aims to have the basic game working well with the core functionality
           already complete.