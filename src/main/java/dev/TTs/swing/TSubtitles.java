package dev.TTs.swing;

import java.awt.*;

import static dev.TTs.TTsGames.Main.Textures;
import static dev.TTs.resources.Translations.*;

@SuppressWarnings({"SameParameterValue", "CanBeFinal"})
public class TSubtitles extends TFrame {
    TLabel text = new TLabel();

    public TSubtitles(Point location) {
        text.setBackground(Color.BLACK);
        text.setForeground(Color.WHITE);
        text.setSize(500, 100);

        this.setTitle(Settings[8]);
        this.setResizable(false);
        this.setSize(500, 140);
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

    private String wrapText(String text, int maxLength) {
        StringBuilder wrappedText = new StringBuilder();
        StringBuilder line = new StringBuilder();

        for (String word : text.split(" ")) {
            if (line.length() + word.length() + 1 > maxLength) {
                if (!wrappedText.isEmpty()) {
                    wrappedText.append("<br>");
                }
                wrappedText.append(line.toString().trim());
                line = new StringBuilder();
            }
            line.append(word).append(" ");
        }

        // Append the last line if there's any
        if (!line.isEmpty()) {
            if (!wrappedText.isEmpty()) {
                wrappedText.append("<br>");
            }
            wrappedText.append(line.toString().trim());
        }

        return wrappedText.toString();
    }

}
