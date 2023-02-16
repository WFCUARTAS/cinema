/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Image;

public class pelicula {
    private String id;
    private String nombre;
    private Image imagen;
    private String tipo;
    private boolean activa;
    private boolean super_estreno;
    private String imagen_url;    
    
    void pelicula(){
        id=null;
        nombre=null;
        imagen=null;
        tipo=null;
        activa=false;
        super_estreno=false;
        imagen_url=null;
    }
    
    public void set_id(String var){
        id=var;
    }
    public String get_id(){
        return id;
    }
    
    public void set_nombre(String var){
        nombre=var;
    }
    public String get_nombre(){
        return nombre;
    }
    
    public void set_imagen(Image var){
        imagen=var;
    }
    public Image get_imagen(){
        return imagen;
    }
    
    public void set_tipo(String var){
        tipo=var;
    }
    public String get_tipo(){
        return tipo;
    }
    
    public void set_activa(boolean var){
        activa=var;
    }
    public boolean get_activa(){
        return activa;
    }
    
    public void set_super_estreno(boolean var){
        super_estreno=var;
    }
    public boolean get_super_estreno(){
        return super_estreno;
    }
    
    public void set_imagen_url(String var){
        imagen_url=var;
    }
    public String get_imagen_url(){
        return imagen_url;
    }
}
