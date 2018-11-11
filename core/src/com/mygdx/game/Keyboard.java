package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

class Keyboard {
    public boolean isQPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.Q);
    }
    public boolean isWPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.W);
    }
    public boolean isEPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.E);
    }
    public boolean isAPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.A);
    }
    public boolean isSPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.S);
    }
    public boolean isDPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.D);
    }
    public boolean isZPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.Z);
    }
    public boolean isXPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.X);
    }
    public boolean isCPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.C);
    }
    public boolean isUpPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.UP);
    }
    public boolean isLeftPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT);
    }
    public boolean isRightPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }
    public boolean isDownPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.DOWN);
    }
    public boolean isRPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.R);
    }
    public boolean isFPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.F);
    }
    public boolean isSpacePressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }
    public boolean isControlPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT);
    }
    public boolean isShiftPressed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
    }
    public boolean isEnterPressed() { return Gdx.input.isKeyPressed(Input.Keys.ENTER);}
}