package TestWiki;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    int step = 1;
    final String logFile = "src/test/log/logFile.txt";

    public Logger() {
        File file = new File(logFile);
        if (file.exists())
        file.delete();
    }

    public void setLog(WebDriver driver) {

        try(FileWriter writer = new FileWriter(logFile, true))
        {
            String logElement = "\n«Шаг "+(step++)+" - PageTitle - "+driver.getTitle()+" - "+driver.getCurrentUrl();
            writer.write(logElement);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
