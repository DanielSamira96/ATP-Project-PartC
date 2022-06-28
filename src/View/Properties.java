package View;

import Server.Configurations;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

import java.io.FileInputStream;
import java.io.IOException;

public class Properties {
    public Label threadPoolSize;
    public Label mazeGeneratingAlgorithm;
    public Label mazeSearchingAlgorithm;

    StringProperty updatethreadPoolSize = new SimpleStringProperty();
    StringProperty updatemazeGeneratingAlgorithm = new SimpleStringProperty();
    StringProperty updatemazeSearchingAlgorithm = new SimpleStringProperty();


    public void initialize() {
        try {
            FileInputStream input = new FileInputStream("resources/config.properties");

            try {
                java.util.Properties prop = new java.util.Properties();
                prop.load(input);

                updatethreadPoolSize.set(prop.getProperty("threadPoolSize"));
                threadPoolSize.textProperty().bind(updatethreadPoolSize);
                updatemazeGeneratingAlgorithm.set(prop.getProperty("mazeGeneratingAlgorithm"));
                mazeGeneratingAlgorithm.textProperty().bind(updatemazeGeneratingAlgorithm);
                updatemazeSearchingAlgorithm.set(prop.getProperty("mazeSearchingAlgorithm"));
                mazeSearchingAlgorithm.textProperty().bind(updatemazeSearchingAlgorithm);

            } catch (Throwable var5) {
                try {
                    input.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            input.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }
}
