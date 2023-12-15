package src.java.edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageToConsole {
    char white;
    char black;
    File file;

    public ImageToConsole(char symbol1, char symbol2, File file) {
        white = symbol1;
        black = symbol2;
        this.file = file;
    }

    public void imageToConsole() {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            int height = bufferedImage.getHeight();
            int width = bufferedImage.getWidth();
            int rgb;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    rgb = bufferedImage.getRGB(j, i);
                    if (rgb == Color.WHITE.getRGB()) {
                        System.out.print(white);
                    } else if (rgb == Color.BLACK.getRGB()) {
                        System.out.print(black);
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.print("Incorrect link to an image");
        }
    }
}
