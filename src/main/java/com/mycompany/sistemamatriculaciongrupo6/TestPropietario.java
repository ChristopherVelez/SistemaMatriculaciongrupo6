package com.mycompany.sistemamatriculaciongrupo6;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestPropietario {

    private SistemaMatriculacionGrupo6 sistema;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static PrintStream originalOut;

    static {
        originalOut = System.out;
    }

    @BeforeEach
    void setUp() {
        sistema = new SistemaMatriculacionGrupo6();
        SistemaMatriculacionGrupo6.propietarioslist.clear();
        SistemaMatriculacionGrupo6.matriculalist.clear();
        SistemaMatriculacionGrupo6.pagolist.clear();

        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("TC03: Registrar propietario con nombre que contiene números")
    void registrarPropietario_TC03_NombreConNumeros() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sistema.registrarPropietario("Pedro123", "0912345678", "Guayaquil", "0987654321");
        });
        assertEquals("El nombre no debe estar vacío ni contener números.", thrown.getMessage());
    }

    @Test
    @DisplayName("TC04: Registrar propietario con cédula de menos de 10 dígitos")
    void registrarPropietario_TC04_CedulaCorta() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sistema.registrarPropietario("Pedro", "09123", "Guayaquil", "0987654321");
        });
        assertEquals("La cédula debe contener exactamente 10 dígitos numéricos.", thrown.getMessage());
    }
}