package com.petdogui.service.repository;

import com.petdogui.model.Atendimento;
import com.petdogui.model.Pet;
import com.petdogui.service.repository.PetRepository;
import com.petdogui.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoRepository {

    private final PetRepository petRepository;

    public AtendimentoRepository() {
        this.petRepository = new PetRepository();
    }

    private static final String INSERT = "INSERT INTO atendimento (pet_id, data, descricao) VALUES (?, ?, ?);";
    private static final String UPDATE = "UPDATE atendimento SET pet_id=?, data=?, descricao=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM atendimento WHERE id=?;";
    private static final String FIND_BY_ID = "SELECT id, pet_id, data, descricao FROM atendimento WHERE id=?;";
    private static final String FIND_ALL = "SELECT id, pet_id, data, descricao FROM atendimento;";

    public Atendimento insert(Atendimento atendimento) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, atendimento.getPet().getId());
            ps.setDate(2, new java.sql.Date(atendimento.getData().getTime()));
            ps.setString(3, atendimento.getDescricao());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                atendimento.setId(rs.getInt(1));
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

        return atendimento;
    }

    public Atendimento update(Atendimento atendimento) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setInt(1, atendimento.getPet().getId());
            ps.setDate(2, new java.sql.Date(atendimento.getData().getTime()));
            ps.setString(3, atendimento.getDescricao());
            ps.setInt(4, atendimento.getId());
            ps.executeUpdate();

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return atendimento;
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

    public Atendimento findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Atendimento atendimento = null;

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                atendimento = new Atendimento();
                atendimento.setId(rs.getInt("id"));
                int pet_id = rs.getInt("pet_id");
                Pet pet = petRepository.findById(pet_id);
                atendimento.setPet(pet);
                atendimento.setData(rs.getDate("data"));
                atendimento.setDescricao(rs.getString("descricao"));
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

        return atendimento;
    }

    public List<Atendimento> findAll() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Atendimento> atendimentos = new ArrayList<>();

        try {
            conn = new ConnectionFactory().getConnection();
            ps = conn.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                Atendimento atendimento = new Atendimento();
                atendimento.setId(rs.getInt("id"));
                int pet_id = rs.getInt("pet_id");
                Pet pet = petRepository.findById(pet_id);
                atendimento.setPet(pet);
                atendimento.setData(rs.getDate("data"));
                atendimento.setDescricao(rs.getString("descricao"));

                atendimentos.add(atendimento);
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

        return atendimentos;
    }
}


