package ooga.Model.StageClasses;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javafx.css.CssParser.ParseError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.*;


public class StageBuilderDataTest {
  @Test
  void testBadData(){
    assertThrows(ExceptionInInitializerError.class, () -> new StageBuilder("doesnt exist"));
    assertThrows(NoClassDefFoundError.class, () -> new StageBuilder(""));
    assertThrows(NoClassDefFoundError.class, () -> new StageBuilder("tests/ooga/Model/StageClasses/bad1.json"));
    assertThrows(NoClassDefFoundError.class, () -> new StageBuilder("tests/ooga/Model/StageClasses/bad2.json"));
    assertThrows(NullPointerException.class, () -> new StageBuilder("tests/ooga/Model/StageClasses/bad3.json"));
    assertThrows(NoClassDefFoundError.class, () -> new StageBuilder("tests/ooga/Model/StageClasses/bad4.json"));
    assertThrows(NullPointerException.class, () -> new StageBuilder("tests/ooga/Model/StageClasses/bad5.json"));
    assertThrows(NoClassDefFoundError.class, () -> new StageBuilder("tests/ooga/Model/StageClasses/bad6.json"));
    assertThrows(NoClassDefFoundError.class, () -> new StageBuilder("tests/ooga/Model/StageClasses/bad7.json"));
    assertThrows(NoClassDefFoundError.class, () -> new StageBuilder("tests/ooga/Model/StageClasses/bad8.json"));
    assertThrows(NullPointerException.class, () -> new StageBuilder("tests/ooga/Model/StageClasses/bad9.json"));
    assertThrows(NoClassDefFoundError.class, () -> new StageBuilder("tests/ooga/Model/StageClasses/bad10.json"));
  }
}

