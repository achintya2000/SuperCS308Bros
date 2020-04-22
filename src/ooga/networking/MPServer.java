package ooga.networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import ooga.Exceptions.ExceptionHelper;

import java.io.IOException;

public class MPServer {

    // Server
    private Server server;

    // Connection details
    private String IPConnection = "localhost";
    private int ServerPort = 2300;

    // Listener
    private ServerNetworkListener serverNetworkListener;

    public MPServer() {
        server = new Server();
        serverNetworkListener = new ServerNetworkListener();

        server.addListener(serverNetworkListener);

        try {
            server.bind(ServerPort);
        } catch (IOException e) {
            new ExceptionHelper(e);
        }

        registerPackets();

        server.start();
    }

    private void registerPackets() {
        Kryo kryo = server.getKryo();
        kryo.register(Packet.packet01Message.class);
    }


    public static void main(String[] args) {
        new MPServer();
    }
}
