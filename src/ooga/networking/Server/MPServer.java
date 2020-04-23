package ooga.networking.Server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import ooga.Exceptions.ExceptionHelper;
import ooga.networking.Packet;

import java.io.IOException;

public class MPServer {

    // Server
    private Server server;

    // Connection details
    private String IPConnection = "localhost";
    private int tcpPort = 2300;
    private int udpPort = 2301;

    // Listener
    private ServerNetworkListener serverNetworkListener;

    public MPServer() {
        server = new Server();
        serverNetworkListener = new ServerNetworkListener();

        server.addListener(serverNetworkListener);

        try {
            server.bind(tcpPort);
        } catch (IOException e) {
            new ExceptionHelper(e);
        }

        registerPackets();

        server.start();
    }

    private void registerPackets() {
        Kryo kryo = server.getKryo();
        kryo.register(Packet.packet01Message.class);
        kryo.register(Packet.packetUserData.class);
    }


    public static void main(String[] args) {
        new MPServer();
    }
}
