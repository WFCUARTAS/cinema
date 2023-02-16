/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.admin;

import cinema.sala;
import clases.conectar;
import clases.funcion;
import clases.pelicula;
import clases.tablaimagen;
import clases.usuario;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WILLIANC
 */
public class reporte_general extends javax.swing.JPanel {

    conectar cc_reporte_gen =new conectar();
    Connection con_reporte_gen;
    
    usuario user = new usuario();
    

    DefaultTableModel modelo_pelicula_reporte_general;

    
    ArrayList<pelicula> peliculas_reporte = new ArrayList<pelicula>();
    pelicula pelicula_selec_reporte = new pelicula();
    
    ArrayList<funcion> funciones_reporte = new ArrayList<funcion>();
    funcion funcion_selec_reporte = new funcion();

    JFrame padre = new JFrame();
    String fecha;
    
    
    public reporte_general(usuario us) {
        user=us;
        
        initComponents();

        modelo_pelicula_reporte_general =  (DefaultTableModel) lista_peliculas_reporte_GENERAL.getModel();

        Calendar calendar = Calendar.getInstance();
        fecha=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        cargarpeliculas_reporte_general(fecha);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        lista_peliculas_reporte_GENERAL = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        fecha_reporte_dia_general = new com.toedter.calendar.JDateChooser();
        btn_anterior_general = new javax.swing.JButton();
        btn_siguiente_general = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        setBackground(new java.awt.Color(204, 255, 204));
        setMaximumSize(new java.awt.Dimension(1340, 640));
        setMinimumSize(new java.awt.Dimension(1340, 640));
        setPreferredSize(new java.awt.Dimension(1340, 640));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lista_peliculas_reporte_GENERAL.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lista_peliculas_reporte_GENERAL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SALA", "NOMBRE PELICULA", "TAQUILLA", "ESPECTADORES", "FORMATO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista_peliculas_reporte_GENERAL.setRowHeight(30);
        jScrollPane5.setViewportView(lista_peliculas_reporte_GENERAL);
        if (lista_peliculas_reporte_GENERAL.getColumnModel().getColumnCount() > 0) {
            lista_peliculas_reporte_GENERAL.getColumnModel().getColumn(0).setResizable(false);
            lista_peliculas_reporte_GENERAL.getColumnModel().getColumn(0).setPreferredWidth(170);
            lista_peliculas_reporte_GENERAL.getColumnModel().getColumn(1).setResizable(false);
            lista_peliculas_reporte_GENERAL.getColumnModel().getColumn(1).setPreferredWidth(250);
            lista_peliculas_reporte_GENERAL.getColumnModel().getColumn(2).setResizable(false);
            lista_peliculas_reporte_GENERAL.getColumnModel().getColumn(2).setPreferredWidth(90);
            lista_peliculas_reporte_GENERAL.getColumnModel().getColumn(3).setResizable(false);
            lista_peliculas_reporte_GENERAL.getColumnModel().getColumn(3).setPreferredWidth(100);
            lista_peliculas_reporte_GENERAL.getColumnModel().getColumn(4).setResizable(false);
            lista_peliculas_reporte_GENERAL.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 780, 350));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("REPORTE GENERAL");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        fecha_reporte_dia_general.setDate(new Date());
        fecha_reporte_dia_general.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fecha_reporte_dia_general.setMaxSelectableDate(new Date());
        fecha_reporte_dia_general.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fecha_reporte_dia_generalPropertyChange(evt);
            }
        });
        add(fecha_reporte_dia_general, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 170, 30));

        btn_anterior_general.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_anterior_general.setText("<");
        btn_anterior_general.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anterior_generalActionPerformed(evt);
            }
        });
        add(btn_anterior_general, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 40, 30));

        btn_siguiente_general.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_siguiente_general.setText(">");
        btn_siguiente_general.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_siguiente_generalActionPerformed(evt);
            }
        });
        add(btn_siguiente_general, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 40, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void fecha_reporte_dia_generalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fecha_reporte_dia_generalPropertyChange
        // TODO add your handling code here:
        Calendar calendar= fecha_reporte_dia_general.getCalendar();
        String fecha=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        cargarpeliculas_reporte_general(fecha);
    }//GEN-LAST:event_fecha_reporte_dia_generalPropertyChange

    private void btn_anterior_generalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anterior_generalActionPerformed
        Calendar c= fecha_reporte_dia_general.getCalendar();

        c.add(Calendar.DAY_OF_YEAR,-1);
        fecha_reporte_dia_general.setDate(new Date(c.getTimeInMillis()));
    }//GEN-LAST:event_btn_anterior_generalActionPerformed

    private void btn_siguiente_generalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_siguiente_generalActionPerformed
        Calendar c= fecha_reporte_dia_general.getCalendar();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        if(!f.format(fecha_reporte_dia_general.getDate()).equals(f.format(new Date()))){
            c.add(Calendar.DAY_OF_YEAR,1);
            fecha_reporte_dia_general.setDate(new Date(c.getTimeInMillis()));
        }
    }//GEN-LAST:event_btn_siguiente_generalActionPerformed

 
private void cargarpeliculas_reporte_general(String f){
    int a =modelo_pelicula_reporte_general.getRowCount();
    for(int i=0; i<a;i++){
        modelo_pelicula_reporte_general.removeRow(0);
    }
    con_reporte_gen = cc_reporte_gen.conexion(user.get_ip());
    
    int taquilla=0;
    int espectadores=0;
    try {
        Statement st = con_reporte_gen.createStatement();
            ResultSet result =st.executeQuery("  SELECT f.sala,p.nombre, sum(v.costo), count(*),p.tipo " +
                                                "FROM peliculas p, funciones f,ventas v " +
                                                "WHERE p.id=f.pelicula_id AND f.id=v.funcion_id AND p.activa AND v.fecha_funcion='"+f+"' " +
                                                "group by f.sala,p.id");

        while(result.next()){
            pelicula p = new pelicula();

            Object[] datosDeLaFila= new Object [5];

            datosDeLaFila[0]="MILTICINE ESPINAL "+result.getString(1);
            datosDeLaFila[1]=result.getString(2);
            datosDeLaFila[2]=mil(result.getInt(3));
            datosDeLaFila[3]=mil(result.getInt(4));
            
            if(result.getString(5).equals("2D")){
                datosDeLaFila[4]="1";
             }else{
                datosDeLaFila[4]="2";
             }
            
            taquilla += result.getInt(3);
            espectadores += result.getInt(4);
            modelo_pelicula_reporte_general.addRow(datosDeLaFila);
            
        }   
        Object[] datosDeLaFila= new Object [5];

        datosDeLaFila[0]="";
        datosDeLaFila[1]="TOTALES";
        datosDeLaFila[2]=mil(taquilla);
        datosDeLaFila[3]=mil(espectadores);
        datosDeLaFila[4]="";
        
        modelo_pelicula_reporte_general.addRow(datosDeLaFila);
        lista_peliculas_reporte_GENERAL.setModel(modelo_pelicula_reporte_general);
        
        con_reporte_gen.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,"error 42:  "+ex);
    }
    
    
}
 
 
 public String mil(int num){
        String numero="";
        DecimalFormat formatea = new DecimalFormat("###,###.##");
        
        return formatea.format(num);
    }
 
 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_anterior_general;
    private javax.swing.JButton btn_siguiente_general;
    private com.toedter.calendar.JDateChooser fecha_reporte_dia_general;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable lista_peliculas_reporte_GENERAL;
    // End of variables declaration//GEN-END:variables
}
