package tam.dev.data.seeder;

import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;

import tam.dev.data.dao.OrderDao;
import tam.dev.data.dao.OrderItemDao;
import tam.dev.data.dao.ProductDao;
import tam.dev.data.model.OrderItem;

public class OrderItemSeeder {
	private Faker faker;
    private OrderItemDao orderItemDao;
    private List<Integer> existingOrderIds;  
    private List<Integer> existingProductIds;  
    private Random random;

    public OrderItemSeeder(OrderItemDao orderItemDao, OrderDao orderDao, ProductDao productDao) {
        this.faker = new Faker();
        this.orderItemDao = orderItemDao;
        this.existingOrderIds = orderDao.getAllOrderIds();  
        this.existingProductIds = productDao.getAllProductIds(); 
        this.random = new Random();
    }

    public void seedOrderItems(int count) {
        for (int i = 0; i < count; i++) {
            // Randomly generate order item details
            int quantity = faker.number().numberBetween(1, 10);  
            double price = faker.number().randomDouble(2, 20, 500);  

            int orderId = existingOrderIds.get(random.nextInt(existingOrderIds.size()));
            int productId = existingProductIds.get(random.nextInt(existingProductIds.size()));

           
            OrderItem orderItem = new OrderItem(quantity, price, orderId, productId);
            orderItemDao.insert(orderItem);  
        }
    }
}
