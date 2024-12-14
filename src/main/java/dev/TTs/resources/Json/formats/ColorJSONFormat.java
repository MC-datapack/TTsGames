package dev.TTs.resources.Json.formats;

@SuppressWarnings("unused")
public class ColorJSONFormat {
    public static class ColorRepresentation {
        private int r;
        private int g;
        private int b;
        private int rgb;

        public int getR() {return r;}
        public int getG() {return g;}
        public int getB() {return b;}
        public int getRGB() {return rgb;}
    }
}
