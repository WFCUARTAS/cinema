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
public class reporte_entradas extends javax.swing.JPanel {

    conectar cc_reporte =new conectar();
    Connection con_reporte;
    
    usuario user = new usuario();
    
    DefaultTableModel modelo_pelicula_reporte;
    DefaultTableModel modelo_pelicula_reporte_dia;
    DefaultTableModel modelo_funcion_reporte; 
    DefaultTableModel modelo_fecha_reporte;
    
    ArrayList<pelicula> peliculas_reporte = new ArrayList<pelicula>();
    pelicula pelicula_selec_reporte = new pelicula();
    
    ArrayList<funcion> funciones_reporte = new ArrayList<funcion>();
    funcion funcion_selec_reporte = new funcion();

    JFrame padre = new JFrame();
    String fecha;
    
    
    public reporte_entradas(java.awt.Frame parent , usuario us) {
        user=us;
        con_reporte = cc_reporte.conexion(user.get_ip());
        padre=(JFrame) parent;
        initComponents();
        modelo_pelicula_reporte =  (DefaultTableModel) lista_peliculas_reporte.getModel();
        lista_peliculas_reporte.setDefaultRenderer(Object.class, new tablaimagen());
        modelo_funcion_reporte =  (DefaultTableModel) lista_funciones_reporte.getModel();
        lista_funciones_reporte.setDefaultRenderer(Object.class, new tablaimagen());
        modelo_fecha_reporte =  (DefaultTableModel) lista_funciones_fecha_reporte.getModel();
        modelo_pelicula_reporte_dia =  (DefaultTableModel) lista_peliculas_reporte_dia.getModel();
        cargarpeliculas_reporte("a.id");
        Calendar calendar = Calendar.getInstance();
        fecha=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        cargarpeliculas_reporte_dia(fecha);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        palabra_buscar_reporte = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_peliculas_reporte = new javax.swing.JTable();
        buscar_pelicula_reporte = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        nombre_pelicula_reporte = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista_funciones_reporte = new javax.swing.JTable();
        fecha_reporte = new com.toedter.calendar.JDateChooser();
        jScrollPane4 = new javax.swing.JScrollPane();
        lista_funciones_fecha_reporte = new javax.swing.JTable();
        seleccionar_fucnion_fecha = new javax.swing.JButton();
        top10 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        lista_peliculas_reporte_dia = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        reporte_gafas = new javax.swing.JButton();
        fecha_reporte_dia_entradas = new com.toedter.calendar.JDateChooser();
        btn_anterior_entradas = new javax.swing.JButton();
        btn_siguiente_entradas = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        titulos_reporte_entradas = new javax.swing.JTable();

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

        palabra_buscar_reporte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        palabra_buscar_reporte.setText("\"ingrese aqui la palagra clave\"");
        palabra_buscar_reporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                palabra_buscar_reporteMousePressed(evt);
            }
        });
        add(palabra_buscar_reporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 200, 30));

        lista_peliculas_reporte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lista_peliculas_reporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "TIPO", "ENTRADAS", "SELECCION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista_peliculas_reporte.setRowHeight(30);
        lista_peliculas_reporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_peliculas_reporteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lista_peliculas_reporte);
        if (lista_peliculas_reporte.getColumnModel().getColumnCount() > 0) {
            lista_peliculas_reporte.getColumnModel().getColumn(0).setResizable(false);
            lista_peliculas_reporte.getColumnModel().getColumn(0).setPreferredWidth(350);
            lista_peliculas_reporte.getColumnModel().getColumn(1).setResizable(false);
            lista_peliculas_reporte.getColumnModel().getColumn(1).setPreferredWidth(60);
            lista_peliculas_reporte.getColumnModel().getColumn(2).setResizable(false);
            lista_peliculas_reporte.getColumnModel().getColumn(3).setResizable(false);
            lista_peliculas_reporte.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 650, 230));

        buscar_pelicula_reporte.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        buscar_pelicula_reporte.setText("BUSCAR");
        buscar_pelicula_reporte.setEnabled(false);
        buscar_pelicula_reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_pelicula_reporteActionPerformed(evt);
            }
        });
        add(buscar_pelicula_reporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, -1, -1));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        nombre_pelicula_reporte.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        nombre_pelicula_reporte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre_pelicula_reporte.setText("TITULO");

        lista_funciones_reporte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lista_funciones_reporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "HORA", "SALA", "TOTAL", "FECHA INICIO", "FECHA FIN", "SELECION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista_funciones_reporte.setRowHeight(30);
        lista_funciones_reporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_funciones_reporteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lista_funciones_reporte);
        if (lista_funciones_reporte.getColumnModel().getColumnCount() > 0) {
            lista_funciones_reporte.getColumnModel().getColumn(0).setResizable(false);
            lista_funciones_reporte.getColumnModel().getColumn(1).setResizable(false);
            lista_funciones_reporte.getColumnModel().getColumn(1).setHeaderValue("SALA");
            lista_funciones_reporte.getColumnModel().getColumn(2).setResizable(false);
            lista_funciones_reporte.getColumnModel().getColumn(3).setResizable(false);
            lista_funciones_reporte.getColumnModel().getColumn(3).setHeaderValue("FECHA INICIO");
            lista_funciones_reporte.getColumnModel().getColumn(4).setResizable(false);
            lista_funciones_reporte.getColumnModel().getColumn(4).setHeaderValue("FECHA FIN");
            lista_funciones_reporte.getColumnModel().getColumn(5).setResizable(false);
            lista_funciones_reporte.getColumnModel().getColumn(5).setHeaderValue("SELECION");
        }

        lista_funciones_fecha_reporte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lista_funciones_fecha_reporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista_funciones_fecha_reporte.setRowHeight(30);
        jScrollPane4.setViewportView(lista_funciones_fecha_reporte);
        if (lista_funciones_fecha_reporte.getColumnModel().getColumnCount() > 0) {
            lista_funciones_fecha_reporte.getColumnModel().getColumn(0).setResizable(false);
            lista_funciones_fecha_reporte.getColumnModel().getColumn(1).setResizable(false);
        }

        seleccionar_fucnion_fecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        seleccionar_fucnion_fecha.setText("SELECCIONAR");
        seleccionar_fucnion_fecha.setEnabled(false);
        seleccionar_fucnion_fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionar_fucnion_fechaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(nombre_pelicula_reporte, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fecha_reporte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seleccionar_fucnion_fecha, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombre_pelicula_reporte, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(fecha_reporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(seleccionar_fucnion_fecha))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, 600, 540));

        top10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        top10.setText("top 10");
        top10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                top10ActionPerformed(evt);
            }
        });
        add(top10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, -1, -1));

        lista_peliculas_reporte_dia.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lista_peliculas_reporte_dia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "INDIVIDUAL", "2X1", "ENTRADAS", "PERSONAS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista_peliculas_reporte_dia.setRowHeight(30);
        jScrollPane5.setViewportView(lista_peliculas_reporte_dia);
        if (lista_peliculas_reporte_dia.getColumnModel().getColumnCount() > 0) {
            lista_peliculas_reporte_dia.getColumnModel().getColumn(0).setResizable(false);
            lista_peliculas_reporte_dia.getColumnModel().getColumn(0).setPreferredWidth(330);
            lista_peliculas_reporte_dia.getColumnModel().getColumn(1).setResizable(false);
            lista_peliculas_reporte_dia.getColumnModel().getColumn(1).setPreferredWidth(80);
            lista_peliculas_reporte_dia.getColumnModel().getColumn(2).setResizable(false);
            lista_peliculas_reporte_dia.getColumnModel().getColumn(2).setPreferredWidth(80);
            lista_peliculas_reporte_dia.getColumnModel().getColumn(3).setResizable(false);
            lista_peliculas_reporte_dia.getColumnModel().getColumn(3).setPreferredWidth(80);
            lista_peliculas_reporte_dia.getColumnModel().getColumn(4).setResizable(false);
            lista_peliculas_reporte_dia.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 650, 170));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("ENTRADAS POR DÍA");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        reporte_gafas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gafas.png"))); // NOI18N
        reporte_gafas.setText("GAFAS");
        reporte_gafas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reporte_gafasActionPerformed(evt);
            }
        });
        add(reporte_gafas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 140, 50));

        fecha_reporte_dia_entradas.setDate(new Date());
        fecha_reporte_dia_entradas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fecha_reporte_dia_entradas.setMaxSelectableDate(new Date());
        fecha_reporte_dia_entradas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fecha_reporte_dia_entradasPropertyChange(evt);
            }
        });
        add(fecha_reporte_dia_entradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 170, 30));

        btn_anterior_entradas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_anterior_entradas.setText("<");
        btn_anterior_entradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anterior_entradasActionPerformed(evt);
            }
        });
        add(btn_anterior_entradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 40, 30));

        btn_siguiente_entradas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_siguiente_entradas.setText(">");
        btn_siguiente_entradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_siguiente_entradasActionPerformed(evt);
            }
        });
        add(btn_siguiente_entradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 40, 30));

        titulos_reporte_entradas.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        titulos_reporte_entradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PELICULA", "ENTRADAS", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        titulos_reporte_entradas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        titulos_reporte_entradas.setRowMargin(2);
        jScrollPane7.setViewportView(titulos_reporte_entradas);
        if (titulos_reporte_entradas.getColumnModel().getColumnCount() > 0) {
            titulos_reporte_entradas.getColumnModel().getColumn(0).setResizable(false);
            titulos_reporte_entradas.getColumnModel().getColumn(0).setPreferredWidth(330);
            titulos_reporte_entradas.getColumnModel().getColumn(1).setResizable(false);
            titulos_reporte_entradas.getColumnModel().getColumn(1).setPreferredWidth(160);
            titulos_reporte_entradas.getColumnModel().getColumn(2).setResizable(false);
            titulos_reporte_entradas.getColumnModel().getColumn(2).setPreferredWidth(160);
        }

        add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 650, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void palabra_buscar_reporteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_palabra_buscar_reporteMousePressed
        // TODO add your handling code here:
        palabra_buscar_reporte.setText("");
        buscar_pelicula_reporte.setEnabled(true);
    }//GEN-LAST:event_palabra_buscar_reporteMousePressed

    private void lista_peliculas_reporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_peliculas_reporteMouseClicked

        pelicula_selec_reporte = peliculas_reporte.get(lista_peliculas_reporte.rowAtPoint(evt.getPoint()));

        if(lista_peliculas_reporte.columnAtPoint(evt.getPoint())==3){
            //ACCION A REALIZAR CUANDO SE CSELECCIONA UNA PELICULA
            nombre_pelicula_reporte.setText(pelicula_selec_reporte.get_nombre());
            cargarfunciones_reporte(pelicula_selec_reporte.get_id());

        }

    }//GEN-LAST:event_lista_peliculas_reporteMouseClicked

    private void buscar_pelicula_reporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_pelicula_reporteActionPerformed
        // TODO add your handling code here:
        if (!palabra_buscar_reporte.getText().equals("ingrese aqui la palagra clave")){
            int a =modelo_pelicula_reporte.getRowCount();
            for(int i=0; i<a;i++){
                modelo_pelicula_reporte.removeRow(0);
            }

            peliculas_reporte.clear();

            try {
                Statement st = con_reporte.createStatement();

                ResultSet result =st.executeQuery("SELECT a.id, a.nombre, a.tipo, a.imagen_url, COUNT(a.id)-1 \n" +
                                                    "from (\n" +
                                                    "SELECT p.id, p.nombre, p.tipo, p.imagen_url \n" +
                                                    "FROM peliculas p, funciones f,ventas v \n" +
                                                    "WHERE p.id=f.pelicula_id AND f.id=v.funcion_id  \n" +
                                                    "union all\n" +
                                                    "SELECT id, nombre, tipo, imagen_url \n" +
                                                    "FROM peliculas) a \n" +
                                                    "where a.NOMBRE like '%"+palabra_buscar_reporte.getText()+"%' \n" +
                                                    "GROUP BY a.id ORDER BY a.id desc \n" +
                                                    "LIMIT 20");

                while(result.next()){
                    pelicula p=new pelicula();

                    Object[] datosDeLaFila= new Object [4];

                    datosDeLaFila[0]=result.getString(2);
                    datosDeLaFila[1]=result.getString(3);
                    datosDeLaFila[2]=result.getInt(5);
                    datosDeLaFila[3]=new JButton("SELEC");

                    modelo_pelicula_reporte.addRow(datosDeLaFila);
                    lista_peliculas_reporte.setModel(modelo_pelicula_reporte);
                    /////cargar valores de la consurla en array de pelicula

                    p.set_id(result.getString(1));
                    p.set_nombre(result.getString(2));
                    p.set_tipo(result.getString(3));
                    p.set_imagen_url(result.getString(4));


                    peliculas_reporte.add(p);

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error 27:  "+ex);
            }
        }
    }//GEN-LAST:event_buscar_pelicula_reporteActionPerformed

    private void top10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_top10ActionPerformed
        // TODO add your handling code here:
        cargarpeliculas_reporte("COUNT(a.id)");
    }//GEN-LAST:event_top10ActionPerformed

    private void lista_funciones_reporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_funciones_reporteMouseClicked
        // TODO add your handling code here:
        int select = lista_funciones_reporte.rowAtPoint(evt.getPoint());
        funcion_selec_reporte=funciones_reporte.get(select);
        String fecha_in=(String)lista_funciones_reporte.getValueAt(select, 3);
        String fecha_fin=(String)lista_funciones_reporte.getValueAt(select, 4);
        
        if(!fecha_in.equals("") && !fecha_fin.equals("") ){     
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha; 
                fecha = (Date)formatter.parse(fecha_in);
                fecha_reporte.setMinSelectableDate(fecha);

                fecha = (Date)formatter.parse(fecha_fin);
                fecha_reporte.setMaxSelectableDate(fecha);
                
                cargarfuncionesfecha_reporte(funciones_reporte.get(select).get_id());

            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null,"error 28:  "+ex);
                Logger.getLogger(reporte_entradas.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }else{
            
        }
    }//GEN-LAST:event_lista_funciones_reporteMouseClicked

    private void seleccionar_fucnion_fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionar_fucnion_fechaActionPerformed
        // TODO add your handling code here:
        if(fecha_reporte.getDate()!=null){
            Calendar calendar = fecha_reporte.getCalendar();
            sala s = new sala("admin",pelicula_selec_reporte,funcion_selec_reporte,user, calendar);
            s.setExtendedState(JFrame.MAXIMIZED_BOTH); 
            s.setResizable(false);
            s.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fecha");
        }
    }//GEN-LAST:event_seleccionar_fucnion_fechaActionPerformed

    private void reporte_gafasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reporte_gafasActionPerformed
        // TODO add your handling code here:
        
        reporte_gafas report_gafas= new reporte_gafas(padre, true, user.get_ip());
        report_gafas.setVisible(true);
    }//GEN-LAST:event_reporte_gafasActionPerformed

    private void fecha_reporte_dia_entradasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fecha_reporte_dia_entradasPropertyChange
        // TODO add your handling code here:
        Calendar calendar= fecha_reporte_dia_entradas.getCalendar();
        String fecha=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        cargarpeliculas_reporte_dia(fecha);
    }//GEN-LAST:event_fecha_reporte_dia_entradasPropertyChange

    private void btn_anterior_entradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anterior_entradasActionPerformed
        Calendar c= fecha_reporte_dia_entradas.getCalendar();

        c.add(Calendar.DAY_OF_YEAR,-1);
        fecha_reporte_dia_entradas.setDate(new Date(c.getTimeInMillis()));
    }//GEN-LAST:event_btn_anterior_entradasActionPerformed

    private void btn_siguiente_entradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_siguiente_entradasActionPerformed
        Calendar c= fecha_reporte_dia_entradas.getCalendar();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        if(!f.format(fecha_reporte_dia_entradas.getDate()).equals(f.format(new Date()))){
            c.add(Calendar.DAY_OF_YEAR,1);
            fecha_reporte_dia_entradas.setDate(new Date(c.getTimeInMillis()));
        }
    }//GEN-LAST:event_btn_siguiente_entradasActionPerformed

 private void cargarpeliculas_reporte(String orden){
    peliculas_reporte.clear();
    int a =modelo_fecha_reporte.getRowCount();

    for(int i=0; i<a;i++){
        modelo_fecha_reporte.removeRow(0);
    }

    a =modelo_pelicula_reporte.getRowCount();
    for(int i=0; i<a;i++){
        modelo_pelicula_reporte.removeRow(0);
    }

    try {
        Statement st = con_reporte.createStatement();
            ResultSet result =st.executeQuery("SELECT a.id, a.nombre, a.tipo, a.imagen_url, COUNT(a.id)-1 \n" +
                                                "from (\n" +
                                                "SELECT p.id, p.nombre, p.tipo, p.imagen_url \n" +
                                                "FROM peliculas p, funciones f,ventas v \n" +
                                                "WHERE p.id=f.pelicula_id AND f.id=v.funcion_id  \n" +
                                                "union all\n" +
                                                "SELECT id, nombre, tipo, imagen_url \n" +
                                                "FROM peliculas) a\n" +
                                                "GROUP BY a.id ORDER BY "+orden+"  desc \n" +
                                                "LIMIT 10");

        while(result.next()){
            pelicula p = new pelicula();

            Object[] datosDeLaFila= new Object [4];

            datosDeLaFila[0]=result.getString(2);
            datosDeLaFila[1]=result.getString(3);
            datosDeLaFila[2]=result.getInt(5);
            datosDeLaFila[3]=new JButton("SELEC");

            modelo_pelicula_reporte.addRow(datosDeLaFila);
           lista_peliculas_reporte.setModel(modelo_pelicula_reporte);
           /////cargar valores de la consurla en array de pelicula
           
           p.set_id(result.getString(1));
           p.set_nombre(result.getString(2));
           p.set_tipo(result.getString(3));
           p.set_imagen_url(result.getString(4));

           peliculas_reporte.add(p);

        }               
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,"error 30:  "+ex);
    }
}
 
 private void cargarfunciones_reporte(String id){
        funciones_reporte.clear();
        int a =modelo_funcion_reporte.getRowCount();
        for(int i=0; i<a;i++){
            modelo_funcion_reporte.removeRow(0);
        }
        
        a =modelo_fecha_reporte.getRowCount();
        seleccionar_fucnion_fecha.setEnabled(false);
        for(int i=0; i<a;i++){
            modelo_fecha_reporte.removeRow(0);
        }

        String ampm="AM";
        char hora[];
        int h;
        
        try {
            Statement st = con_reporte.createStatement();
                ResultSet result =st.executeQuery("SELECT * FROM ( \n" +
                            "SELECT f . * , COUNT( f.id ) AS total, MIN( v.fecha_funcion ) AS fechamin, MAX( v.fecha_funcion ) AS fechamax \n" +
                            "FROM peliculas p, funciones f, ventas v \n" +
                            "WHERE p.id = f.pelicula_id \n" +
                            "AND f.id = v.funcion_id \n" +
                            "AND p.id =  '"+id+"'  \n" +
                            "GROUP BY f.id \n" +
                            "UNION ALL \n" +
                            "SELECT * ,  '' AS total,  '' AS fechamin,  '' AS fechamax \n" +
                            "FROM funciones AS t1 \n" +
                            "WHERE pelicula_id =  '"+id+"') as tabla1 \n" +
                            "GROUP BY id ORDER BY id ");

            while(result.next()){
                funcion f = new funcion();
                ampm="AM";
                
                Object[] datosDeLaFila= new Object [6];

                hora=result.getString(3).toCharArray();
                h=Integer.parseInt(""+hora[0]+hora[1]);
                if(h==0 || h==12){
                    h=h+12;
                }
                if(h>12){
                    h=h-12;
                    ampm="PM";
                }
                
                datosDeLaFila[0]=h+":"+hora[3]+hora[4]+" "+ampm;
                datosDeLaFila[1]=result.getString(2);
                datosDeLaFila[2]=result.getString(9);
                datosDeLaFila[3]=result.getString(10);
                datosDeLaFila[4]=result.getString(11);
                datosDeLaFila[5]=new JButton("SELEC");
                
                modelo_funcion_reporte.addRow(datosDeLaFila);
               lista_funciones_reporte.setModel(modelo_funcion_reporte);
               /////cargar valores de la consurla en array de la funcion
               f.set_id(result.getString(1));
               f.set_sala(result.getInt(2));
               if(h<10){f.set_hora("0"+h);}
               else{f.set_hora(""+h);}
               f.set_minutos(""+hora[3]+hora[4]);
               f.set_ampm(ampm);
               f.set_activa(result.getBoolean(4));
               f.set_pelicula_id(result.getString(7));
               
               funciones_reporte.add(f);
               
            }               
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 31:  "+ex);
        }

    }
    
  private void cargarfuncionesfecha_reporte(String id){
    int a =modelo_fecha_reporte.getRowCount();
    seleccionar_fucnion_fecha.setEnabled(true);
    for(int i=0; i<a;i++){
        modelo_fecha_reporte.removeRow(0);
    }


    try {
        Statement st = con_reporte.createStatement();
            ResultSet result =st.executeQuery("SELECT fecha_funcion , COUNT(fecha_funcion) \n" +
                                                "FROM ventas \n" +
                                                "WHERE funcion_id='"+id+"'  \n" +
                                                "GROUP BY fecha_funcion");

        while(result.next()){

            Object[] datosDeLaFila= new Object [2];

            datosDeLaFila[0]=result.getString(1);
            datosDeLaFila[1]=result.getString(2);

            modelo_fecha_reporte.addRow(datosDeLaFila);
           lista_funciones_fecha_reporte.setModel(modelo_fecha_reporte);

        }               
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,"error 32:  "+ex);
    }
}
  
 
   private void cargarpeliculas_reporte_dia(String f){
    int a =modelo_pelicula_reporte_dia.getRowCount();
    for(int i=0; i<a;i++){
        modelo_pelicula_reporte_dia.removeRow(0);
    }

    try {
        Statement st = con_reporte.createStatement();
            ResultSet result =st.executeQuery("  SELECT a.nombre, \n" +
                                                "sum(CASE WHEN `total`=1 THEN 1 ELSE 0 END) as NORMAL, \n" +
                                                "sum(CASE WHEN `total`=2 THEN 1 ELSE 0 END) as PROMO \n" +
                                                "FROM (SELECT p.id, p.nombre, COUNT(v.consecutivo) AS total \n" +
                                                "    FROM peliculas p, funciones f,ventas v \n" +
                                                "    WHERE p.id=f.pelicula_id AND f.id=v.funcion_id AND p.activa AND v.fecha_funcion='"+f+"'  \n" +
                                                "    GROUP BY v.consecutivo) a \n" +
                                                "GROUP BY a.id ");

        while(result.next()){
            pelicula p = new pelicula();

            Object[] datosDeLaFila= new Object [5];

            datosDeLaFila[0]=result.getString(1);
            datosDeLaFila[1]=result.getInt(2);
            datosDeLaFila[2]=result.getInt(3);
            datosDeLaFila[3]=result.getInt(2)+result.getInt(3);
            datosDeLaFila[4]=result.getInt(2)+(result.getInt(3)*2);

            modelo_pelicula_reporte_dia.addRow(datosDeLaFila);
           lista_peliculas_reporte_dia.setModel(modelo_pelicula_reporte_dia);
           
        }               
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,"error 42:  "+ex);
    }
}
 
 
 
 
 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_anterior_entradas;
    private javax.swing.JButton btn_siguiente_entradas;
    private javax.swing.JButton buscar_pelicula_reporte;
    private com.toedter.calendar.JDateChooser fecha_reporte;
    private com.toedter.calendar.JDateChooser fecha_reporte_dia_entradas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable lista_funciones_fecha_reporte;
    private javax.swing.JTable lista_funciones_reporte;
    private javax.swing.JTable lista_peliculas_reporte;
    private javax.swing.JTable lista_peliculas_reporte_dia;
    private javax.swing.JLabel nombre_pelicula_reporte;
    private javax.swing.JTextField palabra_buscar_reporte;
    private javax.swing.JButton reporte_gafas;
    private javax.swing.JButton seleccionar_fucnion_fecha;
    private javax.swing.JTable titulos_reporte_entradas;
    private javax.swing.JButton top10;
    // End of variables declaration//GEN-END:variables
}
