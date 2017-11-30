package com.ssense.ssense.DataController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by PC on 11/26/2017.
 */

public class InternetController {

    public boolean checkInternet() {
        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress("www.google.com", 80);
        try {
            socket.connect(address);
            return true;
        } catch (IOException e) {
            return false;

        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
