package modelTests;

import model.Interpretation;
import model.Tortue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class InterpretationTest {

    private Tortue tortueMock;
    private Interpretation interpretation;

    @Before
    public void setUp() {
        tortueMock = mock(Tortue.class);
        interpretation = new Interpretation(tortueMock, "F+++FF-[]&^F[]/\\|");
    }

    @Test
    public void testDessiner() {
        
    	interpretation.dessiner(2);
        verify(tortueMock, times(4)).avancer(2);
        verify(tortueMock, times(3)).tournerGauche();
        verify(tortueMock, times(1)).tournerDroite();
        verify(tortueMock, times(2)).empilerEtat();
        verify(tortueMock, times(2)).depilerEtat();
        verify(tortueMock, times(1)).pivoterVersLeBas();
        verify(tortueMock, times(1)).pivoterVersLeHaut();
        verify(tortueMock, times(1)).basculerADroite();
        verify(tortueMock, times(1)).basculerAGauche();
        verify(tortueMock, times(1)).demiTour();
    }
}
