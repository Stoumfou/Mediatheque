/**
 * 
 */
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
import mediatheque.OperationImpossible;
import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.document.Document;
import mediatheque.document.Livre;
import mediatheque.document.Video;
import util.InvariantBroken;

/**
 * @author Alexis
 *
 */
public class MediathequeTest {
	
	private String nom;
	
	private String genre;
	private String genre2;
	private Localisation localisation;
	private CategorieClient categorieClient;
	private CategorieClient categorieClient2;
	private Client client;
	private Livre livre;
	
	private Mediatheque mediatheque;
	private Video film; 
	private Video film2; 

    private int dureeFilm;
    private String mentionLegale;

    
    private String code;
    private String titre;
    private String auteur;
    private String annee;
    private boolean empruntable;
    private Genre genreFilm;
    
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.nom = "AP-NB";
		this.mediatheque = new Mediatheque(nom);
		
		this.genre = "Comédie";
		this.mediatheque.ajouterGenre(this.genre);
		
		this.localisation = new Localisation("404", "42");
		
		this.dureeFilm = 130;
		this.mentionLegale = "Film Créative Commons";
		this.code = "53455345";
		this.titre="Je suis un film";
		this.auteur="Alexis Pambourg et Nicolas Bouvet";
		this.annee="2016";
		this.genreFilm = new Genre("Comédie");
		this.film = new Video(code,localisation,titre,auteur,annee,genreFilm,dureeFilm,mentionLegale);
		this.film2 = new Video("4444444",localisation,"Je suis un autre film",auteur,annee,genreFilm,dureeFilm,mentionLegale);

		this.genre2 = "Action";
		
		this.mediatheque.ajouterLocalisation("404", "42");
				
		this.categorieClient = new CategorieClient("france", 100, 30, 10, 1.5,false);
		this.categorieClient2 = new CategorieClient("espagne", 200, 60, 20, 3,false);
		
		this.mediatheque.ajouterCatClient("france", 100, 30, 10, 1.5,false);
		
		this.client = new Client("Pambourg", "Alexis");
		
