package tests;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import mediatheque.FicheEmprunt;
import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.Mediatheque;
import mediatheque.OperationImpossible;
import mediatheque.client.CategorieClient;
import mediatheque.client.Client;
import mediatheque.document.Livre;
import util.InvariantBroken;

public class ClientTest {

	private String nom;
	private String prenom;
	private String adresse;
	private CategorieClient catClient;
	private int code;
	
	private Mediatheque mediatheque;
	
	private FicheEmprunt fiche;
	
	private Livre doc;
	
	private Client client;
	
	
	@Before 
	public void setUp() throws OperationImpossible, InvariantBroken{
		this.nom = "me Rollin, they Hatin";
		this.prenom = "Daisy";
		this.adresse = "ici";
		this.catClient = new CategorieClient("Canard");
		this.code = 69;
		
		this.client = new Client(this.nom, this.prenom, this.adresse, this.catClient);
		
		this.mediatheque = new Mediatheque("Media");
		
		this.doc = new Livre("69", new Localisation("ici", "la bas"), "doc", "moi", "-42", new Genre("TP"), 18);
		this.doc.metEmpruntable();
		
	}
	
	@Test(expected=OperationImpossible.class)
	public void testConstructeurs() throws OperationImpossible {
		
		assertEquals(this.nom, new Client(this.nom, this.prenom, this.adresse, this.catClient, this.code).getNom());
		assertEquals(this.client.getNom(),new Client(this.nom, this.prenom, this.adresse, this.catClient).getNom());
		assertEquals(this.client.getNom(), new Client(this.nom,this.prenom).getNom());
		
		assertEquals(this.nom, new Client(null, null, null, null).getNom());
		
		this.catClient.modifierCodeReducActif(true);
		
		assertEquals(this.nom, new Client(this.nom, this.prenom, this.adresse, this.catClient, this.code).getNom());
		assertEquals(this.client.getNom(),new Client(this.nom, this.prenom, this.adresse, this.catClient).getNom());
			
	}
	
	@Test
	public void testSetters() throws OperationImpossible{
		
		this.client.setCategorie(this.catClient);
		this.client.setAddresse(this.adresse);
		this.client.setNom(this.nom);
		this.client.setPrenom(this.prenom);
		this.client.setReduc(99);
		this.catClient.modifierCodeReducActif(true);
		this.client.setCategorie(this.catClient,0);
	}
	
	@Test
	public void testGetters(){
		assertEquals(this.nom, this.client.getNom());
		assertEquals(this.prenom, this.client.getPrenom());
		assertEquals(this.adresse, this.client.getAdresse());
		assertEquals(this.catClient, this.client.getCategorie());
		
		assertEquals(0, this.client.getNbEmpruntsEffectues());
		assertEquals(0, this.client.getNbEmpruntsEnCours());
		assertEquals(0, this.client.getNbEmpruntsEnRetard());
		assertEquals(0, Client.getStat());
		assertEquals(0, Client.getnbEmpruntsTotal());
		
		assertEquals(0, this.client.getCoefTarif(), 0.000001f);
		assertEquals(0, this.client.getCoefDuree(), 0.000001f);
		assertEquals(0, this.client.getReduc());
			
	}
	
	
	
	@Test
	public void testEmprunter() throws OperationImpossible, InvariantBroken{
		
		assertEquals(false, this.client.aDesEmpruntsEnCours());
		assertEquals(true, this.client.peutEmprunter());
		
		this.fiche = new FicheEmprunt(this.mediatheque, this.client, this.doc);
		
		this.client.emprunter(this.fiche);
		this.client.emprunter();
		
		assertEquals(true, this.client.aDesEmpruntsEnCours());
		assertEquals(false, this.client.peutEmprunter());
		
		this.client.restituer(this.fiche);
		
		this.client.marquer();
		assertEquals(false, this.client.peutEmprunter());
		
		this.client.restituer(true);
	}
	
	
		
}
