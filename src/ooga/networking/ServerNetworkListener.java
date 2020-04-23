package ooga.networking;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ServerNetworkListener extends Listener {

    public ServerNetworkListener() {

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

        if (o instanceof Packet.packet01Message) {
            Packet.packet01Message packet01Message = (Packet.packet01Message) o;

            System.out.println("[CLIENT} >> " + packet01Message.message);

        }

        if (o instanceof Packet.packetUserData) {
            Packet.packetUserData packetUserData = (Packet.packetUserData) o;

            System.out.println("[CLIENT} >> xPos: " + packetUserData.xPos + " yPos: " + packetUserData.yPos + " health: " + packetUserData.health);
        }
    }

}
