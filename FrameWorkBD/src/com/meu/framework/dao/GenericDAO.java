package com.meu.framework.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T>{
	
	//Está é uma interface para operações básicas do banco, no caso o CRUD.
	void create (T entity) throws SQLException;
	T read(int id) throws SQLException;
	void update(T entity) throws SQLException;
	void delete(int id) throws SQLException;
	List<T> getALL() throws SQLException;
}
