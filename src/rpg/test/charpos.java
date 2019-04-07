/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.test;

/**
 *
 * @author student
 */
public class charpos {

    public int roomx;
    public int roomy;
    public int xpos;
    public int ypos;
    public char symbol;

    public charpos(char symbols, int roomxs, int roomys, int xposs, int yposs) {
        symbol = symbols;
        roomx = roomxs;
        roomy = roomys;
        xpos = xposs;
        ypos = yposs;
    }

    @Override
    public String toString() {
        return ("[" + roomx + "," + roomy + "," + xpos + "," + ypos + "]");
    }
}
