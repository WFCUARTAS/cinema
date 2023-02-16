/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author WILLIAN
 */
public class tarjeta {
    
    private String id,numero,nombre;
    private int puntos, precio_general, precio_preferencial;
    private boolean estado;
    
    public tarjeta(){
        id=numero=nombre=null;
        puntos=precio_general=precio_preferencial=0;
        estado=false;
    }
    
    public void set_id(String var){
        id=var;
    }
    public String get_id(){
        return id;
    }
    
    public void set_numero(String var){
        numero=var;
    }
    public String get_numero(){
        return numero;
    }
    
    public void set_nombre(String var){
        nombre=var;
    }
    public String get_nombre(){
        return nombre;
    }
    
    public void set_puntos(int var){
        puntos=var;
    }
    public int get_puntos(){
        return puntos;
    }
    
    public void set_estado(boolean var){
        estado=var;
    }
    public boolean get_estado(){
        return estado;
    }
    
    public void set_precio_preferencial(int var){
        precio_preferencial=var;
    }
    public int get_precio_preferencial(){
        return precio_preferencial;
    }
 
    public void set_precio_general(int var){
        precio_general=var;
    }
    public int get_precio_general(){
        return precio_general;
    }
        
}


