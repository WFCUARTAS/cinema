/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import clases.tarjeta;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class confirmar_venta extends javax.swing.JDialog {

    private boolean validar = false;
    int valor, valor_total;
    Calendar fec=Calendar.getInstance();;
    String fecha,fecha_venta;
    int gafas;
    boolean incluir_gafa=true;
    
    public confirmar_venta(java.awt.Frame parent, boolean modal, String c,int v, Calendar fe, int g, tarjeta t) {
        super(parent, modal);
        initComponents();
        int tam=0;
        fecha=fec.get(Calendar.YEAR)+"-"+(fec.get(Calendar.MONTH)+1)+"-"+fec.get(Calendar.DAY_OF_MONTH);
        fec=fe;
        fecha_venta=fec.get(Calendar.YEAR)+"-"+(fec.get(Calendar.MONTH)+1)+"-"+fec.get(Calendar.DAY_OF_MONTH);
        
        if(!fecha.equals(fecha_venta)){
            mostrar_fecha.setText("FECHA   "+fecha_venta);
            panel_fecha.setBackground(new Color(255,0,0));
        }
        else{
            panel_fecha.setVisible(false);
            tam=tam+55;
        }
        compra.setText("<html>"+c+"<html>");
        valor=v;
        gafas=g;
        valor_total=valor+gafas;
        precio.setText("$ "+valor);
        precio_gafas.setText("$ "+gafas);
        precio_total.setText("$ "+valor_total);
        
        efectivo.requestFocus();
        
        if(gafas==0){
            jLabel6.setVisible(false);
            precio_gafas.setVisible(false);
            incluir.setVisible(false);
            jPanel1.setVisible(false);
            jLabel7.setVisible(false);
            precio_total.setVisible(false); 
            incluir_gafa=false;
            tam=tam+70;
        }
        
        if(t.get_id()==null){
            panel_cliente.setVisible(false);
            tam=tam+70;
        }
        else{
            cliente.setText(t.get_nombre());
            puntos.setText(""+t.get_puntos());
        }
        this.setSize(540, (580-tam));
        this.setLocationRelativeTo(null);
        
    }

    public boolean es_true(){
        return validar;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        compra = new javax.swing.JLabel();
        cambio = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panel_fecha = new javax.swing.JPanel();
        mostrar_fecha = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        precio = new javax.swing.JLabel();
        efectivo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        precio_gafas = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        precio_total = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        incluir = new javax.swing.JCheckBox();
        confirmar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        panel_cliente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cliente = new javax.swing.JLabel();
        puntos = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("confirmar venta");
        setMinimumSize(new java.awt.Dimension(300, 300));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("CONFIRMA LA COMPRA DE LAS BOLETAS");

        compra.setBackground(new java.awt.Color(255, 255, 255));
        compra.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        compra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        compra.setText("   A1   A2   A3   B4");

        cambio.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        cambio.setText(" ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("PRECIO BOLETAS: ");

        mostrar_fecha.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        mostrar_fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mostrar_fecha.setText("2017-05-06");

        javax.swing.GroupLayout panel_fechaLayout = new javax.swing.GroupLayout(panel_fecha);
        panel_fecha.setLayout(panel_fechaLayout);
        panel_fechaLayout.setHorizontalGroup(
            panel_fechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_fechaLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(mostrar_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_fechaLayout.setVerticalGroup(
            panel_fechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_fechaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mostrar_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("EFECTIVO: ");
        jLabel4.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("CAMBIO: ");

        precio.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        precio.setText("00000");

        efectivo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        efectivo.setToolTipText("");
        efectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                efectivoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                efectivoKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("PRECIO GAFAS: ");

        precio_gafas.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        precio_gafas.setText("00000");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("TOTAL: ");

        precio_total.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        precio_total.setText("00000");

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        incluir.setSelected(true);
        incluir.setText("Incluir");
        incluir.setToolTipText("");
        incluir.setAlignmentX(0.5F);
        incluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incluirActionPerformed(evt);
            }
        });

        confirmar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        confirmar.setText("confirmar");
        confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarActionPerformed(evt);
            }
        });
        confirmar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                confirmarKeyPressed(evt);
            }
        });

        cancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelar.setText("cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        panel_cliente.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("CLIENTE");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setText("PUNTOS");

        cliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cliente.setText("jLabel10");

        puntos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        puntos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        puntos.setText("jLabel10");

        javax.swing.GroupLayout panel_clienteLayout = new javax.swing.GroupLayout(panel_cliente);
        panel_cliente.setLayout(panel_clienteLayout);
        panel_clienteLayout.setHorizontalGroup(
            panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_clienteLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(puntos, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(panel_clienteLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        panel_clienteLayout.setVerticalGroup(
            panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_clienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(puntos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(jLabel5)
                .addGap(25, 25, 25)
                .addComponent(cambio, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(compra, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(precio_total, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(precio_gafas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(incluir))
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(panel_fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_cliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6)
                        .addComponent(compra, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precio))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precio_gafas)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(incluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precio_total))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cambio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(panel_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarActionPerformed
        // TODO add your handling code here:
        validar=true;
        dispose();
    }//GEN-LAST:event_confirmarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        validar=false;
        dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private void efectivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_efectivoKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER ){
            if(efectivo.getText().length()>0){
                cambio.setText("$ "+ (Integer.parseInt(efectivo.getText())-valor_total));
            }
            confirmar.requestFocus();
        }
    }//GEN-LAST:event_efectivoKeyPressed

    private void confirmarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confirmarKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER ){
            validar=true;
            dispose();
        }
    }//GEN-LAST:event_confirmarKeyPressed

    private void efectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_efectivoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_efectivoKeyTyped

    private void incluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incluirActionPerformed
        // TODO add your handling code here:
        if(incluir.isSelected()){
            incluir_gafa=true;
            valor_total=valor+gafas;
            precio_total.setText("$ "+valor_total);
        }
        else{
            incluir_gafa=false;
            valor_total=valor;
            precio_total.setText("$ "+valor_total);
        }
        if(efectivo.getText().length()>0){
            cambio.setText("$ "+ (Integer.parseInt(efectivo.getText())-valor_total));
        }
    }//GEN-LAST:event_incluirActionPerformed

    public boolean incluir_garfas(){
        return incluir_gafa;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cambio;
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel cliente;
    private javax.swing.JLabel compra;
    private javax.swing.JButton confirmar;
    private javax.swing.JTextField efectivo;
    private javax.swing.JCheckBox incluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel mostrar_fecha;
    private javax.swing.JPanel panel_cliente;
    private javax.swing.JPanel panel_fecha;
    private javax.swing.JLabel precio;
    private javax.swing.JLabel precio_gafas;
    private javax.swing.JLabel precio_total;
    private javax.swing.JLabel puntos;
    // End of variables declaration//GEN-END:variables
}
