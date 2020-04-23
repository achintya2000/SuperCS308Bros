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

    // Create another thing to send
    Packet.packetUserData data = new Packet.packetUserData();
    data.xPos = 100;
    data.yPos = 200;
    data.health = 500;
    data.attacked = false;

    client.sendTCP(message);
    client.sendTCP(data);
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