package dev.TTs.TTsGames.Games.PixelQuest.item;

import dev.TTs.util.Identifier;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;

import static dev.TTs.TTsGames.Main.logger;

public class WeaponItem extends Item {
    private int damage;

    public WeaponItem(Identifier identifier, int damage, Settings settings) {
        super(identifier, settings);
        this.damage = damage;
    }

    public int damage() {
        return damage;
    }

    @Override
    public String toString() {
        return "WeaponItem[" +
                "identifier=" + identifier() + ", " +
                "damage=" + damage + ", " +
                "settings=" + settings() + ']';
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(identifier());
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        Identifier identifier = (Identifier) in.readObject();
        Item item = getItem(identifier);
        if (item == null) {
            logger.error("Items not found during deserialization: %s", identifier);
            this.identifier = null;
            this.settings = null;
            this.damage = 0;
        } else if (item instanceof WeaponItem weaponItem) {
            this.identifier = weaponItem.identifier;
            this.settings = weaponItem.settings;
            this.damage = weaponItem.damage;
        }
    }
}
