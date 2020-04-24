package ooga.networking.Client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        A_PRESSED.bindBidirectional(gv.a_PRESSEDProperty());
        D_PRESSED.bindBidirectional(gv.d_PRESSEDProperty());
        W_PRESSED.bindBidirectional(gv.W_PRESSEDProperty());
        S_PRESSED.bindBidirectional(gv.s_PRESSEDProperty());
        T_PRESSED.bindBidirectional(gv.T_PRESSEDProperty());
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

    }

    private void sendDataToServer() {

        while (true) {
            // Create something to sent
            //Packets.packet01Message message = new Packets.packet01Message();
            //message.message = "Hello server. YEETICUS";
            //client.sendTCP(message);

            // Create another thing to send

            Packets.packetLeftPressed leftData = new Packets.packetLeftPressed();
            leftData.leftPressed = A_PRESSED.get();
            client.sendTCP(leftData);


            Packets.packetRightPressed rightData = new Packets.packetRightPressed();
            rightData.rightPressed = D_PRESSED.get();
            client.sendTCP(rightData);

            Packets.packetJumpPressed jumpData = new Packets.packetJumpPressed();
            jumpData.jumpPressed = W_PRESSED.get();
            client.sendTCP(jumpData);

            Packets.packetFallPressed fallData = new Packets.packetFallPressed();
            fallData.fallPressed = S_PRESSED.get();
            client.sendTCP(fallData);

            Packets.packetAttackPressed attackData = new Packets.packetAttackPressed();
            attackData.attackPressed = T_PRESSED.get();
            client.sendTCP(attackData);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
