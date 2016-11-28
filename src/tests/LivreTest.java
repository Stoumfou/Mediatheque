/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import mediatheque.document.Livre;
import util.InvariantBroken;

/**
 * @author Alexis
 *
 */
public class LivreTest {
	
	private String code;
	private Localisation localisation;
	private String titre;
	private String auteur;
	private String annee;
	private Genre genre;
	private int nombrePage;
	
	private Livre livre;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.code = "azertyuiop";
		this.localisation = new Localisation("Sc204","2E");
		this.titre = "Comment faire des pommes au four";
		this.auteur = "Edouard Short";
		this.annee = "2016";
		this.genre = new Genre("Drama");
		this.nombrePage = 1;
		
		this.livre = new Livre(this.code, this.localisation, this.titre, this.auteur, this.annee, this.genre, this.nombrePage);

	}
	
	@Test
	public void testLivreGetters() throws InvariantBroken, OperationImpossible {
		
		assertEquals(this.code, this.livre.getCode());
		assertEquals(this.localisation, this.livre.getLocalisation());
		assertEquals(this.auteur, this.livre.getAuteur());
		assertEquals(this.annee, this.livre.getAnnee());
		assertEquals(this.titre, this.livre.getTitre());
		assertEquals(this.genre, this.livre.getGenre());	
	
	}
	
	@Test
	public void testLivre() throws InvariantBroken, OperationImpossible{
		
		this.livre.metEmpruntable();
		
		assertEquals(true, this.livre.emprunter());
		assertEquals(0, this.livre.getStat());
		assertEquals( 42, this.livre.dureeEmprunt());
		assertEquals(0.5f, this.livre.tarifEmprunt(), 0.00001f);
		assertEquals(true, this.livre.invariantLivre());
	}
	
	@Test(expected = OperationImpossible.class) 
	public void testLivreNbPageInf0() throws InvariantBroken, OperationImpossible{
		new Livre(code,localisation,titre,auteur,annee,genre,-1);
	}
	
	@Test(expected = InvariantBroken.class)
	public final void testVideoInvariantFalse() throws OperationImpossible, InvariantBroken {
			new Livre(code, localisation, titre, auteur, annee, genre, 0);
	}
}
