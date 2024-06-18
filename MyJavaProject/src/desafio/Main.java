package desafio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
            new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
            new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
            new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
            new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
            new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
            new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
            new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
            new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
            new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
            new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));

      
        funcionarios.removeIf(funcionario -> funcionario.nome.equals("João"));

       
        System.out.println("Funcionários:");
        funcionarios.forEach(System.out::println);

       
        BigDecimal aumento = new BigDecimal("10");
        funcionarios.forEach(funcionario -> funcionario.aumentarSalario(aumento));

    
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
            .collect(Collectors.groupingBy(funcionario -> funcionario.funcao));

   
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println(funcao + ":");
            lista.forEach(System.out::println);
        });

     
        System.out.println("\nFuncionários que fazem aniversário em Outubro e Dezembro:");
        funcionarios.stream()
            .filter(funcionario -> funcionario.dataNascimento.getMonthValue() == 10 ||
                                   funcionario.dataNascimento.getMonthValue() == 12)
            .forEach(System.out::println);

       
        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparing(funcionario -> ChronoUnit.YEARS.between(funcionario.dataNascimento, LocalDate.now())));
        long idade = ChronoUnit.YEARS.between(maisVelho.dataNascimento, LocalDate.now());
        System.out.println("\nFuncionário com a maior idade:");
        System.out.println(maisVelho.nome + ", " + idade + " anos");

      
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.stream()
            .sorted(Comparator.comparing(funcionario -> funcionario.nome))
            .forEach(System.out::println);

      
        BigDecimal totalSalarios = funcionarios.stream()
            .map(funcionario -> funcionario.salario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários dos funcionários: " +
                           totalSalarios.setScale(2, RoundingMode.HALF_UP).toString().replace(".", ","));


        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nQuantidade de salários mínimos por funcionário:");
        funcionarios.forEach(funcionario -> {
            BigDecimal qtdSalariosMinimos = funcionario.salario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(funcionario.nome + ": " + qtdSalariosMinimos);
        });
    }
}
