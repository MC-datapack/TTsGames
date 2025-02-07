package dev.TTs.swing3d;

import dev.TTs.swing.TFrame;
import dev.TTs.swing.TPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings({"unused", "UnusedAssignment", "CanBeFinal"})
public class Simple3DCube extends TPanel implements ActionListener, Object3D {
    static final int[][] VERTICES = {
            {1, 1, -1}, {-1, 1, -1}, {-1, -1, -1}, {1, -1, -1},
            {1, 1, 1},  {-1, 1, 1},  {-1, -1, 1},  {1, -1, 1}
    };

    static final int[][] EDGES = {
            {0, 1}, {1, 2}, {2, 3}, {3, 0},
            {4, 5}, {5, 6}, {6, 7}, {7, 4},
            {0, 4}, {1, 5}, {2, 6}, {3, 7}
    };

    static final int[][] FACES = {
            {0, 1, 2, 3}, // back
            {4, 5, 6, 7}, // front
            {0, 1, 5, 4}, // top
            {2, 3, 7, 6}, // bottom
            {0, 3, 7, 4}, // right
            {1, 2, 6, 5}  // left
    };

    Timer timer;
    double angleX = 0;
    double angleY = 0;
    double angleZ = 0;
    int x, y, z;
    final Color[] faceColors;

    int width = 400;
    int height = 400;

    public Simple3DCube(int x, int y, int z) {
        timer = new Timer(30, this);
        faceColors = new Color[] {
                new Color(255, 0, 0, 128),
                new Color(0, 255, 0, 128),
                new Color(0, 0, 255, 128),
                new Color(255, 255, 0, 128),
                new Color(0, 255, 255, 128),
                new Color(255, 0, 255, 128)
        };
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Simple3DCube(int x, int y, int z, Color[] colors) {
        timer = new Timer(30, this);
        timer.start();
        faceColors = colors;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderAtPosition(g, new Point3D(x, y, z));
    }

    void renderAtPosition(Graphics g, Point3D pos) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(getWidth() / 2.0 + pos.x(), getHeight() / 2.0 + pos.y());

        int size = Math.min(getWidth(), getHeight()) / 4;
        int[][] transformedVertices = new int[VERTICES.length][2];

        for (int i = 0; i < VERTICES.length; i++) {
            double[] rotated = rotate(VERTICES[i][0], VERTICES[i][1], VERTICES[i][2], angleX, angleY, angleZ);
            transformedVertices[i][0] = (int) (rotated[0] * size);
            transformedVertices[i][1] = (int) (rotated[1] * size);
        }

        // Draw faces
        for (int i = 0; i < FACES.length; i++) {
            int[] face = FACES[i];
            Polygon poly = new Polygon();
            for (int vertex : face) {
                poly.addPoint(transformedVertices[vertex][0], transformedVertices[vertex][1]);
            }
            g2d.setColor(faceColors[i]);
            g2d.fillPolygon(poly);
        }

        g2d.setColor(Color.BLACK);
        for (int[] edge : EDGES) {
            g2d.drawLine(transformedVertices[edge[0]][0], transformedVertices[edge[0]][1], transformedVertices[edge[1]][0], transformedVertices[edge[1]][1]);
        }

        g2d.dispose();
    }

    private double[] rotate(double x, double y, double z, double angleX, double angleY, double angleZ) {
        double radX = Math.toRadians(angleX);
        double radY = Math.toRadians(angleY);
        double radZ = Math.toRadians(angleZ);

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


    protected double[] rotate(int[] point, double angleX, double angleY, double angleZ) {
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
