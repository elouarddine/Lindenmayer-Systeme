package mainPackageTest;

import org.junit.Test;

import mainPackage.MainConsole;

import static org.junit.Assert.*;

public class ApplicationStressTest {

	@Test
	public void stressTest() {
	    String[] args = {
	        "TROIS_D", 
	        "A", 
	        "A:AB,B:A", 
	        "20", 
	        "60" 
	    };

	    long startTime = System.currentTimeMillis();
	    MainConsole.main(args);
	    long endTime = System.currentTimeMillis();
	    System.out.println("Durée du test de stress: " + (endTime - startTime) + "ms");
	}
}