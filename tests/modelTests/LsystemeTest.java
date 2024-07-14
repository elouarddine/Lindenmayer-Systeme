package modelTests;
import model.Lsysteme;
import model.Regle;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class LsystemeTest {

    private Lsysteme lsysteme;
    private ArrayList<Regle> regles;
    @Before
    public void setUp() {
        
    	regles = new ArrayList<>();
        regles.add(new Regle('X', "X+YF"));
        regles.add(new Regle('Y', "−FX−Y"));
        lsysteme = new Lsysteme("X", regles, 0);
    }

    @Test
    public void testReplace() {
       
    	assertEquals("X+YF", lsysteme.replace('X')); 
        assertEquals("−FX−Y", lsysteme.replace('Y')); 
        assertEquals("+", lsysteme.replace('+')); 
        assertEquals("−", lsysteme.replace('−')); 
    }

    @Test
    public void testGenerate() {
        lsysteme.setNombreIteration(2);
        String resultatAttendu = "X+YF+−FX−YF+−FX+YF−−FX−YF";
        assertEquals(resultatAttendu, lsysteme.generate());
    }
}