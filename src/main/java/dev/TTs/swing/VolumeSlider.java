package dev.TTs.swing;


import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

@SuppressWarnings("unused")
public class VolumeSlider extends JSlider {
    public VolumeSlider(int min, int max) {
        super(min, max);
        setUI(new VolumeSliderUI(this, new Color(200, 100, 250), new Color(50, 50, 200), new Color(220, 220, 220)));
    }

    public VolumeSlider(int min, int max, int value) {
        super(min, max, value);
        setUI(new VolumeSliderUI(this, new Color(200, 100, 250), new Color(50, 50, 200), new Color(220, 220, 220)));
    }

    public VolumeSlider(int min, int max, int value, Color trackColor, Color thumbColor) {
        super(min, max, value);
        setUI(new VolumeSliderUI(this, trackColor, thumbColor, new Color(trackColor.getRed(), trackColor.getGreen(), trackColor.getBlue(), 100)));
    }

    public static class VolumeSliderUI extends BasicSliderUI {
        private static final int TRACK_HEIGHT = 8, THUMB_SIZE = 16;
        private static Color trackColor, thumbColor, backgroundColor;

        public VolumeSliderUI(JSlider b, Color trackColor, Color thumbColor, Color backgroundColor) {
            super(b);
            VolumeSliderUI.trackColor = trackColor;
            VolumeSliderUI.thumbColor = thumbColor;
            VolumeSliderUI.backgroundColor = backgroundColor;
        }

        @Override
        public void paintTrack(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Rectangle trackBounds = trackRect;
            int thumbPos = thumbRect.x + (thumbRect.width / 2);

            // Paint the background track
            g2d.setColor(backgroundColor);
            g2d.fillRoundRect(trackBounds.x, trackBounds.y + (trackBounds.height / 2) - 4, trackBounds.width, TRACK_HEIGHT, 8, 8);

            // Paint the selected value track
            g2d.setColor(trackColor);
            g2d.fillRoundRect(trackBounds.x, trackBounds.y + (trackBounds.height / 2) - 4, thumbPos - trackBounds.x, TRACK_HEIGHT, 8, 8);

            // Paint the unselected value track with transparency
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); // 30% opacity
            g2d.setColor(trackColor); // Same color, but now transparent
            g2d.fillRoundRect(thumbPos, trackBounds.y + (trackBounds.height / 2) - 4, trackBounds.width - (thumbPos - trackBounds.x), TRACK_HEIGHT, 8, 8);

            // Reset composite to default for other components
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }

        @Override
        public void paintThumb(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Rectangle thumbBounds = thumbRect;
            g2d.setColor(thumbColor);
            g2d.fillOval(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);

            // Draw the value on the thumb
            int value = slider.getValue();
            String valueStr = String.valueOf(value);
            FontMetrics fm = g2d.getFontMetrics();
            int strWidth = fm.stringWidth(valueStr);
            int strHeight = fm.getAscent();
            g2d.setColor(Color.WHITE);
            g2d.drawString(valueStr, thumbBounds.x + (thumbBounds.width - strWidth) / 2, thumbBounds.y + (thumbBounds.height + strHeight) / 2 - 2);
        }

        @Override
        public Dimension getThumbSize() {
            return new Dimension(THUMB_SIZE, THUMB_SIZE);
        }
    }
}
