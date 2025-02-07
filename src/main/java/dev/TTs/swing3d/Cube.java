package dev.TTs.swing3d;

import dev.TTs.lang.Buggy;
import dev.TTs.swing.TFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "FieldMayBeFinal", "UnusedAssignment", "CanBeFinal"})
@Buggy
public class Cube extends Simple3DCube {
    private List<Point3D> platform;

    public Cube(int x, int y, int z) {
        super(x, y, z);
        this.platform = new ArrayList<>();
    }

    public Cube(int x, int y, int z, Color[] colors) {
        super(x, y, z, colors);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderPlatform(g);
    }

    public static void main(String[] args) {
        TFrame frame = new TFrame();
        Cube cube = new Cube(0, 0, 0);
        frame.add(cube);
        cube.addPointForCube(250, 250, 0);
        cube.startSpinning();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.Show();
    }
}
