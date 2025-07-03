package com.mycompany.sistemamatriculaciongrupo6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TestRegistrarMatricula {

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
    @DisplayName("TC01: Registrar matrícula válida")
    void registrarMatricula_TC01_Valida() {

        sistema.registrarPropietario("Juan Perez", "0912345678", "Calle Ficticia 123", "0998765432");

        sistema.registrarMatricula("ABC-1234", "Chevrolet", "Aveo", 2015, "Sedán", "0912345678");

        String expectedOutput = "Matrícula registrada con éxito. Recuerde pagar la matrícula.";
        assertTrue(outContent.toString().trim().contains(expectedOutput), "Debería mostrar el mensaje de éxito.");

        assertEquals(1, SistemaMatriculacionGrupo6.matriculalist.size(), "Debería haber 1 matrícula registrada.");
        assertEquals("ABC-1234", SistemaMatriculacionGrupo6.matriculalist.get(0).getVehiculo().getPlaca(), "La placa de la matrícula debe ser la esperada.");
    }

    @Test
    @DisplayName("TC02: Placa con formato inválido")
    void registrarMatricula_TC02_PlacaInvalida() {
        sistema.registrarPropietario("Juan Perez", "0912345678", "Calle Ficticia 123", "0998765432");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sistema.registrarMatricula("123-ABC", "Chevrolet", "Aveo", 2015, "Sedán", "0912345678");
        });

        assertEquals("La placa debe tener el formato ABC-123 o ABC-1234.", thrown.getMessage(), "Debería lanzar IllegalArgumentException por formato de placa inválido.");

        assertTrue(SistemaMatriculacionGrupo6.matriculalist.isEmpty(), "No debería haber matrículas registradas.");
    }

    @Test
    @DisplayName("TC03: Propietario no existe")
    void registrarMatricula_TC03_PropietarioNoExiste() {
        sistema.registrarPropietario("Otro Propietario", "1111111111", "Otra Dirección", "0987654321");


        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
            sistema.registrarMatricula("XYZ-0001", "Toyota", "Corolla", 2020, "Sedán", "0999999999");
        });

        assertEquals("No se encontró un propietario con el número de cédula: 0999999999", thrown.getMessage(), "Debería lanzar NoSuchElementException si el propietario no se encuentra.");
        assertTrue(SistemaMatriculacionGrupo6.matriculalist.isEmpty(), "No debería haber matrículas registradas.");
    }

    @Test
    @DisplayName("TC04: Lista de propietarios vacía")
    void registrarMatricula_TC04_ListaPropietariosVacia() {
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            sistema.registrarMatricula("XYZ-0001", "Toyota", "Corolla", 2020, "Sedán", "0912345678");
        });

        assertEquals("No hay propietarios registrados.", thrown.getMessage(), "Debería lanzar IllegalStateException si no hay propietarios registrados.");
        assertTrue(SistemaMatriculacionGrupo6.matriculalist.isEmpty(), "No debería haber matrículas registradas.");
    }

    @Test
    @DisplayName("TC05: Vehículo ya matriculado (placa ya usada)")
    void registrarMatricula_TC05_VehiculoYaMatriculado() {
        sistema.registrarPropietario("Juan Perez", "0912345678", "Calle Ficticia 123", "0998765432");

        sistema.registrarMatricula("ABC-1234", "Chevrolet", "Aveo", 2015, "Sedán", "0912345678");
        assertEquals(1, SistemaMatriculacionGrupo6.matriculalist.size(), "Debería haber 1 matrícula registrada después del primer registro.");

        outContent.reset();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            sistema.registrarMatricula("ABC-1234", "Ford", "Fiesta", 2020, "Hatchback", "0912345678");
        });
        assertEquals("Este vehículo ya está matriculado.", thrown.getMessage(), "Debería lanzar IllegalArgumentException porque el vehículo ya está matriculado.");
        assertEquals(1, SistemaMatriculacionGrupo6.matriculalist.size(), "No debería haberse añadido una nueva matrícula.");
    }
}