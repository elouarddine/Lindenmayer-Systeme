package modelTests;
import static org.mockito.Mockito.*;
import java.awt.Graphics2D;
import org.junit.Before;
import org.junit.Test;

import view.Tortue2D;

import static org.junit.Assert.*;

public class Tortue2DTest {

    private Tortue2D tortue;
    private Graphics2D graphicsMock;

    @Before
    public void setUp() {
        tortue = new Tortue2D(90); 
        graphicsMock = mock(Graphics2D.class);
        tortue.setG2d(graphicsMock); 
    }

    @Test
    public void testAvancer() {
        tortue.setDx(0);
        tortue.setDy(0);
        tortue.setDirection();
        tortue.avancer(0);
        assertEquals("Vérification de Dx après avancer", 0, tortue.getDx(), 0.01);
        assertEquals("Vérification de Dy après avancer", -12.5, tortue.getDy(), 0.01); 
        verify(graphicsMock).drawLine(eq(0), eq(0), eq(0), eq(-12)); 
    }

    @Test
    public void testTournerGaucheDroite() {
        tortue.setDirection();
        tortue.tournerGauche();
        assertEquals("Direction après tourner à gauche", -180, tortue.getDirection(), 0.01);
        tortue.setDirection(); 
        tortue.tournerDroite();
        assertEquals("Direction après tourner à droite", 0, tortue.getDirection(), 0.01);
    }

    @Test
    public void testEmpilerDepilerEtat() {
        tortue.setDx(100);
        tortue.setDy(100);
        tortue.empilerEtat(); 
        tortue.setDx(200);
        tortue.setDy(200); 
        tortue.depilerEtat();
        assertEquals("Vérification de Dx après depiler", 100, tortue.getDx(), 0.01);
        assertEquals("Vérification de Dy après depiler", 100, tortue.getDy(), 0.01);
    }
}




