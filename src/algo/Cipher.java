/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import java.util.Arrays;

/**
 *
 * @author user
 */
public class Cipher {
    
     public  StringBuffer encrypt(String text, int s) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.length(); i++) {
            if ((int) text.charAt(i) + s >= 127) {
                char ch = (char) (((int) text.charAt(i) + s - 127) + 31 % 127);
                result.append(ch);
            } else {
                char ch = (char) (((int) text.charAt(i) + s) % 127);
                result.append(ch);
            }
        }
        return result;
    }

    public  StringBuffer decrypt(String text, int s) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.length(); i++) {
            if ((int) text.charAt(i) - s < 32) {
                char ch = (char) (((int) text.charAt(i) - s + 127) - 31 % 127);
                result.append(ch);
            } else {
                char ch = (char) (((int) text.charAt(i) - s) % 127);
                result.append(ch);

            }

        }
        return result;
    }

    // Driver code
    /*public static void main(String[] args) {
        String text = "ATTACKATONCE? abc~|";
        int s = 5;
        String tes = encrypt(text, s).toString();
        System.out.println("Text : " + text);
        System.out.println("Shift : " + s);
        System.out.println("Cipher: " + encrypt(text, s));
        System.out.println(" " + tes);
        System.out.println("text : " + decrypt(tes, s));
    }*/
    
}
