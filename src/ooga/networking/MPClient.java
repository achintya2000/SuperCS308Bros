package ooga.networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import ooga.Exceptions.ExceptionHelper;

public class MPClient {

    private int tcpPort = 2300;
    private int udpPort = 2301;
    private String ipAddress = "localhost";

    private Client client;
    private ClientNetworkListener clientNetworkListener;

    public MPClient() {
        client = new Client();
        clientNetworkListener = new ClientNetworkListener();
        clientNetworkListener.init(client);

        registerPackets();

        client.addListener(clientNetworkListener);

        //client.start();
        new Thread(client).start();

        try {
            client.connect(5000, ipAddress, tcpPort);
        } catch (Exception e) {
            new ExceptionHelper(e);
        }

    }

    private void registerPackets() {
        Kryo kryo = client.getKryo();
        kryo.register(Packet.packet01Message.class);
        kryo.register(Packet.packetUserData.class);
    }

    public static void main(String[] args) {
        new MPClient();
    }

}
