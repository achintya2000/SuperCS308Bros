package ooga.networking.Server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ooga.Exceptions.ExceptionHelper;
import ooga.View.GameView;
import ooga.networking.Packets;

public class ServerNetworkListener extends Listener {

    private GameView gameView;
    private Server server;

    private BooleanProperty LEFT_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty RIGHT_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty UP_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty DOWN_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty L_PRESSED = new SimpleBooleanProperty();

    public ServerNetworkListener(Server s, GameView gv) {
        this.server = s;
        this.gameView = gv;
        LEFT_PRESSED.bindBidirectional(gv.LEFT_PRESSEDProperty());
        RIGHT_PRESSED.bindBidirectional(gv.RIGHT_PRESSEDProperty());
        UP_PRESSED.bindBidirectional(gv.UP_PRESSEDProperty());
        DOWN_PRESSED.bindBidirectional(gv.DOWN_PRESSEDProperty());
        L_PRESSED.bindBidirectional(gv.L_PRESSEDProperty());
    }

    @Override
    public void connected(Connection c) {
        System.out.println("Someone has connected");

        LEFT_PRESSED.addListener((observable, oldValue, newValue) -> {
            System.out.println("YEETICUS");
            Packets.packetLeftPressed leftData = new Packets.packetLeftPressed();
            leftData.leftPressed = LEFT_PRESSED.get();
            c.sendTCP(leftData);
        });

        RIGHT_PRESSED.addListener((observable, oldValue, newValue) -> {
            Packets.packetRightPressed rightData = new Packets.packetRightPressed();
            rightData.rightPressed = RIGHT_PRESSED.get();
            c.sendTCP(rightData);
        });

//        Packets.packet01Message packet01Message = new Packets.packet01Message();
//        packet01Message.message = "YOTE";
//
//        c.sendTCP(packet01Message);
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
            gameView.a_PRESSEDProperty().set(packetLeftPressed.leftPressed);
        }

        if (o instanceof Packets.packetRightPressed) {
            Packets.packetRightPressed packetRightPressed = (Packets.packetRightPressed) o;
            gameView.d_PRESSEDProperty().set(packetRightPressed.rightPressed);
        }

        if (o instanceof Packets.packetJumpPressed) {
            Packets.packetJumpPressed packetJumpPressed = (Packets.packetJumpPressed) o;
            gameView.W_PRESSEDProperty().set(packetJumpPressed.jumpPressed);
        }

        if (o instanceof Packets.packetFallPressed) {
            Packets.packetFallPressed packetFallPressed = (Packets.packetFallPressed) o;
            gameView.s_PRESSEDProperty().set(packetFallPressed.fallPressed);
        }

        if (o instanceof Packets.packetAttackPressed) {
            Packets.packetAttackPressed packetAttackPressed = (Packets.packetAttackPressed) o;
            gameView.T_PRESSEDProperty().set(packetAttackPressed.attackPressed);
        }

    }


}
