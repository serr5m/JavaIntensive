package edu.school21.app;

import edu.school21.preprocessor.PreProcessor;
import edu.school21.preprocessor.PreProcessorToUpperImpl;
import edu.school21.printer.Printer;
import edu.school21.printer.PrinterWithPrefixImpl;
import edu.school21.renderer.Renderer;
import edu.school21.renderer.RendererStandardImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererStandardImpl(preProcessor);
        PrinterWithPrefixImpl printerWithPrefix = new PrinterWithPrefixImpl(renderer);
        printerWithPrefix.setPrefix("Prefix");
        printerWithPrefix.print("Hello");
        System.out.println("--------------------------");


        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = applicationContext.getBean("printerWithPrefix", Printer.class);
        printer.print("HeLLo");

        printer = applicationContext.getBean("printerWithPrefixErr", Printer.class);
        printer.print("Holle");

        printer = applicationContext.getBean("PrinterWithDateImpl", Printer.class);
        printer.print("AboBa");
    }
}
