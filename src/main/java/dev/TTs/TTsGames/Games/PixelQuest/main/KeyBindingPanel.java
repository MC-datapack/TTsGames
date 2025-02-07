package dev.TTs.TTsGames.Games.PixelQuest.main;

import dev.TTs.swing.TButton;
import dev.TTs.swing.TPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static dev.TTs.resources.Translations.KeyBindingsTranslation;
import static dev.TTs.resources.Translations.PixelQuest;

public class KeyBindingPanel extends TPanel {

    public KeyBindingPanel() {
        setOpaqueF();
        setLayout(new GridLayout(0, 2));

        addKeyBindingButton(KeyBindingsTranslation[0], "MOVE_UP");
        addKeyBindingButton(KeyBindingsTranslation[1], "MOVE_DOWN");
        addKeyBindingButton(KeyBindingsTranslation[2], "MOVE_LEFT");
        addKeyBindingButton(KeyBindingsTranslation[3], "MOVE_RIGHT");
        addKeyBindingButton(KeyBindingsTranslation[4], "EAT");
        addKeyBindingButton(KeyBindingsTranslation[5], "SHOW_INVENTORY");
        addKeyBindingButton(KeyBindingsTranslation[6], "DROP_ITEM");
        addKeyBindingButton(KeyBindingsTranslation[7], "TRANSFER_ITEM");
        addKeyBindingButton(KeyBindingsTranslation[8], "SELECT");
        addKeyBindingButton(KeyBindingsTranslation[9], "SPRINT");
    }

    private void addKeyBindingButton(String label, String action) {
        TButton button = new TButton(label + ": " + KeyEvent.getKeyText(KeyBindings.getKeyBinding(action)));
        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, PixelQuest[3] + " " + label);
            setNewKeyBinding(button, label, action);
        });
        button.setPSize(300, 30);
        button.setFocusableF();
        add(button);
    }

    private void setNewKeyBinding(TButton button, String label, String action) {
        JDialog keyDialog = new JDialog((Frame) null, "Press a Key", true);
        keyDialog.setSize(300, 100);
        keyDialog.setUndecorated(true);

        keyDialog.setLocation(button.getLocationOnScreen().x - (keyDialog.getWidth() - button.getWidth()) / 2, button.getLocationOnScreen().y - keyDialog.getHeight());

        JLabel instruction = new JLabel(PixelQuest[4] + " " + label, SwingConstants.CENTER);
        keyDialog.add(instruction);

        keyDialog.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                KeyBindings.setKeyBinding(action, e.getKeyCode());
                button.setText(label + ": " + KeyEvent.getKeyText(e.getKeyCode()));
                keyDialog.dispose();
            }
        });

        keyDialog.setVisible(true);
    }
}
