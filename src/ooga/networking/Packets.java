package ooga.networking;

import javafx.beans.property.ObjectProperty;

public class Packets {

    public static class packet01Message { public String message; }

    public static class packetLeftPressed {
        public boolean leftPressed;
    }

    public static class packetRightPressed {
        public boolean rightPressed;
    }

    public static class packetJumpPressed {
        public boolean jumpPressed;
    }

    public static class packetFallPressed {
        public boolean fallPressed;
    }

    public static class packetAttackPressed {
        public boolean attackPressed;
    }

    public static class packetClientCharacterSelected {
        public String characterName;
    }

    public static class packetServerStageSelected {
        public Object stageName;
    }

    public static class packetEndGame { public boolean endGame; }

}
