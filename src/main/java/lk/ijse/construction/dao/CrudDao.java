package lk.ijse.construction.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T,ID> extends SuperDao{
    List<T> getAll() throws SQLException, ClassNotFoundException;

    T get() throws SQLException, ClassNotFoundException;

    boolean save(T dto) throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

    boolean exists(T t) throws SQLException, ClassNotFoundException;

    boolean delete(ID id) throws SQLException, ClassNotFoundException;

    ID getId() throws SQLException, ClassNotFoundException;
}
