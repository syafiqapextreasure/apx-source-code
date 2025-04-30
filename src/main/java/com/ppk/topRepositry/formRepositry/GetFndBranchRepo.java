package com.ppk.topRepositry.formRepositry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ppk.topEntity.formsEntity.GetFndBranch;
import com.ppk.topEntity.formsEntity.dto.DropDownValueDto;

@Repository
public class GetFndBranchRepo  {
   @Autowired
  private JdbcTemplate jdbcTemplate;
	// Query to get all branches (GL71BRNC and GL71DESC) for dropdown
		public List<DropDownValueDto> getDropDownValueForRegistration() {
			String sql = "SELECT * FROM Hubungan"; // Adjust table name if needed

			// Use RowMapper to map the result set to DropDownValueDto objects
			return jdbcTemplate.query(sql, new RowMapper<DropDownValueDto>() {
				@Override
				public DropDownValueDto mapRow(ResultSet rs, int rowNum) throws SQLException {
					DropDownValueDto dto = new DropDownValueDto();
					dto.setDropValueCode(rs.getString("hubcod"));
					dto.setDropValueName(rs.getString("hubval"));
					return dto;
				}
			});
		}

		// Query to get branch codes and descriptions, if needed
		public List<GetFndBranch> getFndBranchCodeandDescDb() {
			String sql = "SELECT GL71BRNC, GL71DESC FROM glbrnc"; // Adjust table name if needed

			// Use RowMapper to map the result set to GetFndBranch objects (make sure
			// GetFndBranch is a valid entity)
			return jdbcTemplate.query(sql, new RowMapper<GetFndBranch>() {
				@Override
				public GetFndBranch mapRow(ResultSet rs, int rowNum) throws SQLException {
					GetFndBranch branch = new GetFndBranch();
					branch.setCode(rs.getString("GL71BRNC"));
					branch.setDesc(rs.getString("GL71DESC"));
					return branch;
				}
			});
		}
}
