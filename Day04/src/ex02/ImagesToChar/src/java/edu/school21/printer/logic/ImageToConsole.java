package src.java.edu.school21.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class ImageToConsole {

    private String white;
    private String black;
    private BufferedImage image;

    public ImageToConsole(Parser args, String filepath) throws IOException {
        this.white = args.getWhite();
        this.black = args.getBlack();
        image = ImageIO.read(Objects.requireNonNull(ImageToConsole.class.getResource(filepath)));
    }

    public void print() {
        ColoredPrinter printer = new ColoredPrinter();
        int width = image.getWidth();
        int height = image.getHeight();

        try {
            for (int i = 0; i < height; i += 2) {
                for (int j = 0; j < width; j++) {
                    int pixel = image.getRGB(j, i);
                    if (pixel == Color.WHITE.getRGB()) {
                        printer.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(white));
                    } else if (pixel == Color.BLACK.getRGB()) {
                        printer.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(black));
                    }
                }
                System.out.println();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument");
            System.out.println(e.getMessage());
        }
    }
}
