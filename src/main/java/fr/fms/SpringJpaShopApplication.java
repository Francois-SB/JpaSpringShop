package fr.fms;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.business.BusinessImpl;
import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class SpringJpaShopApplication implements CommandLineRunner {
	public static final String TEXT_BLUE = "\u001B[36m";
//	@Autowired
//	private CategoryRepository categoryRepository;
//
//	@Autowired
//	private ArticleRepository articleRepository;

	@Autowired
	private BusinessImpl business;// = new BusinessImpl()

	private static Scanner scan = new Scanner(System.in); 
//	private static BusinessImpl business = new BusinessImpl();
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJpaShopApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
//		Scanner scan = new Scanner(System.in);
		String scanTmp;
		//		BusinessImpl business = new BusinessImpl();
		//		Category smartphone = categoryRepository.save(new Category("Smartphone"));
		//		Category tablet = categoryRepository.save(new Category("Tablet"));
		//		Category pc = categoryRepository.save(new Category("PC"));
		//		articleRepository.save(new Article("I10", "Apple", 250, smartphone));
		//		articleRepository.save(new Article("I11", "Apple", 250, smartphone));
		//		articleRepository.save(new Article("I12", "Apple", 350, smartphone));
		//		articleRepository.save(new Article("S9", "Samsung", 250, smartphone));
		//		articleRepository.save(new Article("S10", "Samsung", 350, smartphone));
		//		
		//		articleRepository.save(new Article("GalaxyTab", "Samsung", 450, tablet));
		//		articleRepository.save(new Article("Ipad", "Apple", 350, tablet));
		//		
		//		articleRepository.save(new Article("R510", "Asus", 500, pc));
		System.out.println("Bonjour et bienvenu dans ma boutique, voici la liste d'articles en stock\n");
		displayArticles();
		int choice = 0;
		while(choice < 11) {
			displayMenu();
			scanTmp = scan.nextLine();
			choice = Integer.parseInt(scanTmp);
			switch(choice) {
			case 1 : displayOneArticle();				
			break;					
			case 2 : System.out.println("addArticle"); //addArticle();
			break;					
			case 3 : System.out.println("removeArticle");//removeArticle();
			break;					
			case 4 : System.out.println("modifyArticle");//modifyArticle();
			break;						
			case 5 : System.out.println("displayArticlesByCategoryId");//displayArticlesByCategoryId;
			break;
			case 6 : displayCategories();//displayCategories();
			break;
			case 7 : System.out.println("displayOneCategory");//displayOneCategory();
			break;
			case 8 : System.out.println("addCategory");//addCategory();
			break;	
			case 9 : System.out.println("removeCategory");//removeCategory();
			break;
			case 10: System.out.println("modifyCategory");//modifyCategory();
			break;
			case 11 : System.out.println("à bientôt dans notre boutique :)");
			break;
			default : System.out.println("veuillez saisir une valeur entre 1 et 8");
			}
		}
	}


	public static void displayMenu() {
		System.out.print(TEXT_BLUE + "Menu");
		System.out.println("\n" + "Pour réaliser une action, tapez le code correspondant");
		System.out.println("1 : Afficher un article");
		System.out.println("2 : Ajouter un article en base");
		System.out.println("3 : Retirer un article en base");
		System.out.println("4 : Modifier un article en base");
		System.out.println("5 : Afficher tous les articles d'une categorie");
		System.out.println("6 : Afficher toutes les catégories en base");
		System.out.println("7 : Afficher une categorie");
		System.out.println("8 : Ajouter une categorie en base");
		System.out.println("9 : Retirer une categorie en base");
		System.out.println("10 : Modifier une categorie en base");
		System.out.println("11 : sortir de l'application");
	}
	private void displayOneArticle() {
		// TODO Auto-generated method stub
		
	}
	private void displayArticles() {
		for(Article article : business.readArticles()) {
			System.out.println(article);
		}

	}
	/**
	 * Méthode qui affiche toutes les catégories
	 */
	private void displayCategories() {
		for(Category category : business.readCategories()) {
			System.out.println(category);
		}	
	}
}