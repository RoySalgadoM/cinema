package mx.edu.utez.model.category;

import mx.edu.utez.model.movie.Movie;
import mx.edu.utez.util.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCategory {
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;
    public boolean categories(boolean isCreate, Category category, int id){
        boolean state = false;
        try {
            con = ConnectionMysql.getConnection();
            if(isCreate){
                String query = "insert into category(name)" +
                        " values(?); ";
                pstm = con.prepareStatement(query);
                pstm.setString(1,category.getName());
                state = pstm.executeUpdate()==1;
            }else{
                String query = "update category set name=?" +
                        " where id=?;";
                pstm = con.prepareStatement(query);
                pstm.setString(1,category.getName());
                pstm.setInt(2,id);
                state = pstm.executeUpdate()==1;
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        String query = "select id, name from category;";
        try {
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection();
        }
        return categories;
    }
    public Category findCategoryById(int id) {
        String query = "select id, name from category where id=?;";
        Category category = new Category();
        try {
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            pstm.setInt(1,id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection();
        }
        return category;
    }
    public boolean deleteCategory(int id){
        boolean state = false;
        String query = "delete from category where id = ?;";
        try{
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            pstm.setInt(1,id);
            state = pstm.executeUpdate()==1;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }
    public void closeConnection(){
        try{
            if(con != null){
                con.close();
            }
            if(pstm != null){
                pstm.close();
            }
            if(rs != null){
                rs.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
