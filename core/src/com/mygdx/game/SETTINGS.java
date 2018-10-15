package com.mygdx.game;

public class SETTINGS {
    public int P1HealthMod = 100;
    public int P1PaddleSpeed = 100;
    public int P1Damage = 100;
    public int P1HitSpeed = 100;
    public int P1Cooldown = 100;
    public int P2HealthMod = 100;
    public int P2PaddleSpeed = 100;
    public int P2Damage = 100;
    public int P2HitSpeed = 100;
    public int P2Cooldown = 100;
    public int gameLength = 60;
    public int AllBallSpeed = 100;
    public int AllHealthMod = 100;
    public int AllCooldown = 100;


    public void DisplaySettings()
    {
        System.out.println("The set of settings selected are below, in the order found in the SETTINGS class");
        System.out.println(P1HealthMod);
        System.out.println(P1PaddleSpeed);
        System.out.println(P1Damage);
        System.out.println(P1HitSpeed);
        System.out.println(P1Cooldown);
        System.out.println(P2HealthMod);
        System.out.println(P2PaddleSpeed);
        System.out.println(P2Damage);
        System.out.println(P2HitSpeed);
        System.out.println(P2Cooldown);
        System.out.println(gameLength);
        System.out.println(AllBallSpeed);
        System.out.println(AllHealthMod);
        System.out.println(AllCooldown);
    }
}
