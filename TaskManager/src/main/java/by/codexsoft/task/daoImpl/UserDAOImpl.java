package by.codexsoft.task.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.codexsoft.task.dao.UserDAO;
import by.codexsoft.task.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addUser(User user) throws SQLException {
		currentSession().save(user);

	}

	@Override
	public List<User> getDevelopers(String role) throws SQLException {
		List<User> developers = currentSession().createQuery(
				"FROM User u WHERE u.role = :role ORDER BY u.login"
//				"SELECT u FROM UserRole ur LEFT OUTER JOIN ur.role r LEFT OUTER JOIN ur.user u"
//				+ " WHERE ur.role.id = ur.role"
//				+ " AND ur.user = ur.user.id"
//				+ " AND ur.role.roleName = :role"
				)
				.setParameter("role", role).list();
		return developers;
	}

	@Override
	public User getUser(String login) throws SQLException {
//		User user = (User) currentSession().get(User.class, login);
//		return user;
		Criteria criteria = currentSession().createCriteria(User.class);
		criteria.add(Restrictions.like("login", login));
		return (User) criteria.uniqueResult();
	}

	public Session currentSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession;
	}

}
