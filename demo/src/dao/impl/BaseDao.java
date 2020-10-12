package dao.impl;

import dao.IBaseDao;
import exception.DataBaseException;
import model.*;
import org.apache.commons.collections.CollectionUtils;
import util.DateUtils;
import util.FileUtils;
import util.JDBCUtils;

import java.lang.reflect.*;
import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BaseDao<T> implements IBaseDao<T> {


    @Override
    public T selectObj(String sql) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        T model = null;
        try {
            Class modelClass = this.getGenericType();

            conn = (Connection) JDBCUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            List<String> columnNameList = getColumnNameList(rs);

            int i =0 ;
            while (rs.next()) {
                if(i==1){
                    throw new DataBaseException("亲，您搞错了，查询出来有多条记录");
                }
                model = (T) modelClass.getConstructor().newInstance();
                for (String columnName : columnNameList) {

                    String curSetMethod = getCurSetMethod(columnName);
                    Class paraType = getCurSetType(columnName, modelClass);

                    Method method = modelClass.getDeclaredMethod(curSetMethod, paraType);
                    method.invoke(model, getValueFromDatabase(rs, modelClass, columnName));
                }
                i++;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt, conn);
        }

        return model;
    }

    @Override
    public List<T> selectMulObj(String sql) {

        List<T> list = new LinkedList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = (Connection) JDBCUtils.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery(sql);

            Class modelClass = this.getGenericType();
            List<String> columnNameList = getColumnNameList(rs);

            while (rs.next()) {

                T model = (T) modelClass.getConstructor().newInstance();

                for (String columnName : columnNameList) {

                    String curSetMethod = getCurSetMethod(columnName);
                    Class paraType = getCurSetType(columnName, modelClass);

                    Method method = modelClass.getDeclaredMethod(curSetMethod, paraType);
                    method.invoke(model, getValueFromDatabase(rs, modelClass, columnName));
                }

                list.add(model);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt, conn);
        }

        return list;
    }

    @Override
    public List<T> selectByAndInfo(T obj) {

        return null;
    }

    public T selectById(Object id) {
        return null;
    }

    @Override
    public List<T> selectById(List<Object> idList) {

        if (CollectionUtils.isEmpty(idList)) {
            return null;
        }

        Class objGenericClass = null;
        try {
            objGenericClass = this.getGenericType();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sql = new StringBuilder();
        sql.append("select * from ").append(getTableName(objGenericClass)).append("where id IN (");

        for (int i = 0; i < idList.size(); i++) {
            if (i != 0) {
                sql.append(", ");
            }
            sql.append(idList.get(i));
        }
        sql.append(")");

        List<T> list = this.selectMulObj(sql.toString());

        return list;
    }


    @Override
    public int insertObj(T obj) {

        if (obj == null) {
            return 0;
        }
        int insertLines = 0;

        Class objGenericClass = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            objGenericClass = this.getGenericType();
            List<String> objNotNullAttr = getObjNotNullFields(obj);
            List<String> tbFieldsName = getTableFieldsName(objNotNullAttr);

            StringBuilder sql = new StringBuilder();
            sql.append("insert into ").append(getTableName(objGenericClass)).append(" (");

            for (int i = 0; i < tbFieldsName.size(); i++) {

                if (i != 0) {
                    sql.append(", ");
                }
                sql.append(tbFieldsName.get(i));
            }

            sql.append(") values (");

            for (int i = 0; i < objNotNullAttr.size(); i++) {

                if (i != 0) {
                    sql.append(", ");
                }
                sql.append(FileUtils.handleFiledValue(getValueFromObj(obj, objNotNullAttr.get(i))));
            }
            sql.append(")");

            conn = (Connection) JDBCUtils.getConnection();
            stmt = conn.prepareStatement(sql.toString());
            insertLines = stmt.executeUpdate();

            if (insertLines > 0) {
                rs = stmt.getGeneratedKeys();

                while (rs.next()) {

                    String curSetMethod = getCurSetMethod("id");
                    Class paraType = getCurSetType("id", objGenericClass);

                    Method method = obj.getClass().getDeclaredMethod(curSetMethod, paraType);
                    method.invoke(obj, rs.getInt(1));

                }
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt, conn);
        }

        return insertLines;

    }

    @Override
    public int insertMulObj(List<T> objList) {

        if (CollectionUtils.isEmpty(objList)) {
            return 0;
        }

        int insertLines = 0;
        Class objGenericClass = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            objGenericClass = this.getGenericType();
            List<String> objAttriNameList = getObjFieldsName(objGenericClass);

            StringBuilder sql = new StringBuilder();
            sql.append("insert into ").append(concatSqlFields(objGenericClass)).append(" values");

            for (int i = 0; i < objList.size(); i++) {
                if (i != 0) {
                    sql.append(", ");
                }
                sql.append("(");

                for (int j = 0; j < objAttriNameList.size(); j++) {

                    if (j != 0) {
                        sql.append(", ");
                    }
                    sql.append(FileUtils.handleFiledValue(getValueFromObj(objList.get(i), objAttriNameList.get(j))));
                }
                sql.append(")");
            }

            conn = (Connection) JDBCUtils.getConnection();
            stmt = conn.prepareStatement(sql.toString());
            insertLines = stmt.executeUpdate();

            if (insertLines > 0) {
                rs = stmt.getGeneratedKeys();

                while (rs.next()) {
                    for (int i = 0; i < objList.size(); i++) {

                        String curSetMethod = getCurSetMethod("id");
                        Class paraType = getCurSetType("id", objGenericClass);

                        Method method = objList.get(i).getClass().getDeclaredMethod(curSetMethod, paraType);
                        method.invoke(objList.get(i), rs.getInt(1));
                        rs.next();

                    }
                }
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt,conn);
        }

        return insertLines;
    }

    @Override
    public int deleteObjById(List<Integer> idList) {

        if (CollectionUtils.isEmpty(idList)) {
            return 0;
        }

        int deleteRows = 0;
        Class objGenericClass = null;

        objGenericClass = this.getGenericType();

        StringBuilder sql = new StringBuilder();
        sql.append("delete from ").append(getTableName(objGenericClass)).append(" where id ");

        if (idList.size() == 1) {
            sql.append("= ").append(idList.get(0));
        } else {
            sql.append("in (");
            for (int i = 0; i < idList.size(); i++) {

                if (i != 0) {
                    sql.append(", ");
                }
                sql.append(idList.get(i));
            }
            sql.append(")");
        }

        deleteRows = executeUpdateSql(sql.toString());

        return deleteRows;
    }

    @Override
    public int deleteObjById(Integer id) {

        if (id == null) {
            return 0;
        }
        int deleteRows = 0;
        Class objGenericClass = null;

        objGenericClass = this.getGenericType();

        StringBuilder sql = new StringBuilder();
        sql.append("delete from ").append(getTableName(objGenericClass)).append(" where id = ").append(id);

        deleteRows = executeUpdateSql(sql.toString());

        return deleteRows;
    }

    @Override
    public int updateObjById(T obj) {
        int updateRows = 0;

        if (obj == null) {
            return 0;
        }
        Class objGenericClass = this.getGenericType();

        StringBuilder sql = new StringBuilder();
        sql.append("update ").append(getTableName(objGenericClass)).append(" set ");

        List<String> objNotNullFields = getObjNotNullFields(obj);

        for (int i = 0; i < objNotNullFields.size(); i++) {
            if (i != 0) {
                sql.append(", ");
            }

            sql.append(FileUtils.objAttriConvertTotbField(objNotNullFields.get(i)));
            sql.append("=");
            sql.append(FileUtils.handleFiledValue(getValueFromObj(obj, objNotNullFields.get(i))));
        }
        sql.append(" where id = ").append(getValueFromObj(obj, "id"));

        updateRows = BaseDao.executeUpdateSql(sql.toString());

        return updateRows;
    }






    private static void executeSql(String sql) {
        Connection conn = null;
        Statement stmt = null;
        boolean result = false;
        try {
            conn = (Connection) JDBCUtils.getConnection();
            stmt = conn.createStatement();
            result = stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(stmt, conn);
        }
    }

    private static int executeUpdateSql(String sql) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int affectedRows = 0;

        try {
            conn = (Connection) JDBCUtils.getConnection();
            stmt = conn.prepareStatement(sql);
            affectedRows = stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(stmt, conn);
        }
        return affectedRows;
    }

    private static <T> T getValueFromObj(T t, String attrName) {
        T value = null;

        try {
            String curGetMethod = getCurGetMethod(attrName);

            Method method = t.getClass().getDeclaredMethod(curGetMethod);
            value = (T) method.invoke(t);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    private static <T> List<String> getObjNotNullFields(T t) {

        List<String> fieldNameList = null;
        try {
            fieldNameList = getObjFieldsName(t.getClass());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> notNullFields = new LinkedList<>();

        for (String fieldName : fieldNameList) {
            if (getValueFromObj(t, fieldName) != null) {
                notNullFields.add(fieldName);
            }
        }
        return notNullFields;
    }

    private static <T> List<String> getObjFieldsName(Class<T> clazz) throws SQLException {

        List<String> fieldNameList = new LinkedList<>();

        Field[] fieldList = clazz.getDeclaredFields();
        for (Field field : fieldList) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            fieldNameList.add(field.getName());
        }

        return fieldNameList;
    }

    private static <T> StringBuilder concatSqlFields(Class<T> clazz) throws SQLException {

        StringBuilder sb = new StringBuilder();
        String tableName = getTableName(clazz);
        sb.append(tableName).append("(");

        List<String> objFieldsName = getObjFieldsName(clazz);
        List<String> tbFieldsName = getTableFieldsName(objFieldsName);

        for (int i = 0; i < objFieldsName.size(); i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(tbFieldsName.get(i));
        }

        sb.append(") ");
        return sb;
    }

    private static String getCurSetMethod(String fieldName) {
        StringBuilder methodName = new StringBuilder();
        methodName.append("set");

        String lowerCamel = FileUtils.underscoreToLowerCamel(fieldName);
        methodName.append(FileUtils.lowerCamelToUpperCamel(lowerCamel));

        return methodName.toString();
    }

    private static String getCurGetMethod(String attriName) {
        StringBuilder methodName = new StringBuilder();
        methodName.append("get");

        methodName.append(FileUtils.lowerCamelToUpperCamel(attriName));

        return methodName.toString();
    }

    private static Class getCurSetType(String fieldName, Class clazz) {
        Field field = null;

        try {
            field = clazz.getDeclaredField(FileUtils.underscoreToLowerCamel(fieldName));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return field.getType();
    }

    private Class getGenericType() {

        Class modelClass = null;
        Type typeGeneric = this.getClass().getGenericSuperclass();

        ParameterizedType p = (ParameterizedType) typeGeneric;
        Type[] typeActualArg = p.getActualTypeArguments();

        modelClass = (Class) typeActualArg[0];


        return modelClass;
    }

    private static List<String> getColumnNameList(ResultSet rs) {

        int columnCount = 0;
        List<String> columnNameList = new LinkedList<>();

        try {
            columnCount = rs.getMetaData().getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                columnNameList.add(rs.getMetaData().getColumnName(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return columnNameList;
    }

    private static <T> String getTableName(Class<T> clazz) {

        if (Customer.class == clazz) {
            return "customer";
        }
        if (Merchant.class == clazz) {
            return "merchant";
        }
        if (Product.class == clazz) {
            return "product";
        }
        if (Order.class == clazz) {
            return "`order`";
        }
        if (OrderDetails.class == clazz) {
            return "order_details";
        }
        return null;
    }

    private static List<String> getTableFieldsName(List<String> objFieldsName) {
        List<String> tbFieldsName = new LinkedList<>();

        for (String objField : objFieldsName) {
            tbFieldsName.add(FileUtils.objAttriConvertTotbField(objField));
        }

        return tbFieldsName;
    }

    private static <T> Object getValueFromDatabase(ResultSet rs, Class<T> clazz, String fieldName) {

        Object result = null;
        Class paraType = getCurSetType(fieldName, clazz);

        try {
            if (Integer.class == paraType) {
                result = rs.getInt(fieldName);
            }
            if (Double.class == paraType) {
                result = rs.getDouble(fieldName);
            }
            if (String.class == paraType) {
                result = rs.getString(fieldName);
            }
            if (Date.class == paraType) {
                result = rs.getDate(fieldName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
