/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import clases.conectar;
import clases.funcion;
import clases.pelicula;
import clases.tarjeta;
import clases.usuario;
import imprimir.imprimir_factura_gafas;
import imprimir.imprimir_tiquete;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 ESTADOS 
 * 0= DESOCUPADO
 * 1= SELECIONADA
 * 2= OCUPADA
 * 3= Apartada
 * 4=convenios
 * 5=cortecias
 */
public class sala3 extends javax.swing.JPanel implements ActionListener{

    conectar cc =new conectar();
    Connection con;
    JFrame padre = new JFrame();
    usuario user= new usuario();
    
    private int tam=121;
    private int estado[]= new int[tam];
    private int estado_base[]= new int[tam];
    private String[] apartadas = new String [tam];
    private String[] id_venta = new String [tam];
    
    pelicula pelicula_select = new pelicula();
    funcion funcion_selec = new funcion();
    int cantidad=0;
    Color color_estado []= new Color[6];

    boolean dosxuno=false;
    String fecha="";
    String fecha2="";
    Calendar fe;
    int tiempo= 0;
    public boolean hilo=true;
    
    int precio_general, precio_Preferencial;
    
    private JButton [] sillas = new JButton[tam];
    private String[] nombre_sillas = {"A1","A2","A3","A4","A5","A6","A7","A8","A9","A10","A11","A12","A13","A14",
                                      "B1","B2","B3","B4","B5","B6","B7","B8","B9","B10","B11","B12","B13","B14",
                                      "C1","C2","C3","C4","C5","C6","C7","C8","C9","C10","C11","C12","C13","C14",
                                      "D1","D2","D3","D4","D5","D6","D7","D8","D9","D10","D11","D12","D13",
                                      "E1","E2","E3","E4","E5","E6","E7","E8","E9","E10","E11","E12","E13",
                                      "F1","F2","F3","F4","F5","F6","F7","F8","F9","F10","F11","F12","F13",
                                      "G1","G2","G3","G4","G5","G6","G7","G8","G9","G10","G11","G12",
                                      "H1","H2","H3","H4","H5","H6","H7","H8","H9","H10","H11","H12",
                                      "I1","I2","I3","I4","I5","I6","I7","I8","I9","I10","I11","I12","I13","I14","I15","I16"};
    
    
    
    public sala3(pelicula p,funcion f, java.awt.Frame parent, Calendar fec, usuario us) {
        padre=(JFrame) parent;
        user=us;
        con = cc.conexion(user.get_ip());
        pelicula_select=p;
        funcion_selec=f;
        fecha=fec.get(Calendar.YEAR)+"-"+(fec.get(Calendar.MONTH)+1)+"-"+fec.get(Calendar.DAY_OF_MONTH);
        fecha2=fec.get(Calendar.DAY_OF_MONTH)+"-"+(fec.get(Calendar.MONTH)+1)+"-"+fec.get(Calendar.YEAR);
        fe=fec;
        
        color_estado [0]=new Color(100, 255, 100);
        color_estado [1]=new Color(255,255,0);
        color_estado [2]=new Color(255, 0, 0); 
        color_estado [3]=new Color(255,100,0);
        color_estado [4]=new Color(255,0,153);
        color_estado [5]=new Color(0,0,255);
        
        initComponents();
        insta_boton();
        cargar();
        new Thread(new hilo_tiempo()).start();
    }
    
    
    private class hilo_tiempo implements Runnable{

        @Override
        public void run() {
            //throw new UnsupportedOperationException("Not supported yet.");
            //To change body of generated methods, choose Tools | Templates.
            try {
                tiempo=0;
                while(tiempo<30 && hilo){
                Thread.sleep(2000);
                tiempo=tiempo+1;
                }
                if(hilo && padre.isActive()){
                    Inicio inicio = new Inicio(user);
                    inicio.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                    inicio.setResizable(false);
                    inicio.setVisible(true); 
                    padre.dispose();
                }
                
            } catch (Exception e) {
            // Mensaje en caso de que falle
            }
        } 
    }
    
    public void insta_boton(){
        
        ArrayList<JButton> botones = new ArrayList<JButton>();
        
        botones.add(A1);botones.add(A2);botones.add(A3);botones.add(A4);botones.add(A5);botones.add(A6);botones.add(A7);botones.add(A8);
        botones.add(A9);botones.add(A10);botones.add(A11);botones.add(A12);botones.add(A13);botones.add(A14);
        
        botones.add(B1);botones.add(B2);botones.add(B3);botones.add(B4);botones.add(B5);botones.add(B6);botones.add(B7);botones.add(B8);
        botones.add(B9);botones.add(B10);botones.add(B11);botones.add(B12);botones.add(B13);botones.add(B14);
        
        botones.add(C1);botones.add(C2);botones.add(C3);botones.add(C4);botones.add(C5);botones.add(C6);botones.add(C7);botones.add(C8);
        botones.add(C9);botones.add(C10);botones.add(C11);botones.add(C12);botones.add(C13);botones.add(C14);
        
        botones.add(D1);botones.add(D2);botones.add(D3);botones.add(D4);botones.add(D5);botones.add(D6);botones.add(D7);botones.add(D8);
        botones.add(D9);botones.add(D10);botones.add(D11);botones.add(D12);botones.add(D13);
        
        botones.add(E1);botones.add(E2);botones.add(E3);botones.add(E4);botones.add(E5);botones.add(E6);botones.add(E7);botones.add(E8);
        botones.add(E9);botones.add(E10);botones.add(E11);botones.add(E12);botones.add(E13);       
        
        botones.add(F1);botones.add(F2);botones.add(F3);botones.add(F4);botones.add(F5);botones.add(F6);botones.add(F7);botones.add(F8);
        botones.add(F9);botones.add(F10);botones.add(F11);botones.add(F12);botones.add(F13);
        
        
        botones.add(G1);botones.add(G2);botones.add(G3);botones.add(G4);botones.add(G5);botones.add(G6);botones.add(G7);botones.add(G8);
        botones.add(G9);botones.add(G10);botones.add(G11);botones.add(G12);
       
        
        botones.add(H1);botones.add(H2);botones.add(H3);botones.add(H4);botones.add(H5);botones.add(H6);botones.add(H7);botones.add(H8);
        botones.add(H9);botones.add(H10);botones.add(H11);botones.add(H12);
        
        
        botones.add(I1);botones.add(I2);botones.add(I3);botones.add(I4);botones.add(I5);botones.add(I6);botones.add(I7);botones.add(I8);
        botones.add(I9);botones.add(I10);botones.add(I11);botones.add(I12);botones.add(I13);botones.add(I14);botones.add(I15);
        botones.add(I16);
        
        for(int i=0;i<tam;i++){
            sillas[i]=botones.get(i);
            sillas[i].addActionListener(this);
        }
    }
   
