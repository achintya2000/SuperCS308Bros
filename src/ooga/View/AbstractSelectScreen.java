package ooga.View;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.TOP_CENTER;

public abstract class AbstractSelectScreen {
    public Properties prop;

    public AbstractSelectScreen() throws IOException {
        prop = new Properties();
        prop.load(new FileReader("data/stylesheets/buttonStyle.properties"));
        System.out.println(prop.keySet());
    }

    public VBox createToolbar() throws IOException {
        VBox header = new VBox();
        header.setAlignment(CENTER);
        HBox toolbar = new HBox();
        toolbar.setSpacing(10);
        toolbar.setAlignment(TOP_CENTER);
        header.getChildren().add(toolbar);

        HashMap<String, String> buttonMap = new HashMap<>();
        Properties props = new Properties();
        props.load(new FileReader("data/buttons/toolbar.properties"));
        for (String s : props.stringPropertyNames()) {
            buttonMap.put(s, props.getProperty(s));
        }
        Class<?> thisSelectScreen = AbstractSelectScreen.class;
        for (String key : buttonMap.keySet()) {
            Button b = new Button(key);
            for (Method m : thisSelectScreen.getDeclaredMethods()) {
                System.out.println(m.getName());
                if (buttonMap.get(key).equals(m.getName())) {
                    b.setOnAction(e -> {
                        try {
                            m.setAccessible(true);
                            m.invoke(this);
                        } catch (IllegalAccessException ex) {
                            ex.printStackTrace();
                        } catch (InvocationTargetException ex) {
                            ex.printStackTrace();
                        }
                    });
                }
            }
            toolbar.getChildren().add(b);
        }
        return header;
    }

    public void settings()
    {
        new SettingsPopUp(prop);
    }
}
