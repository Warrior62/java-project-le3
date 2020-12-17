/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.util.ArrayDeque;
import java.util.Deque;
import modele.TypeProduit;
/**
 *
 * @author agpou
 * TEST D'IMPLEMENTATION DE PILE
 */



/*
1) comment faire pour afficher le dernier element d'une pile??
2) est ce que la pile doit etre en bdd??
3) 
*/
public class testPile {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println("taille pile : "+stack.size());
        int a = 0;
        for(Integer j : stack){
            a = stack.peek();
            System.out.println(a);
            stack.pop();
        }
        TypeProduit p1 = new TypeProduit("P001",20,10,3);
    }
        
}
