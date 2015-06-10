package fr.laple.controller.lessons;

import fr.laple.model.lessons.*;
import fr.laple.view.lessons.LessonView;
import fr.laple.view.lessons.ListView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class ListViewController implements ActionListener{

    private List<AbstractLessonContainer> model;
    private ListView view;

    public ListViewController(List model, ListView view)
    {
        this.model = model;
        this.view = view;
        setList();

    }

    private void setList()
    {
        JList<AbstractLessonContainer> list = view.getList();
        DefaultListModel<AbstractLessonContainer> listModel = new DefaultListModel<>();

        for(int i = 0; i < model.size(); i++)
        {
            listModel.add(i, model.get(i));
        }

        list.setModel(listModel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        Object selectedValue = view.getList().getSelectedValue();

        if(selectedValue == null)
        {
            JOptionPane.showMessageDialog(view, "You must select a value !");

        }
        else
        {
            view.invalidate();
            view.removeAll();
            //If not set to 0, borders are cumulating
            view.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

            if(e.getSource().equals(view.getValidationButton()))
            {
                //TODO So ugly, much completement naze, wow


                if(view.getList().getSelectedValue() instanceof AbstractLessonContainer)
                {
                    AbstractLessonContainer container = (AbstractLessonContainer) selectedValue;

                    if(container instanceof SymbolLessonContainer)
                    {

                        SymbolLessonContainer symbolLessonContainer = (SymbolLessonContainer) selectedValue;
                        view.add(new ListView<>(symbolLessonContainer.getLessons(), true));
                    }
                    else if (container instanceof WordLessonContainer)
                    {

                        WordLessonContainer wordLessonContainer = (WordLessonContainer) selectedValue;
                        view.add(new ListView<>(wordLessonContainer.getLessonCategories(), true));
                    }

                }
                else if(selectedValue instanceof Lesson)
                {
                    //TODO add controller
                    LessonView lessonView = new LessonView();
                    new LessonController(lessonView, (Lesson) selectedValue);
                    view.add(lessonView);
                }
                else if(selectedValue instanceof LessonCategory)
                {
                    LessonCategory symbolLessonContainer = (LessonCategory) selectedValue;
                    view.add(new ListView<>(symbolLessonContainer.getLessons(), true));

                }

                view.revalidate();
                view.repaint();

            }
            else
            {
                //Back to root view
                //view.add(new ListView<>(model.getLessonContainers(), false));
            }

        }

    }
}
