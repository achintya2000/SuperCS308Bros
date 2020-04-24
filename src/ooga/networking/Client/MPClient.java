package ooga.networking.Client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import ooga.Exceptions.ExceptionHelper;
import ooga.View.GameView;
import ooga.networking.Packets;

public class MPClient {

  private int tcpPort = 2300;
  private int udpPort = 2301;
  private String ipAddress = "localhost";

  private Client client;
  private ClientNetworkListener clientNetworkListener;

  private GameView gameView;

  public MPClient(GameView gv) {
    this.gameView = gv;
    client = new Client();
    clientNetworkListener = new ClientNetworkListener(client, gameView);

    registerPackets();

    client.addListener(clientNetworkListener);

    //client.start();
    new Thread(client).start();

    try {
      client.connect(5000, ipAddress, tcpPort, udpPort);
    } catch (Exception e) {
      new ExceptionHelper(e);
    }
  }

  private void registerPackets() {
    Kryo kryo = client.getKryo();
    kryo.register(Packets.packet01Message.class);
    kryo.register(Packets.packetUserData.class);
  }

//  public static void main(String[] args) {
//    new MPClient();
//  }

}