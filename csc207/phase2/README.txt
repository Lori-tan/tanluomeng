# Group_0557

### Project Description

* Background
    * A man had a endless nightmare, in his dream he turned in to a hero who was trapped in a
      dungeon, he had to try his best to overcome the obstacles, complete all the levels to end his
       nightmare.

* Game description
    * This is a game describing a hero who is trying to escape from the dungeon. It now contains
      three games, each consists of three levels.
    * In the first game, the player would win by jumping between the stairs to get the crystal. Each
      jump would cause 10 deduction in points. Obtaining a life could allow the player to retry the
      following games without having to restart the level.
    * In the second game, the player would win by controlling the air-balloon and passing through
      the lightening, buildings and clouds, and finally reaching the end line. Touching lightening,
      buildings and clouds would lose one life, and collecting one crystal can get 10 scores.
    * In the third game, the player would win by pushing all the sheep into the sewer to end
      his/her nightmare, with 10 scores for each sheep. The player would lose one life if fall
      into the sewer.

* UML diagrams
    * Under this directory, there is one uml diagrams for each game package, design3.pdf contains
      the uml diagram of game1, design4.pdf contains the uml diagram of game2 and design5.pdf
      contains the uml diagram of game3.
      The design2.pdf contains the uml diagram of the activity package.
      There is also a package diagram that shows the association among each package, which is
      design1.pdf.

### Setup Instructions

* Open Android Studio
* Select New Project from Version Control –> Git
* Copy and paste: https://markus.teach.cs.toronto.edu/git/csc207-2019-09/group_0557
* Click Clone. When being asked whether you want to create an Android Studio project, click Yes.
* Keep the default: Create project from existing sources. Click Next.
* On the next screen you're asked for the project name and location.
* Append this to the end of the location: /phase2
* Source files for your project have been found. Make sure they're selected and click Next.
* Review the libraries and click Next.
* Use the suggested module structure. Click Next.
* Select the SDK, Android API 29 Platform. Click Next.
* Click Finish. You may get an error, and you can safely ignore it.
* Run it.
* Note: this program should be able to run without the need of installing any additional libraries
  or plugins.


### UI Instructions

* A new user needs to first sign up with a username and password.
    * Existing users on this computer could enter username and password to log in right away.
* A new user may select NEW GAME then select any level among the three to start playing. The higher
  the level, the higher the difficulty of the game.
    * Existing users may select LOAD GAME to return to the level of the game when last quits the app.
* Inside each game, there is an Instruction button which includes brief introduction to new users
  how to play this game.
* After the user successfully meets the objective of Game 1 or Game 2, the consequent game is
  automatically loaded up for the user to continue playing.
* After the user completes the third game, the user can select whether he/she would want to save
  this score to the history score of this account.
* The scoreboard would then be displayed to the user and the user may directly choose from that
  screen to whether keep playing a different level or quit.


### Programmer Instructions

* Programmers trying to further develop this game should
    * first review following sections:
        * Project Architecture
        * How to extend this game?
        * Development Standard
        * Naming Convention
    * then check out UML diagrams placed under app package of the project to get a sense of
      relationships among all packages and the relationships between class files under a specific
      game package.

## Project Architecture

* This project mainly implements Model–View–Presenter(MVP) architectural patter. A general logic of
  MVP architecture is usually considered in this way:
    * The model defines the data.
    * The view displays data (the model) and routes user commands (events) to the presenter to act
      upon that data.
    * The presenter acts upon the model and the view. It retrieves data from repositories (the
      model), and formats it for display in the view.

* As for this project, the implementation of MVP architecture is:
    * The model layer
      * defines certain data to be displayed or the act upon the user interface like moving of the
        playable character.
      * consists of hero package which contains the playable characters of each game along with an
        item package containing all items that are involved in the game what the player could
        interact with.
    * The view layer
      * defines passive class(es) that displays data from the model package and routes the commends
        to the presenter layer to allow further process on the action or data.
      * consists of a view class which is responsible for updating drawing and displaying items on
        screen through keeping track of presenter is processing the coming data and commends.
    * The presenter layer
      * defines the transportation classes who connect the view layer and model layer together, and
        acts as the supervising controller in the whole game. It retrieves data from model package,
        and formats it to display on the view.
      * consists of a builder packages which help build up all levels for each game; a processor
        package which contain the Manager and Thread class of the specific game; a strategy package
        that mainly deal with score tracking and updating component of the game.


