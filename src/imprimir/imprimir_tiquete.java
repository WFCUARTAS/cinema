package imprimir;



import cinema.administrador;
import clases.*;
import imprimir.*;
import java.awt.print.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class imprimir_tiquete {
    conectar cc_resolucion =new conectar();
    Connection con_resolucion;
    
    usuario user = new usuario();
    
    public imprimir_tiquete(usuario us,pelicula p, funcion f, ArrayList<String> s, String fecha,ArrayList<Integer> t, boolean dosxuno,ArrayList<Integer> c ) {
        super();
        
        user=us;

        PrinterJob printJob = PrinterJob.getPrinterJob();
        Book libro = new Book();
        Paper pa = new Paper();

        pa.setSize(164, 500);
        pa.setImageableArea(0,0, pa.getWidth(), pa.getHeight()); 
        PageFormat documentoPF = new PageFormat();
        documentoPF.setPaper(pa);
        
        ArrayList<String> resolucion = new ArrayList<String>();
        resolucion=consultar_resolucion(c.get(0));
        
        ArrayList<String> datos = new ArrayList<String>();
        datos.add(p.get_nombre());          // 0   nombre pelicula
        datos.add(f.get_hora_completa());   // 1   hora funcion 1
        datos.add(""+f.get_sala());         //  2    sala funcio 2
        datos.add(fecha);                   //  3  fecha funcion
        datos.add(""+new Date());           // 4  fecha de venta
        
            
        
        
        for(int i=0;i<s.size();i++){
            if(Integer.parseInt(resolucion.get(4))<c.get(i)){
                resolucion=consultar_resolucion(c.get(i));
            }
            
            libro.append(new tiquete(datos, resolucion, s.get(i),"", c.get(i),t.get(i)),documentoPF);
            
            
        }

        printJob.setPageable(libro);

        try {
            printJob.print();
        } catch (Exception PrinterException) {
            PrinterException.printStackTrace();
        }

    }

    public ArrayList<String> consultar_resolucion(int c){
        int consecutivo =c;
        
        ArrayList<String> res = new ArrayList<String>();
        con_resolucion = cc_resolucion.conexion(user.get_ip());
        
        try {
            Statement st = con_resolucion.createStatement();
            ResultSet result =st.executeQuery("SELECT * FROM resoluciones  where inicio<='"+consecutivo+"' and fin>='"+consecutivo+"' limit 1");
            
            result.next();
                    
            res.add(result.getString(1));
            res.add(result.getString(2));
            res.add(result.getString(3));
            res.add(result.getString(4));
            res.add(result.getString(5));
            
            
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 48:  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        return res;
    }

}