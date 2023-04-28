package com.petdogui.repository;

import com.petdogui.model.Animal;
import com.petdogui.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {

    private static final String INSERT = "INSERT INTO animal (nome, idade, dono_id) VALUES(?, ?, ?);";
    private static final String UPDATE = "UPDATE animal SET nome=?, idade=?, dono_id=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM animal WHERE id=?;";
    private static final String FIND_BY_ID = "SELECT id, nome, idade, dono_id FROM animal WHERE id=?;";
    private static final String FIND_ALL = "SELECT id, nome, idade, dono_id FROM animal;";

    public Animal insert(Animal animal) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, animal.getNome());
            ps.setInt(2, animal.getIdade());
            ps.setInt(3, animal.getDonoId());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                animal.setId(rs.getInt(1));
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return animal;
    }

    public Animal update(Animal animal) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, animal.getNome());
            ps.setInt(2, animal.getIdade());
            ps.setInt(3, animal.getDonoId());
            ps.setInt(4, animal.getId());
            ps.executeUpdate();

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return animal;
    }

    public void delete(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Animal findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Animal animal = null;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setNome(rs.getString("nome"));
                animal.setIdade(rs.getInt("idade"));
                animal.setDonoId(rs.getInt("dono_id"));
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return animal;
    }

    public List<Animal> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Animal> animals = new ArrayList<>();

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setNome(rs.getString("nome"));
                animal.setIdade(rs.getInt("idade"));
                animal.setDonoId(rs.getInt("dono_id"));

                animals.add(animal);
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return animals;
    }
}