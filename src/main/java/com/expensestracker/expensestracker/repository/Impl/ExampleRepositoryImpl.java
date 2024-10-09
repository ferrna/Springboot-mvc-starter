package com.expensestracker.expensestracker.repository.Impl;

import com.expensestracker.expensestracker.model.Example;
import com.expensestracker.expensestracker.repository.ExampleRepository;
import com.expensestracker.expensestracker.repository.utils.ExampleRepositoryImplUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ExampleRepositoryImpl implements ExampleRepository {

    private final static String SELECT_ALL_EXAMPLES = "SELECT id, name, genre, price FROM examples";
    private final static String SELECT_EXAMPLE_BY_ID ="SELECT id, name, genre, price FROM examples WHERE id = ?";
    private final static String INSERT_NEW_EXAMPLE = "INSERT INTO examples (name, genre, price) VALUES (?, ?, ?)";
    private final static String DELETE_EXAMPLE_BY_ID = "DELETE FROM examples WHERE id = ?";
    private final static String CREATE_TABLE_EXAMPLES = "CREATE TABLE IF NOT EXISTS examples(id INT PRIMARY KEY,\n" +
            "   name VARCHAR(255), genre VARCHAR(255), price DOUBLE PRECISION);";
    private final static String CREATE_INDEX_PRICE = "CREATE INDEX IF NOT EXISTS idx_price ON examples(price);";

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public void init() {
        // 2°
        // Overrides afterPropertiesSet
        // Lógica de inicialización personalizada
        //jdbcTemplate.execute(CREATE_TABLE_EXAMPLES);
        //jdbcTemplate.execute(CREATE_INDEX_PRICE);
    }

//    @PostConstruct
//    public void postConstruct() {
//        // 1°
//    }

    private final RowMapper<Example> rowMapper = new RowMapper<Example>() {
        @Override
        public Example mapRow(ResultSet rs, int rowNum) throws SQLException {
            Example example = new Example();
            example.setId(rs.getLong("id"));
            example.setName(rs.getString("name"));
            example.setGenre(rs.getString("genre"));
            example.setPrice(rs.getDouble("price"));
            return example;
        }
    };

    @Override
    public List<Example> findAll() {
        return jdbcTemplate.query(SELECT_ALL_EXAMPLES, rowMapper);
    }

    @Override
    public Optional<Example> findById(Long id) {
        return jdbcTemplate.query(SELECT_EXAMPLE_BY_ID, new Object[]{id}, rowMapper).stream().findFirst();
    }

    @Override
    public Example save(Example example) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate
                .update(connection -> ExampleRepositoryImplUtils
                        .buildPreparedStatementInsert(connection, INSERT_NEW_EXAMPLE, example), keyHolder);
        example.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return example;
    }

    @Override
    public int update(Long id, Example example) {
        QueryResult queryResult = ExampleRepositoryImplUtils.buildUpdateQuery(id, example);
        if (queryResult == null) {
            return 0;
        }
        return jdbcTemplate
                .update(queryResult.query(), queryResult.params());
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update(DELETE_EXAMPLE_BY_ID, id);
    }
}
