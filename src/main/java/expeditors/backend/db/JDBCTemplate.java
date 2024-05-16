package expeditors.backend.db;

import expeditors.backend.adoption.AdopterNew;
import expeditors.backend.adoption.AdopterPet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import static java.lang.System.out;

public class JDBCTemplate {
    public static void main(String[] args) {
        JDBCTemplate jtd = new JDBCTemplate();
        String url = "jdbc:postgresql://localhost:5433/adoptapp";
        String user = "larku";
        String pw = "larku";
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, user, pw);

        JdbcTemplate template = new JdbcTemplate(dataSource);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new
                NamedParameterJdbcTemplate(dataSource);

        //jtd.selectSimpleAdopter(template);
        //jtd.insertAdopter(template);
        //jtd.selectObjectAdopter(template);
        //jtd.selectMultipleTables(template);
        //jtd.insertMultipleTables(template);
        //jtd.insertMultipleTables2(template);
        jtd.deletePet(template);
        jtd.deleteAdopter(template);
    }

    public void selectSimpleAdopter(JdbcTemplate template) {
        String sql = "select name from adopter where idAdopter = ?";

        String name =
                template.queryForObject(sql, String.class, 1);

        out.println("name: " + name);
    }

    public void selectObjectAdopter(JdbcTemplate template) {
        String sql = "select * from adopter where idAdopter = ?;";

        RowMapper<AdopterNew> rowMapper = (resultSet, rowNum) -> {
            int idAdopter = resultSet.getInt("idAdopter");
            String name = resultSet.getString("name");
            String phoneNumber = resultSet.getString("phoneNumber");
            LocalDate dateAdoption = resultSet.getObject("dateAdoption", LocalDate.class);

            AdopterNew adopterNew = new AdopterNew(idAdopter,name,phoneNumber,dateAdoption);
            return adopterNew;
        };

        AdopterNew adopterNew =
                template.queryForObject(sql, rowMapper, 1);

        out.println("Adopter: " + adopterNew);
    }

    public void selectMultipleTables(JdbcTemplate template){
        String sql = """
                SELECT a.idAdopter, a.name,a.dateAdoption,a.phoneNumber,p.idPet,p.idTypePet,tp.typeName,p.namePet,p.breedPet FROM Adopter a
                LEFT JOIN Pet p
                    ON a.idAdopter=p.idAdopter
                LEFT JOIN TypePet tp on p.idTypePet = tp.idTypePet
                WHERE a.idAdopter=?""";

        List<AdopterPet> adopterPet = new ArrayList<>();
        ResultSetExtractor<List<AdopterPet>> rowMapper = (resultSet) -> {
            while (resultSet.next()) {
                int idAdopter = resultSet.getInt("idAdopter");
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phoneNumber");
                LocalDate dateAdoption = resultSet.getObject("dateAdoption", LocalDate.class);
                int idPet = resultSet.getInt("idPet");
                int idTypePet = resultSet.getInt("idTypePet");
                String namePet = resultSet.getString("namePet");
                String breedPet = resultSet.getString("breedPet");


                AdopterPet adopterPetItem = new AdopterPet(idAdopter, name, phoneNumber, dateAdoption, idPet, idTypePet, namePet, breedPet);
                adopterPet.add(adopterPetItem);
            }
            return adopterPet;
        };
        //AdopterPet adopterResult =
                //template.queryForObject(sql, rowMapper, 3);
        List<AdopterPet> adopterPetResult = template.query(sql, rowMapper, 2);

        out.println("Adopter: " + adopterPetResult);
    }

    public void insertAdopter(JdbcTemplate template) {
        String insertAdopterSql = "insert into Adopter (name, phoneNumber, dateAdoption) values (?, ?, ?);";

        List<Object[]> adopterParams = new ArrayList<>();

        //List<Integer> newAdopterKeys = new ArrayList<>();
        LocalDate nowDate = LocalDate.now();

        adopterParams.add(new Object[]{"Prueba Insert", "112-15-100", nowDate});
        adopterParams.add(new Object[]{"Prueba 2 Insert", "102-15-199", nowDate});

        int [] newAdopterKeys = template.batchUpdate(insertAdopterSql, adopterParams);

        out.println("Adopters added: " + newAdopterKeys.length);

    }

    public void insertMultipleTables(JdbcTemplate template) {
        String insertAdopterSql = "insert into Adopter (name, phoneNumber, dateAdoption) values (?, ?, ?);";

        String insertPetSql = "insert into Pet (idadopter, idtypepet, namepet, breedpet) values (?, ?, ?, ?);";


        List<Object[]> adopterParams = new ArrayList<>();

        //List<Integer> newAdopterKeys = new ArrayList<>();
        LocalDate nowDate = LocalDate.now();

        adopterParams.add(new Object[]{"Prueba Insert Adopter-Pet", "112-15-100", nowDate});

        int [] newAdopterKeys = template.batchUpdate(insertAdopterSql, adopterParams);
        out.println("Adopters added: " + newAdopterKeys.length);


        for (int idAdopterNew : newAdopterKeys)
        {
            List<Object[]> petParams = new ArrayList<>();
            petParams.add(new Object[]{idAdopterNew,1,"Prueba Insert","Callejero"});
            petParams.add(new Object[]{idAdopterNew,1,"Prueba 2 Insert","Callejero"});
            int [] newPetKeys = template.batchUpdate(insertPetSql, petParams);
            out.println("Pets added: " + newPetKeys.length);
        }

    }

    public void  insertMultipleTables2(JdbcTemplate template) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String insertAdopterSql = "insert into Adopter (name, phoneNumber, dateAdoption) values (?, ?, ?);";
        LocalDate nowDate = LocalDate.now();

        template.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(insertAdopterSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Ana Lopez");
            ps.setString(2, "998-95-010");
            ps.setDate(3, Date.valueOf(nowDate.plusDays(-4)));
            return ps;
        }, keyHolder);

        if (!keyHolder.getKeyList().isEmpty()){
            //Number keyIdAdopter =  keyHolder.getKey();
            Integer keyIdAdopter = (Integer)keyHolder.getKeyList().getFirst().get("idAdopter");

            out.println("Adopters added: " + keyHolder.getKeyList().getFirst());

            String insertPetSql = "insert into Pet (idadopter, idtypepet, namepet, breedpet) values (?, ?, ?, ?);";

            List<Object[]> petParams = new ArrayList<>();
            petParams.add(new Object[]{keyIdAdopter,2,"Negro","Callejero"});
            petParams.add(new Object[]{keyIdAdopter,2,"Huesos","Callejero"});
            int [] newPetKeys = template.batchUpdate(insertPetSql, petParams);
            out.println("Pets added: " + newPetKeys.length);
        }
    }

    public void deletePet(JdbcTemplate template) {
        String deletePetSql = "delete from pet where idPet>?";

        List<Object[]> petParams = new ArrayList<>();
        petParams.add(new Object[]{11});

        int [] newAdopterKeys = template.batchUpdate(deletePetSql, petParams);

        out.println("Pets delete: " + newAdopterKeys[0]);

    }

    public void deleteAdopter(JdbcTemplate template) {
        String deleteAdopterSql = "delete from Adopter where idAdopter>?";

        List<Object[]> adopterParams = new ArrayList<>();
        adopterParams.add(new Object[]{8});

        int [] newAdopterKeys = template.batchUpdate(deleteAdopterSql, adopterParams);

        out.println("Adopters delete: " + newAdopterKeys[0]);

    }
}
