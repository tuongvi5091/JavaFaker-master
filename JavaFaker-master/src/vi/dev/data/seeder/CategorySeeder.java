package tam.dev.data.seeder;

import com.github.javafaker.Faker;

import tam.dev.data.dao.CategoryDao;
import tam.dev.data.model.Category;

public class CategorySeeder {
	private Faker faker;
    private CategoryDao categoryDao;

    public CategorySeeder(CategoryDao categoryDao) {
        this.faker = new Faker();
        this.categoryDao = categoryDao;
    }
    public void seedCategories(int count) {
        for (int i = 0; i < count; i++) {
        	String name = faker.commerce().department(); 
            String description = faker.lorem().sentence();

            Category category = new Category(name, description);
            categoryDao.insert(category);
        }
    }
}
