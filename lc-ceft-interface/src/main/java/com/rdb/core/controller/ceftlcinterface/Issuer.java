package com.rdb.core.controller.ceftlcinterface;

import com.rdb.core.controller.ceftlcinterface.dto.*;
import com.rdb.core.controller.ceftlcinterface.helper.IsoHelper;
import com.rdb.core.controller.ceftlcinterface.helper.MessageHelper;
import com.rdb.core.controller.ceftlcinterface.util.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

@Component
public class Issuer implements Runnable{

    @Autowired
    MessageHelper messageHelper;

    @Autowired
    IsoHelper isoHelper;

    private boolean multipleMsgPackets = false;

    @Override
    public void run() {
        try {

            ServerSocket serverSocket = new ServerSocket(6314);
            while (true) {
                Socket socket = serverSocket.accept();
                messageHelper.send(new EchoMessage(), socket);
                BaseMessage echoResponse = messageHelper.readNext(socket);
                messageHelper.send(new LogonMessage(), socket);

                BaseMessage logonResponse = messageHelper.readNext(socket);
                messageHelper.send(new KeyExchangeMessage(), socket);
                BaseMessage keyExchangeResponse = messageHelper.readNext(socket);
                System.out.println("Logon success");

                FinancialMessage financialMessage = MessageBuilder.build();
                byte[] messageBytes = isoHelper.encodeFinancialMessage(financialMessage);
//                ReversalMessage reversalMessage = MessageBuilder.reversal();
//                byte[] messageBytes = isoHelper.encodeReversalMessage(reversalMessage);
                if (multipleMsgPackets) {
                    int start = 0;
                    int length = messageBytes.length;
                    while (start < length) {


                        int end = start + 100 <= length ? start + 100 : length;
                        byte[] frag = Arrays.copyOfRange(messageBytes, start, end);
                        socket.getOutputStream().write(frag);
                        start = end;
                        Thread.sleep(1000);
                    }
                } else {
                    socket.getOutputStream().write(messageBytes);
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
