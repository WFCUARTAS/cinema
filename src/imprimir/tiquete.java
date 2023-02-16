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
import java.util.Vector;


public class tiquete extends Component implements Printable{
    
    ArrayList<String> datos = new ArrayList<String>();
    ArrayList<String> resolucion = new ArrayList<String>();
    String silla1,silla2;
    int consecutivo, tarifa;
    
    
    public tiquete(ArrayList<String> d, ArrayList<String> r, String s1,String s2, int c, int t){
        datos=d;
        resolucion=r;
        silla1=s1;
        silla2=s2;
        consecutivo=c;
        tarifa = t;
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
    renglon = (int)((pf.getImageableWidth()-(r*2))*2/3);
    
    
    //// Numero de factura ////
    texto ="Factura n°: ECN-"+consecutivo;    
    fo=new Font("Arial", Font.PLAIN, 9);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight();
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon);

    //// Numero de resolucion DIAN ////
    texto ="Res. DIAN n° "+resolucion.get(1);
    fo=new Font("Arial", Font.PLAIN, 7);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight();
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon);
    
    //// Fecha de resolucion DIAN ////
    texto ="Fechade expedicio: "+resolucion.get(2);
    fo=new Font("Arial", Font.PLAIN, 6);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight();
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon);
    
    //// numeros autorizados de resolucion DIAN ////
    texto ="Numeros Autorizados: "+resolucion.get(3)+" al "+resolucion.get(4);
    fo=new Font("Arial", Font.PLAIN, 6);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight();
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon);
    
    //////NOMBRE DE LA PELICLA
    texto=datos.get(0);
    punto = new Point2D.Double(margen, renglon );
    ancho = pf.getImageableWidth()-(margen*2);
    parrafo = new AttributedString(texto);
    parrafo.addAttribute(TextAttribute.FONT,new Font("Arial Black", Font.BOLD, 14));
    lineaBreaker = new LineBreakMeasurer(parrafo.getIterator(),new FontRenderContext(null,true,true));
    lineas = new Vector();
    while ( (Campo = lineaBreaker.nextLayout((float)ancho))!= null) {
        lineas.add(Campo);
    }

    for (int i = 0; i < lineas.size(); i++) {
        Campo = (TextLayout)lineas.get(i);
        if (i != lineas.size()-1) {
            //justCampo = Campo.getJustifiedLayout((float)ancho);
            justCampo = Campo;
        } else {
            justCampo = Campo;
            
        }
            punto.y += justCampo.getAscent();
            
            float f = (float)punto.x+((float)ancho/2)-(justCampo.getAdvance()/2);
            justCampo.draw(g2d,f,(float)punto.y+5);
            
    }
    renglon=(int)punto.y;

    //// HORA FUNCION  ////
    texto =datos.get(1);
    fo=new Font("Arial", Font.BOLD, 17);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight() ;
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon);    
    
    //// SALA FUNCION  ////
    texto ="sala "+datos.get(2);
    fo=new Font("Arial", Font.BOLD, 17);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight() ;
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon);   
    
    ///////////////////////////////////////7
    /////////SILLAS/////////////////////
    //////////////////////////////////
    
    if(silla2==""){
        /// PUESTO --CIRCULO ////
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(5));
        renglon=renglon+5;
        int tamx=50;
        int tamy=50;
        Ellipse2D circulo = new Ellipse2D.Double((pf.getImageableWidth()/2)-(tamx/2),renglon,tamx,tamy);
        g2d.draw(circulo);
        ///  PUESTO--TEXTO ////
        if(silla1=="DIS"){
            MediaTracker mt1 = new MediaTracker(this);
            Image dis = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/discapacidad-full.png"));
            mt1.addImage(dis,0);
            try {mt1.waitForID(0);} catch (InterruptedException e) {}
            g2d.drawImage(dis, (int)(pf.getImageableWidth()/2)-(tamx/2), renglon , tamx, tamy,this);
            
            renglon=renglon+tamy+5;
        }else{
            texto =silla1;
            fo=new Font("Tahoma", Font.BOLD, 20);
            g2d.setFont(fo);
            metrics = g2d.getFontMetrics(fo);
            g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon+ (tamy/2)+(metrics.getHeight()/3));    
            renglon=renglon+tamy+5;
        }
    }else{
        /// PUESTO --CIRCULO1 ////
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(5));
        renglon=renglon+5;
        int tamx=50;
        int tamy=50;
        Ellipse2D circulo = new Ellipse2D.Double((pf.getImageableWidth()/3)-(tamx/2),renglon,tamx,tamy);
        g2d.draw(circulo);
        circulo = new Ellipse2D.Double((pf.getImageableWidth()*2/3)-(tamx/2),renglon,tamx,tamy);
        g2d.draw(circulo);
        ///  PUESTO--TEXTO1 ////
        if(silla1=="DIS"){
            MediaTracker mt1 = new MediaTracker(this);
            Image dis = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/discapacidad-full.png"));
            mt1.addImage(dis,0);
            try {mt1.waitForID(0);} catch (InterruptedException e) {}
            g2d.drawImage(dis, (int)(pf.getImageableWidth()/3)-(tamx/2), renglon , tamx, tamy,this);

        }else{
            texto =silla1;
            fo=new Font("Tahoma", Font.BOLD, 20);
            g2d.setFont(fo);
            metrics = g2d.getFontMetrics(fo);
            g2d.drawString(texto,(int)(pf.getImageableWidth()/3)-(metrics.stringWidth(texto)/2),renglon+ (tamy/2)+(metrics.getHeight()/3));
        }
        
        if(silla2=="DIS"){
            MediaTracker mt1 = new MediaTracker(this);
            Image dis = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/discapacidad-full.png"));
            mt1.addImage(dis,0);
            try {mt1.waitForID(0);} catch (InterruptedException e) {}
            g2d.drawImage(dis, (int)(pf.getImageableWidth()*2/3)-(tamx/2), renglon , tamx, tamy,this);
        }else{
            texto =silla2;
            fo=new Font("Tahoma", Font.BOLD, 20);
            g2d.setFont(fo);
            metrics = g2d.getFontMetrics(fo);
            g2d.drawString(texto,(int)(pf.getImageableWidth()*2/3)-(metrics.stringWidth(texto)/2),renglon+ (tamy/2)+(metrics.getHeight()/3));      
        }
        renglon=renglon+tamy+5;
        
    }
    
    ////FECHA DE FUNCION ////
    texto =datos.get(3);
    fo=new Font("Arial", Font.BOLD, 16);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight();
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon);
    
    ////FECHA DE FUNCION ////
    texto ="Tarifa: $"+tarifa;
    fo=new Font("Arial", Font.BOLD, 14);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight();
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon);
        
    //// NIT ////
    texto ="NIT 901335090-1";
    fo=new Font("Arial", Font.PLAIN, 7);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight() ;
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon);

    ////FECHA DE VENTA  ////
    texto ="Fecha de venta: "+datos.get(4);
    fo=new Font("Arial", Font.PLAIN, 5);
    g2d.setFont(fo);
    metrics = g2d.getFontMetrics(fo);
    renglon = renglon + (int)metrics.getHeight() ;
    g2d.drawString(texto,(int)(pf.getImageableWidth()/2)-(metrics.stringWidth(texto)/2),renglon);    
    
    //////CONDICIONES
    texto="Prohibido ingreso de bebidas o alimentos no adquiridos en el cinema";
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
