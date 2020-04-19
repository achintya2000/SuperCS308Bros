package ooga.Controller;

import javafx.scene.input.KeyCode;
import ooga.Exceptions.ExceptionHelper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public String getPlayer1LeftKey() {
        return player1KeyBinds.get("left").toString();
    }

    public String getPlayer1LRightKey() {
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
}
