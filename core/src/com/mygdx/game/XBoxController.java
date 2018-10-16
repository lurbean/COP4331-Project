package com.mygdx.game;
import com.badlogic.gdx.controllers.Controller;

public class XBoxController {
    public Controller controller;

    XBoxController(Controller controllerIn)
    {
        this.controller = controllerIn;
    }

    public boolean isLeftStickUp() {
        return controller.getAxis(XBox.AXIS_LEFT_Y) < -.15;
    }
    public boolean isLeftStickLeft() {
        return controller.getAxis(XBox.AXIS_LEFT_X) < -.15;
    }
    public boolean isLeftStickRight() {
        return controller.getAxis(XBox.AXIS_LEFT_X) > .15;
    }
    public boolean isLeftStickDown() {
        return controller.getAxis(XBox.AXIS_LEFT_Y) > .15;
    }
    public boolean isRightStickUp() {
        return controller.getAxis(XBox.AXIS_RIGHT_Y) < -.15;
    }
    public boolean isRightStickLeft() {
        return controller.getAxis(XBox.AXIS_RIGHT_X) < -.15;
    }
    public boolean isRightStickRight() {
        return controller.getAxis(XBox.AXIS_RIGHT_X) > .15;
    }
    public boolean isRightStickDown() {
        return controller.getAxis(XBox.AXIS_RIGHT_Y) > .15;
    }
    public boolean isAButtonPressed()
    {
        return controller.getButton(XBox.BUTTON_A);
    }
    public boolean isBButtonPressed()
    {
        return controller.getButton(XBox.BUTTON_B);
    }
    public boolean isXButtonPressed()
    {
        return controller.getButton(XBox.BUTTON_X);
    }
    public boolean isYButtonPressed()
    {
        return controller.getButton(XBox.BUTTON_Y);
    }
    public boolean isStartButtonPressed()
    {
        return controller.getButton(XBox.BUTTON_START);
    }
    public boolean isLeftButtonPressed()
    {
        return controller.getButton(XBox.BUTTON_LB);
    }
    public boolean isRightButtonPressed()
    {
        return controller.getButton(XBox.BUTTON_RB);
    }
    public boolean isLeftTriggerPressed()
    {
        return controller.getAxis(XBox.AXIS_LEFT_TRIGGER) > .15;
    }
    public boolean isRightTriggerPressed()
    {
        return controller.getAxis(XBox.AXIS_RIGHT_TRIGGER) < -.15;
    }
    public boolean isLeftStickPressed()
    {
        return controller.getButton(XBox.BUTTON_LS);
    }
    public boolean isRightStickPressed()
    {
        return controller.getButton(XBox.BUTTON_RS);
    }
}