package dev.TTs.TTsGames.Games.PixelQuest.item;

import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;

import static dev.TTs.TTsGames.Main.logger;

public class WeaponItem extends Item {
    private final int damage;

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
            logger.error("Item not found during deserialization: %s", identifier);
            this.identifier = null;
            this.settings = null;
        } else {
            this.identifier = item.identifier;
            this.settings = item.settings;
        }
    }
}