    public void venta(int gafas, String convenio_id, int convenio_valor,tarjeta t) throws ParseException{
        tiempo=0;
        tarjeta tar = t;
        ArrayList<String> sillas_nombre = new ArrayList<String>();
        ArrayList<Integer> silla_numero = new ArrayList<Integer>();
        ArrayList<Integer> Costo_silla = new ArrayList<Integer>();
        ArrayList<Integer> consecutivo = new ArrayList<Integer>();
        String sql;
        int cont=0;
        int valor_gafas=0;
        String compra="";
        
        if(tar.get_id()==null){
            calcular_precio();
        }else{
            precio_general = tar.get_precio_general() ;
            precio_Preferencial = tar.get_precio_preferencial();
        }
                
        int valor_total=0;
        
        if(cantidad>0){
            for(int i=0;i<estado.length;i++){               
                if (estado[i]==1){              
                    
                    int costo_unitario=0;
                    char inicial = nombre_sillas[i].charAt(0);
                    
                    if(convenio_id != null){
                        costo_unitario=convenio_valor;
                    }
                    else if(inicial=='G' || inicial=='H'|| inicial=='I'){
                        costo_unitario = precio_Preferencial;
                    }
                    else{
                        costo_unitario = precio_general;
                    }
                    
                    valor_total+= costo_unitario;
                    sillas_nombre.add(nombre_sillas[i]);
                    Costo_silla.add(costo_unitario);
                    silla_numero.add(i+1);
                    
                    compra=compra+"   "+nombre_sillas[i];
                    cont=cont+1;
                }  
            }
            if(validar_consecutivo(cont)){
                try{
                    if(gafas!=0){
                        Statement st1= con.createStatement(); 
                        st1 = con.createStatement();
                        ResultSet result1 =st1.executeQuery("SELECT valor2d FROM precios where dia='8' ");
                        result1.next();
                        valor_gafas = result1.getInt(1);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"error 51:  "+ex.getMessage());
                }
                                
                confirmar_venta confirmar= new confirmar_venta(padre,true,compra,valor_total,fe, (gafas*valor_gafas),t);
                confirmar.setVisible(true);

                if (confirmar.es_true()) {
                    try{ 
                        Statement st = con.createStatement();
                        ResultSet result =st.executeQuery("SELECT MAX(consecutivo)+1 from ventas");
                        result.next();
                        int max_con = result.getInt(1);
                        
                        sql="INSERT INTO ventas (silla,fecha_funcion,funcion_id,conveion_id,consecutivo, costo,usuario_id, tarjeta_id) VALUES ";
                        for(int i=0;i<sillas_nombre.size();i++){                             
                            if(i!=0){
                                sql=sql+" , ";
                            }
                            consecutivo.add(max_con+i);
                            
                            sql=sql+"("+silla_numero.get(i)+",'"+fecha+"',"+funcion_selec.get_id()+","+convenio_id+", "+(max_con+i)+","+Costo_silla.get(i)+","+user.get_id()+","+t.get_id()+")";
                        }
                        
                        PreparedStatement pst= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        int n=pst.executeUpdate();
                        
                        if (n>0) {
                            int s;
                            hilo=false;
                            if(confirmar.incluir_gafa){
                                gurdar_gafas(gafas,valor_gafas);
                            }
                            do{
                                imprimir_tiquete imprimir = new imprimir_tiquete(user, pelicula_select,funcion_selec,sillas_nombre,fecha2,Costo_silla,dosxuno,consecutivo);
                                s = JOptionPane.showOptionDialog(
                                this,
                                "Venta exitosa", 
                                "Resultado venta",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,    // null para icono por defecto.
                                new Object[] { "OK", "i"},   // null para YES, NO y CANCEL
                                "OK");
                            }while(s==1);
                            tiempo=0;
                            hilo=true;
                            new Thread(new hilo_tiempo()).start();
                            cargar();
                        }
                    } catch (SQLException ex) {
                        if(ex.getErrorCode() == 1062){
                            JOptionPane.showMessageDialog(null,"alguna boleta ya fue vendida actualice estado de la sala:  ","error",JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"error 34:  "+ex.getMessage());
                        }
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"El sistema no cuenta con una resolucion para generar facturar ","error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar alguna silla ","error",JOptionPane.ERROR_MESSAGE);
        }
    }
   
    public void convenio(int gafas) throws ParseException{
        if(cantidad>0){
            convenios convenio= new convenios(padre, true, user.get_ip());
            convenio.setVisible(true);
            String id=convenio.get_id();
            int val =0;
            if(pelicula_select.get_tipo().equals("2D")){
                val =convenio.valor_total();
             }else{
                val =convenio.valor_total3d();
             }
            
            if (id!= null) {
                venta(gafas,id,val,new tarjeta());
            }
            else{
                JOptionPane.showMessageDialog(null,"no se selecciono convenio","error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar algina silla ","error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void tarjeta_venta (int gafas) throws ParseException{
        if(cantidad>0){
            tarjetas_leer panel_tarjeta= new tarjetas_leer(padre, true, user, fe, pelicula_select.get_tipo(), funcion_selec.get_franja());
            panel_tarjeta.setVisible(true);
            tarjeta t=panel_tarjeta.get_tarjeta();
            
            if (t.get_id()!= null && t.get_estado()) {
                venta(gafas,null,0,t);
            }
            else if(!t.get_estado()){
                JOptionPane.showMessageDialog(null,"TARJETA INACTIVA","error",JOptionPane.WARNING_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null,"tarjeta no existe","error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar algina silla ","error",JOptionPane.ERROR_MESSAGE);
        }
    }
        
    public void apartar() throws ParseException{
        tiempo=0;
        String sql;
        String nombre="";
        int cont=0;
        String compra="";

        
        if(cantidad>0){
            nombre = JOptionPane.showInputDialog(padre,"INGRESE EL NOMBRE DE QUIEN APARTA","apartar",JOptionPane.QUESTION_MESSAGE);
            
            if(nombre != null){
                if(nombre.length()>30){
                    JOptionPane.showMessageDialog(null,"EL NOMBRE ES MUY LARGO INTENTARLO DE NUEVO");
                }
                else {
                    sql="INSERT INTO apartadas  (silla,nombre,fecha_funcion,funcion_id) VALUES ";
                    for(int i=0;i<estado.length;i++){               
                        if (estado[i]==1){              
                            if(cont!=0){
                                sql=sql+" , ";
                            }
                            compra=compra+"  "+nombre_sillas[i];
                            sql=sql+"("+(i+1)+",'"+nombre+"','"+fecha+"',"+funcion_selec.get_id()+")";
                            cont=cont+1;
                        }  
                    }

                    try{ 
                        PreparedStatement pst= con.prepareStatement(sql);
                        int n=pst.executeUpdate();
                        if (n>0) {
                            JOptionPane.showMessageDialog(null,"apartadas exitosamente");
                            cargar();
                        }
                    } catch (SQLException ex) {
                        if(ex.getErrorCode() == 1062){
                            JOptionPane.showMessageDialog(null,"alguna voleta ya fue apartada actualice estado de la sala:  ","error",JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                        JOptionPane.showMessageDialog(null,"error 35:  "+ex.getMessage());
                        }
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"debe digitar un nombre","error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar alguna silla ","error",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void cargar (){
        tiempo=0;
        for(int i=0;i<estado.length;i++){
            estado[i]=0;
            estado_base[i]=0;
            apartadas[i]="";
            sillas[i].setBackground(color_estado[0]);
            id_venta[i]="";
        }
        try {
            ///cargar venta
            Statement st = con.createStatement();
            ResultSet result =st.executeQuery("SELECT v.*, c.color FROM ventas AS v  "
                                            + "LEFT JOIN convenios AS c ON v.conveion_id=c.id "
                                            + "WHERE funcion_id='"+funcion_selec.get_id()+"' and fecha_funcion='"+fecha+"' ");
            
            while(result.next()){
                id_venta[result.getInt(2)-1]=result.getString(1);
                estado[result.getInt(2)-1]=2;
                estado_base[result.getInt(2)-1]=2;
                if(result.getString(6)!=null){
                    String[] color = result.getString(11).split(",");
                    int r=Integer.parseInt(color[0]);
                    int g=Integer.parseInt(color[1]);
                    int b=Integer.parseInt(color[2]);
                
                    sillas[result.getInt(2)-1].setBackground(new Color(r,g,b));

                }else{
                sillas[result.getInt(2)-1].setBackground(color_estado[2]);}
            }  
            //cargat apartadas
            result =st.executeQuery("SELECT * FROM apartadas WHERE funcion_id='"+funcion_selec.get_id()+"' and fecha_funcion='"+fecha+"'");
            while(result.next()){
                if(estado[result.getInt(2)-1] != 2){
                    apartadas[result.getInt(2)-1]=result.getString(3);
                    estado[result.getInt(2)-1]=3;
                    estado_base[result.getInt(2)-1]=3;
                    sillas[result.getInt(2)-1].setBackground(color_estado[3]);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 36:  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cantidad=0;
    }
        
    ////EVENTO DEL LOS BOTONES
    @Override    
    public void actionPerformed(ActionEvent e) {
        tiempo=0;
        for(int i=0;i<tam;i++){
            if(e.getSource()==sillas[i]){
                if(user.get_privilegios()==1 && estado[i]==2){
                    int response = JOptionPane.showConfirmDialog(null, "DESEA ELIMINAR SILLA?", "Confirmacion",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                            eliminar(id_venta[i]);
                    }
                }else if(estado[i]==0 || estado[i]==3){
                    sillas[i].setBackground(color_estado[1]);
                    estado[i]=1;
                    cantidad = cantidad+1;
                }else if(estado[i]==1){
                    estado[i]=estado_base[i];
                    sillas[i].setBackground(color_estado[estado[i]]);
                    cantidad = cantidad-1;
                }
            }
        }
    }
    
    public void calcular_precio(){
        if(funcion_selec.get_fecha()== null){
            try {
                Statement st = con.createStatement();
                ResultSet result =st.executeQuery("SELECT valor2d, valor3d, pref_valor2d, pref_valor3d, dosxuno FROM `precios` WHERE dia='"+fe.get(Calendar.DAY_OF_WEEK)+"' AND franja="+(funcion_selec.get_franja())+" ");

                if(result.next()){
                    if(pelicula_select.get_tipo().equals("2D")){
                       precio_general=result.getInt(1);
                       precio_Preferencial=result.getInt(3);
                    }else{
                       precio_general=result.getInt(2);
                       precio_Preferencial=result.getInt(4);
                    }   
                    
                    if(result.getBoolean(5)){
                       precio_general=precio_general/2;
                       precio_Preferencial=precio_Preferencial/2;
                    }
                }               
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error 37:  "+ex);
                Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            precio_general=precio_Preferencial= funcion_selec.get_precio();
        } 
    }
   
    public void selec_apartadas (String nombre){
        tiempo=0;
        for(int i=0;i<apartadas.length;i++){
            if(apartadas[i].equals(nombre) && estado[i]!=1){
                sillas[i].setBackground(color_estado[1]);
                estado[i]=1;
                cantidad = cantidad+1;
            }
        }
        
    }
    
    public void eliminar(String id){
        try{
            String sql;
            sql="DELETE FROM ventas WHERE id ='"+id+"'";
            PreparedStatement pst= con.prepareStatement(sql);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"se elimino exitosamente");
            cargar();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 39:  "+ex); 
        }
    }
    
    
    public boolean validar_consecutivo (int c){
        int max_consecutivo=0;
        int max_resolucion=0;
        int cantidad = c;
        Boolean puede= true;
        
        try{
            Statement st = con.createStatement();
            ResultSet result =st.executeQuery("SELECT MAX(consecutivo) from ventas");
            result.next();
            max_consecutivo=result.getInt(1);
            
            result =st.executeQuery("SELECT MAX(fin) from resoluciones");
            result.next();
            max_resolucion=result.getInt(1);
            
            if((max_consecutivo+cantidad)>max_resolucion){
                puede=false;
            }
 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 47:  "+ex); 
        }
        return puede;
    }
    
    public void gurdar_gafas(int cantidad, int valor){
        ImageIcon icon = new ImageIcon("src/imagenes/gafas.png");
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO gafas (cantidad, valor,usuario_id) VALUES ( '"+cantidad+"', '"+(valor*cantidad)+"', '"+user.get_id()+"')");
            pst.executeUpdate();

            imprimir_factura_gafas imprimir = new imprimir_factura_gafas(cantidad,(valor*cantidad));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 43  "+ex);
        } 
    }
    
    public void close (){
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 41:  "+ex.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel20 = new javax.swing.JLabel();
        I16 = new javax.swing.JButton();
        I15 = new javax.swing.JButton();
        I14 = new javax.swing.JButton();
        I13 = new javax.swing.JButton();
        I12 = new javax.swing.JButton();
        I11 = new javax.swing.JButton();
        I10 = new javax.swing.JButton();
        I9 = new javax.swing.JButton();
        I8 = new javax.swing.JButton();
        I7 = new javax.swing.JButton();
        I6 = new javax.swing.JButton();
        I5 = new javax.swing.JButton();
        I4 = new javax.swing.JButton();
        I3 = new javax.swing.JButton();
        I2 = new javax.swing.JButton();
        I1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        H12 = new javax.swing.JButton();
        H11 = new javax.swing.JButton();
        H10 = new javax.swing.JButton();
        H9 = new javax.swing.JButton();
        H8 = new javax.swing.JButton();
        H7 = new javax.swing.JButton();
        H6 = new javax.swing.JButton();
        H5 = new javax.swing.JButton();
        H4 = new javax.swing.JButton();
        H3 = new javax.swing.JButton();
        H2 = new javax.swing.JButton();
        H1 = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        G12 = new javax.swing.JButton();
        G11 = new javax.swing.JButton();
        G10 = new javax.swing.JButton();
        G9 = new javax.swing.JButton();
        G8 = new javax.swing.JButton();
        G7 = new javax.swing.JButton();
        G6 = new javax.swing.JButton();
        G5 = new javax.swing.JButton();
        G4 = new javax.swing.JButton();
        G3 = new javax.swing.JButton();
        G2 = new javax.swing.JButton();
        G1 = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        F13 = new javax.swing.JButton();
        F12 = new javax.swing.JButton();
        F11 = new javax.swing.JButton();
        F10 = new javax.swing.JButton();
        F9 = new javax.swing.JButton();
        F8 = new javax.swing.JButton();
        F7 = new javax.swing.JButton();
        F6 = new javax.swing.JButton();
        F5 = new javax.swing.JButton();
        F4 = new javax.swing.JButton();
        F3 = new javax.swing.JButton();
        F2 = new javax.swing.JButton();
        F1 = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        E13 = new javax.swing.JButton();
        E12 = new javax.swing.JButton();
        E11 = new javax.swing.JButton();
        E10 = new javax.swing.JButton();
        E9 = new javax.swing.JButton();
        E8 = new javax.swing.JButton();
        E7 = new javax.swing.JButton();
        E6 = new javax.swing.JButton();
        E5 = new javax.swing.JButton();
        E4 = new javax.swing.JButton();
        E3 = new javax.swing.JButton();
        E2 = new javax.swing.JButton();
        E1 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        D13 = new javax.swing.JButton();
        D12 = new javax.swing.JButton();
        D11 = new javax.swing.JButton();
        D10 = new javax.swing.JButton();
        D9 = new javax.swing.JButton();
        D8 = new javax.swing.JButton();
        D7 = new javax.swing.JButton();
        D6 = new javax.swing.JButton();
        D5 = new javax.swing.JButton();
        D4 = new javax.swing.JButton();
        D3 = new javax.swing.JButton();
        D2 = new javax.swing.JButton();
        D1 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        C14 = new javax.swing.JButton();
        C13 = new javax.swing.JButton();
        C12 = new javax.swing.JButton();
        C11 = new javax.swing.JButton();
        C10 = new javax.swing.JButton();
        C9 = new javax.swing.JButton();
        C8 = new javax.swing.JButton();
        C7 = new javax.swing.JButton();
        C6 = new javax.swing.JButton();
        C5 = new javax.swing.JButton();
        C4 = new javax.swing.JButton();
        C3 = new javax.swing.JButton();
        C2 = new javax.swing.JButton();
        C1 = new javax.swing.JButton();
        jLabel60 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        B14 = new javax.swing.JButton();
        B13 = new javax.swing.JButton();
        B12 = new javax.swing.JButton();
        B11 = new javax.swing.JButton();
        B10 = new javax.swing.JButton();
        B9 = new javax.swing.JButton();
        B8 = new javax.swing.JButton();
        B7 = new javax.swing.JButton();
        B6 = new javax.swing.JButton();
        B5 = new javax.swing.JButton();
        B4 = new javax.swing.JButton();
        B3 = new javax.swing.JButton();
        B2 = new javax.swing.JButton();
        B1 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        A14 = new javax.swing.JButton();
        A13 = new javax.swing.JButton();
        A12 = new javax.swing.JButton();
        A11 = new javax.swing.JButton();
        A10 = new javax.swing.JButton();
        A9 = new javax.swing.JButton();
        A8 = new javax.swing.JButton();
        A7 = new javax.swing.JButton();
        A6 = new javax.swing.JButton();
        A5 = new javax.swing.JButton();
        A4 = new javax.swing.JButton();
        A3 = new javax.swing.JButton();
        A2 = new javax.swing.JButton();
        A1 = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));
        setMaximumSize(new java.awt.Dimension(1180, 590));
        setPreferredSize(new java.awt.Dimension(1180, 590));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("I");
        jLabel20.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel20.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel20.setName(""); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel20);

        I16.setBackground(new java.awt.Color(0, 255, 0));
        I16.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I16.setText("16");
        I16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I16.setDisabledIcon(null);
        I16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I16.setMaximumSize(new java.awt.Dimension(52, 45));
        I16.setMinimumSize(new java.awt.Dimension(52, 45));
        I16.setName(""); // NOI18N
        I16.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I16);

        I15.setBackground(new java.awt.Color(0, 255, 0));
        I15.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I15.setText("15");
        I15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I15.setDisabledIcon(null);
        I15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I15.setMaximumSize(new java.awt.Dimension(52, 45));
        I15.setMinimumSize(new java.awt.Dimension(52, 45));
        I15.setName(""); // NOI18N
        I15.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I15);

        I14.setBackground(new java.awt.Color(0, 255, 0));
        I14.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I14.setText("14");
        I14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I14.setDisabledIcon(null);
        I14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I14.setMaximumSize(new java.awt.Dimension(52, 45));
        I14.setMinimumSize(new java.awt.Dimension(52, 45));
        I14.setName(""); // NOI18N
        I14.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I14);

        I13.setBackground(new java.awt.Color(0, 255, 0));
        I13.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I13.setText("13");
        I13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I13.setDisabledIcon(null);
        I13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I13.setMaximumSize(new java.awt.Dimension(52, 45));
        I13.setMinimumSize(new java.awt.Dimension(52, 45));
        I13.setName(""); // NOI18N
        I13.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I13);

        I12.setBackground(new java.awt.Color(0, 255, 0));
        I12.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I12.setText("12");
        I12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I12.setDisabledIcon(null);
        I12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I12.setMaximumSize(new java.awt.Dimension(52, 45));
        I12.setMinimumSize(new java.awt.Dimension(52, 45));
        I12.setName(""); // NOI18N
        I12.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I12);

        I11.setBackground(new java.awt.Color(0, 255, 0));
        I11.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I11.setText("11");
        I11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I11.setDisabledIcon(null);
        I11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I11.setMaximumSize(new java.awt.Dimension(52, 45));
        I11.setMinimumSize(new java.awt.Dimension(52, 45));
        I11.setName(""); // NOI18N
        I11.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I11);

        I10.setBackground(new java.awt.Color(0, 255, 0));
        I10.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I10.setText("10");
        I10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I10.setDisabledIcon(null);
        I10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I10.setMaximumSize(new java.awt.Dimension(52, 45));
        I10.setMinimumSize(new java.awt.Dimension(52, 45));
        I10.setName(""); // NOI18N
        I10.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I10);

        I9.setBackground(new java.awt.Color(0, 255, 0));
        I9.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I9.setText("9");
        I9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I9.setDisabledIcon(null);
        I9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I9.setMaximumSize(new java.awt.Dimension(52, 45));
        I9.setMinimumSize(new java.awt.Dimension(52, 45));
        I9.setName(""); // NOI18N
        I9.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I9);

        I8.setBackground(new java.awt.Color(0, 255, 0));
        I8.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I8.setText("8");
        I8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I8.setDisabledIcon(null);
        I8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I8.setMaximumSize(new java.awt.Dimension(52, 45));
        I8.setMinimumSize(new java.awt.Dimension(52, 45));
        I8.setName(""); // NOI18N
        I8.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I8);

        I7.setBackground(new java.awt.Color(0, 255, 0));
        I7.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I7.setText("7");
        I7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I7.setDisabledIcon(null);
        I7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I7.setMaximumSize(new java.awt.Dimension(52, 45));
        I7.setMinimumSize(new java.awt.Dimension(52, 45));
        I7.setName(""); // NOI18N
        I7.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I7);

        I6.setBackground(new java.awt.Color(0, 255, 0));
        I6.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I6.setText("6");
        I6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I6.setDisabledIcon(null);
        I6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I6.setMaximumSize(new java.awt.Dimension(52, 45));
        I6.setMinimumSize(new java.awt.Dimension(52, 45));
        I6.setName(""); // NOI18N
        I6.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I6);

        I5.setBackground(new java.awt.Color(0, 255, 0));
        I5.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I5.setText("5");
        I5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I5.setDisabledIcon(null);
        I5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I5.setMaximumSize(new java.awt.Dimension(52, 45));
        I5.setMinimumSize(new java.awt.Dimension(52, 45));
        I5.setName(""); // NOI18N
        I5.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I5);

