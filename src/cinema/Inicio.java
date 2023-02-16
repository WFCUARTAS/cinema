/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import clases.usuario;
import clases.conectar;
import clases.pelicula;
import imprimir.imprimir_factura_gafas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.*;


public class Inicio extends javax.swing.JFrame implements ActionListener{
   
    conectar cc =new conectar();
    Connection con;
    usuario user= new usuario();
    
   private JButton [] botones_pelicula = new JButton[50];
   private JLabel [] nombre_pelicula = new JLabel[50];
   
   ArrayList<pelicula> peliculas = new ArrayList<pelicula>();
   

    public Inicio(usuario us) {
        user=us;

        con = cc.conexion(user.get_ip());
        initComponents();
        if(user.get_privilegios()==0){
            configuracion.setVisible(false);
        }
        new Thread(new hilo_listar()).start();
        jScrollPane2.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 50));


        ImageIcon ruta= new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"));
        Icon icono = new ImageIcon(ruta.getImage().getScaledInstance(logo1.getWidth(),logo1.getHeight(), Image.SCALE_DEFAULT));
        logo1.setIcon(icono);
        logo2.setIcon(icono);
        
        
        //Backup("root", "Cine_(MHS840)", "cine-espinal");
    }
    
    
    private class hilo_listar implements Runnable{

        @Override
        public void run() {
            //throw new UnsupportedOperationException("Not supported yet.");
            //To change body of generated methods, choose Tools | Templates.
            listar();
            if(user.get_id()==null){
                logear();
            }
        } 
    }
    
    void listar(){
        
        int pos =20;
        int i =0;
        boolean super_estreno=false;
        try {
            Statement st = con.createStatement();
            ResultSet result =st.executeQuery("SELECT id, nombre, tipo,super_estreno, imagen_url  FROM `peliculas` WHERE activa='1' ORDER BY super_estreno DESC,id DESC ");

            while(result.next()){
               //imagen
               pelicula p = new pelicula();
               
                
                Image img = null;
                File fichero = new File("C:\\cinema\\peliculas\\"+result.getString(5));
                
                if(!fichero.exists()){
                    Statement st1 = con.createStatement();
                    ResultSet result2 = st1.executeQuery("SELECT imagen FROM `peliculas` where id = "+result.getString(1)+"");
                    result2.next();
                    
                    Blob blob = result2.getBlob(1);
                    byte[]data=blob.getBytes(1,(int)blob.length());
                    img = ImageIO.read(new ByteArrayInputStream(data));
                    BufferedImage bImage = (BufferedImage) img;
                    
                    ImageIO.write(bImage, getFileExtension(result.getString(5)) , new File("C:\\cinema\\peliculas\\"+result.getString(5)));                
                    img = getToolkit().getImage("src/imagenes/sin-foto.png");
                }
                
                img = getToolkit().getImage("C:\\cinema\\peliculas\\"+result.getString(5));

                super_estreno=false;
                if(result.getString(4).equals("1")){
                    super_estreno=true;
                }
                
                agregar_pelicula(pos,10,i,result.getString(1),result.getString(2),img,super_estreno);
                pos=pos+320;
                i=i+1;
                ///caragar valores en el arreglo
                p.set_id(result.getString(1));
                p.set_nombre(result.getString(2));
                //p.set_imagen(img);
                p.set_tipo(result.getString(3));
                p.set_imagen_url(result.getString(5));

                peliculas.add(p);
                
            }
            con.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 2 "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"error 2 imagen"+ex);
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void agregar_pelicula(int x, int y, int indice,String id, String contenido ,Image img ,boolean super_estreno){
        ////boton
        Image imagen = img;
        botones_pelicula [indice] = new javax.swing.JButton();
        botones_pelicula [indice].setText(id);
        jPanel1.add(botones_pelicula [indice], new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, 300, 500));
        botones_pelicula [indice].addActionListener(this);
        imagen = imagen.getScaledInstance(310,510, Image.SCALE_AREA_AVERAGING);
        botones_pelicula [indice].setIcon(new ImageIcon(imagen)); 
        
        if(super_estreno){
            botones_pelicula [indice].setBorder(new javax.swing.border.MatteBorder(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borde.png"))));
        }
        
        //nombre
        nombre_pelicula [indice] = new javax.swing.JLabel();
        nombre_pelicula [indice].setText("<html>"+contenido+"<html>");
        jPanel1.add(nombre_pelicula [indice], new org.netbeans.lib.awtextra.AbsoluteConstraints(x, 510, 300, 60));
        nombre_pelicula [indice].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nombre_pelicula [indice].setVerticalAlignment(javax.swing.SwingConstants.TOP);
        nombre_pelicula [indice].setFont(new java.awt.Font("Tahoma", 0, 24));
    }  
    
    public void actionPerformed(ActionEvent e) {

        for(int i=0;i<peliculas.size();i++){
            if(peliculas.get(i).get_id().equals(e.getActionCommand())){

                funciones inicio = new funciones(peliculas.get(i),user);
                inicio.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                inicio.setResizable(false);
                inicio.setVisible(true);
                reloj.stop();
                dispose();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        configuracion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        logo1 = new javax.swing.JLabel();
        logo2 = new javax.swing.JLabel();
        GEARSOFT = new javax.swing.JLabel();
        gafas = new javax.swing.JButton();
        reloj = new cinema.reloj();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("inicio");
        setExtendedState(1);
        setUndecorated(true);
        setSize(new java.awt.Dimension(700, 400));

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane2.setName(""); // NOI18N

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jScrollPane2.setViewportView(jPanel1);

        configuracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/configuracion.png"))); // NOI18N
        configuracion.setToolTipText("");
        configuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configuracionActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 55)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MULTICINE ESPINAL");

        logo1.setText("jLabel2");

        logo2.setText("jLabel2");

        GEARSOFT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        GEARSOFT.setText("© GEARSOFT STUDIO 2017");

        gafas.setBackground(new java.awt.Color(204, 255, 204));
        gafas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        gafas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gafas.png"))); // NOI18N
        gafas.setText("  GAFAS");
        gafas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gafasActionPerformed(evt);
            }
        });

        jMenu1.setText("ADMINISTRADOR");

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem2.setText("Reporte Ventas");
        jMenuItem2.setActionCommand("Reporte Ventas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem1.setText("Cambiar usuario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jMenuItem3.setText("salir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jMenuItem4.setText("conexion");
        jMenuItem4.setToolTipText("");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(GEARSOFT)
                        .addGap(426, 426, 426)
                        .addComponent(reloj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addComponent(gafas)
                            .addGap(52, 52, 52)
                            .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(logo2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(configuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(configuracion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(logo2, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                                .addComponent(logo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gafas, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reloj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GEARSOFT))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

       ///ABRIR PANEL DE ADMINISTRADOR
    private void configuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configuracionActionPerformed
        // TODO add your handling code here:
        
        administrador admin =new administrador(user);
        admin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        admin.setResizable(false);
        admin.setVisible(true);
        reloj.stop();
        dispose();
    }//GEN-LAST:event_configuracionActionPerformed

        ///panel de inicio de secion
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        logear ();
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error cerrando conexion:  "+ex.getMessage());
        }
        
        reloj.stop();
        dispose();
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        String ip = JOptionPane.showInputDialog(this,"DIRECCION IP BASE DE DATOS",user.get_ip());
        
        if(ip != null){
            //JOptionPane.showMessageDialog(null,""+ ip);
            
            FileWriter fichero = null;
            PrintWriter pw = null;
            try
            {
                fichero = new FileWriter("C:\\cinema\\ip.txt");
                pw = new PrintWriter(fichero);
                pw.println(ip);
                
                user.set_ip(ip);
                
                con = cc.conexion(user.get_ip());
                if(user.get_privilegios()!=1){
                    configuracion.setVisible(false);
                }
                new Thread(new hilo_listar()).start();


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
               try {
               // Nuevamente aprovechamos el finally para 
               // asegurarnos que se cierra el fichero.
               if (null != fichero)
                  fichero.close();
               } catch (Exception e2) {
                  e2.printStackTrace();
               }
        }
            
        }
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void gafasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gafasActionPerformed
        // TODO add your handling code here:
        vender_gafas report_gafas= new vender_gafas(this, true, user, false);
        report_gafas.setVisible(true);
    }//GEN-LAST:event_gafasActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        reporte_mis_ventas report_venta= new reporte_mis_ventas(this, true, user);
        report_venta.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    
    public void Backup(String user, String pass, String database) {
        /*
        // configuracion de la fecha actual 
        Calendar c = Calendar.getInstance();
        String fecha = String.valueOf(c.get(Calendar.DATE));
        fecha = fecha + "-" + String.valueOf(c.get(Calendar.MONTH));
        fecha = fecha + "-" + String.valueOf(c.get(Calendar.YEAR));
        fecha = fecha + "." + String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        fecha = fecha + "." + String.valueOf(c.get(Calendar.MINUTE));
        fecha = fecha + "." + String.valueOf(c.get(Calendar.SECOND));
               
        // clase Runtime para lanzar DOS
        Runtime runtime = Runtime.getRuntime();
        //se prepara File para con la ruta
        File backup = new File("src/backup");
        // se crea la ruta, si no existe crea la ruta completa
        backup.mkdirs();
        //preparamos el backupfile con la ruta
        //y el archivo sql que tendra la fecha y hora de creacion
        File backupFile = new File("src/backup/backup" + "_" + fecha + ".sql");
         
        try {
             
            InputStreamReader irs;
            BufferedReader br;
            //preparamos el filewrite para guardar el backup
            FileWriter filewrite = new FileWriter(backupFile);
            //ruta donde se encuntra mysqldump
            String patch = "C:\\wamp64\\bin\\mysql\\mysql5.7.24\\bin\\";
            //se lanza el DOS
            Process child = runtime.exec(patch
                    + "mysqldump "
                    + " --opt "
                    + " --user=" + user
                    + " --password=" + pass
                    + " --databases " + database + " -R");
             
            // Se obtiene el stream de salida del programa 
            irs = new InputStreamReader(child.getInputStream());
            // Se prepara un BufferedReader para poder leer la salida más comodamente. 
            br = new BufferedReader(irs);
             
            String line;
            //mientras exista una linea
            while ((line = br.readLine()) != null) {
                filewrite.write(line + "\n");
            }
            // terminamos los servicios
            irs.close();
            br.close();
             
        } catch (IOException e) {
            System.out.println("Error.. No se realizo el backup!");
        }
        */
    }
    
    public  void logear (){
        user.set_id(null);
        user.set_nombre(null);
        user.set_privilegios(0);
        configuracion.setVisible(false);
        
        
        loggin logg= new loggin(this, true, user);
        logg.setVisible(true);
        user= logg.get_user();
        
        if(user.get_id()!=null){
            JOptionPane.showMessageDialog(null,"a iniciado sesion:  "+user.get_nombre());
            if(user.get_privilegios()==1 || user.get_privilegios()==2){
                configuracion.setVisible(true);
            }
        }
        else{
            reloj.stop();
            dispose();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    
                String ip="localhost";
                        
                try {
                    File ruta = new File ("C:\\cinema");
                    if(!ruta.exists()){
                        ruta.mkdir();
                        
                        new PrintStream(new File(ruta+"\\ip.txt")).println(ip);

                        ruta = new File ("C:\\cinema\\peliculas");
                        ruta.mkdir();
                    }
                    File archivo = new File ("C:\\cinema\\ip.txt");
                    FileReader fr;
                    fr = new FileReader (archivo);
                    BufferedReader br = new BufferedReader(fr);
                    String linea = br.readLine();
                    
                    ip=linea;
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }

                usuario u = new usuario();
                u.set_ip(ip);
                
                /* borra es para pruebas
                u.set_id("1");
                u.set_nombre("admin");
                u.set_privilegios(1);
                */
                
                Inicio inicio = new Inicio(u);
                inicio.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                inicio.setResizable(false);
                inicio.setVisible(true); 

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GEARSOFT;
    private javax.swing.JButton configuracion;
    private javax.swing.JButton gafas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel logo1;
    private javax.swing.JLabel logo2;
    private cinema.reloj reloj;
    // End of variables declaration//GEN-END:variables
}
