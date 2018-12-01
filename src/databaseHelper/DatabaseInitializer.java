package databaseHelper;

import exception.DriverNotFoundException;

import java.sql.*;

public class DatabaseInitializer {

    public void initializeDataBase() throws SQLException {
        checkForJDBCDriver();

        Connection con = MySQLConnectionFactory.getConnectionWithoutSchema();
        Statement s=  con.createStatement();
        int result=s.executeUpdate("CREATE DATABASE if not exists VEHICLESINSURANCECHECK");
        //creation of table owners


        PreparedStatement stmt2=con.prepareStatement("Create table if not exists owners(Name varchar(20) not null,LastName varchar(30) not null," +
                "ownerId integer not null auto_increment,Address Varchar(60) not null,Telephone Varchar(30) not null,Mobile Varchar(30) not null, AFM Varchar(30) Unique not null, Primary Key(ownerId) );");
        //creation of table cars
        stmt2.execute();
        PreparedStatement stmt3=con.prepareStatement("Create table if not exists cars(year integer not null,Manufacturer varchar(30) not null," +
                "Model Varchar(30) not null,RegistrationDate DATE  ,ExpirationDate DATE  ,InsuranceCompany Varchar(30) ," +
                " Color varchar(20)not null," +
                "Plate varchar(10)not null,ownerId integer not null, Primary Key(plate)," +
                "foreign Key(ownerId) references owners(ownerId));");
        //creation of table fines
        stmt3.execute();
        PreparedStatement stmt4=con.prepareStatement("Create table if not exists fines(FineId integer not null auto_increment,ownerId integer not null," +
                "Amount float null, FineDate DATE  not null, plate varchar(10) not null, Primary Key (FineId), foreign Key(ownerId) references owners(ownerId)," +
                "foreign Key (plate) references cars(plate));");
        stmt4.execute();
        //textArea.setText("Db created");
    }

    public void importSampleData() throws SQLException {
            String sql = "INSERT INTO VEHICLESINSURANCECHECK.owners (Name,LastName,Address ,Telephone,Mobile,AFM) "
                    + "VALUES ('Xenofon','Kotsaris','Andrea_Papandreou_155_Ilion','2102622140','6980327900', '12345678' )";

            String sql2 = "INSERT INTO VEHICLESINSURANCECHECK.owners (Name,LastName,Address ,Telephone,Mobile,AFM) "
                    + "VALUES('Vaggelis','Agorogiannis','Andrea_Kalvou_155_Zografou','2102622142','6980327903','91375846' )";

            String sql3 = "INSERT INTO VEHICLESINSURANCECHECK.owners (Name,LastName,Address ,Telephone,Mobile,AFM) "
                    + "VALUES('Eleni','Dimopoulou','Papandreou_143_Ilion','2102522140','6980443330','74103265' )";

            String sql4 = "INSERT INTO VEHICLESINSURANCECHECK.owners (Name,LastName,Address ,Telephone,Mobile,AFM) "
                    + "VALUES('Giannis','Kurillos','Andrea_133_Kifissia','2109922140','6983427900','96587412' )";
            Statement sttt = MySQLConnectionFactory.getConnection().createStatement();
            sttt.executeUpdate(sql);
            sttt.executeUpdate(sql2);
            sttt.executeUpdate(sql3);
            sttt.executeUpdate(sql4);

            sql = "INSERT INTO VEHICLESINSURANCECHECK.cars (year,Manufacturer,Model ,RegistrationDate,ExpirationDate,InsuranceCompany,Color," +
                    " Plate,ownerId) "
                    + "VALUES(1998,'Lancia','Ypsilon','2017-08-09','2018-01-31','Interamerican','blue','ASD-1234',1 )";

            sql2 = "INSERT INTO VEHICLESINSURANCECHECK.cars (year,Manufacturer,Model ,RegistrationDate,ExpirationDate,InsuranceCompany,Color," +
                    " Plate,ownerId) "
                    + "VALUES(2008,'Fiat','Seicento','2017-09-09','2018-01-01','Interamerican','blue','SDF-1234',2 )";

            sql3 = "INSERT INTO VEHICLESINSURANCECHECK.cars (year,Manufacturer,Model ,RegistrationDate,ExpirationDate,InsuranceCompany,Color," +
                    " Plate,ownerId) "
                    + "VALUES(2009,'Ferrari','Murcielago','2018-09-09','2019-01-01','Interamerican','blue','QWE-1234',3 )";

            sql4 = "INSERT INTO VEHICLESINSURANCECHECK.cars (year,Manufacturer,Model ,RegistrationDate,ExpirationDate,InsuranceCompany,Color," +
                    " Plate,ownerId) "
                    + "VALUES(2018,'Fiat','Tipo','2017-09-09','2019-01-01','Interamerican','yellow','ERT-1234',2 )";

            sttt.executeUpdate(sql);
            sttt.executeUpdate(sql2);
            sttt.executeUpdate(sql3);
            sttt.executeUpdate(sql4);

            sql=  "INSERT INTO VEHICLESINSURANCECHECK.fines( ownerId, Amount  , FineDate  ," +
                    " plate ) VALUES (1, null,'2018-11-26','ASD-1234' )";
            sttt.executeUpdate(sql);
    }





    public boolean isConnectionPossible() {
        checkForJDBCDriver();
        try{
            MySQLConnectionFactory.getConnection();
            return true;
        }catch(SQLException e){
            return false;
        }
    }

    private void checkForJDBCDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found!");
            throw new DriverNotFoundException("Driver not found!", e);
        }
    }

}
