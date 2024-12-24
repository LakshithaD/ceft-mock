package com.rdb.core.controller.ceftlcinterface;

import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.parse.ConfigParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URL;

@SpringBootApplication
@Configuration
public class CeftLcInterfaceMain implements CommandLineRunner {

	@Autowired
	private Issuer issuerer;

	@Autowired
	private Acquirer acquirer;

	private boolean runAcquirer = false;

	public static void main(String[] args) {
		SpringApplication.run(CeftLcInterfaceMain.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (runAcquirer) {
			Thread acquirerThread = new Thread(acquirer);
			acquirerThread.start();
		} else {

			Thread issuerThread = new Thread(issuerer);
			issuerThread.start();
		}
	}

	@Bean
	public MessageFactory<IsoMessage> msgFactory() throws IOException {

		/*LOGGER.info("intializing Message Factory");//classpath:iso.xml iso.xml*/
		URL url = this.getClass().getClassLoader().getResource( "iso.xml");
		/*LOGGER.info(url.getPath());*/
		MessageFactory<IsoMessage> msgFactory = ConfigParser.createFromUrl( url);
		msgFactory.setForceSecondaryBitmap(Boolean.FALSE);
		msgFactory.setUseBinaryBitmap(Boolean.TRUE);
		// smsgFactory.setUseBinaryBitmap(Boolean.FALSE);
		/*LOGGER.info("intialized Message Factory");*/
		return msgFactory;
	}
}
