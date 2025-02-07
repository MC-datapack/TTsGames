package dev.TTs.TTsGames.Games.PixelQuest.item;

import dev.TTs.util.Identifier;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;

import static dev.TTs.TTsGames.Main.logger;

public class PowerUpItem extends Item {
    private final int amount;

    public PowerUpItem(Identifier identifier, int amount, Settings settings) {
        super(identifier, settings);
        this.amount = amount;
    }

    public int amount() {
        return amount;
    }

    @Override
    public String toString() {
        return "PowerUpItem[" +
                "identifier=" + identifier() + ", " +
                "amount=" + amount + ", " +
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
        } else {
            this.identifier = item.identifier;
            this.settings = item.settings;
        }
    }
}
