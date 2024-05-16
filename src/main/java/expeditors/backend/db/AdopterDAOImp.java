package expeditors.backend.db;

import expeditors.backend.adoption.AdopterNew;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AdopterDAOImp extends AbstractDao implements AdopterDAO<AdopterNew>{
    @Override
    public Optional<AdopterNew> findById(long id) {
        Optional<AdopterNew> adopterNew = Optional.empty();
        String sql = "SELECT idAdopter, name, phonenumber FROM Adopter WHERE idAdopter = ?";

        try (
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql);
        ) {
            prepStmt.setLong(1, id);

            try (ResultSet rset = prepStmt.executeQuery();) {
                AdopterNew resAdopter = new AdopterNew();

                if(rset.next()) {
                    resAdopter.setIdAdopter(rset.getInt("idAdopter"));
                    resAdopter.setName(rset.getString("name"));
                    resAdopter.setPhoneNumber(rset.getString("phonenumber"));
                }
                adopterNew = Optional.of(resAdopter);
            }
        }
        catch (SQLException sqe) { sqe.printStackTrace();}

        return adopterNew;
    }

    @Override
    public List<AdopterNew> findAll() {
        List<AdopterNew> adopters = Collections.emptyList();

        JdbcQueryTemplate<AdopterNew> template = new JdbcQueryTemplate<AdopterNew>() {
            @Override
            public AdopterNew mapItem(ResultSet rset) throws SQLException {
                AdopterNew resAdopter = new AdopterNew();
                resAdopter.setIdAdopter(rset.getInt("idAdopter"));
                resAdopter.setName(rset.getString("name"));
                resAdopter.setPhoneNumber(rset.getString("phonenumber"));
                return resAdopter;
            }
        };

        adopters = template.queryForList("SELECT idAdopter, name, phonenumber FROM Adopter");

        return adopters;
    }

    @Override
    public AdopterNew create(AdopterNew adopterNew) {
        String sql = "insert into Adopter (name, phoneNumber, dateAdoption) values (?, ?, ?);";

        try (
                Connection con = getConnection();
                PreparedStatement prepStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            prepStmt.setString(1, adopterNew.getName());
            prepStmt.setString(2, adopterNew.getPhoneNumber());
            prepStmt.setDate(3, Date.valueOf(adopterNew.getDateAdoption()));
            prepStmt.executeUpdate();

            try (ResultSet genKeys = prepStmt.getGeneratedKeys()) {
                if(genKeys.next()) {
                    adopterNew.setIdAdopter(genKeys.getInt("idAdopter"));
                }
            }
        }
        catch (SQLException sqe) { sqe.printStackTrace(); }

        return adopterNew;
    }

    @Override
    public AdopterNew update(AdopterNew adopterNew) {
        return null;
    }

    @Override
    public int[] update(List<AdopterNew> t) {
        return new int[0];
    }

    @Override
    public int delete(AdopterNew adopterNew) {
        return 0;
    }
}
