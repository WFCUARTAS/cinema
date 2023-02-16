/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import clases.*;
import java.awt.Image;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author WILLIANC
 */
public class sala extends javax.swing.JFrame {
    conectar cc =new conectar();
    Connection con;
    
    usuario user = new usuario();
    pelicula pelicula_select = new pelicula();
    funcion funcion_selec = new funcion();
    private sala1 s1;
    private sala2 s2;
    private sala3 s3;
    private sala4 s4;
    
    
    Calendar calendar;
    String fecha="";
    int gafas;
    
    //abre el frame salas cuando es llamado desde el frame funciones
    public sala(pelicula p,funcion f,usuario us, Calendar fec  ) {
        pelicula_select=p;
        user= us;
        con = cc.conexion(user.get_ip());
        funcion_selec=f;
        calendar=fec;
        gafas=0;
        initComponents();

        btn_gafas.setText("<html>GAFAS <br>"+gafas+"</html>");
        
        cargar();
        cargar_apartadas();
        
        ImageIcon ruta= new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"));
       Icon icono = new ImageIcon(ruta.getImage().getScaledInstance(logo1.getWidth(),logo1.getHeight(), Image.SCALE_AREA_AVERAGING));
       logo1.setIcon(icono);
       
       regresar.setVisible(false);
       
    }
    
    //abre el frame sala cuando es llamado desde el reporte-administrador
    public sala(String s,pelicula p,funcion f,usuario us, Calendar fec  ) {
        pelicula_select=p;
        user= us;
        con = cc.conexion(user.get_ip());
        funcion_selec=f;
        gafas=0;
        initComponents();
        
        calendar=fec;
        
        cargar();
        cargar_apartadas();
        
        ImageIcon ruta= new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"));
       Icon icono = new ImageIcon(ruta.getImage().getScaledInstance(logo1.getWidth(),logo1.getHeight(), Image.SCALE_AREA_AVERAGING));
       logo1.setIcon(icono);
       
       atras.setVisible(false);
       inicio.setVisible(false);
       if(funcion_selec.get_sala()==1){
            s1.hilo=false;
        }else if(funcion_selec.get_sala()==2){
            s2.hilo=false;
        }else if(funcion_selec.get_sala()==3){
            s3.hilo=false;
        }
        else{
             s4.hilo=false;
         }
    }

    void cargar(){
        
        Image img = getToolkit().getImage("C:\\cinema\\peliculas\\"+pelicula_select.get_imagen_url()).getScaledInstance(imagen_pelicula.getWidth(),imagen_pelicula.getHeight(), Image.SCALE_AREA_AVERAGING);
        //Image img = pelicula_select.get_imagen().getScaledInstance(imagen_pelicula.getWidth(),imagen_pelicula.getHeight(), Image.SCALE_DEFAULT);
        imagen_pelicula.setIcon(new ImageIcon(img)); 
        num_sala.setText(""+funcion_selec.get_sala());
        titulo.setText(pelicula_select.get_nombre());
        hora.setText(funcion_selec.get_hora_completa());
        
        fecha=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        imagen_pelicula.setText("");
        
        if(funcion_selec.get_sala()==1){
            s1 = new sala1(pelicula_select,funcion_selec,this, calendar,user);
            s1.setSize(1164,555);
            s1.setLocation(2,2);
            sala.add(s1);
            s1.cargar();
        }else if(funcion_selec.get_sala()==2){
            s2 = new sala2(pelicula_select,funcion_selec,this, calendar,user);
            s2.setSize(1164,555);
            s2.setLocation(2,2);
            sala.add(s2);
            s2.cargar();
        }else if(funcion_selec.get_sala()==3){
            s3 = new sala3(pelicula_select,funcion_selec,this, calendar,user);
            s3.setSize(1164,555);
            s3.setLocation(2,2);
            sala.add(s3);
            s3.cargar();
        }
        else{
            s4 = new sala4(pelicula_select,funcion_selec,this, calendar,user);
            s4.setSize(1164,555);
            s4.setLocation(2,2);
            sala.add(s4);
            s4.cargar();
        }

    }
    
