package Electricity;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener{
    JLabel l1, l2;
    JTextArea t1;
    JButton b1;
    JButton b2;
    Choice c2;
    JPanel p1;
    String meter;
    GenerateBill(String meter){
        this.meter =meter;
        setSize(500,600);
        setLocation(750,100);
//      setLayout(new BorderLayout());
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBounds(0,0,500,60);
        
        l1 = new JLabel("Generate Bill");
        l1.setBounds(140,20,70,20);
        
        l2 = new JLabel(meter);
        l2.setBounds(220,20,50,20);
        c2 = new Choice();
        c2.setBounds(280,20,100,30);
        
        c2.add("January");
        c2.add("February");
        c2.add("March");
        c2.add("April");
        c2.add("May");
        c2.add("June");
        c2.add("July");
        c2.add("August");
        c2.add("September");
        c2.add("October");
        c2.add("November");
        c2.add("December");

        
        t1 = new JTextArea(50,15);
        t1.setText("\n\n\t------- Click on the -------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month\n\n");
        JScrollPane jsp = new JScrollPane(t1);
        jsp.setBounds(480,0,20,440);
        t1.setFont(new Font("Senserif",Font.ITALIC,18));
        t1.setBounds(3,60,480,440);
        
        b1 = new JButton("Generate Bill");
        b1.setBounds(110,520,100,30);
        
          b2=new JButton("Print Bill");
        b2.setBounds(280,520,100,30);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
          
            
            try{
    t1.print();
}catch(Exception f){
}
            
            }
        });
        
        
        p1.add(l1);
        p1.add(l2);
        p1.add(c2);
        add(p1);
        add(t1);
        
        add(jsp);
        add(b1);
        add(b2);
        
        b1.addActionListener(this);
        setVisible(true);
        
       
    }
    public void actionPerformed(ActionEvent ae){
        try{
            Conn2 c = new Conn2();
   
            String month = c2.getSelectedItem();
            t1.setText("\tReliance Power Limited\nELECTRICITY BILL FOR THE MONTH OF "+month+" ,2021\n\n\n");
            
            ResultSet rs = c.s.executeQuery("select * from customer where meter="+meter);
            
            if(rs.next()){
                t1.append("\n    Customer Name:"+rs.getString("name"));
                t1.append("\n    Meter Number:  "+rs.getString("meter"));
                t1.append("\n    Address:            "+rs.getString("address"));
                t1.append("\n    State:             "+rs.getString("state"));
                t1.append("\n    City:                   "+rs.getString("city"));
                t1.append("\n    Email:                "+rs.getString("email"));
                t1.append("\n    Phone Number  "+rs.getString("phone"));
                t1.append("\n-------------------------------------------------------------");
                t1.append("\n");
            }
            
            rs = c.s.executeQuery("select * from meter_info where meter_number = " + meter);
            
            if(rs.next()){
                t1.append("\n    Meter Location:"+rs.getString("meter_location"));
                t1.append("\n    Meter Type:      "+rs.getString("meter_type"));
                t1.append("\n    Phase Code:    "+rs.getString("phase_code"));
                t1.append("\n    Bill Type:         "+rs.getString("bill_type"));
                t1.append("\n    Days:               "+rs.getString("days"));
                t1.append("\n");
            }
            rs = c.s.executeQuery("select * from tax");
            if(rs.next()){
                t1.append("---------------------------------------------------------------");
                t1.append("\n\n");
                t1.append("\n Cost per Unit:             Rs "+rs.getString("cost_per_unit"));
                t1.append("\n Meter Rent:                Rs "+rs.getString("meter_rent"));
                t1.append("\n Service Charge:            Rs "+rs.getString("service_charge"));
                t1.append("\n Service Tax:               Rs "+rs.getString("service_tax"));
                t1.append("\n Swacch Bharat Cess:        Rs "+rs.getString("swacch_bharat_cess"));
                t1.append("\n Fixed Tax:                 Rs "+rs.getString("fixed_tax"));
                t1.append("\n");
                
            }
            
            rs = c.s.executeQuery("select * from bill where meter="+meter+" AND month = '"+c2.getSelectedItem()+"'");
            
            if(rs.next()){
                t1.append("\n    Current Month :\t"+rs.getString("month"));
                t1.append("\n    Units Consumed:\t"+rs.getString("units"));
                t1.append("\n    Total Charges :\t"+rs.getString("total_bill"));
                t1.append("\n---------------------------------------------------------------");
                t1.append("\n    TOTAL PAYABLE :\t"+rs.getString("total_bill"));
            }
            
            
            
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        try {
  
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        new GenerateBill("").setVisible(true);
    }

    
}

//b2 = new JButton("Print");
//        b2.setBounds(120, 70, 80, 20);
//        b2.addActionListener(this);
//        add(b2);

//            try{
//                Conn2 c = new Conn2();
//                ResultSet rs = c.s.executeQuery(str);
//                t1.setModel(DbUtils.resultSetToTableModel(rs));
//            }catch(Exception e){}
//        }else if(ae.getSource() == b2){
//            try{
//                t1.print();
//            }catch(Exception e){}
//        }