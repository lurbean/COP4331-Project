# COP4331 Project: Mortal Pongbat

## Vision Statement
Mortal Pongbat will be a unique twist to the classic Pong game by incorporating elements of a fighting game. While previous variations of Pong may change the playing field or the amount of players, our game will allow players to select from a variety of characters each with a unique speed, power, and way to hit the ball, that encourages a new way to master the game. For those who want to compete with their friends one on one, the goal is that it will provide a familiar entry point to fighting games that anyone can enjoy.

**Patrick Burton | Adam Bush | Brandon Cuevas | Alex Lam | Lavine Von**

## Table of Contents
### [1. Product Backlog](#product-backlog)
### [2. Sprint Backlog](#sprint-backlog)
### [3. Burndown Chart](#burndown-chart)
### [4. Design Documents](#design-documents)
### [5. Source Code](#source-code)
### [6. Software Tests](#software-tests)
### [7. Demonstration](#demonstration)
### [8. Build Instructions](#build-instructions)
### [9. Appendix](#appendix)

## Product Backlog
| Story 	| Priority 	| Effort 	| Validation 	| Status 	|
| ------- |:---------:|:-------:| ----------- | --------------------- |
| As a developer, I want a continuously-maintained list of supported user controls, so I can maintain a "help" suite for users and see an overview of functionality 	| 2 	| 1 	| Each time a developer pushes an update mentioning controls, it must be paired with an update to the list of controls in the repository 	| Terminated 	|
| As a developer, I want to be able to be able to add an ability that is sensitive to a certain key - for example, I want to be able to add a method and assert that method will be called whenever a keyboard user presses "Q" and whenever a gamepad user presses "right trigger" 	| 2 	| 3.5 	| A new feature is added using this functionality and works flawlessly for both control schemes - routine development will provide regression testing 	| Terminated 	|
| As a user, I want to be able to select an AI as my opponent so I can explore functionality 	| 2 	| 1 	| Select vs. AI, try to start a game 	|Terminated 	|
| As a user, I want multiple characters to choose between, each with unique abilities 	| 3 	| NAN 	| N/A 	| Terminated 	|
| As a user, I want a character select screen that provides clear and concise description of my options 	| 3 	| 3 	| Ensure each character has associated information, and check with customers (friends, family, classmates) that the provided information is useful and sufficient 	| Terminated 	|
| As a user, I want to see game-relevant information such as ability status prominently on the game screen 	| 3 	| 4.5 	| Test that each player's hitpoints and abilities reflect changes, and check in-game timers against out-of-game timers 	| Terminated 	|
| As a user, I want to return to the menu after a game ends, so a new game can be started and configured 	| 5 	| 1 	| Verification that the menu opens after a victory in either player's favor or a manual quit 	| Terminated 	|
| As a user, I want to be able to quit a game and return to the main menu, so I can change any settings or restart any matches I no longer want 	| 5 	| 1 	| Verify quit button returns to the menu from all control schemes 	| Terminated 	|
| As a user, I want the option to replay or quit the game when one of the players lose|3 |3 |Verification that when one of the players loses, the respective win/lose screen pops up|Terminated|

## Sprint Backlog
### Sprint #1: September 16, 2018
| Story ID | Story                                                  | Priority|Effort|Validation                                        |
|:-:| ------------------------------------------------------ |:-------:|:----:| ------------------------------------------------ |
|1.01|As a user, I want to start the game, so I can play|1|1|The main menu is shown on program start|
|1.02|As a user, I want to have a menu, so I can choose to start a match|5|3|The main menu has a button to start a match|
|1.03|As a user, I want to see the ball move, so I can try to play with it|1|2|The ball moves across the playing field|
|1.04|As a user, I want to use the keyboard, so I can move my paddle up and down|1|3|The paddle moves up and down, depending on keyboard input|
|1.05|As a user, I want to use an Xbox controller, so I can move my paddle up and down|3|4|The paddle moves up and down, corresponding to input from an Xbox controller|
|1.06|As a user, I want to be able to hit the ball with my paddle, so I can try to keep the ball in play|1|3|The ball bounces back when it hits a paddle|
|1.07|As a user, I want the ball to stay in play when it can't be hit, so the game can continue|1|2|The ball bounces back when it hits a boundary wall|
|1.08|As a user, I want to have some control over the ball's direction when my paddle hits it, so I have more control|3|2.5|The ball has a reflection velocity corresponding to the velocity of the paddle|
|1.09|As a user, I want to know when I score points, so I can know who is winning|2|3|Each player has a health bar, which is lowered when a ball is missed|
|1.10|As a developer, I want to be able to change paddle damage and speed, so I can create different characters|4|4|Varying amounts of damage can be dealt; paddles can move with different speeds|

