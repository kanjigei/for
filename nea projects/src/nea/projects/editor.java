/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nea.projects;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import nea.projects.editor.TColour.DrawingCanvas;
import static nea.projects.window.hue;
import static nea.projects.window.renderMandelbrotset;

//import nea.projects.TColour.DrawingCanvas;
/**
 *
 * @author 15choasi
 */
public class editor extends JFrame{
    
    public float hsbValues;
    DrawingCanvas canvas;
    public static String mysa;
    public JLabel rgbValue;
    public static int rgb, pas;
    public static float[] hsb;
    public static Color sdf;
    
    
public editor() {
    getContentPane().add(new TColour());
//    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 700);
    setLocationRelativeTo(null);
    
    JButton apply = new JButton("apply");
    apply.setActionCommand("apply");
    
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            String action = ae.getActionCommand();
            if (action.equals("apply")){
                mysa = rgbValue.getText();
                
                if (mysa != null){
                    try{
                        sdf = Color.decode("0x"+mysa);
                        pas = sdf.getRGB();
                    }catch (NumberFormatException e) {
                        sdf = Color.WHITE;
                        System.out.println("goofed it up mate");
                    }
                }else{
                    sdf = Color.WHITE;
                }
                
//                int editorColour = Integer.parseInt(mysa);
//                int red = (editorColour>>16)&0xFF;
//                int blue = editorColour&0xFF;
//                int green = (editorColour>>8)&0xFF;
//                hsb = Color.RGBtoHSB(red, green, blue, null);
//                        
//                float hue = hsb[0];
//                float saturation = hsb[1];
//                float brightness = hsb[2];
//                
//                rgb = Color.HSBtoRGB(hue, saturation, brightness);
                System.out.println("RGB value: "+mysa+"| red, green, blue values: "+sdf + "| RGB: "+pas + "| hue: "+hue);
                renderMandelbrotset();
//                System.out.println(red +" " +blue+ "  "+ green +"  "+ rgb);
            //    float muasd = displayHSBcolor;
            }
            
        }
    };
    apply.addActionListener(listener);
    
    
    getContentPane().add(apply,BorderLayout.PAGE_END);
    
//    setVisible(true);
  }


class TColour extends JPanel {
  DrawingCanvas canvas = new DrawingCanvas();
 

  JSlider sliderR, sliderG, sliderB, sliderH, sliderS, sliderBr,
      sliderAlpha;

  public TColour() {
    sliderR = getSlider(0, 255, 0, 50, 5);
    sliderG = getSlider(0, 255, 0, 50, 5);
    sliderB = getSlider(0, 255, 0, 50, 5);
    sliderH = getSlider(0, 10, 0, 5, 1);
    sliderS = getSlider(0, 10, 0, 5, 1);
    sliderBr = getSlider(0, 10, 0, 5, 1);
    sliderAlpha = getSlider(0, 255, 255, 50, 5);
    rgbValue = new JLabel("");
     

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(6, 2, 15, 10));
  
   
   

    panel.add(new JLabel("R-G-B Sliders (0 - 255)"));
    panel.add(new JLabel("H-S-B Sliders (ex-1)"));
    panel.add(sliderR);
    panel.add(sliderH);
    panel.add(sliderG);
    panel.add(sliderS);
    panel.add(sliderB);
    panel.add(sliderBr);
    
   
    
    
    
    

    panel.add(new JLabel("Alpha Adjustment (0 - 255): ", JLabel.RIGHT));
    panel.add(sliderAlpha);

    panel.add(new JLabel("RGB Value: ", JLabel.RIGHT));
    
    rgbValue.setBackground(Color.white);
    rgbValue.setForeground(Color.black);
    rgbValue.setOpaque(true);
    panel.add(rgbValue);

    add(panel, BorderLayout.SOUTH);
    add(canvas);
    
    
  }

  public JSlider getSlider(int min, int max, int init, int mjrTkSp, int mnrTkSp) {
    JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, init);
    slider.setPaintTicks(true);
    slider.setMajorTickSpacing(mjrTkSp);
    slider.setMinorTickSpacing(mnrTkSp);
    slider.setPaintLabels(true);
    slider.addChangeListener(new SliderListener());
    return slider;
  }

  class DrawingCanvas extends Canvas {
    Color colour;
    int redValue, greenValue, blueValue;
    int alphaValue = 255;
    float[] hsbValues = new float[3];

    public DrawingCanvas() {
      setSize(350, 350);
      colour = new Color(40, 40, 20);
      setBackgroundColour();
    }

    public void setBackgroundColour() {
      colour = new Color(redValue, greenValue, blueValue, alphaValue);
      setBackground(colour);
    }
  }

  class SliderListener implements ChangeListener {
    public void stateChanged(ChangeEvent e) {
      JSlider slider = (JSlider) e.getSource();

      if (slider == sliderAlpha) {
        canvas.alphaValue = slider.getValue();
        canvas.setBackgroundColour();
      } else if (slider == sliderR) {
        canvas.redValue = slider.getValue();
        displayRGBColor();
      } else if (slider == sliderG) {
        canvas.greenValue = slider.getValue();
        displayRGBColor();
      } else if (slider == sliderB) {
        canvas.blueValue = slider.getValue();
        displayRGBColor();
      } else if (slider == sliderH) {
        canvas.hsbValues[0] = (float) (slider.getValue() * 0.1);
        displayHSBColor();
      } else if (slider == sliderS) {
        canvas.hsbValues[1] = (float) (slider.getValue() * 0.1);
        displayHSBColor();
      } else if (slider == sliderBr) {
        canvas.hsbValues[2] = (float) (slider.getValue() * 0.1);
        displayHSBColor();
      }
      canvas.repaint();
    }

    public void displayRGBColor() {
      canvas.setBackgroundColour();
      Color.RGBtoHSB(canvas.redValue, canvas.greenValue, canvas.blueValue,canvas.hsbValues);
      sliderH.setValue((int) (canvas.hsbValues[0] * 10));
      sliderS.setValue((int) (canvas.hsbValues[1] * 10));
      sliderBr.setValue((int) (canvas.hsbValues[2] * 10));

      rgbValue.setText(Integer.toString(canvas.colour.getRGB() & 0xffffff, 16));
      
    }

    public void displayHSBColor() {
      canvas.colour = Color.getHSBColor(canvas.hsbValues[0],
          canvas.hsbValues[1], canvas.hsbValues[2]);
      canvas.redValue = canvas.colour.getRed();
      canvas.greenValue = canvas.colour.getGreen();
      canvas.blueValue = canvas.colour.getBlue();

      sliderR.setValue(canvas.redValue);
      sliderG.setValue(canvas.greenValue);
      sliderB.setValue(canvas.blueValue);

      canvas.colour = new Color(canvas.redValue, canvas.greenValue,
          canvas.blueValue, canvas.alphaValue);
      canvas.setBackground(canvas.colour);
    }
  }
    
//    @Override
//    public void addNotify(){
//    setPreferredSize(new Dimension(500, 700));
//    
//    }
    
    
}
}
