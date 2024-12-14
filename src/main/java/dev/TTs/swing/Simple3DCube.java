package dev.TTs.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings({"unused", "UnusedAssignment"})
public class Simple3DCube extends TPanel implements ActionListener, Object3D {
    private static final int[][] VERTICES = {
            {1, 1, -1}, {-1, 1, -1}, {-1, -1, -1}, {1, -1, -1},
            {1, 1, 1},  {-1, 1, 1},  {-1, -1, 1},  {1, -1, 1}
    };

    private static final int[][] EDGES = {
            {0, 1}, {1, 2}, {2, 3}, {3, 0},
            {4, 5}, {5, 6}, {6, 7}, {7, 4},
            {0, 4}, {1, 5}, {2, 6}, {3, 7}
    };

    private static final int[][] FACES = {
            {0, 1, 2, 3}, // back
            {4, 5, 6, 7}, // front
            {0, 1, 5, 4}, // top
            {2, 3, 7, 6}, // bottom
            {0, 3, 7, 4}, // right
            {1, 2, 6, 5}  // left
    };

    private Timer timer;
    private double angleX = 0;
    private double angleY = 0;
    private double angleZ = 0;
    private final Color[] faceColors;

    private int width = 400;
    private int height = 400;

    public Simple3DCube() {
        timer = new Timer(30, this);
        faceColors = new Color[] {
                new Color(255, 0, 0, 128),
                new Color(0, 255, 0, 128),
                new Color(0, 0, 255, 128),
                new Color(255, 255, 0, 128),
                new Color(0, 255, 255, 128),
                new Color(255, 0, 255, 128)
        };
    }

    public Simple3DCube(Color[] colors) {
        timer = new Timer(30, this);
        timer.start();
        faceColors = colors;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = Math.min(width, height) / 4;

        int[] xPoints = new int[8];
        int[] yPoints = new int[8];

        for (int i = 0; i < VERTICES.length; i++) {
            double[] rotated = rotate(VERTICES[i], angleX, angleY, angleZ);
            xPoints[i] = (int) (width / 2.0 + rotated[0] * size);
            yPoints[i] = (int) (height / 2.0 - rotated[1] * size);
        }

        for (int i = 0; i < FACES.length; i++) {
            int[] face = FACES[i];
            Polygon poly = new Polygon();
            for (int vertex : face) {
                poly.addPoint(xPoints[vertex], yPoints[vertex]);
            }
            g2d.setColor(faceColors[i]);
            g2d.fillPolygon(poly);
        }

        g2d.setColor(Color.BLACK);
        for (int[] edge : EDGES) {
            g2d.drawLine(xPoints[edge[0]], yPoints[edge[0]], xPoints[edge[1]], yPoints[edge[1]]);
        }
    }


    private double[] rotate(int[] point, double angleX, double angleY, double angleZ) {
        double radX = Math.toRadians(angleX);
        double radY = Math.toRadians(angleY);
        double radZ = Math.toRadians(angleZ);

        double x = point[0];
        double y = point[1];
        double z = point[2];

        double cosX = Math.cos(radX);
        double sinX = Math.sin(radX);
        double cosY = Math.cos(radY);
        double sinY = Math.sin(radY);
        double cosZ = Math.cos(radZ);
        double sinZ = Math.sin(radZ);

        double newY = y * cosX - z * sinX;
        double newZ = y * sinX + z * cosX;
        y = newY;
        z = newZ;

        double newX = x * cosY + z * sinY;
        newZ = -x * sinY + z * cosY;
        x = newX;
        z = newZ;

        newX = x * cosZ - y * sinZ;
        newY = x * sinZ + y * cosZ;

        return new double[]{newX, newY, newZ};
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        angleX += 2;
        angleY += 2;
        angleZ += 2;
        repaint();
    }

    @Override
    public void setSize(Dimension size) {
        this.width = size.width;
        this.height = size.height;
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void startSpinning() {
        timer.start();
    }

    @Override
    public void stopSpinning() {
        timer.stop();
    }

    @Override
    public void setAngleX(double x) {
        this.angleX = x;
    }

    @Override
    public void setAngleY(double y) {
        this.angleY = y;
    }

    @Override
    public void setAngleZ(double z) {
        this.angleZ = z;
    }

    @Override
    public double getAngleX() {
        return angleX;
    }

    @Override
    public double getAngleY() {
        return angleY;
    }

    @Override
    public double getAngleZ() {
        return angleZ;
    }
}