### Sprint #2: October 7, 2018
| Story ID | Story | Priority | Effort | Validation | Status | Assigned Member | Pending Requirements |
|:--------:|----------------------------------------------------------------------------------------------------------------------------------------------------------------|:--------:|:------:|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-----------:|:---------------:|:-------------------------------------------------------------------------------------------:|
| 2.01 | As a user, I want a timer to show me the duration of the game, and I want something to ensure a game doesn't go on for too long | 3 | 3 | See individual requirements | 7-Oct | Adam | N/A |
| 2.01.1 | Acquire images of all digits in two colors to support the two following requirements | N/A | N/A | Self-evident | 7-Oct | Adam | N/A |
| 2.01.2 | Implement a visual count-down timer from 99 to 0 | N/A | N/A | In Step Five, watch the timer and ensure it counts down once per second for each transition. If it does, this test passes | 7-Oct | Adam | N/A |
| 2.01.3 | Extend the visual count-down to track back up to 99, this time in red, to indicate the increasing deadliness of the game | N/A | N/A | As above, but watch the transistion from 00 time to red 01 time and continues to increment. If successful, this test passes | 7-Oct | Adam | N/A |
| 2.01.4 | After the countdown reaches zero, begin increasing the ball speed and damage to ensure the game ends soon | N/A | N/A | As above, but watch as the ball speed begins to increase at each timer value with a 1 in the ones place. Also observe the damage taken upon a hit afterthe countdown has ticked far back up. This should increase also. Optionally, test with increased player health if you're note capable of keeping up. | 7-Oct | Adam | N/A |
| 2.02 | As a user, I want to be able to customize the setting of the game, so I can play stronger characters, longer games, deadlier modes, etc. | 3 | 4 | Between steps two and three, modify settings parameters and then advance to step 5, where damage, health, speed, cooldowns, and hit speed are to be monitored. If each is affected at approximately the modification specified (note that the game runs in the integer domain, so a .75 multiplier applied to a 3 speed gives you a speed of 2), this test passes | 7-Oct | Adam | N/A |
| 2.02.1 | Add a settings set in the code base that allow a drop-down based settings menu to easily modify player health, damage, speed, ability cooldowns, and hit speed | N/A | 3 | N/A | 7-Oct | Adam | N/A |
| 2.02.2 | Add a settings menu that connects drop-down boxes to the recommended set of values | N/A | 3 | N/A | 7-Oct | Adam | N/A |
| 2.03 | As a user, I want to see game-relevant information such as hit points and timers prominently on the game screen | 3 | 4.5 | Test that each player's hitpoint displays reflect changes, and check in-game timers against out-of-game timers | 20-Sep | Lavine | NONE |
| 2.04 | As a developer, I want to be able to select an AI as my opponent so I can test functionality | 2 | 1 | Playing against a beatable AI is an option | 26-Sep | Brandon | Requires multiplayer to be the default mode of the game |
| 2.05 | As a user, I want to play against other people, not just an AI | 1 | 5 | Try all control schemes and selecting the same character - realistically, this will test itself | 28-Sep | Patrick | Character set, multi-input distinction |
| 2.06 | As a user, I want to be able to choose my control scheme, either keyboard or gamepad | 3 | 1.5 | Ensure both work - routine gameplay will provide regression testing | 28-Sep | Patrick | NONE |
| 2.07 | As a user, I want to hear sound effects when I hit the ball, damage my opponent, use an ability, win the game, etc. | 4 | 5 | Test each sound immediately after implementation | 1-Oct | Lavine | NONE - treating asset gathering as a programmer task, as we are the entire development team |
| 2.08 | As a user, I want to be able to pause an ongoing game | 3 | 2 | Ensure both keyboard and mouse users have a functioning button-press that pauses the game | 3-Oct | Brandon | Extra control keys |
| 2.09 | As a user, I want to view a list of controls, so I can learn how to play and discover useful features | 3 | 2 | Verify controls are displayed on pause | 3-Oct | Brandon | A comprehensive list of supported user controls |
| 2.10 | As a developer, I want to have indications of various end-game states, so I can offer options to the user later | 3 | 4 | When a game end, win/lose windows and options are shown | 4-Oct | Alex |  |
| 2.11 | As a developer, I want a framework for adding and using special abilities | 3 | 2 | Special abilities are witnessed in-game when the corresponding buttons are pressed | 6-Oct | Patrick |  |
| 2.12 | As a user, I want a timer to show me the duration of the game, and I want something to ensure a game doesn't go on for too long | 3 | 3 | Accurate visual countdown timer is shown, game enters "sudden death"" mode after time runs out" | 7-Oct | Adam | Done |
| 2.13 | As a developer, I want to be able to customize the settings of the game, so I can modify parameters like speed, health, and damage | 3 | 2 | When settings are modified, corresponding changes are reflected in-game | 7-Oct | Adam | Done |
| 2.14 | As a user/developer, I want graphics that make the game look more visually appealing | 4 | 4 | Graphics are visually appleaing and fit a common theme | 7-Oct | Lavine |  |
| 2.15 | As a user, I want to be able to select an AI as my opponent so I can explore functionality | 2 | 1 | Select vs. AI, try to start a game | In Progress | Brandon |  |
| 2.16 | As a user, I want to see game-relevant information such as ability status prominently on the game screen | 3 | 4.5 | Test that each player's hitpoints and abilities reflect changes, and check in-game timers against out-of-game timers | In Progress | Lavine |  |
| 2.17 | As a user, I want to return to the menu after a game ends, so a new game can be started and configured | 5 | 1 | Verification that the menu opens after a victory in either player's favor or a manual quit | In Progress | Alex | NONE |
| 2.18 | As a user, I want to be able to quit a game and return to the main menu, so I can change any settings or restart any matches I no longer want | 5 | 1 | Verify quit button returns to the menu from all control schemes | In Progress | Brandon | A pause screen |
| 2.19 | As a user, I want the option to replay or quit the game when one of the player loses | 3 | 3 | Verification that when one of the players loses, the respective win/lose screen pops up | In Progress | Alex | Win/lose screen |

