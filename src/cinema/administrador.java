
package cinema;

import cinema.admin.reporte_entradas;
import cinema.admin.editar_funcion_especial;
import cinema.admin.reporte_general;
import cinema.admin.reporte_ventas;
import cinema.admin.resoluciones;
import cinema.admin.tarjetas_admin;
import cinema.admin.usuarios_admin;
import clases.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class administrador extends javax.swing.JFrame {

    conectar cc =new conectar();
    Connection con;
    
    String ruta_agregar="";
    String ruta_imagen="";
    JTextField [] precios2d = new JTextField[7];
    JTextField [] precios3d = new JTextField[7];
    JTextField [] precios_tarjeta = new JTextField[7];
    JTextField [] precios_tarjeta3d = new JTextField[7];
    
    JTextField [] pref_precios2d = new JTextField[7];
    JTextField [] pref_precios3d = new JTextField[7];
    JTextField [] pref_precios_tarjeta = new JTextField[7];
    JTextField [] pref_precios_tarjeta3d = new JTextField[7];

    boolean MitadPrecio_cambio=false;
    JRadioButton [] dia_MitadPrecio = new JRadioButton[7];
    
    usuario user = new usuario();
    ArrayList<pelicula> peliculas = new ArrayList<pelicula>();
    pelicula pelicula_selec = new pelicula();
    ArrayList<funcion> funciones = new ArrayList<funcion>();
    ArrayList<String> funciones_especiales = new ArrayList<String>();
    funcion funcion_selec = new funcion();
    DefaultTableModel modelo_pelicula;
    DefaultTableModel modelo_funcion;
    DefaultTableModel modelo_funcion_especial;
    
    ArrayList<convenio> convenios =new ArrayList<convenio>();
    boolean puede_selec = false;
    String selec_convenio;
    
    ImageIcon icono_activa;
    ImageIcon icono_inactiva;
    
    Color color_convenio=new Color(255,0,153);
            
    public administrador(usuario us) {
        user= us;
        con = cc.conexion(user.get_ip());
        initComponents();

        if(user.get_privilegios()==2){
            paneles.setEnabledAt(2, false);
            paneles.setEnabledAt(3, false);
            paneles.setEnabledAt(6, false);
        }
        
        color_select.setBackground(color_convenio);
        
        inicir_areglos();
        
       new Thread(new hilo_cargar()).start();
    }
    
    //delcarar arreglos
    public void inicir_areglos(){
        precios2d[0]=domingo2d;
        precios2d[1]=lunes2d;
        precios2d[2]=martes2d;
        precios2d[3]=miercoles2d;
        precios2d[4]=jueves2d;
        precios2d[5]=viernes2d;
        precios2d[6]=sabado2d;
        precios3d[0]=domingo3d;
        precios3d[1]=lunes3d;
        precios3d[2]=martes3d;
        precios3d[3]=miercoles3d;
        precios3d[4]=jueves3d;
        precios3d[5]=viernes3d;
        precios3d[6]=sabado3d;
        
        precios_tarjeta[0]=domingo_tarjeta2d;
        precios_tarjeta[1]=lunes_tarjeta2d;
        precios_tarjeta[2]=martes_tarjeta2d;
        precios_tarjeta[3]=miercoles_tarjeta2d;
        precios_tarjeta[4]=jueves_tarjeta2d;
        precios_tarjeta[5]=viernes_tarjeta2d;
        precios_tarjeta[6]=sabado_tarjeta2d;
        
        precios_tarjeta3d[0]=domingo_tarjeta3d;
        precios_tarjeta3d[1]=lunes_tarjeta3d;
        precios_tarjeta3d[2]=martes_tarjeta3d;
        precios_tarjeta3d[3]=miercoles_tarjeta3d;
        precios_tarjeta3d[4]=jueves_tarjeta3d;
        precios_tarjeta3d[5]=viernes_tarjeta3d;
        precios_tarjeta3d[6]=sabado_tarjeta3d;
        
        //PRECIOS PREFERENCIAL
        pref_precios2d[0]=pref_domingo2d;
        pref_precios2d[1]=pref_lunes2d;
        pref_precios2d[2]=pref_martes2d;
        pref_precios2d[3]=pref_miercoles2d;
        pref_precios2d[4]=pref_jueves2d;
        pref_precios2d[5]=pref_viernes2d;
        pref_precios2d[6]=pref_sabado2d;
        pref_precios3d[0]=pref_domingo3d;
        pref_precios3d[1]=pref_lunes3d;
        pref_precios3d[2]=pref_martes3d;
        pref_precios3d[3]=pref_miercoles3d;
        pref_precios3d[4]=pref_jueves3d;
        pref_precios3d[5]=pref_viernes3d;
        pref_precios3d[6]=pref_sabado3d;
        
        
        pref_precios_tarjeta[0]=pref_domingo_tarjeta2d;
        pref_precios_tarjeta[1]=pref_lunes_tarjeta2d;
        pref_precios_tarjeta[2]=pref_martes_tarjeta2d;
        pref_precios_tarjeta[3]=pref_miercoles_tarjeta2d;
        pref_precios_tarjeta[4]=pref_jueves_tarjeta2d;
        pref_precios_tarjeta[5]=pref_viernes_tarjeta2d;
        pref_precios_tarjeta[6]=pref_sabado_tarjeta2d;
        
        pref_precios_tarjeta3d[0]=pref_domingo_tarjeta3d;
        pref_precios_tarjeta3d[1]=pref_lunes_tarjeta3d;
        pref_precios_tarjeta3d[2]=pref_martes_tarjeta3d;
        pref_precios_tarjeta3d[3]=pref_miercoles_tarjeta3d;
        pref_precios_tarjeta3d[4]=pref_jueves_tarjeta3d;
        pref_precios_tarjeta3d[5]=pref_viernes_tarjeta3d;
        pref_precios_tarjeta3d[6]=pref_sabado_tarjeta3d;
        
        //Radio buton mitad de precio
        dia_MitadPrecio[0]=MitadPrecio_domingo;
        dia_MitadPrecio[1]=MitadPrecio_lunes;
        dia_MitadPrecio[2]=MitadPrecio_martes;
        dia_MitadPrecio[3]=MitadPrecio_miercoles;
        dia_MitadPrecio[4]=MitadPrecio_jueves;
        dia_MitadPrecio[5]=MitadPrecio_viernes;
        dia_MitadPrecio[6]=MitadPrecio_sabado;
    }
    
    private class hilo_cargar implements Runnable{

        @Override
        public void run() {
            ImageIcon ruta;

            modelo_pelicula =  (DefaultTableModel) lista_peliculas.getModel();
            lista_peliculas.setDefaultRenderer(Object.class, new tablaimagen());
            modelo_funcion =  (DefaultTableModel) lista_funciones.getModel();
            lista_funciones.setDefaultRenderer(Object.class, new tablaimagen());
            modelo_funcion_especial =  (DefaultTableModel) lista_funciones_especial.getModel();

            actualizar_pelicula.setEnabled(false);
            imagen_agregar.setText("");
            imagen_pelicula.setText("");
            listar_convenios();

            ruta= new javax.swing.ImageIcon(getClass().getResource("/imagenes/ok.png"));
            icono_activa = new ImageIcon(ruta.getImage().getScaledInstance(15,15, 0));

             ruta= new javax.swing.ImageIcon(getClass().getResource("/imagenes/mal.png"));
            icono_inactiva = new ImageIcon(ruta.getImage().getScaledInstance(15,15, 0));

            //carar valores tabla peliculas 

            ruta= new javax.swing.ImageIcon(getClass().getResource("/imagenes/sin-foto.png"));
            Icon icono = new ImageIcon(ruta.getImage().getScaledInstance(imagen_pelicula.getWidth(),imagen_pelicula.getHeight(), Image.SCALE_DEFAULT));
            imagen_pelicula.setIcon(icono);
            icono = new ImageIcon(ruta.getImage().getScaledInstance(imagen_agregar.getWidth(),imagen_agregar.getHeight(), Image.SCALE_DEFAULT));
            imagen_agregar.setIcon(icono);
            cargarpeliculas();

            ///cargar logos 
            ruta= new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"));
            icono = new ImageIcon(ruta.getImage().getScaledInstance(logo1.getWidth(),logo1.getHeight(), Image.SCALE_DEFAULT));
            logo1.setIcon(icono);
            icono = new ImageIcon(ruta.getImage().getScaledInstance(logo2.getWidth(),logo2.getHeight(), Image.SCALE_DEFAULT));
            logo2.setIcon(icono);
            logo3.setIcon(icono);
            /////logo gearsoft
            ruta= new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo-gearsoft.png"));
            icono = new ImageIcon(ruta.getImage().getScaledInstance(logo4.getWidth(),logo4.getHeight(), Image.SCALE_DEFAULT));
            logo4.setIcon(icono);

        } 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        funcion_especial = new javax.swing.JPopupMenu();
        editar = new javax.swing.JMenuItem();
        eliminar = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        paneles = new javax.swing.JTabbedPane();
        cartelera = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista_funciones_especial = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        agregar_pelicula = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        tipo_pelicula_agregar = new javax.swing.JComboBox<>();
        nombre_pelicula_agregar = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        seleccionar_imagen_agregar = new javax.swing.JButton();
        imagen_agregar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_peliculas = new javax.swing.JTable();
        palabra_buscar = new javax.swing.JTextField();
        buscar_pelicula = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        nombre_pelicula = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        tipo_pelicula = new javax.swing.JComboBox<>();
        seleccionar_imagen = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        actualizar_pelicula = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        activar_pelicula = new javax.swing.JRadioButton();
        superestreno_pelicula = new javax.swing.JRadioButton();
        jLabel28 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lista_funciones = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        sala_funcion = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        agregar_funcion = new javax.swing.JButton();
        activar_funcion = new javax.swing.JRadioButton();
        jLabel29 = new javax.swing.JLabel();
        hora_funcion = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        minutos_funcion = new javax.swing.JComboBox<>();
        ampm_funcion = new javax.swing.JComboBox<>();
        actualizar_funcion = new javax.swing.JButton();
        valor_funcion_especial = new javax.swing.JTextField();
        fecha_funcion = new com.toedter.calendar.JDateChooser();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        agregar_funcion_especial = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        Franja_seleccionada_funcion = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        imagen_pelicula = new javax.swing.JLabel();
        repogeneral = new javax.swing.JPanel();
        panel_reporte_general = new javax.swing.JPanel();
        repoentradas = new javax.swing.JPanel();
        panel_reporte_entradas = new javax.swing.JPanel();
        repoventas = new javax.swing.JPanel();
        panel_reporte_ventas = new javax.swing.JPanel();
        tarjeta = new javax.swing.JPanel();
        panel_tarjetas = new javax.swing.JPanel();
        precios = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        actualizar_precio = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        gafas = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lunes2d = new javax.swing.JTextField();
        martes2d = new javax.swing.JTextField();
        miercoles2d = new javax.swing.JTextField();
        jueves2d = new javax.swing.JTextField();
        viernes2d = new javax.swing.JTextField();
        sabado2d = new javax.swing.JTextField();
        domingo2d = new javax.swing.JTextField();
        lunes3d = new javax.swing.JTextField();
        martes3d = new javax.swing.JTextField();
        miercoles3d = new javax.swing.JTextField();
        jueves3d = new javax.swing.JTextField();
        viernes3d = new javax.swing.JTextField();
        sabado3d = new javax.swing.JTextField();
        domingo3d = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        domingo_tarjeta2d = new javax.swing.JTextField();
        lunes_tarjeta2d = new javax.swing.JTextField();
        martes_tarjeta2d = new javax.swing.JTextField();
        miercoles_tarjeta2d = new javax.swing.JTextField();
        jueves_tarjeta2d = new javax.swing.JTextField();
        viernes_tarjeta2d = new javax.swing.JTextField();
        sabado_tarjeta2d = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        domingo_tarjeta3d = new javax.swing.JTextField();
        lunes_tarjeta3d = new javax.swing.JTextField();
        martes_tarjeta3d = new javax.swing.JTextField();
        miercoles_tarjeta3d = new javax.swing.JTextField();
        jueves_tarjeta3d = new javax.swing.JTextField();
        viernes_tarjeta3d = new javax.swing.JTextField();
        sabado_tarjeta3d = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pref_lunes2d = new javax.swing.JTextField();
        pref_martes2d = new javax.swing.JTextField();
        pref_miercoles2d = new javax.swing.JTextField();
        pref_jueves2d = new javax.swing.JTextField();
        pref_viernes2d = new javax.swing.JTextField();
        pref_sabado2d = new javax.swing.JTextField();
        pref_domingo2d = new javax.swing.JTextField();
        pref_lunes3d = new javax.swing.JTextField();
        pref_martes3d = new javax.swing.JTextField();
        pref_miercoles3d = new javax.swing.JTextField();
        pref_jueves3d = new javax.swing.JTextField();
        pref_viernes3d = new javax.swing.JTextField();
        pref_sabado3d = new javax.swing.JTextField();
        pref_domingo3d = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pref_domingo_tarjeta2d = new javax.swing.JTextField();
        pref_lunes_tarjeta2d = new javax.swing.JTextField();
        pref_martes_tarjeta2d = new javax.swing.JTextField();
        pref_miercoles_tarjeta2d = new javax.swing.JTextField();
        pref_jueves_tarjeta2d = new javax.swing.JTextField();
        pref_viernes_tarjeta2d = new javax.swing.JTextField();
        pref_sabado_tarjeta2d = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        pref_domingo_tarjeta3d = new javax.swing.JTextField();
        pref_lunes_tarjeta3d = new javax.swing.JTextField();
        pref_martes_tarjeta3d = new javax.swing.JTextField();
        pref_miercoles_tarjeta3d = new javax.swing.JTextField();
        pref_jueves_tarjeta3d = new javax.swing.JTextField();
        pref_viernes_tarjeta3d = new javax.swing.JTextField();
        pref_sabado_tarjeta3d = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        MitadPrecio_domingo = new javax.swing.JRadioButton();
        MitadPrecio_lunes = new javax.swing.JRadioButton();
        MitadPrecio_martes = new javax.swing.JRadioButton();
        MitadPrecio_miercoles = new javax.swing.JRadioButton();
        MitadPrecio_jueves = new javax.swing.JRadioButton();
        MitadPrecio_viernes = new javax.swing.JRadioButton();
        MitadPrecio_sabado = new javax.swing.JRadioButton();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        Franja_seleccionada = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        nombre_convenio = new javax.swing.JTextField();
        valor_convenio2d = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        convenio_activo = new javax.swing.JCheckBox();
        lista_convenios = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        agregar_convenio = new javax.swing.JButton();
        actualizar_convenio = new javax.swing.JButton();
        editar_convenio = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        color_select = new javax.swing.JButton();
        valor_convenio3d = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        ajustes = new javax.swing.JPanel();
        contrasena_anterior = new javax.swing.JPasswordField();
        contrasena1 = new javax.swing.JPasswordField();
        contrasena2 = new javax.swing.JPasswordField();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        actualizar_contra = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        logo1 = new javax.swing.JLabel();
        logo4 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        panel_resolucion = new javax.swing.JPanel();
        panel_usuario_admin = new javax.swing.JPanel();
        btn_inicio = new javax.swing.JButton();
        logo2 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        logo3 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        editar.setText("editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        funcion_especial.add(editar);

        eliminar.setText("eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        funcion_especial.add(eliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("administrador");
        setExtendedState(1);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ADMINISTRADOR");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 440, -1));

        paneles.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                panelesStateChanged(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("FUNCIONES ESPECIALES");

        lista_funciones_especial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "HORA", "SALA", "FECHA", "PRECIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista_funciones_especial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lista_funciones_especialMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(lista_funciones_especial);
        if (lista_funciones_especial.getColumnModel().getColumnCount() > 0) {
            lista_funciones_especial.getColumnModel().getColumn(0).setResizable(false);
            lista_funciones_especial.getColumnModel().getColumn(0).setPreferredWidth(80);
            lista_funciones_especial.getColumnModel().getColumn(1).setResizable(false);
            lista_funciones_especial.getColumnModel().getColumn(1).setPreferredWidth(70);
            lista_funciones_especial.getColumnModel().getColumn(2).setResizable(false);
            lista_funciones_especial.getColumnModel().getColumn(2).setPreferredWidth(120);
            lista_funciones_especial.getColumnModel().getColumn(3).setResizable(false);
            lista_funciones_especial.getColumnModel().getColumn(3).setPreferredWidth(90);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(51, 255, 255));

        agregar_pelicula.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        agregar_pelicula.setText("AGREGAR");
        agregar_pelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_peliculaActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("TIPO");

        tipo_pelicula_agregar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tipo_pelicula_agregar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2D", "3D" }));

        nombre_pelicula_agregar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nombre_pelicula_agregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombre_pelicula_agregarKeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("NOMBRE");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("IMAGEN");

        seleccionar_imagen_agregar.setText("SELECCIONAR");
        seleccionar_imagen_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionar_imagen_agregarActionPerformed(evt);
            }
        });

        imagen_agregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagen_agregar.setText("imagen_agregar");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel19)
                        .addComponent(jLabel18))
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addComponent(nombre_pelicula_agregar)
                .addGap(49, 49, 49))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(agregar_pelicula))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seleccionar_imagen_agregar)
                            .addComponent(tipo_pelicula_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(imagen_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(29, 32, Short.MAX_VALUE)
                        .addComponent(jLabel18))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nombre_pelicula_agregar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel16))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(tipo_pelicula_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(seleccionar_imagen_agregar)
                            .addComponent(jLabel19))
                        .addGap(32, 32, 32)
                        .addComponent(agregar_pelicula))
                    .addComponent(imagen_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        lista_peliculas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lista_peliculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "TIPO", "ESTADO", "EDITAR", "ELIMINAR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista_peliculas.setRowHeight(30);
        lista_peliculas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_peliculasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lista_peliculas);
        if (lista_peliculas.getColumnModel().getColumnCount() > 0) {
            lista_peliculas.getColumnModel().getColumn(0).setResizable(false);
            lista_peliculas.getColumnModel().getColumn(0).setPreferredWidth(230);
            lista_peliculas.getColumnModel().getColumn(1).setResizable(false);
            lista_peliculas.getColumnModel().getColumn(1).setPreferredWidth(25);
            lista_peliculas.getColumnModel().getColumn(2).setResizable(false);
            lista_peliculas.getColumnModel().getColumn(2).setPreferredWidth(45);
            lista_peliculas.getColumnModel().getColumn(3).setResizable(false);
            lista_peliculas.getColumnModel().getColumn(3).setPreferredWidth(50);
            lista_peliculas.getColumnModel().getColumn(4).setResizable(false);
            lista_peliculas.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        palabra_buscar.setText("ingrese aqui la palagra clave");
        palabra_buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                palabra_buscarMousePressed(evt);
            }
        });

        buscar_pelicula.setText("BUSCAR");
        buscar_pelicula.setEnabled(false);
        buscar_pelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_peliculaActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nombre_pelicula.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nombre_pelicula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombre_peliculaKeyTyped(evt);
            }
        });
        jPanel7.add(nombre_pelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 37, 518, 43));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setText("NOMBRE");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 0, 83, 31));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("TIPO");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 100, -1, -1));

        tipo_pelicula.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        tipo_pelicula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2D", "3D" }));
        jPanel7.add(tipo_pelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 91, -1, 39));

        seleccionar_imagen.setText("SELECCIONAR");
        seleccionar_imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionar_imagenActionPerformed(evt);
            }
        });
        jPanel7.add(seleccionar_imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 99, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setText("IMAGEN");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 100, -1, -1));

        actualizar_pelicula.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        actualizar_pelicula.setText("ACTUALIZAR");
        actualizar_pelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizar_peliculaActionPerformed(evt);
            }
        });
        jPanel7.add(actualizar_pelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 146, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel27.setText("ACTIVA");
        jPanel7.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 98, -1, -1));

        activar_pelicula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mal.png"))); // NOI18N
        activar_pelicula.setIconTextGap(0);
        activar_pelicula.setRequestFocusEnabled(false);
        activar_pelicula.setRolloverEnabled(false);
        activar_pelicula.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ok.png"))); // NOI18N
        jPanel7.add(activar_pelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 91, -1, 44));
        jPanel7.add(superestreno_pelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 570, -1, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("SUPER ESTRENO");
        jPanel7.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 570, -1, -1));

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel20.setText("FUNCIONES");
        jPanel8.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, 17));

        lista_funciones.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lista_funciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "HORA", "SALA", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista_funciones.setRowHeight(30);
        lista_funciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_funcionesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(lista_funciones);
        if (lista_funciones.getColumnModel().getColumnCount() > 0) {
            lista_funciones.getColumnModel().getColumn(0).setResizable(false);
            lista_funciones.getColumnModel().getColumn(0).setPreferredWidth(120);
            lista_funciones.getColumnModel().getColumn(1).setResizable(false);
            lista_funciones.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel8.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 46, 292, 290));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("HORAS");
        jPanel8.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 46, 52, -1));

        sala_funcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sala_funcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sala 1", "Sala 2", "Sala 3", "Sala 4" }));
        jPanel8.add(sala_funcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 143, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("SALA");
        jPanel8.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 146, -1, -1));

        agregar_funcion.setText("AGREGAR");
        agregar_funcion.setEnabled(false);
        agregar_funcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_funcionActionPerformed(evt);
            }
        });
        jPanel8.add(agregar_funcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 219, -1, -1));

        activar_funcion.setSelected(true);
        activar_funcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mal.png"))); // NOI18N
        activar_funcion.setIconTextGap(0);
        activar_funcion.setRequestFocusEnabled(false);
        activar_funcion.setRolloverEnabled(false);
        activar_funcion.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ok.png"))); // NOI18N
        jPanel8.add(activar_funcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(411, 177, -1, 33));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("ACTIVA");
        jPanel8.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, -1, 28));

        hora_funcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hora_funcion.setMaximumRowCount(12);
        hora_funcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jPanel8.add(hora_funcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 67, 52, 32));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("MINUTOS");
        jPanel8.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 46, -1, -1));

        minutos_funcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        minutos_funcion.setMaximumRowCount(12);
        minutos_funcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
        jPanel8.add(minutos_funcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 67, 52, 32));

        ampm_funcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ampm_funcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));
        ampm_funcion.setSelectedIndex(1);
        jPanel8.add(ampm_funcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(472, 67, 52, 32));

        actualizar_funcion.setText("ACTUALIZAR");
        actualizar_funcion.setEnabled(false);
        actualizar_funcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizar_funcionActionPerformed(evt);
            }
        });
        jPanel8.add(actualizar_funcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 219, -1, -1));

        valor_funcion_especial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valor_funcion_especialKeyTyped(evt);
            }
        });
        jPanel8.add(valor_funcion_especial, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 310, 143, -1));
        jPanel8.add(fecha_funcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 284, 143, -1));

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setText("FECHA");
        jPanel8.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 290, -1, -1));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("VALOR");
        jPanel8.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 313, 41, -1));

        agregar_funcion_especial.setText("AGREGAR");
        agregar_funcion_especial.setEnabled(false);
        agregar_funcion_especial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_funcion_especialActionPerformed(evt);
            }
        });
        jPanel8.add(agregar_funcion_especial, new org.netbeans.lib.awtextra.AbsoluteConstraints(389, 336, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("FUNCIONES ESPECIALES");
        jPanel8.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 261, -1, -1));

        Franja_seleccionada_funcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MATINAL", "VESPERTINA", "NOCTURNA", "ANY TIME" }));
        jPanel8.add(Franja_seleccionada_funcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 100, 20));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("FRANJA");
        jPanel8.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 115, -1, -1));

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 530, 370));

        imagen_pelicula.setBackground(new java.awt.Color(255, 0, 51));
        imagen_pelicula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagen_pelicula.setText("imagen_pelicula");

        javax.swing.GroupLayout carteleraLayout = new javax.swing.GroupLayout(cartelera);
        cartelera.setLayout(carteleraLayout);
        carteleraLayout.setHorizontalGroup(
            carteleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carteleraLayout.createSequentialGroup()
                .addGroup(carteleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(carteleraLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(carteleraLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(palabra_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(buscar_pelicula))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(carteleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(carteleraLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(carteleraLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(imagen_pelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(27, Short.MAX_VALUE))))
        );
        carteleraLayout.setVerticalGroup(
            carteleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(carteleraLayout.createSequentialGroup()
                .addGroup(carteleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(carteleraLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(carteleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(carteleraLayout.createSequentialGroup()
                                .addGap(316, 316, 316)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(carteleraLayout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addGroup(carteleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(palabra_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buscar_pelicula))
                                .addGap(27, 27, 27)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                    .addGroup(carteleraLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(carteleraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(carteleraLayout.createSequentialGroup()
                                .addComponent(imagen_pelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        paneles.addTab("CARTELERA", cartelera);

        javax.swing.GroupLayout panel_reporte_generalLayout = new javax.swing.GroupLayout(panel_reporte_general);
        panel_reporte_general.setLayout(panel_reporte_generalLayout);
        panel_reporte_generalLayout.setHorizontalGroup(
            panel_reporte_generalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1310, Short.MAX_VALUE)
        );
        panel_reporte_generalLayout.setVerticalGroup(
            panel_reporte_generalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout repogeneralLayout = new javax.swing.GroupLayout(repogeneral);
        repogeneral.setLayout(repogeneralLayout);
        repogeneralLayout.setHorizontalGroup(
            repogeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1335, Short.MAX_VALUE)
            .addGroup(repogeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(repogeneralLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel_reporte_general, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        repogeneralLayout.setVerticalGroup(
            repogeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
            .addGroup(repogeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(repogeneralLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel_reporte_general, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        paneles.addTab("REPORTE_GENERAL", repogeneral);

        repoentradas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout panel_reporte_entradasLayout = new javax.swing.GroupLayout(panel_reporte_entradas);
        panel_reporte_entradas.setLayout(panel_reporte_entradasLayout);
        panel_reporte_entradasLayout.setHorizontalGroup(
            panel_reporte_entradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1310, Short.MAX_VALUE)
        );
        panel_reporte_entradasLayout.setVerticalGroup(
            panel_reporte_entradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        repoentradas.add(panel_reporte_entradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1310, 590));

        paneles.addTab("REPORTE_ENTRADAS", repoentradas);

        repoventas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout panel_reporte_ventasLayout = new javax.swing.GroupLayout(panel_reporte_ventas);
        panel_reporte_ventas.setLayout(panel_reporte_ventasLayout);
        panel_reporte_ventasLayout.setHorizontalGroup(
            panel_reporte_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1310, Short.MAX_VALUE)
        );
        panel_reporte_ventasLayout.setVerticalGroup(
            panel_reporte_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        repoventas.add(panel_reporte_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1310, 600));

        paneles.addTab("REPORTE_VENTAS", repoventas);

        tarjeta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout panel_tarjetasLayout = new javax.swing.GroupLayout(panel_tarjetas);
        panel_tarjetas.setLayout(panel_tarjetasLayout);
        panel_tarjetasLayout.setHorizontalGroup(
            panel_tarjetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1310, Short.MAX_VALUE)
        );
        panel_tarjetasLayout.setVerticalGroup(
            panel_tarjetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        tarjeta.add(panel_tarjetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1310, 600));

        paneles.addTab("TARJETAS", tarjeta);

        jPanel9.setBackground(new java.awt.Color(204, 255, 204));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("LUNES");
        jPanel9.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 120, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("MARTES");
        jPanel9.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 110, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("MIERCOLES");
        jPanel9.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("JUEVES");
        jPanel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 120, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("VIERNES");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 140, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("SABADO");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 130, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("DOMINGO");
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 120, -1));

        actualizar_precio.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        actualizar_precio.setText("ACTUALIZAR");
        actualizar_precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizar_precioActionPerformed(evt);
            }
        });
        jPanel9.add(actualizar_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 520, 220, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("PRECIOS");
        jPanel9.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 270, 45));

        jPanel12.setBackground(new java.awt.Color(102, 255, 102));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel39.setText("GAFAS");

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gafas.png"))); // NOI18N

        gafas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        gafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                gafasKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addGap(26, 26, 26)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(gafas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gafas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel9.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, -1, 60));

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lunes2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lunes2d.setMaximumSize(new java.awt.Dimension(60, 30));
        lunes2d.setMinimumSize(new java.awt.Dimension(60, 30));
        lunes2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lunes2dKeyTyped(evt);
            }
        });
        jPanel2.add(lunes2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 65, 30));

        martes2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        martes2d.setMaximumSize(new java.awt.Dimension(60, 30));
        martes2d.setMinimumSize(new java.awt.Dimension(60, 30));
        martes2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                martes2dKeyTyped(evt);
            }
        });
        jPanel2.add(martes2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 65, 30));

        miercoles2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        miercoles2d.setMaximumSize(new java.awt.Dimension(60, 30));
        miercoles2d.setMinimumSize(new java.awt.Dimension(60, 30));
        miercoles2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                miercoles2dKeyTyped(evt);
            }
        });
        jPanel2.add(miercoles2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 65, 30));

        jueves2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jueves2d.setMaximumSize(new java.awt.Dimension(60, 30));
        jueves2d.setMinimumSize(new java.awt.Dimension(60, 30));
        jueves2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jueves2dKeyTyped(evt);
            }
        });
        jPanel2.add(jueves2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 65, 30));

        viernes2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        viernes2d.setMaximumSize(new java.awt.Dimension(60, 30));
        viernes2d.setMinimumSize(new java.awt.Dimension(60, 30));
        viernes2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                viernes2dKeyTyped(evt);
            }
        });
        jPanel2.add(viernes2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 65, 30));

        sabado2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sabado2d.setMaximumSize(new java.awt.Dimension(60, 30));
        sabado2d.setMinimumSize(new java.awt.Dimension(60, 30));
        sabado2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sabado2dKeyTyped(evt);
            }
        });
        jPanel2.add(sabado2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 65, 30));

        domingo2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        domingo2d.setMaximumSize(new java.awt.Dimension(60, 30));
        domingo2d.setMinimumSize(new java.awt.Dimension(60, 30));
        domingo2d.setPreferredSize(new java.awt.Dimension(60, 28));
        domingo2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                domingo2dKeyTyped(evt);
            }
        });
        jPanel2.add(domingo2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 65, 30));

        lunes3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lunes3d.setMaximumSize(new java.awt.Dimension(60, 30));
        lunes3d.setMinimumSize(new java.awt.Dimension(60, 30));
        lunes3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lunes3dKeyTyped(evt);
            }
        });
        jPanel2.add(lunes3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 65, 30));

        martes3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        martes3d.setMaximumSize(new java.awt.Dimension(60, 30));
        martes3d.setMinimumSize(new java.awt.Dimension(60, 30));
        martes3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                martes3dKeyTyped(evt);
            }
        });
        jPanel2.add(martes3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 65, 30));

        miercoles3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        miercoles3d.setMaximumSize(new java.awt.Dimension(60, 30));
        miercoles3d.setMinimumSize(new java.awt.Dimension(60, 30));
        miercoles3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                miercoles3dKeyTyped(evt);
            }
        });
        jPanel2.add(miercoles3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 65, 30));

        jueves3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jueves3d.setMaximumSize(new java.awt.Dimension(60, 30));
        jueves3d.setMinimumSize(new java.awt.Dimension(60, 30));
        jueves3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jueves3dKeyTyped(evt);
            }
        });
        jPanel2.add(jueves3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 65, 30));

        viernes3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        viernes3d.setMaximumSize(new java.awt.Dimension(60, 30));
        viernes3d.setMinimumSize(new java.awt.Dimension(60, 30));
        viernes3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                viernes3dKeyTyped(evt);
            }
        });
        jPanel2.add(viernes3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 65, 30));

        sabado3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sabado3d.setMaximumSize(new java.awt.Dimension(60, 30));
        sabado3d.setMinimumSize(new java.awt.Dimension(60, 30));
        sabado3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sabado3dKeyTyped(evt);
            }
        });
        jPanel2.add(sabado3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 65, 30));

        domingo3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        domingo3d.setMaximumSize(new java.awt.Dimension(60, 30));
        domingo3d.setMinimumSize(new java.awt.Dimension(60, 30));
        domingo3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                domingo3dKeyTyped(evt);
            }
        });
        jPanel2.add(domingo3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 65, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setText("2D");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setText("3D");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        jPanel1.setBackground(new java.awt.Color(102, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        domingo_tarjeta2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        domingo_tarjeta2d.setMaximumSize(new java.awt.Dimension(60, 30));
        domingo_tarjeta2d.setMinimumSize(new java.awt.Dimension(60, 30));
        domingo_tarjeta2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                domingo_tarjeta2dKeyTyped(evt);
            }
        });
        jPanel1.add(domingo_tarjeta2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 65, 30));

        lunes_tarjeta2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lunes_tarjeta2d.setMaximumSize(new java.awt.Dimension(60, 30));
        lunes_tarjeta2d.setMinimumSize(new java.awt.Dimension(60, 30));
        lunes_tarjeta2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lunes_tarjeta2dKeyTyped(evt);
            }
        });
        jPanel1.add(lunes_tarjeta2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 65, 30));

        martes_tarjeta2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        martes_tarjeta2d.setMaximumSize(new java.awt.Dimension(60, 30));
        martes_tarjeta2d.setMinimumSize(new java.awt.Dimension(60, 30));
        martes_tarjeta2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                martes_tarjeta2dKeyTyped(evt);
            }
        });
        jPanel1.add(martes_tarjeta2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 65, 30));

        miercoles_tarjeta2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        miercoles_tarjeta2d.setMaximumSize(new java.awt.Dimension(60, 30));
        miercoles_tarjeta2d.setMinimumSize(new java.awt.Dimension(60, 30));
        miercoles_tarjeta2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                miercoles_tarjeta2dKeyTyped(evt);
            }
        });
        jPanel1.add(miercoles_tarjeta2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 65, 30));

        jueves_tarjeta2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jueves_tarjeta2d.setMaximumSize(new java.awt.Dimension(60, 30));
        jueves_tarjeta2d.setMinimumSize(new java.awt.Dimension(60, 30));
        jueves_tarjeta2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jueves_tarjeta2dKeyTyped(evt);
            }
        });
        jPanel1.add(jueves_tarjeta2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 65, 30));

        viernes_tarjeta2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        viernes_tarjeta2d.setMaximumSize(new java.awt.Dimension(60, 30));
        viernes_tarjeta2d.setMinimumSize(new java.awt.Dimension(60, 30));
        viernes_tarjeta2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                viernes_tarjeta2dKeyTyped(evt);
            }
        });
        jPanel1.add(viernes_tarjeta2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 65, 30));

        sabado_tarjeta2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sabado_tarjeta2d.setMaximumSize(new java.awt.Dimension(60, 30));
        sabado_tarjeta2d.setMinimumSize(new java.awt.Dimension(60, 30));
        sabado_tarjeta2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sabado_tarjeta2dKeyTyped(evt);
            }
        });
        jPanel1.add(sabado_tarjeta2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 65, 30));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel44.setText("TARJETA");
        jPanel1.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, -1));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel46.setText("2D");
        jPanel1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel47.setText("3D");
        jPanel1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        domingo_tarjeta3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        domingo_tarjeta3d.setMaximumSize(new java.awt.Dimension(60, 30));
        domingo_tarjeta3d.setMinimumSize(new java.awt.Dimension(60, 30));
        domingo_tarjeta3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                domingo_tarjeta3dKeyTyped(evt);
            }
        });
        jPanel1.add(domingo_tarjeta3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 65, 30));

        lunes_tarjeta3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lunes_tarjeta3d.setMaximumSize(new java.awt.Dimension(60, 30));
        lunes_tarjeta3d.setMinimumSize(new java.awt.Dimension(60, 30));
        lunes_tarjeta3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lunes_tarjeta3dKeyTyped(evt);
            }
        });
        jPanel1.add(lunes_tarjeta3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 65, 30));

        martes_tarjeta3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        martes_tarjeta3d.setMaximumSize(new java.awt.Dimension(60, 30));
        martes_tarjeta3d.setMinimumSize(new java.awt.Dimension(60, 30));
        martes_tarjeta3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                martes_tarjeta3dKeyTyped(evt);
            }
        });
        jPanel1.add(martes_tarjeta3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 65, 30));

        miercoles_tarjeta3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        miercoles_tarjeta3d.setMaximumSize(new java.awt.Dimension(60, 30));
        miercoles_tarjeta3d.setMinimumSize(new java.awt.Dimension(60, 30));
        miercoles_tarjeta3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                miercoles_tarjeta3dKeyTyped(evt);
            }
        });
        jPanel1.add(miercoles_tarjeta3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 65, 30));

        jueves_tarjeta3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jueves_tarjeta3d.setMaximumSize(new java.awt.Dimension(60, 30));
        jueves_tarjeta3d.setMinimumSize(new java.awt.Dimension(60, 30));
        jueves_tarjeta3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jueves_tarjeta3dKeyTyped(evt);
            }
        });
        jPanel1.add(jueves_tarjeta3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 65, 30));

        viernes_tarjeta3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        viernes_tarjeta3d.setMaximumSize(new java.awt.Dimension(60, 30));
        viernes_tarjeta3d.setMinimumSize(new java.awt.Dimension(60, 30));
        viernes_tarjeta3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                viernes_tarjeta3dKeyTyped(evt);
            }
        });
        jPanel1.add(viernes_tarjeta3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 65, 30));

        sabado_tarjeta3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sabado_tarjeta3d.setMaximumSize(new java.awt.Dimension(60, 30));
        sabado_tarjeta3d.setMinimumSize(new java.awt.Dimension(60, 30));
        sabado_tarjeta3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sabado_tarjeta3dKeyTyped(evt);
            }
        });
        jPanel1.add(sabado_tarjeta3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 65, 30));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 160, 400));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("NORMAL");
        jPanel2.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 190, 45));

        jPanel9.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 320, 450));

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setBorder(new javax.swing.border.MatteBorder(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borde.png")))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pref_lunes2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_lunes2d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_lunes2d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_lunes2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_lunes2dKeyTyped(evt);
            }
        });
        jPanel3.add(pref_lunes2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 65, 30));

        pref_martes2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_martes2d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_martes2d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_martes2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_martes2dKeyTyped(evt);
            }
        });
        jPanel3.add(pref_martes2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 65, 30));

        pref_miercoles2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_miercoles2d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_miercoles2d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_miercoles2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_miercoles2dKeyTyped(evt);
            }
        });
        jPanel3.add(pref_miercoles2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 65, 30));

        pref_jueves2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_jueves2d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_jueves2d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_jueves2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_jueves2dKeyTyped(evt);
            }
        });
        jPanel3.add(pref_jueves2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 65, 30));

        pref_viernes2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_viernes2d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_viernes2d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_viernes2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_viernes2dKeyTyped(evt);
            }
        });
        jPanel3.add(pref_viernes2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 65, 30));

        pref_sabado2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_sabado2d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_sabado2d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_sabado2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_sabado2dKeyTyped(evt);
            }
        });
        jPanel3.add(pref_sabado2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 65, 30));

        pref_domingo2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_domingo2d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_domingo2d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_domingo2d.setPreferredSize(new java.awt.Dimension(60, 28));
        pref_domingo2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_domingo2dKeyTyped(evt);
            }
        });
        jPanel3.add(pref_domingo2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 65, 30));

        pref_lunes3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_lunes3d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_lunes3d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_lunes3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_lunes3dKeyTyped(evt);
            }
        });
        jPanel3.add(pref_lunes3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 65, 30));

        pref_martes3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_martes3d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_martes3d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_martes3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_martes3dKeyTyped(evt);
            }
        });
        jPanel3.add(pref_martes3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 65, 30));

        pref_miercoles3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_miercoles3d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_miercoles3d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_miercoles3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_miercoles3dKeyTyped(evt);
            }
        });
        jPanel3.add(pref_miercoles3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 65, 30));

        pref_jueves3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_jueves3d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_jueves3d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_jueves3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_jueves3dKeyTyped(evt);
            }
        });
        jPanel3.add(pref_jueves3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 65, 30));

        pref_viernes3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_viernes3d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_viernes3d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_viernes3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_viernes3dKeyTyped(evt);
            }
        });
        jPanel3.add(pref_viernes3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 65, 30));

        pref_sabado3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_sabado3d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_sabado3d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_sabado3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_sabado3dKeyTyped(evt);
            }
        });
        jPanel3.add(pref_sabado3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 65, 30));

        pref_domingo3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_domingo3d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_domingo3d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_domingo3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_domingo3dKeyTyped(evt);
            }
        });
        jPanel3.add(pref_domingo3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 65, 30));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel50.setText("2D");
        jPanel3.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel51.setText("3D");
        jPanel3.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        jPanel4.setBackground(new java.awt.Color(102, 255, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pref_domingo_tarjeta2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_domingo_tarjeta2d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_domingo_tarjeta2d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_domingo_tarjeta2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_domingo_tarjeta2dKeyTyped(evt);
            }
        });
        jPanel4.add(pref_domingo_tarjeta2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 65, 30));

        pref_lunes_tarjeta2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_lunes_tarjeta2d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_lunes_tarjeta2d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_lunes_tarjeta2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_lunes_tarjeta2dKeyTyped(evt);
            }
        });
        jPanel4.add(pref_lunes_tarjeta2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 65, 30));

        pref_martes_tarjeta2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_martes_tarjeta2d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_martes_tarjeta2d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_martes_tarjeta2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_martes_tarjeta2dKeyTyped(evt);
            }
        });
        jPanel4.add(pref_martes_tarjeta2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 65, 30));

        pref_miercoles_tarjeta2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_miercoles_tarjeta2d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_miercoles_tarjeta2d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_miercoles_tarjeta2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_miercoles_tarjeta2dKeyTyped(evt);
            }
        });
        jPanel4.add(pref_miercoles_tarjeta2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 65, 30));

        pref_jueves_tarjeta2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_jueves_tarjeta2d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_jueves_tarjeta2d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_jueves_tarjeta2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_jueves_tarjeta2dKeyTyped(evt);
            }
        });
        jPanel4.add(pref_jueves_tarjeta2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 65, 30));

        pref_viernes_tarjeta2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_viernes_tarjeta2d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_viernes_tarjeta2d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_viernes_tarjeta2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_viernes_tarjeta2dKeyTyped(evt);
            }
        });
        jPanel4.add(pref_viernes_tarjeta2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 65, 30));

        pref_sabado_tarjeta2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_sabado_tarjeta2d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_sabado_tarjeta2d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_sabado_tarjeta2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_sabado_tarjeta2dKeyTyped(evt);
            }
        });
        jPanel4.add(pref_sabado_tarjeta2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 65, 30));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel52.setText("TARJETA");
        jPanel4.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, -1));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel53.setText("2D");
        jPanel4.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel54.setText("3D");
        jPanel4.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        pref_domingo_tarjeta3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_domingo_tarjeta3d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_domingo_tarjeta3d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_domingo_tarjeta3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_domingo_tarjeta3dKeyTyped(evt);
            }
        });
        jPanel4.add(pref_domingo_tarjeta3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 65, 30));

        pref_lunes_tarjeta3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_lunes_tarjeta3d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_lunes_tarjeta3d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_lunes_tarjeta3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_lunes_tarjeta3dKeyTyped(evt);
            }
        });
        jPanel4.add(pref_lunes_tarjeta3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 65, 30));

        pref_martes_tarjeta3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_martes_tarjeta3d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_martes_tarjeta3d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_martes_tarjeta3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_martes_tarjeta3dKeyTyped(evt);
            }
        });
        jPanel4.add(pref_martes_tarjeta3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 65, 30));

        pref_miercoles_tarjeta3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_miercoles_tarjeta3d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_miercoles_tarjeta3d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_miercoles_tarjeta3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_miercoles_tarjeta3dKeyTyped(evt);
            }
        });
        jPanel4.add(pref_miercoles_tarjeta3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 65, 30));

        pref_jueves_tarjeta3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_jueves_tarjeta3d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_jueves_tarjeta3d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_jueves_tarjeta3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_jueves_tarjeta3dKeyTyped(evt);
            }
        });
        jPanel4.add(pref_jueves_tarjeta3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 65, 30));

        pref_viernes_tarjeta3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_viernes_tarjeta3d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_viernes_tarjeta3d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_viernes_tarjeta3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_viernes_tarjeta3dKeyTyped(evt);
            }
        });
        jPanel4.add(pref_viernes_tarjeta3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 65, 30));

        pref_sabado_tarjeta3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pref_sabado_tarjeta3d.setMaximumSize(new java.awt.Dimension(60, 30));
        pref_sabado_tarjeta3d.setMinimumSize(new java.awt.Dimension(60, 30));
        pref_sabado_tarjeta3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pref_sabado_tarjeta3dKeyTyped(evt);
            }
        });
        jPanel4.add(pref_sabado_tarjeta3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 65, 30));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 160, 400));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("PREFERENCIAL");
        jPanel3.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 190, 45));

        jPanel9.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 320, 450));

        MitadPrecio_domingo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mal.png"))); // NOI18N
        MitadPrecio_domingo.setIconTextGap(0);
        MitadPrecio_domingo.setRequestFocusEnabled(false);
        MitadPrecio_domingo.setRolloverEnabled(false);
        MitadPrecio_domingo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ok.png"))); // NOI18N
        MitadPrecio_domingo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MitadPrecio_domingoActionPerformed(evt);
            }
        });
        jPanel9.add(MitadPrecio_domingo, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 150, -1, 40));

        MitadPrecio_lunes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mal.png"))); // NOI18N
        MitadPrecio_lunes.setIconTextGap(0);
        MitadPrecio_lunes.setRequestFocusEnabled(false);
        MitadPrecio_lunes.setRolloverEnabled(false);
        MitadPrecio_lunes.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ok.png"))); // NOI18N
        MitadPrecio_lunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MitadPrecio_lunesActionPerformed(evt);
            }
        });
        jPanel9.add(MitadPrecio_lunes, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 200, -1, 40));

        MitadPrecio_martes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mal.png"))); // NOI18N
        MitadPrecio_martes.setIconTextGap(0);
        MitadPrecio_martes.setRequestFocusEnabled(false);
        MitadPrecio_martes.setRolloverEnabled(false);
        MitadPrecio_martes.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ok.png"))); // NOI18N
        MitadPrecio_martes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MitadPrecio_martesActionPerformed(evt);
            }
        });
        jPanel9.add(MitadPrecio_martes, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 250, -1, 40));

        MitadPrecio_miercoles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mal.png"))); // NOI18N
        MitadPrecio_miercoles.setIconTextGap(0);
        MitadPrecio_miercoles.setRequestFocusEnabled(false);
        MitadPrecio_miercoles.setRolloverEnabled(false);
        MitadPrecio_miercoles.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ok.png"))); // NOI18N
        MitadPrecio_miercoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MitadPrecio_miercolesActionPerformed(evt);
            }
        });
        jPanel9.add(MitadPrecio_miercoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 300, -1, 40));

        MitadPrecio_jueves.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mal.png"))); // NOI18N
        MitadPrecio_jueves.setIconTextGap(0);
        MitadPrecio_jueves.setRequestFocusEnabled(false);
        MitadPrecio_jueves.setRolloverEnabled(false);
        MitadPrecio_jueves.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ok.png"))); // NOI18N
        MitadPrecio_jueves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MitadPrecio_juevesActionPerformed(evt);
            }
        });
        jPanel9.add(MitadPrecio_jueves, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 350, -1, 40));

        MitadPrecio_viernes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mal.png"))); // NOI18N
        MitadPrecio_viernes.setIconTextGap(0);
        MitadPrecio_viernes.setRequestFocusEnabled(false);
        MitadPrecio_viernes.setRolloverEnabled(false);
        MitadPrecio_viernes.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ok.png"))); // NOI18N
        MitadPrecio_viernes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MitadPrecio_viernesActionPerformed(evt);
            }
        });
        jPanel9.add(MitadPrecio_viernes, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 400, -1, 40));

        MitadPrecio_sabado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mal.png"))); // NOI18N
        MitadPrecio_sabado.setIconTextGap(0);
        MitadPrecio_sabado.setRequestFocusEnabled(false);
        MitadPrecio_sabado.setRolloverEnabled(false);
        MitadPrecio_sabado.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ok.png"))); // NOI18N
        MitadPrecio_sabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MitadPrecio_sabadoActionPerformed(evt);
            }
        });
        jPanel9.add(MitadPrecio_sabado, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 450, -1, 40));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("PRECIO");
        jPanel9.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 110, 90, 30));

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("MITAD");
        jPanel9.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 90, 90, 30));

        Franja_seleccionada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MATINAL", "VESPERTINA", "NOCTURNA", "TODO EL DIA" }));
        Franja_seleccionada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Franja_seleccionadaItemStateChanged(evt);
            }
        });
        jPanel9.add(Franja_seleccionada, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 220, 30));

        jPanel10.setBackground(new java.awt.Color(204, 204, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("NOMBRE");
        jPanel10.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        nombre_convenio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nombre_convenio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombre_convenioKeyTyped(evt);
            }
        });
        jPanel10.add(nombre_convenio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 250, 40));

        valor_convenio2d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        valor_convenio2d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valor_convenio2dKeyTyped(evt);
            }
        });
        jPanel10.add(valor_convenio2d, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 120, 40));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setText("VALOR");
        jPanel10.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, 20));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setText("COLOR");
        jPanel10.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, -1, -1));

        convenio_activo.setAlignmentX(0.5F);
        convenio_activo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        convenio_activo.setPreferredSize(new java.awt.Dimension(60, 21));
        jPanel10.add(convenio_activo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 20, 30));

        lista_convenios.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lista_convenios.setMaximumRowCount(12);
        lista_convenios.setFocusable(false);
        jPanel10.add(lista_convenios, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 340, 40));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel30.setText("CONVENIO");
        jPanel10.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        agregar_convenio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        agregar_convenio.setText("AGREGAR");
        agregar_convenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_convenioActionPerformed(evt);
            }
        });
        jPanel10.add(agregar_convenio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 100, 40));

        actualizar_convenio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        actualizar_convenio.setText("actualizar");
        actualizar_convenio.setEnabled(false);
        actualizar_convenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizar_convenioActionPerformed(evt);
            }
        });
        jPanel10.add(actualizar_convenio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 500, 100, 40));

        editar_convenio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        editar_convenio.setText("EDITAR");
        editar_convenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_convenioActionPerformed(evt);
            }
        });
        jPanel10.add(editar_convenio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 110, 40));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setText("<html>Valor a cancelar por la entrada al aplicar el convenio.</html>");
        jPanel10.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 320, 30));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel45.setText("ACTIVO");
        jPanel10.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, -1));

        color_select.setBackground(new java.awt.Color(0, 255, 0));
        color_select.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        color_select.setText("0");
        color_select.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        color_select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                color_selectActionPerformed(evt);
            }
        });
        jPanel10.add(color_select, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 400, 60, 50));

        valor_convenio3d.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        valor_convenio3d.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valor_convenio3dKeyTyped(evt);
            }
        });
        jPanel10.add(valor_convenio3d, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 120, 40));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel48.setText("2D");
        jPanel10.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, -1, -1));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel49.setText("3D");
        jPanel10.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, -1, -1));

        javax.swing.GroupLayout preciosLayout = new javax.swing.GroupLayout(precios);
        precios.setLayout(preciosLayout);
        preciosLayout.setHorizontalGroup(
            preciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(preciosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        preciosLayout.setVerticalGroup(
            preciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(preciosLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(preciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        paneles.addTab("PRECIOS y CONVENIOS", precios);

        ajustes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contrasena_anterior.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        contrasena_anterior.setToolTipText("");
        ajustes.add(contrasena_anterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 43, 236, 30));

        contrasena1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        contrasena1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                contrasena1KeyTyped(evt);
            }
        });
        ajustes.add(contrasena1, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 90, 236, 30));

        contrasena2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ajustes.add(contrasena2, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 131, 236, 30));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setText("CONTRASEÑA NUEVA");
        ajustes.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 92, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setText("CONTRASEÑA ANTERIOR");
        ajustes.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 51, -1, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setText("REPETIR CONTRASEÑA");
        ajustes.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 139, -1, -1));

        actualizar_contra.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        actualizar_contra.setText("ACTUALIZAR");
        actualizar_contra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizar_contraActionPerformed(evt);
            }
        });
        ajustes.add(actualizar_contra, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 166, 46));

        jPanel11.setBackground(new java.awt.Color(204, 255, 204));

        logo1.setText("logo-cine");

        logo4.setText("logo-gearsoft");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel36.setText("LICENCIA DE USO CONCEDIDA A");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel37.setText("DESARROLLADO POR");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logo4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ajustes.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 570, 200));

        panel_resolucion.setBackground(new java.awt.Color(101, 149, 244));

        javax.swing.GroupLayout panel_resolucionLayout = new javax.swing.GroupLayout(panel_resolucion);
        panel_resolucion.setLayout(panel_resolucionLayout);
        panel_resolucionLayout.setHorizontalGroup(
            panel_resolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
        );
        panel_resolucionLayout.setVerticalGroup(
            panel_resolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        ajustes.add(panel_resolucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 260, -1, 340));

        panel_usuario_admin.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout panel_usuario_adminLayout = new javax.swing.GroupLayout(panel_usuario_admin);
        panel_usuario_admin.setLayout(panel_usuario_adminLayout);
        panel_usuario_adminLayout.setHorizontalGroup(
            panel_usuario_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        panel_usuario_adminLayout.setVerticalGroup(
            panel_usuario_adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        ajustes.add(panel_usuario_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 600, 340));

        paneles.addTab("AJUSTES", ajustes);

        getContentPane().add(paneles, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1340, 640));

        btn_inicio.setText("INICIO");
        btn_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inicioActionPerformed(evt);
            }
        });
        getContentPane().add(btn_inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 10, 100, 80));

        logo2.setText("jLabel2");
        getContentPane().add(logo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 110, 70));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("© GEARSOFT STUDIO 2019");
        getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 740, -1, -1));

        logo3.setText("jLabel2");
        getContentPane().add(logo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 110, 70));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //ACTUALIZAR PRECIOS DE VOLETERIA
    private void actualizar_precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizar_precioActionPerformed
        try {
            for(int i=0;i<7;i++){
                PreparedStatement pst = con.prepareStatement("UPDATE precios SET "
                                                        + "valor2d='"+precios2d[i].getText()+"', "
                                                        + "valor3d='"+precios3d[i].getText()+"', "
                                                        + "tarjeta='"+precios_tarjeta[i].getText()+"', "
                                                        + "tarjeta3d='"+precios_tarjeta3d[i].getText()+"', "
                                                        + "pref_valor2d='"+pref_precios2d[i].getText()+"', "
                                                        + "pref_valor3d='"+pref_precios3d[i].getText()+"', "                
                                                        + "pref_tarjeta2d='"+pref_precios_tarjeta[i].getText()+"', "
                                                        + "pref_tarjeta3d='"+pref_precios_tarjeta3d[i].getText()+"' "   
                                                        + "WHERE dia='"+(i+1)+"' AND franja="+(Franja_seleccionada.getSelectedIndex()+1)+" ");
                pst.executeUpdate();
            }
             
            if(MitadPrecio_cambio){
                for(int i=0;i<7;i++){
                    int aux=0;
                    if(dia_MitadPrecio[i].isSelected()){
                        aux=1;
                    }
                    
                    PreparedStatement pst = con.prepareStatement("UPDATE precios SET `dosxuno`= "+aux+" "  
                                                            + " WHERE dia='"+(i+1)+"' AND franja=1 ");
                    pst.executeUpdate();
                }
            }
            
            PreparedStatement pst = con.prepareStatement("UPDATE precios SET valor2d='"+gafas.getText()+"' WHERE dia='8' AND franja=1 ");
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Precios actualizados con exito");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 3 "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_actualizar_precioActionPerformed
    
//AGREGAR PELICULA
    private void agregar_peliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_peliculaActionPerformed
        // TODO add your handling code here:
        
        if(nombre_pelicula_agregar.getText().equals("") || ruta_agregar==""){
            JOptionPane.showMessageDialog(null,"DATOS INCOMPLETOS +" + nombre_pelicula_agregar.getText()+"+");   
        }
        else {       
            String sql;
            String imagen_url="";

            sql="INSERT INTO peliculas  (nombre,imagen,tipo,activa,super_estreno,imagen_url) VALUES (?,?,?,'1','0',?)";
            try {
                FileInputStream archivofoto;
                PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1,nombre_pelicula_agregar.getText());
                pst.setString(3,(String)tipo_pelicula_agregar.getSelectedItem());

                archivofoto = new FileInputStream(ruta_agregar);

                pst.setBinaryStream(2,archivofoto);
                
                //agregar imagen a carpeta imagenes/peliculas
                java.util.Date fechaActual = new java.util.Date();
                DateFormat formatoFecha = new SimpleDateFormat("yyyyMMddHHmmss");
       
                BufferedImage bImage = null;
                File file = new File(ruta_agregar);
                bImage = ImageIO.read(file);
                
                imagen_url= "img"+formatoFecha.format(fechaActual)+"."+getFileExtension(file);
                ImageIO.write(bImage, getFileExtension(file) , new File("C:\\cinema\\peliculas\\"+imagen_url));
                pst.setString(4,imagen_url);
                
                int n=pst.executeUpdate();


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"error 4:  "+ex);
                Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
            cargarpeliculas();
            nombre_pelicula_agregar.setText("");
            tipo_pelicula_agregar.setSelectedIndex(0);
            ruta_agregar="";
            ImageIcon ruta= new ImageIcon("src/imagenes/sin-foto.png");
            Icon icono = new ImageIcon(ruta.getImage().getScaledInstance(imagen_agregar.getWidth(),imagen_agregar.getHeight(), Image.SCALE_DEFAULT));
            imagen_agregar.setIcon(icono);
        }
    }//GEN-LAST:event_agregar_peliculaActionPerformed

    //BUSCAR PELICULA
    private void buscar_peliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_peliculaActionPerformed
        // TODO add your handling code here:
    if (!palabra_buscar.getText().equals("ingrese aqui la palagra clave")){
        int a =modelo_pelicula.getRowCount();
        for(int i=0; i<a;i++){
            modelo_pelicula.removeRow(0);
        }
            
        peliculas.clear();
        ImageIcon estado;

        try {
            Statement st = con.createStatement();

            ResultSet result =st.executeQuery("SELECT id, nombre, tipo, activa \n" +
                                                "from peliculas \n" +
                                                "where nombre like '%"+palabra_buscar.getText()+"%' \n" +
                                                "ORDER BY id desc \n" +
                                                "LIMIT 20");

            while(result.next()){
                pelicula p=new pelicula();
                if(result.getString(4).equals("1")){
                    estado=icono_activa;
                }
                else {estado=icono_inactiva;}
                Object[] datosDeLaFila= new Object [5];

                datosDeLaFila[0]=result.getString(2);
                datosDeLaFila[1]=result.getString(3);
                datosDeLaFila[2]=new JLabel(estado);
                datosDeLaFila[3]=new JButton("editar");
                datosDeLaFila[4]=new JButton("eliminar");
                
                modelo_pelicula.addRow(datosDeLaFila);
                lista_peliculas.setModel(modelo_pelicula);
                /////cargar valores de la consurla en array de pelicula
              
               p.set_id(result.getString(1));
               p.set_nombre(result.getString(2));
               p.set_tipo(result.getString(3));
               p.set_activa(result.getBoolean(4));

               peliculas.add(p);
                
            }               
        } catch (SQLException ex) {
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"error 6:  "+ex);
        }
    }
    }//GEN-LAST:event_buscar_peliculaActionPerformed
    
