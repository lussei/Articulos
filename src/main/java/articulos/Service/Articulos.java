package articulos.Service;

import articulos.Data.Articulo;
import articulos.Error.ExcepcionValidacion;
import articulos.Error.Mensajes;

import java.util.ArrayList;

public class Articulos {
    private articulos.DataAccess.Articulos da;
    public Articulos(){
        try {
            da = new articulos.DataAccess.Articulos();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void add(Articulo articulo) throws ExcepcionValidacion{
        if(da.get(articulo).isEmpty())
            da.add(articulo);
        else
            throw new ExcepcionValidacion(Mensajes.ARTICULO_EXISTE.getMensaje());
    }
    public void update(Articulo articulo)throws ExcepcionValidacion{
        if(da.get(articulo).isEmpty())
            throw new ExcepcionValidacion(Mensajes.ARTICULO_INEXISTENTE.getMensaje());
        else
            da.add(articulo);

    }
    public void delete(Articulo articulo)throws ExcepcionValidacion{
        if(da.get(articulo).isEmpty())
            throw new ExcepcionValidacion(Mensajes.ARTICULO_INEXISTENTE.getMensaje());
        else
            da.delete(articulo);


    }
    public ArrayList<Articulo> get(Articulo articulo){
        return da.get(articulo);
    }
}
