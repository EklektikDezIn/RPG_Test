/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg.test;

/**
 *
 * @author Elizabeth
 */
public class Inv {
    public ACItems Item;
    public int count;
    
    
    public Inv(ACItems Items, int counts){
        Item = Items;
        count = counts;
    }
    
    @Override
    public String toString() {
        return ("[" + Item.toString() + ", X" + count + "]");
                
    }
}
