package ooga.networking;

public class Packet {

    public static class packet01Message { public String message; }

    public static class packetUserData {
        public int xPos;
        public int yPos;
        public int health;
        public boolean attacked;
    }

}
