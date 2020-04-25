package ooga.networking.Server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import ooga.View.GameView;
import ooga.networking.Packets;

public class ServerNetworkListener extends Listener {

    private GameView gameView;

    private BooleanProperty SERVER_LEFT_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty SERVER_RIGHT_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty SERVER_FALL_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty SERVER_JUMP_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty SERVER_ATTACK_PRESSED = new SimpleBooleanProperty();

    public ServerNetworkListener(GameView gv) {
        this.gameView = gv;
        SERVER_LEFT_PRESSED.bindBidirectional(gv.getPlayer2LeftProp());
        SERVER_RIGHT_PRESSED.bindBidirectional(gv.getPlayer2RightProp());
        SERVER_FALL_PRESSED.bindBidirectional(gv.getPlayer2FallProp());
        SERVER_JUMP_PRESSED.bindBidirectional(gv.getPlayer2JumpProp());
        SERVER_ATTACK_PRESSED.bindBidirectional(gv.getPlayer2AttackProp());
    }

    @Override
    public void connected(Connection c) {
        System.out.println("Someone has connected");
        sendDataToClient(c);
    }

    @Override
    public void disconnected(Connection c) {
        System.out.println("Someone has disconnected");
    }

    @Override
    public void received(Connection c, Object o) {
        if (o instanceof Packets.packet01Message) {
            Packets.packet01Message packet01Message = (Packets.packet01Message) o;
            System.out.println("[CLIENT} >> " + packet01Message.message);
        }

        if (o instanceof Packets.packetLeftPressed) {
            Packets.packetLeftPressed packetLeftPressed = (Packets.packetLeftPressed) o;
            gameView.getPlayer1LeftProp().set(packetLeftPressed.leftPressed);
        }

        if (o instanceof Packets.packetRightPressed) {
            Packets.packetRightPressed packetRightPressed = (Packets.packetRightPressed) o;
            gameView.getPlayer1RightProp().set(packetRightPressed.rightPressed);
        }

        if (o instanceof Packets.packetJumpPressed) {
            Packets.packetJumpPressed packetJumpPressed = (Packets.packetJumpPressed) o;
            gameView.getPlayer1FallProp().set(packetJumpPressed.jumpPressed);
        }

        if (o instanceof Packets.packetFallPressed) {
            Packets.packetFallPressed packetFallPressed = (Packets.packetFallPressed) o;
            gameView.getPlayer1JumpProp().set(packetFallPressed.fallPressed);
        }

        if (o instanceof Packets.packetAttackPressed) {
            Packets.packetAttackPressed packetAttackPressed = (Packets.packetAttackPressed) o;
            gameView.getPlayer1AttackProp().set(packetAttackPressed.attackPressed);
        }

    }

    private void sendDataToClient(Connection c) {
        SERVER_LEFT_PRESSED.addListener((observable, oldValue, newValue) -> {
            System.out.println("YEETICUS");
            Packets.packetLeftPressed leftData = new Packets.packetLeftPressed();
            leftData.leftPressed = SERVER_LEFT_PRESSED.get();
            c.sendTCP(leftData);
        });

        SERVER_RIGHT_PRESSED.addListener((observable, oldValue, newValue) -> {
            Packets.packetRightPressed rightData = new Packets.packetRightPressed();
            rightData.rightPressed = SERVER_RIGHT_PRESSED.get();
            c.sendTCP(rightData);
        });

        SERVER_FALL_PRESSED.addListener((observable, oldValue, newValue) -> {
            Packets.packetFallPressed fallData = new Packets.packetFallPressed();
            fallData.fallPressed = SERVER_FALL_PRESSED.get();
            c.sendTCP(fallData);
        });

        SERVER_JUMP_PRESSED.addListener((observable, oldValue, newValue) -> {
            Packets.packetJumpPressed jumpData = new Packets.packetJumpPressed();
            jumpData.jumpPressed = SERVER_JUMP_PRESSED.get();
            c.sendTCP(jumpData);
        });

        SERVER_ATTACK_PRESSED.addListener((observable, oldValue, newValue) -> {
            Packets.packetAttackPressed attackData = new Packets.packetAttackPressed();
            attackData.attackPressed = SERVER_ATTACK_PRESSED.get();
            c.sendTCP(attackData);
        });
    }

}
