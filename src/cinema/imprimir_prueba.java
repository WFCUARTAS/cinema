/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;



import clases.*;
import clases.usuario;
import imprimir.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.print.Doc;
import javax.print.DocPrintJob;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author XIMENA
 */
public class imprimir_prueba extends javax.swing.JFrame {


    public imprimir_prueba() {
        initComponents();
        
        codigo.requestFocus();
       
        
    }

    conectar cc =new conectar();
    Connection con;      
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        codigo = new javax.swing.JTextField();
        seleccion = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        codigo.setToolTipText("");
        codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout seleccionLayout = new javax.swing.GroupLayout(seleccion);
        seleccion.setLayout(seleccionLayout);
        seleccionLayout.setHorizontalGroup(
            seleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        seleccionLayout.setVerticalGroup(
            seleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(jButton1)
                .addGap(177, 177, 177))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(seleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(135, 135, 135)
                .addComponent(seleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        Color col = JColorChooser.showDialog(null,
            "JColorChooser Sample",Color.BLUE);
        
         JOptionPane.showMessageDialog(null,"selecioc "+col.getRed());
        
         seleccion.setBackground(col);
         
         
         
        /*
        int s = JOptionPane.showOptionDialog(
        this,
        "Venta exitosa", 
        "Resultado venta",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.INFORMATION_MESSAGE,
        null,    // null para icono por defecto.
        new Object[] { "    OK     ", "i"},   // null para YES, NO y CANCEL
        "OK");
        */
        
        /*ArrayList<String> sillas = new ArrayList<String>();
        pelicula p = new pelicula();
        funcion f = new funcion();
        
        p.set_nombre("nombre de la pelicula");
        f.set_hora("11");
        f.set_minutos("22");
        f.set_ampm("AM");
        f.set_sala(1);
                
        sillas.add("A1");
        sillas.add("A2");
        
        imprimir_tiquete ej = new imprimir_tiquete(p,f,sillas,"07-07-2017","10000",false);*/
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void codigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER ){
            JOptionPane.showMessageDialog(null,"selecioco la terjeta n°  "+codigo.getText());
            codigo.setEditable(false);
        }
    }//GEN-LAST:event_codigoKeyPressed

    
    
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new imprimir_prueba().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codigo;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel seleccion;
    // End of variables declaration//GEN-END:variables
}
