package edu.school21.printer;

import edu.school21.renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {
    Renderer renderer;

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String text) {
        renderer.renderMessage(text);
    }

    public void setPrefix(String prefix) {
        renderer.renderMessage(prefix);
    }

}
