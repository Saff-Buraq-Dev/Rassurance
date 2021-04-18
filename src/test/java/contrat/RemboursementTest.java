
package contrat;

import client.Client;
import client.Reclamation;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RemboursementTest {
    Client clientA;
    Client clientB;
    Client clientC;
    Client clientD;
    Client clientE;
    Client clientMax;
    ArrayList<Reclamation> listeReclamation;
    ArrayList<Reclamation> listeReclamationAnterieure;

    @BeforeEach
    void setUp() {
        listeReclamation = new ArrayList<>();
        listeReclamation.add(new Reclamation(0, "2021-01-01", "200.00"));
        listeReclamation.add(new Reclamation(100, "2021-01-01", "100.00"));
        listeReclamation.add(new Reclamation(150, "2021-01-01", "100.00"));
        listeReclamation.add(new Reclamation(175, "2021-01-01", "100.00"));
        listeReclamation.add(new Reclamation(200, "2021-01-01", "100.00"));
        listeReclamation.add(new Reclamation(300, "2021-01-01", "100.00"));
        listeReclamation.add(new Reclamation(400, "2021-01-01", "100.00"));
        listeReclamation.add(new Reclamation(500, "2021-01-01", "100.00"));
        listeReclamation.add(new Reclamation(600, "2021-01-01", "100.00"));
        listeReclamation.add(new Reclamation(700, "2021-01-01", "100.00"));
        listeReclamation.add(new Reclamation(100, "2021-01-01", "100.00"));
        listeReclamation.add(new Reclamation(600, "2021-01-01", "100.00"));

        clientA = new Client("A000000", "2021-01", listeReclamation);
        clientB = new Client("B000000", "2021-01", listeReclamation);
        clientC = new Client("C000000", "2021-01", listeReclamation);
        clientD = new Client("D000000", "2021-01", listeReclamation);
        clientE = new Client("E000000", "2021-01", listeReclamation);
        clientMax = new Client("E000001", "2021-01", listeReclamation);
    }

    @AfterEach
    void TearDown() {
        clientA = null;
        clientB = null;
        clientC = null;
        clientD = null;
        clientE = null;
        clientMax = null;
    }

    @Test
    @DisplayName("A0 : remboursement valide")
    void calculRemboursementA0() {
        new Remboursement().calculerRemboursementSelonContrat(clientA);
        assertEquals("50.00$", clientA.getListeReclamation().get(0).getMontantRemboursementString());
    }

    @Test
    @DisplayName("A100 : remboursement valide")
    void calculRemboursementA100() {
        new Remboursement().calculerRemboursementSelonContrat(clientA);
        assertEquals("35.00$", clientA.getListeReclamation().get(1).getMontantRemboursementString());
    }

    @Test
    @DisplayName("A150 : remboursement valide")
    void calculRemboursementA150() {
        new Remboursement().calculerRemboursementSelonContrat(clientA);
        assertEquals("0.00$", clientA.getListeReclamation().get(2).getMontantRemboursementString());
    }

    @Test
    @DisplayName("A175 : remboursement valide")
    void calculRemboursementA175() {
        new Remboursement().calculerRemboursementSelonContrat(clientA);
        assertEquals("50.00$", clientA.getListeReclamation().get(3).getMontantRemboursementString());
    }

    @Test
    @DisplayName("A200 : remboursement valide")
    void calculRemboursementA200() {
        new Remboursement().calculerRemboursementSelonContrat(clientA);
        assertEquals("25.00$", clientA.getListeReclamation().get(4).getMontantRemboursementString());
    }

    @Test
    @DisplayName("A300 : remboursement valide")
    void calculRemboursementA300() {
        new Remboursement().calculerRemboursementSelonContrat(clientA);
        assertEquals("0.00$", clientA.getListeReclamation().get(5).getMontantRemboursementString());
    }

    @Test
    @DisplayName("A400 : remboursement valide")
    void calculRemboursementA400() {
        new Remboursement().calculerRemboursementSelonContrat(clientA);
        assertEquals("0.00$", clientA.getListeReclamation().get(6).getMontantRemboursementString());
    }

    @Test
    @DisplayName("A500 : remboursement valide")
    void calculRemboursementA500() {
        new Remboursement().calculerRemboursementSelonContrat(clientA);
        assertEquals("25.00$", clientA.getListeReclamation().get(7).getMontantRemboursementString());
    }

    @Test
    @DisplayName("A600 : remboursement valide")
    void calculRemboursementA600() {
        new Remboursement().calculerRemboursementSelonContrat(clientA);
        assertEquals("40.00$", clientA.getListeReclamation().get(8).getMontantRemboursementString());
    }

    @Test
    @DisplayName("A700 : remboursement valide")
    void calculRemboursementA700() {
        new Remboursement().calculerRemboursementSelonContrat(clientA);
        assertEquals("0.00$", clientA.getListeReclamation().get(9).getMontantRemboursementString());
    }

    @Test
    @DisplayName("B0 : remboursement valide")
    void calculRemboursementB0() {
        new Remboursement().calculerRemboursementSelonContrat(clientB);
        assertEquals("40.00$", clientB.getListeReclamation().get(0).getMontantRemboursementString());
    }

    @Test
    @DisplayName("B100 : remboursement valide")
    void calculRemboursementB100() {
        new Remboursement().calculerRemboursementSelonContrat(clientB);
        assertEquals("50.00$", clientB.getListeReclamation().get(1).getMontantRemboursementString());
    }

    @Test
    @DisplayName("B150 : remboursement valide")
    void calculRemboursementB150() {
        new Remboursement().calculerRemboursementSelonContrat(clientB);
        assertEquals("0.00$", clientB.getListeReclamation().get(2).getMontantRemboursementString());
    }

    @Test
    @DisplayName("B175 : remboursement valide")
    void calculRemboursementB175() {
        new Remboursement().calculerRemboursementSelonContrat(clientB);
        assertEquals("75.00$", clientB.getListeReclamation().get(3).getMontantRemboursementString());
    }

    @Test
    @DisplayName("B200 : remboursement valide")
    void calculRemboursementB200() {
        new Remboursement().calculerRemboursementSelonContrat(clientB);
        assertEquals("100.00$", clientB.getListeReclamation().get(4).getMontantRemboursementString());
    }

    @Test
    @DisplayName("B300 : remboursement valide")
    void calculRemboursementB300() {
        new Remboursement().calculerRemboursementSelonContrat(clientB);
        assertEquals("50.00$", clientB.getListeReclamation().get(5).getMontantRemboursementString());
    }

    @Test
    @DisplayName("B400 : remboursement valide")
    void calculRemboursementB400() {
        new Remboursement().calculerRemboursementSelonContrat(clientB);
        assertEquals("0.00$", clientB.getListeReclamation().get(6).getMontantRemboursementString());
    }

    @Test
    @DisplayName("B500 : remboursement valide")
    void calculRemboursementB500() {
        new Remboursement().calculerRemboursementSelonContrat(clientB);
        assertEquals("50.00$", clientB.getListeReclamation().get(7).getMontantRemboursementString());
    }

    @Test
    @DisplayName("B600 : remboursement valide")
    void calculRemboursementB600() {
        new Remboursement().calculerRemboursementSelonContrat(clientB);
        assertEquals("100.00$", clientB.getListeReclamation().get(8).getMontantRemboursementString());
    }

    @Test
    @DisplayName("B700 : remboursement valide")
    void calculRemboursementB700() {
        new Remboursement().calculerRemboursementSelonContrat(clientB);
        assertEquals("70.00$", clientB.getListeReclamation().get(9).getMontantRemboursementString());
    }

    @Test
    @DisplayName("C0 : remboursement valide")
    void calculRemboursementC0() {
        new Remboursement().calculerRemboursementSelonContrat(clientC);
        assertEquals("180.00$", clientC.getListeReclamation().get(0).getMontantRemboursementString());
    }

    @Test
    @DisplayName("C100 : remboursement valide")
    void calculRemboursementC100() {
        new Remboursement().calculerRemboursementSelonContrat(clientC);
        assertEquals("95.00$", clientC.getListeReclamation().get(1).getMontantRemboursementString());
    }

    @Test
    @DisplayName("C150 : remboursement valide")
    void calculRemboursementC150() {
        new Remboursement().calculerRemboursementSelonContrat(clientC);
        assertEquals("85.00$", clientC.getListeReclamation().get(2).getMontantRemboursementString());
    }

    @Test
    @DisplayName("C175 : remboursement valide")
    void calculRemboursementC175() {
        new Remboursement().calculerRemboursementSelonContrat(clientC);
        assertEquals("90.00$", clientC.getListeReclamation().get(3).getMontantRemboursementString());
    }

    @Test
    @DisplayName("C200 : remboursement valide")
    void calculRemboursementC200() {
        new Remboursement().calculerRemboursementSelonContrat(clientC);
        assertEquals("90.00$", clientC.getListeReclamation().get(4).getMontantRemboursementString());
    }

    @Test
    @DisplayName("C300 : remboursement valide")
    void calculRemboursementC300() {
        new Remboursement().calculerRemboursementSelonContrat(clientC);
        assertEquals("90.00$", clientC.getListeReclamation().get(5).getMontantRemboursementString());
    }

    @Test
    @DisplayName("C400 : remboursement valide")
    void calculRemboursement400() {
        new Remboursement().calculerRemboursementSelonContrat(clientC);
        assertEquals("90.00$", clientC.getListeReclamation().get(6).getMontantRemboursementString());
    }

    @Test
    @DisplayName("C500 : remboursement valide")
    void calculRemboursementC500() {
        new Remboursement().calculerRemboursementSelonContrat(clientC);
        assertEquals("90.00$", clientC.getListeReclamation().get(7).getMontantRemboursementString());
    }

    @Test
    @DisplayName("C600 : remboursement valide")
    void calculRemboursementC600() {
        new Remboursement().calculerRemboursementSelonContrat(clientC);
        assertEquals("75.00$", clientC.getListeReclamation().get(8).getMontantRemboursementString());
    }

    @Test
    @DisplayName("C700 : remboursement valide")
    void calculRemboursementC700() {
        new Remboursement().calculerRemboursementSelonContrat(clientC);
        assertEquals("90.00$", clientC.getListeReclamation().get(9).getMontantRemboursementString());
    }

    @Test
    @DisplayName("D0 : remboursement valide")
    void calculRemboursementD0() {
        new Remboursement().calculerRemboursementSelonContrat(clientD);
        assertEquals("85.00$", clientD.getListeReclamation().get(0).getMontantRemboursementString());
    }

    @Test
    @DisplayName("D100 : remboursement valide")
    void calculRemboursementD100() {
        new Remboursement().calculerRemboursementSelonContrat(clientD);
        assertEquals("75.00$", clientD.getListeReclamation().get(1).getMontantRemboursementString());
    }

    @Test
    @DisplayName("D150 : remboursement valide")
    void calculRemboursementD150() {
        new Remboursement().calculerRemboursementSelonContrat(clientD);
        assertEquals("100.00$", clientD.getListeReclamation().get(2).getMontantRemboursementString());
    }

    @Test
    @DisplayName("D175 : remboursement valide")
    void calculRemboursementD175() {
        new Remboursement().calculerRemboursementSelonContrat(clientD);
        assertEquals("95.00$", clientD.getListeReclamation().get(3).getMontantRemboursementString());
    }

    @Test
    @DisplayName("D200 : remboursement valide")
    void calculRemboursementD200() {
        new Remboursement().calculerRemboursementSelonContrat(clientD);
        assertEquals("100.00$", clientD.getListeReclamation().get(4).getMontantRemboursementString());
    }

    @Test
    @DisplayName("D300 : remboursement valide")
    void calculRemboursementD300() {
        new Remboursement().calculerRemboursementSelonContrat(clientD);
        assertEquals("100.00$", clientD.getListeReclamation().get(5).getMontantRemboursementString());
    }

    @Test
    @DisplayName("D400 : remboursement valide")
    void calculRemboursementD400() {
        new Remboursement().calculerRemboursementSelonContrat(clientD);
        assertEquals("65.00$", clientD.getListeReclamation().get(6).getMontantRemboursementString());
    }

    @Test
    @DisplayName("D500 : remboursement valide")
    void calculRemboursementD500() {
        new Remboursement().calculerRemboursementSelonContrat(clientD);
        assertEquals("100.00$", clientD.getListeReclamation().get(7).getMontantRemboursementString());
    }

    @Test
    @DisplayName("D600 : remboursement valide")
    void calculRemboursementD600() {
        new Remboursement().calculerRemboursementSelonContrat(clientD);
        assertEquals("100.00$", clientD.getListeReclamation().get(8).getMontantRemboursementString());
    }

    @Test
    @DisplayName("D700 : remboursement valide")
    void calculRemboursementD700() {
        new Remboursement().calculerRemboursementSelonContrat(clientD);
        assertEquals("90.00$", clientD.getListeReclamation().get(9).getMontantRemboursementString());
    }

    @Test
    @DisplayName("E0 : remboursement valide")
    void calculRemboursementE0() {
        new Remboursement().calculerRemboursementSelonContrat(clientE);
        assertEquals("30.00$", clientE.getListeReclamation().get(0).getMontantRemboursementString());
    }

    @Test
    @DisplayName("E100 : remboursement valide")
    void calculRemboursementE100() {
        new Remboursement().calculerRemboursementSelonContrat(clientE);
        assertEquals("25.00$", clientE.getListeReclamation().get(1).getMontantRemboursementString());
    }

    @Test
    @DisplayName("E150 : remboursement valide")
    void calculRemboursementE150() {
        new Remboursement().calculerRemboursementSelonContrat(clientE);
        assertEquals("15.00$", clientE.getListeReclamation().get(2).getMontantRemboursementString());
    }

    @Test
    @DisplayName("E175 : remboursement valide")
    void calculRemboursementE175() {
        new Remboursement().calculerRemboursementSelonContrat(clientE);
        assertEquals("20.00$", clientE.getListeReclamation().get(3).getMontantRemboursementString());
    }

    @Test
    @DisplayName("E200 : remboursement valide")
    void calculRemboursementE200() {
        new Remboursement().calculerRemboursementSelonContrat(clientE);
        assertEquals("12.00$", clientE.getListeReclamation().get(4).getMontantRemboursementString());
    }

    @Test
    @DisplayName("E300 : remboursement valide")
    void calculRemboursementE300() {
        new Remboursement().calculerRemboursementSelonContrat(clientE);
        assertEquals("60.00$", clientE.getListeReclamation().get(5).getMontantRemboursementString());
    }

    @Test
    @DisplayName("E400 : remboursement valide")
    void calculRemboursementE400() {
        new Remboursement().calculerRemboursementSelonContrat(clientE);
        assertEquals("15.00$", clientE.getListeReclamation().get(6).getMontantRemboursementString());
    }

    @Test
    @DisplayName("E500 : remboursement valide")
    void calculRemboursementE500() {
        new Remboursement().calculerRemboursementSelonContrat(clientE);
        assertEquals("20.00$", clientE.getListeReclamation().get(7).getMontantRemboursementString());
    }

    @Test
    @DisplayName("E600 : remboursement valide")
    void calculRemboursementE600() {
        new Remboursement().calculerRemboursementSelonContrat(clientE);
        assertEquals("15.00$", clientE.getListeReclamation().get(8).getMontantRemboursementString());
    }

    @Test
    @DisplayName("E700 : remboursement valide")
    void calculRemboursementE700() {
        new Remboursement().calculerRemboursementSelonContrat(clientE);
        assertEquals("22.00$", clientE.getListeReclamation().get(9).getMontantRemboursementString());
    }


}
