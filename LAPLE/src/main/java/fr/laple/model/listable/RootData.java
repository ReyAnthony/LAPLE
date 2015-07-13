package fr.laple.model.listable;

import java.util.List;

/**
 * Created by anthonyrey on 13/07/2015.
 */
public class RootData {

    private List<IListable> rootModel;
    private String rootTitle;


    public RootData(List<IListable> rootModel, String rootTitle) {
        this.rootModel = rootModel;
        this.rootTitle = rootTitle;
    }

    public String getRootTitle() {
        return rootTitle;
    }

    public List<IListable> getRootModel() {
        return rootModel;
    }
}