		this.mediatheque.ajouterDocument(this.film);
		this.mediatheque.inscrire(this.client.getNom(), this.client.getPrenom(), "", this.categorieClient.getNom());
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#Mediatheque(java.lang.String)}.
	 */
	@Test
	public void testMediathequeString() {
		this.mediatheque = new Mediatheque(nom);
		assertEquals("AP-NB", this.mediatheque.getNom());
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#Mediatheque(java.lang.String, boolean)}.
	 */
	@Test
	public void testMediathequeStringBoolean() {
		new Mediatheque(nom,true);
		assertEquals("AP-NB", this.mediatheque.getNom());
	}
	
	/*
	 * test de Genre
	 */
	
	/**
	 * Test method for {@link mediatheque.Mediatheque#chercherGenre(java.lang.String)}.
	 */
	@Test
	public void testChercherGenre() {
		assertEquals(null, this.mediatheque.chercherGenre("NULL"));
		assertEquals(this.genreFilm, this.mediatheque.chercherGenre(this.genre));
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#supprimerGenre(java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test
	public void testSupprimerGenre() throws OperationImpossible {
		this.mediatheque.supprimerGenre(this.genre);
	}
	
	/**
	 * Test method for {@link mediatheque.Mediatheque#supprimerGenre(java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test (expected = OperationImpossible.class)
	public void testSupprimerGenreNoDocument() throws OperationImpossible {
		this.mediatheque.ajouterLocalisation(this.localisation.getSalle(), this.localisation.getRayon());
		this.mediatheque.ajouterDocument(this.film);
		this.mediatheque.supprimerGenre(this.genre);
	}
	
	/**
	 * Test method for {@link mediatheque.Mediatheque#supprimerGenre(java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test (expected = OperationImpossible.class)
	public void testSupprimerGenreNoGenre() throws OperationImpossible {
		this.mediatheque.supprimerGenre(this.genre2);
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#ajouterGenre(java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test
	public void testAjouterGenre() throws OperationImpossible {
		this.mediatheque.ajouterGenre(this.genre2);
		Genre action = new Genre(this.genre2);
		assertEquals(action, this.mediatheque.getGenreAt(1));
	}
	
	/**
	 * Test method for {@link mediatheque.Mediatheque#ajouterGenre(java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test(expected= OperationImpossible.class)
	public void testAjouterGenreExist() throws OperationImpossible {
		this.mediatheque.ajouterGenre(this.genre);
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#modifierGenre(java.lang.String, java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test
	public void testModifierGenre() throws OperationImpossible {
		this.mediatheque.modifierGenre(this.genre, this.genre2);
		Genre action = new Genre(this.genre2);
		assertEquals(action, this.mediatheque.getGenreAt(0));
	}
	
	/**
	 * Test method for {@link mediatheque.Mediatheque#modifierGenre(java.lang.String, java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test (expected = OperationImpossible.class)
	public void testModifierGenreNotExits() throws OperationImpossible {
		this.mediatheque.modifierGenre(this.genre2, this.genre);
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#listerGenres()}.
	 */
	@Test
	public void testListerGenres() {
		this.mediatheque.listerGenres();		
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#getGenresSize()}.
	 */
	@Test
	public void testGetGenresSize() {
		assertEquals(1,this.mediatheque.getGenresSize());
	}
	
	/*
	 * test de Localisation
	 */

	/**
	 * Test method for {@link mediatheque.Mediatheque#supprimerLocalisation(java.lang.String, java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test
	public void testSupprimerLocalisation() throws OperationImpossible {
		this.mediatheque.supprimerLocalisation("404", "42");
		assertEquals(null, this.mediatheque.chercherLocalisation("404", "42"));
	}
	
	/**
	 * Test method for {@link mediatheque.Mediatheque#supprimerLocalisation(java.lang.String, java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test (expected = OperationImpossible.class)
	public void testSupprimerLocalisationNoLocalisation() throws OperationImpossible {
		this.mediatheque.supprimerLocalisation("100", "100");
	}
	
	/**
	 * Test method for {@link mediatheque.Mediatheque#supprimerLocalisation(java.lang.String, java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test (expected = OperationImpossible.class)
	public void testSupprimerLocalisationNoLocalisationButHaveDoc() throws OperationImpossible {
		this.mediatheque.ajouterDocument(this.film);
		this.mediatheque.supprimerLocalisation("404", "42");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#modifierLocalisation(mediatheque.Localisation, java.lang.String, java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test
	public void testModifierLocalisation() throws OperationImpossible {
		this.mediatheque.modifierLocalisation(this.localisation, "500", "10");
		Localisation newLoc = new Localisation("500", "10");
		assertEquals(newLoc, this.mediatheque.getLocalisationAt(0));
	}
	
	/**
	 * Test method for {@link mediatheque.Mediatheque#modifierLocalisation(mediatheque.Localisation, java.lang.String, java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test (expected = OperationImpossible.class)
	public void testModifierLocalisationNotExist() throws OperationImpossible {
		Localisation failLoc = new Localisation("500", "10");
		this.mediatheque.modifierLocalisation(failLoc, "400", "400");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#listerLocalisations()}.
	 */
	@Test
	public void testListerLocalisations() {
		this.mediatheque.listerLocalisations();
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#getLocalisationsSize()}.
	 */
	@Test
	public void testGetLocalisationsSize() {
		assertEquals(1, this.mediatheque.getLocalisationsSize());
	}
	
	/*
	 * Catégorie Client
	 */

	/**
	 * Test method for {@link mediatheque.Mediatheque#chercherCatClient(java.lang.String)}.
	 */
	@Test
	public void testChercherCatClient() {
		assertEquals(this.categorieClient, this.mediatheque.chercherCatClient("france"));
	}
	
	/**
	 * Test method for {@link mediatheque.Mediatheque#chercherCatClient(java.lang.String)}.
	 */
	@Test
	public void testChercherCatClientNotCat() {
		assertEquals(null, this.mediatheque.chercherCatClient("espagne"));
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#supprimerCatClient(java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test
	public void testSupprimerCatClient() throws OperationImpossible {
		this.mediatheque.supprimerCatClient("france");
	}
	
	/**
	 * Test method for {@link mediatheque.Mediatheque#supprimerCatClient(java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test (expected = OperationImpossible.class)
	public void testSupprimerCatClientNotCat() throws OperationImpossible {
		this.mediatheque.supprimerCatClient("espagne");
	}
	
	/**
	 * Test method for {@link mediatheque.Mediatheque#supprimerCatClient(java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test (expected = OperationImpossible.class)
	public void testSupprimerCatClientNotCatWithClient() throws OperationImpossible {
		this.client.setCategorie(this.categorieClient);
		this.mediatheque.supprimerCatClient("france");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#ajouterCatClient(java.lang.String, int, double, double, double, boolean)}.
	 */
	@Test
	public void testAjouterCatClient() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#modifierCatClient(mediatheque.client.CategorieClient, java.lang.String, int, double, double, double, boolean)}.
	 * @throws OperationImpossible 
	 */
	@Test
	public void testModifierCatClient() throws OperationImpossible {
		this.mediatheque.modifierCatClient(this.categorieClient,
				"espagne", 200, 60, 20, 3,false);
	}
	
	/**
	 * Test method for {@link mediatheque.Mediatheque#modifierCatClient(mediatheque.client.CategorieClient, java.lang.String, int, double, double, double, boolean)}.
	 * @throws OperationImpossible 
	 */
	@Test (expected = OperationImpossible.class)
	public void testModifierCatClientNoCat() throws OperationImpossible {
		this.mediatheque.modifierCatClient(this.categorieClient2,
				"espagne", 200, 60, 20, 3,false);
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#listerCatsClient()}.
	 */
	@Test
	public void testListerCatsClient() {
		this.mediatheque.listerCatsClient();
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#getCategorieAt(int)}.
	 */
	@Test
	public void testGetCategorieAt() {
		assertEquals(this.categorieClient,this.mediatheque.getCategorieAt(0));
		this.mediatheque.getCategorieAt(2);
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#getCategoriesSize()}.
	 */
	@Test
	public void testGetCategoriesSize() {
		assertEquals(1,this.mediatheque.getCategoriesSize());
	}
	
	/*
	 * Document
	 */

	/**
	 * Test method for {@link mediatheque.Mediatheque#chercherDocument(java.lang.String)}.
	 */
	@Test
	public void testChercherDocument() {
		assertEquals(this.film,this.mediatheque.chercherDocument("53455345"));
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#ajouterDocument(mediatheque.document.Document)}.
	 * @throws OperationImpossible 
	 */
	@Test
	public void testAjouterDocument() throws OperationImpossible {
		mediatheque.ajouterDocument(this.film2);
		assertEquals(2, mediatheque.getDocumentsSize());
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#retirerDocument(java.lang.String)}.
	 * @throws OperationImpossible 
	 */
	@Test
	public void testRetirerDocument() throws OperationImpossible {
		this.mediatheque.retirerDocument("53455345");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#metEmpruntable(java.lang.String)}.
	 * @throws InvariantBroken 
	 * @throws OperationImpossible 
	 */
	@Test
	public void testMetEmpruntable() throws OperationImpossible, InvariantBroken {
		this.mediatheque.metEmpruntable("53455345");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#metConsultable(java.lang.String)}.
	 * @throws InvariantBroken 
	 * @throws OperationImpossible 
	 */
	@Test
	public void testMetConsultable() throws OperationImpossible, InvariantBroken {
		this.mediatheque.metEmpruntable("53455345");
		this.mediatheque.metConsultable("53455345");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#listerDocuments()}.
	 */
	@Test
	public void testListerDocuments() {
		this.mediatheque.listerDocuments();
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#getDocumentAt(int)}.
	 */
	@Test
	public void testGetDocumentAt() {
		assertEquals(this.film,this.mediatheque.getDocumentAt(1));
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#getDocumentsSize()}.
	 */
	@Test
	public void testGetDocumentsSize() {
		assertEquals(1,this.mediatheque.getDocumentsSize());
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#emprunter(java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws InvariantBroken 
	 * @throws OperationImpossible 
	 */
	@Test
	public void testEmprunter() throws OperationImpossible, InvariantBroken {
		this.mediatheque.metEmpruntable("53455345");
		this.mediatheque.emprunter("BOUVET","Nicolas","53455345");
	}
	
	/**
	 * Test method for {@link mediatheque.Mediatheque#emprunter(java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws InvariantBroken 
	 * @throws OperationImpossible 
	 */
	@Test (expected = OperationImpossible.class)
	public void testEmprunterTwo() throws OperationImpossible, InvariantBroken {
		this.mediatheque.metEmpruntable("53455345");
		this.mediatheque.emprunter("BOUVET","Nicolas","53455345");
		this.mediatheque.emprunter("PAMBOURG","Alexis","53455345");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#restituer(java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws InvariantBroken 
	 * @throws OperationImpossible 
	 */
	@Test
	public void testRestituer() throws OperationImpossible, InvariantBroken {
		this.mediatheque.metEmpruntable("53455345");
		this.mediatheque.emprunter("BOUVET","Nicolas","53455345");
		this.mediatheque.restituer("BOUVET", "Nicolas", "53455345");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#verifier()}.
	 */
	@Test
	public void testVerifier() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#listerFicheEmprunts()}.
	 */
	@Test
	public void testListerFicheEmprunts() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#getFicheEmpruntAt(int)}.
	 */
	@Test
	public void testGetFicheEmpruntAt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#getFicheEmpruntsSize()}.
	 */
	@Test
	public void testGetFicheEmpruntsSize() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#inscrire(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testInscrire() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#inscrire(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testInscrireStringStringStringStringInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#resilier(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testResilier() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#modifierClient(mediatheque.client.Client, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 * @throws OperationImpossible 
	 */
	@Test
	public void testModifierClient() throws OperationImpossible {
		this.mediatheque.modifierClient(this.client, "Pambourg", "Alexis", "ici", "Chat", 69);
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#changerCategorie(java.lang.String, java.lang.String, java.lang.String, int)}.
	 * @throws OperationImpossible 
	 */
	@Test
	public void testChangerCategorie() throws OperationImpossible {

		this.client.setCategorie(categorieClient);
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#changerCodeReduction(java.lang.String, java.lang.String, int)}.
	 * @throws OperationImpossible 
	 */
	@Test
	public void testChangerCodeReduction() throws OperationImpossible {
		
		this.client.setCategorie(categorieClient);
		this.client.getCategorie().modifierCodeReducActif(true);
		this.mediatheque.changerCodeReduction(this.client.getNom(), this.client.getPrenom(), 80);
		assertEquals(80,this.client.getReduc());
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#chercherClient(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testChercherClient() {
		assertEquals(this.client, this.mediatheque.chercherClient(this.client.getNom(), this.client.getPrenom()));
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#listerClients()}.
	 */
	@Test
	public void testListerClients() {
		this.mediatheque.listerClients();
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#existeClient(mediatheque.client.CategorieClient)}.
	 */
	@Test
	public void testExisteClient() {
		assertEquals(true, this.mediatheque.existeClient(this.categorieClient));
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#getClientAt(int)}.
	 */
	@Test
	public void testGetClientAt() {
		assertEquals(this.client, this.mediatheque.getClientAt(0));
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#getClientsSize()}.
	 */
	@Test
	public void testGetClientsSize() {
		assertEquals(1,this.mediatheque.getClientsSize());
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#findClient(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testFindClient() {
		assertEquals(this.client, this.mediatheque.findClient(this.client.getNom(), this.client.getPrenom()));
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#afficherStatistiques()}.
	 */
	@Test
	public void testAfficherStatistiques() {
		this.mediatheque.afficherStatistiques();
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#getNom()}.
	 */
	@Test
	public void testGetNom() {
		assertEquals("AP-NB", this.mediatheque.getNom());
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#initFromFile()}.
	 */
	@Test
	public void testInitFromFile() {
		
		Mediatheque save = new Mediatheque("Test Save");
		assertEquals(true, save.initFromFile());
	}

	/**
	 * Test method for {@link mediatheque.Mediatheque#saveToFile()}.
	 */
	@Test
	public void testSaveToFile() {
		
		Mediatheque save = new Mediatheque("Test Save");
		assertEquals(true,save.saveToFile());
	}

}
