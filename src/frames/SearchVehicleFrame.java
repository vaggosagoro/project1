package frames;

import databaseHelper.MySQLConnectionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;
import java.util.Calendar;
 


    public class SearchVehicleFrame extends JFrame {


        private Connection con;

        public SearchVehicleFrame() {
            super("Search Vehicle");

            try {
                this.con= MySQLConnectionFactory.getConnection();
            } catch (SQLException e) {
                //show error message
            }
            setSize(250, 150);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            setLayout(new GridLayout(3, 1));
            JTextField plate,Manufacturer,model,regD8,expD8,InsCo,Color,year;
            plate=new JTextField("Plate number");
            plate.setBounds(50,100, 200,30);
            add(plate);


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


                        if (  plateError(plate)){
                            throw new Exception("Wrong Plate Format");
                        }

                        searchVehicleinDB(plate.getText().trim());

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



        private void searchVehicleinDB(String plate) throws Exception
        {
            Date expD8 =null,   regD8=null;
            String sql= "SELECT * FROM VEHICLESINSURANCECHECK.cars  WHERE plate='"+plate+"'" ;
               Statement stmt = con.createStatement();
            try {
                ResultSet rs = stmt.executeQuery(sql);

                if(!rs.next()){
                    final JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, "No such vehicle in DB" , "Insurance status",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String insco = rs.getString(6);
                    expD8 = rs.getDate(5);
                    regD8 = rs.getDate(4);
                    Calendar currenttime = Calendar.getInstance();
                    Date sqldate = new Date((currenttime.getTime()).getTime());
                    if (expD8.compareTo(sqldate)<0)
                    {
                        throw new Exception ();
                     }

                    final JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, "Insurance Company:" + insco + " ,Registration Date:" + regD8.toString() + " ,Expiration Date:" + expD8.toString(), "Insurance status",
                            JOptionPane.INFORMATION_MESSAGE);
                }}  catch(Exception e){

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






