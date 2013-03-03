package com.eib.agenda.modelo.dao;

import java.util.Collection;

public abstract class AbstractDAO<T> {
    protected static final String SQL_DRIVER = "com.mysql.jdbc.Driver";
    protected static final String SQL_URL    = "jdbc:mysql://127.0.0.1/agenda";
    protected static final String SQL_USER   = "root";
    protected static final String SQL_PASS   = "root";
	
 	public abstract int insert(T arg1);
	public abstract int update(T arg1);
	public abstract int delete(T arg1);
	public abstract T select(int id);
	public abstract Collection<T> select();
}
