/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.admin;

import cinema.administrador;
import clases.conectar;
import clases.tablaimagen;
import clases.usuario;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WILLIAN
 */
public class tarjetas_admin extends javax.swing.JPanel {

    conectar cc_tarjetas =new conectar();
    Connection con_tarjetas;
    
    usuario user = new usuario();
    boolean add =false;
    
    DefaultTableModel modelo_tarjeta;
    
    ImageIcon icono_activa;
    ImageIcon icono_inactiva;
    ImageIcon ruta;
        
    public tarjetas_admin(usuario us) {
        user=us;
        initComponents();
        editar.setVisible(false);
        agregar.setVisible(false);
        nombre_add.setEditable(false);
        cedula_add.setEditable(false);
        correo_add.setEditable(false);
        telefono_add.setEditable(false);
        
        estado_add.setEnabled(false);
                
        codigo_add.requestFocus();
        
        modelo_tarjeta =  (DefaultTableModel) lista_tarjetas.getModel();
        lista_tarjetas.setDefaultRenderer(Object.class, new tablaimagen());
        
        ruta= new javax.swing.ImageIcon(getClass().getResource("/imagenes/ok.png"));
        icono_activa = new ImageIcon(ruta.getImage().getScaledInstance(15,15, 0));

        ruta= new javax.swing.ImageIcon(getClass().getResource("/imagenes/mal.png"));
        icono_inactiva = new ImageIcon(ruta.getImage().getScaledInstance(15,15, 0));
        
    }
    public void cargar(){
        String estado;
        
        int a =modelo_tarjeta.getRowCount();
        for(int i=0; i<a;i++){
            modelo_tarjeta.removeRow(0);
        }
        
        try {
            con_tarjetas= cc_tarjetas.conexion(user.get_ip());
            Statement st = con_tarjetas.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM tarjetas ORDER BY id DESC");
            
            while(result.next()){
                if(result.getString(7).equals("1")){estado="SI";}
                else {estado="NO";}
                
                Object[] datosDeLaFila= new Object [8];
                
                datosDeLaFila[0]=result.getString(2);
                datosDeLaFila[1]=result.getString(3);
                datosDeLaFila[2]=result.getString(4);
                datosDeLaFila[3]=result.getString(5);
                datosDeLaFila[4]=result.getString(6);
                datosDeLaFila[5]=estado;
                datosDeLaFila[6]=new JButton("ver");
                datosDeLaFila[7]=new JButton("edit");
               
                modelo_tarjeta.addRow(datosDeLaFila);
            }
            lista_tarjetas.setModel(modelo_tarjeta);
            
            con_tarjetas.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 64  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        codigo_add = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        estado_add = new javax.swing.JRadioButton();
        nombre_add = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cancelar_add = new javax.swing.JButton();
        agregar = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cedula_add = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        correo_add = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        telefono_add = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_tarjetas = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        codigo_add.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        codigo_add.setToolTipText("");
        codigo_add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigo_addKeyPressed(evt);
            }
        });
        jPanel1.add(codigo_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 190, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("AGREGAR-EDITAR TARJETAS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 350, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Haga CLICK en el cuadro para leer tarjeta");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 70, 280, -1));

        estado_add.setSelected(true);
        estado_add.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        estado_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mal.png"))); // NOI18N
        estado_add.setIconTextGap(0);
        estado_add.setRequestFocusEnabled(false);
        estado_add.setRolloverEnabled(false);
        estado_add.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ok.png"))); // NOI18N
        jPanel1.add(estado_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, -1, -1));

        nombre_add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombre_addKeyTyped(evt);
            }
        });
        jPanel1.add(nombre_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 260, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("NOMBRE");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 150, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ESTADO");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 100, 30));

        cancelar_add.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cancelar_add.setText("CANCELAR");
        cancelar_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelar_addActionPerformed(evt);
            }
        });
        jPanel1.add(cancelar_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 500, -1, 40));

        agregar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        agregar.setText("AGREGAR");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        jPanel1.add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 120, 40));

        editar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        editar.setText("EDITAR");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        jPanel1.add(editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 120, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CEDULA");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 150, 30));

        cedula_add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cedula_addKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cedula_addKeyTyped(evt);
            }
        });
        jPanel1.add(cedula_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 260, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("CORREO");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 150, 30));

        correo_add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                correo_addKeyTyped(evt);
            }
        });
        jPanel1.add(correo_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 260, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("TELEFONO");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 150, 30));

        telefono_add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefono_addKeyTyped(evt);
            }
        });
        jPanel1.add(telefono_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 260, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 400, 560));

        jPanel3.setBackground(new java.awt.Color(153, 255, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lista_tarjetas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lista_tarjetas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° TARJETA", "NOMBRE", "CEDULA", "CORREO", "TELEFONO", "ACTIVA", "PUNTOS", "EDIT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista_tarjetas.setRowHeight(20);
        lista_tarjetas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_tarjetasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lista_tarjetas);
        if (lista_tarjetas.getColumnModel().getColumnCount() > 0) {
            lista_tarjetas.getColumnModel().getColumn(0).setResizable(false);
            lista_tarjetas.getColumnModel().getColumn(0).setPreferredWidth(120);
            lista_tarjetas.getColumnModel().getColumn(1).setResizable(false);
            lista_tarjetas.getColumnModel().getColumn(1).setPreferredWidth(180);
            lista_tarjetas.getColumnModel().getColumn(2).setResizable(false);
            lista_tarjetas.getColumnModel().getColumn(2).setPreferredWidth(80);
            lista_tarjetas.getColumnModel().getColumn(3).setResizable(false);
            lista_tarjetas.getColumnModel().getColumn(3).setPreferredWidth(120);
            lista_tarjetas.getColumnModel().getColumn(4).setResizable(false);
            lista_tarjetas.getColumnModel().getColumn(4).setPreferredWidth(80);
            lista_tarjetas.getColumnModel().getColumn(5).setResizable(false);
            lista_tarjetas.getColumnModel().getColumn(5).setPreferredWidth(50);
            lista_tarjetas.getColumnModel().getColumn(6).setResizable(false);
            lista_tarjetas.getColumnModel().getColumn(6).setPreferredWidth(50);
            lista_tarjetas.getColumnModel().getColumn(7).setResizable(false);
            lista_tarjetas.getColumnModel().getColumn(7).setPreferredWidth(50);
        }

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 750, 470));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel6.setText("LISTA DE TARJETAS");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 220, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 810, 540));
    }// </editor-fold>//GEN-END:initComponents

    private void agregar(){
        try {
            con_tarjetas= cc_tarjetas.conexion(user.get_ip());
            String sql="INSERT INTO tarjetas  (numero,nombre,cedula,correo,telefono,estado) VALUES (?,?,?,?,?,?)";
            
            PreparedStatement pst= con_tarjetas.prepareStatement(sql);
            pst.setString(1,codigo_add.getText());
            pst.setString(2,nombre_add.getText());
            pst.setString(3,cedula_add.getText());
            pst.setString(4,correo_add.getText());
            pst.setString(5,telefono_add.getText());
                  
            if(estado_add.isSelected()){pst.setString(6,"1");}
            else{pst.setString(6,"0");}
                        
            pst.executeUpdate();
            
            codigo_add.setEditable(true);
            codigo_add.setText("");
            nombre_add.setText("");
            cedula_add.setText("");
            correo_add.setText("");
            telefono_add.setText("");
            estado_add.setSelected(true);
            codigo_add.requestFocus();
            agregar.setVisible(false);
            nombre_add.setEditable(false);
            cedula_add.setEditable(false);
            correo_add.setEditable(false);
            telefono_add.setEditable(false);
            estado_add.setEnabled(false);
            add=false;
            
            con_tarjetas.close();
            cargar();
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1062){
                JOptionPane.showMessageDialog(null,"La cedula ya esta registrada ","Error",JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null,"error 61:  "+ex);
            }
        }
    }   
    
    private void codigo_addKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigo_addKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER && codigo_add.getText().length()>10 ){
            try {
                con_tarjetas= cc_tarjetas.conexion(user.get_ip());
                Statement st = con_tarjetas.createStatement();
                ResultSet result =st.executeQuery("SELECT * FROM tarjetas WHERE numero='"+codigo_add.getText()+"'");

                if(result.next()){///   editra tarjetas
                    editar.setVisible(true);
                    codigo_add.setEditable(true);
                    nombre_add.setEditable(true);
                    cedula_add.setEditable(true);
                    correo_add.setEditable(true);
                    telefono_add.setEditable(true);
                    estado_add.setEnabled(true);
                    
                    
                    nombre_add.setText(result.getString(3));
                    cedula_add.setText(result.getString(4));
                    correo_add.setText(result.getString(5));
                    telefono_add.setText(result.getString(6));
                    if(result.getInt(7)==1){estado_add.setSelected(true);}
                    else{estado_add.setSelected(false);}
                    
                    nombre_add.requestFocus();
                }
                else{///agragar tarjeta
                    add=true;
                    agregar.setVisible(true);
                    codigo_add.setEditable(false);
                    nombre_add.setEditable(true);
                    cedula_add.setEditable(true);
                    correo_add.setEditable(true);
                    telefono_add.setEditable(true);
                    estado_add.setEnabled(true);
                    nombre_add.requestFocus();
                    
                }
                con_tarjetas.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error 60:  "+ex);
                Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_codigo_addKeyPressed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        agregar();
    }//GEN-LAST:event_agregarActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        try {
            con_tarjetas= cc_tarjetas.conexion(user.get_ip());
            String sql="UPDATE tarjetas  SET nombre=? ,cedula=? ,correo=? ,telefono=? ,estado=?  WHERE numero='"+codigo_add.getText()+"'";
            
            PreparedStatement pst= con_tarjetas.prepareStatement(sql);
            pst.setString(1,nombre_add.getText());
            pst.setString(2,cedula_add.getText());
            pst.setString(3,correo_add.getText());
            pst.setString(4,telefono_add.getText());
            if(estado_add.isSelected()){pst.setString(5,"1");}
            else{pst.setString(5,"0");}
                        
            pst.executeUpdate();
            
            codigo_add.setEditable(true);
            codigo_add.setText("");
            nombre_add.setText("");
            cedula_add.setText("");
            telefono_add.setText("");
            correo_add.setText("");
            
            estado_add.setSelected(true);
            codigo_add.requestFocus();
            editar.setVisible(false);
            nombre_add.setEditable(false);
            cedula_add.setEditable(false);
            correo_add.setEditable(false);
            telefono_add.setEditable(false);
            estado_add.setEnabled(false);
            
            con_tarjetas.close();
            cargar();
        } catch (SQLException ex) {
            if(ex.getErrorCode()==1062){
                JOptionPane.showMessageDialog(null,"La cedula ya esta registrada ","Error",JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null,"error 61:  "+ex);
            }
        }
    }//GEN-LAST:event_editarActionPerformed

    private void cancelar_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelar_addActionPerformed
        estado_add.setSelected(true);
        codigo_add.setEditable(true);
        codigo_add.setText("");
        nombre_add.setText("");
        cedula_add.setText("");
        correo_add.setText("");
        telefono_add.setText("");
        
        codigo_add.requestFocus();
        editar.setVisible(false);
        agregar.setVisible(false);
        nombre_add.setEditable(false);
        estado_add.setEnabled(false);
        add=false;
    }//GEN-LAST:event_cancelar_addActionPerformed

    private void nombre_addKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_addKeyTyped
        if(nombre_add.getText().length()>19)evt.consume(); 
    }//GEN-LAST:event_nombre_addKeyTyped

    private void cedula_addKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedula_addKeyTyped
        if(cedula_add.getText().length()>15)evt.consume();
        
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_cedula_addKeyTyped

    private void correo_addKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_correo_addKeyTyped
        if(correo_add.getText().length()>39)evt.consume();
    }//GEN-LAST:event_correo_addKeyTyped

    private void telefono_addKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefono_addKeyTyped
        if(telefono_add.getText().length()>15)evt.consume();
        
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_telefono_addKeyTyped

    private void cedula_addKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedula_addKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER && codigo_add.getText().length()>10 && cedula_add.getText().length()>6 &&add){
            agregar();
        }
    }//GEN-LAST:event_cedula_addKeyPressed

    private void lista_tarjetasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_tarjetasMouseClicked
        // TODO add your handling code here:
        String num_tarjeta = (String) lista_tarjetas.getValueAt(lista_tarjetas.rowAtPoint(evt.getPoint()), 0);
        String nombre_tarjeta = (String) lista_tarjetas.getValueAt(lista_tarjetas.rowAtPoint(evt.getPoint()), 1);
            
        if(lista_tarjetas.columnAtPoint(evt.getPoint())==6){
            try {
                con_tarjetas= cc_tarjetas.conexion(user.get_ip());
                Statement st = con_tarjetas.createStatement();
                ResultSet result =st.executeQuery("SELECT SUM(v.costo)/1000 FROM ventas As v, tarjetas AS t WHERE v.tarjeta_id=t.id AND t.numero='"+num_tarjeta+"'");
                
                if(result.next()){
                    JOptionPane.showMessageDialog(null,nombre_tarjeta+" tiene "+result.getInt(1)+" puntos ","Puntos",JOptionPane.INFORMATION_MESSAGE);
                }
                
                con_tarjetas.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(tarjetas_admin.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        else if (lista_tarjetas.columnAtPoint(evt.getPoint())==7){
            try {
                con_tarjetas= cc_tarjetas.conexion(user.get_ip());
                Statement st = con_tarjetas.createStatement();
                ResultSet result =st.executeQuery("SELECT * FROM tarjetas WHERE numero='"+num_tarjeta+"'");

                if(result.next()){///   editra tarjetas
                    editar.setVisible(true);
                    codigo_add.setEditable(true);
                    nombre_add.setEditable(true);
                    cedula_add.setEditable(true);
                    correo_add.setEditable(true);
                    telefono_add.setEditable(true);
                    estado_add.setEnabled(true);
                    
                    codigo_add.setText(result.getString(2));
                    nombre_add.setText(result.getString(3));
                    cedula_add.setText(result.getString(4));
                    correo_add.setText(result.getString(5));
                    telefono_add.setText(result.getString(6));
                    if(result.getInt(7)==1){estado_add.setSelected(true);}
                    else{estado_add.setSelected(false);}
                    
                    nombre_add.requestFocus();
                }
                
                con_tarjetas.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error 60:  "+ex);
                Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lista_tarjetasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton cancelar_add;
    private javax.swing.JTextField cedula_add;
    private javax.swing.JTextField codigo_add;
    private javax.swing.JTextField correo_add;
    private javax.swing.JButton editar;
    private javax.swing.JRadioButton estado_add;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable lista_tarjetas;
    private javax.swing.JTextField nombre_add;
    private javax.swing.JTextField telefono_add;
    // End of variables declaration//GEN-END:variables
}
