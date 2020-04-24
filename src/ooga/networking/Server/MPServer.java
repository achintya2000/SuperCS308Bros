package ooga.networking.Server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import ooga.Exceptions.ExceptionHelper;
import ooga.View.GameView;
import ooga.networking.Packets;

import java.io.IOException;

public class MPServer {

  // Server
  private Server server;

  private GameView gameView;

  // Connection details
  private String IPConnection = "localhost";
  private int tcpPort = 2300;
  private int udpPort = 2301;

  // Listener
  private ServerNetworkListener serverNetworkListener;

  public MPServer(GameView gv) {

    this.gameView = gv;

    server = new Server();
    serverNetworkListener = new ServerNetworkListener(gameView);

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
    kryo.register(Packets.packet01Message.class);
    kryo.register(Packets.packetUserData.class);
  }

  //public static void main(String[] args) {
  //  new MPServer();
  //}
}