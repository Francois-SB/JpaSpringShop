package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.business.BusinessImpl;
//import fr.fms.business.IBusinessImpl;
import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class SpringJpaShopApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private BusinessImpl business;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJpaShopApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
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
		
		business.readArticles();
		System.out.println("in");
		for(Article article : business.readArticles()) {
			System.out.println(article);
		}
		}
}
