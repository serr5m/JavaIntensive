package edu.school21.printer;

import edu.school21.renderer.Renderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrinterWithDateTimeImpl implements Printer{

    private Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String text) {
        renderer.renderMessage(text + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

}
