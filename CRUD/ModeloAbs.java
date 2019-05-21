package CRUD;

import java.io.Serializable;

/**
 * Abstract class ModeloAbs - Clase de acceso a Modelo de DATOS
 * 
 * @author: Alberto Lopez
 * Date: 24/04/2018
 */
public abstract class ModeloAbs implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModeloAbs() {
    }
    
    abstract public boolean insertarProducto ( Producto p);
    
    abstract boolean borrarProducto ( int codigo );
    
    abstract public Producto buscarProducto ( int codigo);
    
    abstract void listarProductos ();
    
    abstract void listarPocoStock();
    
    abstract void modificarProducto (String fichero);
    
    
}
