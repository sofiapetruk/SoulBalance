package br.com.fiap.SoulBalance.exception;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }


    public static Supplier<NotFoundException> forUser(Long id) {
        return () -> new NotFoundException("Usuario não achado para id" + id);
    }

    public static Supplier<NotFoundException> forLogin(String email) {
        return () -> new NotFoundException("Usuario não encontrado" + email);
    }


}
