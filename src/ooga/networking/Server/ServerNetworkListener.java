package ooga.networking.Server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import ooga.Exceptions.ExceptionHelper;
import ooga.View.GameView;
import ooga.networking.Packets;

public class ServerNetworkListener extends Listener {

    private GameView gameView;

    private BooleanProperty LEFT_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty RIGHT_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty UP_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty DOWN_PRESSED = new SimpleBooleanProperty();
    private BooleanProperty L_PRESSED = new SimpleBooleanProperty();

    public ServerNetworkListener(GameView gv) {
        this.gameView = gv;
        LEFT_PRESSED.bindBidirectional(gv.LEFT_PRESSEDProperty());
        RIGHT_PRESSED.bindBidirectional(gv.RIGHT_PRESSEDProperty());
    }

    @Override
    public void connected(Connection c) {
        System.out.println("Someone has connected");

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

//        if (o instanceof Packets.packetUserData) {
//            Packets.packetUserData packetUserData = (Packets.packetUserData) o;
//
//            //System.out.println("[CLIENT} >> left pressed?: " + packetUserData.leftPressed);
//            gameView.a_PRESSEDProperty().set(packetUserData.leftPressed);
//            gameView.d_PRESSEDProperty().set(packetUserData.rightPressed);
//            gameView.W_PRESSEDProperty().set(packetUserData.jumpPressed);
//            gameView.s_PRESSEDProperty().set(packetUserData.fallPressed);
//            gameView.T_PRESSEDProperty().set(packetUserData.attackPressed);
//        }
    }


}
