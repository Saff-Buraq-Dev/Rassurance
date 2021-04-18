package validation;

import org.junit.jupiter.api.AfterEach;
import client.Client;
import client.Reclamation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {
    Client clientValide;
    Client clientInvalideNumero;
    Client clientInvalideContrat;
    Client clientInvalideMoisFormat;
    Client clientInvalideMoisInexistant;
    Client clientInvalideMoisIncompatible;
    Client clientInvalideReclamationSoinIncorrect;
    Client clientInvalideReclamationDateFormat;
    Client clientInvalideReclamationDateInexistante;
    Client clientInvalideReclamationMontantVirgule;
    Client clientInvalideReclamationMontantPoint;
    Client clientInvalideReclamationMontantDollar;
    Client clientInvalideDossierNull;
    Client clientInvalideReclamationMontantNull;
    ArrayList<Reclamation> reclamationValide = new ArrayList<>();
    ArrayList<Reclamation> reclamationInvalideSoinIncorrect = new ArrayList<>();
    ArrayList<Reclamation> reclamationInvalideDateFormat = new ArrayList<>();
    ArrayList<Reclamation> reclamationInvalideDateInexistante = new ArrayList<>();
    ArrayList<Reclamation> reclamationInvalideMontantVirgule = new ArrayList<>();
    ArrayList<Reclamation> reclamationInvalideMontantPoint = new ArrayList<>();
    ArrayList<Reclamation> reclamationInvalideMontantDollar = new ArrayList<>();
    ArrayList<Reclamation> reclamationInvalideMontantNull = new ArrayList<>();


    @BeforeEach
    void setUp() {
        reclamationValide.add(new Reclamation(100, "2021-01-15", "234.00$"));
        reclamationInvalideSoinIncorrect.add(new Reclamation(203, "2021-01-15", "234.00"));
        reclamationInvalideDateFormat.add(new Reclamation(100, "2021-23-02", "234.00$"));
        reclamationInvalideDateInexistante.add(new Reclamation(100, "2019-02-29", "234.00$"));
        reclamationInvalideMontantVirgule.add(new Reclamation(100, "2021-01-15", "1,234.00$"));
        reclamationInvalideMontantPoint.add(new Reclamation(100, "2021-01-15", "23400$"));
        reclamationInvalideMontantDollar.add(new Reclamation(100, "2021-01-15", "234.00"));
        reclamationInvalideMontantNull.add(new Reclamation(100, "2021-01-15", null));
        clientValide = new Client("A123456", "2021-01", reclamationValide);
        clientInvalideNumero = new Client("A1234567", "2021-01", reclamationValide);
        clientInvalideContrat = new Client("F123456", "2021-01" , reclamationValide);
        clientInvalideMoisFormat = new Client("A123456", "2021-01-12", reclamationValide);
        clientInvalideMoisInexistant = new Client("A123456", "2021-13" , reclamationValide);
        clientInvalideMoisIncompatible= new Client("A123456", "2021-02" , reclamationValide);
        clientInvalideReclamationSoinIncorrect = new Client("A123456", "2021-01" ,
                reclamationInvalideSoinIncorrect);
        clientInvalideReclamationDateFormat = new Client("A123456", "2021-01",
                reclamationInvalideDateFormat);
        clientInvalideReclamationDateInexistante = new Client("A123456", "2021-01",
                reclamationInvalideDateInexistante);
        clientInvalideReclamationMontantVirgule = new Client("A123456", "2021-01" ,
                reclamationInvalideMontantVirgule);
        clientInvalideReclamationMontantPoint = new Client("A123456", "2021-01",
                reclamationInvalideMontantPoint);
        clientInvalideReclamationMontantDollar = new Client("A123456", "2021-01",
                reclamationInvalideMontantDollar);
        clientInvalideDossierNull = new Client(null, "2021-01", reclamationValide);
        clientInvalideReclamationMontantNull = new Client("A123456", "2021-01", reclamationInvalideMontantNull);

    }

    @AfterEach
    void tearDown() {
        clientValide = null;
        clientInvalideNumero = null;
        clientInvalideContrat = null;
        clientInvalideMoisFormat = null;
        clientInvalideMoisInexistant = null;
        clientInvalideMoisIncompatible = null;
        clientInvalideReclamationSoinIncorrect = null;
        clientInvalideReclamationDateFormat = null;
        clientInvalideReclamationDateInexistante = null;
        clientInvalideReclamationMontantVirgule = null;
        clientInvalideReclamationMontantPoint = null;
        clientInvalideReclamationMontantDollar = null;
    }

    @Test
    void validerDonneesClientValide() {
        assertDoesNotThrow(()-> Validation.validerDonnees(clientValide));
    }

    @Test
    void validerDonneesClientInvalideNumero() {
        assertThrows(DonneeInvalideException.class, ()-> Validation.validerDonnees(clientInvalideNumero));
    }

    @Test
    void validerDonneesClientInvalideContrat() {
        assertThrows(DonneeInvalideException.class, ()-> Validation.validerDonnees(clientInvalideContrat));
    }

    @Test
    void validerDonneesClientInvalideMoisFormat() {
        assertThrows(DonneeInvalideException.class, ()-> Validation.validerDonnees(clientInvalideMoisFormat));
    }

    @Test
    void validerDonneesClientInvalideMoisInexistant() {
        assertThrows(DonneeInvalideException.class, ()-> Validation.validerDonnees(clientInvalideMoisInexistant));
    }

    @Test
    void validerDonneesClientInvalideMoisIncompatible() {
        assertThrows(DonneeInvalideException.class, ()-> Validation.validerDonnees(clientInvalideMoisIncompatible));
    }


    @Test
    void validerDonneesClientInvalideReclamationSoinIncorrect() {
        assertThrows(DonneeInvalideException.class, ()-> Validation.validerDonnees(clientInvalideReclamationSoinIncorrect));
    }

    @Test
    void validerDonneesClientInvalideReclamationDateFormat() {
        assertThrows(DonneeInvalideException.class, ()-> Validation.validerDonnees(clientInvalideReclamationDateFormat));
    }

    @Test
    void validerDonneesClientInvalideReclamationDateInexistante() {
        assertThrows(DonneeInvalideException.class, ()-> Validation.validerDonnees(clientInvalideReclamationDateInexistante));
    }


    @Test
    void validerDonneesClientInvalideReclamationMontantVirgule() {
        assertThrows(DonneeInvalideException.class, ()-> Validation.validerDonnees(clientInvalideReclamationMontantVirgule));
    }

    @Test
    void validerDonneesClientInvalideReclamationMontantPoint() {
        assertThrows(DonneeInvalideException.class, ()-> Validation.validerDonnees(clientInvalideReclamationMontantPoint));
    }

    @Test
    void validerDonneesClientInvalideReclamationMontantDollar() {
        assertThrows(DonneeInvalideException.class, ()-> Validation.validerDonnees(clientInvalideReclamationMontantDollar));
    }

    @Test
    void validerDonneesClientInvalideReclamationMontantNull(){
        assertThrows(DonneeInvalideException.class, ()-> Validation.validerDonnees(clientInvalideReclamationMontantNull));
    }

    @Test
    void validerDonneesClientInvalideDossierNull(){
        assertThrows(DonneeInvalideException.class, ()-> Validation.validerDonnees(clientInvalideDossierNull));
    }
}
