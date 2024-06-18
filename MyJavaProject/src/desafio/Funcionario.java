package desafio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {
    BigDecimal salario;
    String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public void aumentarSalario(BigDecimal percentual) {
        salario = salario.add(salario.multiply(percentual).divide(BigDecimal.valueOf(100)));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return nome + ", " + dataNascimento.format(formatter) + ", " +
               salario.setScale(2, RoundingMode.HALF_UP).toString().replace(".", ",") + ", " + funcao;
    }
}
