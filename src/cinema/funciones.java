/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import clases.usuario;
import clases.conectar;
import clases.funcion;
import clases.pelicula;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author WILLIANC
 */
public class funciones extends javax.swing.JFrame implements ActionListener{

    conectar cc =new conectar();
    Connection con;
    
    
    private JButton [] botones_pelicula = new JButton[10];
    private JLabel [] cantidad = new JLabel[10];
    private JPanel [] panel_color= new JPanel [10];
     
    Color color_lleno=new Color(255, 0, 0);
    Color color_bacio=new Color(0, 255, 0);
    Color color_medio=new Color(255,255 , 0);
    
    usuario user = new usuario();
    pelicula pelicula_select = new pelicula();
    ArrayList<funcion> funciones = new ArrayList<funcion>();
    int capacidad1,capacidad2,capacidad3,capacidad4;
    
    Calendar calendar = Calendar.getInstance();
    Calendar c = Calendar.getInstance();
    String fecha;
    int aumento[]=new int[]{3,2,1,0,6,5,4};
    
    public funciones(pelicula p, usuario us) {
        pelicula_select=p;
        user= us;
        con = cc.conexion(user.get_ip());
        capacidad1=137;
        capacidad2=137;
        capacidad3=121;
        capacidad4=121;
        initComponents();
        fecha=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        
        titulo_pelicula.setText(pelicula_select.get_nombre());
        cargar_fechas();
        imagen_pelicula.setText("");
       new Thread(new hilo_listar()).start();
       
       ImageIcon ruta= new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"));
       Icon icono = new ImageIcon(ruta.getImage().getScaledInstance(logo1.getWidth(),logo1.getHeight(), Image.SCALE_AREA_AVERAGING));
       logo1.setIcon(icono);

    }
    
    private class hilo_listar implements Runnable{

        @Override
        public void run() {
            listar();
            Image img = getToolkit().getImage("C:\\cinema\\peliculas\\"+pelicula_select.get_imagen_url()).getScaledInstance(imagen_pelicula.getWidth(),imagen_pelicula.getHeight(), Image.SCALE_AREA_AVERAGING);
            //img = pelicula_select.get_imagen().getScaledInstance(imagen_pelicula.getWidth(),imagen_pelicula.getHeight(), Image.SCALE_DEFAULT);
            imagen_pelicula.setIcon(new ImageIcon(img)); 
        } 
    
    }
    