//borrar contenido de texfile palabra_buscar
    private void palabra_buscarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_palabra_buscarMousePressed
        // TODO add your handling code here:
        palabra_buscar.setText("");
        buscar_pelicula.setEnabled(true);
    }//GEN-LAST:event_palabra_buscarMousePressed

    ///SELECCIONAR IMAGEN PARA AGREGAR PELICULA
    private void seleccionar_imagen_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionar_imagen_agregarActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo imagen jpg, png, jpeg", "jpg", "png", "jpeg");
        JFileChooser archivo = new JFileChooser();
        archivo.setFileFilter(filtro);
        archivo.setAcceptAllFileFilterUsed(false);
        archivo.setDialogTitle("Abrir archivo");
        int ventana = archivo.showOpenDialog(null);
        if(ventana == JFileChooser.APPROVE_OPTION){
            File file =  archivo.getSelectedFile();
            if(file.length()>1000000){   
                JOptionPane.showMessageDialog(null,"El tamaño maximo de la imagen es de 1 MB  ");
            }else{
                ruta_agregar=String.valueOf(file);
                Image foto = getToolkit().getImage(ruta_agregar);
                foto = foto.getScaledInstance(imagen_agregar.getWidth(),imagen_agregar.getHeight(), Image.SCALE_DEFAULT);
                imagen_agregar.setIcon(new ImageIcon(foto));  
            }
        }
        
    }//GEN-LAST:event_seleccionar_imagen_agregarActionPerformed

    //CARGAR PELICULA SELECCIONADA DE LA LISTA
    private void lista_peliculasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_peliculasMouseClicked
        String selec_id = pelicula_selec.get_id();
        
        pelicula_selec = peliculas.get(lista_peliculas.rowAtPoint(evt.getPoint()));
        ruta_imagen="";
        ///EDITAR
        if(lista_peliculas.columnAtPoint(evt.getPoint())==3){         
            try {
                Statement st = con.createStatement();
            
                ResultSet result =st.executeQuery("select * from peliculas where id="+pelicula_selec.get_id()+"");
                while (result.next())
                {
                   
                    pelicula_selec.set_imagen_url(result.getString(7));
                    pelicula_selec.set_super_estreno(result.getBoolean(6));

                }

                nombre_pelicula.setText(pelicula_selec.get_nombre());
                tipo_pelicula.setSelectedItem(pelicula_selec.get_tipo());
                //imagen

                Image img = getToolkit().getImage("C:\\cinema\\peliculas\\"+pelicula_selec.get_imagen_url()).getScaledInstance(imagen_pelicula.getWidth(),imagen_pelicula.getHeight(), Image.SCALE_AREA_AVERAGING);
                imagen_pelicula.setIcon(new ImageIcon(img)); 

                if(pelicula_selec.get_activa()){
                    activar_pelicula.setSelected(true);
                }
                else {activar_pelicula.setSelected(false);}

                if(pelicula_selec.get_super_estreno()){
                    superestreno_pelicula.setSelected(true);
                }
                else {superestreno_pelicula.setSelected(false);}



                actualizar_pelicula.setEnabled(true);
                agregar_funcion.setEnabled(true);
                agregar_funcion_especial.setEnabled(true);
                cargarfunciones();
                cargarfuncionesespeciales();
            } catch (SQLException ex) {
                Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        ///ELIMINAR
        else if(lista_peliculas.columnAtPoint(evt.getPoint())==4){ 
            try{
                Statement st = con.createStatement();
                ResultSet result =st.executeQuery("SELECT * \n" +
                                                "FROM peliculas AS p, funciones AS f, ventas AS v\n" +
                                                "WHERE v.funcion_id=f.id AND f.pelicula_id=p.id AND p.id='"+pelicula_selec.get_id()+"'");

                if(result.next()){
                    JOptionPane.showMessageDialog(null,"LA PELICULA YA TIENE VENTAS NO SE PUEDE ELIMINAR","error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String sql;
                    sql="DELETE FROM peliculas WHERE id ='"+pelicula_selec.get_id()+"'";
                    PreparedStatement pst= con.prepareStatement(sql);
                    int response = JOptionPane.showConfirmDialog(null, "esta seguro de eliminar "+pelicula_selec.get_nombre()+"?", "Confirmacion",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        pst.executeUpdate();
                        if(pelicula_selec.get_id().equals(selec_id)){
                            actualizar_pelicula.setEnabled(false);
                            agregar_funcion.setEnabled(false);
                            agregar_funcion_especial.setEnabled(false);
                            actualizar_funcion.setEnabled(false);
                        }
                        JOptionPane.showMessageDialog(null,"se elimino exitosamente");
                        cargarpeliculas();
                    }
                }
                
                
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error 7:  "+ex);
                Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_lista_peliculasMouseClicked
   
    /////ACTUALZAR PELICULA
    private void actualizar_peliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizar_peliculaActionPerformed
        // TODO add your handling code here:
         String sql;
        if(ruta_imagen==""){
            sql="UPDATE peliculas  SET nombre=?,tipo=?,activa=?,super_estreno=?  WHERE id='"+pelicula_selec.get_id()+"' ";
        }
        else{
            sql="UPDATE peliculas  SET nombre=?,tipo=?,activa=?,super_estreno=? , imagen=?, imagen_url=? WHERE id='"+pelicula_selec.get_id()+"' ";
        }

        try {
            FileInputStream archivofoto;
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setString(1,nombre_pelicula.getText());
            pst.setString(2,(String)tipo_pelicula.getSelectedItem());
            if(activar_pelicula.isSelected()){
                pst.setString(3,"1");
            }
            else{pst.setString(3,"0");}
            
            if(superestreno_pelicula.isSelected()){
                pst.setString(4,"1");
            }
            else{pst.setString(4,"0");}
            
            if(ruta_imagen!=""){
                archivofoto = new FileInputStream(ruta_imagen);
                pst.setBinaryStream(5,archivofoto);

                //agregar imagen a carpeta imagenes/peliculas
                java.util.Date fechaActual = new java.util.Date();
                DateFormat formatoFecha = new SimpleDateFormat("yyyyMMddHHmmss");

                BufferedImage bImage = null;
                File file = new File(ruta_imagen);
                bImage = ImageIO.read(file);

                String imagen_url= "img"+formatoFecha.format(fechaActual)+"."+getFileExtension(file);
                ImageIO.write(bImage, getFileExtension(file) , new File("C:\\cinema\\peliculas\\"+imagen_url));
                pst.setString(6,imagen_url);
            
            }
            pst.executeUpdate();   
            
            JOptionPane.showMessageDialog(null,"actualizacion exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 8:  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"error 9:  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        ////actilizar valores en la tabla si el dato existe 
        for(int i=0;i<peliculas.size();i++){
                
            if(peliculas.get(i).get_id().equals(pelicula_selec.get_id())){
                //actualizar valores en la tabla
                lista_peliculas.setValueAt(nombre_pelicula.getText(), i,0);
                lista_peliculas.setValueAt(tipo_pelicula.getSelectedItem(), i,1);
                if(activar_pelicula.isSelected()){
                    lista_peliculas.setValueAt(new JLabel(icono_activa), i,2);
                }
                else{lista_peliculas.setValueAt(new JLabel(icono_inactiva), i,2);}
                //actualizar valores en el arreglo
                peliculas.get(i).set_nombre(nombre_pelicula.getText());
                peliculas.get(i).set_tipo((String)tipo_pelicula.getSelectedItem());
                peliculas.get(i).set_activa(activar_pelicula.isSelected());
                peliculas.get(i).set_super_estreno(superestreno_pelicula.isSelected());
                
            }
        }
        ruta_imagen="";
        
    }//GEN-LAST:event_actualizar_peliculaActionPerformed

    ///SELECCIONAR IMAGEN PARA ACTUALIZAR PELICULA
    private void seleccionar_imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionar_imagenActionPerformed
        // TODO add your handling code here:
        
        ruta_imagen="";
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo imagen jpg, png, jpeg", "jpg", "png", "jpeg");
        JFileChooser archivo = new JFileChooser();
        archivo.setFileFilter(filtro);
        archivo.setAcceptAllFileFilterUsed(false);
        archivo.setDialogTitle("Abrir archivo");
        int ventana = archivo.showOpenDialog(null);
        if(ventana == JFileChooser.APPROVE_OPTION){
            File file =  archivo.getSelectedFile();
            if(file.length()>1000000){   
                JOptionPane.showMessageDialog(null,"El tamaño maximo de la imagen es de 1 MB  ");
            }else{
                ruta_imagen=String.valueOf(file);
                Image foto = getToolkit().getImage(ruta_imagen);
                foto = foto.getScaledInstance(imagen_pelicula.getWidth(),imagen_pelicula.getHeight(), Image.SCALE_DEFAULT);
                imagen_pelicula.setIcon(new ImageIcon(foto));  
            }
        }
        
    }//GEN-LAST:event_seleccionar_imagenActionPerformed

    ////AGREGAR FUNCION
    private void agregar_funcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_funcionActionPerformed
        // TODO add your handling code here:
        String hora="";
        int h = Integer.parseInt((String)hora_funcion.getSelectedItem());
        
        if(h==12){
            h=h-12;
        }
        if(ampm_funcion.getSelectedItem().equals("PM")){
            h=h+12;
        }
        hora=h+":"+minutos_funcion.getSelectedItem();
                
        String sql;

            sql="INSERT INTO funciones  (sala,hora,activa,pelicula_id, franja) VALUES (?,?,'1',?,?)";
            try {
                PreparedStatement pst= con.prepareStatement(sql);
                pst.setInt(1,(sala_funcion.getSelectedIndex()+1));
                pst.setString(2,hora);
                pst.setString(3,pelicula_selec.get_id());
                pst.setInt(4,(Franja_seleccionada_funcion.getSelectedIndex()+1));

                int n=pst.executeUpdate();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"error 10:  "+ex);
                Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
            cargarfunciones();            
    }//GEN-LAST:event_agregar_funcionActionPerformed
    
    //CARGAR FUNCION SELECCIONADAS DE LA TABLE 
    private void lista_funcionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_funcionesMouseClicked
        funcion_selec = funciones.get(lista_funciones.rowAtPoint(evt.getPoint()));

        hora_funcion.setSelectedItem(funcion_selec.get_hora());
        minutos_funcion.setSelectedItem(funcion_selec.get_minutos());
        ampm_funcion.setSelectedItem(funcion_selec.get_ampm());
        sala_funcion.setSelectedIndex(funcion_selec.get_sala()-1);
        Franja_seleccionada_funcion.setSelectedIndex(funcion_selec.get_franja()-1);
        //imagen

        activar_funcion.setSelected(funcion_selec.get_activa());
        
        actualizar_funcion.setEnabled(true);
        
    }//GEN-LAST:event_lista_funcionesMouseClicked

    ///ACTUALIZAR FUNCIONES
    private void actualizar_funcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizar_funcionActionPerformed
        String hora="";
        int h = Integer.parseInt((String)hora_funcion.getSelectedItem());
        
        if(h==12){
            h=h-12;
        }
        if(ampm_funcion.getSelectedItem().equals("PM")){
            h=h+12;
        }
        
        hora=h+":"+minutos_funcion.getSelectedItem();
                
        String sql;

            sql="UPDATE funciones  SET sala=?,hora=?,activa=?,franja=? WHERE id='"+funcion_selec.get_id()+"' ";
            try {
                PreparedStatement pst= con.prepareStatement(sql);
                pst.setInt(1,(sala_funcion.getSelectedIndex()+1));
                pst.setString(2,hora);
                if(activar_funcion.isSelected()){pst.setString(3,"1");}
                else{pst.setString(3,"0");}
                pst.setInt(4,(Franja_seleccionada_funcion.getSelectedIndex()+1));
                
                pst.executeUpdate();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"error 11:  "+ex);
                Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
            cargarfunciones();
        
    }//GEN-LAST:event_actualizar_funcionActionPerformed

        ///AGREGAR FUNCION ESPECIAL
    private void agregar_funcion_especialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_funcion_especialActionPerformed
        if(fecha_funcion.getDate() != null){
            String dia,mes,ano,fecha;
            dia= Integer.toString(fecha_funcion.getCalendar().get(Calendar.DAY_OF_MONTH));
            mes= Integer.toString(fecha_funcion.getCalendar().get(Calendar.MONTH)+1);
            ano= Integer.toString(fecha_funcion.getCalendar().get(Calendar.YEAR));
            fecha=ano+"-"+mes+"-"+dia;
            String hora="";
        int h = Integer.parseInt((String)hora_funcion.getSelectedItem());
        
        if(h==12){
            h=h-12;
        }
        if(ampm_funcion.getSelectedItem().equals("PM")){
            h=h+12;
        }
        hora=h+":"+minutos_funcion.getSelectedItem();
                
        String sql;

        sql="INSERT INTO funciones  (sala,hora,activa,fecha,precio,pelicula_id) VALUES (?,?,'1',?,?,?)";
        try {
            PreparedStatement pst= con.prepareStatement(sql);
            pst.setInt(1,(sala_funcion.getSelectedIndex()+1));
            pst.setString(2,hora);
            pst.setString(3,fecha);
            pst.setString(4,valor_funcion_especial.getText()); 
            pst.setString(5,pelicula_selec.get_id());

            int n=pst.executeUpdate();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"error 12:  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarfunciones();
        cargarfuncionesespeciales();
        fecha_funcion.setDate(null);
        }
        else{
            JOptionPane.showMessageDialog(null,"debe seleccionar una fecha","error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_agregar_funcion_especialActionPerformed

    private void btn_inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inicioActionPerformed
        // TODO add your handling code here:
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error cerrando conexion:  "+ex.getMessage());
        }
        
        Inicio inicio = new Inicio(user);
        inicio.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        inicio.setResizable(false);
        inicio.setVisible(true); 
        dispose();
    }//GEN-LAST:event_btn_inicioActionPerformed

        ////AGREGAR CONVENIOS
    private void agregar_convenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_convenioActionPerformed
        // TODO add your handling code here:
        String g = "0";
        if(convenio_activo.isSelected()){
            g="1";
        }
        
        String color=color_convenio.getRed()+","+color_convenio.getGreen()+","+color_convenio.getBlue();
        String sql="INSERT INTO convenios (nombre,valor,guarda,color,valor3d) VALUES ('"+nombre_convenio.getText()+"','"+valor_convenio2d.getText()+"','"+g+"','"+color+"','"+valor_convenio3d.getText()+"')";

        try{ 
            PreparedStatement pst= con.prepareStatement(sql);
            pst.executeUpdate();
            nombre_convenio.setText("");
            valor_convenio2d.setText("");
            valor_convenio3d.setText("");
            convenio_activo.setSelected(false);
            listar_convenios();
        } catch (SQLException ex) {   
            JOptionPane.showMessageDialog(null,"error 13:  "+ex.getMessage());
        }        
    }//GEN-LAST:event_agregar_convenioActionPerformed
    ///actualizar convenio
    private void actualizar_convenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizar_convenioActionPerformed
        // TODO add your handling code here:
        String e ="0";
        if(convenio_activo.isSelected()){
            e="1";
        }
        String color=color_convenio.getRed()+","+color_convenio.getGreen()+","+color_convenio.getBlue();
        String sql="UPDATE convenios  SET nombre='"+nombre_convenio.getText()+"' ,valor= '"+valor_convenio2d.getText()+"' ,guarda= '"+e+"', color='"+color+"' ,valor3d= '"+valor_convenio3d.getText()+"'  WHERE id='"+selec_convenio+"' ";
            try {
                PreparedStatement pst= con.prepareStatement(sql);
                pst.executeUpdate();
                nombre_convenio.setText("");
                valor_convenio2d.setText("");
                valor_convenio3d.setText("");
                convenio_activo.setSelected(false);
                listar_convenios();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error 14:  "+ex);
            }
        actualizar_convenio.setEnabled(false);
    }//GEN-LAST:event_actualizar_convenioActionPerformed

    private void editar_convenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_convenioActionPerformed
        // TODO add your handling code here:
            int i =lista_convenios.getSelectedIndex()-1;
            selec_convenio =convenios.get(i).get_id();
            nombre_convenio.setText(convenios.get(i).get_nombre());
            valor_convenio2d.setText(convenios.get(i).get_valor());
            valor_convenio3d.setText(convenios.get(i).get_valor3d());
            convenio_activo.setSelected(convenios.get(i).get_guarda());
            actualizar_convenio.setEnabled(true);
            color_select.setBackground(convenios.get(i).get_color());
            color_convenio=convenios.get(i).get_color();
    }//GEN-LAST:event_editar_convenioActionPerformed
    //qctuaizar contraseña
    private void actualizar_contraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizar_contraActionPerformed
        // TODO add your handling code here:
        String actual="";
        try {
            
            Statement st = con.createStatement();
            ResultSet result =st.executeQuery("SELECT contrasena FROM usuarios WHERE id="+user.get_id()+"");
            
            while(result.next()){
                actual=result.getString(1);                
            }  

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 15:  "+ex);
        }
        
        if(contrasena_anterior.getText().equals(actual)){
            if(contrasena1.getText().equals(contrasena2.getText())){
                String sql;

                sql="UPDATE usuarios  SET contrasena='"+contrasena1.getText()+"' WHERE id='"+user.get_id()+"' ";
                try {
                    PreparedStatement pst= con.prepareStatement(sql);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"ACTUALIZADO CON EXITO");
                    contrasena_anterior.setText("");
                    contrasena1.setText("");
                    contrasena2.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"error 16:  "+ex);
                }
                
            }
            else{
                JOptionPane.showMessageDialog(null,"CONTRASEÑA NUEVA NO COINCIDE");
            }
            
        }
        else{
            JOptionPane.showMessageDialog(null,"CONTRASEÑA ACTUAL ERRONEA","error",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_actualizar_contraActionPerformed

    //////////////////////////////////////////////////
    ///////////VALIDACIONES////////////////////////
    //////////////////////////////////////////////
    
        ///nombre de la pelicula 30 caracteress
    private void nombre_convenioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_convenioKeyTyped
        if(nombre_convenio.getText().length()>29)evt.consume();
    }//GEN-LAST:event_nombre_convenioKeyTyped
        ///nombre de la pelicula agregar 50 caracteress
    private void nombre_pelicula_agregarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_pelicula_agregarKeyTyped
        if(nombre_pelicula_agregar.getText().length()>49)evt.consume();  
    }//GEN-LAST:event_nombre_pelicula_agregarKeyTyped
        ///nombre de la pelicula 50 caracteress
    private void nombre_peliculaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_peliculaKeyTyped
        if(nombre_pelicula.getText().length()>49)evt.consume();
    }//GEN-LAST:event_nombre_peliculaKeyTyped
        ///// validacion de campos que solo admite numeros///////
    private void valor_convenio2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valor_convenio2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_valor_convenio2dKeyTyped
    private void lunes2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lunes2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_lunes2dKeyTyped
    private void lunes3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lunes3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_lunes3dKeyTyped
    private void martes2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_martes2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_martes2dKeyTyped
    private void martes3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_martes3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_martes3dKeyTyped
    private void miercoles2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_miercoles2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_miercoles2dKeyTyped
    private void miercoles3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_miercoles3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_miercoles3dKeyTyped
    private void jueves2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jueves2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_jueves2dKeyTyped
    private void jueves3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jueves3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_jueves3dKeyTyped
    private void viernes2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_viernes2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_viernes2dKeyTyped
    private void viernes3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_viernes3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_viernes3dKeyTyped
    private void sabado2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sabado2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_sabado2dKeyTyped
    private void sabado3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sabado3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_sabado3dKeyTyped
    private void domingo2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_domingo2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_domingo2dKeyTyped
    private void domingo3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_domingo3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_domingo3dKeyTyped
        ///nombre de la CONTRASEÑA 20 caracteress
    private void contrasena1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contrasena1KeyTyped
        if(contrasena1.getText().length()>19)evt.consume();
    }//GEN-LAST:event_contrasena1KeyTyped
    private void gafasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gafasKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_gafasKeyTyped
    private void valor_funcion_especialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valor_funcion_especialKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_valor_funcion_especialKeyTyped
    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        // TODO add your handling code here:
        String id = funciones_especiales.get(lista_funciones_especial.getSelectedRow());
        editar_funcion_especial edit= new editar_funcion_especial(this, true, user.get_ip(), id);
        edit.setVisible(true);
        
        cargarfuncionesespeciales();
        
    }//GEN-LAST:event_editarActionPerformed
    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
        String id = funciones_especiales.get(lista_funciones_especial.getSelectedRow());
        try{
            String sql;
            sql="DELETE FROM funciones WHERE id ='"+id+"'";
            PreparedStatement pst= con.prepareStatement(sql);
            int response = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar la funcion especial ?", "Confirmacion",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null,"se elimino exitosamente");
                cargarfuncionesespeciales();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 7:  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_eliminarActionPerformed
    private void lista_funciones_especialMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_funciones_especialMouseReleased
        int r = lista_funciones_especial.rowAtPoint(evt.getPoint());
        if (r >= 0 && r < lista_funciones_especial.getRowCount()) {
            lista_funciones_especial.setRowSelectionInterval(r, r);
        } else {
            lista_funciones_especial.clearSelection();
        }
        
        int rowindex = lista_funciones_especial.getSelectedRow();
        if (rowindex < 0)
            return;
        if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable ) {
            JPopupMenu popup = funcion_especial;
            popup.show(evt.getComponent(), evt.getX(), evt.getY());
        }
        
    }//GEN-LAST:event_lista_funciones_especialMouseReleased
    private void domingo_tarjeta2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_domingo_tarjeta2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_domingo_tarjeta2dKeyTyped
    private void lunes_tarjeta2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lunes_tarjeta2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_lunes_tarjeta2dKeyTyped
    private void martes_tarjeta2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_martes_tarjeta2dKeyTyped
       char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_martes_tarjeta2dKeyTyped
    private void miercoles_tarjeta2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_miercoles_tarjeta2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_miercoles_tarjeta2dKeyTyped
    private void jueves_tarjeta2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jueves_tarjeta2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_jueves_tarjeta2dKeyTyped
    private void viernes_tarjeta2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_viernes_tarjeta2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_viernes_tarjeta2dKeyTyped
    private void sabado_tarjeta2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sabado_tarjeta2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_sabado_tarjeta2dKeyTyped
    private void color_selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_color_selectActionPerformed
        Color col = JColorChooser.showDialog(null,"Seleccione Color",color_convenio);
        
        if(col!=null){
            color_convenio=col;
            color_select.setBackground(color_convenio);
        }        
    }//GEN-LAST:event_color_selectActionPerformed
    private void domingo_tarjeta3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_domingo_tarjeta3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_domingo_tarjeta3dKeyTyped
    private void lunes_tarjeta3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lunes_tarjeta3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_lunes_tarjeta3dKeyTyped
    private void martes_tarjeta3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_martes_tarjeta3dKeyTyped
       char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_martes_tarjeta3dKeyTyped
    private void miercoles_tarjeta3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_miercoles_tarjeta3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_miercoles_tarjeta3dKeyTyped
    private void jueves_tarjeta3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jueves_tarjeta3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_jueves_tarjeta3dKeyTyped
    private void viernes_tarjeta3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_viernes_tarjeta3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_viernes_tarjeta3dKeyTyped
    private void sabado_tarjeta3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sabado_tarjeta3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_sabado_tarjeta3dKeyTyped
    private void valor_convenio3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valor_convenio3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_valor_convenio3dKeyTyped
    private void pref_lunes2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_lunes2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_lunes2dKeyTyped
    private void pref_martes2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_martes2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_martes2dKeyTyped
    private void pref_miercoles2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_miercoles2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_miercoles2dKeyTyped
    private void pref_jueves2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_jueves2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_jueves2dKeyTyped
    private void pref_viernes2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_viernes2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_viernes2dKeyTyped
    private void pref_sabado2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_sabado2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_sabado2dKeyTyped
    private void pref_domingo2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_domingo2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_domingo2dKeyTyped
    private void pref_lunes3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_lunes3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_lunes3dKeyTyped
    private void pref_martes3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_martes3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_martes3dKeyTyped
    private void pref_miercoles3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_miercoles3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_miercoles3dKeyTyped
    private void pref_jueves3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_jueves3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_jueves3dKeyTyped
    private void pref_viernes3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_viernes3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_viernes3dKeyTyped
    private void pref_sabado3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_sabado3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_sabado3dKeyTyped
    private void pref_domingo3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_domingo3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_domingo3dKeyTyped
    private void pref_domingo_tarjeta2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_domingo_tarjeta2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_domingo_tarjeta2dKeyTyped
    private void pref_lunes_tarjeta2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_lunes_tarjeta2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_lunes_tarjeta2dKeyTyped
    private void pref_martes_tarjeta2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_martes_tarjeta2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_martes_tarjeta2dKeyTyped
    private void pref_miercoles_tarjeta2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_miercoles_tarjeta2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_miercoles_tarjeta2dKeyTyped
    private void pref_jueves_tarjeta2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_jueves_tarjeta2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_jueves_tarjeta2dKeyTyped
    private void pref_viernes_tarjeta2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_viernes_tarjeta2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_viernes_tarjeta2dKeyTyped
    private void pref_sabado_tarjeta2dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_sabado_tarjeta2dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_sabado_tarjeta2dKeyTyped
    private void pref_domingo_tarjeta3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_domingo_tarjeta3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_domingo_tarjeta3dKeyTyped
    private void pref_lunes_tarjeta3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_lunes_tarjeta3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_lunes_tarjeta3dKeyTyped
    private void pref_martes_tarjeta3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_martes_tarjeta3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_martes_tarjeta3dKeyTyped
    private void pref_miercoles_tarjeta3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_miercoles_tarjeta3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_miercoles_tarjeta3dKeyTyped
    private void pref_jueves_tarjeta3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_jueves_tarjeta3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_jueves_tarjeta3dKeyTyped
    private void pref_viernes_tarjeta3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_viernes_tarjeta3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_viernes_tarjeta3dKeyTyped
  
    private void pref_sabado_tarjeta3dKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pref_sabado_tarjeta3dKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_pref_sabado_tarjeta3dKeyTyped
