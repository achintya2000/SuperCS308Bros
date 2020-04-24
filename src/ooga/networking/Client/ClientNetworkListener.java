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

    private BooleanProperty A_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty D_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty W_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty S_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty T_PRESSED = new SimpleBooleanProperty();

    public ClientNetworkListener(Client c, GameView gv) {
        this.client = c;
        this.gameView = gv;
        A_PRESSED.bindBidirectional(gv.getPlayer1LeftProp());
        D_PRESSED.bindBidirectional(gv.getPlayer1RightProp());
        W_PRESSED.bindBidirectional(gv.getPlayer1FallProp());
        S_PRESSED.bindBidirectional(gv.getPlayer1JumpProp());
        T_PRESSED.bindBidirectional(gv.getPlayer1AttackProp());
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

            // Create another thing to send

            A_PRESSED.addListener((observable, oldValue, newValue) -> {
                Packets.packetLeftPressed leftData = new Packets.packetLeftPressed();
                leftData.leftPressed = A_PRESSED.get();
                client.sendTCP(leftData);
            });

            D_PRESSED.addListener((observable, oldValue, newValue) -> {
                Packets.packetRightPressed rightData = new Packets.packetRightPressed();
                rightData.rightPressed = D_PRESSED.get();
                client.sendTCP(rightData);
            });

            W_PRESSED.addListener((observable, oldValue, newValue) -> {
                Packets.packetJumpPressed jumpData = new Packets.packetJumpPressed();
                jumpData.jumpPressed = W_PRESSED.get();
                client.sendTCP(jumpData);
            });

            S_PRESSED.addListener((observable, oldValue, newValue) -> {
                Packets.packetFallPressed fallData = new Packets.packetFallPressed();
                fallData.fallPressed = S_PRESSED.get();
                client.sendTCP(fallData);
            });

            T_PRESSED.addListener((observable, oldValue, newValue) -> {
                Packets.packetAttackPressed attackData = new Packets.packetAttackPressed();
                attackData.attackPressed = T_PRESSED.get();
                client.sendTCP(attackData);
            });

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        //}
    }

}
