/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author ZZZ
 */
public class conectar {
    Connection con=null;
    
    public Connection conexion(String ip){
        try{
            String contra="";
            
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://"+ip+"/cine-espinal","root",contra);
            //JOptionPane.showMessageDialog(null,"conexion exitosa");
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"error conexion a base de datos "+ e,"error",JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
}
