package com.rdb.core.controller;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        RestTemplate template = new RestTemplate();
        RdbCoreBankRequest request = new RdbCoreBankRequest();
        request.setNarration("3323");
        request.setSourceAccountNo("3323");
        request.setDestinationAccountNo("3323");
        request.setAmount(12.33);
        ResponseEntity<String> response = template.postForEntity("http://127.0.0.1:9100/api/fund-transfer/account-to-gl", request, String.class);
        System.out.println(response.getStatusCode());

    }
}
