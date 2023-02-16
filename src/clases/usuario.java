/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

public class usuario {

    private String id;
    private String nombre;
    private int privilegios;
    private String ip;
    
    public usuario(){
        id=null;
        nombre=null;
        privilegios=0;
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
    
    public void set_privilegios(int var){
        privilegios=var;
    }
    public int get_privilegios(){
        return privilegios;
    }
    public void set_ip(String var){
        ip=var;
    }
    public String get_ip(){
        return ip;
    }
    
    
}
