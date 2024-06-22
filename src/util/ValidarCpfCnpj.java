package util;

public class ValidarCpfCnpj {

    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF inválido: Tamanho incorreto.");
        }

        if (todosDigitosIguais(cpf)) {
            throw new IllegalArgumentException("CPF inválido: Todos os dígitos são iguais.");
        }

        try {
            int[] digitos = obterDigitos(cpf, 11);

            int digitoVerificador1 = calcularDigitoVerificador(digitos, new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2}, 9);
            int digitoVerificador2 = calcularDigitoVerificador(digitos, new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2}, 10);

            return digitoVerificador1 == digitos[9] && digitoVerificador2 == digitos[10];

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("CPF inválido: Formato incorreto.");
        }
    }

    public static boolean validarCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.length() != 14) {
            throw new IllegalArgumentException("CNPJ inválido: Tamanho incorreto.");
        }

        if (todosDigitosIguais(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido: Todos os dígitos são iguais.");
        }

        try {
            int[] digitos = obterDigitos(cnpj, 14);

            int digitoVerificador1 = calcularDigitoVerificador(digitos, new int[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}, 12);
            int digitoVerificador2 = calcularDigitoVerificador(digitos, new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}, 13);

            return digitoVerificador1 == digitos[12] && digitoVerificador2 == digitos[13];

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("CNPJ inválido: Formato incorreto.");
        }
    }

    private static int[] obterDigitos(String documento, int tamanho) {
        int[] digitos = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            digitos[i] = Integer.parseInt(documento.substring(i, i + 1));
        }
        return digitos;
    }

    private static int calcularDigitoVerificador(int[] digitos, int[] pesos, int tamanho) {
        int soma = 0;
        for (int i = 0; i < tamanho; i++) {
            soma += digitos[i] * pesos[i];
        }
        int digitoVerificador = 11 - (soma % 11);
        if (digitoVerificador == 10 || digitoVerificador == 11) {
            digitoVerificador = 0;
        }
        return digitoVerificador;
    }

    private static boolean todosDigitosIguais(String documento) {
        return documento.chars().distinct().count() == 1;
    }
}
