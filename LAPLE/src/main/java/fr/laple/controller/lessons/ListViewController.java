package fr.laple.controller.lessons;

import fr.laple.model.language.ILanguagePlugin;
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
public class ListViewController implements ActionListener {

    private List<AbstractLessonContainer> displayModel;
    private ListView view;
    private ILanguagePlugin model;

    public ListViewController(ILanguagePlugin model, List displayModel, ListView view) {
        this.displayModel = displayModel;
        this.view = view;
        this.model = model;
        setList();

    }

    private void setList() {
        JList list = view.getList();
        DefaultListModel listModel = new DefaultListModel<>();
        list.addListSelectionListener(new MyListSelectionListener());

        for (int i = 0; i < displayModel.size(); i++) {
            listModel.add(i, displayModel.get(i));

        }

        list.setModel(listModel);
        list.setCellRenderer(new ListViewCellRenderer());
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        Object selectedValue = view.getList().getSelectedValue();

        if (e.getSource().equals(view.getValidationButton())) {

            JTabbedPane tabbedPane = (JTabbedPane) view.getParent();

            if (selectedValue == null) {
                JOptionPane.showMessageDialog(view, "You must select a value !");

            } else {

                //TODO Not OOP at all


                if (view.getList().getSelectedValue() instanceof AbstractLessonContainer) {
                    AbstractLessonContainer container = (AbstractLessonContainer) selectedValue;

                    if (container instanceof SymbolLessonContainer) {

                        SymbolLessonContainer symbolLessonContainer = (SymbolLessonContainer) selectedValue;
                        int selected = tabbedPane.getSelectedIndex();
                        tabbedPane.remove(selected);
                        //view = new ListView<>(symbolLessonContainer.getLessons(), true);
                        tabbedPane.insertTab("Lessons", null, new ListView<>(model, symbolLessonContainer.getLessons(), true),
                                null, selected);
                        tabbedPane.setSelectedIndex(selected);

                    } else if (container instanceof WordLessonContainer) {

                        WordLessonContainer wordLessonContainer = (WordLessonContainer) selectedValue;
                        int selected = tabbedPane.getSelectedIndex();
                        tabbedPane.remove(selected);
                        //view = new ListView<>(wordLessonContainer.getLessonCategories(), true);
                        tabbedPane.insertTab("Lessons", null, new ListView<>(model, wordLessonContainer.getLessonCategories(), true), null, selected);
                        tabbedPane.setSelectedIndex(selected);
                    }

                } else if (selectedValue instanceof Lesson) {
                    Lesson selectedLesson = (Lesson) selectedValue;
                    int selected = tabbedPane.getSelectedIndex();

                    tabbedPane.remove(selected);
                    LessonView lessonView = new LessonView();
                    tabbedPane.insertTab("Lessons", null, lessonView, null, selected);
                    new LessonController(model, lessonView, selectedLesson);

                    tabbedPane.setSelectedIndex(selected);

                } else if (selectedValue instanceof LessonCategory) {
                    LessonCategory symbolLessonContainer = (LessonCategory) selectedValue;
                    int selected = tabbedPane.getSelectedIndex();
                    tabbedPane.remove(selected);
                    //view = new ListView<>(symbolLessonContainer.getLessons(), true);
                    tabbedPane.insertTab("Lessons", null, new ListView<>(model, symbolLessonContainer.getLessons(), true),
                            null, selected);
                    tabbedPane.setSelectedIndex(selected);

                }
            }

        } else {

            JTabbedPane tabbedPane = (JTabbedPane) view.getParent();

            int selected = tabbedPane.getSelectedIndex();
            tabbedPane.remove(selected);
            tabbedPane.insertTab("Lessons", null, new ListView<>(model, model.getLessonContainers(), false),
                    null, selected);
            tabbedPane.setSelectedIndex(selected);

        }
    }
}

