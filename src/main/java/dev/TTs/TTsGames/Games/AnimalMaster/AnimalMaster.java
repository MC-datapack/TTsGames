package dev.TTs.TTsGames.Games.AnimalMaster; import static dev.TTs.TTsGames.Main.*; import static dev.TTs.resources.Translations.*; import static java.util.concurrent.ThreadLocalRandom.current;
import dev.TTs.TTsGames.Main;import dev.TTs.lang.Array;import dev.TTs.math.Time;import dev.TTs.swing.*; import java.awt.*; public final class AnimalMaster {Color black = Color.BLACK;public static int sel,
correct = 0; TButton[] a = new TButton[jsonReader.AInf("a")], n = new TButton[jsonReader.AInf("q")]; TImage[] i = new TImage[jsonReader.AInf("i")]; TBorderPanel[] q = new TBorderPanel
[jsonReader.AInf("q")], c = new TBorderPanel[jsonReader.AInf("q")], b = new TBorderPanel[jsonReader.AInf("q")], C = new TBorderPanel[jsonReader.AInf("c")]; TLabel P = new TLabel(
jsonReader.AInfS("P")), pl = new TLabel(jsonReader.AInfS("pl")); TPanel p = new TPanel(new FlowLayout(FlowLayout.LEFT, (int) (10 * Main.a), (int) (10 * Main.a)));
TButton inf = new TButton(Informations[0]); TButton A = new TButton(OtherButtons[1]), Auswertung2 = new TButton(OtherButtons[1]), HQ = new TButton(OtherButtons[2]), big = new TButton(OtherButtons[3]);
public AnimalMaster(FrameInformationT inf) {int[] widths = {102, 118, 133, 200, 300, 360}; Dimension[] buttonSizes = new Dimension[widths.length]; for (int i = 0; i < widths.length; i++) {buttonSizes[i] =
new Dimension((int) (widths[i] * Main.a), (int) (30 * Main.a));} p.SetOpaqueF(); AMTime[0] = Time.milliTime(); windows[1] = new TFrame(inf.title()); startedVersion = Versions[4];
p.setBorder(10, 10, 50, 10); for (int i = 0; i < a.length; i++) a[i] = new TButton(AnswerButtons[i]); for (int i = 0; i < this.i.length; i++) this.i[i] = Textures[1][i].toTImage();
i[0].setLayout(new BorderLayout()); i[0].setSize((int) (420 * Main.a), (int) (171 * Main.a));i[0].add(p);for (int i = 0; i < q.length; i++) {n[i] = new TButton(OtherButtons[0]);
q[i] = new TBorderPanel(Questions[i], black, true); c[i] = new TBorderPanel(r(Compliments), black, true); b[i] = new TBorderPanel(r(Insults),
black, true); q[i].setBackground(r()); n[i].setBackground(r()); c[i].setBackground(r()); b[i].setBackground(r());} for (int i = 0; i < C.length; i++) {C[i] = new TBorderPanel(Results[i],
black, true);} TPanel[][] dSLS = {q, c, b, C}; p.add("South", dSLS); for (TPanel[] componentArray : dSLS) for (TPanel component : componentArray) {component.setForeground
(Color.WHITE); component.Hide();} TButton[][] dSBS = {{this.inf, A, Auswertung2, HQ}, a, n, {big}}; p.add("South", dSBS); for (TButton[] componentArray : dSBS) for (TButton component : componentArray)
{component.setBackground(r());component.setForeground(black); component.setFocusable(false); component.Hide();} q[0].Show(); a[0].Show(); a[1].Show(); a[2].Show(); for (TImage component : i)
{windows[1].add(component, "South");component.setBorderLayout(10, 100); component.setSize((int) (420 * Main.a), (int) (190 * Main.a)); component.Hide();}i[0].Show(); i[0].add(
p, "South"); TButton[][] sPSBS = {a, n, {this.inf, big}}; TButton[] sPBS2 = {A, Auswertung2}; for (TButton[] componentArray : sPSBS) {for (TButton component : componentArray)
{component.setPSize(buttonSizes[1]);}}for (TButton component : sPBS2) {component.setPSize(buttonSizes[4]);} HQ.setPSize(buttonSizes[3]); TBorderPanel[][] setPrefSize5 = {q, b, c};for (int i = 0; i < 13; i++)
AN(i);NX(new int[]{7, 12}); for (TBorderPanel[] components : setPrefSize5) {for (TBorderPanel component : components) {component.setPSize(buttonSizes[5]);}} for (TBorderPanel component : C)
{component.setPSize(new Dimension((int) (380 * Main.a), (int) (35 * Main.a)));} n[7].clickAction(() -> {this.inf.Hide(); A.Show(); c[7].Hide();b[7].Hide(); n[7].Hide();
i[20].Hide();i[2].Hide(); i[21].Hide(); i[0].Show(); i[0].add(p, "South"); big.Hide();}); n[12].clickAction(() -> {this.inf.Hide();c[12].Hide(); b[12].Hide(); n[12].Hide();
Auswertung2.Show(); i[34].Hide(); i[35].Hide(); i[36].Hide(); i[0].Show(); i[0].add(p, "South"); big.Hide();}); A.clickAction(() -> { C[correct].Show(); if (correct == 8)
HQ.Show(); A.Hide(); fAMReset = true;}); Auswertung2.clickAction(() -> { C[correct + 1].Show(); Auswertung2.Hide(); AMTime[1] = Time.milliTime(); if ((AMTime[1] - AMTime[0]) < AMTime[2]) {
AMTime[2] = AMTime[1] - AMTime[0]; } else if (AMTime[2] == -1.0F) { AMTime[2] = AMTime[1] - AMTime[0];} logger.info(AMTime[2]); configLoader.setAMTimeRecord(AMTime[2]); fAM = true;});
HQ.clickAction(() -> {P.Hide(); pl.Hide(); for (TButton component : n) component.Hide(); for (TPanel component : C)  component.Hide(); q[8].Show(); a[24].Show(); a[25].Show(); a[26].Show();HQ.Hide();});
this.inf.clickAction(() -> openWebpage(Informations[sel])); big.clickAction(() ->new Big(sel)); a[34].setPSize(buttonSizes[2]); a[35].setPSize(buttonSizes[0]);
WindowOperations(1, inf.frameInformation());} private void AN(int question) {int i1 = question*3, i2 = question*3+1, i3 = question*3+2; int[] se = jsonReader.AMSelectedAnimal(); boolean[] cor =
jsonReader.AMCorrect(); a[i1].clickAction(() -> ACL(question, se[i1], cor[i1])); a[i2].clickAction(() ->ACL(question, se[i2], cor[i2])); a[i3].clickAction(() ->
ACL(question, se[i3], cor[i3]));} private void NX(int[] not) {for (int i = 0; i < 13; i++) if (Array.dontContains(not, i)) {int I = i; n[i].clickAction(() ->NCL(I));}}
private void ACL(int question, int i, boolean Correct) {this.i[i].Show(); this.i[i].add(p, "South"); if (Correct) {Sounds[1][0].playSound(); correct++; c[question].Show();}
else {Sounds[1][1].playSound(); b[question].Show();} q[question].Hide(); a[question*3].Hide(); a[question*3+1].Hide(); a[question*3+2].Hide();n[question].Show(); sel = i; inf.Show(); big.Show(); this.i[0].Hide();}
private void NCL(int question) {n[question].Hide(); c[question].Hide(); b[question].Hide();i[jsonReader.AMSelectedAnimal()[question*3]].Hide(); i[jsonReader.AMSelectedAnimal()[question*3+1]].Hide();
i[jsonReader.AMSelectedAnimal()[question*3+2]].Hide(); inf.Hide(); big.Hide(); i[0].Show(); int i = question+1; this.i[0].add(p, "South"); q[i].Show(); a[i*3].Show(); a[i*3+1].Show();
a[i*3+2].Show();} private Color r() {return buttonColors[current().nextInt(buttonColors.length)];} private String r(String[] a) {return a[current().nextInt(a.length)];}}