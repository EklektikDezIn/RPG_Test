/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.test;

import java.util.*;

/**
 *
 * @author Granite B.
 */
public class RPGTest {
    //names, symbol, status, health, accuracy, strength, room, X, Y){
    //creates the characters, map, and storage variables for the program
    public static ArrayList<charpos> charposs = new ArrayList<charpos>();
    public static Rooms[][] map = new Rooms[3][3];
    public static ArrayList<Stickman> characters = new ArrayList<Stickman>();
    public static int[] currentroom = new int[2];
    public static Scanner console = new Scanner(System.in); //create a scanner
    public static ACItems[] acceptableitems = new ACItems[]{
        new ACItems("Sword", '/', 75, -30, 30, 10, 10), //0

        new ACItems("Gold piece", '.', 10, .01, 0, 0, 0), //1
        new ACItems("Gold Pile", ':', 100, .10, 0, 0, 0), //2
        new ACItems("Gold Brick", '=', 350, .35, 0, 0, 0), //3 

        new ACItems("Apple", 'a', 2, 20, 1, 0, 0) //4
    };
    public static int[] villains = new int[]{
        10, //orc count
        30, //goblin count
        15 //troll count
    };
    public static char[] villainsym = new char[]{
        '&',
        '%',
        '#',};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launches game
        play();

    }

    public static int randint(int min, int max) {
        return ((int) (Math.random() * 100) % (max - min)) + min;
    }

    public static Stickman chargen(char type, int roomx, int roomy, int posx, int posy) {

        if (type == '&') {
            characters.set(1, new Stickman("Goblin", '&', acceptableitems[1], "Alive", randint(30, 50), randint(70, 80), randint(10, 20), randint(5, 25), roomx, roomy, posx, posy));
        }
        if (type == '%') {
            characters.set(1, new Stickman("Orc", '%', acceptableitems[2], "Alive", randint(100, 300), randint(10, 30), randint(80, 100), randint(10, 20), roomx, roomy, posx, posy));
        }
        if (type == '#') {
            characters.set(1, new Stickman("Troll", '#', acceptableitems[3], "Alive", randint(70, 120), randint(40, 60), randint(35, 50), randint(20, 50), roomx, roomy, posx, posy));
        }
        map[characters.get(1).location.roomx][characters.get(1).location.roomy].roomsize[characters.get(1).location.xpos][characters.get(1).location.ypos] = characters.get(1).location.symbol;
        return characters.get(1);
    }

    public static int enemy(charpos charc) {

        for (int i = 0; i < villainsym.length; i++) {
            if (villainsym[i] == charc.symbol) {
                for (int j = 0; j < charposs.size(); j++) {
                    if (charposs.get(i).roomx == charc.roomx && charposs.get(i).roomy == charc.roomy && charposs.get(i).xpos == charc.xpos && charposs.get(i).ypos == charc.ypos) {
                        return i;
                    }
                }
                return 0;
            }
        }
        return 0;
    }

    public static void vargen() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Rooms(40, 20);//randint(10,100),randint(5,30));
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                createmap(i, j);
            }
        }
        int roomx = 0;//randint(0,map.length-1);
        int roomy = 0;//randint(0,map[roomx].length-1);
        characters.add(new Stickman("Bob", '@', acceptableitems[0], "Alive", 300, 50, 80, 0, roomx, roomy, randint(1, map[roomx][roomy].roomsize.length), randint(1, map[roomx][roomy].roomsize[0].length - 2)));
        characters.add(new Stickman("", ' ', new ACItems("", ' ', 0, .0, .0, .0, .0), "", 0, 0, 0, 0, 0, 0, 0, 0));

        characters.get(0).Inventory.get(1).Item = acceptableitems[4];
        currentroom[0] = characters.get(0).location.roomx;
        currentroom[1] = characters.get(0).location.roomy;
        for (int i = 0; i < villains[0]; i++) {
            roomx = randint(0, map.length - 1);
            roomy = randint(0, map[roomx].length - 1);
            charposs.add(new charpos('%', roomx, roomy, randint(1, map[roomx][roomy].roomsize.length - 2), randint(1, map[roomx][roomy].roomsize[0].length - 2)));//Orc
            //characters.add(new  Stickman("Orc",'%',acceptableitems[2],"Alive",randint(100,300),randint(10,30),randint(80,100),randint(10,20),roomx,roomy,randint(1,map[roomx][roomy].roomsize.length-2),randint(1,map[roomx][roomy].roomsize[0].length-2)));

        }
        for (int i = 0; i < villains[1]; i++) {
            roomx = randint(0, map.length - 1);
            roomy = randint(0, map[roomx].length - 1);
            //characters.add (new  Stickman("Goblin",'&',acceptableitems[1],"Alive",randint(30,50),randint(70,80),randint(10,20),randint(5,25),roomx,roomy,randint(1,map[roomx][roomy].roomsize.length-2),randint(1,map[roomx][roomy].roomsize[0].length-2)));
            charposs.add(new charpos('&', roomx, roomy, randint(1, map[roomx][roomy].roomsize.length - 2), randint(1, map[roomx][roomy].roomsize[0].length - 2)));//Goblin
        }
        for (int i = 0; i < villains[2]; i++) {
            roomx = randint(0, map.length - 1);
            roomy = randint(0, map[roomx].length - 1);
            //characters.add (new Stickman("Troll",'#',acceptableitems[3],"Alive",randint(70,120),randint(40,60),randint(35,50),randint(20,50),roomx,roomy,randint(1,map[roomx][roomy].roomsize.length-2),randint(1,map[roomx][roomy].roomsize[0].length-2)));
            charposs.add(new charpos('#', roomx, roomy, randint(1, map[roomx][roomy].roomsize.length - 2), randint(1, map[roomx][roomy].roomsize[0].length - 2)));//Troll
        }
        map[characters.get(0).location.roomx][characters.get(0).location.roomy].roomsize[characters.get(0).location.xpos][characters.get(0).location.ypos] = '@'; //human
        for (int i = 0; i < charposs.size(); i++) {
            map[charposs.get(i).roomx][charposs.get(i).roomy].roomsize[charposs.get(i).xpos][charposs.get(i).ypos] = charposs.get(i).symbol;
        }
    }

    public static void play() {
        vargen();
        //create and display the map
        showdata();
        //stores characters in "characters" array and displays their stats to the user

        while (!characters.get(0).status.equals("Dead")) { //assuming you haven't lost the game
            showmap();//dropinv(Bob);
            System.out.println("Please choose a direction to move in."); //prompt user to move charaver
            char inpt = console.next().charAt(0);
            moving(characters.get(0), inpt);  //move character
            specialcommands(characters.get(0), inpt);
        }
    }

    public static void specialcommands(Stickman Man, char inpt) {
        if (inpt == 'i') {
            System.out.println(Man.showinv() + ", Upgradepoints :" + Man.upoints);
        }
        if (inpt == 'c') {
            System.out.println(Man.toString());
        }
        if (inpt == 'h') {
            System.out.println("w moves the character forward.");
            System.out.println("a moves the character left.");
            System.out.println("s moves the character down.");
            System.out.println("d moves the character right.");
            System.out.println("i displays the character's inventory.");
            System.out.println("c displays the characters stats.");
            System.out.println("h shows help menu.");
        }
        if (inpt == 'u') {
            levelup(Man);
        }
    }

    public static void levelup(Stickman Man) {
        System.out.println("You have " + Man.upoints + " upgrade points");
        System.out.println("(1)Max Health + 10%  Current Maximum: " + Man.maxhealth);
        System.out.println("(2)Accuracy + 10%  Current Accyracy: " + Man.accuracy);
        System.out.println("(3)Strength + 10%  Current strength: " + Man.strength);
        System.out.println("(4)Armor + 10%  Current strength: " + Man.armor);
        System.out.println("(5)Inventory Size + 1  Current Size: " + Man.invmax);
        System.out.println("(x)Exit Menu");
        char temp = console.next().charAt(0);
        if (temp == '1' && Man.upoints > 0) {
            Man.maxhealth *= 1.1;
            Man.spentpoints++;
        }
        if (temp == '2' && Man.upoints > 0) {
            Man.accuracy *= 1.1;
            Man.spentpoints++;
        }
        if (temp == '3' && Man.upoints > 0) {
            Man.strength *= 1.1;
            Man.spentpoints++;
        }
        if (temp == '4' && Man.upoints > 0) {
            Man.armor *= 1.1;
            Man.spentpoints++;
        }
        if (temp == '5' && Man.upoints > 0) {
            Man.invmax++;
            Man.spentpoints++;
            Man.Inventory.add(new Inv(new ACItems("empty", ' ', 0, 0, 0, 0, 0), 0));
        }

    }

    public static ACItems symtoob(char symbol) {
        int tempi = -1;
        for (int i = 0; i < acceptableitems.length; i++) {
            if (symbol == acceptableitems[i].symbol) {
                tempi = i;
            }
        }
        if (tempi != -1) {
            return acceptableitems[tempi];
        }
        return new ACItems("empty", ' ', 0, 0, 0, 0, 0);
    }

    public static void dropinv(Stickman Man) {
        //map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos] = Man.Inventory[0].Item;//drop item at current location

        if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos + 1][Man.location.ypos] == ' ') {
            map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos + 1][Man.location.ypos] = Man.Inventory.get(1).Item.symbol;
        }
        if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos - 1][Man.location.ypos] == ' ') {
            map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos - 1][Man.location.ypos] = Man.Inventory.get(2).Item.symbol;
        }
        if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos + 1] == ' ') {
            map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos + 1] = Man.Inventory.get(3).Item.symbol;
        }//drop items at edges
        if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos - 1] == ' ') {
            map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos - 1] = Man.Inventory.get(4).Item.symbol;
        }

        if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos + 1][Man.location.ypos + 1] == ' ') {
            map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos + 1][Man.location.ypos + 1] = Man.Inventory.get(5).Item.symbol;
        }
        if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos - 1][Man.location.ypos - 1] == ' ') {
            map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos - 1][Man.location.ypos - 1] = Man.Inventory.get(6).Item.symbol;
        }
        if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos - 1][Man.location.ypos + 1] == ' ') {
            map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos - 1][Man.location.ypos + 1] = Man.Inventory.get(7).Item.symbol;
        }//drop items at corners
        if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos + 1][Man.location.ypos - 1] == ' ') {
            map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos + 1][Man.location.ypos - 1] = Man.Inventory.get(8).Item.symbol;
        }
    }

    public static void moving(Stickman Man, char motion) {
        //move Man in direction motion, assuming there is no wall
        if (Man.status.equals("Attacking")) {
            // Man.under = ' ';
            if (motion == 'w') {
                if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos - 1] != 'H' && Man.location.ypos >= 0) {
                    map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos] = ' '; //check
                    Man.move(motion);
                    map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = Man.location.symbol; //check
                }
            } else if (motion == 'a') {
                if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos - 1][Man.location.ypos] != 'H' && Man.location.xpos > 0) {
                    map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos] = ' ';//check
                    Man.move(motion);
                    map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = Man.location.symbol;//check
                }
            } else if (motion == 's') {
                if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos + 1] != 'H' && Man.location.ypos < map[currentroom[0]][currentroom[1]].roomsize[0].length - 1) {
                    map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos] = ' ';//check
                    Man.move(motion);
                    map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = Man.location.symbol;//check
                }
            } else if (motion == 'd') {
                if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos + 1] != 'H' && Man.location.ypos < map[currentroom[0]][currentroom[1]].roomsize[0].length - 1) {
                    map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos] = ' ';//check
                    Man.move(motion);
                    map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = Man.location.symbol;//check
                }
            }
        } else if (Man.status.equals("Alive")) {

            if (motion == 'w') {
                if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos - 1] != 'H' && Man.location.ypos >= 0) {
                    map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos] = Man.under;//check
                    Man.under = map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos - 1];
                    if (Man == characters.get(0)) {
                        if (enemy(new charpos(Man.under, Man.location.roomx, Man.location.roomy, Man.location.xpos, Man.location.ypos)) != 0 && Man.under != ' ' && Man.location.xpos != characters.get(1).location.xpos && Man.location.ypos != characters.get(1).location.ypos) {
                            charcollide(Man, chargen(Man.under, currentroom[0], currentroom[1], Man.location.xpos, Man.location.ypos));
                        } else {
                            takeitem(Man, Man.under);
                        }
                    }
                    Man.move(motion);
                    map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = Man.location.symbol;//check
                }
            } else if (motion == 'a') {
                if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos - 1][Man.location.ypos] != 'H' && Man.location.xpos > 0) {
                    map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos] = Man.under;//check
                    Man.under = map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos - 1][Man.location.ypos];
                    if (Man == characters.get(0)) {
                        if (enemy(new charpos(Man.under, Man.location.roomx, Man.location.roomy, Man.location.xpos, Man.location.ypos)) != 0 && Man.under != ' ' && Man.location.xpos != characters.get(1).location.xpos && Man.location.ypos != characters.get(1).location.ypos) {
                            charcollide(Man, chargen(Man.under, currentroom[0], currentroom[1], Man.location.xpos, Man.location.ypos));
                        } else {
                            takeitem(Man, Man.under);
                        }
                    }
                    Man.move(motion);
                    System.out.println("3");
                    showmap();
                    map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = Man.location.symbol;//check
                }
            } else if (motion == 's') {
                if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos + 1] != 'H' && Man.location.ypos < map[currentroom[0]][currentroom[1]].roomsize[0].length - 1) {
                    map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos] = Man.under; //check
                    Man.under = map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos + 1];
                    if (Man == characters.get(0)) {
                        if (enemy(new charpos(Man.under, Man.location.roomx, Man.location.roomy, Man.location.xpos, Man.location.ypos)) != 0 && Man.under != ' ' && Man.location.xpos != characters.get(1).location.xpos && Man.location.ypos != characters.get(1).location.ypos) {
                            charcollide(Man, chargen(Man.under, currentroom[0], currentroom[1], Man.location.xpos, Man.location.ypos));
                        } else {
                            takeitem(Man, Man.under);
                        }
                    }
                    Man.move(motion);
                    map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = Man.location.symbol;//check
                }
            } else if (motion == 'd') {
                if (map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos + 1][Man.location.ypos] != 'H' && Man.location.xpos < map[currentroom[0]][currentroom[1]].roomsize.length - 1) {
                    map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos] = Man.under;//check
                    Man.under = map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos + 1][Man.location.ypos];
                    if (Man == characters.get(0)) {
                        if (enemy(new charpos(Man.under, Man.location.roomx, Man.location.roomy, Man.location.xpos, Man.location.ypos)) != 0 && Man.under != ' ' && Man.location.xpos != characters.get(1).location.xpos && Man.location.ypos != characters.get(1).location.ypos) {
                            charcollide(Man, chargen(Man.under, currentroom[0], currentroom[1], Man.location.xpos, Man.location.ypos));
                        } else {
                            takeitem(Man, Man.under);
                        }
                    }
                    Man.move(motion);
                    map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = Man.location.symbol;//check
                }

            }            //if Man is in a door, move to next room

            if (Man.location.xpos == map[currentroom[0]][currentroom[1]].roomsize.length - 1) {
                map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = ' ';//check
                currentroom[0]++;
                Man.location.roomx++;
                Man.location.xpos = 0;

                for (int i = 0; i < map[currentroom[0]][currentroom[1]].roomsize[0].length; i++) {
                    if (map[currentroom[0]][currentroom[1]].roomsize[0][i] == ' ') {
                        Man.location.ypos = i;
                    }
                }
                map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = Man.location.symbol;//check
            } else if (Man.location.xpos == 0) {
                map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = ' ';//check
                currentroom[0]--;
                Man.location.roomx--;
                Man.location.xpos = map[currentroom[0]][currentroom[1]].roomsize.length - 1;
                for (int i = 0; i < map[currentroom[0]][currentroom[1]].roomsize[0].length - 1; i++) {
                    if (map[currentroom[0]][currentroom[1]].roomsize[map[currentroom[0]][currentroom[1]].roomsize.length - 1][i] == ' ') {
                        Man.location.ypos = i;
                    }
                }
                map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = Man.location.symbol;//check
            } else if (Man.location.ypos == map[currentroom[0]][currentroom[1]].roomsize[0].length - 1) {
                map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = ' ';//check
                currentroom[1]--;
                Man.location.roomy--;
                Man.location.ypos = 0;
                for (int i = 0; i < map[currentroom[0]][currentroom[1]].roomsize[0].length; i++) {
                    if (map[currentroom[0]][currentroom[1]].roomsize[i][0] == ' ') {
                        Man.location.ypos = i;
                    }
                }
                map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = Man.location.symbol;//check
            } else if (Man.location.ypos == 0) {
                map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = ' ';//check
                currentroom[1]++;
                Man.location.roomy++;
                Man.location.ypos = map[currentroom[0]][currentroom[1]].roomsize[0].length - 1;
                for (int i = 0; i < map[currentroom[0]][currentroom[1]].roomsize[0].length; i++) {
                    System.out.println(currentroom[0] + ", " + currentroom[1]);
                    if (map[currentroom[0]][currentroom[1]].roomsize[i][map[currentroom[0]][currentroom[1]].roomsize[0].length - 1] == ' ') {
                        Man.location.ypos = i;
                    }
                }
                System.out.println(currentroom[0] + ", " + currentroom[1]);
                map[Man.location.roomx][Man.location.roomy].roomsize[Man.location.xpos][Man.location.ypos] = Man.location.symbol;//check
            }
        }
    }

    public static void takeitem(Stickman Man, char Item) {
        Boolean cont = false;
        int money = 0;
        String itemname = " ";
        for (int i = 0; i < acceptableitems.length; i++) {
            if (Item == acceptableitems[i].symbol) {
                itemname = acceptableitems[i].name;
                money = acceptableitems[i].value;
                cont = true;
            }
        }
        if (cont) {

            System.out.println("Would you like to pick up the " + itemname + "? (" + Item + ")  Yes(y) No(n)");
            char TempI = console.next().charAt(0);
            Boolean TempB = true;
            if (TempI == 'y') {
                if (itemname.substring(0, 4).equals("Gold")) {
                    Man.cash += money;
                    System.out.println("You currently have $" + Man.cash);
                } else {
                    for (int i = 0; i < Man.invmax; i++) {
                        if (Man.Inventory.get(i).Item.symbol == Item) {
                            Man.Inventory.get(i).count++;
                        }
                    }
                    for (int i = 0; i < Man.invmax; i++) {
                        if (Man.Inventory.get(i).Item.symbol == ' ' && TempB) {
                            Man.Inventory.get(i).Item = symtoob(Item);
                            TempB = false;
                        }
                    }
                    if (TempB) {
                        System.out.println("Your inventory is full.");
                    }
                }
            } else if (TempI != 'n') {
                takeitem(Man, Item);
            }
        }
        Man.under = ' ';
    }

    public static void charcollide(Stickman Man, Stickman Other) {
        //if the characters are in the same position
        String statustempman = Man.status;
        Man.status = "Attacking";

        String statustempother = Other.status;
        Other.status = "Attacking";

        if (Man.location.xpos == Other.location.xpos && Man.location.ypos == Other.location.ypos) {
            String Winner = Man.attacking(Other); //run attack sequence
            System.out.println("The winner is " + Winner);
            System.out.println(Man.name + " " + Man.health);
            System.out.println(Other.name + " " + Other.health);
            //display results

            if (Winner.equals(Man.name)) {
                if (Other.status.equals("Dead")) {
                    takeitem(Man, Other.Inventory.get(0).Item.symbol);
                    dropinv(Other);
                    //map[currentroom[0]][currentroom[1]].roomsize[Other.location.xpos][Other.location.ypos] = Man.symbol;

                    Other.die();
                } else {
                    moving(Other, 'w');
                    moving(Other, 'd');
                    System.out.println("0");
                    showmap();
                }
            } else if (Winner.equals(Other.name)) {
                if (Man.status.equals("Dead")) {
                    dropinv(Man);
                    //map[currentroom[0]][currentroom[1]].roomsize[Man.location.xpos][Man.location.ypos] = Other.symbol;
                    Man.die();
                    takeitem(Other, Man.Inventory.get(0).Item.symbol);
                } else {
                    moving(Man, 'a');
                    moving(Man, 's');
                    System.out.println("1");
                    showmap();
                }
            } else if (Winner.equals("a tie")) {
                moving(Other, 'a');
                moving(Other, 's');
                moving(Man, 'w');
                moving(Man, 'd');
                System.out.println("2");
                showmap();
            }//move loser(s) and drop dead man's inventory
        }
        Man.status = statustempman;
        Other.status = statustempother;
    }

    public static void createmap(int roomx, int roomy) {
        for (int j = 0; j <= map[roomx][roomy].roomsize.length - 1; j++) {
            for (int i = 0; i <= map[roomx][roomy].roomsize[0].length - 1; i++) {
                map[roomx][roomy].roomsize[j][i] = ' ';//check
            }
            //create empty map
        }

        for (int j = 0; j <= map[roomx][roomy].roomsize[0].length - 1; j++) {
            map[roomx][roomy].roomsize[0][j] = 'H';//check
            map[roomx][roomy].roomsize[map[roomx][roomy].roomsize.length - 1][j] = 'H';//check
        }
        for (int j = 0; j <= map[roomx][roomy].roomsize.length - 1; j++) {
            map[roomx][roomy].roomsize[j][0] = 'H';//check
            map[roomx][roomy].roomsize[j][map[roomx][roomy].roomsize[0].length - 1] = 'H';//check
        }//add walls

        for (int y = 0; y < map.length; y++) {
            for (int x = y % 2; x < map[y].length; x += 2) {
                //checks corners
                if (x == 0 && y == 0) {

                    createdoors(x, y, randint(1, map[x][y].roomsize[0].length - 2), x + 1, y, randint(1, map[x][y].roomsize[0].length - 2));
                    createdoors(x, y, randint(1, map[x][y].roomsize.length - 2), x, y + 1, randint(1, map[x][y].roomsize.length - 2));
                }
                if (x == 0 && y == map[0].length - 1) {
                    createdoors(x, y, randint(1, map[x][y].roomsize[0].length - 2), x + 1, y, randint(1, map[x][y].roomsize[0].length - 2));
                    createdoors(x, y, randint(1, map[x][y].roomsize.length - 2), x, y - 1, randint(1, map[x][y].roomsize.length - 2));
                }
                if (x == map.length - 1 && y == 0) {
                    createdoors(x, y, randint(1, map[x][y].roomsize[0].length - 2), x - 1, y, randint(1, map[x][y].roomsize[0].length - 2));
                    createdoors(x, y, randint(1, map[x][y].roomsize.length - 2), x, y + 1, randint(1, map[x][y].roomsize.length - 2));
                }
                if (x == map.length - 1 && y == map[0].length - 1) {
                    createdoors(x, y, randint(1, map[x][y].roomsize[0].length - 2), x - 1, y, randint(1, map[x][y].roomsize[0].length - 2));
                    createdoors(x, y, randint(1, map[x][y].roomsize.length - 2), x, y - 1, randint(1, map[x][y].roomsize.length - 2));
                }
                //checks edges
                if (x == 0 && y != 0 && y != map[x].length - 1) {
                    createdoors(x, y, randint(1, map[x][y].roomsize[0].length - 2), x + 1, y, randint(1, map[x][y].roomsize[0].length - 2));
                    createdoors(x, y, randint(1, map[x][y].roomsize.length - 2), x, y + 1, randint(1, map[x][y].roomsize.length - 2));
                    createdoors(x, y, randint(1, map[x][y].roomsize.length - 2), x, y - 1, randint(1, map[x][y].roomsize.length - 2));
                }
                if (x == map.length - 1 && y != 0 && y != map[x].length - 1) {
                    createdoors(x, y, randint(1, map[x][y].roomsize[0].length - 2), x - 1, y, randint(1, map[x][y].roomsize[0].length - 2));
                    createdoors(x, y, randint(1, map[x][y].roomsize.length - 2), x, y + 1, randint(1, map[x][y].roomsize.length - 2));
                    createdoors(x, y, randint(1, map[x][y].roomsize.length - 2), x, y - 1, randint(1, map[x][y].roomsize.length - 2));
                }
                if (x != 0 && x != map.length - 1 && y == 0) {
                    createdoors(x, y, randint(1, map[x][y].roomsize.length - 2), x, y + 1, randint(1, map[x][y].roomsize.length - 2));
                    createdoors(x, y, randint(1, map[x][y].roomsize[0].length - 2), x + 1, y, randint(1, map[x][y].roomsize[0].length - 2));
                    createdoors(x, y, randint(1, map[x][y].roomsize[0].length - 2), x - 1, y, randint(1, map[x][y].roomsize[0].length - 2));
                }
                if (x != 0 && x != map.length - 1 && y == map[x].length - 1) {
                    createdoors(x, y, randint(1, map[x][y].roomsize.length - 2), x, y - 1, randint(1, map[x][y].roomsize.length - 2));
                    createdoors(x, y, randint(1, map[x][y].roomsize[0].length - 2), x + 1, y, randint(1, map[x][y].roomsize[0].length - 2));
                    createdoors(x, y, randint(1, map[x][y].roomsize[0].length - 2), x - 1, y, randint(1, map[x][y].roomsize[0].length - 2));
                }
                if (x != 0 && x != map.length - 1 && y != 0 && y != map[x].length - 1) {
                    createdoors(x, y, randint(1, map[x][y].roomsize[0].length - 2), x + 1, y, randint(1, map[x][y].roomsize[0].length - 2));
                    createdoors(x, y, randint(1, map[x][y].roomsize[0].length - 2), x - 1, y, randint(1, map[x][y].roomsize[0].length - 2));
                    createdoors(x, y, randint(1, map[x][y].roomsize.length - 2), x, y + 1, randint(1, map[x][y].roomsize.length - 2));
                    createdoors(x, y, randint(1, map[x][y].roomsize.length - 2), x, y - 1, randint(1, map[x][y].roomsize.length - 2));
                }
            }
        }




    }

    public static void createdoors(int room1x, int room1y, int place1, int room2x, int room2y, int place2) {
        int side = 0;
        if (room1x + 1 == room2x) {
            side = 6;
        } else if (room1x - 1 == room2x) {
            side = 4;
        } else if (room1y + 1 == room2y) {
            side = 8;
        } else if (room1y - 1 == room2y) {
            side = 5;
        }

        if (side == 8) {
            //add door on top
            map[room1x][room1y].roomsize[place1][0] = ' ';//check
            map[room2x][room2y].roomsize[place2][map[room2x][room2y].roomsize[0].length - 1] = ' ';//check
        } else if (side == 5) {
            //add door on bottom
            map[room1x][room1y].roomsize[place1][map[room1x][room1y].roomsize[0].length - 1] = ' ';//check
            map[room2x][room2y].roomsize[place2][0] = ' ';//check
        } else if (side == 4) {
            //add door on left
            map[room1x][room1y].roomsize[0][place1] = ' ';//check
            map[room2x][room2y].roomsize[map[room2x][room2y].roomsize.length - 1][place2] = ' ';//check
        } else if (side == 6) {
            //add door on right
            map[room1x][room1y].roomsize[map[room1x][room1y].roomsize.length - 1][place1] = ' ';//check
            map[room2x][room2y].roomsize[0][place2] = ' ';//check
        }
    }

    public static void showmap() {
        for (int i = 0; i <= map[currentroom[0]][currentroom[1]].roomsize[0].length - 1; i++) {
            for (int j = 0; j <= map[currentroom[0]][currentroom[1]].roomsize.length - 1; j++) {
                System.out.print(map[currentroom[0]][currentroom[1]].roomsize[j][i]);
            }
            System.out.println();//display map
        }

    }

    public static void showdata() {
        for (int i = 0; i < characters.size() - 1; i++) {
            System.out.println(characters.get(i).toString());
            //show character info for all characters
        }

    }
}