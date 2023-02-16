/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.admin;

import cinema.administrador;
import clases.conectar;
import clases.tablaimagen;
import clases.usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 privilegios de usuario
 * 
 * 0= usuario vendedor
 * 1=ususario admin
 * 2=usuario editor
 * 
 */
public class usuarios_admin extends javax.swing.JPanel {

    conectar cc_usuarios =new conectar();
    Connection con_usuarios;
    
    usuario user = new usuario();
    
    DefaultTableModel modelo_usuarios;
    
    
    public usuarios_admin(usuario us) {
        user=us;
        ImageIcon ruta;
        
        initComponents();
        

        lista_usuarios.setDefaultRenderer(Object.class, new tablaimagen());
        modelo_usuarios =  (DefaultTableModel) lista_usuarios.getModel();
        cargar();
    }

    public void cargar(){
        con_usuarios = cc_usuarios.conexion(user.get_ip());
        ImageIcon estado;
        
        int a =modelo_usuarios.getRowCount();
        for(int i=0; i<a;i++){
            modelo_usuarios.removeRow(0);
        }
        

        try {
            Statement st = con_usuarios.createStatement();
            ResultSet result =st.executeQuery("SELECT * FROM usuarios WHERE id!="+user.get_id()+" AND id!=0 ORDER BY id DESC");

            while(result.next()){
                
                Object[] datosDeLaFila= new Object [3];
                
                datosDeLaFila[0]=result.getString(2);
                if(result.getString(4).equals("0")){
                    datosDeLaFila[1]="VENDE";
                }else if(result.getString(4).equals("1")){
                    datosDeLaFila[1]="ADMIN";
                }else if(result.getString(4).equals("2")){
                    datosDeLaFila[1]="EDIT";
                }
                
                datosDeLaFila[2]=new JButton("actualizar");
                
                modelo_usuarios.addRow(datosDeLaFila);
                lista_usuarios.setModel(modelo_usuarios);
            }
            
            
            con_usuarios.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 52:  "+ex);
            Logger.getLogger(administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lista_usuarios = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        contrasena1_add = new javax.swing.JPasswordField();
        jLabel34 = new javax.swing.JLabel();
        contrasena2_add = new javax.swing.JPasswordField();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        agregar_usuario = new javax.swing.JButton();
        nombre_usuario_add = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tipo_usuario = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(204, 204, 255));
        setPreferredSize(new java.awt.Dimension(600, 300));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lista_usuarios.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lista_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "TIPO", "CONTRASEÑA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lista_usuarios.setRowHeight(25);
        lista_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_usuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lista_usuarios);
        if (lista_usuarios.getColumnModel().getColumnCount() > 0) {
            lista_usuarios.getColumnModel().getColumn(0).setResizable(false);
            lista_usuarios.getColumnModel().getColumn(0).setPreferredWidth(120);
            lista_usuarios.getColumnModel().getColumn(1).setResizable(false);
            lista_usuarios.getColumnModel().getColumn(1).setPreferredWidth(80);
            lista_usuarios.getColumnModel().getColumn(2).setResizable(false);
            lista_usuarios.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 300, 240));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ADMINISTRAR USUARIOS");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 398, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("CONTRASEÑA");
        add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 160, 20));

        contrasena1_add.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        contrasena1_add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                contrasena1_addKeyTyped(evt);
            }
        });
        add(contrasena1_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 110, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("TIPO DE USUARIO ");
        add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 140, 20));

        contrasena2_add.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(contrasena2_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 110, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("NOMBRE DE USUARIO");
        add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 160, 20));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("REPETIR CONTRASEÑA");
        add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 180, 20));

        agregar_usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        agregar_usuario.setText("AGREGAR");
        agregar_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_usuarioActionPerformed(evt);
            }
        });
        add(agregar_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 130, 30));

        nombre_usuario_add.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        add(nombre_usuario_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 110, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("TIPOS:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 90, 30));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("VENDE: vendedor    ADMIN:administrador     EDIT:editor");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 460, -1));

        tipo_usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tipo_usuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VENDE", "ADMIN", "EDIT" }));
        add(tipo_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 80, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void contrasena1_addKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contrasena1_addKeyTyped
        if(contrasena1_add.getText().length()>19)evt.consume();
    }//GEN-LAST:event_contrasena1_addKeyTyped

    private void agregar_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_usuarioActionPerformed
        // TODO add your handling code here:
        boolean usuario_existe=false;
        try {
            con_usuarios = cc_usuarios.conexion(user.get_ip());
            
            Statement st = con_usuarios.createStatement();
            ResultSet result =st.executeQuery("SELECT nombre FROM usuarios WHERE nombre='"+nombre_usuario_add.getText()+"'");

            if(result.next()){
                usuario_existe=true;
            }
            con_usuarios.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error 53:  "+ex);
        }

        if(!usuario_existe){
            if(contrasena1_add.getText().equals(contrasena2_add.getText())){
                String sql;

                sql="INSERT INTO usuarios  (nombre,contrasena,privilegios) VALUES (?,?,?)";
                try {
                    con_usuarios = cc_usuarios.conexion(user.get_ip());
                    
                    PreparedStatement pst= con_usuarios.prepareStatement(sql);
                    
                    pst.setString(1,nombre_usuario_add.getText());
                    pst.setString(2,contrasena1_add.getText());
                    pst.setInt(3,tipo_usuario.getSelectedIndex());
                
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"AGREGADO CON EXITO");
                    contrasena1_add.setText("");
                    contrasena2_add.setText("");
                    nombre_usuario_add.setText("");
                    tipo_usuario.setSelectedIndex(0);
                    
                    con_usuarios.close();
                    
                    cargar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"error 54:  "+ex);
                }

            }
            else{
                JOptionPane.showMessageDialog(null,"CONTRASEÑA NO COINCIDE");
            }

        }
        else{
            JOptionPane.showMessageDialog(null,"EL NOMBRE DE USUARIO YA EXISTE","error",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_agregar_usuarioActionPerformed

    private void lista_usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_usuariosMouseClicked
        int fila= lista_usuarios.rowAtPoint(evt.getPoint());
        String nombre=""+lista_usuarios.getValueAt(fila, 0);
        
        Object [] colores ={"VENDEDOR","ADMINISTRADOR","EDITOR"}; 
        //// canbiar tipo de usuario 
        if(lista_usuarios.columnAtPoint(evt.getPoint())==1){ 
            
            try {
                con_usuarios = cc_usuarios.conexion(user.get_ip());
                Statement st= con_usuarios.createStatement();

                ResultSet result =st.executeQuery("SELECT * FROM usuarios WHERE nombre='"+nombre+"'");
                result.next();
                int privilegios=result.getInt(4);  

                Object opcion = JOptionPane.showInputDialog(null,"Selecciona tipo de usuarios", "ACTUALIZAR",JOptionPane.QUESTION_MESSAGE,null,colores, colores[privilegios]);
                
                if(opcion!=null){
                    if(opcion.equals("VENDEDOR")){
                        privilegios=0;
                    }else if(opcion.equals("ADMINISTRADOR")){
                         privilegios=1;
                    }else if(opcion.equals("EDITOR")){
                         privilegios=2;
                    }

                    String sql="UPDATE usuarios  SET privilegios='"+privilegios+"' WHERE nombre='"+nombre+"' ";
                    PreparedStatement pst= con_usuarios.prepareStatement(sql);
                    pst.executeUpdate();

                    if(privilegios==0){
                        lista_usuarios.setValueAt("VENDE", fila,1);
                    }else if(privilegios==1){
                        lista_usuarios.setValueAt("ADMIN", fila,1);
                    }else if(privilegios==2){
                        lista_usuarios.setValueAt("EDIT", fila,1);
                    }
                    

                }
                con_usuarios.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error 55:  "+ex);
                Logger.getLogger(usuarios_admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        /////ACTUALIZAR CONTRASEÑA
        if(lista_usuarios.columnAtPoint(evt.getPoint())==2){ 
            String pass = JOptionPane.showInputDialog(null,"DIGITE NUEVA CONTRASEÑA");
            
            if(pass != null){
                try {
                    con_usuarios = cc_usuarios.conexion(user.get_ip());
                    Statement st= con_usuarios.createStatement();

                    String sql="UPDATE usuarios  SET contrasena='"+pass+"' WHERE nombre='"+nombre+"' ";
                    PreparedStatement pst= con_usuarios.prepareStatement(sql);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null,"ACTUALIZADO CON EXITO");
                    con_usuarios.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"error 56:  "+ex);
                    Logger.getLogger(usuarios_admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }

        
    }//GEN-LAST:event_lista_usuariosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar_usuario;
    private javax.swing.JPasswordField contrasena1_add;
    private javax.swing.JPasswordField contrasena2_add;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable lista_usuarios;
    private javax.swing.JTextField nombre_usuario_add;
    private javax.swing.JComboBox<String> tipo_usuario;
    // End of variables declaration//GEN-END:variables
}
