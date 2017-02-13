package com.telegram.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
    public AppTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( AppTest.class );
    }

    public void testApp() {
        // Node n = new Node(12345);
        // GUI g = new GUI(n);
        // assertTrue( g.getNode() == n );
    }
}
