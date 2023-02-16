/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Date;

/**
 *
 * @author WILLIANC
 */
public class funcion {
    private String id;
    private int sala;
    private String hora;
    private String minutos;
    private String ampm;
    private boolean activa;
    private Date fecha;
    private int precio;
    private String pelicula_id;
    private int franja;

    
    public funcion(){
        id=null;
        sala=0;
        hora=null;
        minutos=null;
        ampm=null;
        activa=false;
        fecha=null;
        precio=0;
        pelicula_id=null;
        franja = 1;
    }
    
    public void set_id(String var){
        id=var;
    }
    public String get_id(){
        return id;
    }
    
    public void set_sala(int var){
        sala=var;
    }
    public int get_sala(){
        return sala;
    }
    
    public void set_hora(String var){
        hora=var;
    }
    public String get_hora(){
        return hora;
    }
    
    public void set_minutos(String var){
        minutos=var;
    }
    public String get_minutos(){
        return minutos;
    }
    
    public void set_ampm(String var){
        ampm=var;
    }
    public String get_ampm(){
        return ampm;
    }
    
    public String get_hora_completa(){
        return hora+":"+minutos+" "+ampm;
    }
    
    public void set_activa(boolean var){
        activa=var;
    }
    public boolean get_activa(){
        return activa;
    }

    public void set_fecha(Date var){
        fecha=var;
    }
    public Date get_fecha(){
        return fecha;
    }
    
    public void set_precio(int var){
        precio=var;
    }
    public int get_precio(){
        return precio;
    }
    
    public void set_pelicula_id(String var){
        pelicula_id=var;
    }
    public String get_pelicula_id(){
        return pelicula_id;
    }
    
    public void set_franja(int var){
        franja=var;
    }
    public int get_franja(){
        return franja;
    }
    
}
