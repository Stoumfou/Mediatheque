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
import mediatheque.OperationImpossible;
import mediatheque.document.Video;
import util.InvariantBroken;

/**
 * @author Alexis
 *
 */
public class VideoTest {

	private Video film; 
	private Video film2;
    private int dureeFilm;
    private String mentionLegale;

    
    private String code;
    private Localisation localisation;
    private String titre;
    private String auteur;
    private String annee;
    private Genre genre;
    
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.dureeFilm = 130;
		this.mentionLegale = "Film Créative Commons";
		this.code = "53455345";
		this.localisation = new Localisation("404", "42");
		this.titre="Je suis un film";
		this.auteur="Alexis Pambourg et Nicolas Bouvet";
		this.annee="2016";
		this.genre= new Genre("Comédie");
		this.film = new Video(code, localisation, titre, auteur, annee, genre, dureeFilm, mentionLegale);
	
	}

	/**
	 * Test method for {@link mediatheque.document.Video#emprunter()}.
	 * @throws InvariantBroken 
	 * @throws OperationImpossible 
	 */
	@Test
	public void testEmprunter() throws OperationImpossible, InvariantBroken {
		this.film.metEmpruntable();
		assertEquals(true, this.film.emprunter());
	}

	/**
	 * Test method for {@link mediatheque.document.Video#getStat()}.
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testGetStat() {
		assertEquals(1, this.film.getStat());
	}

	/**
	 * Test method for {@link mediatheque.document.Video#dureeEmprunt()}.
	 */
	@Test
	public void testDureeEmprunt() {
		assertEquals(2*7, this.film.dureeEmprunt());
	}

	/**
	 * Test method for {@link mediatheque.document.Video#tarifEmprunt()}.
	 */
	@Test
	public void testTarifEmprunt() {
		assertEquals(1.5, this.film.tarifEmprunt(),1e-15);
	}

	/**
	 * Test method for {@link mediatheque.document.Video#getMentionLegale()}.
	 */
	@Test
	public void testGetMentionLegale() {
		assertEquals("Film Créative Commons", this.film.getMentionLegale());
	}

	/**
	 * Test method for {@link mediatheque.document.Video#getDureeFilm()}.
	 */
	@Test
	public void testGetDureeFilm() {
		assertEquals(130, this.film.getDureeFilm());
	}

	/**
	 * Test method for {@link mediatheque.document.Video#invariantVideo()}.
	 */
	@Test
	public void testInvariantVideo() {
		assertEquals(true, this.film.invariantVideo());
	}
	
	/**
	 * Test method for {@link mediatheque.document.Video#invariantVideo()}.
	 */
	@Test
	public final void testInvariantVideoDuree0() {
		this.dureeFilm = 0;
		assertEquals(this.film.invariant(), this.film.invariantVideo());
	}
	
	/**
	 * Test method for {@link mediatheque.document.Video#invariantVideo()}.
	 */
	@Test(expected = OperationImpossible.class)
	public final void testVideoMentionLegalNull() throws OperationImpossible, InvariantBroken {
			this.film2 = new Video(code, localisation, titre, auteur, annee, genre, dureeFilm, null);
	}

	/**
	 * Test method for {@link mediatheque.document.Video#invariantVideo()}.
	 */
	@Test(expected = OperationImpossible.class)
	public final void testVideoDuree0() throws OperationImpossible, InvariantBroken {
			this.film2 = new Video(code, localisation, titre, auteur, annee, genre, 0, mentionLegale);
	}

	/**
	 * Test method for {@link mediatheque.document.Video#invariantVideo()}.
	 */
	@Test(expected = InvariantBroken.class)
	public final void testVideoInvariantFalse() throws OperationImpossible, InvariantBroken {
			this.film2 = new Video(code, localisation, titre, auteur, annee, genre, -1, mentionLegale);
	}
}