///VERIFICR SI SE HA REALIZADO CAMBIOS EN MITAD BOTONES DE PRECIO
    private void MitadPrecio_domingoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MitadPrecio_domingoActionPerformed
        MitadPrecio_cambio= true;
    }//GEN-LAST:event_MitadPrecio_domingoActionPerformed
    private void MitadPrecio_lunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MitadPrecio_lunesActionPerformed
        MitadPrecio_cambio= true;
    }//GEN-LAST:event_MitadPrecio_lunesActionPerformed
    private void MitadPrecio_martesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MitadPrecio_martesActionPerformed
        MitadPrecio_cambio= true;
    }//GEN-LAST:event_MitadPrecio_martesActionPerformed
    private void MitadPrecio_miercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MitadPrecio_miercolesActionPerformed
        MitadPrecio_cambio= true;
    }//GEN-LAST:event_MitadPrecio_miercolesActionPerformed
    private void MitadPrecio_juevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MitadPrecio_juevesActionPerformed
        MitadPrecio_cambio= true;
    }//GEN-LAST:event_MitadPrecio_juevesActionPerformed
    private void MitadPrecio_viernesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MitadPrecio_viernesActionPerformed
        MitadPrecio_cambio= true;
    }//GEN-LAST:event_MitadPrecio_viernesActionPerformed
    private void MitadPrecio_sabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MitadPrecio_sabadoActionPerformed
        MitadPrecio_cambio= true;
    }//GEN-LAST:event_MitadPrecio_sabadoActionPerformed
    private void Franja_seleccionadaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Franja_seleccionadaItemStateChanged
        if (evt.getStateChange()== ItemEvent.SELECTED){
            cargarprecios(Franja_seleccionada.getSelectedIndex()+1);
        }
    }//GEN-LAST:event_Franja_seleccionadaItemStateChanged

    private void panelesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_panelesStateChanged
        // TODO add your handling code here:
        
        
        if(paneles.getSelectedIndex()==1 && panel_reporte_general.getComponentCount()==0){
            reporte_general rg= new reporte_general(user);
            rg.setSize(1340,640);
            rg.setLocation(0,0);
            panel_reporte_general.add(rg);
        }
        else if(paneles.getSelectedIndex()==2 && panel_reporte_entradas.getComponentCount()==0){
            reporte_entradas r= new reporte_entradas(this,user);
            r.setSize(1340,640);
            r.setLocation(0,0);
            panel_reporte_entradas.add(r);
        }
        else if(paneles.getSelectedIndex()==3 && panel_reporte_ventas.getComponentCount()==0){
            reporte_ventas rv= new reporte_ventas(user);
            rv.setSize(1340,640);
            rv.setLocation(0,0);
            panel_reporte_ventas.add(rv);
        }
        else if(paneles.getSelectedIndex()==4 && panel_tarjetas.getComponentCount()==0){
            tarjetas_admin t= new tarjetas_admin(user);
            t.setSize(1340,640);
            t.setLocation(0,0);
            panel_tarjetas.add(t);
            t.cargar();
        }
        else if(paneles.getSelectedIndex()== 5){
            cargarprecios(1);
            Franja_seleccionada.setSelectedIndex(0);
        }
        else if(paneles.getSelectedIndex()==6 && panel_resolucion.getComponentCount()==0 && panel_usuario_admin.getComponentCount()==0){
            resoluciones res= new resoluciones(user);
            res.setSize(550,300);
            res.setLocation(0,0);
            panel_resolucion.add(res);
            
            usuarios_admin us_admin= new usuarios_admin(user);
            us_admin.setSize(600,340);
            us_admin.setLocation(0,0);
            panel_usuario_admin.add(us_admin);
        }
        
    }//GEN-LAST:event_panelesStateChanged

    private void cargarprecios(int franja){     
        
        //Franja_seleccionada.setSelectedIndex(0);
        try {
            Statement st = con.createStatement();
            ResultSet result =st.executeQuery("SELECT * FROM precios WHERE franja="+franja+"  ORDER BY(dia)");
            
            for (int i = 0; i < 7; i++) {
                result.next();
                precios2d[i].setText(result.getString(2));
                precios3d[i].setText(result.getString(3));

                precios_tarjeta[i].setText(result.getString(5));
                precios_tarjeta3d[i].setText(result.getString(6));
                
                //PREFERENCIAL
                pref_precios2d[i].setText(result.getString(7));
                pref_precios3d[i].setText(result.getString(8));

                pref_precios_tarjeta[i].setText(result.getString(9));
                pref_precios_tarjeta3d[i].setText(result.getString(10));
                
                if(franja==1 && result.getBoolean(4)){
                    dia_MitadPrecio[i].setSelected(true); 
                }
                    
            }
            if(result.next()){
                gafas.setText(result.getString(2));
            }
       
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 17  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void cargarpeliculas(){
        peliculas.clear();
        
        int a =modelo_pelicula.getRowCount();
        for(int i=0; i<a;i++){
            modelo_pelicula.removeRow(0);
        }
        
        ImageIcon estado;

        
        try {
            Statement st = con.createStatement();
            ResultSet result =st.executeQuery("SELECT id, nombre, tipo, activa  FROM peliculas ORDER BY id desc LIMIT 7");

            while(result.next()){
                pelicula p = new pelicula();
                if(result.getString(4).equals("1")){
                   estado=icono_activa;
                }
                else {estado=icono_inactiva;}
                Object[] datosDeLaFila= new Object [5];
                
                
                datosDeLaFila[0]=result.getString(2);
                datosDeLaFila[1]=result.getString(3);
                datosDeLaFila[2]=new JLabel(estado);
                datosDeLaFila[3]=new JButton("editar");
                datosDeLaFila[4]=new JButton("eliminar");
                
                modelo_pelicula.addRow(datosDeLaFila);
                lista_peliculas.setModel(modelo_pelicula);
                /////cargar valores de la consurla en array de pelicula

                p.set_id(result.getString(1));
                p.set_nombre(result.getString(2));
                p.set_tipo(result.getString(3));
                p.set_activa(result.getBoolean(4));

                peliculas.add(p);
               
            }               
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 19:  "+ex);
        }

    }
        
    private void cargarfunciones(){
        funciones.clear();
        
        int a =modelo_funcion.getRowCount();
        for(int i=0; i<a;i++){
            modelo_funcion.removeRow(0);
        }
        
        ImageIcon estado;

        String ampm="AM";
        char hora[];
        int h;
        
        try {
            Statement st = con.createStatement();
            ResultSet result =st.executeQuery("SELECT * FROM funciones WHERE pelicula_id='"+pelicula_selec.get_id()+"' AND fecha IS NULL ORDER BY id DESC LIMIT 20");

            while(result.next()){
                ampm="AM";
                funcion f = new funcion();
                if(result.getString(4).equals("1")){
                    estado=icono_activa;
                }
                else {estado=icono_inactiva;}
                
                hora=result.getString(3).toCharArray();
                h=Integer.parseInt(""+hora[0]+hora[1]);
                if(h==0 || h==12){
                    h=h+12;
                }
                if(h>12){
                    h=h-12;
                    ampm="PM";
                }
                
                 Object[] datosDeLaFila= new Object [3];
                
                
                datosDeLaFila[0]=h+":"+hora[3]+hora[4]+" "+ampm;
                datosDeLaFila[1]=result.getString(2);
                datosDeLaFila[2]=new JLabel(estado);
                
                
                modelo_funcion.addRow(datosDeLaFila);
                lista_funciones.setModel(modelo_funcion);

                f.set_id(result.getString(1));
                f.set_sala(result.getInt(2));
                if(h<10){f.set_hora("0"+h);}
                else{f.set_hora(""+h);}
                f.set_minutos(""+hora[3]+hora[4]);
                f.set_ampm(ampm);
                f.set_activa(result.getBoolean(4));
                f.set_pelicula_id(result.getString(7));
                f.set_franja(result.getInt(8));

                funciones.add(f);
            }               
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 20:  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        hora_funcion.setSelectedIndex(0);
        minutos_funcion.setSelectedIndex(0);
        ampm_funcion.setSelectedIndex(1);
        activar_funcion.setSelected(true);
        actualizar_funcion.setEnabled(false);
}
   
    private void cargarfuncionesespeciales(){
        funciones_especiales.clear();
        int a =modelo_funcion_especial.getRowCount();
        for(int i=0; i<a;i++){
            modelo_funcion_especial.removeRow(0);
        }
        
        int i =0;
        String ampm="AM";
        char hora[];
        int h;
        
        try {
            Statement st = con.createStatement();
            ResultSet result =st.executeQuery("SELECT * FROM funciones WHERE pelicula_id='"+pelicula_selec.get_id()+"' AND fecha IS NOT NULL ORDER BY id DESC LIMIT 20");

            while(result.next()){
                ampm="AM";
                hora=result.getString(3).toCharArray();
                h=Integer.parseInt(""+hora[0]+hora[1]);
                if(h==0 || h==12){
                    h=h+12;
                }
                if(h>12){
                    h=h-12;
                    ampm="PM";
                }
                Object[] datosDeLaFila=
                {
                    
                    h+":"+hora[3]+hora[4]+" "+ampm,
                    result.getString(2),
                    result.getString(5),
                    result.getString(6)
                };

                funciones_especiales.add(result.getString(1));
                i+=1;   
                modelo_funcion_especial.addRow(datosDeLaFila);
               lista_funciones_especial.setModel(modelo_funcion_especial);   
            }               
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 21:  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     
    public void listar_convenios(){
        lista_convenios.removeAllItems();
        convenios.clear();
        puede_selec=false;
        lista_convenios.addItem("");
        try {
            Statement st = con.createStatement();
            ResultSet result =st.executeQuery("SELECT * FROM `convenios` WHERE id !=1 ORDER BY id DESC");
            
            while(result.next()){
                convenio co = new convenio();
                co.set_id(result.getString(1));
                co.set_nombre(result.getString(2));
                co.set_valor(result.getString(3));
                co.set_guarda(result.getBoolean(4));
                
                String[] color = result.getString(5).split(",");
                int r=Integer.parseInt(color[0]);
                int g=Integer.parseInt(color[1]);
                int b=Integer.parseInt(color[2]);
                
                co.set_color(new Color(r,g,b));
                co.set_valor3d(result.getString(6));
                
                convenios.add(co);
                lista_convenios.addItem(result.getString(2)); 
            }  
            lista_convenios.setSelectedIndex(0);
            puede_selec=true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 22:  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Franja_seleccionada;
    private javax.swing.JComboBox<String> Franja_seleccionada_funcion;
    private javax.swing.JRadioButton MitadPrecio_domingo;
    private javax.swing.JRadioButton MitadPrecio_jueves;
    private javax.swing.JRadioButton MitadPrecio_lunes;
    private javax.swing.JRadioButton MitadPrecio_martes;
    private javax.swing.JRadioButton MitadPrecio_miercoles;
    private javax.swing.JRadioButton MitadPrecio_sabado;
    private javax.swing.JRadioButton MitadPrecio_viernes;
    private javax.swing.JRadioButton activar_funcion;
    private javax.swing.JRadioButton activar_pelicula;
    private javax.swing.JButton actualizar_contra;
    private javax.swing.JButton actualizar_convenio;
    private javax.swing.JButton actualizar_funcion;
    private javax.swing.JButton actualizar_pelicula;
    private javax.swing.JButton actualizar_precio;
    private javax.swing.JButton agregar_convenio;
    private javax.swing.JButton agregar_funcion;
    private javax.swing.JButton agregar_funcion_especial;
    private javax.swing.JButton agregar_pelicula;
    private javax.swing.JPanel ajustes;
    private javax.swing.JComboBox<String> ampm_funcion;
    private javax.swing.JButton btn_inicio;
    private javax.swing.JButton buscar_pelicula;
    private javax.swing.JPanel cartelera;
    private javax.swing.JButton color_select;
    private javax.swing.JPasswordField contrasena1;
    private javax.swing.JPasswordField contrasena2;
    private javax.swing.JPasswordField contrasena_anterior;
    private javax.swing.JCheckBox convenio_activo;
    private javax.swing.JTextField domingo2d;
    private javax.swing.JTextField domingo3d;
    private javax.swing.JTextField domingo_tarjeta2d;
    private javax.swing.JTextField domingo_tarjeta3d;
    private javax.swing.JMenuItem editar;
    private javax.swing.JButton editar_convenio;
    private javax.swing.JMenuItem eliminar;
    private com.toedter.calendar.JDateChooser fecha_funcion;
    private javax.swing.JPopupMenu funcion_especial;
    private javax.swing.JTextField gafas;
    private javax.swing.JComboBox<String> hora_funcion;
    private javax.swing.JLabel imagen_agregar;
    private javax.swing.JLabel imagen_pelicula;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jueves2d;
    private javax.swing.JTextField jueves3d;
    private javax.swing.JTextField jueves_tarjeta2d;
    private javax.swing.JTextField jueves_tarjeta3d;
    private javax.swing.JComboBox<String> lista_convenios;
    private javax.swing.JTable lista_funciones;
    private javax.swing.JTable lista_funciones_especial;
    private javax.swing.JTable lista_peliculas;
    private javax.swing.JLabel logo1;
    private javax.swing.JLabel logo2;
    private javax.swing.JLabel logo3;
    private javax.swing.JLabel logo4;
    private javax.swing.JTextField lunes2d;
    private javax.swing.JTextField lunes3d;
    private javax.swing.JTextField lunes_tarjeta2d;
    private javax.swing.JTextField lunes_tarjeta3d;
    private javax.swing.JTextField martes2d;
    private javax.swing.JTextField martes3d;
    private javax.swing.JTextField martes_tarjeta2d;
    private javax.swing.JTextField martes_tarjeta3d;
    private javax.swing.JTextField miercoles2d;
    private javax.swing.JTextField miercoles3d;
    private javax.swing.JTextField miercoles_tarjeta2d;
    private javax.swing.JTextField miercoles_tarjeta3d;
    private javax.swing.JComboBox<String> minutos_funcion;
    private javax.swing.JTextField nombre_convenio;
    private javax.swing.JTextField nombre_pelicula;
    private javax.swing.JTextField nombre_pelicula_agregar;
    private javax.swing.JTextField palabra_buscar;
    private javax.swing.JPanel panel_reporte_entradas;
    private javax.swing.JPanel panel_reporte_general;
    private javax.swing.JPanel panel_reporte_ventas;
    private javax.swing.JPanel panel_resolucion;
    private javax.swing.JPanel panel_tarjetas;
    private javax.swing.JPanel panel_usuario_admin;
    private javax.swing.JTabbedPane paneles;
    private javax.swing.JPanel precios;
    private javax.swing.JTextField pref_domingo2d;
    private javax.swing.JTextField pref_domingo3d;
    private javax.swing.JTextField pref_domingo_tarjeta2d;
    private javax.swing.JTextField pref_domingo_tarjeta3d;
    private javax.swing.JTextField pref_jueves2d;
    private javax.swing.JTextField pref_jueves3d;
    private javax.swing.JTextField pref_jueves_tarjeta2d;
    private javax.swing.JTextField pref_jueves_tarjeta3d;
    private javax.swing.JTextField pref_lunes2d;
    private javax.swing.JTextField pref_lunes3d;
    private javax.swing.JTextField pref_lunes_tarjeta2d;
    private javax.swing.JTextField pref_lunes_tarjeta3d;
    private javax.swing.JTextField pref_martes2d;
    private javax.swing.JTextField pref_martes3d;
    private javax.swing.JTextField pref_martes_tarjeta2d;
    private javax.swing.JTextField pref_martes_tarjeta3d;
    private javax.swing.JTextField pref_miercoles2d;
    private javax.swing.JTextField pref_miercoles3d;
    private javax.swing.JTextField pref_miercoles_tarjeta2d;
    private javax.swing.JTextField pref_miercoles_tarjeta3d;
    private javax.swing.JTextField pref_sabado2d;
    private javax.swing.JTextField pref_sabado3d;
    private javax.swing.JTextField pref_sabado_tarjeta2d;
    private javax.swing.JTextField pref_sabado_tarjeta3d;
    private javax.swing.JTextField pref_viernes2d;
    private javax.swing.JTextField pref_viernes3d;
    private javax.swing.JTextField pref_viernes_tarjeta2d;
    private javax.swing.JTextField pref_viernes_tarjeta3d;
    private javax.swing.JPanel repoentradas;
    private javax.swing.JPanel repogeneral;
    private javax.swing.JPanel repoventas;
    private javax.swing.JTextField sabado2d;
    private javax.swing.JTextField sabado3d;
    private javax.swing.JTextField sabado_tarjeta2d;
    private javax.swing.JTextField sabado_tarjeta3d;
    private javax.swing.JComboBox<String> sala_funcion;
    private javax.swing.JButton seleccionar_imagen;
    private javax.swing.JButton seleccionar_imagen_agregar;
    private javax.swing.JRadioButton superestreno_pelicula;
    private javax.swing.JPanel tarjeta;
    private javax.swing.JComboBox<String> tipo_pelicula;
    private javax.swing.JComboBox<String> tipo_pelicula_agregar;
    private javax.swing.JTextField valor_convenio2d;
    private javax.swing.JTextField valor_convenio3d;
    private javax.swing.JTextField valor_funcion_especial;
    private javax.swing.JTextField viernes2d;
    private javax.swing.JTextField viernes3d;
    private javax.swing.JTextField viernes_tarjeta2d;
    private javax.swing.JTextField viernes_tarjeta3d;
    // End of variables declaration//GEN-END:variables

}
