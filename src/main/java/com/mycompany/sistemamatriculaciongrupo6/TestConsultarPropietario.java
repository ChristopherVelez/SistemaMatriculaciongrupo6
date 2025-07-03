package com.mycompany.sistemamatriculaciongrupo6;

import java.util.NoSuchElementException;

public class TestConsultarPropietario {

    public static void main(String[] args) {
        SistemaMatriculacionGrupo6 sistema = new SistemaMatriculacionGrupo6();

        // Caso TC01: Consultar propietario existente
        try {
            // Primero agregamos un propietario para la prueba
            sistema.registrarPropietario("Juan Pérez", "0912345678", "Av. Quito", "0999988776");
            sistema.consultarPropietario("0912345678");  // Debe imprimir info del propietario
            System.out.println("TC01: Aprobado");
        } catch (Exception e) {
            System.out.println("TC01: Fallido - " + e.getMessage());
        }

        // Caso TC02: Consultar con cédula incorrecta
        try {
            sistema.consultarPropietario("abc123");
            System.out.println("TC02: Fallido - No lanzó excepción");
        } catch (IllegalArgumentException e) {
            System.out.println("TC02: Aprobado");
        } catch (Exception e) {
            System.out.println("TC02: Fallido - " + e.getMessage());
        }

    }
}
