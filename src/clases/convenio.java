/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Color;
/**
 *
 * @author WILLIANC
 */
public class convenio {
    private String id;
    private String  nombre;
    private String valor,valor3d;
    private boolean guarda;
    private Color color;

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
    
    public void set_valor(String var){
        valor=var;
    }
    public String get_valor(){
        return valor;
    }
    
    public void set_valor3d(String var){
        valor3d=var;
    }
    public String get_valor3d(){
        return valor3d;
    }
    
    public void set_guarda(boolean var){
        guarda=var;
    }
    public boolean get_guarda(){
        return guarda;
    }
    
    public void set_color(Color var){
        color=var;
    }
    public Color get_color(){
        return color;
    }
}
