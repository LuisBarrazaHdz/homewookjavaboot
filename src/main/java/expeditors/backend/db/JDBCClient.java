package expeditors.backend.db;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import expeditors.backend.adoption.Adopter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;


import static java.lang.System.out;


public class JDBCClient {
    public static void main(String[] args) {
        JDBCClient jtd = new JDBCClient();
        String url = "jdbc:postgresql://localhost:5433/adoptapp";
        String user = "larku";
        String pw = "larku";
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, user, pw);

        JdbcClient jdbcClient = JdbcClient.create(dataSource);

        //jtd.getSimpleSelect(jdbcClient);

        jtd.insertTables(jdbcClient);
    }

    public void getSimpleSelect(JdbcClient jdbcClient) {
        String sql = "select name from adopter where idAdopter = ?";

        String name = jdbcClient.sql(sql)
                .param(1)
                .query(String.class).single();

        out.println("name: " + name);
    }

    public void insertTables(JdbcClient jdbcClient) {
        String insertAdopterSql = "insert into Adopter (name, phoneNumber, dateAdoption) values (?, ?, ?)";

        List<Object[]> adopterParams = new ArrayList<>();

        List<Integer> newAdopterKeys = new ArrayList<>();

        //adopterParams.add(new Object[]{"Prueba Insert", "112-15-100", "2024-05-02"});
        adopterParams.add(new Object[]{"Prueba 2 Insert", "102-15-199", "2024-05-08"});

        var keyHolder = new GeneratedKeyHolder();

        for (Object[] params : adopterParams) {
            jdbcClient.sql(insertAdopterSql)
                    .params(params)
                    .update(keyHolder);

            newAdopterKeys.add((int) keyHolder.getKeys().get("idAdopter"));
        }

        out.println("Adopters added : " + newAdopterKeys.size());
        newAdopterKeys.forEach(out::println);
    }

}
