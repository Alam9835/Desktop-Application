package Electricity;

import javax.swing.*;
import java.awt.*;
 
public class Splash extends JFrame implements Runnable{
     JLabel image1;
     Thread T1;
    public Splash(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image_1=new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
        ImageIcon image_2=new ImageIcon(image_1.getImage().getScaledInstance(500, 300,Image.SCALE_DEFAULT));
        image1=new JLabel(image_2);
        
        add(image1);
        setUndecorated(true);
        setVisible(true);
        getresized();
        T1=new Thread(this);
        T1.start();
    }
    
    private void getresized() {
            int x=1;
        for(int i=1;i<=300;i+=6,x+=7){
            setBounds(750-(x+i)/2,400-(i/2),x+i,i+100);
            try{
                 Thread.sleep(10);
            }
            catch(Exception e){

            }
        }
    }
    public static void main(String[] args) {
        try {
  
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        new Splash();
    }
    @Override
    public void run() {
        try{
            Thread.sleep(7000);
            this.setVisible(false);
            new Login();
        }
        catch(Exception e){

        }
        
    }

}


//"icon/elect.jpg"