package fr.fms.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
@Service
public class BusinessImpl implements Business{
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	private HashMap<Integer,Article> tempMap;
	
	public BusinessImpl() {
		this.tempMap = new HashMap<Integer,Article>();
	}
	
	@Override
	public ArrayList<Article> readArticles() {
		return (ArrayList<Article>) articleRepository.findAll();
	}

	@Override
	public ArrayList<Article> readArticlesByCatId(long idCat) {
		return (ArrayList<Article>) articleRepository.findByCategoryId(idCat);
	}

	@Override
	public Article readOneArticle(long id) {
		Optional<Article> optional = articleRepository.findById(id);
		return optional.get();
	}

	@Override
	public boolean addOneArticle(Article article) {
		try {
			articleRepository.save(article);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateOneArticle(Article article) {
		try {
			articleRepository.save(article);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteOneArticle(Article article) {
		try {
			articleRepository.delete(article);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Category readOneCategory(long id) {
		Optional<Category> optional = categoryRepository.findById(id);
		return optional.get();
	}

	@Override
	public boolean addOneCategory(Category category) {
		try {
			categoryRepository.save(category);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateOneCategory(Category category) {
		try {
			categoryRepository.save(category);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteOneCategory(Category category) {
		try {
			categoryRepository.delete(category);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
