package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void cleanupTable() {
      sessionFactory.getCurrentSession().createQuery("delete User").executeUpdate();
   }

   @Override
   public User getUserByCarDetails(String model, int series) {
      Query<User> query =  sessionFactory.getCurrentSession().createQuery(
              "from User u where u.car.model =: m and u.car.series =: s");
      query.setParameter("m", model);
      query.setParameter("s", series);
      return query.getSingleResult();
   }

}
