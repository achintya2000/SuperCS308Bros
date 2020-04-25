package ooga.networking.Client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import ooga.View.GameView;
import ooga.networking.Packets;

public class ClientNetworkListener extends Listener {

    private Client client;
    private GameView gameView;

    private BooleanProperty CLIENT_LEFT_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty CLIENT_RIGHT_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty CLIENT_FALL_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty CLIENT_JUMP_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty CLIENT_ATTACK_PRESSED = new SimpleBooleanProperty();

    public ClientNetworkListener(Client c, GameView gv) {
        this.client = c;
        this.gameView = gv;
        CLIENT_LEFT_PRESSED.bindBidirectional(gv.getPlayer2LeftProp());
        CLIENT_RIGHT_PRESSED.bindBidirectional(gv.getPlayer2RightProp());
        CLIENT_FALL_PRESSED.bindBidirectional(gv.getPlayer2FallProp());
        CLIENT_JUMP_PRESSED.bindBidirectional(gv.getPlayer2JumpProp());
        CLIENT_ATTACK_PRESSED.bindBidirectional(gv.getPlayer2AttackProp());
    }

    @Override
    public void connected(Connection c) {
        System.out.println("[CLIENT] >> You have connect");
        sendDataToServer();
    }

    @Override
    public void disconnected(Connection c) {
        System.out.println("[CLIENT] >> You have disconnect");
    }

    @Override
    public void received(Connection c, Object o) {

        if (o instanceof Packets.packet01Message) {
            Packets.packet01Message packet01Message = (Packets.packet01Message) o;
            System.out.println("[SERVER} >> " + packet01Message.message);
        }

        if (o instanceof Packets.packetLeftPressed) {
            Packets.packetLeftPressed packetLeftPressed = (Packets.packetLeftPressed) o;
            gameView.getPlayer2LeftProp().set(packetLeftPressed.leftPressed);
        }

        if (o instanceof Packets.packetRightPressed) {
            Packets.packetRightPressed packetRightPressed = (Packets.packetRightPressed) o;
            gameView.getPlayer2RightProp().set(packetRightPressed.rightPressed);
        }

        if (o instanceof Packets.packetJumpPressed) {
            Packets.packetJumpPressed packetJumpPressed = (Packets.packetJumpPressed) o;
            gameView.getPlayer2JumpProp().set(packetJumpPressed.jumpPressed);
        }

        if (o instanceof Packets.packetFallPressed) {
            Packets.packetFallPressed packetFallPressed = (Packets.packetFallPressed) o;
            gameView.getPlayer2FallProp().set(packetFallPressed.fallPressed);
        }

        if (o instanceof Packets.packetAttackPressed) {
            Packets.packetAttackPressed packetAttackPressed = (Packets.packetAttackPressed) o;
            gameView.getPlayer2AttackProp().set(packetAttackPressed.attackPressed);
        }

    }

    private void sendDataToServer() {
        CLIENT_LEFT_PRESSED.addListener((observable, oldValue, newValue) -> {
            Packets.packetLeftPressed leftData = new Packets.packetLeftPressed();
            leftData.leftPressed = CLIENT_LEFT_PRESSED.get();
            client.sendTCP(leftData);
        });

        CLIENT_RIGHT_PRESSED.addListener((observable, oldValue, newValue) -> {
            Packets.packetRightPressed rightData = new Packets.packetRightPressed();
            rightData.rightPressed = CLIENT_RIGHT_PRESSED.get();
            client.sendTCP(rightData);
        });

        CLIENT_FALL_PRESSED.addListener((observable, oldValue, newValue) -> {
            Packets.packetFallPressed fallData = new Packets.packetFallPressed();
            fallData.fallPressed = CLIENT_JUMP_PRESSED.get();
            client.sendTCP(fallData);
        });

        CLIENT_JUMP_PRESSED.addListener((observable, oldValue, newValue) -> {
            Packets.packetJumpPressed jumpData = new Packets.packetJumpPressed();
            jumpData.jumpPressed = CLIENT_FALL_PRESSED.get();
            client.sendTCP(jumpData);
        });

        CLIENT_ATTACK_PRESSED.addListener((observable, oldValue, newValue) -> {
            Packets.packetAttackPressed attackData = new Packets.packetAttackPressed();
            attackData.attackPressed = CLIENT_ATTACK_PRESSED.get();
            client.sendTCP(attackData);
        });
    }

}
