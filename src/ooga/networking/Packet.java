package ooga.networking;

public class Packet {

    public static class packet01Message { String message; }

    public static class packetUserData {
        int xPos;
        int yPos;
        int health;
        boolean attacked;
    }

}
