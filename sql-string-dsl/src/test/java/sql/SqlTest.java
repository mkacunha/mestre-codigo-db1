package sql;

import br.com.mkacunha.core.Select;
import h2.H2Memory;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SqlTest {

    private Connection connection;

    @BeforeAll
    void setUp() {
        H2Memory h2Memory = new H2Memory();
        h2Memory.initDatabase();
        this.connection = h2Memory.getConnection();
    }

    @Test
    @DisplayName("Retorna todas as pessoas")
    public void deveRealizarSelectSemWhereEmPessoa() throws SQLException {
        String sql = Select.start().all().from("pessoa").toSql();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.last();
        assertEquals(6, resultSet.getRow());
    }

    @Test
    @DisplayName("Retorna apenas o nome de todas as pessoas")
    public void deveRetornsrApenasONome() throws SQLException {
        String sql = Select.start().columns("nome").from("pessoa").toSql();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.last();
        assertEquals(6, resultSet.getRow());
    }

    @Test
    @DisplayName("Retorna pessoa filtrada pelo nome")
    public void deveRetornarPessoaFiltradaPeloNome() throws SQLException {
        String sql = Select.start().columns("nome").from("pessoa").where().field("nome").equals("JOÃO DA SILVA").toSql();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.last();
        assertEquals("JOÃO DA SILVA", resultSet.getString("nome"));
    }
    @Test
    @DisplayName("Retorna pessoas filtradas pela data de nascimento")
    public void deveRetornarPessoaFiltradaPelaDataNascimento() throws SQLException {
        String sql = Select.start().columns("nome").from("pessoa").where().field("data_nascimento").between("1990-01-01", "1990-10-01").toSql();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.last();
        assertEquals("JOÃO DA SILVA", resultSet.getString("nome"));
    }

    @Test
    @DisplayName("Retonar pessoas que contém MARIA no nome")
    public void deveRetornarPessoaFiltradaPeloNomeQueContemMaria() throws SQLException {
        String sql = Select.start().columns("nome").from("pessoa").where().field("nome").like("%MARIA%").toSql();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.last();
        assertEquals(2, resultSet.getRow());
    }
}
