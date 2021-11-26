package mx.edu.utez.model.movie;

import mx.edu.utez.util.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoMovie {
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;
    public boolean movies(boolean isCreate, Movie movie, int idMovie){
        boolean state = false;
        try {
            con = ConnectionMysql.getConnection();
            if(isCreate){
                String query = "insert into movie(title,description,synopsis,rating,registered_date,updated_date,state,category)" +
                        " values(?,?,?,?,?,?,?,?); ";
                pstm = con.prepareStatement(query);
                pstm.setString(1,movie.getTitle());
                pstm.setString(2,movie.getDescription());
                pstm.setString(3,movie.getSynopsis());
                pstm.setInt(4,movie.getRating());
                pstm.setString(5,movie.getRegistered_date());
                pstm.setString(6,movie.getUpdated_date());
                pstm.setInt(7,movie.getState());
                pstm.setInt(8,movie.getCategory());
                state = pstm.executeUpdate()==1;
            }else{
                String query = "update movie set title=?,description=?,synopsis=?,rating=?,updated_date=?,state=?,category=?" +
                        " where id=?;";
                pstm = con.prepareStatement(query);
                pstm.setString(1,movie.getTitle());
                pstm.setString(2,movie.getDescription());
                pstm.setString(3,movie.getSynopsis());
                pstm.setInt(4,movie.getRating());
                pstm.setString(5,movie.getUpdated_date());
                pstm.setInt(6,movie.getState());
                pstm.setInt(7,movie.getCategory());
                pstm.setInt(8,idMovie);
                state = pstm.executeUpdate()==1;
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }
    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        String query = "select id, title,description,synopsis,rating,registered_date,updated_date,state, category from movie;";
        try {
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDescription(rs.getString("description"));
                movie.setSynopsis(rs.getString("synopsis"));
                movie.setRating(rs.getInt("rating"));
                movie.setRegistered_date(rs.getString("registered_date"));
                movie.setUpdated_date(rs.getString("updated_date"));
                movie.setState(rs.getInt("state"));
                movie.setCategory(rs.getInt("category"));
                movies.add(movie);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection();
        }
        return movies;
    }
    public Movie findMovieById(int id) {
        String query = "select id, title,description,synopsis,rating,registered_date,updated_date,state, category from movie where id=?;";
        Movie movie = new Movie();
        try {
            con = ConnectionMysql.getConnection();
            pstm = con.prepareStatement(query);
            pstm.setInt(1,id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDescription(rs.getString("description"));
                movie.setSynopsis(rs.getString("synopsis"));
                movie.setRating(rs.getInt("rating"));
                movie.setRegistered_date(rs.getString("registered_date"));
                movie.setUpdated_date(rs.getString("updated_date"));
                movie.setState(rs.getInt("state"));
                movie.setCategory(rs.getInt("category"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection();
        }
        return movie;
    }
    public boolean deleteMovie(int id){
        boolean state = false;
        String query = "delete from movie where id = ?;";
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

