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

            //TODO remove when problem with add panel fixed
            //If not set to 0, borders are cumulating
            //view.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

            if(e.getSource().equals(view.getValidationButton()))
            {
                //TODO So ugly, much completement naze, wow
                //TODO do the same modification (tabs instead of modify the panel)
                JTabbedPane tabbedPane = (JTabbedPane) view.getParent();

                if(view.getList().getSelectedValue() instanceof AbstractLessonContainer)
                {
                    AbstractLessonContainer container = (AbstractLessonContainer) selectedValue;

                    if(container instanceof SymbolLessonContainer)
                    {

                        SymbolLessonContainer symbolLessonContainer = (SymbolLessonContainer) selectedValue;
                        int selected = tabbedPane.getSelectedIndex();
                        tabbedPane.remove(selected);
                        //view = new ListView<>(symbolLessonContainer.getLessons(), true);
                        tabbedPane.insertTab("Lessons", null, new ListView<>(symbolLessonContainer.getLessons(), true),
                                null, selected);
                        tabbedPane.setSelectedIndex(selected);

                    }
                    else if (container instanceof WordLessonContainer)
                    {

                        WordLessonContainer wordLessonContainer = (WordLessonContainer) selectedValue;
                        int selected = tabbedPane.getSelectedIndex();
                        tabbedPane.remove(selected);
                        //view = new ListView<>(wordLessonContainer.getLessonCategories(), true);
                        tabbedPane.insertTab("Lessons", null, new ListView<>(wordLessonContainer.getLessonCategories(), true), null, selected);
                        tabbedPane.setSelectedIndex(selected);
                    }

                }
                else if(selectedValue instanceof Lesson)
                {
                    Lesson selectedLesson = (Lesson) selectedValue;
                    int selected = tabbedPane.getSelectedIndex();

                    tabbedPane.remove(selected);
                    LessonView lessonView = new LessonView();
                    tabbedPane.insertTab("Lessons", null, lessonView, null, selected);
                    new LessonController(lessonView, selectedLesson);

                    tabbedPane.setSelectedIndex(selected);

                }
                else if(selectedValue instanceof LessonCategory)
                {
                    LessonCategory symbolLessonContainer = (LessonCategory) selectedValue;
                    int selected = tabbedPane.getSelectedIndex();
                    tabbedPane.remove(selected);
                    //view = new ListView<>(symbolLessonContainer.getLessons(), true);
                    tabbedPane.insertTab("Lessons", null, new ListView<>(symbolLessonContainer.getLessons(), true),
                            null, selected);
                    tabbedPane.setSelectedIndex(selected);

                }
            }
            else
            {
                //Back to root view

            }

        }

    }
}
