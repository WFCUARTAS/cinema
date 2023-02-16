/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import clases.conectar;
import clases.convenio;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author XIMENA
 */
public class convenios extends javax.swing.JDialog {

    conectar cc =new conectar();
    Connection con;
    String id = null;
    int val=0;
    int val3d=0;
    
    ArrayList<convenio> convenios =new ArrayList<convenio>();
 
    
    public convenios(java.awt.Frame parent, boolean modal, String ip) {
        super(parent, modal);
        con = cc.conexion(ip);
        initComponents();
        listar();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lista_convenios = new javax.swing.JComboBox<String>();
        seleccionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("convenios");
        setMaximumSize(new java.awt.Dimension(310, 200));
        setMinimumSize(new java.awt.Dimension(310, 200));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("CONVENIO");

        lista_convenios.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lista_convenios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lista_conveniosKeyPressed(evt);
            }
        });

        seleccionar.setText("SELECCIONAR");
        seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lista_convenios, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(seleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lista_convenios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(seleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarActionPerformed
        // TODO add your handling code here:
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error cerrando conexion:  "+ex.getMessage());
        }
        
        int selec =lista_convenios.getSelectedIndex();
        id=convenios.get(selec).get_id();
        val=Integer.parseInt(convenios.get(selec).get_valor());
        val3d=Integer.parseInt(convenios.get(selec).get_valor3d());
        dispose();        
    }//GEN-LAST:event_seleccionarActionPerformed

    private void lista_conveniosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lista_conveniosKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER ){
            try {
                con.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error cerrando conexion:  "+ex.getMessage());
            }

            int selec =lista_convenios.getSelectedIndex();
            id=convenios.get(selec).get_id();
            val=Integer.parseInt(convenios.get(selec).get_valor());
            dispose();
        }
    }//GEN-LAST:event_lista_conveniosKeyPressed

    public void listar(){
        
        try {
            Statement st = con.createStatement();
            ResultSet result =st.executeQuery("SELECT * FROM `convenios` WHERE guarda=1 ORDER BY id ASC");
            while(result.next()){
                convenio c= new convenio();
                c.set_id(result.getString(1));
                c.set_nombre(result.getString(2));
                c.set_valor(result.getString(3));
                c.set_valor3d(result.getString(6));
                lista_convenios.addItem(result.getString(2));
                convenios.add(c);
            }  

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error  23:  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public int valor_total(){
        return val;
    }
    public String get_id(){
        return id;
    }
    public int valor_total3d(){
        return val3d;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> lista_convenios;
    private javax.swing.JButton seleccionar;
    // End of variables declaration//GEN-END:variables
}
