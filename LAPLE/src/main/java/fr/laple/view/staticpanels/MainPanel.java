package fr.laple.view.staticpanels;

import javax.swing.*;
import java.awt.*;

/**
 * This class is the main LAPLE panel. It contains the software's manual
 *
 * @author anthonyrey
 */
public class MainPanel extends JPanel {

    private JTextArea manual;

    public MainPanel(){

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        manual = new JTextArea();
        manual.setEnabled(false);

        createUI();

    }

    private void createUI()
    {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setHorizontalScrollBar(null);

        manual.setText("MANUAL \n\nSed ut perspiciatis unde omnis iste natus error sit" +
                        " voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo" +
                        " inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem " +
                        "quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione " +
                        "voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, " +
                        "adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat " +
                        "voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, " +
                        "nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate " +
                        "velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?" +
                        "Sed ut perspiciatis unde omnis iste natus error sit" +
                        " voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo" +
                        " inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem " +
                        "quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione " +
                        "voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, " +
                        "adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat " +
                        "voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, " +
                        "nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate " +
                        "velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"+
                        "Sed ut perspiciatis unde omnis iste natus error sit" +
                        " voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo" +
                        " inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem " +
                        "quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione " +
                        "voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, " +
                        "adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat " +
                        "voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, " +
                        "nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate " +
                        "velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"+
                        "Sed ut perspiciatis unde omnis iste natus error sit" +
                        " voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo" +
                        " inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem " +
                        "quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione " +
                        "voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, " +
                        "adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat " +
                        "voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, " +
                        "nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate " +
                        "velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?");

        manual.setLineWrap(true);
        manual.setWrapStyleWord(true);
        manual.setMargin(new Insets(20,20,20,20));
        panel.add(manual);
        add(scroll, BorderLayout.CENTER);


    }

}
