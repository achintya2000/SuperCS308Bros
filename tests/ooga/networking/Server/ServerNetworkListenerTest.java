package ooga.networking.Server;

import ooga.View.GameView;
import ooga.networking.Client.MPClient;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ServerNetworkListenerTest {

    GameView gameView = new GameView(false, false);
    MPServer mpServer = new MPServer(gameView);
    ServerNetworkListener serverNetworkListener = new ServerNetworkListener(gameView);

    GameView gameView2 = new GameView(false, true);
    MPClient mpClient = new MPClient(gameView2, "localhost");

    @Test
    void connected() {
        assertEquals(mpServer.getServerConnection(), 1);
    }

    @Test
    void disconnected() {
        mpClient.forceDisconnect();
        assertEquals(mpServer.getServerConnection(), 0);
    }

}