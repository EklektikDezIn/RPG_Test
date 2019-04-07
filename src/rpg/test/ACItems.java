/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.test;

/**
 *
 * @author student
 */
public class ACItems {

    public String name;
    public char symbol;
    public int value;
    public double healvalue;
    public double strengthb;
    public double accuracyb;
    public double armorb;

    public ACItems(String names, char symbols, int values, double healvalues, double strength, double accuracy, double armor) {
        name = names;
        symbol = symbols;
        value = values;
        healvalue = healvalues;
        strengthb = strength;
        accuracyb = accuracy;
        armorb = armor;
    }

    @Override
    public String toString() {
        return name + ", " + symbol + ", $" + value + ", healthpoints: " + healvalue + ", strength bonus: " + strengthb + ", accuracy bonus: " + accuracyb + ", armor bonus: " + armorb;
    }
}
