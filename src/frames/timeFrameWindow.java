package frames;

import databaseHelper.MySQLConnectionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.util.Calendar;
 

public class timeFrameWindow extends JFrame {


    private Connection con;
    private JTextArea ta;

    public timeFrameWindow() {
        super("Search Vehicles that are about to expire");

        try {
            this.con= MySQLConnectionFactory.getConnection();
        } catch (SQLException e) {
            // show error
        }
        setSize(650, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridLayout(4, 1));
        JTextField plate;
        plate=new JTextField("Give number of Days");
        plate.setBounds(50,100, 200,30);
        add(plate);

        this.ta=  new JTextArea(200, 50);
        this.ta.setSize(650,400);
        JScrollPane scrollPane = new JScrollPane(ta);
        getContentPane().add(scrollPane);

        JButton OK= new JButton("Search");
        JButton Close= new JButton("Cancel");
        add(OK);
        add(Close);

        OK.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try{

                    if (
                            plate.getText().trim().length()==0
                    ){
                        throw new Exception("empty field");
                    }

                   int days= Integer.parseInt( plate.getText().trim());
                    searchExpiringVehiclesinDB(days);

                }catch(Exception ex){
                    final JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, ex.getLocalizedMessage(), "error",
                            JOptionPane.INFORMATION_MESSAGE);                    }
            }


        });

        Close.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }



    private void searchExpiringVehiclesinDB(int days) throws Exception
    {   Date expD8 =null,   regD8=null;

        //find insured vehicles
        Calendar currenttime = Calendar.getInstance();
        Date sqldate = new Date((currenttime.getTime()).getTime());
         String sql= "SELECT * FROM VEHICLESINSURANCECHECK.cars  WHERE ExpirationDate >'"+sqldate+"'" ;
         Statement stmt = con.createStatement();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            //for every insured vehicle
            String vehicles="";
             while(rs.next()) {


             String insco = rs.getString(6);
             String plate = rs.getString(8);

             expD8 = rs.getDate(5);
             regD8 = rs.getDate(4);

              //  if (expD8-NOW<= DAYS) THEN...

                 long diff = expD8.getTime() - sqldate.getTime();//in Milli seconds

                 long numOfDays = (long) diff/(1000*60*60*24);

                 if (numOfDays<=days) {
                     vehicles+= "Plate: " + plate + " ,Insurance Company:" + insco + " ,Registration Date:" + regD8.toString() + " ,Expiration Date:" + expD8.toString()+"\n";
                 }
            }
            this.ta.append(vehicles);

            }  catch(Exception e){
            System.out.println(e.getMessage());
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Uninsured vehicle" , "Insurance status",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean plateError(JTextField plate) {
        String platte=plate.getText().trim();

        for (int i=0; i<=2; i++){
            char c = platte.charAt(i);
            int ascii = (int)  c;
            if ((ascii >=65 && ascii <= 90) ){
            }else return true;
        }
        char c = platte.charAt(3);
        int minus = (int)  c;
        if (minus != 45 ) return true;

        for (int i=4; i<=7; i++){
            char test = platte.charAt(i);
            int num = (int)  test;
            if (num<48 || num>57){
                return true;
            }
        }
        return false;
    }

}







