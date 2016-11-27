package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.Mediatheque;
import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.document.Livre;

public class MediathequeTest {
	
	private String nom;
	
	private Genre genre;
	private Localisation localisation;
	private CategorieClient categorieClient;
	private Client client;
	private Livre livre;
	
	private Mediatheque mediatheque;
	
	
	
	@Before
	public void setUp() throws Exception {
		this.nom = "AP-NB";
		this.mediatheque = new Mediatheque(nom);
	}

	@Test
	public void testMediatheque() {
		this.mediatheque = new Mediatheque(nom);
		assertEquals("AP-NB", this.mediatheque.getNom());
	}
	
	@Test
	public void testMediathequeDebug() {
		this.mediatheque = new Mediatheque(nom);
		assertEquals("AP-NB", this.mediatheque.getNom());
	}

	@Test
	public void testEmpty() {
		fail("Not yet implemented");
	}

	@Test
	public void testChercherGenre() {
		fail("Not yet implemented");
	}

	@Test
	public void testSupprimerGenre() {
		fail("Not yet implemented");
	}

	@Test
	public void testAjouterGenre() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifierGenre() {
		fail("Not yet implemented");
	}

	@Test
	public void testListerGenres() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGenreAt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGenresSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testSupprimerLocalisation() {
		fail("Not yet implemented");
	}

	@Test
	public void testChercherLocalisation() {
		fail("Not yet implemented");
	}

	@Test
	public void testAjouterLocalisation() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifierLocalisation() {
		fail("Not yet implemented");
	}

	@Test
	public void testListerLocalisations() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLocalisationAt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLocalisationsSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testChercherCatClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testSupprimerCatClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testAjouterCatClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifierCatClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testListerCatsClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCategorieAt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCategoriesSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testChercherDocument() {
		fail("Not yet implemented");
	}

	@Test
	public void testAjouterDocument() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetirerDocument() {
		fail("Not yet implemented");
	}

	@Test
	public void testMetEmpruntable() {
		fail("Not yet implemented");
	}

	@Test
	public void testMetConsultable() {
		fail("Not yet implemented");
	}

	@Test
	public void testListerDocuments() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDocumentAt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDocumentsSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmprunter() {
		fail("Not yet implemented");
	}

	@Test
	public void testRestituer() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerifier() {
		fail("Not yet implemented");
	}

	@Test
	public void testListerFicheEmprunts() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFicheEmpruntAt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFicheEmpruntsSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testInscrireStringStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testInscrireStringStringStringStringInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testResilier() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifierClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangerCategorie() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangerCodeReduction() {
		fail("Not yet implemented");
	}

	@Test
	public void testChercherClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testListerClients() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClientAt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClientsSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindClient() {
		fail("Not yet implemented");
	}

	@Test
	public void testAfficherStatistiques() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNom() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitFromFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveToFile() {
		fail("Not yet implemented");
	}

}
