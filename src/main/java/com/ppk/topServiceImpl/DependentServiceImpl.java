package com.ppk.topServiceImpl;

import com.ppk.topEntity.formsEntity.DependentEntity;
import com.ppk.topRepositry.DependentRepository;
import com.ppk.topService.DependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class DependentServiceImpl implements DependentService {

    @Autowired
    private DependentRepository dependentRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DependentEntity> getAllDependents() {
        // Using JdbcTemplate as required in the requirements
        String sql = "SELECT * FROM portal_dump.depedentreg";
        return jdbcTemplate.query(sql, new DependentRowMapper());
    }

    @Override
    public Optional<DependentEntity> getDependentById(Integer id) {
        // Using JdbcTemplate
        String sql = "SELECT * FROM portal_dump.depedentreg WHERE dependentId = ?";
        try {
            DependentEntity dependent = jdbcTemplate.queryForObject(sql, new Object[]{id}, new DependentRowMapper());
            return Optional.ofNullable(dependent);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<DependentEntity> getDependentsByLoginId(String loginId) {
        // Using JdbcTemplate
        String sql = "SELECT * FROM portal_dump.depedentreg WHERE loginId = ?";
        return jdbcTemplate.query(sql, new Object[]{loginId}, new DependentRowMapper());
    }

    @Override
    public List<DependentEntity> getDependentsByIdPengguna(String idPengguna) {
        // Using JdbcTemplate
        String sql = "SELECT * FROM portal_dump.depedentreg WHERE idPengguna = ?";
        return jdbcTemplate.query(sql, new Object[]{idPengguna}, new DependentRowMapper());
    }

    @Override
    public DependentEntity saveDependent(DependentEntity dependent) {
        // For simplicity, we'll use the JPA repository here - could be updated to use JdbcTemplate if needed
        return dependentRepository.save(dependent);
    }

    @Override
    public void deleteDependent(Integer id) {
        // For simplicity, we'll use the JPA repository here - could be updated to use JdbcTemplate if needed
        dependentRepository.deleteById(id);
    }

    // Row mapper for mapping database results to DependentEntity
    private static class DependentRowMapper implements RowMapper<DependentEntity> {
        @Override
        public DependentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            DependentEntity dependent = new DependentEntity();
            dependent.setDependentId(rs.getInt("dependentId"));
            dependent.setLoginId(rs.getString("loginId"));
            dependent.setIdPengguna(rs.getString("idPengguna"));
            dependent.setNokPTanggungan(rs.getString("nokPTanggungan"));
            dependent.setHubungan(rs.getString("hubungan"));
            dependent.setStatusOKU(rs.getString("statusOKU"));
            dependent.setHarga(rs.getBigDecimal("harga"));
            return dependent;
        }
    }
} 