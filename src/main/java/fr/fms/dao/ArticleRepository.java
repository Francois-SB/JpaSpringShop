package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.fms.entities.Article;
import fr.fms.entities.Category;

public interface ArticleRepository extends JpaRepository<Article,Long>{
	
	public List<Article> findByBrand(String brand);
	public List<Article> findByBrandContains(String brand);
	public List<Article> findByBrandAndPrice(String brand, double price);
	@Query("select A from Article A where A.brand like %:x% and A.price > :y")
	public List<Article> searchArticles(@Param("x") String kw, @Param("y") double price);
	public List<Article> findByBrandContainingAndPriceGreaterThan(String brand, double price);
	
	public List<Article> findByCategoryId(Long categoryId);
	
	//findByCategory @query innerJoin
	@Query("select A from Article A INNER JOIN Category C ON A.category = C.id where C.name like %:x%")
	public List<Article> findByCategory(@Param("x") String cat);
	
	//1.3
	public List<Article> findByDescriptionContainingAndBrandContaining(String description, String brand);
	//1.5 bis TODO error Executing an update/delete query => may not be possible
//	@Modifying(clearAutomatically = true)
//	@Query("UPDATE Article A SET A.description = :#{#x.description}, A.brand = :#{#x.brand}, A.price = :#{#x.price} WHERE A.id = :#{#x.id}")
//	public void oioioi(@Param("x") Article atr);
}
