package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;

public class GenreTest {

	private String name;
	private String rename;
	
	private Genre genre;
	
	@Before
	public void init(){
		this.name = "Corde";
		this.rename = "Tabouret";
		
		this.genre = new Genre(this.name);
		this.genre.emprunter();
		this.genre.modifier(this.rename);
	}
	
	@Test
	public void test() {
		
		assertEquals(this.rename, this.genre.getNom());
		assertEquals(2,this.genre.getNbEmprunts());
		
	}

}
