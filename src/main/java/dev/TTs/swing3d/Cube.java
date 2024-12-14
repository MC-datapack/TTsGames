package dev.TTs.swing3d;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "FieldMayBeFinal"})
public class Cube extends Simple3DCube {
    private List<Point3D> platform;

    public Cube() {
        super();
        this.platform = new ArrayList<>();
    }

    public Cube(Color[] colors) {
        super(colors);
        this.platform = new ArrayList<>();
    }

    public void addPointForCube(int x, int y, int z) {
        platform.add(new Point3D(x, y, z));
    }

    private void renderPlatform(Graphics g) {
        for (Point3D pos : platform) {
            renderAtPosition(g, pos);
        }
    }

    private void renderAtPosition(Graphics g, Point3D pos) {
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderPlatform(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        angleX += 2;
        angleY += 2;
        angleZ += 2;
        repaint();
    }
}
