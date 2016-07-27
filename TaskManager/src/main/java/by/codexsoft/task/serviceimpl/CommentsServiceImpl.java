package by.codexsoft.task.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.codexsoft.task.dao.CommentDAO;
import by.codexsoft.task.entity.Comment;
import by.codexsoft.task.service.CommentService;

@Service
public class CommentsServiceImpl implements CommentService {

	@Autowired
	private CommentDAO commentDAO;

	@Override
	@Transactional
	public void addComment(Comment comment) throws SQLException {
		commentDAO.addComment(comment);
	}

	@Override
	@Transactional
	public void editComment(Comment comment) throws SQLException {
		commentDAO.editComment(comment);
	}

	@Override
	@Transactional
	public void deleteComment(Comment comment) throws SQLException {
		commentDAO.deleteComment(comment);
	}

	@Override
	@Transactional
	public List<Comment> getComments(int taskId) throws SQLException {
		List<Comment> comments = commentDAO.getComments(taskId);
		return comments;
	}

	@Override
	@Transactional
	public Comment getComment(int commentId) throws SQLException {
		Comment comment = commentDAO.getComment(commentId);
		return comment;
	}

}
