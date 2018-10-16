package com.mygdx.game;

import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.utils.Array;

// Notes:
// Keyboard supports keys QWE - ASD - ZXC - RF - SPACE - ARROW KEYS
// and the modifiers left control and right control
// Controller supports all but the back button and D-Pad
public class Control {
    // state 0 -> two controllers, 1 -> left player using keyboard, 2 -> right player using keyboard, 3 -> left using keyboard right inactive
    Control(int state, Player player1, Player player2) {
        Array<Controller> controllers = Controllers.getControllers();
        System.out.println(controllers.size);
        if (state == 3)
        {
            player1.character.controlMode = 1;
            player2.character.controlMode = 9;
            player1.character.keyboard = new Keyboard();
        }
        else if (controllers.size == 0) {
            System.out.println("Not enough controllers - Entering player one only mode");
            player1.character.controlMode = 1;
            player2.character.controlMode = 9;
            player1.character.keyboard = new Keyboard();
        }
        else {
            switch (state) {
                case 0: //two controllers
                    if (controllers.size == 1) {
                        System.out.println("There are not enough controllers. Player two will play with the keyboard.");
                        state = 1;
                    } else {
                        player1.character.controller = new XBoxController(controllers.get(0));
                        player2.character.controller = new XBoxController(controllers.get(1));
                        player1.character.controlMode = 0;
                        player2.character.controlMode = 0;
                        break;
                    }
                case 1:
                    player1.character.keyboard = new Keyboard();
                    player2.character.controller = new XBoxController(controllers.get(0));
                    player1.character.controlMode = 1;
                    player2.character.controlMode = 0;
                    break;
                case 2:
                    player1.character.controller = new XBoxController(controllers.get(0));
                    player2.character.keyboard = new Keyboard();
                    player1.character.controlMode = 0;
                    player2.character.controlMode = 1;
                    break;
            }
        }
    }
}
    /*
            paddle.movePaddleUp();
            paddle.movePaddleDown();
        if(!Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.DOWN))
            paddle.zeroMomentum();

            paddle.pause();
    */