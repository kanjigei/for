/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nea.projects;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import static nea.projects.window.J;
import static nea.projects.window.renderMandelbrotset;
import static nea.projects.window.frame;
import static nea.projects.window.keyListener;
import static nea.projects.window.mouseListener;



/**
 *
 * @author 15choasi
 */

public class main {
    
public static void main(String[] args) {
       
        EventQueue.invokeLater(() -> {  
        
        frame = new JFrame("mandelbrot set");
        frame.addMouseListener(mouseListener());
        frame.addKeyListener(keyListener());
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.add(new window());
//        frame.getContentPane().add(ls);
        frame.getContentPane().add(J,BorderLayout.PAGE_END);
//        frame.getContentPane().addKeyListener(keyListener());
       
//        Component add = frame.getContentPane().add(this);
//        frame.getContentPane().add(this);
        
        
        
        frame.pack();
        frame.setVisible(true);
        
        renderMandelbrotset();
        
        });
      }
 }
