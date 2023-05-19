package fr.fms;

import java.util.Scanner;
import java.util.stream.Stream;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.business.Business;
import fr.fms.business.BusinessImpl;
import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class SpringJpaShopApplication implements CommandLineRunner {
	public static final String TEXT_BLUE = "\u001B[36m";
	public static final String TEXT_RESET = "\u001B[0m";	
	private static final String COLUMN_ID = "IDENTIFIANT";
	private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
	private static final String COLUMN_BRAND = "MARQUE";
	private static final String COLUMN_PRICE = "PRIX";
	private static final String COLUMN_NAME = "NAME";
//	@Autowired
//	private CategoryRepository categoryRepository;
//
//	@Autowired
//	private ArticleRepository articleRepository;

	@Autowired
	private BusinessImpl business;// = new BusinessImpl()

	private static Scanner scan = new Scanner(System.in); 
	private static String scanTmp;
//	private static BusinessImpl business = new BusinessImpl();
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJpaShopApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
//		Scanner scan = new Scanner(System.in);
		
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
			scan.nextLine();
			switch(choice) {
			case 0 : displayArticles();				
			break;
			case 1 : displayOneArticle();				
			break;					
			case 2 : addArticle(); //addArticle();
			break;					
			case 3 : removeArticle();//removeArticle();
			break;					
			case 4 : modifyArticle();//modifyArticle();
			break;						
			case 5 : displayArticlesByCategoryId();//displayArticlesByCategoryId;
			break;
			case 6 : displayCategories();//displayCategories();
			break;
			case 7 : displayOneCategory();//displayOneCategory();
			break;
			case 8 : addCategory();//addCategory();
			break;	
			case 9 : removeCategory();//removeCategory();
			break;
			case 10: modifyCategory();//modifyCategory();
			break;
			case 11 : System.out.println("à bientôt dans notre boutique :)");
			break;
			default : System.out.println("veuillez saisir une valeur entre 1 et 8");
			}
		}
		scan.close();
		
	}


	public static void displayMenu() {
		System.out.print(TEXT_BLUE + "Menu");
		System.out.println("\n" + "Pour réaliser une action, tapez le code correspondant");
		System.out.println("0 : display articles");
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
	private void displayOneArticle() {//TODO exception not working
		System.out.println("displayOneArticle");
		int id = scanInt();
		System.out.println("INT ID ; "+id);
		Article article = new Article();
		try {
			article = business.readOneArticle((long)id);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("Pas d'articles pour cet Id, veuillez réessayer");
		}
		System.out.println(article);
		
	}
	

	private void displayOneCategory() {//TODO pas de null return 
		int id = scanInt();
		System.out.println( business.readOneCategory((long)id)!=null ? business.readOneCategory((long)id) : "pas de cat pour cet id");
		
	}
	
	private void displayArticlesByCategoryId() {
		displayCategories();
		System.out.println("saisissez l'id de la catégorie concerné");
		int id = scanInt();
		Category category = business.readOneCategory(id);
		if(category != null) {
			System.out.printf("              AFFICHAGE PAR CATEGORIE    %n");
			System.out.printf("                     %-10s               %n",category.getName());
			System.out.printf("------------------------------------------------------------%n");
			System.out.printf("%-15s | %-15s | %-15s | %-15s %n",COLUMN_ID,COLUMN_DESCRIPTION,COLUMN_BRAND,COLUMN_PRICE);
			System.out.printf("------------------------------------------------------------%n");
			business.readArticlesByCatId(id).forEach( a -> System.out.printf("%-15s | %-15s | %-15s | %-15s%n",a.getId(),a.getDescription(),a.getBrand(), a.getPrice()));
		}
		else System.out.println("cette catégorie n'existe pas !");
		
	}
	private int scanInt() {
		try {//(Scanner scann = new Scanner(System.in))
			System.out.println("scanInt");
			scanTmp = scan.nextLine();
			System.out.println("Integer.parseInt(scanTmp) : "+Integer.parseInt(scanTmp));
		} catch (Exception e) {
			e.printStackTrace();
		}
		scan.nextLine();
		return Integer.parseInt(scanTmp);
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
		System.out.println();
	}
	/**
	 * Méthode qui ajoute un article au panier
	 */
	public void addArticle() {
		Article article = new Article();
		System.out.println("Description :");
		String descString = scan.nextLine();
		article.setDescription(descString);
		
		System.out.println("Marque :");
		String brandString = scan.nextLine();
		article.setBrand(brandString);
		
		System.out.println("Prix :");
		double priceString = Double.parseDouble(scan.nextLine());
		article.setPrice(priceString);
		
		for (Category category : business.readCategories()) {
			System.out.println(category);
		}
		System.out.println("Category :");
		int catInt = scanInt();
		Category newcategory = business.readOneCategory((long)catInt);
		article.setCategory(newcategory);
		//save TODO test save ok ?
		business.addOneArticle(article);
			
		
	}
	public void addCategory() {
		Category category = new Category();
		System.out.println("CatName :");
		String name = scan.nextLine();
		category.setName(name);

		//save TODO test save ok ?
		business.addOneCategory(category);

	}
	
	public void modifyArticle() {//TODO
		//select art
		System.out.println("Selectionner l'id de l'article à modifier");
		int id = scanInt();
		Article article = business.readOneArticle((long)id);
		if(article != null) {
			//modify
			System.out.println(article);
			System.out.println("1 - desc | 2 - marque | 3 - prix | 4 - categorie");
			//choix attribut à moif TODO
			String otherChange = "o";
			while (otherChange.equalsIgnoreCase("o")) {
		
				int change = scanInt();
					switch (change) {
					case 1:System.out.println("Description :");
					String descString = scan.nextLine();
					article.setDescription(descString);
						
						break;
						
					case 2:System.out.println("Marque :");
					String brandString = scan.nextLine();
					article.setBrand(brandString);
						
						break;
					case 3:System.out.println("Prix :");
					double priceString = Double.parseDouble(scan.nextLine());
					article.setPrice(priceString);
						
						break;
					case 4:for (Category category : business.readCategories()) {
						System.out.println(category);	
						}
						System.out.println("Category :");
						int catInt = scanInt();
						Category newcategory = business.readOneCategory((long)catInt);
						article.setCategory(newcategory);	
						break;
	
					default:System.out.println("Jacques ?");
						break;
					}
				System.out.println(article);
				System.out.println("Souhaitez vous effectuer une autre modification ? [o/n]");
				otherChange=scan.nextLine();
			}
			//maj bdd
			try {
				business.updateOneArticle(article);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else System.out.println("l'article que vous souhaitez ajouter n'existe pas, pb de saisi id");
		System.out.println(article+" MAJ done.");
	}
	
	public void modifyCategory() {//TODO
		//select art
		System.out.println("Selectionner l'id de la cat à modifier");
		int id = scanInt();
		Category category = business.readOneCategory((long)id);
		if(category != null) {
			//modify
			System.out.println(category);
			
			//choix attribut à moif TODO
			String otherChange = "n";
			while (otherChange.equalsIgnoreCase("n")) {

				System.out.println("Name :");
				String nameNew = scan.nextLine();
				category.setName(nameNew);

				System.out.println(category);
				System.out.println("Valider la modification ? [o/n]");
				otherChange=scan.nextLine();
			}
			//maj bdd
			try {
				business.updateOneCategory(category);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else System.out.println("l'article que vous souhaitez ajouter n'existe pas, pb de saisi id");
		System.out.println(category+" MAJ done.");
	}
	
	public void removeArticle() {
		System.out.println("Selectionner l'id de l'article à supprimer du panier");
		int id = scanInt();
		try {
			Article article = business.readOneArticle((long)id);
			business.deleteOneArticle(article);
			System.out.println("article d'id "+id+" supprimé");
			displayArticles();
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("Id non valide");
		}

	}
	public void removeCategory() {
		System.out.println("Selectionner l'id de la cat à supprimer du panier");
		int id = scanInt();
		try {
			Category category = business.readOneCategory((long)id);
			business.deleteOneCategory(category);
			//TODO confirmation
			System.out.println("article d'id "+id+" supprimé");
			displayArticles();
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("Id non valide");
		}

	}
}