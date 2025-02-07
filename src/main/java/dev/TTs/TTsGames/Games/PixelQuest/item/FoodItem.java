package dev.TTs.TTsGames.Games.PixelQuest.item;

import dev.TTs.util.Identifier;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;

import static dev.TTs.TTsGames.Main.logger;

public class FoodItem extends Item {
    private int saturation;

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
    private void writeObject(ObjectOutputStream ous) throws IOException {
        ous.writeObject(identifier());
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        Identifier identifier = (Identifier) in.readObject();
        Item item = getItem(identifier);
        if (item == null) {
            logger.error("Items not found during deserialization: %s", identifier);
            this.identifier = null;
            this.settings = null;
            this.saturation = 0;
        } else if (item instanceof FoodItem foodItem) {
            this.identifier = foodItem.identifier;
            this.settings = foodItem.settings;
            this.saturation = foodItem.saturation;
        }
    }
}
