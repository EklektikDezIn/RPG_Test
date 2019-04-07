/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.test;

import java.util.*;

/**
 *
 * @author Elizabeth
 */
public class Stickman {

    public double maxhealth;
    public double accuracy;
    public double strength;
    public String name;
    public String status;
    public charpos location;
    public int level;
    public int cash;
    public int armor;
    public int invmax;
    public int spentpoints;
    public ArrayList<Inv> Inventory = new ArrayList<Inv>();
    public int killcount;
    public double health;
    public int upoints;
    public char under;
    // declare variables

    public Stickman(String names, char symbols, ACItems initItem, String statuss, double healths, double accuracys, double strengths, int armors, int roomsx, int roomsy, int X, int Y) {
        name = names;
        status = statuss;
        cash = 0;
        maxhealth = healths;
        health = maxhealth;
        accuracy = accuracys;
        strength = strengths;
        armor = armors;
        location = new charpos(symbols, roomsx, roomsy, X, Y);
        killcount = 0;
        level = (int) Math.ceil((accuracy / 10) * strength);
        invmax = 10;
        upoints = 0;
        spentpoints = 0;
        under = ' ';
        Inventory.add(new Inv(initItem, 1));
        ACItems temp = new ACItems("empty", ' ', 0, 0, 0, 0, 0);
        for (int i = 1; i < invmax; i++) {
            Inventory.add(new Inv(temp, 0));
        }
    }

    public String attacking(Stickman opponent) {
        double attack = Math.random() * 100;
        String Victory = "a tie"; //use accuracy to return success or failure of attack
        if (attack <= accuracy + this.bacc()) {
            opponent.health -= (strength + strength * this.bstr()) - (opponent.armor + opponent.armor * opponent.barm()); //deal damage if success
            Victory = name;
        } else {
            attack = Math.random() * 100;
            if (attack <= opponent.accuracy + opponent.accuracy * opponent.bacc()) { //if failure, give opponent chance to attack
                health -= (opponent.strength + opponent.strength * opponent.bstr()) - (armor + armor * this.barm());
                Victory = opponent.name;  //deal damage if opponent success
            }
        }
        if (opponent.health <= 0) {  //if opponent health = 0 opponent dies
            killcount++;
            this.uppoints();
            opponent.status = "Dead";
            System.out.println(opponent.name + " is dead.");
        } else if (health <= 0) {
            //if Man's health = 0 Man dies
            opponent.killcount++;
            opponent.uppoints();
            status = "Dead";
        }
        return Victory;
    }

//    public void upgrade(double level){
//        accuracy+=accuracy*(level/100);
//        strength+=strength*(level/100);
//        System.out.println("Level Up! " + this.toString());
//    } //upgrades character based on opponent's difficulty 
    @Override
    public String toString() {
        return ("Name = " + name + ", Symbol = " + location.symbol + ", Status = " + status + ", Health = " + health + ", Accuracy = " + accuracy + " + " + this.bacc() * accuracy + ",Strength = " + strength + " + " + this.bstr() * strength + ", Armor = " + armor + " + " + this.barm() * armor + ", " + location.toString());
    }//overrides toString procedure

    public void uppoints() {
        int tempi = killcount;
        int currentlevel = 10;
        int points = 0;
        while (Math.floor(tempi - currentlevel) >= 0) {
            points++;
            currentlevel++;
            tempi -= currentlevel;
        }
        upoints = points - spentpoints;


    }

    public String showinv() {
        String Temp = " ";
        for (int i = 0; i < invmax; i++) {
            Temp += Inventory.get(i).toString() + " ";
        }
        return Temp + "$" + cash + ", Upgrade points: ";
    }

    public void die() {
        health = 0;
        accuracy = 0;
        strength = 0;
        location.xpos = 0;
        location.ypos = 0;
        location.symbol = 'H';
        System.out.println(name + " is dead.");
    }//reduces all variables to 0 and moves charactre to corner

    public void move(char dir) {
        if (dir == 'w') {
            location.ypos--;
        }
        if (dir == 's') {
            location.ypos++;
        }
        if (dir == 'd') {
            location.xpos++;
        }
        if (dir == 'a') {
            location.xpos--;
        }
        //System.out.println(" X=" + positionx + " Y=" + positiony);
    }//adjusts character's position on the map based on input

    public double bstr() {
        double temp = 0.0;
        for (int i = 0; i < invmax; i++) {
            temp += Inventory.get(i).Item.strengthb;
        }
        return temp / 100;
    }

    public double bacc() {
        double temp = 0.0;
        for (int i = 0; i < invmax; i++) {
            temp += Inventory.get(i).Item.accuracyb;
        }
        return temp / 100;
    }

    public double barm() {
        double temp = 0.0;
        for (int i = 0; i < invmax; i++) {
            temp += Inventory.get(i).Item.armorb;
        }
        return temp / 100;
    }
}
