package ooga.networking;

public class Packets {

    public static class packet01Message { public String message; }

    public static class packetUserData {
        public boolean leftPressed;
        public boolean rightPressed;
        public boolean jumpPressed;
        public boolean fallPressed;
        public boolean attackPressed;
    }

    public static class packetEndGame { public boolean endGame; }

}
