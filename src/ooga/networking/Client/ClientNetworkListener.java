package ooga.networking.Client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import ooga.networking.Packets;

public class ClientNetworkListener extends Listener {

    private Client client;

    public void init(Client c) {
        this.client = c;
    }

    @Override
    public void connected(Connection c) {
        System.out.println("[CLIENT] >> You have connect");

        sendClientData();
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

    private void sendClientData() {

        // Create something to sent
        Packets.packet01Message message = new Packets.packet01Message();
        message.message = "Hello server. YEETICUS";
        client.sendTCP(message);

        while (true) {
            // Create another thing to send
            Packets.packetUserData data = new Packets.packetUserData();
            data.leftPressed = true;

            client.sendTCP(data);
        }
    }

}