        I4.setBackground(new java.awt.Color(0, 255, 0));
        I4.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I4.setText("4");
        I4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I4.setDisabledIcon(null);
        I4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I4.setMaximumSize(new java.awt.Dimension(52, 45));
        I4.setMinimumSize(new java.awt.Dimension(52, 45));
        I4.setName(""); // NOI18N
        I4.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I4);

        I3.setBackground(new java.awt.Color(0, 255, 0));
        I3.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I3.setText("3");
        I3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I3.setDisabledIcon(null);
        I3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I3.setMaximumSize(new java.awt.Dimension(52, 45));
        I3.setMinimumSize(new java.awt.Dimension(52, 45));
        I3.setName(""); // NOI18N
        I3.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I3);

        I2.setBackground(new java.awt.Color(0, 255, 0));
        I2.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I2.setText("2");
        I2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I2.setDisabledIcon(null);
        I2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I2.setMaximumSize(new java.awt.Dimension(52, 45));
        I2.setMinimumSize(new java.awt.Dimension(52, 45));
        I2.setName(""); // NOI18N
        I2.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I2);

        I1.setBackground(new java.awt.Color(0, 255, 0));
        I1.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        I1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        I1.setText("1");
        I1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        I1.setDisabledIcon(null);
        I1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        I1.setMaximumSize(new java.awt.Dimension(52, 45));
        I1.setMinimumSize(new java.awt.Dimension(52, 45));
        I1.setName(""); // NOI18N
        I1.setPreferredSize(new java.awt.Dimension(52, 45));
        add(I1);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("I");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel10.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel10.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel10.setName(""); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel10);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("H");
        jLabel19.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel19.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel19.setName(""); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel19);

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel61.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel61.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel61.setName(""); // NOI18N
        jLabel61.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel61);

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel36.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel36.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel36.setName(""); // NOI18N
        jLabel36.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel36);

        H12.setBackground(new java.awt.Color(0, 255, 0));
        H12.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        H12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        H12.setText("12");
        H12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        H12.setDisabledIcon(null);
        H12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        H12.setMaximumSize(new java.awt.Dimension(52, 45));
        H12.setMinimumSize(new java.awt.Dimension(52, 45));
        H12.setName(""); // NOI18N
        H12.setPreferredSize(new java.awt.Dimension(52, 45));
        add(H12);

        H11.setBackground(new java.awt.Color(0, 255, 0));
        H11.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        H11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        H11.setText("11");
        H11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        H11.setDisabledIcon(null);
        H11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        H11.setMaximumSize(new java.awt.Dimension(52, 45));
        H11.setMinimumSize(new java.awt.Dimension(52, 45));
        H11.setName(""); // NOI18N
        H11.setPreferredSize(new java.awt.Dimension(52, 45));
        add(H11);

        H10.setBackground(new java.awt.Color(0, 255, 0));
        H10.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        H10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        H10.setText("10");
        H10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        H10.setDisabledIcon(null);
        H10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        H10.setMaximumSize(new java.awt.Dimension(52, 45));
        H10.setMinimumSize(new java.awt.Dimension(52, 45));
        H10.setName(""); // NOI18N
        H10.setPreferredSize(new java.awt.Dimension(52, 45));
        add(H10);

        H9.setBackground(new java.awt.Color(0, 255, 0));
        H9.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        H9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        H9.setText("9");
        H9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        H9.setDisabledIcon(null);
        H9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        H9.setMaximumSize(new java.awt.Dimension(52, 45));
        H9.setMinimumSize(new java.awt.Dimension(52, 45));
        H9.setName(""); // NOI18N
        H9.setPreferredSize(new java.awt.Dimension(52, 45));
        add(H9);

        H8.setBackground(new java.awt.Color(0, 255, 0));
        H8.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        H8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        H8.setText("8");
        H8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        H8.setDisabledIcon(null);
        H8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        H8.setMaximumSize(new java.awt.Dimension(52, 45));
        H8.setMinimumSize(new java.awt.Dimension(52, 45));
        H8.setName(""); // NOI18N
        H8.setPreferredSize(new java.awt.Dimension(52, 45));
        add(H8);

        H7.setBackground(new java.awt.Color(0, 255, 0));
        H7.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        H7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        H7.setText("7");
        H7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        H7.setDisabledIcon(null);
        H7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        H7.setMaximumSize(new java.awt.Dimension(52, 45));
        H7.setMinimumSize(new java.awt.Dimension(52, 45));
        H7.setName(""); // NOI18N
        H7.setPreferredSize(new java.awt.Dimension(52, 45));
        add(H7);

        H6.setBackground(new java.awt.Color(0, 255, 0));
        H6.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        H6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        H6.setText("6");
        H6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        H6.setDisabledIcon(null);
        H6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        H6.setMaximumSize(new java.awt.Dimension(52, 45));
        H6.setMinimumSize(new java.awt.Dimension(52, 45));
        H6.setName(""); // NOI18N
        H6.setPreferredSize(new java.awt.Dimension(52, 45));
        add(H6);

        H5.setBackground(new java.awt.Color(0, 255, 0));
        H5.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        H5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        H5.setText("5");
        H5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        H5.setDisabledIcon(null);
        H5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        H5.setMaximumSize(new java.awt.Dimension(52, 45));
        H5.setMinimumSize(new java.awt.Dimension(52, 45));
        H5.setName(""); // NOI18N
        H5.setPreferredSize(new java.awt.Dimension(52, 45));
        add(H5);

        H4.setBackground(new java.awt.Color(0, 255, 0));
        H4.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        H4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        H4.setText("4");
        H4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        H4.setDisabledIcon(null);
        H4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        H4.setMaximumSize(new java.awt.Dimension(52, 45));
        H4.setMinimumSize(new java.awt.Dimension(52, 45));
        H4.setName(""); // NOI18N
        H4.setPreferredSize(new java.awt.Dimension(52, 45));
        add(H4);

        H3.setBackground(new java.awt.Color(0, 255, 0));
        H3.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        H3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        H3.setText("3");
        H3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        H3.setDisabledIcon(null);
        H3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        H3.setMaximumSize(new java.awt.Dimension(52, 45));
        H3.setMinimumSize(new java.awt.Dimension(52, 45));
        H3.setName(""); // NOI18N
        H3.setPreferredSize(new java.awt.Dimension(52, 45));
        add(H3);

        H2.setBackground(new java.awt.Color(0, 255, 0));
        H2.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        H2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        H2.setText("2");
        H2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        H2.setDisabledIcon(null);
        H2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        H2.setMaximumSize(new java.awt.Dimension(52, 45));
        H2.setMinimumSize(new java.awt.Dimension(52, 45));
        H2.setName(""); // NOI18N
        H2.setPreferredSize(new java.awt.Dimension(52, 45));
        add(H2);

        H1.setBackground(new java.awt.Color(0, 255, 0));
        H1.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        H1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        H1.setText("1");
        H1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        H1.setDisabledIcon(null);
        H1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        H1.setMaximumSize(new java.awt.Dimension(52, 45));
        H1.setMinimumSize(new java.awt.Dimension(52, 45));
        H1.setName(""); // NOI18N
        H1.setPreferredSize(new java.awt.Dimension(52, 45));
        add(H1);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel48.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel48.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel48.setName(""); // NOI18N
        jLabel48.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel48);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel37.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel37.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel37.setName(""); // NOI18N
        jLabel37.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel37);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("H");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel9.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel9.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel9.setName(""); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel9);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("G");
        jLabel18.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel18.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel18.setName(""); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel18);

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel35.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel35.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel35.setName(""); // NOI18N
        jLabel35.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel35);

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel54.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel54.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel54.setName(""); // NOI18N
        jLabel54.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel54);

        G12.setBackground(new java.awt.Color(0, 255, 0));
        G12.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        G12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        G12.setText("12");
        G12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        G12.setDisabledIcon(null);
        G12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        G12.setMaximumSize(new java.awt.Dimension(52, 45));
        G12.setMinimumSize(new java.awt.Dimension(52, 45));
        G12.setName(""); // NOI18N
        G12.setPreferredSize(new java.awt.Dimension(52, 45));
        add(G12);

        G11.setBackground(new java.awt.Color(0, 255, 0));
        G11.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        G11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        G11.setText("11");
        G11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        G11.setDisabledIcon(null);
        G11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        G11.setMaximumSize(new java.awt.Dimension(52, 45));
        G11.setMinimumSize(new java.awt.Dimension(52, 45));
        G11.setName(""); // NOI18N
        G11.setPreferredSize(new java.awt.Dimension(52, 45));
        add(G11);

        G10.setBackground(new java.awt.Color(0, 255, 0));
        G10.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        G10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        G10.setText("10");
        G10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        G10.setDisabledIcon(null);
        G10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        G10.setMaximumSize(new java.awt.Dimension(52, 45));
        G10.setMinimumSize(new java.awt.Dimension(52, 45));
        G10.setName(""); // NOI18N
        G10.setPreferredSize(new java.awt.Dimension(52, 45));
        add(G10);

        G9.setBackground(new java.awt.Color(0, 255, 0));
        G9.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        G9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        G9.setText("9");
        G9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        G9.setDisabledIcon(null);
        G9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        G9.setMaximumSize(new java.awt.Dimension(52, 45));
        G9.setMinimumSize(new java.awt.Dimension(52, 45));
        G9.setName(""); // NOI18N
        G9.setPreferredSize(new java.awt.Dimension(52, 45));
        add(G9);

        G8.setBackground(new java.awt.Color(0, 255, 0));
        G8.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        G8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        G8.setText("8");
        G8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        G8.setDisabledIcon(null);
        G8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        G8.setMaximumSize(new java.awt.Dimension(52, 45));
        G8.setMinimumSize(new java.awt.Dimension(52, 45));
        G8.setName(""); // NOI18N
        G8.setPreferredSize(new java.awt.Dimension(52, 45));
        add(G8);

        G7.setBackground(new java.awt.Color(0, 255, 0));
        G7.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        G7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        G7.setText("7");
        G7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        G7.setDisabledIcon(null);
        G7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        G7.setMaximumSize(new java.awt.Dimension(52, 45));
        G7.setMinimumSize(new java.awt.Dimension(52, 45));
        G7.setName(""); // NOI18N
        G7.setPreferredSize(new java.awt.Dimension(52, 45));
        add(G7);

        G6.setBackground(new java.awt.Color(0, 255, 0));
        G6.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        G6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        G6.setText("6");
        G6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        G6.setDisabledIcon(null);
        G6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        G6.setMaximumSize(new java.awt.Dimension(52, 45));
        G6.setMinimumSize(new java.awt.Dimension(52, 45));
        G6.setName(""); // NOI18N
        G6.setPreferredSize(new java.awt.Dimension(52, 45));
        add(G6);

        G5.setBackground(new java.awt.Color(0, 255, 0));
        G5.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        G5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        G5.setText("5");
        G5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        G5.setDisabledIcon(null);
        G5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        G5.setMaximumSize(new java.awt.Dimension(52, 45));
        G5.setMinimumSize(new java.awt.Dimension(52, 45));
        G5.setName(""); // NOI18N
        G5.setPreferredSize(new java.awt.Dimension(52, 45));
        add(G5);

        G4.setBackground(new java.awt.Color(0, 255, 0));
        G4.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        G4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        G4.setText("4");
        G4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        G4.setDisabledIcon(null);
        G4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        G4.setMaximumSize(new java.awt.Dimension(52, 45));
        G4.setMinimumSize(new java.awt.Dimension(52, 45));
        G4.setName(""); // NOI18N
        G4.setPreferredSize(new java.awt.Dimension(52, 45));
        add(G4);

        G3.setBackground(new java.awt.Color(0, 255, 0));
        G3.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        G3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        G3.setText("3");
        G3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        G3.setDisabledIcon(null);
        G3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        G3.setMaximumSize(new java.awt.Dimension(52, 45));
        G3.setMinimumSize(new java.awt.Dimension(52, 45));
        G3.setName(""); // NOI18N
        G3.setPreferredSize(new java.awt.Dimension(52, 45));
        add(G3);

        G2.setBackground(new java.awt.Color(0, 255, 0));
        G2.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        G2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        G2.setText("2");
        G2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        G2.setDisabledIcon(null);
        G2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        G2.setMaximumSize(new java.awt.Dimension(52, 45));
        G2.setMinimumSize(new java.awt.Dimension(52, 45));
        G2.setName(""); // NOI18N
        G2.setPreferredSize(new java.awt.Dimension(52, 45));
        add(G2);

        G1.setBackground(new java.awt.Color(0, 255, 0));
        G1.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        G1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/preferencial.png"))); // NOI18N
        G1.setText("1");
        G1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        G1.setDisabledIcon(null);
        G1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        G1.setMaximumSize(new java.awt.Dimension(52, 45));
        G1.setMinimumSize(new java.awt.Dimension(52, 45));
        G1.setName(""); // NOI18N
        G1.setPreferredSize(new java.awt.Dimension(52, 45));
        add(G1);

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel59.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel59.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel59.setName(""); // NOI18N
        jLabel59.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel59);

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel53.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel53.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel53.setName(""); // NOI18N
        jLabel53.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel53);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("G");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel8.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel8.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel8.setName(""); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel8);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("F");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel7.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel7.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel7.setName(""); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel7);

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel42.setMaximumSize(new java.awt.Dimension(36, 45));
        jLabel42.setMinimumSize(new java.awt.Dimension(36, 45));
        jLabel42.setName(""); // NOI18N
        jLabel42.setPreferredSize(new java.awt.Dimension(36, 45));
        add(jLabel42);

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel52.setMaximumSize(new java.awt.Dimension(36, 45));
        jLabel52.setMinimumSize(new java.awt.Dimension(36, 45));
        jLabel52.setName(""); // NOI18N
        jLabel52.setPreferredSize(new java.awt.Dimension(36, 45));
        add(jLabel52);

        F13.setBackground(new java.awt.Color(0, 255, 0));
        F13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        F13.setText("13");
        F13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F13.setMaximumSize(new java.awt.Dimension(52, 45));
        F13.setMinimumSize(new java.awt.Dimension(52, 45));
        F13.setName(""); // NOI18N
        F13.setPreferredSize(new java.awt.Dimension(52, 45));
        add(F13);

        F12.setBackground(new java.awt.Color(0, 255, 0));
        F12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        F12.setText("12");
        F12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F12.setMaximumSize(new java.awt.Dimension(52, 45));
        F12.setMinimumSize(new java.awt.Dimension(52, 45));
        F12.setName(""); // NOI18N
        F12.setPreferredSize(new java.awt.Dimension(52, 45));
        add(F12);

        F11.setBackground(new java.awt.Color(0, 255, 0));
        F11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        F11.setText("11");
        F11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F11.setMaximumSize(new java.awt.Dimension(52, 45));
        F11.setMinimumSize(new java.awt.Dimension(52, 45));
        F11.setName(""); // NOI18N
        F11.setPreferredSize(new java.awt.Dimension(52, 45));
        add(F11);

        F10.setBackground(new java.awt.Color(0, 255, 0));
        F10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        F10.setText("10");
        F10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F10.setMaximumSize(new java.awt.Dimension(52, 45));
        F10.setMinimumSize(new java.awt.Dimension(52, 45));
        F10.setName(""); // NOI18N
        F10.setPreferredSize(new java.awt.Dimension(52, 45));
        add(F10);

        F9.setBackground(new java.awt.Color(0, 255, 0));
        F9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        F9.setText("9");
        F9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F9.setMaximumSize(new java.awt.Dimension(52, 45));
        F9.setMinimumSize(new java.awt.Dimension(52, 45));
        F9.setName(""); // NOI18N
        F9.setPreferredSize(new java.awt.Dimension(52, 45));
        add(F9);

        F8.setBackground(new java.awt.Color(0, 255, 0));
        F8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        F8.setText("8");
        F8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F8.setMaximumSize(new java.awt.Dimension(52, 45));
        F8.setMinimumSize(new java.awt.Dimension(52, 45));
        F8.setName(""); // NOI18N
        F8.setPreferredSize(new java.awt.Dimension(52, 45));
        add(F8);

        F7.setBackground(new java.awt.Color(0, 255, 0));
        F7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        F7.setText("7");
        F7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F7.setMaximumSize(new java.awt.Dimension(52, 45));
        F7.setMinimumSize(new java.awt.Dimension(52, 45));
        F7.setName(""); // NOI18N
        F7.setPreferredSize(new java.awt.Dimension(52, 45));
        add(F7);

        F6.setBackground(new java.awt.Color(0, 255, 0));
        F6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        F6.setText("6");
        F6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F6.setMaximumSize(new java.awt.Dimension(52, 45));
        F6.setMinimumSize(new java.awt.Dimension(52, 45));
        F6.setName(""); // NOI18N
        F6.setPreferredSize(new java.awt.Dimension(52, 45));
        add(F6);

        F5.setBackground(new java.awt.Color(0, 255, 0));
        F5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        F5.setText("5");
        F5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F5.setMaximumSize(new java.awt.Dimension(52, 45));
        F5.setMinimumSize(new java.awt.Dimension(52, 45));
        F5.setName(""); // NOI18N
        F5.setPreferredSize(new java.awt.Dimension(52, 45));
        add(F5);

        F4.setBackground(new java.awt.Color(0, 255, 0));
        F4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        F4.setText("4");
        F4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F4.setMaximumSize(new java.awt.Dimension(52, 45));
        F4.setMinimumSize(new java.awt.Dimension(52, 45));
        F4.setName(""); // NOI18N
        F4.setPreferredSize(new java.awt.Dimension(52, 45));
        add(F4);

        F3.setBackground(new java.awt.Color(0, 255, 0));
        F3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        F3.setText("3");
        F3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F3.setMaximumSize(new java.awt.Dimension(52, 45));
        F3.setMinimumSize(new java.awt.Dimension(52, 45));
        F3.setName(""); // NOI18N
        F3.setPreferredSize(new java.awt.Dimension(52, 45));
        add(F3);

        F2.setBackground(new java.awt.Color(0, 255, 0));
        F2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        F2.setText("2");
        F2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F2.setMaximumSize(new java.awt.Dimension(52, 45));
        F2.setMinimumSize(new java.awt.Dimension(52, 45));
        F2.setName(""); // NOI18N
        F2.setPreferredSize(new java.awt.Dimension(52, 45));
        add(F2);

        F1.setBackground(new java.awt.Color(0, 255, 0));
        F1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        F1.setText("1");
        F1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F1.setMaximumSize(new java.awt.Dimension(52, 45));
        F1.setMinimumSize(new java.awt.Dimension(52, 45));
        F1.setName(""); // NOI18N
        F1.setPreferredSize(new java.awt.Dimension(52, 45));
        add(F1);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel46.setMaximumSize(new java.awt.Dimension(36, 45));
        jLabel46.setMinimumSize(new java.awt.Dimension(36, 45));
        jLabel46.setName(""); // NOI18N
        jLabel46.setPreferredSize(new java.awt.Dimension(36, 45));
        add(jLabel46);

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel55.setMaximumSize(new java.awt.Dimension(36, 45));
        jLabel55.setMinimumSize(new java.awt.Dimension(36, 45));
        jLabel55.setName(""); // NOI18N
        jLabel55.setPreferredSize(new java.awt.Dimension(36, 45));
        add(jLabel55);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("F");
        jLabel17.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel17.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel17.setName(""); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel17);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("E");
        jLabel16.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel16.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel16.setName(""); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel16);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel41.setMaximumSize(new java.awt.Dimension(36, 45));
        jLabel41.setMinimumSize(new java.awt.Dimension(36, 45));
        jLabel41.setName(""); // NOI18N
        jLabel41.setPreferredSize(new java.awt.Dimension(36, 45));
        add(jLabel41);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel32.setMaximumSize(new java.awt.Dimension(36, 45));
        jLabel32.setMinimumSize(new java.awt.Dimension(36, 45));
        jLabel32.setName(""); // NOI18N
        jLabel32.setPreferredSize(new java.awt.Dimension(36, 45));
        add(jLabel32);

        E13.setBackground(new java.awt.Color(0, 255, 0));
        E13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        E13.setText("13");
        E13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        E13.setMaximumSize(new java.awt.Dimension(52, 45));
        E13.setMinimumSize(new java.awt.Dimension(52, 45));
        E13.setName(""); // NOI18N
        E13.setPreferredSize(new java.awt.Dimension(52, 45));
        add(E13);

        E12.setBackground(new java.awt.Color(0, 255, 0));
        E12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        E12.setText("12");
        E12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        E12.setMaximumSize(new java.awt.Dimension(52, 45));
        E12.setMinimumSize(new java.awt.Dimension(52, 45));
        E12.setName(""); // NOI18N
        E12.setPreferredSize(new java.awt.Dimension(52, 45));
        add(E12);

        E11.setBackground(new java.awt.Color(0, 255, 0));
        E11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        E11.setText("11");
        E11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        E11.setMaximumSize(new java.awt.Dimension(52, 45));
        E11.setMinimumSize(new java.awt.Dimension(52, 45));
        E11.setName(""); // NOI18N
        E11.setPreferredSize(new java.awt.Dimension(52, 45));
        add(E11);

        E10.setBackground(new java.awt.Color(0, 255, 0));
        E10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        E10.setText("10");
        E10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        E10.setMaximumSize(new java.awt.Dimension(52, 45));
        E10.setMinimumSize(new java.awt.Dimension(52, 45));
        E10.setName(""); // NOI18N
        E10.setPreferredSize(new java.awt.Dimension(52, 45));
        add(E10);

        E9.setBackground(new java.awt.Color(0, 255, 0));
        E9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        E9.setText("9");
        E9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        E9.setMaximumSize(new java.awt.Dimension(52, 45));
        E9.setMinimumSize(new java.awt.Dimension(52, 45));
        E9.setName(""); // NOI18N
        E9.setPreferredSize(new java.awt.Dimension(52, 45));
        add(E9);

        E8.setBackground(new java.awt.Color(0, 255, 0));
        E8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        E8.setText("8");
        E8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        E8.setMaximumSize(new java.awt.Dimension(52, 45));
        E8.setMinimumSize(new java.awt.Dimension(52, 45));
        E8.setName(""); // NOI18N
        E8.setPreferredSize(new java.awt.Dimension(52, 45));
        add(E8);

        E7.setBackground(new java.awt.Color(0, 255, 0));
        E7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        E7.setText("7");
        E7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        E7.setMaximumSize(new java.awt.Dimension(52, 45));
        E7.setMinimumSize(new java.awt.Dimension(52, 45));
        E7.setName(""); // NOI18N
        E7.setPreferredSize(new java.awt.Dimension(52, 45));
        add(E7);

        E6.setBackground(new java.awt.Color(0, 255, 0));
        E6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        E6.setText("6");
        E6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        E6.setMaximumSize(new java.awt.Dimension(52, 45));
        E6.setMinimumSize(new java.awt.Dimension(52, 45));
        E6.setName(""); // NOI18N
        E6.setPreferredSize(new java.awt.Dimension(52, 45));
        add(E6);

        E5.setBackground(new java.awt.Color(0, 255, 0));
        E5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        E5.setText("5");
        E5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        E5.setMaximumSize(new java.awt.Dimension(52, 45));
        E5.setMinimumSize(new java.awt.Dimension(52, 45));
        E5.setName(""); // NOI18N
        E5.setPreferredSize(new java.awt.Dimension(52, 45));
        add(E5);

        E4.setBackground(new java.awt.Color(0, 255, 0));
        E4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        E4.setText("4");
        E4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        E4.setMaximumSize(new java.awt.Dimension(52, 45));
        E4.setMinimumSize(new java.awt.Dimension(52, 45));
        E4.setName(""); // NOI18N
        E4.setPreferredSize(new java.awt.Dimension(52, 45));
        add(E4);

        E3.setBackground(new java.awt.Color(0, 255, 0));
        E3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        E3.setText("3");
        E3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        E3.setMaximumSize(new java.awt.Dimension(52, 45));
        E3.setMinimumSize(new java.awt.Dimension(52, 45));
        E3.setName(""); // NOI18N
        E3.setPreferredSize(new java.awt.Dimension(52, 45));
        add(E3);

        E2.setBackground(new java.awt.Color(0, 255, 0));
        E2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        E2.setText("2");
        E2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        E2.setMaximumSize(new java.awt.Dimension(52, 45));
        E2.setMinimumSize(new java.awt.Dimension(52, 45));
        E2.setName(""); // NOI18N
        E2.setPreferredSize(new java.awt.Dimension(52, 45));
        add(E2);

        E1.setBackground(new java.awt.Color(0, 255, 0));
        E1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        E1.setText("1");
        E1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        E1.setMaximumSize(new java.awt.Dimension(52, 45));
        E1.setMinimumSize(new java.awt.Dimension(52, 45));
        E1.setName(""); // NOI18N
        E1.setPreferredSize(new java.awt.Dimension(52, 45));
        add(E1);

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel44.setMaximumSize(new java.awt.Dimension(36, 45));
        jLabel44.setMinimumSize(new java.awt.Dimension(36, 45));
        jLabel44.setName(""); // NOI18N
        jLabel44.setPreferredSize(new java.awt.Dimension(36, 45));
        add(jLabel44);

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel43.setMaximumSize(new java.awt.Dimension(36, 45));
        jLabel43.setMinimumSize(new java.awt.Dimension(36, 45));
        jLabel43.setName(""); // NOI18N
        jLabel43.setPreferredSize(new java.awt.Dimension(36, 45));
        add(jLabel43);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("E");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel6.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel6.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel6.setName(""); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel6);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("D");
        jLabel14.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel14.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel14.setName(""); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel14);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel33.setMaximumSize(new java.awt.Dimension(36, 45));
        jLabel33.setMinimumSize(new java.awt.Dimension(36, 45));
        jLabel33.setName(""); // NOI18N
        jLabel33.setPreferredSize(new java.awt.Dimension(36, 45));
        add(jLabel33);

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel56.setMaximumSize(new java.awt.Dimension(36, 45));
        jLabel56.setMinimumSize(new java.awt.Dimension(36, 45));
        jLabel56.setName(""); // NOI18N
        jLabel56.setPreferredSize(new java.awt.Dimension(36, 45));
        add(jLabel56);

        D13.setBackground(new java.awt.Color(0, 255, 0));
        D13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        D13.setText("13");
        D13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D13.setMaximumSize(new java.awt.Dimension(52, 45));
        D13.setMinimumSize(new java.awt.Dimension(52, 45));
        D13.setName(""); // NOI18N
        D13.setPreferredSize(new java.awt.Dimension(52, 45));
        add(D13);

        D12.setBackground(new java.awt.Color(0, 255, 0));
        D12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        D12.setText("12");
        D12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D12.setMaximumSize(new java.awt.Dimension(52, 45));
        D12.setMinimumSize(new java.awt.Dimension(52, 45));
        D12.setName(""); // NOI18N
        D12.setPreferredSize(new java.awt.Dimension(52, 45));
        add(D12);

        D11.setBackground(new java.awt.Color(0, 255, 0));
        D11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        D11.setText("11");
        D11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D11.setMaximumSize(new java.awt.Dimension(52, 45));
        D11.setMinimumSize(new java.awt.Dimension(52, 45));
        D11.setName(""); // NOI18N
        D11.setPreferredSize(new java.awt.Dimension(52, 45));
        add(D11);

        D10.setBackground(new java.awt.Color(0, 255, 0));
        D10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        D10.setText("10");
        D10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D10.setMaximumSize(new java.awt.Dimension(52, 45));
        D10.setMinimumSize(new java.awt.Dimension(52, 45));
        D10.setName(""); // NOI18N
        D10.setPreferredSize(new java.awt.Dimension(52, 45));
        add(D10);

        D9.setBackground(new java.awt.Color(0, 255, 0));
        D9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        D9.setText("9");
        D9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D9.setMaximumSize(new java.awt.Dimension(52, 45));
        D9.setMinimumSize(new java.awt.Dimension(52, 45));
        D9.setName(""); // NOI18N
        D9.setPreferredSize(new java.awt.Dimension(52, 45));
        add(D9);

        D8.setBackground(new java.awt.Color(0, 255, 0));
        D8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        D8.setText("8");
        D8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D8.setMaximumSize(new java.awt.Dimension(52, 45));
        D8.setMinimumSize(new java.awt.Dimension(52, 45));
        D8.setName(""); // NOI18N
        D8.setPreferredSize(new java.awt.Dimension(52, 45));
        add(D8);

        D7.setBackground(new java.awt.Color(0, 255, 0));
        D7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        D7.setText("7");
        D7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D7.setMaximumSize(new java.awt.Dimension(52, 45));
        D7.setMinimumSize(new java.awt.Dimension(52, 45));
        D7.setName(""); // NOI18N
        D7.setPreferredSize(new java.awt.Dimension(52, 45));
        add(D7);

        D6.setBackground(new java.awt.Color(0, 255, 0));
        D6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        D6.setText("6");
        D6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D6.setMaximumSize(new java.awt.Dimension(52, 45));
        D6.setMinimumSize(new java.awt.Dimension(52, 45));
        D6.setName(""); // NOI18N
        D6.setPreferredSize(new java.awt.Dimension(52, 45));
        add(D6);

        D5.setBackground(new java.awt.Color(0, 255, 0));
        D5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        D5.setText("5");
        D5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D5.setMaximumSize(new java.awt.Dimension(52, 45));
        D5.setMinimumSize(new java.awt.Dimension(52, 45));
        D5.setName(""); // NOI18N
        D5.setPreferredSize(new java.awt.Dimension(52, 45));
        add(D5);

        D4.setBackground(new java.awt.Color(0, 255, 0));
        D4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        D4.setText("4");
        D4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D4.setMaximumSize(new java.awt.Dimension(52, 45));
        D4.setMinimumSize(new java.awt.Dimension(52, 45));
        D4.setName(""); // NOI18N
        D4.setPreferredSize(new java.awt.Dimension(52, 45));
        add(D4);

        D3.setBackground(new java.awt.Color(0, 255, 0));
        D3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        D3.setText("3");
        D3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D3.setMaximumSize(new java.awt.Dimension(52, 45));
        D3.setMinimumSize(new java.awt.Dimension(52, 45));
        D3.setName(""); // NOI18N
        D3.setPreferredSize(new java.awt.Dimension(52, 45));
        add(D3);

        D2.setBackground(new java.awt.Color(0, 255, 0));
        D2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        D2.setText("2");
        D2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D2.setMaximumSize(new java.awt.Dimension(52, 45));
        D2.setMinimumSize(new java.awt.Dimension(52, 45));
        D2.setName(""); // NOI18N
        D2.setPreferredSize(new java.awt.Dimension(52, 45));
        add(D2);

        D1.setBackground(new java.awt.Color(0, 255, 0));
        D1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        D1.setText("1");
        D1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D1.setMaximumSize(new java.awt.Dimension(52, 45));
        D1.setMinimumSize(new java.awt.Dimension(52, 45));
        D1.setName(""); // NOI18N
        D1.setPreferredSize(new java.awt.Dimension(52, 45));
        add(D1);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel45.setMaximumSize(new java.awt.Dimension(36, 45));
        jLabel45.setMinimumSize(new java.awt.Dimension(36, 45));
        jLabel45.setName(""); // NOI18N
        jLabel45.setPreferredSize(new java.awt.Dimension(36, 45));
        add(jLabel45);

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel57.setMaximumSize(new java.awt.Dimension(36, 45));
        jLabel57.setMinimumSize(new java.awt.Dimension(36, 45));
        jLabel57.setName(""); // NOI18N
        jLabel57.setPreferredSize(new java.awt.Dimension(36, 45));
        add(jLabel57);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("D");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel4.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel4.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel4.setName(""); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel4);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("C");
        jLabel13.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel13.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel13.setName(""); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel13);

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel47.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel47.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel47.setName(""); // NOI18N
        jLabel47.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel47);

        C14.setBackground(new java.awt.Color(0, 255, 0));
        C14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        C14.setText("14");
        C14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        C14.setMaximumSize(new java.awt.Dimension(52, 45));
        C14.setMinimumSize(new java.awt.Dimension(52, 45));
        C14.setName(""); // NOI18N
        C14.setPreferredSize(new java.awt.Dimension(52, 45));
        add(C14);

        C13.setBackground(new java.awt.Color(0, 255, 0));
        C13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        C13.setText("13");
        C13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        C13.setMaximumSize(new java.awt.Dimension(52, 45));
        C13.setMinimumSize(new java.awt.Dimension(52, 45));
        C13.setName(""); // NOI18N
        C13.setPreferredSize(new java.awt.Dimension(52, 45));
        add(C13);

        C12.setBackground(new java.awt.Color(0, 255, 0));
        C12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        C12.setText("12");
        C12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        C12.setMaximumSize(new java.awt.Dimension(52, 45));
        C12.setMinimumSize(new java.awt.Dimension(52, 45));
        C12.setName(""); // NOI18N
        C12.setPreferredSize(new java.awt.Dimension(52, 45));
        add(C12);

        C11.setBackground(new java.awt.Color(0, 255, 0));
        C11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        C11.setText("11");
        C11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        C11.setMaximumSize(new java.awt.Dimension(52, 45));
        C11.setMinimumSize(new java.awt.Dimension(52, 45));
        C11.setName(""); // NOI18N
        C11.setPreferredSize(new java.awt.Dimension(52, 45));
        add(C11);

        C10.setBackground(new java.awt.Color(0, 255, 0));
        C10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        C10.setText("10");
        C10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        C10.setMaximumSize(new java.awt.Dimension(52, 45));
        C10.setMinimumSize(new java.awt.Dimension(52, 45));
        C10.setName(""); // NOI18N
        C10.setPreferredSize(new java.awt.Dimension(52, 45));
        add(C10);

        C9.setBackground(new java.awt.Color(0, 255, 0));
        C9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        C9.setText("9");
        C9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        C9.setMaximumSize(new java.awt.Dimension(52, 45));
        C9.setMinimumSize(new java.awt.Dimension(52, 45));
        C9.setName(""); // NOI18N
        C9.setPreferredSize(new java.awt.Dimension(52, 45));
        add(C9);

        C8.setBackground(new java.awt.Color(0, 255, 0));
        C8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        C8.setText("8");
        C8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        C8.setMaximumSize(new java.awt.Dimension(52, 45));
        C8.setMinimumSize(new java.awt.Dimension(52, 45));
        C8.setName(""); // NOI18N
        C8.setPreferredSize(new java.awt.Dimension(52, 45));
        add(C8);

        C7.setBackground(new java.awt.Color(0, 255, 0));
        C7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        C7.setText("7");
        C7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        C7.setMaximumSize(new java.awt.Dimension(52, 45));
        C7.setMinimumSize(new java.awt.Dimension(52, 45));
        C7.setName(""); // NOI18N
        C7.setPreferredSize(new java.awt.Dimension(52, 45));
        add(C7);

        C6.setBackground(new java.awt.Color(0, 255, 0));
        C6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        C6.setText("6");
        C6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        C6.setMaximumSize(new java.awt.Dimension(52, 45));
        C6.setMinimumSize(new java.awt.Dimension(52, 45));
        C6.setName(""); // NOI18N
        C6.setPreferredSize(new java.awt.Dimension(52, 45));
        C6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C6ActionPerformed(evt);
            }
        });
        add(C6);

        C5.setBackground(new java.awt.Color(0, 255, 0));
        C5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        C5.setText("5");
        C5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        C5.setMaximumSize(new java.awt.Dimension(52, 45));
        C5.setMinimumSize(new java.awt.Dimension(52, 45));
        C5.setName(""); // NOI18N
        C5.setPreferredSize(new java.awt.Dimension(52, 45));
        add(C5);

        C4.setBackground(new java.awt.Color(0, 255, 0));
        C4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        C4.setText("4");
        C4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        C4.setMaximumSize(new java.awt.Dimension(52, 45));
        C4.setMinimumSize(new java.awt.Dimension(52, 45));
        C4.setName(""); // NOI18N
        C4.setPreferredSize(new java.awt.Dimension(52, 45));
        add(C4);

        C3.setBackground(new java.awt.Color(0, 255, 0));
        C3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        C3.setText("3");
        C3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        C3.setMaximumSize(new java.awt.Dimension(52, 45));
        C3.setMinimumSize(new java.awt.Dimension(52, 45));
        C3.setName(""); // NOI18N
        C3.setPreferredSize(new java.awt.Dimension(52, 45));
        add(C3);

        C2.setBackground(new java.awt.Color(0, 255, 0));
        C2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        C2.setText("2");
        C2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        C2.setMaximumSize(new java.awt.Dimension(52, 45));
        C2.setMinimumSize(new java.awt.Dimension(52, 45));
        C2.setName(""); // NOI18N
        C2.setPreferredSize(new java.awt.Dimension(52, 45));
        add(C2);

        C1.setBackground(new java.awt.Color(0, 255, 0));
        C1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        C1.setText("1");
        C1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        C1.setMaximumSize(new java.awt.Dimension(52, 45));
        C1.setMinimumSize(new java.awt.Dimension(52, 45));
        C1.setName(""); // NOI18N
        C1.setPreferredSize(new java.awt.Dimension(52, 45));
        add(C1);

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel60.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel60.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel60.setName(""); // NOI18N
        jLabel60.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel60);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("C");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel3.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel3.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel3.setName(""); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel3);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("B");
        jLabel15.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel15.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel15.setName(""); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel15);

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel50.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel50.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel50.setName(""); // NOI18N
        jLabel50.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel50);

        B14.setBackground(new java.awt.Color(0, 255, 0));
        B14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        B14.setText("14");
        B14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B14.setMaximumSize(new java.awt.Dimension(52, 45));
        B14.setMinimumSize(new java.awt.Dimension(52, 45));
        B14.setName(""); // NOI18N
        B14.setPreferredSize(new java.awt.Dimension(52, 45));
        add(B14);

        B13.setBackground(new java.awt.Color(0, 255, 0));
        B13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        B13.setText("13");
        B13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B13.setMaximumSize(new java.awt.Dimension(52, 45));
        B13.setMinimumSize(new java.awt.Dimension(52, 45));
        B13.setName(""); // NOI18N
        B13.setPreferredSize(new java.awt.Dimension(52, 45));
        add(B13);

        B12.setBackground(new java.awt.Color(0, 255, 0));
        B12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        B12.setText("12");
        B12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B12.setMaximumSize(new java.awt.Dimension(52, 45));
        B12.setMinimumSize(new java.awt.Dimension(52, 45));
        B12.setName(""); // NOI18N
        B12.setPreferredSize(new java.awt.Dimension(52, 45));
        add(B12);

        B11.setBackground(new java.awt.Color(0, 255, 0));
        B11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        B11.setText("11");
        B11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B11.setMaximumSize(new java.awt.Dimension(52, 45));
        B11.setMinimumSize(new java.awt.Dimension(52, 45));
        B11.setName(""); // NOI18N
        B11.setPreferredSize(new java.awt.Dimension(52, 45));
        add(B11);

        B10.setBackground(new java.awt.Color(0, 255, 0));
        B10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        B10.setText("10");
        B10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B10.setMaximumSize(new java.awt.Dimension(52, 45));
        B10.setMinimumSize(new java.awt.Dimension(52, 45));
        B10.setName(""); // NOI18N
        B10.setPreferredSize(new java.awt.Dimension(52, 45));
        add(B10);

        B9.setBackground(new java.awt.Color(0, 255, 0));
        B9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        B9.setText("9");
        B9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B9.setMaximumSize(new java.awt.Dimension(52, 45));
        B9.setMinimumSize(new java.awt.Dimension(52, 45));
        B9.setName(""); // NOI18N
        B9.setPreferredSize(new java.awt.Dimension(52, 45));
        add(B9);

        B8.setBackground(new java.awt.Color(0, 255, 0));
        B8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        B8.setText("8");
        B8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B8.setMaximumSize(new java.awt.Dimension(52, 45));
        B8.setMinimumSize(new java.awt.Dimension(52, 45));
        B8.setName(""); // NOI18N
        B8.setPreferredSize(new java.awt.Dimension(52, 45));
        add(B8);

        B7.setBackground(new java.awt.Color(0, 255, 0));
        B7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        B7.setText("7");
        B7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B7.setMaximumSize(new java.awt.Dimension(52, 45));
        B7.setMinimumSize(new java.awt.Dimension(52, 45));
        B7.setName(""); // NOI18N
        B7.setPreferredSize(new java.awt.Dimension(52, 45));
        add(B7);

        B6.setBackground(new java.awt.Color(0, 255, 0));
        B6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        B6.setText("6");
        B6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B6.setMaximumSize(new java.awt.Dimension(52, 45));
        B6.setMinimumSize(new java.awt.Dimension(52, 45));
        B6.setName(""); // NOI18N
        B6.setPreferredSize(new java.awt.Dimension(52, 45));
        add(B6);

        B5.setBackground(new java.awt.Color(0, 255, 0));
        B5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        B5.setText("5");
        B5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B5.setMaximumSize(new java.awt.Dimension(52, 45));
        B5.setMinimumSize(new java.awt.Dimension(52, 45));
        B5.setName(""); // NOI18N
        B5.setPreferredSize(new java.awt.Dimension(52, 45));
        add(B5);

        B4.setBackground(new java.awt.Color(0, 255, 0));
        B4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        B4.setText("4");
        B4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B4.setMaximumSize(new java.awt.Dimension(52, 45));
        B4.setMinimumSize(new java.awt.Dimension(52, 45));
        B4.setName(""); // NOI18N
        B4.setPreferredSize(new java.awt.Dimension(52, 45));
        add(B4);

        B3.setBackground(new java.awt.Color(0, 255, 0));
        B3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        B3.setText("3");
        B3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B3.setMaximumSize(new java.awt.Dimension(52, 45));
        B3.setMinimumSize(new java.awt.Dimension(52, 45));
        B3.setName(""); // NOI18N
        B3.setPreferredSize(new java.awt.Dimension(52, 45));
        add(B3);

        B2.setBackground(new java.awt.Color(0, 255, 0));
        B2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        B2.setText("2");
        B2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B2.setMaximumSize(new java.awt.Dimension(52, 45));
        B2.setMinimumSize(new java.awt.Dimension(52, 45));
        B2.setName(""); // NOI18N
        B2.setPreferredSize(new java.awt.Dimension(52, 45));
        add(B2);

        B1.setBackground(new java.awt.Color(0, 255, 0));
        B1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        B1.setText("1");
        B1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B1.setMaximumSize(new java.awt.Dimension(52, 45));
        B1.setMinimumSize(new java.awt.Dimension(52, 45));
        B1.setName(""); // NOI18N
        B1.setPreferredSize(new java.awt.Dimension(52, 45));
        add(B1);

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel34.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel34.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel34.setName(""); // NOI18N
        jLabel34.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel34);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("B");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel5.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel5.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel5.setName(""); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel5);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("A");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel2.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel2.setName(""); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel2);

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel38.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel38.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel38.setName(""); // NOI18N
        jLabel38.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel38);

        A14.setBackground(new java.awt.Color(0, 255, 0));
        A14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        A14.setText("14");
        A14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        A14.setMaximumSize(new java.awt.Dimension(52, 45));
        A14.setMinimumSize(new java.awt.Dimension(52, 45));
        A14.setName(""); // NOI18N
        A14.setPreferredSize(new java.awt.Dimension(52, 45));
        add(A14);

        A13.setBackground(new java.awt.Color(0, 255, 0));
        A13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        A13.setText("13");
        A13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        A13.setMaximumSize(new java.awt.Dimension(52, 45));
        A13.setMinimumSize(new java.awt.Dimension(52, 45));
        A13.setName(""); // NOI18N
        A13.setPreferredSize(new java.awt.Dimension(52, 45));
        add(A13);

        A12.setBackground(new java.awt.Color(0, 255, 0));
        A12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        A12.setText("12");
        A12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        A12.setMaximumSize(new java.awt.Dimension(52, 45));
        A12.setMinimumSize(new java.awt.Dimension(52, 45));
        A12.setName(""); // NOI18N
        A12.setPreferredSize(new java.awt.Dimension(52, 45));
        add(A12);

        A11.setBackground(new java.awt.Color(0, 255, 0));
        A11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        A11.setText("11");
        A11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        A11.setMaximumSize(new java.awt.Dimension(52, 45));
        A11.setMinimumSize(new java.awt.Dimension(52, 45));
        A11.setName(""); // NOI18N
        A11.setPreferredSize(new java.awt.Dimension(52, 45));
        add(A11);

        A10.setBackground(new java.awt.Color(0, 255, 0));
        A10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        A10.setText("10");
        A10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        A10.setMaximumSize(new java.awt.Dimension(52, 45));
        A10.setMinimumSize(new java.awt.Dimension(52, 45));
        A10.setName(""); // NOI18N
        A10.setPreferredSize(new java.awt.Dimension(52, 45));
        add(A10);

        A9.setBackground(new java.awt.Color(0, 255, 0));
        A9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        A9.setText("9");
        A9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        A9.setMaximumSize(new java.awt.Dimension(52, 45));
        A9.setMinimumSize(new java.awt.Dimension(52, 45));
        A9.setName(""); // NOI18N
        A9.setPreferredSize(new java.awt.Dimension(52, 45));
        add(A9);

        A8.setBackground(new java.awt.Color(0, 255, 0));
        A8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        A8.setText("8");
        A8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        A8.setMaximumSize(new java.awt.Dimension(52, 45));
        A8.setMinimumSize(new java.awt.Dimension(52, 45));
        A8.setName(""); // NOI18N
        A8.setPreferredSize(new java.awt.Dimension(52, 45));
        add(A8);

        A7.setBackground(new java.awt.Color(0, 255, 0));
        A7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        A7.setText("7");
        A7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        A7.setMaximumSize(new java.awt.Dimension(52, 45));
        A7.setMinimumSize(new java.awt.Dimension(52, 45));
        A7.setName(""); // NOI18N
        A7.setPreferredSize(new java.awt.Dimension(52, 45));
        add(A7);

        A6.setBackground(new java.awt.Color(0, 255, 0));
        A6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        A6.setText("6");
        A6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        A6.setMaximumSize(new java.awt.Dimension(52, 45));
        A6.setMinimumSize(new java.awt.Dimension(52, 45));
        A6.setName(""); // NOI18N
        A6.setPreferredSize(new java.awt.Dimension(52, 45));
        add(A6);

        A5.setBackground(new java.awt.Color(0, 255, 0));
        A5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        A5.setText("5");
        A5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        A5.setMaximumSize(new java.awt.Dimension(52, 45));
        A5.setMinimumSize(new java.awt.Dimension(52, 45));
        A5.setName(""); // NOI18N
        A5.setPreferredSize(new java.awt.Dimension(52, 45));
        add(A5);

        A4.setBackground(new java.awt.Color(0, 255, 0));
        A4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        A4.setText("4");
        A4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        A4.setMaximumSize(new java.awt.Dimension(52, 45));
        A4.setMinimumSize(new java.awt.Dimension(52, 45));
        A4.setName(""); // NOI18N
        A4.setPreferredSize(new java.awt.Dimension(52, 45));
        add(A4);

        A3.setBackground(new java.awt.Color(0, 255, 0));
        A3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        A3.setText("3");
        A3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        A3.setMaximumSize(new java.awt.Dimension(52, 45));
        A3.setMinimumSize(new java.awt.Dimension(52, 45));
        A3.setName(""); // NOI18N
        A3.setPreferredSize(new java.awt.Dimension(52, 45));
        add(A3);

        A2.setBackground(new java.awt.Color(0, 255, 0));
        A2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        A2.setText("2");
        A2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        A2.setMaximumSize(new java.awt.Dimension(52, 45));
        A2.setMinimumSize(new java.awt.Dimension(52, 45));
        A2.setName(""); // NOI18N
        A2.setPreferredSize(new java.awt.Dimension(52, 45));
        add(A2);

        A1.setBackground(new java.awt.Color(0, 255, 0));
        A1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        A1.setText("1");
        A1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        A1.setMaximumSize(new java.awt.Dimension(52, 45));
        A1.setMinimumSize(new java.awt.Dimension(52, 45));
        A1.setName(""); // NOI18N
        A1.setPreferredSize(new java.awt.Dimension(52, 45));
        add(A1);

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel64.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel64.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel64.setName(""); // NOI18N
        jLabel64.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel64);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("A");
        jLabel12.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel12.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel12.setName(""); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel12);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel31.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel31.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel31.setName(""); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel31);

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel49.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel49.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel49.setName(""); // NOI18N
        jLabel49.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel49);

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel51.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel51.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel51.setName(""); // NOI18N
        jLabel51.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel51);

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel62.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel62.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel62.setName(""); // NOI18N
        jLabel62.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel62);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel39.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel39.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel39.setName(""); // NOI18N
        jLabel39.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel39);

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel40.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel40.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel40.setName(""); // NOI18N
        jLabel40.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel40);

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel63.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel63.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel63.setName(""); // NOI18N
        jLabel63.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel63);

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel65.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel65.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel65.setName(""); // NOI18N
        jLabel65.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel65);

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel66.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel66.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel66.setName(""); // NOI18N
        jLabel66.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel66);

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel67.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel67.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel67.setName(""); // NOI18N
        jLabel67.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel67);

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel68.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel68.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel68.setName(""); // NOI18N
        jLabel68.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel68);

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel69.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel69.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel69.setName(""); // NOI18N
        jLabel69.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel69);

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel70.setMaximumSize(new java.awt.Dimension(52, 45));
        jLabel70.setMinimumSize(new java.awt.Dimension(52, 45));
        jLabel70.setName(""); // NOI18N
        jLabel70.setPreferredSize(new java.awt.Dimension(52, 45));
        add(jLabel70);
    }// </editor-fold>//GEN-END:initComponents

    private void C6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_C6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton A1;
    private javax.swing.JButton A10;
    private javax.swing.JButton A11;
    private javax.swing.JButton A12;
    private javax.swing.JButton A13;
    private javax.swing.JButton A14;
    private javax.swing.JButton A2;
    private javax.swing.JButton A3;
    private javax.swing.JButton A4;
    private javax.swing.JButton A5;
    private javax.swing.JButton A6;
    private javax.swing.JButton A7;
    private javax.swing.JButton A8;
    private javax.swing.JButton A9;
    private javax.swing.JButton B1;
    private javax.swing.JButton B10;
    private javax.swing.JButton B11;
    private javax.swing.JButton B12;
    private javax.swing.JButton B13;
    private javax.swing.JButton B14;
    private javax.swing.JButton B2;
    private javax.swing.JButton B3;
    private javax.swing.JButton B4;
    private javax.swing.JButton B5;
    private javax.swing.JButton B6;
    private javax.swing.JButton B7;
    private javax.swing.JButton B8;
    private javax.swing.JButton B9;
    private javax.swing.JButton C1;
    private javax.swing.JButton C10;
    private javax.swing.JButton C11;
    private javax.swing.JButton C12;
    private javax.swing.JButton C13;
    private javax.swing.JButton C14;
    private javax.swing.JButton C2;
    private javax.swing.JButton C3;
    private javax.swing.JButton C4;
    private javax.swing.JButton C5;
    private javax.swing.JButton C6;
    private javax.swing.JButton C7;
    private javax.swing.JButton C8;
    private javax.swing.JButton C9;
    private javax.swing.JButton D1;
    private javax.swing.JButton D10;
    private javax.swing.JButton D11;
    private javax.swing.JButton D12;
    private javax.swing.JButton D13;
    private javax.swing.JButton D2;
    private javax.swing.JButton D3;
    private javax.swing.JButton D4;
    private javax.swing.JButton D5;
    private javax.swing.JButton D6;
    private javax.swing.JButton D7;
    private javax.swing.JButton D8;
    private javax.swing.JButton D9;
    private javax.swing.JButton E1;
    private javax.swing.JButton E10;
    private javax.swing.JButton E11;
    private javax.swing.JButton E12;
    private javax.swing.JButton E13;
    private javax.swing.JButton E2;
    private javax.swing.JButton E3;
    private javax.swing.JButton E4;
    private javax.swing.JButton E5;
    private javax.swing.JButton E6;
    private javax.swing.JButton E7;
    private javax.swing.JButton E8;
    private javax.swing.JButton E9;
    private javax.swing.JButton F1;
    private javax.swing.JButton F10;
    private javax.swing.JButton F11;
    private javax.swing.JButton F12;
    private javax.swing.JButton F13;
    private javax.swing.JButton F2;
    private javax.swing.JButton F3;
    private javax.swing.JButton F4;
    private javax.swing.JButton F5;
    private javax.swing.JButton F6;
    private javax.swing.JButton F7;
    private javax.swing.JButton F8;
    private javax.swing.JButton F9;
    private javax.swing.JButton G1;
    private javax.swing.JButton G10;
    private javax.swing.JButton G11;
    private javax.swing.JButton G12;
    private javax.swing.JButton G2;
    private javax.swing.JButton G3;
    private javax.swing.JButton G4;
    private javax.swing.JButton G5;
    private javax.swing.JButton G6;
    private javax.swing.JButton G7;
    private javax.swing.JButton G8;
    private javax.swing.JButton G9;
    private javax.swing.JButton H1;
    private javax.swing.JButton H10;
    private javax.swing.JButton H11;
    private javax.swing.JButton H12;
    private javax.swing.JButton H2;
    private javax.swing.JButton H3;
    private javax.swing.JButton H4;
    private javax.swing.JButton H5;
    private javax.swing.JButton H6;
    private javax.swing.JButton H7;
    private javax.swing.JButton H8;
    private javax.swing.JButton H9;
    private javax.swing.JButton I1;
    private javax.swing.JButton I10;
    private javax.swing.JButton I11;
    private javax.swing.JButton I12;
    private javax.swing.JButton I13;
    private javax.swing.JButton I14;
    private javax.swing.JButton I15;
    private javax.swing.JButton I16;
    private javax.swing.JButton I2;
    private javax.swing.JButton I3;
    private javax.swing.JButton I4;
    private javax.swing.JButton I5;
    private javax.swing.JButton I6;
    private javax.swing.JButton I7;
    private javax.swing.JButton I8;
    private javax.swing.JButton I9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables

}
