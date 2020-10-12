package dao;

import java.util.List;

public interface IBaseDao<T> {

    T selectObj(String sql);

    List<T> selectMulObj(String sql);

    List<T> selectByAndInfo(T obj);

    int insertMulObj(List<T> objList);

    int insertObj(T obj);

    int deleteObjById(List<Integer> idList);

    int deleteObjById(Integer id);

    int updateObjById(T obj);

    T selectById(Object id);

    List<T> selectById(List<Object> idList);

}
