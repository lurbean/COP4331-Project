package com.mygdx.game;
import javax.swing.*;
import java.awt.*;

public class PopUpQuestion{
    public static int TwoOptions(Component window, String Option1, String Option2, String Question, String Title)
    {
        Object[] options = {Option1, Option2};
        return JOptionPane.showOptionDialog(window,
                Question,
                Title,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

    }
}