    public void cargar_apartadas(){
        lista_apartadas.removeAllItems();
        try {
            Statement st = con.createStatement();
            ResultSet result =st.executeQuery("SELECT * FROM apartadas WHERE funcion_id='"+funcion_selec.get_id()+"' and fecha_funcion='"+fecha+"'");
            while(result.next()){
                boolean b= true;
                for(int i=0;i<lista_apartadas.getItemCount();i++){
                    if(lista_apartadas.getItemAt(i).equals(result.getString(3))){
                        b=false;
                    }
                }
                if(b){
                    lista_apartadas.addItem(result.getString(3));
                }
            }  

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error:  33"+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sala = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        num_sala = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        apartar = new javax.swing.JButton();
        convenio = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        imagen_pelicula = new javax.swing.JLabel();
        atras = new javax.swing.JButton();
        inicio = new javax.swing.JButton();
        hora = new javax.swing.JLabel();
        actualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        logo1 = new javax.swing.JLabel();
        regresar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lista_apartadas = new javax.swing.JComboBox<>();
        seleccionar = new javax.swing.JButton();
        liberar_apartada = new javax.swing.JButton();
        reloj = new cinema.reloj();
        btn_gafas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("sala");
        setExtendedState(1);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sala.setBackground(new java.awt.Color(204, 204, 255));
        sala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sala.setMaximumSize(new java.awt.Dimension(1164, 590));
        sala.setMinimumSize(new java.awt.Dimension(1164, 590));
        sala.setPreferredSize(new java.awt.Dimension(1068, 590));
        sala.setRequestFocusEnabled(false);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla.png"))); // NOI18N

        javax.swing.GroupLayout salaLayout = new javax.swing.GroupLayout(sala);
        sala.setLayout(salaLayout);
        salaLayout.setHorizontalGroup(
            salaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salaLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jLabel9)
                .addContainerGap(138, Short.MAX_VALUE))
        );
        salaLayout.setVerticalGroup(
            salaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, salaLayout.createSequentialGroup()
                .addContainerGap(560, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        getContentPane().add(sala, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 63, 1170, 650));

        titulo.setFont(new java.awt.Font("Tahoma", 1, 43)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("pelicula con el titulo que tiene 50 caracteres pru");
        titulo.setToolTipText("");
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 0, 1164, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("sala");

        num_sala.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        num_sala.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        num_sala.setText("1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num_sala, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(num_sala)
                .addGap(0, 0, 0))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 69, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(0, 255, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setText("VENDER");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 145, 61));

        apartar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        apartar.setText("APARTAR");
        apartar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apartarActionPerformed(evt);
            }
        });
        jPanel2.add(apartar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 185, 145, 30));

        convenio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        convenio.setText("CONVENIO");
        convenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convenioActionPerformed(evt);
            }
        });
        jPanel2.add(convenio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 145, 40));

        jButton2.setBackground(new java.awt.Color(204, 255, 204));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icono_tarjeta.png"))); // NOI18N
        jButton2.setText("VENTA VIP");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 145, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 207, 160, 220));

        imagen_pelicula.setText("imagen_pelicula");
        getContentPane().add(imagen_pelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 150, 210));

        atras.setBackground(new java.awt.Color(255, 0, 204));
        atras.setText("ATRAS");
        atras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                atrasMousePressed(evt);
            }
        });
        getContentPane().add(atras, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 720, 116, 43));

        inicio.setBackground(new java.awt.Color(102, 102, 255));
        inicio.setText("INICIO");
        inicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                inicioMousePressed(evt);
            }
        });
        getContentPane().add(inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 720, 108, 41));

        hora.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        hora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        hora.setText("06:30 PM");
        getContentPane().add(hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 11, 153, 52));

        actualizar.setBackground(new java.awt.Color(255, 255, 0));
        actualizar.setText("ACTUALIZAR");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });
        getContentPane().add(actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 720, 105, 41));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("© GEARSOFT STUDIO 2019");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 730, 181, -1));

        logo1.setText("logo-cina");
        getContentPane().add(logo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 710, 120, 60));

        regresar.setText("REGRESAR");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });
        getContentPane().add(regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 720, 116, 41));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        seleccionar.setText("seleccionar");
        seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarActionPerformed(evt);
            }
        });

        liberar_apartada.setText("liberar");
        liberar_apartada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liberar_apartadaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lista_apartadas, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(seleccionar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(liberar_apartada)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(liberar_apartada)
                    .addComponent(lista_apartadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seleccionar))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 720, 360, 40));
        getContentPane().add(reloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 720, -1, -1));

        btn_gafas.setBackground(new java.awt.Color(204, 255, 204));
        btn_gafas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_gafas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gafas.png"))); // NOI18N
        btn_gafas.setText("GAFAS");
        btn_gafas.setToolTipText("");
        btn_gafas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gafasActionPerformed(evt);
            }
        });
        getContentPane().add(btn_gafas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if(funcion_selec.get_sala()==1){
                s1.venta(gafas,null,0,new tarjeta());
            }else if(funcion_selec.get_sala()==2){
                s2.venta(gafas,null,0,new tarjeta());
            }else if(funcion_selec.get_sala()==3){
                s3.venta(gafas,null,0,new tarjeta());
            }else{
                s4.venta(gafas,null,0,new tarjeta());
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(sala.class.getName()).log(Level.SEVERE, null, ex);
        }
        gafas=0;
        btn_gafas.setText("<html>GAFAS <br>"+gafas+"</html>");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        if(funcion_selec.get_sala()==1){
            s1.cargar();
        }else if(funcion_selec.get_sala()==2){
            s2.cargar();
        }else if(funcion_selec.get_sala()==3){
            s3.cargar();
        }else{
            s4.cargar();
        }
    }//GEN-LAST:event_actualizarActionPerformed

    private void apartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apartarActionPerformed
        try {
            if(funcion_selec.get_sala()==1){
                s1.apartar();
            }else if(funcion_selec.get_sala()==2){
                s2.apartar();
            }else if(funcion_selec.get_sala()==3){
                s3.apartar();
            }else{
                s4.apartar();
            }
        } catch (ParseException ex) {
            Logger.getLogger(sala.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargar_apartadas();
    }//GEN-LAST:event_apartarActionPerformed

    private void seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarActionPerformed
        if(funcion_selec.get_sala()==1){
            s1.selec_apartadas(lista_apartadas.getItemAt(lista_apartadas.getSelectedIndex()));
        }else if(funcion_selec.get_sala()==2){
            s2.selec_apartadas(lista_apartadas.getItemAt(lista_apartadas.getSelectedIndex()));
        }else if(funcion_selec.get_sala()==3){
            s3.selec_apartadas(lista_apartadas.getItemAt(lista_apartadas.getSelectedIndex()));
        }else{
            s4.selec_apartadas(lista_apartadas.getItemAt(lista_apartadas.getSelectedIndex()));
        }      
    }//GEN-LAST:event_seleccionarActionPerformed

    private void convenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convenioActionPerformed
        try {
            if(funcion_selec.get_sala()==1){
                s1.convenio(gafas);
            }else if(funcion_selec.get_sala()==2){
                s2.convenio(gafas);
            }else if(funcion_selec.get_sala()==3){
                s3.convenio(gafas);
            }else {
                s4.convenio(gafas);
            }            
        } catch (ParseException ex) {
            Logger.getLogger(sala.class.getName()).log(Level.SEVERE, null, ex);
        }
        gafas=0;
        btn_gafas.setText("<html>GAFAS <br>"+gafas+"</html>");
    }//GEN-LAST:event_convenioActionPerformed

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        // TODO add your handling code here:
        if(funcion_selec.get_sala()==1){
            s1.hilo=false;
            s1.close();
        }else if(funcion_selec.get_sala()==2){
            s2.hilo=false;
            s2.close();
        }else if(funcion_selec.get_sala()==3){
            s3.hilo=false;
            s3.close();
        }else{
            s4.hilo=false;
            s4.close();
        }
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error cerrando conexion:  "+ex.getMessage());
        }
        
        reloj.stop();
        dispose();
    }//GEN-LAST:event_regresarActionPerformed

        //eliminar apartadas
    private void liberar_apartadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_liberar_apartadaActionPerformed
        int response = JOptionPane.showConfirmDialog(null, "DESEA ELIMINAR APARTADAS", "Confirmacion",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            try{
                String sql;
                sql="DELETE FROM apartadas WHERE nombre='"+lista_apartadas.getItemAt(lista_apartadas.getSelectedIndex())+"' AND fecha_funcion='"+fecha+"'";
                PreparedStatement pst= con.prepareStatement(sql);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null,"se elimino exitosamente");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error 40:  "+ex); 
            }
        }
        cargar_apartadas();
        if(funcion_selec.get_sala()==1){
            s1.cargar();
        }else if(funcion_selec.get_sala()==2){
            s2.cargar();
        }else if(funcion_selec.get_sala()==3){
            s3.cargar();
        }else{
            s4.cargar();
        }
    }//GEN-LAST:event_liberar_apartadaActionPerformed

    private void atrasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atrasMousePressed
        if(funcion_selec.get_sala()==1){
            s1.hilo=false;
            s1.close();
        }else if(funcion_selec.get_sala()==2){
            s2.hilo=false;
            s2.close();
        }else if(funcion_selec.get_sala()==3){
            s3.hilo=false;
            s3.close();
        }else{
            s4.hilo=false;
            s4.close();
        }
        
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error cerrando conexion:  "+ex.getMessage());
        }
        
        funciones inicio = new funciones(pelicula_select,user);
        inicio.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        inicio.setResizable(false);
        inicio.setVisible(true);
        reloj.stop();
        dispose();
    }//GEN-LAST:event_atrasMousePressed

    private void inicioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inicioMousePressed
        if(funcion_selec.get_sala()==1){
            s1.hilo=false;
            s1.close();;
        }else if(funcion_selec.get_sala()==2){
            s2.hilo=false;
            s2.close();
        }else if(funcion_selec.get_sala()==3){
            s3.hilo=false;
            s3.close();
        }else {
            s4.hilo=false;
            s4.close();
        }
        
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
    }//GEN-LAST:event_inicioMousePressed

    private void btn_gafasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gafasActionPerformed
        vender_gafas report_gafas= new vender_gafas(this, true, user, true);
        report_gafas.setVisible(true);
        gafas = report_gafas.get_cantidad();
        btn_gafas.setText("<html>GAFAS <br>"+gafas+"</html>");
    }//GEN-LAST:event_btn_gafasActionPerformed

    //VENTAS VIP
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            if(funcion_selec.get_sala()==1){
                s1.tarjeta_venta(gafas);
            }else if(funcion_selec.get_sala()==2){
                s2.tarjeta_venta(gafas);
            }else if(funcion_selec.get_sala()==3){
                s3.tarjeta_venta(gafas);
            }else {
                s4.tarjeta_venta(gafas);
            }            
        } catch (ParseException ex) {
            Logger.getLogger(sala.class.getName()).log(Level.SEVERE, null, ex);
        }
        gafas=0;
        btn_gafas.setText("<html>GAFAS <br>"+gafas+"</html>");
    }//GEN-LAST:event_jButton2ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton apartar;
    private javax.swing.JButton atras;
    private javax.swing.JButton btn_gafas;
    private javax.swing.JButton convenio;
    private javax.swing.JLabel hora;
    private javax.swing.JLabel imagen_pelicula;
    private javax.swing.JButton inicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton liberar_apartada;
    private javax.swing.JComboBox<String> lista_apartadas;
    private javax.swing.JLabel logo1;
    private javax.swing.JLabel num_sala;
    private javax.swing.JButton regresar;
    private cinema.reloj reloj;
    private javax.swing.JPanel sala;
    private javax.swing.JButton seleccionar;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
