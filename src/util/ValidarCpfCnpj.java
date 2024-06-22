package util;

public class ValidarCpfCnpj {

    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF inválido (Tamanho incopatível)");
        }

        if (todosDigitosIguais(cpf)) {
            throw new IllegalArgumentException("CPF inválido (todos os dígitos são iguais)");
        }

        try {
            int[] digitos = new int[11];
            for (int i = 0; i < 11; i++) {
                digitos[i] = Integer.parseInt(cpf.substring(i, i + 1));
            }

            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += digitos[i] * (10 - i);
            }
            int digitoVerificador1 = 11 - (soma % 11);
            if (digitoVerificador1 == 10 || digitoVerificador1 == 11) {
                digitoVerificador1 = 0;
            }

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += digitos[i] * (11 - i);
            }
            int digitoVerificador2 = 11 - (soma % 11);
            if (digitoVerificador2 == 10 || digitoVerificador2 == 11) {
                digitoVerificador2 = 0;
            }

            return digitoVerificador1 == digitos[9] && digitoVerificador2 == digitos[10];

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    public static boolean validarCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\D", "");

        if (cnpj.length() != 14) {
            throw new IllegalArgumentException("CNPJ inválido (Tamanho incopatível)");
        }

        if (todosDigitosIguais(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido (todos os dígitos são iguais)");
        }

        try {
            int[] digitos = new int[14];
            for (int i = 0; i < 14; i++) {
                digitos[i] = Integer.parseInt(cnpj.substring(i, i + 1));
            }

            int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int soma = 0;
            for (int i = 0; i < 12; i++) {
                soma += digitos[i] * pesos1[i];
            }
            int digitoVerificador1 = 11 - (soma % 11);
            if (digitoVerificador1 == 10 || digitoVerificador1 == 11) {
                digitoVerificador1 = 0;
            }

            int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += digitos[i] * pesos2[i];
            }
            int digitoVerificador2 = 11 - (soma % 11);
            if (digitoVerificador2 == 10 || digitoVerificador2 == 11) {
                digitoVerificador2 = 0;
            }

            return digitoVerificador1 == digitos[12] && digitoVerificador2 == digitos[13];

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
    }

    private static boolean todosDigitosIguais(String documento) {
        char firstDigit = documento.charAt(0);
        for (int i = 1; i < documento.length(); i++) {
            if (documento.charAt(i) != firstDigit) {
                return false;
            }
        }
        return true;
    }
}
