/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;

/**
 * @author Alexis
 *
 */
public class GenreTest {
	
	private String name;
	private String rename;
	
	private Genre genre;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.name = "Corde";
		this.rename = "Tabouret";
		
		this.genre = new Genre(this.name);
		this.genre.emprunter();
		this.genre.modifier(this.rename);
	}
	
	@Test
	public void testGenre() {
		
		assertEquals(this.rename, this.genre.getNom());
		assertEquals(2,this.genre.getNbEmprunts());
		
	}

}
