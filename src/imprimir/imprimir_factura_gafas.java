package imprimir;



import clases.*;
import imprimir.*;
import java.awt.print.*;
import java.util.ArrayList;
import java.util.Date;

public class imprimir_factura_gafas {


    public imprimir_factura_gafas(int cantidad, int valor) {
        super();

        PrinterJob printJob = PrinterJob.getPrinterJob();
        Book libro = new Book();
        Paper pa = new Paper();

        pa.setSize(164, 500);
        pa.setImageableArea(0,0, pa.getWidth(), pa.getHeight());
        
        PageFormat documentoPF = new PageFormat();
        
        documentoPF.setPaper(pa);
        
        ArrayList<Integer> datos = new ArrayList<Integer>();
        datos.add(cantidad);          // 0   nombre pelicula
        datos.add(valor); 
        
        libro.append(new factura_gafas(datos),documentoPF);
        

        //if(printJob.printDialog()){
    
            printJob.setPageable(libro);

            try {
                printJob.print();
            } catch (Exception PrinterException) {
                PrinterException.printStackTrace();
            }
        //}

    }



}