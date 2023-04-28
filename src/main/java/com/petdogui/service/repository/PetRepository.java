package com.petdogui.service.repository;

import com.petdogui.model.Dono;
import com.petdogui.model.Pet;
import com.petdogui.service.repository.DonoRepository;
import com.petdogui.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PetRepository {

    private static final String INSERT = "INSERT INTO pet (nome, dono_id) VALUES(?, ?);";
    private static final String UPDATE = "UPDATE pet SET nome=?, dono_id=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM pet WHERE id=?;";
    private static final String FIND_BY_ID = "SELECT id, nome, dono_id FROM pet WHERE id=?;";
    private static final String FIND_ALL = "SELECT id, nome, dono_id FROM pet;";

    public Pet insert(Pet pet) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pet.getNome());
            ps.setInt(2, (int) pet.getDono().getId());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                pet.setId(rs.getInt(1));
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

        return pet;
    }

    public Pet update(Pet pet) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, pet.getNome());
            ps.setInt(2, (int) pet.getDono().getId());
            ps.setInt(3, pet.getId());
            ps.executeUpdate();

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return pet;
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

    public Pet findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pet pet = null;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                pet = new Pet();
                pet.setId(rs.getInt("id"));
                pet.setNome(rs.getString("nome"));
                // Retrieve and set the dono object using the dono_id from the result set
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

        return pet;
    }

    public List<Pet> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List <Pet> pets = new ArrayList<>();

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pet pet = new Pet();
                pet.setId(rs.getInt("id"));
                pet.setNome(rs.getString("nome"));

                // Retrieve and set the dono object using the dono_id from the result set
                int donoId = rs.getInt("dono_id");
                DonoRepository donoRepository = new DonoRepository();
                Dono dono = donoRepository.findById(donoId);
                pet.setDono(dono);

                pets.add(pet);
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

        return pets;
    }
}