    void listar(){
        int pos =30;
        int i =0;
        int cont =0;
        String contenido;
        String ampm="AM";
        char hora[];
        int h;
        int cap=0;
        
        try {
            Statement st = con.createStatement();
                ResultSet result =st.executeQuery( "select fun.* , COUNT(fun.id)-1 total from (\n" +
                                                    "(SELECT * \n" +
                                                    "FROM funciones \n" +
                                                    "WHERE activa='1' AND\n" +
                                                    "pelicula_id ='"+pelicula_select.get_id()+"' AND \n" +
                                                    "(fecha IS NULL OR fecha='"+fecha+"'))\n" +
                                                    "union all\n" +
                                                    "(SELECT f.* \n" +
                                                    "FROM funciones f , ventas v \n" +
                                                    "WHERE f.activa='1' AND \n" +
                                                    "f.pelicula_id='"+pelicula_select.get_id()+"' AND \n" +
                                                    "(f.fecha IS NULL OR f.fecha='"+fecha+"') AND\n" +
                                                    "v.funcion_id=f.id AND v.fecha_funcion='"+fecha+"')) fun \n" +
                                                    "GROUP BY (fun.id)\n" +
                                                    "ORDER BY fun.hora  LIMIT 10");
            
            while(result.next()){
                cont=cont+1;
            }
            result.beforeFirst();
            while(result.next()){
                ampm="AM";
                funcion f = new funcion();
                hora=result.getString(3).toCharArray();
                h=Integer.parseInt(""+hora[0]+hora[1]);
                if(h==0 || h==12){
                    h=h+12;
                }
                if(h>12){
                    h=h-12;
                    ampm="PM";
                }
                
                contenido=h+":"+hora[3]+hora[4]+"  "+ampm+"     SALA "+result.getString(2);
                if(result.getInt(2)==1){cap=capacidad1-result.getInt(9);}
                else if(result.getInt(2)==2){cap=capacidad2-result.getInt(9);}
                else if(result.getInt(2)==3){cap=capacidad3-result.getInt(9);}
                else if(result.getInt(2)==4){cap=capacidad4-result.getInt(9);}
                
                agregar_funcion(40,pos,(500/cont)-10,i,result.getString(1),contenido,cap);
                pos=pos+(500/cont);
                i=i+1;
                
                f.set_id(result.getString(1));
               f.set_sala(result.getInt(2));
               if(h<10){f.set_hora("0"+h);}
               else{f.set_hora(""+h);}
               f.set_minutos(""+hora[3]+hora[4]);
               f.set_ampm(ampm);
               f.set_activa(result.getBoolean(4));
               f.set_fecha(result.getDate(5));
               f.set_precio(result.getInt(6));
               f.set_pelicula_id(result.getString(7));
               f.set_franja(result.getInt(8));
               
               funciones.add(f);
            }               
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 24:  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void agregar_funcion(int x, int y, int tam, int indice,String id, String contenido,int c){
        
        botones_pelicula [indice] = new javax.swing.JButton();
        botones_pelicula [indice].setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        botones_pelicula [indice].setText(contenido);
        jPanel1.add(botones_pelicula [indice], new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, 500, tam));
        botones_pelicula [indice].addActionListener(this);  
        
        ///cantidad
        cantidad [indice] = new javax.swing.JLabel();
        cantidad [indice].setText(""+(c));
        jPanel1.add(cantidad [indice], new org.netbeans.lib.awtextra.AbsoluteConstraints(550,y+(tam/2)-15, 60, 30));
        cantidad [indice].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cantidad [indice].setVerticalAlignment(javax.swing.SwingConstants.TOP);
        cantidad [indice].setFont(new java.awt.Font("Tahoma", 1, 24));
        
        ///panel con color 
        panel_color [indice]= new javax.swing.JPanel();
        if(c>60){panel_color [indice].setBackground(color_bacio);}
        else if(c>30){panel_color [indice].setBackground(color_medio);}
        else{panel_color [indice].setBackground(color_lleno);}
        
        jPanel1.add(panel_color [indice], new org.netbeans.lib.awtextra.AbsoluteConstraints(620,y+(tam/2)-15, 30, 30));
    }
    
    
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<funciones.size();i++){
            if(e.getSource()==botones_pelicula [i]){
                try {
                con.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"error cerrando conexion:  "+ex.getMessage());
                }

                sala s = new sala(pelicula_select,funciones.get(i),user, calendar);
                s.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                s.setResizable(false);
                s.setVisible(true);
                reloj.stop();
                dispose();
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo_pelicula = new javax.swing.JLabel();
        imagen_pelicula = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_atras = new javax.swing.JButton();
        fecha_funcion = new com.toedter.calendar.JDateChooser();
        seleccionar_fecha = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        logo1 = new javax.swing.JLabel();
        reloj = new cinema.reloj();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("funciones");
        setExtendedState(1);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setSize(new java.awt.Dimension(1366, 768));

        titulo_pelicula.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        titulo_pelicula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo_pelicula.setText("TITULO DE LA PELICULA con 50 caracteres para prova");

        imagen_pelicula.setText("jLabel1");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_atras.setText("ATRAS");
        btn_atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atrasActionPerformed(evt);
            }
        });

        fecha_funcion.setBackground(new java.awt.Color(255, 255, 255));

        seleccionar_fecha.setText("seleccionar");
        seleccionar_fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionar_fechaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("© GEARSOFT STUDIO 2019");

        logo1.setText("logo-cina");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imagen_pelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_atras, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(fecha_funcion, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addComponent(seleccionar_fecha))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(394, 394, 394)
                                        .addComponent(jLabel2)))
                                .addGap(120, 120, 120)
                                .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(reloj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(titulo_pelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 1338, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(titulo_pelicula)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imagen_pelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(btn_atras, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(seleccionar_fecha)
                                            .addComponent(fecha_funcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 32, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(reloj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atrasActionPerformed
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error cerrando conexion:  "+ex.getMessage());
        }
        
        Inicio inicio = new Inicio(user);
        inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
        inicio.setResizable(false);
        inicio.setVisible(true);
        reloj.stop();
        dispose();
    }//GEN-LAST:event_btn_atrasActionPerformed

    private void seleccionar_fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionar_fechaActionPerformed
        // TODO add your handling code here:
        if(fecha_funcion.getDate()!=null){
            int response = JOptionPane.showConfirmDialog(null, "esta seguro de cambiar la fecha de venta?", "Confirmacion",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                calendar= fecha_funcion.getCalendar();
                fecha=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
                funciones.clear();
                jPanel1.removeAll();
                jPanel1.repaint();
                new Thread(new hilo_listar()).start(); 
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fecha");
        }
        
    }//GEN-LAST:event_seleccionar_fechaActionPerformed

    private void cargar_fechas(){
        
        c.add(Calendar.DAY_OF_YEAR,1);
        fecha_funcion.setMinSelectableDate(new Date(c.getTimeInMillis()));
        

        /*  ///cambiar que solo deje vender hasta e miescoles sguiente para que deje vende para las siguientes 15 dias  2018-03-29
        c.add(Calendar.DAY_OF_YEAR, aumento[c.get(Calendar.DAY_OF_WEEK)-1]);
        */
        c.add(Calendar.DAY_OF_YEAR, 14);
        fecha_funcion.setMaxSelectableDate(new Date(c.getTimeInMillis()));

    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_atras;
    private com.toedter.calendar.JDateChooser fecha_funcion;
    private javax.swing.JLabel imagen_pelicula;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logo1;
    private cinema.reloj reloj;
    private javax.swing.JButton seleccionar_fecha;
    private javax.swing.JLabel titulo_pelicula;
    // End of variables declaration//GEN-END:variables

}
