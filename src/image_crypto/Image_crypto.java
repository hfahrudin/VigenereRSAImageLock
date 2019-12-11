/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image_crypto;

import java.awt.Color; 
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon; 
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException; 
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.DefaultMetalTheme; 
import javax.swing.plaf.metal.MetalLookAndFeel; 
import javax.swing.plaf.metal.OceanTheme;

/**
 *
 * @author Hasby
 */
public class Image_crypto extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    final static String LOOKANDFEEL = "System";
    final static String THEME = "Ocean";
    JButton enc = new JButton("Encrypt");
    JButton dec = new JButton("Decrypt"); 
    JLabel title = new JLabel("Image Crypto");
    JLabel title2 = new JLabel("With Viginere and RSA");

    public Image_crypto(){
        setTitle("Image Crypto");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(300,175); 
        setLocationRelativeTo(null); 
        
        title.setBounds(80,10,500,30);
        title.setFont(new java.awt.Font("Tahoma", 0, 18));
        
        title2.setBounds(50, 30, 500, 30);
        title2.setFont(new java.awt.Font("Tahoma", 0, 18));
        
        
        enc.setBounds(30,80,100,30); 
        dec.setBounds(150,80,100,30); 
        enc.setFocusable(false);
        dec.setFocusable(false);
        enc.setFont(new java.awt.Font("Tahoma", 0, 16));
        dec.setFont(new java.awt.Font("Tahoma", 0, 16)); 

        add(title);
        add(title2);
        add(enc);
        add(dec);

        enc.addActionListener((java.awt.event.ActionEvent evt) -> { 
            EnkripFrame ef = new EnkripFrame(this); 
            ef.setVisible(true);
            this.setVisible(false);
        });
        dec.addActionListener((java.awt.event.ActionEvent evt) -> { 
            DekripFrame df = new DekripFrame(this); 
            df.setVisible(true);
            this.setVisible(false);
        });
    }

    public static void initLookAndFeel() { 
        String lookAndFeel;
        if (LOOKANDFEEL != null) {
            if (LOOKANDFEEL.equals("Metal")) {
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            } else if (LOOKANDFEEL.equals("System")) {
                lookAndFeel = UIManager.getSystemLookAndFeelClassName();
            } else if (LOOKANDFEEL.equals("Motif")) {
                lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            } else if (LOOKANDFEEL.equals("GTK")) {
                 lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            } else {
                System.err.println("Unexpected value of LOOKANDFEEL specified: " + LOOKANDFEEL);
            lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            }

            try {
                UIManager.setLookAndFeel(lookAndFeel);
                if (LOOKANDFEEL.equals("Metal")) {
                    if (THEME.equals("DefaultMetal")) { 
                        MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
                    }else if (THEME.equals("Ocean")) { 
                        MetalLookAndFeel.setCurrentTheme(new OceanTheme());
                    }else{
                        UIManager.setLookAndFeel(new MetalLookAndFeel());
                    }
                }
            }catch (ClassNotFoundException e) {
                System.err.println("Couldn't find class for specified look and feel:"
                + lookAndFeel);
                System.err.println("Did you include the L&F library in the class path?");
                System.err.println("Using the default look and feel.");
            } catch (UnsupportedLookAndFeelException e) { 
                System.err.println("Can't use the specified look and feel (" + lookAndFeel + ") on this platform."); 
                System.err.println("Using the default look and feel.");
            } catch (IllegalAccessException | InstantiationException e) { 
                System.err.println("Couldn't get specified look and feel (" + lookAndFeel + "), for some reason."); 
                System.err.println("Using the default look and feel.");
            }
        }
    }


    public static void main(String[] args) { 
        initLookAndFeel();
        new Image_crypto().setVisible(true);
    }
}
