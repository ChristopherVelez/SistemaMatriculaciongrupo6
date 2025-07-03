package com.mycompany.sistemamatriculaciongrupo6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TestConsultarMatricula {

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
    @DisplayName("TC01: Consultar matrícula existente")
    void consultarMatricula_TC01_Existente() {
        sistema.registrarPropietario("Ana Gomez", "0987654321", "Calle B 456", "0991234567");
        sistema.registrarMatricula("ABC-1234", "Ford", "Fiesta", 2018, "Hatchback", "0987654321");
        outContent.reset();
        SistemaMatriculacionGrupo6.consultarMatricula("ABC-1234");
        String expectedOutputPart = "Vehiculo{placa='ABC-1234', marca='Ford', modelo='Fiesta', tipo='Hatchback', anio=2018";
        assertTrue(outContent.toString().trim().contains(expectedOutputPart), "Debería imprimir la información de la matrícula existente.");
    }

    @Test
    @DisplayName("TC02: Consultar matrícula no existente")
    void consultarMatricula_TC02_NoExistente() {
        sistema.registrarPropietario("Carlos Ruiz", "0900000000", "Av. Principal 789", "0980000000");
        sistema.registrarMatricula("ABC-0001", "Nissan", "Sentra", 2022, "Sedán", "0900000000");
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
            SistemaMatriculacionGrupo6.consultarMatricula("XYZ-9999");
        });
        assertEquals("No se encontró ninguna matrícula con esta placa: XYZ-9999", thrown.getMessage(), "Debería lanzar NoSuchElementException si la matrícula no existe.");
    }

    @Test
    @DisplayName("TC03: Consultar matrícula cuando no hay matrículas registradas (estado inicial)")
    void consultarMatricula_TC03_ListaVacia() {
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            SistemaMatriculacionGrupo6.consultarMatricula("CUALQUIER-PLACA");
        });
        assertEquals("No hay matrículas registradas.", thrown.getMessage(), "Debería lanzar IllegalStateException si no hay matrículas registradas.");
    }

    @Test
    @DisplayName("TC04: Consultar con placa nula")
    void consultarMatricula_TC04_PlacaNula() {
        sistema.registrarPropietario("Pedro Lopez", "0998765432", "Calle X 10", "0987654321");
        sistema.registrarMatricula("AAA-111", "Toyota", "Corolla", 2010, "Sedan", "0998765432");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            SistemaMatriculacionGrupo6.consultarMatricula(null);
        });
        assertEquals("La placa debe tener el formato ABC-123 o ABC-1234.", thrown.getMessage());
    }

    @Test
    @DisplayName("TC05: Consultar con placa con formato incorrecto")
    void consultarMatricula_TC05_PlacaFormatoIncorrecto() {
        sistema.registrarPropietario("Luis Ramirez", "0976543210", "Av. Sur 20", "0990987654");
        sistema.registrarMatricula("BBB-222", "Honda", "Civic", 2015, "Sedan", "0976543210");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            SistemaMatriculacionGrupo6.consultarMatricula("123-ABC");
        });
        assertEquals("La placa debe tener el formato ABC-123 o ABC-1234.", thrown.getMessage());
    }
}
