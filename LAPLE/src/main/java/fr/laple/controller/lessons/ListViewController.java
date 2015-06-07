package fr.laple.controller.lessons;

import fr.laple.model.language.ILanguagePlugin;
import fr.laple.model.lessons.AbstractLessonContainer;
import fr.laple.view.lessons.ListView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class ListViewController implements ActionListener{

    private ILanguagePlugin model;
    private ListView view;

    public ListViewController(ILanguagePlugin model, ListView view)
    {
        this.model = model;
        this.view = view;
        setList();

    }

    private void setList()
    {
        JList<AbstractLessonContainer> list = view.getList();
        DefaultListModel listModel = new DefaultListModel<>();

        for(int i = 0; i < model.getLessonContainers().size(); i++)
        {
            listModel.add(i, model.getLessonContainers().get(i));
        }

        list.setModel(listModel);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //TODO

    }
}
