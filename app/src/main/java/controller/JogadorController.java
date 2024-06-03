// JogadorController.java
import android.content.Context;
import java.sql.SQLException;
import java.util.List;

public class JogadorController {
    private JogadorDao jogadorDao;

    public JogadorController(Context context) {
        jogadorDao = new JogadorDao(context);
    }

    public void addJogador(Jogador jogador) throws SQLException {
        jogadorDao.insert(jogador);
    }

    public void updateJogador(Jogador jogador) throws SQLException {
        jogadorDao.update(jogador);
    }

    public void deleteJogador(Jogador jogador) throws SQLException {
        jogadorDao.delete(jogador);
    }

    public Jogador getJogador(int id) throws SQLException {
        return jogadorDao.findOne(id);
    }

    public List<Jogador> getAllJogadores() throws SQLException {
        return jogadorDao.findAll();
    }
}
