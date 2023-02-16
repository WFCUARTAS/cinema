package imprimir;

import clases.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;


public class factura_gafas extends Component implements Printable{
    

    int cantidad,valor;
        
    public factura_gafas(ArrayList<Integer> d){

        cantidad=d.get(0);
        valor=d.get(1);
        
    }
public int print(Graphics g,PageFormat pf, int page){
    Graphics2D g2d = (Graphics2D)g;
    int margen=15;
    int renglon;
    String texto ="";
    Font fo;
    FontMetrics metrics;
    ///variables para parrafos
    Point2D.Double punto;
    double ancho;
    AttributedString parrafo;
    LineBreakMeasurer lineaBreaker;
    TextLayout Campo;
    TextLayout justCampo;
    Vector lineas;
    
    
    /// BORDE //// SOLO PARA PRUEBAS BORRAR
    //g2d.translate(pf.getImageableX(),pf.getImageableY());
    g2d.setColor(Color.black);
    g2d.setStroke(new BasicStroke(5));
    Rectangle2D.Double borde = new Rectangle2D.Double(0,0,pf.getImageableWidth(),pf.getImageableHeight());
    //g2d.draw(borde);

    ///IMAGEN LOGO CINE ///
    MediaTracker mt = new MediaTracker(this);
    Image imagen = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/logo.png"));
    mt.addImage(imagen,0);
    try {mt.waitForID(0);} catch (InterruptedException e) {}
    int r =30;
    g2d.drawImage(imagen, r-5, 10 , (int)pf.getImageableWidth()-(r*2), (int) (pf.getImageableWidth()-(r*2))*2/3,this);
    renglon = (int)((pf.getImageableWidth()-(r*2))*2/3)+10;
    
    //////NOMBRE DE LA PELICLA
    texto ="VENTA GAFAS";
    fo=new Font("Arial Black", Font.BOLD, 14);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight() ;
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon); 
    
    ///IMAGEN LOGO GAFAS ///
    MediaTracker gafa = new MediaTracker(this);
    Image imagen_gafa = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/gafas.png"));
    gafa.addImage(imagen_gafa,0);
    try {gafa.waitForID(0);} catch (InterruptedException e) {}
    r =60;
    g2d.drawImage(imagen_gafa, r-5, renglon , (int)pf.getImageableWidth()-(r*2), (int) (pf.getImageableWidth()-(r*2))*2/3,this);
    renglon = renglon+ (int)((pf.getImageableWidth()-(r*2))*2/3);
    
    
    //// CANTIDAD  ////
    texto ="CANTIDAD: "+cantidad;
    fo=new Font("Arial", Font.BOLD, 12);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight() ;
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon);    
    
    //// VALOR UNITARIO  ////
    texto ="VALOR UNIT: $"+(valor/cantidad);
    fo=new Font("Arial", Font.BOLD, 12);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight() ;
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon);   
    
   
    ////TARIFA DE FUNCION ////
    texto ="TOTAL: $"+valor;
    fo=new Font("Arial", Font.BOLD, 17);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight();
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon);
        
    //// NIT ////
    texto ="NIT 39627750-1";
    fo=new Font("Arial", Font.PLAIN, 8);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight() ;
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon);

    ////FECHA DE VENTA  ////
    texto ="Fecha de venta: "+new Date();
    fo=new Font("Arial", Font.PLAIN, 6);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight() +2;
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon);    
    
    //////CONDICIONES
    
    texto="espacio para ingresar condiciones de la compra de gafas";
    punto = new Point2D.Double(margen, renglon );
    ancho = pf.getImageableWidth()-(margen*2);
    parrafo = new AttributedString(texto);
    parrafo.addAttribute(TextAttribute.FONT,new Font("Arial", Font.PLAIN, 7));
    lineaBreaker = new LineBreakMeasurer(parrafo.getIterator(),new FontRenderContext(null,true,true));
    lineas = new Vector();
    while ( (Campo = lineaBreaker.nextLayout((float)ancho))!= null) {
        lineas.add(Campo);
    }

    for (int i = 0; i < lineas.size(); i++) {
        Campo = (TextLayout)lineas.get(i);
        if (i != lineas.size()-1) {
            justCampo = Campo.getJustifiedLayout((float)ancho);
        } else {
            justCampo = Campo;
            
        }
            punto.y += justCampo.getAscent();
            
            float f = (float)punto.x+((float)ancho/2)-(justCampo.getAdvance()/2);
            justCampo.draw(g2d,f,(float)punto.y);
            
    }
    renglon=(int)punto.y;
    
    
    ////LINEA FINAL////
    texto ="-------------------------";
    fo=new Font("Arial", Font.PLAIN, 20);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight() ;
    g2d.drawString(texto,margen,renglon); 
    
    
    return (PAGE_EXISTS);
    }    
}
