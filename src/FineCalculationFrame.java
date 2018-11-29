import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Connection;

public class FineCalculationFrame extends JFrame {

    private Connection con;

    public FineCalculationFrame(Connection conn) {
        super("Total Fine Cost");

        this.con = conn;
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridLayout(9, 1));
        JTextField afm;
        afm=new JTextField("Enter AFM");
        afm.setBounds(50,100, 200,30);
        add(afm);

        JButton OK= new JButton("Confirm");
        JButton Close= new JButton("Cancel");
        add(OK);
        add(Close);

        OK.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try{

                    if (
                            afm.getText().trim().length()==0
                    ){
                        throw new Exception("empty field");
                    }

                    String myafm=afm.getText().trim();

                    if (!checkafm(myafm)){
                        throw new Exception("Wrong AFM Format");
                    }

                    searchAFMinDB(myafm);

                }catch(Exception ex){
                    final JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, ex.getLocalizedMessage(), "error",
                            JOptionPane.INFORMATION_MESSAGE);}
            }


        });

        Close.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);


        JTextField fine;
        fine=new JTextField("Fine");
        fine.setBounds(50,100, 200,30);
        add(fine);

        JButton OK1= new JButton("Calculate");
        JButton Close1= new JButton("Cancel");
        add(OK1);
        add(Close1);

        OK1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try{

                    if (
                            fine.getText().trim().length()==0
                    ){
                        throw new Exception("empty field");
                    }

                    String myfine=fine.getText().trim();

                    if (!isInteger(myfine)){
                        throw new Exception("Wrong Fine Format");
                    }

                    String afm1 = afm.getText().trim();

                    CalcFun(myfine, afm1);

                }catch(Exception ex){
                    final JPanel panel = new JPanel();
                    JOptionPane.showMessageDialog(panel, ex.getLocalizedMessage(), "error",
                            JOptionPane.INFORMATION_MESSAGE);                    }
            }


        });

        Close1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void searchAFMinDB(String afm) throws Exception {

        String sql= "SELECT * FROM VEHICLESINSURANCECHECK.owners  WHERE AFM='"+afm+"'" ;
        Statement stmt = con.createStatement();
        try {
            ResultSet rs = stmt.executeQuery(sql);

            if(!rs.next()){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "No such owner in DB" , "Owner",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                String fname = rs.getString(1);
                String lname = rs.getString(2);

                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "FirstName:" + fname + " ,LastName:" + lname , "Owner",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception ex){
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, ex.getLocalizedMessage(), "error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public boolean checkafm(String afm){

        if (afm.length()!=8) return false;

        for (int i = 0; i < afm.length(); i++) {
            if(!Character.isDigit(afm.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    public boolean isInteger(String fine) {

        for (int i = 0; i < fine.length(); i++) {
            if(!Character.isDigit(fine.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private void CalcFun (String cost, String afm) throws Exception {

        int mycost = Integer.parseInt(cost);

        String sql= "SELECT COUNT(*) AS total FROM fines f join owners o on f.ownerId=o.ownerId WHERE o.AFM='"+afm+"'";
        Statement stmt = con.createStatement();
        try {
            ResultSet rs = stmt.executeQuery(sql);

            if(!rs.next()){
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "No such owner in DB" , "Total Fine",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {

               // int ownId= rs.getInt(2);
                int tot = rs.getInt("total");
                int finalcost=mycost*tot;

//                String sql1= "UPDATE fines SET amount='" +finalcost+" where ownerId='"+ownId+"'";
//                PreparedStatement preparedStmt = con.prepareStatement(sql1);
//                preparedStmt.executeUpdate();

                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Fine:" + finalcost , "Owner",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception ex){
            final JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, ex.getLocalizedMessage(), "error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
