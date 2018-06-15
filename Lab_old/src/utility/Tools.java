package utility;

public class Tools implements Constants {
    public static String createIP(int labGate, int computerIpLast) {
        String IP = IP_GATE;
        IP += String.valueOf(labGate);
        IP += "." + computerIpLast;
        return IP;
    }
}
