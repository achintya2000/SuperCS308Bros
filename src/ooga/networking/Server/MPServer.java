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

    server = new Server(1024*1024, 1024*1024);
    serverNetworkListener = new ServerNetworkListener(gameView);

    server.addListener(serverNetworkListener);

    try {
      server.bind(tcpPort, udpPort);
    } catch (IOException e) {
      new ExceptionHelper(e);
    }

    registerPackets();

    new Thread(server).start();
  }

  private void registerPackets() {
    Kryo kryo = server.getKryo();
    kryo.register(Packets.packet01Message.class);
    //kryo.register(Packets.packetUserData.class);
    kryo.register(Packets.packetLeftPressed.class);
    kryo.register(Packets.packetRightPressed.class);
    kryo.register(Packets.packetJumpPressed.class);
    kryo.register(Packets.packetFallPressed.class);
    kryo.register(Packets.packetAttackPressed.class);
  }

  public int getServerConnection() {
    return server.getConnections().length;
  }

  //public static void main(String[] args) {
  //  new MPServer();
  //}
}