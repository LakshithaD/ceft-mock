package com.rdb.core.controller.ceftlcinterface.helper;

import com.rdb.core.controller.ceftlcinterface.dto.BaseMessage;
import com.rdb.core.controller.ceftlcinterface.dto.NetworkMessage;
import com.rdb.core.controller.ceftlcinterface.util.ByteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.text.ParseException;

@Component
public class MessageHelper {

    @Autowired
    private MessageConversionHelper messageConversionHelper;

    @Autowired
    private IsoHelper isoHelper;

    public Boolean sendMessage(BaseMessage baseMessage, Socket socket) {

        try {
            byte[] messageBytes = messageConversionHelper.convertMessageToBytes(baseMessage);
            //  LOGGER.info("Sending bytes {}", messageBytes);
            send(messageBytes, socket);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            //LOGGER.error("EXC", ex);
        }
        return null;
    }

    public Boolean send(NetworkMessage baseMessage, Socket socket) {

        byte[] messageBytes = baseMessage.isRequest() ? isoHelper.encodeNetworkResponseMessage(baseMessage) : isoHelper.encodeNetworkMessage(baseMessage);
        return send(messageBytes, socket);
    }

    private Boolean send(byte[] messageBytes, Socket socket) {

        try {
            socket.getOutputStream().write(messageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }

    public BaseMessage readNext(Socket socket) {

        try {
            byte[] lengthBytes = new byte[4];
            int length = socket.getInputStream().read(lengthBytes);
            while (length == -1) {

                length = socket.getInputStream().read(lengthBytes);
                Thread.sleep(10 * 1000);
            }
            //LOGGER.info("Read {} number of length bytes. bytes {}", length, lengthBytes);

            int msgLength = ByteUtil.getLength(lengthBytes);
            byte[] msgBytes = new byte[msgLength];
            length = socket.getInputStream().read(msgBytes);
            while (length == -1) {

                length = socket.getInputStream().read(lengthBytes);
                Thread.sleep(10 * 1000);
            }
            // LOGGER.info("Read {} number of message bytes. bytes {}", length, msgBytes);
            BaseMessage baseMessage = messageConversionHelper.convertIsoMessage(msgBytes);
            return baseMessage;
        } catch (SocketException ex) {

            //throw new LcConnectivityLostException();

        } catch (IOException | ParseException e) {

            //  log.error("Issue parsing message bytes", e);
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
