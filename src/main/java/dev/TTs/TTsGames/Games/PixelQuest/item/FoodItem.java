package dev.TTs.TTsGames.Games.PixelQuest.item;

import dev.TTs.TTsGames.Games.PixelQuest.util.Identifier;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;

import static dev.TTs.TTsGames.Main.logger;

public class FoodItem extends Item {
    private final int saturation;

    public FoodItem(Identifier identifier, int saturation, Settings settings) {
        super(identifier, settings);
        this.saturation = saturation;
    }

    public int saturation() {
        return saturation;
    }

    @Override
    public String toString() {
        return "FoodItem[" +
                "identifier=" + identifier() + ", " +
                "saturation=" + saturation + ", " +
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
