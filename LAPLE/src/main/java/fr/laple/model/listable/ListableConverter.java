package fr.laple.model.listable;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter for Lists of Listables
 *
 * @author anthonyrey
 */
public class ListableConverter<T extends IListable> {

    public List<IListable> typeToIListable(List<T> list)
    {
        ArrayList<IListable> listables = new ArrayList<>();
        for(T type : list )
            listables.add( type);

        return listables;
    }

}
