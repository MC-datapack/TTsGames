package TTs.TTsGames.Games.AnimalMaster;

import TTs.TTsGames.Resources.Text;
import TTs.lang.Array;
import TTs.swing.BorderPanel;
import TTs.swing.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import static TTs.TTsGames.Main.*;
import static TTs.TTsGames.Resources.Translations.*;

public class AnimalMaster {
    public static int selectedAnimal, correct = 0; JButton[] answers = new JButton[39], next = new JButton[13];ImagePanel[] ImagePanels = new ImagePanel[37];
    BorderPanel[] questions = new BorderPanel[13], komplimente = new BorderPanel[13], beleidigungen = new BorderPanel[13], Correct = new BorderPanel[15];
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); JButton informations = new JButton(Informations[0]);
    JLabel placeholder = new JLabel("                           "), placeholderLarge = new JLabel("                                                          ");
    Dimension[] buttonSizes = {new Dimension((int) (102 * a), (int) (30 * a)), new Dimension((int) (118 * a), (int) (30 * a)),
            new Dimension((int) (133 * a), (int) (30 * a)), new Dimension((int) (200 * a), (int) (30 * a)),
            new Dimension((int) (300 * a), (int) (30 * a)), new Dimension((int) (360 * a), (int) (30 * a))};
    JButton Auswertung = new JButton(NextButtons[1]), Auswertung2 = new JButton(NextButtons[1]), HardQuestions = new JButton(NextButtons[2]),
    big = new JButton(Text.translatable("animal_master.picture.big"));
    public AnimalMaster(boolean resizable, Point location, Dimension size) { AnimalMasterTime[0] = System.nanoTime() + 1000000000;
        windows[1] = new JFrame(TTsGames[0] + Versions[1]); startedVersion = Versions[1];
        panel.setOpaque(false); panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 50, 10));
        for (int i = 0; i < 39; i++) answers[i] = new JButton(AnswerButtons[i]); for (int i = 0; i < 37; i++) ImagePanels[i] = Textures[1][i].toImagePanel();
        ImagePanels[0].setLayout(new BorderLayout()); ImagePanels[0].setSize((int) (420 * a), (int) (171 * a)); ImagePanels[0].add(panel);
        for (int i = 0; i < 13; i++) {next[i] = new JButton(NextButtons[0]); next[i].setBackground(buttonColors[ThreadLocalRandom.current().nextInt(buttonColors.length)]);
            questions[i] = new BorderPanel(Questions[i], Color.BLACK, true);
            questions[i].setBackground(buttonColors[ThreadLocalRandom.current().nextInt(buttonColors.length)]);
            komplimente[i] = new BorderPanel(Komplimente[ThreadLocalRandom.current().nextInt(Komplimente.length)], Color.BLACK, true);
            komplimente[i].setBackground(buttonColors[ThreadLocalRandom.current().nextInt(buttonColors.length)]);
            beleidigungen[i] = new BorderPanel(Beleidigungen[ThreadLocalRandom.current().nextInt(Beleidigungen.length)], Color.BLACK, true);
            beleidigungen[i].setBackground(buttonColors[ThreadLocalRandom.current().nextInt(buttonColors.length)]);
        } for (int i = 0; i < 15; i++) Correct[i] = new BorderPanel(EndResulate[i],Color.BLACK, true);
        Component[][] doStandardLabelStuff = {questions, komplimente, beleidigungen, Correct};
        for (Component[] componentArray : doStandardLabelStuff) for (Component component : componentArray) {
            component.setForeground(Color.WHITE); component.setVisible(false); panel.add(component, "South");}
        Component[][] doStandardButtonstuff = {{informations, Auswertung, Auswertung2, HardQuestions}, answers, next, {big}};
        for (Component[] componentArray : doStandardButtonstuff) for (Component component : componentArray) {
                ((JButton) component).setOpaque(true); ((JButton) component).setContentAreaFilled(true); component.setBackground(buttonColors[ThreadLocalRandom.current().nextInt(buttonColors.length)]);
                component.setForeground(Color.BLACK); component.setFocusable(false); component.setVisible(false); panel.add(component, "South");}
        questions[0].setVisible(true); answers[0].setVisible(true); answers[1].setVisible(true); answers[2].setVisible(true);
        for (ImagePanel component : ImagePanels) {windows[1].add(component, "South"); component.setLayout(new BorderLayout(10, 100));
            component.setSize((int) (420 * a), (int) (190 * a)); component.setVisible(false);}
        ImagePanels[0].setVisible(true); ImagePanels[0].add(panel, "South");
        Component[][] setPrefferendSizeButtonSize = {answers, next, {informations, big}}; Component[] setPrefferendSizeButtonSize2 = {Auswertung, Auswertung2};
        for (Component[] componentArray : setPrefferendSizeButtonSize) for (Component component : componentArray) component.setPreferredSize(buttonSizes[1]);
        for (Component component : setPrefferendSizeButtonSize2) component.setPreferredSize(buttonSizes[4]);
        HardQuestions.setPreferredSize(buttonSizes[3]); answers[34].setPreferredSize(buttonSizes[2]); answers[35].setPreferredSize(buttonSizes[0]);
        for (Component component : questions) component.setPreferredSize(buttonSizes[5]);
        for (Component component : beleidigungen) component.setPreferredSize(buttonSizes[5]);
        for (Component component : komplimente) component.setPreferredSize(buttonSizes[5]);
        for (BorderPanel component : Correct) component.setPreferredSize(new Dimension((int) (380 * a), (int) (35 * a))); //TODO ACTION LISTENER
        for (int i = 0; i < 13; i++) Answer(i);
        Next(new int[]{7, 12});
        next[7].addActionListener(e -> {informations.setVisible(false); Auswertung.setVisible(true);komplimente[7].setVisible(false); beleidigungen[7].setVisible(false);
            next[7].setVisible(false);ImagePanels[20].setVisible(false); ImagePanels[2].setVisible(false); ImagePanels[21].setVisible(false);
            ImagePanels[0].setVisible(true); ImagePanels[0].add(panel, "South"); big.setVisible(false);});
        next[12].addActionListener(e -> {informations.setVisible(false); komplimente[12].setVisible(false); beleidigungen[12].setVisible(false); next[12].setVisible(false);
            Auswertung2.setVisible(true); ImagePanels[34].setVisible(false); ImagePanels[35].setVisible(false); ImagePanels[36].setVisible(false);
            ImagePanels[0].setVisible(true); ImagePanels[0].add(panel, "South"); big.setVisible(false);});
        Auswertung.addActionListener(e -> {Correct[correct].setVisible(true); if (correct == 8) HardQuestions.setVisible(true); Auswertung.setVisible(false); fAMReset = true;});
        Auswertung2.addActionListener(e -> {Correct[correct + 1].setVisible(true); Auswertung2.setVisible(false); AnimalMasterTime[1] = System.nanoTime();
            if (((AnimalMasterTime[1] - AnimalMasterTime[0]) / 1000000000) < AnimalMasterTime[2]) {AnimalMasterTime[2] = ((AnimalMasterTime[1] - AnimalMasterTime[0]) / 1000000000);}
            else if (AnimalMasterTime[2] == -1.0F) {AnimalMasterTime[2] = ((AnimalMasterTime[1] - AnimalMasterTime[0]) / 1000000000);}
            logger.info(AnimalMasterTime[2]); configLoader.setAnimalMasterTimeRecord(AnimalMasterTime[2]); fAM = true;});
        HardQuestions.addActionListener(e -> {placeholder.setVisible(false); placeholderLarge.setVisible(false);
            for (Component component : next) component.setVisible(false); for (Component component : Correct) component.setVisible(false);
            questions[8].setVisible(true); answers[24].setVisible(true); answers[25].setVisible(true); answers[26].setVisible(true); HardQuestions.setVisible(false);});
        informations.addActionListener(e -> openWebpage(Informations[selectedAnimal]));
        big.addActionListener(e -> new Big(selectedAnimal, windows[1].getLocation()));
        WindowOperations(1, resizable, size, location, Textures[1][37]);}
    private void Answer(int question) {
        answers[question*3].addActionListener(e -> AnswerACL(question, jsonReader.AMSelectedAnimal()[question*3], jsonReader.AMCorrect()[question*3]));
        answers[question*3+1].addActionListener(e -> AnswerACL(question, jsonReader.AMSelectedAnimal()[question*3+1], jsonReader.AMCorrect()[question*3+1]));
        answers[question*3+2].addActionListener(e -> AnswerACL(question, jsonReader.AMSelectedAnimal()[question*3+2], jsonReader.AMCorrect()[question*3+2]));}
    private void Next(int[] not) {for (int i = 0; i < 13; i++) if (Array.dontContains(not, i)) {int I = i;next[i].addActionListener(e -> NextACL(I));}}
    private void AnswerACL(int question, int i, boolean Correct) {
        if (Correct) {Sounds[1][0].playSound(); correct++; komplimente[question].setVisible(true);} else {Sounds[1][1].playSound(); beleidigungen[question].setVisible(true);}
        questions[question].setVisible(false);answers[question*3].setVisible(false); answers[question*3+1].setVisible(false); answers[question*3+2].setVisible(false);
        next[question].setVisible(true); selectedAnimal = i; informations.setVisible(true); big.setVisible(true); ImagePanels[0].setVisible(false);
        ImagePanels[i].setVisible(true); ImagePanels[i].add(panel, "South");}
    private void NextACL(int question) {
        next[question].setVisible(false); komplimente[question].setVisible(false); beleidigungen[question].setVisible(false);
        ImagePanels[jsonReader.AMSelectedAnimal()[question*3]].setVisible(false); ImagePanels[jsonReader.AMSelectedAnimal()[question*3+1]].setVisible(false);
        ImagePanels[jsonReader.AMSelectedAnimal()[question*3+2]].setVisible(false);
        informations.setVisible(false); big.setVisible(false); ImagePanels[0].setVisible(true); ImagePanels[0].add(panel, "South");
        questions[question+1].setVisible(true); answers[(question+1)*3].setVisible(true); answers[(question+1)*3+1].setVisible(true); answers[(question+1)*3+2].setVisible(true);}
}