package dev.TTs.swing;

import java.awt.*;

import static dev.TTs.TTsGames.Main.Textures;
import static dev.TTs.resources.Translations.*;

public class Subtitles extends TFrame {
    TLabel text = new TLabel();

    public Subtitles(Point location) {
        text.setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        text.setSize(500, 60);

        this.setTitle(Settings[8]);
        this.setResizable(false);
        this.setSize(500, 100);
        this.setLocation(location);
        this.getContentPane().setBackground(Color.BLACK);
        this.setIconImage(Textures[0][2].toImage());
        this.setLayout(new BorderLayout());
        this.add(text);
    }

    public void setString(String s) {
        String wrappedText = wrapText(s, 120);
        text.setText("<html>" + wrappedText + "</html>");
        text.repaint();
        this.repaint();
    }

    private String wrapText(String text, int length) {
        StringBuilder wrappedText = new StringBuilder();
        int lineLength = 0;

        for (String word : text.split(" ")) {
            if (lineLength + word.length() > length) {
                wrappedText.append("<br>");
                lineLength = 0;
            }
            wrappedText.append(word).append(" ");
            lineLength += word.length() + 1;
        }

        return wrappedText.toString().trim();
    }
}