## How to extend this game?

* Add new levels to an existed game:
    * In the packages of either game1, game2, game3, open the presenter package and add the
      Level(N)Builder to the builder package.
    * The Level(N)Builder is required to implement either game1Builder, game2Builder or game3Builder
      depends on what game the programmer whats to add the level(s) to.
    * The programmer should add a new button-listener to the ChooseLevelActivity.java in the
      activity-interaction package and modify the activity_game1_level.xml in the layout package to
      allow entrance to the added level.

* Add new games:
    * The new game is required to be first placed under its own package; under that newly created
      package, there should be a model package, a presenter package and a view package.
    * The model package mainly consists of the user-controlled character(s) and in-game item(s).
      The user-controlled character(s) should be placed in the hero package while the item(s) should
      be placed in the item package.
    * The presenter package consists of builder package, processor package and strategy package.
        * The builder package should contain the BuilderFactory and the Game(N)Builder Interface,
          in which the programmer should also add the level builders to the Builder. The default
          number of levels is three and if the programmer whats to add more levels to the game,
          please consult "Add new levels to an existed game". The number of levels are not
          recommended to be fewer than three.
        * The processor package should contain the Manager class and Thread class of the game. The
          programmer could also put any java files which are related to game processing into the
          package.
        * The strategy package contains mostly of the score tracking and updating component of the
          game. The Game(N)Score.java should extend the GameScore.java and implements the
          ObserverInterface.
    * The view package consists of the Game(N)View.java.
    * The programmer should add the Game(N)Activity file to the activity.game package.
    * Super classes are in the game package and the programmer should consult those files in
      association with those in the helper package to assist building up the game.
    * If felt existing interfaces and abstract classes do not assist the wanted design by much,
      strongly recommended is to first introduce new abstract classes or interfaces to this game.
      This is aimed to keep abstraction at the focal point of this program.
    * Always remember, "Abstractions should not depend upon details. Details should depend upon
      abstractions."
    * Strictly following the SOLID principle and MVP architecture, one may find this game to be
      extremely straightforward to extend and the program would also be error-free.


## Development Standard

* Inheritance and abstraction should be employed to get rid of redundant codes under two different
  packages.
* Encapsulation among classes should be a strong focus with the help from the structure of this
  program. See ### Project Architecture for specific details.
    * Within specific classes, getter and setter methods should be added for private variables and
      helper methods should be included to break down long methods that tend to get messy.
* Visibility modifiers for classes/methods/variables should be kept to as strict as possible.
    * The suggested degree of visibility modifiers by the IDE is recommended to follow.
* All class files inside a package should be organized to the same level.
    * Preferably, what's inside a package should be either all packages or all class files in order
      to facilitate searching for files.
* Javadoc should be coherent and straightforward throughout all source codes, which would all
  classes, methods, and variables.
* The google-java-format tool should be employed to format all source codes.


## Naming Convention (refer to https://www.geeksforgeeks.org/java-naming-conventions/)

* classes and interfaces
    * Class and Interface names should in mixed case with the first letter of each word capitalized.
    * Avoid abbreviations.

* packages
    * Package name is written in lowercase letters.

* methods
    * Methods should be verbs, in mixed case with the first letter lowercase and with the first
      letter of each internal word capitalized.

* variables
    * Variables' name should not start with '_' or '$'.
    * One-character variable names should be avoided except for temporary variables.
    * Common names for temporary variables:
        i, j, k for integers(most occasion for index);
        c, d, e for characters.
    * We recommend:
        Boolean variable names start with is/has.
        Variable names adopt their class name. For instance, the name of a variable of Game1Manager
        class can be game1Manager.

* Constant variables
    * Constant variables should be all uppercase with words separated by underscores ('_').
