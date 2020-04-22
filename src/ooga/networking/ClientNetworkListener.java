package ooga.networking;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientNetworkListener extends Listener {

    private Client client;

    public void init(Client c) {
        this.client = c;
    }

    @Override
    public void connected(Connection c) {
        System.out.println("[CLIENT] >> You have connect");

        // Create something to sent
        Packet.packet01Message message = new Packet.packet01Message();
        message.message = "Hello server. YEETICUS";

        client.sendTCP(message);
    }

    @Override
    public void disconnected(Connection c) {
        System.out.println("[CLIENT] >> You have disconnect");
    }

    @Override
    public void received(Connection c, Object o) {
        if (o instanceof Packet.packet01Message) {
            Packet.packet01Message packet01Message = (Packet.packet01Message) o;

            System.out.println("[SERVER} >> " + packet01Message.message);
        }
    }

}
