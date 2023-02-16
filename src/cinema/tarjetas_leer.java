/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import clases.conectar;
import clases.tarjeta;
import clases.usuario;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author WILLIAN
 */
public class tarjetas_leer extends javax.swing.JDialog {

    conectar cc_tarjetas =new conectar();
    Connection con_tarjetas;
    tarjeta tarjeta = new tarjeta();
    usuario user = new usuario();
    Calendar fe;
    String tipo;
    int franja;
    
    public tarjetas_leer(java.awt.Frame parent, boolean modal,usuario us, Calendar fec, String t, int f) {
        super(parent, modal);
        user=us;
        fe=fec;
        tipo = t;
        franja = f;
        initComponents();
        codigo.requestFocus();
        
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Haga CLICK en el cuadro para leer tarjeta");

        codigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        codigo.setToolTipText("");
        codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("LEER TARJETA");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icono_tarjeta.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icono_tarjeta.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void codigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER && codigo.getText().length()>10 ){
            try {
                con_tarjetas= cc_tarjetas.conexion(user.get_ip());
                Statement st = con_tarjetas.createStatement();
                ResultSet result =st.executeQuery("SELECT tarjetas.id, tarjetas.numero, tarjetas.nombre, tarjetas.estado, SUM(ventas.costo) AS puntos\n" +
                                                    "FROM tarjetas \n" +
                                                    "LEFT JOIN ventas on tarjetas.id=ventas.tarjeta_id\n" +
                                                    "WHERE tarjetas.numero='"+codigo.getText()+"'");

                if(result.next()){///TARJETA SI EXISTE
                    tarjeta.set_id(result.getString(1));
                    tarjeta.set_numero(result.getString(2));
                    tarjeta.set_nombre(result.getString(3));
                    tarjeta.set_estado(result.getBoolean(4));
                    tarjeta.set_puntos(result.getInt(5)/1000);
                    
                    result =st.executeQuery("SELECT tarjeta, tarjeta3d, pref_tarjeta2d, pref_tarjeta3d, dosxuno FROM precios where dia='"+fe.get(Calendar.DAY_OF_WEEK)+"' AND franja="+franja+" ");
                    result.next();                 
                    if(tipo.equals("2D")){
                        tarjeta.set_precio_general(result.getInt(1));
                        tarjeta.set_precio_preferencial(result.getInt(3)); 
                    }else{
                        tarjeta.set_precio_general(result.getInt(2));
                        tarjeta.set_precio_preferencial(result.getInt(4));
                    }
                    
                    if(result.getBoolean(5)){
                       tarjeta.set_precio_general(tarjeta.get_precio_general()/2);
                       tarjeta.set_precio_preferencial(tarjeta.get_precio_preferencial()/2);
                    }
                    
                    dispose(); 
                }
                else{///NO EXISTE TARJETA
                    JOptionPane.showMessageDialog(null,"Numero de tarjeta no existe","error",JOptionPane.ERROR_MESSAGE);
                    codigo.requestFocus();
                }
                con_tarjetas.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error 62:  "+ex);
                Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_codigoKeyPressed

    public tarjeta get_tarjeta(){
        return tarjeta;
    }
    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codigo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
