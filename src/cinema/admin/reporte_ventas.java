/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.admin;

import cinema.administrador;
import clases.conectar;
import clases.convenio;
import clases.pelicula;
import clases.usuario;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WILLIAN
 */
public class reporte_ventas extends javax.swing.JPanel {

    conectar cc_reporte_v =new conectar();
    Connection con_reporte_v;
    
    usuario user = new usuario();
    String fecha_actual;
    
    DefaultTableModel modelo_ventas_general;
    DefaultTableModel modelo_ventas_detallado;
    DefaultTableModel modelo_detalle_general;
    
    public reporte_ventas(usuario us) {
        user=us;
        initComponents();
        modelo_ventas_general =  (DefaultTableModel) lista_ventas_general.getModel();
        modelo_ventas_detallado =  (DefaultTableModel) lista_ventas_detallado.getModel();
        modelo_detalle_general =  (DefaultTableModel) lista_detalle_general.getModel();
        
        Calendar calendar = Calendar.getInstance();
        fecha_actual=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
            
        listar_usuarios();

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lista_ventas_general = new javax.swing.JTable();
        fecha_reporte_general = new com.toedter.calendar.JDateChooser();
        btn_anterior = new javax.swing.JButton();
        btn_siguiente = new javax.swing.JButton();
        lista_usuarios_report = new javax.swing.JComboBox();
        fecha_reporte_detallado = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista_ventas_detallado = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        lista_detalle_general = new javax.swing.JTable();
        fecha_reporte_detallado_general = new com.toedter.calendar.JDateChooser();
        btn_siguiente_det = new javax.swing.JButton();
        btn_anterior_det = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 204));
        setPreferredSize(new java.awt.Dimension(1310, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lista_ventas_general.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lista_ventas_general.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "VENTAS DEL DIA", "PREVENTA", "TOTAL BOLETAS", "VENTA GAFAS", "RECAUDO TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista_ventas_general.setRowHeight(25);
        jScrollPane1.setViewportView(lista_ventas_general);
        if (lista_ventas_general.getColumnModel().getColumnCount() > 0) {
            lista_ventas_general.getColumnModel().getColumn(0).setResizable(false);
            lista_ventas_general.getColumnModel().getColumn(0).setPreferredWidth(100);
            lista_ventas_general.getColumnModel().getColumn(1).setResizable(false);
            lista_ventas_general.getColumnModel().getColumn(1).setPreferredWidth(120);
            lista_ventas_general.getColumnModel().getColumn(2).setResizable(false);
            lista_ventas_general.getColumnModel().getColumn(3).setResizable(false);
            lista_ventas_general.getColumnModel().getColumn(3).setPreferredWidth(120);
            lista_ventas_general.getColumnModel().getColumn(4).setResizable(false);
            lista_ventas_general.getColumnModel().getColumn(5).setResizable(false);
            lista_ventas_general.getColumnModel().getColumn(5).setPreferredWidth(120);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 700, 220));

        fecha_reporte_general.setDate(new Date());
        fecha_reporte_general.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fecha_reporte_general.setMaxSelectableDate(new Date());
        fecha_reporte_general.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fecha_reporte_generalPropertyChange(evt);
            }
        });
        add(fecha_reporte_general, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 170, 30));

        btn_anterior.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_anterior.setText("<");
        btn_anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anteriorActionPerformed(evt);
            }
        });
        add(btn_anterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 40, 30));

        btn_siguiente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_siguiente.setText(">");
        btn_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_siguienteActionPerformed(evt);
            }
        });
        add(btn_siguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 40, 30));

        lista_usuarios_report.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lista_usuarios_report.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lista_usuarios_reportItemStateChanged(evt);
            }
        });
        add(lista_usuarios_report, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, 170, -1));

        fecha_reporte_detallado.setDate(new Date());
        fecha_reporte_detallado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fecha_reporte_detallado.setMaxSelectableDate(new Date());
        fecha_reporte_detallado.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fecha_reporte_detalladoPropertyChange(evt);
            }
        });
        add(fecha_reporte_detallado, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, 160, 30));

        lista_ventas_detallado.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lista_ventas_detallado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE PELICULA", "VENTAS DEL DIA", "PREVENTA", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista_ventas_detallado.setRowHeight(25);
        jScrollPane2.setViewportView(lista_ventas_detallado);
        if (lista_ventas_detallado.getColumnModel().getColumnCount() > 0) {
            lista_ventas_detallado.getColumnModel().getColumn(0).setResizable(false);
            lista_ventas_detallado.getColumnModel().getColumn(0).setPreferredWidth(300);
            lista_ventas_detallado.getColumnModel().getColumn(1).setResizable(false);
            lista_ventas_detallado.getColumnModel().getColumn(2).setResizable(false);
            lista_ventas_detallado.getColumnModel().getColumn(3).setResizable(false);
        }

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 700, 200));

        lista_detalle_general.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lista_detalle_general.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sala", "Nombre Pelicula", "Valor", "Espectadores", "Taquilla"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista_detalle_general.setRowHeight(25);
        jScrollPane3.setViewportView(lista_detalle_general);
        if (lista_detalle_general.getColumnModel().getColumnCount() > 0) {
            lista_detalle_general.getColumnModel().getColumn(0).setResizable(false);
            lista_detalle_general.getColumnModel().getColumn(0).setPreferredWidth(70);
            lista_detalle_general.getColumnModel().getColumn(1).setResizable(false);
            lista_detalle_general.getColumnModel().getColumn(1).setPreferredWidth(200);
            lista_detalle_general.getColumnModel().getColumn(2).setResizable(false);
            lista_detalle_general.getColumnModel().getColumn(2).setPreferredWidth(100);
            lista_detalle_general.getColumnModel().getColumn(3).setResizable(false);
            lista_detalle_general.getColumnModel().getColumn(3).setPreferredWidth(100);
            lista_detalle_general.getColumnModel().getColumn(4).setResizable(false);
            lista_detalle_general.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 100, 570, 480));

        fecha_reporte_detallado_general.setDate(new Date());
        fecha_reporte_detallado_general.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fecha_reporte_detallado_general.setMaxSelectableDate(new Date());
        fecha_reporte_detallado_general.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fecha_reporte_detallado_generalPropertyChange(evt);
            }
        });
        add(fecha_reporte_detallado_general, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 60, 170, 30));

        btn_siguiente_det.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_siguiente_det.setText(">");
        btn_siguiente_det.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_siguiente_detActionPerformed(evt);
            }
        });
        add(btn_siguiente_det, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 60, 40, 30));

        btn_anterior_det.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_anterior_det.setText("<");
        btn_anterior_det.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anterior_detActionPerformed(evt);
            }
        });
        add(btn_anterior_det, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 60, 40, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("VENTAS POR SALA");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("VENTAS POR USUARIO");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void listar_usuarios(){
        lista_usuarios_report.addItem("");
        
         try {
            con_reporte_v = cc_reporte_v.conexion(user.get_ip());
            Statement st = con_reporte_v.createStatement();
            ResultSet result =st.executeQuery("SELECT * FROM usuarios");
            
            while(result.next()){
                lista_usuarios_report.addItem(result.getString(2)); 
            }  
            lista_usuarios_report.setSelectedIndex(0);
            
            con_reporte_v.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 58:  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void cargar_ventas_general(String f){
        int a =modelo_ventas_general.getRowCount();
        for(int i=0; i<a;i++){
            modelo_ventas_general.removeRow(0);
        }
        boolean resultado=false;
        int venta_dia,preventa,boleta_total,gafas,total;
        venta_dia=preventa=boleta_total=gafas=total=0;
        
        try {
            con_reporte_v = cc_reporte_v.conexion(user.get_ip());
            Statement st = con_reporte_v.createStatement();
            ResultSet result =st.executeQuery("SELECT v.id, v.nombre, v.venta_dia, v.preventa, v.total, \n" +
                                                "SUM(CASE WHEN DATE(gafas.`fecha`)='"+f+"' THEN gafas.`valor` ELSE 0 END) gafas_venta  \n" +
                                                "FROM gafas\n" +
                                                "RIGHT JOIN(\n" +
                                                "    SELECT usuarios.id as id, usuarios.nombre as nombre, \n" +
                                                "    SUM(CASE WHEN `fecha_funcion`='"+f+"' THEN `costo` ELSE 0 END) as venta_dia,\n" +
                                                "    SUM(CASE WHEN `fecha_funcion`!='"+f+"' THEN `costo` ELSE 0 END) as preventa,\n" +
                                                "    IFNULL(SUM(`costo`),0) as total\n" +
                                                "    FROM usuarios\n" +
                                                "    LEFT JOIN ventas ON usuarios.id = ventas.usuario_id \n" +
                                                "    AND DATE(ventas.`fecha_venta`)='"+f+"' \n" +
                                                "    GROUP BY usuarios.id) AS v ON v.id=gafas.usuario_id \n" +
                                                "GROUP BY usuario_id");

            while(result.next()){
                resultado=true;
                Object[] datosDeLaFila= new Object [6];

                datosDeLaFila[0]=result.getString(2);
                datosDeLaFila[1]=mil(result.getInt(3));
                datosDeLaFila[2]=mil(result.getInt(4));
                datosDeLaFila[3]=mil(result.getInt(5));
                datosDeLaFila[4]=mil(result.getInt(6));
                datosDeLaFila[5]=mil(result.getInt(5)+result.getInt(6));
 
                modelo_ventas_general.addRow(datosDeLaFila);
                venta_dia=venta_dia+result.getInt(3);
                preventa=preventa+result.getInt(4);
                boleta_total=boleta_total+result.getInt(5);
                gafas=gafas+result.getInt(6);
                total=total+result.getInt(5)+result.getInt(6);
                
            } 
 
            if (resultado){
                Object[] datosDeLaFila= new Object [6];

                datosDeLaFila[0]="";
                datosDeLaFila[1]="";
                datosDeLaFila[2]="";
                datosDeLaFila[3]="";
                datosDeLaFila[4]="";
                datosDeLaFila[5]="";
                modelo_ventas_general.addRow(datosDeLaFila);

                datosDeLaFila[0]="TOTAL";
                datosDeLaFila[1]=mil(venta_dia);
                datosDeLaFila[2]=mil(preventa);
                datosDeLaFila[3]=mil(boleta_total);
                datosDeLaFila[4]=mil(gafas);
                datosDeLaFila[5]=mil(total);
                modelo_ventas_general.addRow(datosDeLaFila);
                
            }
            
            lista_ventas_general.setModel(modelo_ventas_general);
            con_reporte_v.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 57:  "+ex);
        }
  
    }
    
    private void cargar_ventas_detallado(){
        Calendar calendar= fecha_reporte_detallado.getCalendar();
        String fecha=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        
        int a =modelo_ventas_detallado.getRowCount();
        for(int i=0; i<a;i++){
            modelo_ventas_detallado.removeRow(0);
        }
       
        try {
            con_reporte_v = cc_reporte_v.conexion(user.get_ip());
            Statement st = con_reporte_v.createStatement();
            ResultSet result =st.executeQuery("SELECT p.nombre, \n" +
                                            "sum(CASE WHEN v.`fecha_funcion`='"+fecha+"' THEN v.`costo` ELSE 0 END) as venta_dia, \n" +
                                            "sum(CASE WHEN v.`fecha_funcion`!='"+fecha+"' THEN v.`costo` ELSE 0 END) as preventa, \n" +
                                            "sum(v.`costo`)as total \n" +
                                            "from ventas as v, funciones as f, peliculas as p, usuarios u \n" +
                                            "where v.usuario_id=u.id AND v.funcion_id=f.id AND f.pelicula_id=p.id AND "+
                                            "u.nombre='"+lista_usuarios_report.getSelectedItem()+"' AND DATE(v.`fecha_venta`)='"+fecha+"' \n" +
                                            "GROUP BY p.id ORDER BY p.id DESC");

            while(result.next()){

                Object[] datosDeLaFila= new Object [4];

                datosDeLaFila[0]=result.getString(1);
                datosDeLaFila[1]=mil(result.getInt(2));
                datosDeLaFila[2]=mil(result.getInt(3));
                datosDeLaFila[3]=mil(result.getInt(4));
 
                modelo_ventas_detallado.addRow(datosDeLaFila);
                
            } 

            lista_ventas_detallado.setModel(modelo_ventas_detallado);
            con_reporte_v.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 58:  "+ex);
        }
  
    }

    private void cargar_detalle_general(String f){
        int a =modelo_detalle_general.getRowCount();
        for(int i=0; i<a;i++){
            modelo_detalle_general.removeRow(0);
        }

        int total_espectadores=0;
        int total_taquilla=0;
        
        Object[] datosDeLaFila= new Object [5];
        
        try {
            con_reporte_v = cc_reporte_v.conexion(user.get_ip());
            Statement st = con_reporte_v.createStatement();
            ResultSet result =st.executeQuery("select f.sala, p.nombre, v.costo, COUNT(f.sala) \n" +
                                                "from ventas AS v, funciones AS f, peliculas AS p \n" +
                                                "WHERE fecha_funcion ='"+f+"' AND \n" +
                                                "v.funcion_id=f.id AND \n" +
                                                "f.pelicula_id=p.id AND v.conveion_id IS NULL \n" +
                                                "GROUP BY f.sala, p.id , v.costo \n" +
                                                "ORDER BY f.sala , p.nombre");

            while(result.next()){

                datosDeLaFila[0]="Sala "+result.getString(1);
                datosDeLaFila[1]=result.getString(2);
                datosDeLaFila[2]="$ "+mil(result.getInt(3));
                datosDeLaFila[3]=result.getInt(4);
                datosDeLaFila[4]="$ "+mil(result.getInt(3)*result.getInt(4));
 
                modelo_detalle_general.addRow(datosDeLaFila);
                total_espectadores+=result.getInt(4);
                total_taquilla+=(result.getInt(3)*result.getInt(4));
                
            }
            if(total_espectadores!=0){
                agregar_linea();
                datosDeLaFila[0]="TOTAL";
                datosDeLaFila[1]="";
                datosDeLaFila[2]="";
                datosDeLaFila[3]=total_espectadores;
                datosDeLaFila[4]="$ "+mil(total_taquilla);
 
                modelo_detalle_general.addRow(datosDeLaFila);
            }
            
            ////LIASTAR CONVENIOS
            String convenio="";
            int total_espectadores_c=0;
            int total_taquilla_c=0;
            
            result =st.executeQuery("select f.sala, p.nombre, v.costo, COUNT(f.sala), v.conveion_id , c.nombre\n" +
                                    "from ventas AS v, funciones AS f, peliculas AS p, convenios AS c\n" +
                                    "WHERE fecha_funcion ='"+f+"' AND\n" +
                                    "v.conveion_id=c.id AND\n" +
                                    "v.funcion_id=f.id AND\n" +
                                    "f.pelicula_id=p.id AND v.conveion_id IS NOT NULL\n" +
                                    "GROUP BY f.sala, p.id , v.conveion_id, v.costo\n" +
                                    "ORDER BY c.id, f.sala, p.nombre");

            while(result.next()){
                
                if(!convenio.equals(result.getString(5))){
                    if(!convenio.equals("")){
                        agregar_linea();
                        datosDeLaFila[0]="TOTAL";
                        datosDeLaFila[1]="";
                        datosDeLaFila[2]="";
                        datosDeLaFila[3]=total_espectadores_c;
                        datosDeLaFila[4]="$ "+mil(total_taquilla_c);
                        modelo_detalle_general.addRow(datosDeLaFila);
                        
                        total_espectadores_c=0;
                        total_taquilla_c=0;
                    }
                    datosDeLaFila[0]="";
                    datosDeLaFila[1]=result.getString(6);
                    datosDeLaFila[2]="";
                    datosDeLaFila[3]="";
                    datosDeLaFila[4]=" ";
                    
                    modelo_detalle_general.addRow(datosDeLaFila);
                    convenio=result.getString(5);

                }

                datosDeLaFila[0]="Sala "+result.getString(1);
                datosDeLaFila[1]=result.getString(2);
                datosDeLaFila[2]="$ "+mil(result.getInt(3));
                datosDeLaFila[3]=result.getInt(4);
                datosDeLaFila[4]="$ "+mil(result.getInt(3)*result.getInt(4));
 
                total_espectadores_c+=result.getInt(4);
                total_taquilla_c+=(result.getInt(3)*result.getInt(4));
                    
                modelo_detalle_general.addRow(datosDeLaFila);

                total_espectadores+=result.getInt(4);
                total_taquilla+=(result.getInt(3)*result.getInt(4));
                
            } 
  
            if(!convenio.equals("")){
                agregar_linea();
                datosDeLaFila[0]="TOTAL";
                datosDeLaFila[1]="";
                datosDeLaFila[2]="";
                datosDeLaFila[3]=total_espectadores_c;
                datosDeLaFila[4]="$ "+mil(total_taquilla_c);
                modelo_detalle_general.addRow(datosDeLaFila);

                modelo_detalle_general.addRow(new Object [5]);
                agregar_linea();
                datosDeLaFila[0]="TOTAL";
                datosDeLaFila[1]="";
                datosDeLaFila[2]="";
                datosDeLaFila[3]=total_espectadores;
                datosDeLaFila[4]="$ "+mil(total_taquilla);
                modelo_detalle_general.addRow(datosDeLaFila);
            }
                
            lista_detalle_general.setModel(modelo_detalle_general);
            con_reporte_v.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error pendiente de numero:  "+ex);
        } 
    }
    
    public void agregar_linea(){
        Object[] datosDeLaFila= new Object [5];
        datosDeLaFila[0]="---------";
        datosDeLaFila[1]="------------------------------";
        datosDeLaFila[2]="--------------";
        datosDeLaFila[3]="-------------";
        datosDeLaFila[4]="--------------";

        modelo_detalle_general.addRow(datosDeLaFila);

    }
    
    public String mil(int num){
        String numero="";
        DecimalFormat formatea = new DecimalFormat("###,###.##");
        
        return formatea.format(num);
    }
    
    private void fecha_reporte_generalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fecha_reporte_generalPropertyChange
        // TODO add your handling code here:
        Calendar calendar= fecha_reporte_general.getCalendar();
        String fecha=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        cargar_ventas_general(fecha);
    }//GEN-LAST:event_fecha_reporte_generalPropertyChange

    private void btn_anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anteriorActionPerformed
        Calendar c= fecha_reporte_general.getCalendar();
        
        c.add(Calendar.DAY_OF_YEAR,-1);
        fecha_reporte_general.setDate(new Date(c.getTimeInMillis()));
    }//GEN-LAST:event_btn_anteriorActionPerformed

    private void btn_siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_siguienteActionPerformed
        Calendar c= fecha_reporte_general.getCalendar();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        if(!f.format(fecha_reporte_general.getDate()).equals(f.format(new Date()))){
            c.add(Calendar.DAY_OF_YEAR,1);
            fecha_reporte_general.setDate(new Date(c.getTimeInMillis()));
        }
    }//GEN-LAST:event_btn_siguienteActionPerformed

    private void lista_usuarios_reportItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lista_usuarios_reportItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED && !lista_usuarios_report.getSelectedItem().equals("")) {
            cargar_ventas_detallado();       
        }
        else{
            int a =modelo_ventas_detallado.getRowCount();
            for(int i=0; i<a;i++){
                modelo_ventas_detallado.removeRow(0);
            }
        }
    }//GEN-LAST:event_lista_usuarios_reportItemStateChanged

    private void fecha_reporte_detalladoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fecha_reporte_detalladoPropertyChange
        if (!lista_usuarios_report.getSelectedItem().equals("")) {
            cargar_ventas_detallado();       
        }
        else{
            int a =modelo_ventas_detallado.getRowCount();
            for(int i=0; i<a;i++){
                modelo_ventas_detallado.removeRow(0);
            }
        }
    }//GEN-LAST:event_fecha_reporte_detalladoPropertyChange

    private void fecha_reporte_detallado_generalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fecha_reporte_detallado_generalPropertyChange
        Calendar calendar= fecha_reporte_detallado_general.getCalendar();
        String fecha=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        cargar_detalle_general(fecha);
    }//GEN-LAST:event_fecha_reporte_detallado_generalPropertyChange

    private void btn_siguiente_detActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_siguiente_detActionPerformed
        Calendar c= fecha_reporte_detallado_general.getCalendar();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        if(!f.format(fecha_reporte_detallado_general.getDate()).equals(f.format(new Date()))){
            c.add(Calendar.DAY_OF_YEAR,1);
            fecha_reporte_detallado_general.setDate(new Date(c.getTimeInMillis()));
        }
    }//GEN-LAST:event_btn_siguiente_detActionPerformed

    private void btn_anterior_detActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anterior_detActionPerformed
        Calendar c= fecha_reporte_detallado_general.getCalendar();
        
        c.add(Calendar.DAY_OF_YEAR,-1);
        fecha_reporte_detallado_general.setDate(new Date(c.getTimeInMillis()));
    }//GEN-LAST:event_btn_anterior_detActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_anterior;
    private javax.swing.JButton btn_anterior_det;
    private javax.swing.JButton btn_siguiente;
    private javax.swing.JButton btn_siguiente_det;
    private com.toedter.calendar.JDateChooser fecha_reporte_detallado;
    private com.toedter.calendar.JDateChooser fecha_reporte_detallado_general;
    private com.toedter.calendar.JDateChooser fecha_reporte_general;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable lista_detalle_general;
    private javax.swing.JComboBox lista_usuarios_report;
    private javax.swing.JTable lista_ventas_detallado;
    private javax.swing.JTable lista_ventas_general;
    // End of variables declaration//GEN-END:variables
}
