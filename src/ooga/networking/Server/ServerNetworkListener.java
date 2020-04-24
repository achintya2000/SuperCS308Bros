package ooga.networking.Server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import ooga.View.GameView;
import ooga.networking.Packets;

public class ServerNetworkListener extends Listener {

    private GameView gameView;

    public ServerNetworkListener(GameView gv) {
        this.gameView = gv;
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

        if (o instanceof Packets.packetUserData) {
            Packets.packetUserData packetUserData = (Packets.packetUserData) o;

            System.out.println("[CLIENT} >> left pressed?: " + packetUserData.leftPressed);
            gameView.a_PRESSEDProperty().set(packetUserData.leftPressed);
            gameView.d_PRESSEDProperty().set(packetUserData.rightPressed);

        }
    }

}
