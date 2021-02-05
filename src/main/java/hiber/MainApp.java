package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      userService.cleanupTable();
      Car car1 = new Car("Ferrari", 12);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru",car1);
      userService.add(user1);

      Car car2 = new Car("Lada", 33);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru",car2);
      userService.add(user2);

      Car car3 = new Car("Honda", 25);
      User user3 = new User("User3", "Lastname3", "user3@mail.ru",car3);
      userService.add(user3);

      Car car4 = new Car("Mazda", 7);
      User user4 = new User("User4", "Lastname4", "user4@mail.ru",car4);
      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      try{
         System.out.println(userService.getUserByCarDetails("Lada", 33));
         System.out.println(userService.getUserByCarDetails("Vesta", 32));
      }catch (NoResultException nre){
         System.out.println("Sorry, we don't have such users.");
      }


      context.close();
   }
}
