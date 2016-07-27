package by.codexsoft.task.dao;

import java.sql.SQLException;
import java.util.List;

import by.codexsoft.task.entity.Comment;

public interface CommentDAO {

	public void addComment(Comment comment) throws SQLException;

	public void editComment(Comment comment) throws SQLException;

	public void deleteComment(Comment comment) throws SQLException;
	
	public Comment getComment(int commentId) throws SQLException;

	public List<Comment> getComments(int taskId) throws SQLException;

}
