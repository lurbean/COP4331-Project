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
### [7. Build Instructions](#build-instructions)
### [8. Appendix](#appendix)

## Product Backlog
| Story 	| Priority 	| Effort 	| Validation 	| Pending Requirements 	|
| ------- |:---------:|:-------:| ----------- | --------------------- |
| As a user, I want to play against other people, not just an AI 	| 1 	| 5 	| Try all control schemes and selecting the same character - realistically, this will test itself 	| Character set, multi-input distinction 	|
| As a developer, I want a continuously-maintained list of supported user controls, so I can maintain a "help" suite for users and see an overview of functionality 	| 2 	| 1 	| Each time a developer pushes an update mentioning controls, it must be paired with an update to the list of controls in the repository 	| NONE 	|
| As a developer, I want to be able to be able to add an ability that is sensitive to a certain key - for example, I want to be able to add a method and assert that method will be called whenever a keyboard user presses "Q" and whenever a gamepad user presses "right trigger" 	| 2 	| 3.5 	| A new feature is added using this functionality and works flawlessly for both control schemes - routine development will provide regression testing 	| NONE 	|
| As a user/developer, I want to be able to select an AI as my opponent so I can explore/test functionality 	| 2 	| 1 	| Select vs. AI, try to start a game 	| Requires multiplayer to be the default mode of the game 	|
| As a user, I want to be able to pause an ongoing game 	| 3 	| 2 	| Ensure both keyboard and mouse users have a functioning button-press that pauses the game 	| Extra control keys 	|
| As a user, I want to view a list of controls, so I can learn how to play and discover useful features 	| 3 	| 2 	| Verify controls window opens 	| A comprehensive list of supported user controls 	|
| As a user, I want multiple characters to choose between, each with unique abilities 	| 3 	| NAN 	| N/A 	| Project-scale item, will see frequent decomposition 	|
| As a user, I want a character select screen that provides clear and concise description of my options 	| 3 	| 3 	| Ensure each character has associated information, and check with customers (friends, family, classmates) that the provided information is useful and sufficient 	| NONE / art 	|
| As a user, I want to see game-relevant information such as hit points, abilities, and timers prominently on the game screen 	| 3 	| 4.5 	| Test that each player's hitpoints and abilities reflect changes, and check in-game timers against out-of-game timers 	| NONE 	|
| As a user, I want to be able to choose my control scheme, either keyboard or gamepad 	| 3 	| 1.5 	| Ensure both work - routine gameplay will provide regression testing 	| NONE 	|
| As a user, I want to hear sound effects when I hit the ball, damage my opponent, use an ability, win the game, etc. 	| 4 	| 5 	| Test each sound immediately after implementation 	| NONE - treating asset gathering as a programmer task, as we are the entire development team 	|
| As a user, I want to return to the menu after a game ends, so a new game can be started and configured 	| 5 	| 1 	| Verification that the menu opens after a victory in either player's favor or a manual quit 	| NONE 	|
| As a user, I want to be able to quit a game and return to the main menu, so I can change any settings or restart any matches I no longer want 	| 5 	| 1 	| Verify quit button returns to the menu from all control schemes 	| A pause screen 	|

## Sprint Backlog
### Sprint #1: September 16, 2018
| Story                                                  | Priority|Effort|Validation                                        |
| ------------------------------------------------------ |:-------:|:----:| ------------------------------------------------ |
|As a user, I want to start the game, so I can play|1|1|The main menu is shown on program start|
|As a user, I want to have a menu, so I can choose to start a match|5|3|The main menu has a button to start a match|
|As a user, I want to see the ball move, so I can try to play with it|1|2|The ball moves across the playing field|
|As a user, I want to use the keyboard, so I can move my paddle up and down|1|3|The paddle moves up and down, depending on keyboard input|
|As a user, I want to use an Xbox controller, so I can move my paddle up and down|3|4|The paddle moves up and down, corresponding to input from an Xbox controller|
|As a user, I want to be able to hit the ball with my paddle, so I can try to keep the ball in play|1|3|The ball bounces back when it hits a paddle|
|As a user, I want the ball to stay in play when it can't be hit, so the game can continue|1|2|The ball bounces back when it hits a boundary wall|
|As a user, I want to have some control over the ball's direction when my paddle hits it, so I have more control|3|2.5|The ball has a reflection velocity corresponding to the velocity of the paddle|
|As a user, I want to know when I score points, so I can know who is winning|2|3|Each player has a health bar, which is lowered when a ball is missed|
|As a developer, I want to be able to change paddle damage and speed, so I can create different characters|4|4|Varying amounts of damage can be dealt; paddles can move with different speeds|
### Sprint #2: October 7, 2018
| Story ID    | Story                                                                                                                                                          | Priority | Effort | Validation                                                                                                                                                                                                                                                                                                                                                        | Status          | Assigned Member | Pending Requirements |
|-------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|----------|--------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------|-----------------|----------------------|
|  0001       | As a user, I want a timer to show me the duration of the game, and I want something to ensure a game doesn't go on for too long                                | 3        | 3      |                                                                                                                                                                                                                                                                                                                                                                   | Near completion | Adam            | Req 0001.3              |
|  Req 0001.1 | Implement a visual count-down timer from 99 to 0                                                                                                               | N/A      | N/A    | In Step Five, watch the timer and ensure it counts down once per second for each transition. If it does, this test passes                                                                                                                                                                                                                                         | Done            | Adam            |                      |
| Req 0001.2  | Extend the visual count-down to track back up to 99, this time in red, to indicate the increasing deadliness of the game                                       | N/A      | N/A    | As above, but watch the transistion from 00 time to red 01 time and continues to increment. If successful, this test passes                                                                                                                                                                                                                                       | Done            | Adam            |                      |
| Req 0001.3  | When the countdown reaches zero, begin increasing the ball speed and damage to ensure the game ends soon                                                       | N/A      | N/A    | Unfinished                                                                                                                                                                                                                                                                                                                                                        | In Progress     | Adam            |                      |
| 0002        | As a user, I want to be able to customize the setting of the game, so I can play stronger characters, longer games, deadlier modes, etc.                       | 3        | 3      | Between steps two and three, modify settings parameters and then advance to step 5, where damage, health, speed, cooldowns, and hit speed are to be monitored. If each is affected at approximately the modification specified (note that the game runs in the integer domain, so a .75 multiplier applied to a 3 speed gives you a speed of 2), this test passes | Near completion | Adam            | Req 0002.2            |
| Req 0002.1  | Add a settings set in the code base that allow a drop-down based settings menu to easily modify player health, damage, speed, ability cooldowns, and hit speed | N/A      | 3      | N/A                                                                                                                                                                                                                                                                                                                                                               | Done            | Adam            |                      |
| Req 0002.2  | Add a settings menu that connects drop-down boxes to the recommended set of values                                                                             | N/A      | 1      | N/A                                                                                                                                                                                                                                                                                                                                                               | Not Started     | Alex            |                      |

## Burndown Chart
### Sprint #1: September 16, 2018
![Sprint #1 Burndown Chart](https://github.com/lurbean/COP4331-Project/blob/master/figures/sprint1_burndown.png "Sprint #1 Burndown Chart")

## Design Documents
### Sprint #1: September 16, 2018: UML Diagram
![Sprint #1 UML Diagram](https://github.com/lurbean/COP4331-Project/blob/master/figures/sprint1_uml.jpg "Sprint #1 UML Diagram")

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
