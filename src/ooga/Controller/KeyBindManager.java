package ooga.Controller;

import javafx.scene.input.KeyCode;
import ooga.Exceptions.ExceptionHelper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class KeyBindManager {

    JSONParser jsonParser = new JSONParser();
    JSONObject player1KeyBinds;
    JSONObject player2KeyBinds;

    public KeyBindManager() {
        try {
            player1KeyBinds = (JSONObject) jsonParser.parse(new FileReader("data/keybindings/player1.json"));
            player2KeyBinds = (JSONObject) jsonParser.parse(new FileReader("data/keybindings/player2.json"));
        } catch (ParseException | IOException e) {
            new ExceptionHelper(e);
        }
    }

    public void setPlayer1KeyBinds(String left, String right, String jump, String fall, String attack, String special) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("left", left.toUpperCase());
        jsonObject.put("right", right.toUpperCase());
        jsonObject.put("jump", jump.toUpperCase());
        jsonObject.put("fall", fall.toUpperCase());
        jsonObject.put("attack", attack.toUpperCase());
        jsonObject.put("special", special.toUpperCase());

        try (FileWriter fileWriter = new FileWriter("data/keybindings/player1.json")) {
            fileWriter.write(jsonObject.toString());
            //resetPlayer1KeyBinds();
        } catch (IOException e) {
            new ExceptionHelper(e);
        }
    }

    public void setPlayer2KeyBinds(String left, String right, String jump, String fall, String attack, String special) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("left", left);
        jsonObject.put("right", right);
        jsonObject.put("jump", jump);
        jsonObject.put("fall", fall);
        jsonObject.put("attack", attack);
        jsonObject.put("special", special.toUpperCase());

        try (FileWriter fileWriter = new FileWriter("data/keybindings/player2.json")) {
            fileWriter.write(jsonObject.toString());
            //resetPlayer2KeyBinds();
        } catch (IOException e) {
            new ExceptionHelper(e);
        }
    }

    public String getPlayer1LeftKey() {
        return player1KeyBinds.get("left").toString();
    }

    public String getPlayer1RightKey() {
        return player1KeyBinds.get("right").toString();
    }

    public String getPlayer1JumpKey() {
        return player1KeyBinds.get("jump").toString();
    }

    public String getPlayer1FallKey() {
        return player1KeyBinds.get("fall").toString();
    }

    public String getPlayer1AttackKey() {
        return player1KeyBinds.get("attack").toString();
    }

    public String getPlayer1SpecialKey() {
        return player1KeyBinds.get("special").toString();
    }

    public String getPlayer2LeftKey() {
        return player2KeyBinds.get("left").toString();
    }

    public String getPlayer2RightKey() {
        return player2KeyBinds.get("right").toString();
    }

    public String getPlayer2JumpKey() {
        return player2KeyBinds.get("jump").toString();
    }

    public String getPlayer2FallKey() {
        return player2KeyBinds.get("fall").toString();
    }

    public String getPlayer2AttackKey() { return player2KeyBinds.get("attack").toString(); }

    public String getPlayer2SpecialKey() {
        return player1KeyBinds.get("special").toString();
    }

//    private void resetPlayer1KeyBinds() {
//        try {
//            player1KeyBinds = (JSONObject) jsonParser.parse(new FileReader("data/keybindings/player1.json"));
//        } catch (ParseException | IOException e) {
//            new ExceptionHelper(e);
//        }
//    }
//
//    private void resetPlayer2KeyBinds() {
//        try {
//            player2KeyBinds = (JSONObject) jsonParser.parse(new FileReader("data/keybindings/player2.json"));
//        } catch (ParseException | IOException e) {
//            new ExceptionHelper(e);
//        }
//    }
}
