package dev.TTs.TTsGames.Games.AnimalMaster; import static dev.TTs.TTsGames.Main.*; import static dev.TTs.TTsGames.Resources.Translations.*; import static java.util.concurrent.ThreadLocalRandom.current;
import dev.TTs.lang.Array;import dev.TTs.math.Time;import dev.TTs.swing.*; import java.awt.*; public final class AnimalMaster {Color black = Color.BLACK; public static int sel, correct = 0;
TButton[] answers = new TButton[jsonReader.AInf("a")], next = new TButton[jsonReader.AInf("q")]; TImage[] TImages = new TImage[jsonReader.AInf("i")];
TBorderPanel[] questions = new TBorderPanel[jsonReader.AInf("q")], compliments = new TBorderPanel[jsonReader.AInf("q")], insults = new TBorderPanel[jsonReader.AInf("q")],
Correct = new TBorderPanel[jsonReader.AInf("c")]; TLabel placeholder = new TLabel(jsonReader.AInfS("p")), placeholderLarge = new TLabel(jsonReader.AInfS("pl"));
TPanel panel = new TPanel(new FlowLayout(FlowLayout.LEFT, (int) (10 * a), (int) (10 * a))); TButton informations = new TButton(Informations[0]);
TButton Auswertung = new TButton(OtherButtons[1]), Auswertung2 = new TButton(OtherButtons[1]), HardQuestions = new TButton(OtherButtons[2]), big = new TButton(OtherButtons[3]);
public AnimalMaster(FrameInformationT inf) {int[] widths = {102, 118, 133, 200, 300, 360}; Dimension[] buttonSizes = new Dimension[widths.length]; for (int i = 0; i < widths.length; i++) {
buttonSizes[i] = new Dimension((int) (widths[i] * a), (int) (30 * a));} panel.setOpaqueF(); AMTime[0] = Time.milliTime(); windows[1] = new TFrame(inf.title());
startedVersion = Versions[4]; panel.setBorder(10, 10, 50, 10); for (int i = 0; i < answers.length; i++) answers[i] = new TButton(AnswerButtons[i]);
for (int i = 0; i < TImages.length; i++) TImages[i] = Textures[1][i].toTImage(); TImages[0].setLayout(new BorderLayout()); TImages[0].setSize((int) (420 * a), (int) (171 * a));
TImages[0].add(panel);for (int i = 0; i < questions.length; i++) {next[i] = new TButton(OtherButtons[0]); questions[i] = new TBorderPanel(Questions[i], black, true);
compliments[i] = new TBorderPanel(r(Compliments), black, true); insults[i] = new TBorderPanel(r(Insults), black, true);
questions[i].setBackground(r()); next[i].setBackground(r()); compliments[i].setBackground(r()); insults[i].setBackground(r());} for (int i = 0; i < Correct.length; i++) {Correct[i] =
new TBorderPanel(Results[i], black, true);} TPanel[][] dSLS = {questions, compliments, insults, Correct}; panel.add("South", dSLS);
for (TPanel[] componentArray : dSLS) for (TPanel component : componentArray) {component.setForeground(Color.WHITE); component.Hide();} TButton[][] dSBS = {{informations, Auswertung, Auswertung2,
HardQuestions}, answers, next, {big}}; panel.add("South", dSBS);for (TButton[] componentArray : dSBS) for (TButton component : componentArray) {
component.setBackground(r());component.setForeground(black); component.setFocusable(false); component.Hide();} questions[0].Show(); answers[0].Show(); answers[1].Show(); answers[2].Show();
for (TImage component : TImages) {windows[1].add(component, "South");component.setBorderLayout(10, 100); component.setSize((int) (420 * a), (int) (190 * a));
component.Hide();} TImages[0].Show(); TImages[0].add(panel, "South"); TButton[][] setPrefferendSizeButtonSize = {answers, next, {informations, big}};
TButton[] setPrefferendSizeButtonSize2 = {Auswertung, Auswertung2}; for (TButton[] componentArray : setPrefferendSizeButtonSize) {for (TButton component : componentArray) {
component.setPSize(buttonSizes[1]);}} for (TButton component : setPrefferendSizeButtonSize2) {component.setPSize(buttonSizes[4]);} HardQuestions.setPSize(buttonSizes[3]);
TBorderPanel[][] setPrefSize5 = {questions, insults, compliments}; for (int i = 0; i < 13; i++) AN(i); NX(new int[]{7, 12}); for (TBorderPanel[] components : setPrefSize5) {
for (TBorderPanel component : components) {component.setPSize(buttonSizes[5]);}} for (TBorderPanel component : Correct) { component.setPSize(new Dimension((int) (380 * a),
(int) (35 * a)));} next[7].clickAction(() -> {informations.Hide(); Auswertung.Show(); compliments[7].Hide(); insults[7].Hide(); next[7].Hide(); TImages[20].Hide();
TImages[2].Hide();TImages[21].Hide(); TImages[0].Show(); TImages[0].add(panel, "South"); big.Hide();}); next[12].clickAction(() -> {informations.Hide();
compliments[12].Hide();insults[12].Hide(); next[12].Hide(); Auswertung2.Show(); TImages[34].Hide(); TImages[35].Hide(); TImages[36].Hide(); TImages[0].Show();
TImages[0].add(panel, "South"); big.Hide();}); Auswertung.clickAction(() -> { Correct[correct].Show(); if (correct == 8) HardQuestions.Show();
Auswertung.Hide(); fAMReset = true;}); Auswertung2.clickAction(() -> { Correct[correct + 1].Show(); Auswertung2.Hide(); AMTime[1] = Time.milliTime(); if ((AMTime[1] - AMTime[0]) < AMTime[2]) {
AMTime[2] = AMTime[1] - AMTime[0]; } else if (AMTime[2] == -1.0F) { AMTime[2] = AMTime[1] - AMTime[0];} logger.info(AMTime[2]); configLoader.setAMTimeRecord(AMTime[2]); fAM = true;});
HardQuestions.clickAction(() -> {placeholder.Hide(); placeholderLarge.Hide(); for (TButton component : next) component.Hide(); for (TPanel component : Correct)  component.Hide();
questions[8].Show(); answers[24].Show(); answers[25].Show(); answers[26].Show();HardQuestions.Hide();});informations.clickAction(() -> openWebpage(Informations[sel]));
big.clickAction(() ->new Big(sel)); answers[34].setPSize(buttonSizes[2]); answers[35].setPSize(buttonSizes[0]); WindowOperations(1, inf.frameInformation(), Textures[1][37]);}
private void AN(int question) {int i1 = question*3, i2 = question*3+1, i3 = question*3+2; int[] se = jsonReader.AMSelectedAnimal(); boolean[] cor = jsonReader.AMCorrect();
answers[i1].clickAction(() -> ACL(question, se[i1], cor[i1])); answers[i2].clickAction(() ->ACL(question, se[i2], cor[i2]));
answers[i3].clickAction(() -> ACL(question, se[i3], cor[i3]));} private void NX(int[] not) {for (int i = 0; i < 13; i++) if (Array.dontContains(not, i)) {
int I = i; next[i].clickAction(() ->NCL(I));}}private void ACL(int question, int i, boolean Correct) {TImages[i].Show(); TImages[i].add(panel, "South");
if (Correct) {Sounds[1][0].playSound(); correct++; compliments[question].Show();} else {Sounds[1][1].playSound(); insults[question].Show();} questions[question].Hide(); answers[question*3].Hide();
answers[question*3+1].Hide(); answers[question*3+2].Hide(); next[question].Show(); sel = i; informations.Show(); big.Show(); TImages[0].Hide();} private void NCL(int question) {next[question].Hide();
compliments[question].Hide(); insults[question].Hide();TImages[jsonReader.AMSelectedAnimal()[question*3]].Hide(); TImages[jsonReader.AMSelectedAnimal()[question*3+1]].Hide();
TImages[jsonReader.AMSelectedAnimal()[question*3+2]].Hide(); informations.Hide(); big.Hide(); TImages[0].Show(); int i = question+1; TImages[0].add(panel, "South");
questions[i].Show(); answers[i*3].Show(); answers[i*3+1].Show(); answers[i*3+2].Show();} private Color r() {return buttonColors[current().nextInt(buttonColors.length)];}
private String r(String[] a) {return a[current().nextInt(a.length)];}}