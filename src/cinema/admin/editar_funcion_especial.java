/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.admin;

import cinema.administrador;
import clases.conectar;
import clases.funcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author WILLIAN
 */
public class editar_funcion_especial extends javax.swing.JDialog {

    /**
     * Creates new form editar_funcion_especial
     */
    conectar cc =new conectar();
    Connection con;
    
    funcion f = new funcion();
    String ampm="AM";
    char hora[];
    int h;
    String id_funcion;
    
    public editar_funcion_especial(java.awt.Frame parent, boolean modal, String ip, String id) {
        super(parent, modal);
        con = cc.conexion(ip);
        initComponents();
        this.setLocationRelativeTo(null);
        id_funcion=id;
        cargar();
        
    }

    
    public void cargar(){
        
        try {
            Statement st = con.createStatement();
            ResultSet result =st.executeQuery("SELECT * FROM funciones WHERE id='"+id_funcion+"'");

            while(result.next()){
                
                hora=result.getString(3).toCharArray();
                h=Integer.parseInt(""+hora[0]+hora[1]);
                if(h==0 || h==12){
                    h=h+12;
                }
                if(h>12){
                    h=h-12;
                    ampm="PM";
                }
                

                f.set_id(result.getString(1));
                f.set_sala(result.getInt(2));
                if(h<10){f.set_hora("0"+h);}
                else{f.set_hora(""+h);}
                f.set_minutos(""+hora[3]+hora[4]);
                f.set_ampm(ampm);
                f.set_pelicula_id(result.getString(7));
                f.set_fecha(result.getDate(5));
                f.set_precio(result.getInt(6));

                hora_funcion.setSelectedItem(f.get_hora());
                minutos_funcion.setSelectedItem(f.get_minutos());
                ampm_funcion.setSelectedItem(f.get_ampm());
                sala_funcion.setSelectedIndex(f.get_sala()-1);
                fecha_funcion.setDate(f.get_fecha());
                valor_funcion_especial.setText(""+f.get_precio());
                

            }               
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 20:  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hora_funcion = new javax.swing.JComboBox<String>();
        jLabel5 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        minutos_funcion = new javax.swing.JComboBox<String>();
        ampm_funcion = new javax.swing.JComboBox<String>();
        sala_funcion = new javax.swing.JComboBox<String>();
        jLabel6 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        fecha_funcion = new com.toedter.calendar.JDateChooser();
        valor_funcion_especial = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        actualizar = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        hora_funcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hora_funcion.setMaximumRowCount(12);
        hora_funcion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("HORAS");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("MINUTOS");

        minutos_funcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        minutos_funcion.setMaximumRowCount(12);
        minutos_funcion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));

        ampm_funcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ampm_funcion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));
        ampm_funcion.setSelectedIndex(1);

        sala_funcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sala_funcion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sala 1", "Sala 2", "Sala 3" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("SALA");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setText("FECHA");

        valor_funcion_especial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valor_funcion_especialKeyTyped(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("VALOR");

        actualizar.setText("ACTUALIZAR");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel20.setText("EDITAR FUNCION");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(hora_funcion, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(minutos_funcion, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ampm_funcion, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel31)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(sala_funcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(actualizar)
                            .addGap(50, 50, 50))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fecha_funcion, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valor_funcion_especial, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel20))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hora_funcion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minutos_funcion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ampm_funcion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(sala_funcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fecha_funcion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valor_funcion_especial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(actualizar)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void valor_funcion_especialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valor_funcion_especialKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_valor_funcion_especialKeyTyped

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        // TODO add your handling code here:
        String hora="";
        int h = Integer.parseInt((String)hora_funcion.getSelectedItem());
        
        String dia,mes,ano,fecha;
        dia= Integer.toString(fecha_funcion.getCalendar().get(Calendar.DAY_OF_MONTH));
        mes= Integer.toString(fecha_funcion.getCalendar().get(Calendar.MONTH)+1);
        ano= Integer.toString(fecha_funcion.getCalendar().get(Calendar.YEAR));
        fecha=ano+"-"+mes+"-"+dia;
        
        if(h==12){
            h=h-12;
        }
        if(ampm_funcion.getSelectedItem().equals("PM")){
            h=h+12;
        }
        hora=h+":"+minutos_funcion.getSelectedItem();
                
        String sql;

            sql="UPDATE funciones  SET sala=?,hora=?, fecha=?, precio=? WHERE id='"+id_funcion+"' ";
            try {
                PreparedStatement pst= con.prepareStatement(sql);
                pst.setInt(1,(sala_funcion.getSelectedIndex()+1));
                pst.setString(2,hora);
                pst.setString(3,fecha);
                pst.setString(4,valor_funcion_especial.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null,"Se actualizo con exito");
                               
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"error 11:  "+ex);
                Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error cerrando conexion:  "+ex.getMessage());
        }
        dispose(); 
    }//GEN-LAST:event_actualizarActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JComboBox<String> ampm_funcion;
    private com.toedter.calendar.JDateChooser fecha_funcion;
    private javax.swing.JComboBox<String> hora_funcion;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JComboBox<String> minutos_funcion;
    private javax.swing.JComboBox<String> sala_funcion;
    private javax.swing.JTextField valor_funcion_especial;
    // End of variables declaration//GEN-END:variables
}
