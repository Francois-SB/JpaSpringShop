package fr.fms.business;

import java.util.ArrayList;

import fr.fms.entities.Article;
import fr.fms.entities.Category;

public interface Business {
	
	//-----------------------Article------------------------------
	/**
	 * méthode qui renvoi tous les articles de la table t_articles en bdd
	 * @return Liste d'articles en base
	 */
	public ArrayList<Article> readArticles();
	
	/**
	 * méthode qui renvoi tous les articles d'une catégorie
	 * @param id de la catégorie
	 * @return Liste d'articles
	 */
	public ArrayList<Article> readArticlesByCatId(long idCat);
	
	/**
	 * méthode renvoie l'article correspondant à l'id
	 * @param id de l'article à renvoyer
	 * @return article correspondant si trouvé, null sinon
	 */
	public Article readOneArticle(long id);
	
	/**
	 * méthode qui ajoute un article en bdd
	 * @param article à ajouter
	 * @return true si ok
	 */
	public boolean addOneArticle(Article article);	
	
	/**
	 * méthode qui maj un article en bdd
	 * @param article mis à jour
	 * @return true si ok
	 */
	public boolean updateOneArticle(Article article);	
	
	/**
	 * méthode qui supprime un article en bdd
	 * @param article à sppr
	 * @return true si ok
	 */
	public boolean deleteOneArticle(Article article);	
	
	//-----------------------Cat------------------------------
	/**
	 * méthode renvoie la categorie correspondant à l'id
	 * @param id de la categorie à renvoyer
	 * @return la categorie correspondant si trouvé, null sinon
	 */
	public Category readOneCategory(long id);
	
	/**
	 * méthode qui ajoute une categorie en bdd
	 * @param la categorie à ajouter
	 * @return true si ok
	 */
	public boolean addOneCategory(Category category);	
	
	/**
	 * méthode qui maj la categorie en bdd
	 * @param la categorie mis à jour
	 * @return true si ok
	 */
	public boolean updateOneCategory(Category category);	
	
	/**
	 * méthode qui supprime la categorie en bdd
	 * @param la categorie à sppr
	 * @return true si ok
	 */
	public boolean deleteOneCategory(Category category);

	ArrayList<Category> readCategories();	
}
