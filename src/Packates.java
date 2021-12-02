public class Packates {
    private static int CONNECT_TYPE_AND_FLAG = 0x10;
    private static int CONNACK_TYPE_AND_FLAG = 0x20;
    private static int PUBLISH_TYPE_AND_FLAG = 0x30; // change able
    private static int SUBSCRIBE_TYPE_AND_FLAG = 0x82;
    private static int SUBACK_TYPE_AND_FLAG = 0x90;
    private static int UNSUBSCRIBE_TYPE_AND_FLAG = 0xA2;
    private static int UNSUBACK_TYPE_AND_FLAG = 0xB0;
    private static int PINGREQ_TYPE_AND_FLAG = 0xC0;
    private static int PINGRESP_TYPE_AND_FLAG = 0xD0;
    private static int DISCONNECT_TYPE_AND_FLAG = 0xE0;

    /**
     * <h1>CONNECT</h1>
     * return the connection packate with appropriate header informations
     * @param variable_header byte[] - two byte of variable header eg. connect flags and keep alive
     * @param payload byte[] 4 byte or field of payload eg. client id ,will topic, will message, username, pwd
     * @return connect_packate byte[] full construction version of connect_packate
     * */
    public static byte[] connect_packate(byte[] variable_header,byte[] payload) {
        byte[] connect_packate;
        
        return null;
    }

    /**
     * <h1>CONNACK</h1>
     * return the connection acknowledgement packate
     * @param variable_header byte[] - two byte of variable header eg. sp flag, return code
     * @return connack_packate byte[] return the conncetion acknowledgement packate
     * */
    public static byte[] connack_packate(byte[] variable_header) {
        return null;
    }

    /**
     * <h1>PUBLISH</h1>
     * return the publish packate
     * @param variable_header byte[] - one byte of variable header topic name
     * @return publish_packate byte[] - return the publish packate
     * */
    public static byte[] publish_packate(byte[] variable_header, byte[] payload) {
        return null;
    }

    public static byte[] subscribe_packate(byte[] variable_header, byte[] payload) {
        return null;
    }

    public static byte[] unsubscribe_packate(byte[] variable_header, byte[] paylod) {
        return null;
    }

    public static byte[] unsuback_packate(byte[] variable_heder,byte[] packate_id) {
        byte[] unsuback_packate = new byte[4];

        unsuback_packate[0] = (byte)UNSUBACK_TYPE_AND_FLAG;
        unsuback_packate[1] = 0x2;
        unsuback_packate[2] = packate_id[0];
        unsuback_packate[3] = packate_id[1];

        return unsuback_packate;
    }

    public static byte[] pingreq_packate() {
        byte[] pingreq_packate = new byte[2];

        pingreq_packate[0] = (byte)PINGREQ_TYPE_AND_FLAG;
        pingreq_packate[1] = 0x00;

        return pingreq_packate;
    }

    public static byte[] pingresp_packate () {
        byte[] pingresp_packate = new byte[2];

        pingresp_packate[0] = (byte)PINGRESP_TYPE_AND_FLAG;
        pingresp_packate[1] = 0x00;

        return pingresp_packate;
    }

    public static byte[] disconnect_packate() {
        byte[] disconnect_packate = new byte[2];

        disconnect_packate[0] = (byte)DISCONNECT_TYPE_AND_FLAG;
        disconnect_packate[1] = 0x00;

        return disconnect_packate;
    }
}
