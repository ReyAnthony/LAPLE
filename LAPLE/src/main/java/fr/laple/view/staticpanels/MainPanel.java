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
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        manual = new JTextArea();
        manual.setEditable(false);

        createUI();

    }

    private void createUI()
    {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setHorizontalScrollBar(null);

        manual.setText("LAPLE, Document utilisateur \n" +
                "\n" +
                "LAPLE, Logiciel D’Apprentissage De Langues Etrangères, est un logiciel dont le but est de faciliter " +
                "l’apprentissage de nouvelles langues aux utilisateurs.\n" +
                "\n" +
                "Divisé en 2 sections principales, Leçons et Exercices, le logiciel met l’emphase sur un apprentissage " +
                "associatif. On affiche un symbole d’une langue étrangère et sa transcription à l’utilisateur et celui-ci dans le but créer une association dans sa mémoire. \n" +
                "\n" +
                "Avec LAPLE, on peut apprendre les alphabets ainsi que les mots,  mais pour créer des associations plus" +
                " efficaces,  on préconisera l’apprentissage de langues à idéogrammes (comme le Japonais ou le chinois) car on peut directement associer un symbole à une transcription. \n" +
                "\n" +
                "Merci de noter que l’interface peut varier selon votre système.\n" +
                "\n" +
                "I) Démarrage de l’application\n" +
                "I)\tSe connecter à l’application \n" +
                "\n" +
                "Pour se connecter à l’application, rien de plus simple : \n" +
                "Il suffit de lancer celle-ci et vous verrez apparaître, une fois le splashscreen disparu, " +
                "l’interface de connexion au compte LAPLE.\n" +
                "\n" +
                "\n" +
                "Si vous avez déjà crée un compte à partir du site web, il suffit de rentrer vos informations de connexion. \n" +
                "\n" +
                "En cas de panne de connexion internet et si vous vous êtes déjà connecté une fois à l’application, " +
                "celle-ci basculera en mode en hors ligne une fois vos identifiants entrés. \n" +
                "\n" +
                "Dans le cas ou vous n’avez pas de compte, cliquez sur « I do not have an account » " +
                "et vous serez redirigé vers le site http://laple.fr .\n" +
                "\n" +
                "\n" +
                "2) Sélectionner une langue \n" +
                "\n" +
                "Une fois connecté à l’application, vous arriverez sur la page de sélection des langues. \n" +
                "Sélectionnez simplement la langue voulue dans le menu déroulant puis cliquez sur « Ok ».\n" +
                "\n" +
                "\n" +
                "\n" +
                "II) Les différentes fonctionnalités du logiciel \n" +
                "\n" +
                "Une fois la langue sélectionnée on arrive directement sur l’interface principale de LAPLE.\n" +
                "\n" +
                " \n" +
                "Panneau principal de l’interface LAPLE\n" +
                "\n" +
                "A partir de cette interface vous pouvez sélectionner différents onglets qui correspondent aux" +
                " fonctionnalités du logiciel. \n" +
                "\n" +
                "\n" +
                "1)  Onglet leçons \n" +
                "\n" +
                "L’onglet leçon permet à un utilisateur d’apprendre de nouveaux symboles et de nouveaux mots." +
                " L’affichage dépend de l’implémentation du plugin de langue.\n" +
                "\n" +
                "Une leçon prend la forme suivante : \n" +
                "\n" +
                "\n" +
                "On peut donc dans une leçon : \n" +
                "\n" +
                "-\tS’entrainer à dessiner un symbole\n" +
                "-\tL’entendre\n" +
                "-\tSe tester sur l’ensemble des symboles déjà connus\n" +
                "\n" +
                "\n" +
                "2) Onglet exercices \n" +
                "\n" +
                "Il peut y’avoir plusieurs types d’exercices disponibles selon les plugins installés, " +
                "néanmoins il y’a de base des exercices standards :\n" +
                "\n" +
                "\n" +
                "\n" +
                "On vous proposera les choix suivants : \n" +
                "\n" +
                "-\tModes de symboles \n" +
                "\n" +
                "-\tMode de questions \n" +
                "o\tLangage appris vers langue utilisateur\n" +
                "o\tLangue utilisateur vers langage appris\n" +
                "\n" +
                "-\tMode de réponse \n" +
                "o\tQCM Mode\n" +
                "o\tMode dessin\n" +
                "o\tMode réponse libre (texte) \n" +
                " \n" +
                "\n" +
                "Certaines combinaisons sont impossibles : \n" +
                "\n" +
                "o\tLangage appris vers langue utilisateur + mode dessin\n" +
                "o\tLangue utilisateur vers langage appris + mode réponse libre \n" +
                "\n" +
                "\n" +
                "Vue d’exemple d’un exercice \n" +
                "\n" +
                "\n" +
                "3) Onglet statistiques\n" +
                "4) User Settings\n" +
                "\n" +
                "Vous pouvez également changer vos informations et la configuration des plugins dans l’onglet user settings. \n" +
                "\n" +
                "\n" +
                "Vue pour régler les plugins\n" +
                "\n" +
                "On peut ajouter / supprimer 2 types de plugins : \n" +
                "\n" +
                "-\tLes plugins de langue \n" +
                "-\tLes plugins de fonctionnalités \n" +
                "o\tCeux-ci ajoutent des onglets ou des modes d’exercices\n" +
                "\n" +
                "Si un plugin est « interne » vous ne pourrez pas le supprimer. \n" +
                "\n" +
                "\n");

        manual.setLineWrap(true);
        manual.setWrapStyleWord(true);
        manual.setMargin(new Insets(20,20,20,20));
        panel.add(manual);
        add(scroll, BorderLayout.CENTER);


    }

}
