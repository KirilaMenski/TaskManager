package by.codexsoft.task.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.codexsoft.task.dao.CommentDAO;
import by.codexsoft.task.entity.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addComment(Comment comment) throws SQLException {
		currentSession().save(comment);
	}

	@Override
	public void editComment(Comment comment) throws SQLException {
		currentSession().update(comment);
	}

	@Override
	public void deleteComment(Comment comment) throws SQLException {
		currentSession().delete(comment);
	}

	@Override
	public List<Comment> getComments(int taskId) throws SQLException {
		List<Comment> comments = currentSession().createQuery("FROM Comment c WHERE c.taskId = :id")
				.setParameter("id", taskId).list();
		return comments;
	}

	@Override
	public Comment getComment(int commentId) throws SQLException {
		Comment comment = (Comment) currentSession().get(Comment.class, commentId);
		return comment;
	}

	public Session currentSession() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession;
	}

}
