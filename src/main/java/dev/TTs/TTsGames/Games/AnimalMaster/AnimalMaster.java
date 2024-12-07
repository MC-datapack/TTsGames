package dev.TTs.TTsGames.Games.AnimalMaster; import static dev.TTs.TTsGames.Main.*; import static dev.TTs.TTsGames.Resources.Translations.*; import static java.util.concurrent.ThreadLocalRandom.current;
import dev.TTs.lang.Array; import dev.TTs.swing.*; import javax.swing.*; import java.awt.*; public final class AnimalMaster {Color black = Color.BLACK; public static int sel, correct = 0;
    JButton[] answers = new JButton[jsonReader.AInf("a")], next = new JButton[jsonReader.AInf("q")]; ImagePanel[] ImagePanels = new ImagePanel[jsonReader.AInf("i")];
    BorderPanel[] questions = new BorderPanel[jsonReader.AInf("q")], compliments = new BorderPanel[jsonReader.AInf("q")], insults = new BorderPanel[jsonReader.AInf("q")],
            Correct = new BorderPanel[jsonReader.AInf("c")]; JLabel placeholder = new JLabel(jsonReader.AInfS("p")), placeholderLarge = new JLabel(jsonReader.AInfS("pl"));
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, (int) (10 * a), (int) (10 * a))); JButton informations = new JButton(Informations[0]);
    JButton Auswertung = new JButton(OtherButtons[1]), Auswertung2 = new JButton(OtherButtons[1]), HardQuestions = new JButton(OtherButtons[2]), big = new JButton(OtherButtons[3]);
    public AnimalMaster(boolean resizable, Point location, Dimension size, String title) {int[] widths = {102, 118, 133, 200, 300, 360}; Dimension[] buttonSizes = new Dimension[widths.length];
        for (int i = 0; i < widths.length; i++) buttonSizes[i] = new Dimension((int) (widths[i] * a), (int) (30 * a)); panel.setOpaque(false);
        AMTime[0] = System.nanoTime(); windows[1] = new JFrame(title); startedVersion = Versions[1]; panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 50, 10));
        for (int i = 0; i < answers.length; i++) answers[i] = new JButton(AnswerButtons[i]); for (int i = 0; i < ImagePanels.length; i++) ImagePanels[i] = Textures[1][i].toImagePanel();
        ImagePanels[0].setLayout(new BorderLayout()); ImagePanels[0].setSize((int) (420 * a), (int) (171 * a)); ImagePanels[0].add(panel);
        for (int i = 0; i < questions.length; i++) {next[i] = new JButton(OtherButtons[0]); questions[i] = new BorderPanel(Questions[i], black, true);
            compliments[i] = new BorderPanel(rand(Compliments), black, true); insults[i] = new BorderPanel(rand(Insults), black, true);
            questions[i].setBackground(rand()); next[i].setBackground(rand()); compliments[i].setBackground(rand()); insults[i].setBackground(rand());}
        for (int i = 0; i < Correct.length; i++) Correct[i] = new BorderPanel(Results[i], black, true); Component[][] doStandardLabelStuff = {questions, compliments, insults, Correct};
        for (Component[] componentArray : doStandardLabelStuff) for (Component component : componentArray) {component.setForeground(Color.WHITE); component.setVisible(false);
            panel.add(component, "South");}Component[][] doStandardButtonstuff = {{informations, Auswertung, Auswertung2, HardQuestions}, answers, next, {big}};
        for (Component[] componentArray : doStandardButtonstuff) for (Component component : componentArray) {((JButton) component).setOpaque(true); ((JButton) component).setContentAreaFilled(true);
            component.setBackground(rand()); component.setForeground(black); component.setFocusable(false); component.setVisible(false); panel.add(component, "South");}
        questions[0].setVisible(true); answers[0].setVisible(true); answers[1].setVisible(true); answers[2].setVisible(true); for (ImagePanel component : ImagePanels) {
            windows[1].add(component, "South"); component.setLayout(new BorderLayout(10, 100)); component.setSize((int) (420 * a), (int) (190 * a));
                component.setVisible(false);} ImagePanels[0].setVisible(true); ImagePanels[0].add(panel, "South"); Component[][] setPrefferendSizeButtonSize = {answers, next,
                {informations, big}}; Component[] setPrefferendSizeButtonSize2 = {Auswertung, Auswertung2};for (Component[] componentArray : setPrefferendSizeButtonSize) {
                    for (Component component : componentArray) {component.setPreferredSize(buttonSizes[1]);}} for (Component component : setPrefferendSizeButtonSize2) component.setPreferredSize(buttonSizes[4]);
        HardQuestions.setPreferredSize(buttonSizes[3]); Component[][] setPrefSize5 = {questions, insults, compliments}; for (int i = 0; i < 13; i++) Answer(i); Next(new int[]{7, 12});
        for (Component[] components : setPrefSize5) for (Component component : components) {component.setPreferredSize(buttonSizes[5]);} for (BorderPanel component : Correct) {
            component.setPreferredSize(new Dimension((int) (380 * a), (int) (35 * a)));} next[7].addActionListener(e -> {informations.setVisible(false); Auswertung.setVisible(true);
            compliments[7].setVisible(false); insults[7].setVisible(false); next[7].setVisible(false); ImagePanels[20].setVisible(false); ImagePanels[2].setVisible(false); ImagePanels[21].setVisible(false);
            ImagePanels[0].setVisible(true); ImagePanels[0].add(panel, "South"); big.setVisible(false);}); next[12].addActionListener(e -> {informations.setVisible(false);
            compliments[12].setVisible(false); insults[12].setVisible(false); next[12].setVisible(false); Auswertung2.setVisible(true); ImagePanels[34].setVisible(false); ImagePanels[35].setVisible(false);
            ImagePanels[36].setVisible(false); ImagePanels[0].setVisible(true); ImagePanels[0].add(panel, "South"); big.setVisible(false);}); Auswertung.addActionListener(e -> {
            Correct[correct].setVisible(true); if (correct == 8) HardQuestions.setVisible(true); Auswertung.setVisible(false); fAMReset = true;}); Auswertung2.addActionListener(e -> {
            Correct[correct + 1].setVisible(true); Auswertung2.setVisible(false); AMTime[1] = System.nanoTime(); if (((AMTime[1] - AMTime[0]) / 1000000000) < AMTime[2]) {AMTime[2] = ((AMTime[1] - AMTime[0]) / 1000000000);
            } else if (AMTime[2] == -1.0F) {AMTime[2] = ((AMTime[1] - AMTime[0]) / 1000000000);} logger.info(AMTime[2]); configLoader.setAnimalMasterTimeRecord(AMTime[2]); fAM = true;});
            HardQuestions.addActionListener(e -> {placeholder.setVisible(false); placeholderLarge.setVisible(false); for (Component component : next) component.setVisible(false);
            for (Component component : Correct) component.setVisible(false); questions[8].setVisible(true); answers[24].setVisible(true); answers[25].setVisible(true); answers[26].setVisible(true);
            HardQuestions.setVisible(false);}); informations.addActionListener(e -> openWebpage(Informations[sel])); big.addActionListener(e -> new Big(sel));
        answers[34].setPreferredSize(buttonSizes[2]); answers[35].setPreferredSize(buttonSizes[0]); WindowOperations(1, resizable, size, location, Textures[1][37]);}
    private void Answer(int question) {int i1 = question*3, i2 = question*3+1, i3 = question*3+2; int[] se = jsonReader.AMSelectedAnimal(); boolean[] cor = jsonReader.AMCorrect();
        answers[i1].addActionListener(e -> ACL(question, se[i1], cor[i1])); answers[i2].addActionListener(e -> ACL(question, se[i2], cor[i2]));
        answers[i3].addActionListener(e -> ACL(question, se[i3], cor[i3]));} private void Next(int[] not) {for (int i = 0; i < 13; i++) if (Array.dontContains(not, i)) {
                int I = i; next[i].addActionListener(e -> NCL(I));}}private void ACL(int question, int i, boolean Correct) {ImagePanels[i].setVisible(true); ImagePanels[i].add(panel, "South");
        if (Correct) {Sounds[1][0].playSound(); correct++; compliments[question].setVisible(true);} else {Sounds[1][1].playSound(); insults[question].setVisible(true);} questions[question].setVisible(false);
        answers[question*3].setVisible(false); answers[question*3+1].setVisible(false); answers[question*3+2].setVisible(false); next[question].setVisible(true); sel = i; informations.setVisible(true);
        big.setVisible(true); ImagePanels[0].setVisible(false);} private void NCL(int question) {next[question].setVisible(false); compliments[question].setVisible(false); insults[question].setVisible(false);
        ImagePanels[jsonReader.AMSelectedAnimal()[question*3]].setVisible(false); ImagePanels[jsonReader.AMSelectedAnimal()[question*3+1]].setVisible(false);
        ImagePanels[jsonReader.AMSelectedAnimal()[question*3+2]].setVisible(false); informations.setVisible(false); big.setVisible(false); ImagePanels[0].setVisible(true); int i = question+1;
        ImagePanels[0].add(panel, "South"); questions[i].setVisible(true); answers[i*3].setVisible(true); answers[i*3+1].setVisible(true); answers[i*3+2].setVisible(true);}
    private Color rand() {return buttonColors[current().nextInt(buttonColors.length)];} private String rand(String[] a) {return a[current().nextInt(a.length)];}}