### Sprint #3: November 11, 2018
| Story ID | Story | Priority | Effort | Validation | Status | Assigned Member | Pending Requirements |
|:--------:|------------------------------------------------------------------------------------------------|:--------:|:------:|------------------------------------------------------------------------------------------|:------:|:---------------:|:--------------------:|
| 3.01 | As a user, I want to see a display of the winner after a game ends | 3 | 2 | An end game screen is shown after a game wins, with the winner displayed | 8-Oct | Alex | None |
| 3.02 | As a developer, I want to be able to set the cooldown for an ability | 3 | 2 | Abilities can only be used after a certain amount of time has passed since the first use | 9-Oct | Patrick | None |
| 3.03 | As a user, I want to see an animated ball | 4 | 3 | The ball is animated during play (not a static image) | 14-Oct | Lavine | None |
| 3.04 | As a user, I want a complete menu screen with a way to controls, characters, and game settings | 1 | 5 | A complete menu screen is shown with character, control, and setting options | 16-Oct | Adam | None |
| 3.05 | As a developer, I want a framework for adding a new character to the game | 2 | 4 | New characters can be created based on a parent Character class | 24-Oct | Adam | None |
| 3.06 | As a user, I want to be able to play as Adam's character | 3 | 4 | Adam's character can be selected and is fully playable | 24-Oct | Adam | None |
| 3.07 | As a developer, I want to have updated assets and graphics for better gameplay and testing | 4 | 4 | Graphics and sounds are functional and match gameplay | 7-Nov | Lavine | None |
| 3.08 | As a user, I want to be able to play as a magician character | 3 | 3 | A magician character can be selected and is fully playable | 9-Nov | Lavine | None |

