package modelTests;

import org.joml.Vector3f;
import org.joml.Quaternionf;

import org.junit.Before;
import org.junit.Test;

import view.Tortue3D;

import static org.junit.Assert.*;

public class Tortue3DTest {

    private Tortue3D tortue;

    @Before
    public void setUp() {
        tortue = new Tortue3D(30);
        tortue.setPosition(new Vector3f(1.0f, 1.0f, 1.0f));
        tortue.setOrientation();
    }

    @Test
    public void testTournerGaucheDroite() {
        Quaternionf initialOrientation = new Quaternionf(tortue.getOrientation());

        tortue.tournerGauche();
        assertFalse("L'orientation devrait changer après avoir tourné à gauche.", initialOrientation.equals(tortue.getOrientation(), 1e-4f));

        initialOrientation.set(tortue.getOrientation());
        tortue.tournerDroite();
        assertFalse("L'orientation devrait changer après avoir tourné à droite.", initialOrientation.equals(tortue.getOrientation(), 1e-4f));
    }

    @Test
    public void testPivoterVersLeHautEtVersLeBas() {
        Quaternionf initialOrientation = new Quaternionf(tortue.getOrientation());

        tortue.pivoterVersLeHaut();
        assertFalse("L'orientation devrait changer après avoir pivoté vers le haut.", initialOrientation.equals(tortue.getOrientation(), 1e-4f));

        initialOrientation.set(tortue.getOrientation());
        tortue.pivoterVersLeBas();
        assertFalse("L'orientation devrait changer après avoir pivoté vers le bas.", initialOrientation.equals(tortue.getOrientation(), 1e-4f));
    }

    @Test
    public void testEmpilerDepilerEtat() {
        Vector3f initialPosition = new Vector3f(tortue.getPosition());
        Quaternionf initialOrientation = new Quaternionf(tortue.getOrientation());

        tortue.empilerEtat();
        tortue.setPosition(new Vector3f(10.0f, 10.0f, 10.0f));
        tortue.pivoterVersLeHaut();

        tortue.depilerEtat();
        assertEquals("La position après dépilage doit correspondre à l'initial.", initialPosition, tortue.getPosition());
        assertEquals("L'orientation après dépilage doit correspondre à l'initial.", initialOrientation, tortue.getOrientation());
    }

    @Test
    public void testBasculerADroite() {
        Quaternionf initialOrientation = new Quaternionf(tortue.getOrientation());

        tortue.basculerADroite();
        assertFalse("L'orientation devrait changer après basculer à droite.", initialOrientation.equals(tortue.getOrientation(), 1e-4f));
    }

    @Test
    public void testBasculerAGauche() {
        Quaternionf initialOrientation = new Quaternionf(tortue.getOrientation());

        tortue.basculerAGauche();
        assertFalse("L'orientation devrait changer après basculer à gauche.", initialOrientation.equals(tortue.getOrientation(), 1e-4f));
    }

}








