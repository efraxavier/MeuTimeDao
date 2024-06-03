// Time.java
public class Time {
    private int codigo;
    private String nome;
    private String cidade;

    // Getters e Setters
    @Override
    public String toString() {
        return "Time{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}

// Jogador.java
import java.time.LocalDate;

public class Jogador {
    private int id;
    private String nome;
    private LocalDate dataNasc;
    private float altura;
    private float peso;
    private Time time;

    // Getters e Setters
    @Override
    public String toString() {
        return "Jogador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNasc=" + dataNasc +
                ", altura=" + altura +
                ", peso=" + peso +
                ", time=" + time +
                '}';
    }
}
