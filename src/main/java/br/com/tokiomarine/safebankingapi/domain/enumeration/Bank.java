package br.com.tokiomarine.safebankingapi.domain.enumeration;

import br.com.tokiomarine.safebankingapi.domain.exception.DomainException;

public enum Bank {
    BANCO_DO_BRASIL("001", "Banco do Brasil"),
    SANTANDER("033", "Santander"),
    ITAU("341", "ItaÚ"),
    CAIXA("104", "Caixa Economica Federal"),
    BRADESCO("237", "Bradesco");

    private final String code;
    private final String name;

    private Bank(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getBankByCode(String code) {
        for (Bank bank : Bank.values()) {
            if (bank.getCode().equals(code)) {
                return bank.getName();
            }
        }
        throw new DomainException("Bank Code Not Found " + code, "BANK_NOT_FOUND");
    }

}
