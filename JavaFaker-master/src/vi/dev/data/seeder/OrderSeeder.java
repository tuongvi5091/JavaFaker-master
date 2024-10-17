package tam.dev.data.seeder;

import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;

import tam.dev.data.dao.OrderDao;
import tam.dev.data.dao.UserDao;
import tam.dev.data.model.Order;

public class OrderSeeder {
	private Faker faker;
    private OrderDao orderDao;
    private List<Integer> existingUserIds;  
    private Random random;

    public OrderSeeder(OrderDao orderDao, UserDao userDao) {
        this.faker = new Faker();
        this.orderDao = orderDao;
        this.existingUserIds = userDao.getAllUserIds(); 
        this.random = new Random();
    }

    public void seedOrders(int count) {
        for (int i = 0; i < count; i++) {
            String code = faker.commerce().promotionCode();  
            String status = faker.options().option("Pending", "Finished");

            int userId = existingUserIds.get(random.nextInt(existingUserIds.size()));

            Order order = new Order(code, status, userId);
            orderDao.insert(order);
        }
    }
}
