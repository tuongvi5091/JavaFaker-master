package tam.dev.data.seeder;

import java.util.Random;

import com.github.javafaker.Faker;

import tam.dev.data.dao.ProductDao;
import tam.dev.data.model.Product;

public class ProductSeeder {
	 	private Faker faker;
	    private Random random;
	    private ProductDao productDao;

	    public ProductSeeder(ProductDao productDao) {
	        this.faker = new Faker();
	        this.random = new Random();
	        this.productDao = productDao;
	    }

	    public void seedProducts(int count) {
	        for (int i = 0; i < count; i++) {
	            String name = faker.commerce().productName();
	            String description = faker.lorem().sentence();
	            String thumbnail = faker.internet().avatar();
	            double price = Double.parseDouble(faker.commerce().price());
	            int quantity = random.nextInt(100) + 1;
	            int categoryId = random.nextInt(10) + 1;

	            Product product = new Product(name, description, thumbnail, price, quantity, categoryId);
	            productDao.insert(product);
	        }
	    }
}
