/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.admin;

import clases.conectar;
import clases.pelicula;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WILLIANC
 */
public class reporte_gafas extends javax.swing.JDialog {

    conectar cc =new conectar();
    Connection con;
    String ip;
    DefaultTableModel modelo_reporte_gafas;
    
    
    public reporte_gafas(java.awt.Frame parent, boolean modal, String ip1) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        ip=ip1;
        
               
        modelo_reporte_gafas =  (DefaultTableModel) lista_venta_gafas.getModel();
        
        cargar("limit 30");
                
    }

    public void cargar(String limite){
        
        con = cc.conexion(ip);
        
        int a =modelo_reporte_gafas.getRowCount();
        for(int i=0; i<a;i++){
            modelo_reporte_gafas.removeRow(0);
        }
        
        try {
            Statement st = con.createStatement();
                ResultSet result =st.executeQuery("SELECT DATE(fecha), SUM(cantidad), SUM(valor)  FROM gafas GROUP BY DATE(fecha) ORDER BY DATE(fecha) DESC  "+limite+" ");

            while(result.next()){
                pelicula p = new pelicula();

                Object[] datosDeLaFila= new Object [3];

                datosDeLaFila[0]=result.getString(1);
                datosDeLaFila[1]=result.getString(2);   
                datosDeLaFila[2]=result.getInt(3);

                modelo_reporte_gafas.addRow(datosDeLaFila);
               lista_venta_gafas.setModel(modelo_reporte_gafas);
            }  
            
            
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 44:  "+ex);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_venta_gafas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel2.setText("REPORTE VENTA GAFAS");

        lista_venta_gafas.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lista_venta_gafas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "CANTIDAD", "VALOR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista_venta_gafas.setRowHeight(22);
        jScrollPane1.setViewportView(lista_venta_gafas);
        if (lista_venta_gafas.getColumnModel().getColumnCount() > 0) {
            lista_venta_gafas.getColumnModel().getColumn(0).setResizable(false);
            lista_venta_gafas.getColumnModel().getColumn(1).setResizable(false);
            lista_venta_gafas.getColumnModel().getColumn(2).setResizable(false);
        }

        jButton1.setText("Ver mas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(215, 215, 215))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cargar("");
    }//GEN-LAST:event_jButton1ActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable lista_venta_gafas;
    // End of variables declaration//GEN-END:variables
}