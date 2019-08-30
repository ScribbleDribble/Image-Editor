package sample.Filters;

class Colour {

    private int pixel;

    public Colour(int pixel) {
        this.pixel = pixel;
    }

    enum PalleteRGB {
        BLACK(0,0,0), RED(255,0,0), BLUE(0,0,255), GREEN(0,255,0),
        YELLOW(255,255,0), MAGNETA(255,0,255), CYAN(0, 255, 255),
        WHITE(255,255,255);

        private int r;
        private int g;
        private int b;

        PalleteRGB(int r, int g, int b) {

            this.r  = r;
            this.g = g;
            this.b = b;
        }

        public int getR() {
            return r;
        }

        public int getG() {
            return g;
        }

        public int getB() {
            return b;
        }

    }


    // ensures rgb values stay within valid range
    public static int colourBoundaryCheck(int value) {
        if (value < 0)
        {
            value = 0;
        }

        else if (value > 255) {
            value = 255;
        }

        return value;
    }

    public static double colourBoundaryCheckDouble(double value) {
        if (value < 0)
        {
            value = 0;
        }

        else if (value > 255) {
            value = 255;
        }

        return value;
    }

}
