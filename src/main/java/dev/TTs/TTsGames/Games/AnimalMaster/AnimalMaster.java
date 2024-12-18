package dev.TTs.TTsGames.Games.AnimalMaster; import static dev.TTs.TTsGames.Main.*; import static dev.TTs.resources.Translations.*; import static java.util.concurrent.ThreadLocalRandom.current;
import dev.TTs.TTsGames.Main;import dev.TTs.lang.Array;import dev.TTs.math.Time;import dev.TTs.resources.Configs; import dev.TTs.swing.*; import java.awt.*;
public class AnimalMaster {Color black = Color.BLACK;public static int sel, correct = 0; TButton[] an = new TButton[jsonReader.AInf("a")], ne = new TButton[jsonReader.AInf("q")]; TImage[] in =
new TImage[jsonReader.AInf("i")]; TBorderPanel[] qu = new TBorderPanel[jsonReader.AInf("q")], c = new TBorderPanel[jsonReader.AInf("q")], b = new TBorderPanel[jsonReader.AInf("q")], C = new TBorderPanel[jsonReader.AInf("c")];
TLabel P = new TLabel(jsonReader.AInfS("P")), pl = new TLabel(jsonReader.AInfS("pl")); TPanel p = new TPanel(new FlowLayout(FlowLayout.LEFT, (int) (10 * a), (int) (10 * a)));
TButton info = new TButton(Informations[0]); TButton A = new TButton(OtherButtons[1]), Auswertung2 = new TButton(OtherButtons[1]), HQ = new TButton(OtherButtons[2]), big = new TButton(OtherButtons[3]);
public AnimalMaster(WindowInformation inf) {int[] widths = {102, 118, 133, 200, 300, 360}; Dimension[] buttonSizes = new Dimension[widths.length]; for (int i = 0; i < widths.length; i++) {buttonSizes[i] =
new Dimension((int) (widths[i] * a), (int) (30 * a));} p.setOpaqueF(); AMTime[0] = Time.milliTime(); windows[1] = new TFrame(inf.title()); startedVersion = Versions[4];
p.setBorder(10, 10, 50, 10); for (int i = 0; i < an.length; i++) an[i] = new TButton(AnswerButtons[i]); for (int i = 0; i < in.length; i++) in[i] = Textures[1][i].toTImage();
in[0].setLayout(new BorderLayout()); in[0].setSize((420 * Main.a), (171 * Main.a));in[0].add(p);for (int i = 0; i < qu.length; i++) {ne[i] = new TButton(OtherButtons[0]);
qu[i] = new TBorderPanel(Questions[i], black, true); c[i] = new TBorderPanel(r(Compliments), black, true); b[i] = new TBorderPanel(r(Insults),
black, true); qu[i].setBackground(r()); ne[i].setBackground(r()); c[i].setBackground(r()); b[i].setBackground(r());} for (int i = 0; i < C.length; i++) {C[i] = new TBorderPanel(Results[i],
black, true);} TPanel[][] dSLS = {qu, c, b, C}; p.addSouth(dSLS); for (TPanel[] cArray : dSLS) for (TPanel c : cArray) {c.setForeground
(Color.WHITE); c.Hide();} TButton[][] dSBS = {{info, A, Auswertung2, HQ}, an, ne, {big}}; p.addSouth(dSBS); for (TButton[] cArray : dSBS) for (TButton c : cArray)
{c.setBackground(r());c.setForeground(black); c.setFocusableF(); c.Hide();} qu[0].Show(); an[0].Show(); an[1].Show(); an[2].Show(); for (TImage c : in)
{windows[1].addSouth(c);c.setBorderLayout(10, 100); c.setSize(420 * Main.a, 190 * Main.a); c.Hide();}in[0].Show(); in[0].addSouth(
p); TButton[][] sPSBS = {an, ne, {info, big}}; TButton[] sPBS2 = {A, Auswertung2}; for (TButton[] cArray : sPSBS) {for (TButton c : cArray)
{c.setPSize(buttonSizes[1]);}}for (TButton c : sPBS2) {c.setPSize(buttonSizes[4]);} HQ.setPSize(buttonSizes[3]); TBorderPanel[][] setPS5 = {qu, b, c};for (int i = 0; i < 13; i++)
AN(i);NX(7, 12); for (TBorderPanel[] cs : setPS5) {for (TBorderPanel c : cs) {c.setPSize(buttonSizes[5]);}} for (TBorderPanel c : C)
{c.setPSize((380 * Main.a), (35 * Main.a));} ne[7].event(() -> {info.Hide(); A.Show(); c[7].Hide();b[7].Hide(); ne[7].Hide();
in[20].Hide();in[2].Hide(); in[21].Hide(); in[0].Show(); in[0].addSouth(p); big.Hide();}); ne[12].event(() -> {info.Hide();c[12].Hide(); b[12].Hide(); ne[12].Hide();
Auswertung2.Show(); in[34].Hide(); in[35].Hide(); in[36].Hide(); in[0].Show(); in[0].addSouth(p); big.Hide();}); A.event(() -> { C[correct].Show(); if (correct == 8)
HQ.Show(); A.Hide(); fAMReset = true;}); Auswertung2.event(() -> { C[correct + 1].Show(); Auswertung2.Hide(); AMTime[1] = Time.milliTime(); if ((AMTime[1] - AMTime[0]) < AMTime[2]) {
AMTime[2] = AMTime[1] - AMTime[0]; } else if (AMTime[2] == -1.0F) { AMTime[2] = AMTime[1] - AMTime[0];} logger.info(AMTime[2]); configLoader.set(Configs.AM_RECORD, AMTime[2]); fAM = true;});
HQ.event(() -> {P.Hide(); pl.Hide(); for (TButton c : ne) c.Hide(); for (TPanel c : C)  c.Hide(); qu[8].Show(); an[24].Show(); an[25].Show(); an[26].Show();HQ.Hide();});
info.event(() -> openWebpage(Informations[sel])); big.event(() ->new Big(sel)); an[34].setPSize(buttonSizes[2]); an[35].setPSize(buttonSizes[0]);
WindowOperations(1, inf);} private void AN(int q) {int i1 = q *3, i2 = q *3+1, i3 = q *3+2; int[] se = jsonReader.AMSelectedAnimal(); boolean[] cor = jsonReader.AMCorrect();
an[i1].event(() -> ACL(q, se[i1], cor[i1])); an[i2].event(() -> ACL(q, se[i2], cor[i2])); an[i3].event(() -> ACL(q, se[i3], cor[i3]));}
private void NX(int... n) {for (int i = 0; i < 13; i++) if (Array.dontContains(n, i)) {int I = i; ne[i].event(() -> NCL(I));}} private void ACL(int q, int i, boolean c)
{in[i].Show(); in[i].addSouth(p); if (c) {Sounds[1][0].playSound(); correct++; this.c[q].Show();} else {Sounds[1][1].playSound(); b[q].Show();} qu[q].Hide(); an[q *3].Hide();
an[q *3+1].Hide(); an[q *3+2].Hide();ne[q].Show(); sel = i; info.Show(); big.Show(); in[0].Hide();} private void NCL(int q) {ne[q].Hide();c[q].Hide(); b[q].Hide(); in[jsonReader.AMSelectedAnimal()[q *3]].Hide();
in[jsonReader.AMSelectedAnimal()[q *3+1]].Hide();in[jsonReader.AMSelectedAnimal()[q *3+2]].Hide(); info.Hide();big.Hide();in[0].Show(); int i = q +1; in[0].addSouth(p); qu[i].Show(); an[i*3].Show();
an[i*3+1].Show();an[i*3+2].Show();} private Color r() {return buttonColors[current().nextInt(buttonColors.length)];} private String r(String[] a) {return a[current().nextInt(a.length)];}}