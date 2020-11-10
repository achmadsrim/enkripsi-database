/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skripa3l;

import javax.swing.SwingUtilities;


/**
 *
 * @author KAMPRTO
 */
public class Skripa3l {
    

 public static void main(String[] args) {
        // TODO code application logic here
           try {
            
            SwingUtilities.updateComponentTreeUI(new Login());
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    new Login().setVisible(true);
                }
            });
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}