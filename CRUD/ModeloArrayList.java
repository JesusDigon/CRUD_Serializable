package CRUD;
import java.util.Scanner;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Implementa la parte de Modelo de Datow
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class ModeloArrayList extends ModeloAbs implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList <Producto> lista;
    Scanner sc=new Scanner(System.in);
    
    public ModeloArrayList()
    {
       lista=new ArrayList <Producto>();
       if(cargarLista("productos.objetos")!=null) {
       lista= cargarLista("productos.objetos");
       }
    }

    // Implementar los metodos abstractos de ModeloAbs
    public boolean insertarProducto ( Producto p){
     
    	boolean retorno= false;
    	
    	if(p!=null) {
    	lista.add(p);
    	retorno = true;
    	}
    	
    	return retorno;    
    }
    
 
    public boolean borrarProducto ( int codigo ){
      boolean retorno=false;
      if(buscarProducto(codigo)!=null) {
    	  lista.remove(buscarProducto(codigo));
    	  retorno=true;
      }
      return retorno;
    }
    
    
    public Producto buscarProducto ( int codigo) {
        Producto r= null;
        
        for(int i=0;i<lista.size();i++) {
        	if(lista.get(i).getCodigo()==codigo) {
        		r=lista.get(i);
        		break;
        	}
        }
    	return r;
    }
    
    public void listarProductos (){
       
    	for(int i=0;i<lista.size();i++) {
        	System.out.println(lista.get(i));
        }
    }
    
    public void listarPocoStock() {
    	
    	for (int i=0;i<lista.size();i++) {
    		if(lista.get(i).getStock_min()>=lista.get(i).getStock()) {
    			System.out.println(lista.get(i));
    		}
    	}
    }
    
    public void modificarProducto (String fichero){
    	try {
    		FileOutputStream fos=new FileOutputStream(fichero);
    		ObjectOutputStream oos=new ObjectOutputStream(fos);
    		for(Producto p: lista) {
    			oos.writeObject(p);
    		}
    		oos.close();
    	}catch(IOException ioe) {
    		System.err.println("Error en E/S sobre fichero: "+fichero+ "" +ioe);
    	}
    
}
    public ArrayList<Producto> cargarLista(String fichero) {
    	ArrayList <Producto> lista=new ArrayList <Producto>();
    	try {
    		FileInputStream fis=new FileInputStream(fichero);
    		ObjectInputStream ois=new ObjectInputStream(fis);
    		try {
    			lista.add((Producto)ois.readObject());
    			while(true) {
    				lista.add((Producto)ois.readObject());
    			}
    		}catch(EOFException ex) {}
    		ois.close();
    		fis.close();
    	}catch(IOException ioe) {
    		System.err.println("Error en E/S sobre fichero "+fichero+ " "+ioe);
    	}catch(ClassNotFoundException cex) {
    		System.err.println("El fichero no tiene objetos");
    	}
    	
    	return lista;
    }
}
    