## Burndown Chart
### Sprint #1: September 16, 2018
![Sprint #1 Burndown Chart](https://github.com/lurbean/COP4331-Project/blob/master/figures/sprint1_burndown.png "Sprint #1 Burndown Chart")

### Sprint #2: October 7, 2018
![Sprint #2 Burndown Chart](https://github.com/lurbean/COP4331-Project/blob/master/figures/sprint2_burndown.png "Sprint #2 Burndown Chart")

### Sprint #3: November 11, 2018
![Sprint #3 Burndown Chart](https://github.com/lurbean/COP4331-Project/blob/master/figures/sprint3_burndown.png "Sprint #3 Burndown Chart")

## Design Documents
### Sprint #1: September 16, 2018
#### Class Diagram
![Sprint #1 UML Diagram](https://github.com/lurbean/COP4331-Project/blob/master/figures/sprint1_uml.jpg "Sprint #1 UML Diagram")

### Sprint #2: October 7, 2018
#### Class Diagram
![Sprint #2 Class Diagram](https://github.com/lurbean/COP4331-Project/blob/master/figures/sprint2_class_uml.jpg "Sprint #2 Class Diagram")

### Sprint #3: November 11, 2018
#### Class Diagram
![Sprint #3 Class Diagram](https://github.com/lurbean/COP4331-Project/blob/master/figures/sprint3_class_uml.jpg "Sprint #3 Class Diagram")

#### Architecture Diagram
![Sprint #2 Architecture Diagram](https://github.com/lurbean/COP4331-Project/blob/master/figures/sprint2_architecture.jpg "Sprint #2 Architecture Diagram")

## Source Code
- [Desktop Launcher](https://github.com/lurbean/COP4331-Project/tree/master/desktop/src/com/mygdx/game/desktop)
- [Main Source Files](https://github.com/lurbean/COP4331-Project/tree/master/core/src/com/mygdx/game)

## Software Tests
### Test Procedure
All test procedures use the following skeleton:
Step One: Run the application
Step Two: Press "Settings"
Step Three: Close the Settings window or press "Back to Main Menu"
Step Four: Press "Play"
Step Five: Any tests to be made within the confines of one round
Step Six: Reduce one player's health to zero by hitting the ball into their side of the screen, as needed
Step Seven: Either exit the program by choosing not to play again, go to Step Five by pressing "Play Again", or go to Step Two by pressing "New Game"
### Primary Acceptance and Functionality Tests
#### Test 1 - Launch and Open
After Step One, advance directly to Step Four. If the program opens and displays the main menu (after Step One) and the game screen (after Step Four), this test passes.
#### Test 2 - Move and Hit
After Step Four, use the WASD keys and arrow keys to attempt to move the left and right paddles, respectively. Verify neither can leave the boundary of the game screen.
Then move the paddles in front of the ball and verify the ball returns (as expected in Pong), and that the speed and direction are suitable to each player hitting the ball in turn.
Without failures, this test passes.
#### Test 3 - Damage and Win
During Step Five, using the movement controls established in Test 2, manipulate the paddles such that the ball hits one player's side, and confirm that their health is reduced.
After their health bar is reduced to nothing (it will be invisible), verify the game-end screen displays per Step Six.
Without failures, this test passes.
#### Test 4 - Replaying a Game [unmet]
During Step Seven, attempt any option. It should function per its name and there should be no errors. Currently this test will not pass unless trying to close the game.

## Demonstration
### Sprint #2: October 7, 2018
<a href="http://www.youtube.com/watch?feature=player_embedded&v=NFM6kI9OJIo&feature=youtu.be
" target="_blank"><img src="http://img.youtube.com/vi/NFM6kI9OJIo&feature=youtu.be/0.jpg" 
alt="Sprint #2 Demonstration" width="240" height="180" border="10" /></a>
#### WARNING: POOR AUDIO QUALITY (Turn your speakers down.)

## Build Instructions
### Required Programs
- [Gradle](https://gradle.org/install/)
- [ItelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows) (community is fine)

### Procedures
1. After downloading the two programs above and the project, open IntelliJ. Click Open Project.
![Build Instructions Step #1](https://github.com/lurbean/COP4331-Project/blob/master/figures/buildinstructions_1.png "Build Instructions Step #1")

2. Find the file the project is located in and find the build.gradle file. Open it as a project.
![Build Instructions Step #2](https://github.com/lurbean/COP4331-Project/blob/master/figures/buildinstructions_2.png "Build Instructions Step #2")

3. When you open the project, make sure you’re using the latest JDK.
![Build Instructions Step #3](https://github.com/lurbean/COP4331-Project/blob/master/figures/buildinstructions_3.png "Build Instructions Step #3")

4. Once you opened the project, hit ‘Add Configuration’.
![Build Instructions Step #4](https://github.com/lurbean/COP4331-Project/blob/master/figures/buildinstructions_4.png "Build Instructions Step #4")

5. Hit the plus sign to add configuration and select Application.
![Build Instructions Step #5](https://github.com/lurbean/COP4331-Project/blob/master/figures/buildinstructions_5.png "Build Instructions Step #5")

6. After naming the configuration, set the main class to DesktopLauncher, the working directory to assets under the core file, and the module to desktop_main as shown below.
![Build Instructions Step #6](https://github.com/lurbean/COP4331-Project/blob/master/figures/buildinstructions_6.png "Build Instructions Step #6")

7. You should now be ready to build.

## Appendix
### I - Backlog Quantification Metrics: Priority
|Number|Meaning|
|:------:| -------- |
|1|Critical functionality|
|2|Essential functionality|
|3|Usability requirement|
|4|High-priority aesthetic feature|
|5|Desired feature|

### II - Backlog Quantification Metrics: Effort
|Number|Meaning|
|:------:| -------- |
|1|A quick fix, likely half an hour of work including testing and integration|
|2|A simple feature, ideally taking, at most, a little more than an hour|
|3|An involved feature, meaningfully interfacing with multiple other classes/methods, requiring a couple hours of work and light consultation or debriefing with other team members|
|4|A small-scale refactoring or large-scale feature integration, such as adding sound to ball collisions|
|5|A large-scale refactoring, involving the redesign or reworking of multiple moving parts, usually involving multiple team members|
