/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import clases.usuario;
import clases.conectar;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author XIMENA
 */
public class loggin extends javax.swing.JDialog {

    
    conectar cc =new conectar();
    Connection con;
    usuario user = new usuario();
    
    public loggin(java.awt.Frame parent, boolean modal, usuario us) {
        super(parent, modal);
        user = us;
        
        this.setUndecorated(true);
        
        initComponents();
        this.setLocationRelativeTo(null);
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inicio_sesion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        contrasena = new javax.swing.JPasswordField();
        cerrar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setMaximumSize(new java.awt.Dimension(550, 300));
        setMinimumSize(new java.awt.Dimension(550, 300));

        inicio_sesion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        inicio_sesion.setText("INICIAR SESION");
        inicio_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicio_sesionActionPerformed(evt);
            }
        });
        inicio_sesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inicio_sesionKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("USUARIO");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("CONTRASEÑA");

        usuario.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        usuario.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usuarioMouseClicked(evt);
            }
        });
        usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuarioKeyPressed(evt);
            }
        });

        contrasena.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        contrasena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contrasenaMouseClicked(evt);
            }
        });
        contrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                contrasenaKeyPressed(evt);
            }
        });

        cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mal.png"))); // NOI18N
        cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(contrasena, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(usuario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(185, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(inicio_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(143, 143, 143))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cerrar)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(inicio_sesion)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inicio_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicio_sesionActionPerformed
        // TODO add your handling code here:
        validar();
        //dispose();
    }//GEN-LAST:event_inicio_sesionActionPerformed

    private void usuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            contrasena.requestFocus();
        }
    }//GEN-LAST:event_usuarioKeyPressed

    private void contrasenaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contrasenaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER ){
            validar();
        }
    }//GEN-LAST:event_contrasenaKeyPressed

    private void usuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioMouseClicked
        // TODO add your handling code here:
        usuario.requestFocus();
    }//GEN-LAST:event_usuarioMouseClicked

    private void contrasenaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contrasenaMouseClicked
        // TODO add your handling code here:
        contrasena.requestFocus();
    }//GEN-LAST:event_contrasenaMouseClicked

    private void inicio_sesionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inicio_sesionKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER ){
            validar();
        }
    }//GEN-LAST:event_inicio_sesionKeyPressed

    private void cerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cerrarMouseClicked
    
    private void validar(){
        boolean existe=false;
        try {
            con = cc.conexion(user.get_ip());
            Statement st = con.createStatement();
            ResultSet result =st.executeQuery("SELECT * FROM `usuarios` WHERE nombre='"+usuario.getText()+"'");
            
            while(result.next()){
                existe = true;
                if(contrasena.getText().equals(result.getString(3))){
                    user.set_id(result.getString(1));
                    user.set_nombre(result.getString(2));
                    user.set_privilegios(Integer.parseInt(result.getString(4)));
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(this," Contraseña incorrecta ", "error",JOptionPane.ERROR_MESSAGE);
                    contrasena.requestFocus();
                    contrasena.setText("");
                }
            }    
            if(!existe){
                JOptionPane.showMessageDialog(this," usuario no encontrado ", "error",JOptionPane.ERROR_MESSAGE);
                usuario.requestFocus();
                usuario.setText("");
                contrasena.setText("");
            }
          
            try {
                con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error cerrando conexion:  "+ex.getMessage());
            }
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"error 25:  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public usuario get_user(){
        return user;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cerrar;
    private javax.swing.JPasswordField contrasena;
    private javax.swing.JButton inicio_sesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
