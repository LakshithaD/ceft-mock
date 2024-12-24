package com.rdb.core.controller.ceftlcinterface;

import com.rdb.core.controller.ceftlcinterface.dto.*;
import com.rdb.core.controller.ceftlcinterface.enums.MerchantType;
import com.rdb.core.controller.ceftlcinterface.enums.ResponseCode;
import com.rdb.core.controller.ceftlcinterface.helper.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
    public class Acquirer implements Runnable{

    @Autowired
    MessageHelper messageHelper;
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(4040);
            Socket socket = serverSocket.accept();
            while (true) {
                BaseMessage message = messageHelper.readNext(socket);
                System.out.println(message);
                if (message.isRequest()) {

                    if (message instanceof NetworkMessage) {
                        NetworkMessage networkMessage = (NetworkMessage) message;
                        switch (networkMessage.getNetworkMgtCode()) {
                            case KEY_MESSAGE_MEMBER_BANK:
                                LcKeyExchangeMessage keyReq = new LcKeyExchangeMessage();
                                messageHelper.send(keyReq, socket);
                                break;
                        }
                        message.setResponseCode(ResponseCode.SUCCESS);
                        messageHelper.sendMessage(message, socket);
                    } else if (message instanceof FinancialMessage) {
                        FinancialMessage financialMessage = (FinancialMessage)message;
                        if (financialMessage.getMerchantType() == MerchantType.JP_TRANSACTION
                                || financialMessage.getMerchantType() == MerchantType.QR_TYPE_1_MERCHANT_CR
                                || financialMessage.getMerchantType() == MerchantType.QR_TYPE_2_CUSTOMER_DR
                                || financialMessage.getMerchantType() == MerchantType.QR_TYPE_3_MERCHANT_CR
                                || financialMessage.getMerchantType() == MerchantType.QR_TYPE_4_MERCHANT_CR) {
                            financialMessage.setResponseCode(ResponseCode.SUCCESS);
                        } else {
                            financialMessage.setResponseCode(ResponseCode.SUCCESS);
                        }

                        messageHelper.sendMessage(message, socket);
                    } else if (message instanceof ReversalMessage) {
                        System.out.println("REversal recevided --------------------------------------");
                        message.setResponseCode(ResponseCode.SUCCESS);
                        messageHelper.sendMessage(message, socket);
                        System.out.println("REversal sent --------------------------------------");
                    }

                } else {

                    System.out.println("----------------------- IMPLEMENT");
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
