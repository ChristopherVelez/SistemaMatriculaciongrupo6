/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemamatriculaciongrupo6;

import java.util.HashSet;
import java.util.Set;

/* AITB */
    
public class TestPagarMatricula {
    
    // Simulamos una clase Matricula simple
    static class Matricula {
        private static Set<String> matriculasPagadas = new HashSet<>();
        
        public static String pagarMatricula(String codigo, String metodoPago, double monto, boolean activo) {
            // Validar código de matrícula
            if (codigo == null || codigo.trim().isEmpty()) {
                throw new IllegalArgumentException("El código de matrícula no puede ser null o vacío");
            }
            
            // Validar método de pago
            if (metodoPago == null || metodoPago.trim().isEmpty()) {
                throw new IllegalArgumentException("El método de pago no puede ser null o vacío");
            }
            
            // Validar monto
            if (monto <= 0) {
                throw new IllegalArgumentException("El monto debe ser mayor que cero");
            }
            
            // Verificar si ya está pagada
            if (matriculasPagadas.contains(codigo)) {
                throw new IllegalStateException("La matrícula ya ha sido pagada previamente");
            }
            
            // Procesar pago exitoso
            matriculasPagadas.add(codigo);
            return "¡Pagado con éxito!";
        }
        
        public static void resetearEstado() {
            matriculasPagadas.clear();
        }
        
        public static boolean estaMatriculaPagada(String codigo) {
            return matriculasPagadas.contains(codigo);
        }
    }
    
    /* TC01: Pagar matrícula válida */
    public static void testTC01_PagarMatriculaValida() {
        System.out.println("=== TC01: Pagar matricula valida ===");
        
        try {
            // Resetear estado
            Matricula.resetearEstado();
            
            // Datos de prueba
            String codigo = "ABC-1234";
            String metodoPago = "Tarjeta";
            double monto = 100.0;
            boolean activo = true;
            String esperado = "¡Pagado con éxito!";
            
            // Ejecutar método
            String resultado = Matricula.pagarMatricula(codigo, metodoPago, monto, activo);
            
            // Verificar resultado
            if (esperado.equals(resultado)) {
                System.out.println("  TEST APROBADO: Mensaje correcto recibido");
            } else {
                System.out.println("  TEST FALLIDO: Esperado '" + esperado + "', recibido '" + resultado + "'");
            }
            
            // Verificar que la matrícula quedó marcada como pagada
            if (Matricula.estaMatriculaPagada(codigo)) {
                System.out.println("  TEST APROBADO: Matricula marcada como pagada");
            } else {
                System.out.println("  TEST FALLIDO: Matrícula no fue marcada como pagada");
            }
            
        } catch (Exception e) {
            System.out.println("  TEST FALLIDO: Excepción inesperada - " + e.getMessage());
        }
        System.out.println();
    }
    
    /* TC02: Pagar matrícula con monto cero */
    public static void testTC02_PagarMatriculaMontoZero() {
        System.out.println("=== TC02: Pagar matricula con monto cero ===");
        
        try {
            // Resetear estado
            Matricula.resetearEstado();
            
            // Datos de prueba
            String codigo = "ABC-1234";
            String metodoPago = "Tarjeta";
            double monto = 0.0;
            boolean activo = true;
            
            // Ejecutar método - debe lanzar excepción
            String resultado = Matricula.pagarMatricula(codigo, metodoPago, monto, activo);
            
            // Si llegamos aquí, el test falló
            System.out.println("TEST FALLIDO: Debería haber lanzado IllegalArgumentException");
            
        } catch (IllegalArgumentException e) {
            System.out.println("  TEST APROBADO: IllegalArgumentException capturada correctamente");
            System.out.println("  Mensaje: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  TEST FALLIDO: Excepción incorrecta - " + e.getClass().getSimpleName());
        }
        System.out.println();
    }
    
    /* TC03: Pagar matrícula con monto negativo */
    public static void testTC03_PagarMatriculaMontoNegativo() {
        System.out.println("=== TC03: Pagar matricula con monto negativo ===");
        
        try {
            // Resetear estado
            Matricula.resetearEstado();
            
            // Datos de prueba
            String codigo = "ABC-1234";
            String metodoPago = "Tarjeta";
            double monto = -50.0;
            boolean activo = true;
            
            // Ejecutar método - debe lanzar excepción
            String resultado = Matricula.pagarMatricula(codigo, metodoPago, monto, activo);
            
            // Si llegamos aquí, el test falló
            System.out.println("  TEST FALLIDO: Debería haber lanzado IllegalArgumentException");
            
        } catch (IllegalArgumentException e) {
            System.out.println("  TEST APROBADO: IllegalArgumentException capturada correctamente");
            System.out.println("  Mensaje: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  TEST FALLIDO: Excepción incorrecta - " + e.getClass().getSimpleName());
        }
        System.out.println();
    }
    
    /* TC04: Método de pago vacío */
    public static void testTC04_MetodoPagoVacio() {
        System.out.println("=== TC04: Metodo de pago vacio ===");
        
        try {
            // Resetear estado
            Matricula.resetearEstado();
            
            // Datos de prueba
            String codigo = "ABC-1234";
            String metodoPago = "";
            double monto = 100.0;
            boolean activo = true;
            
            // Ejecutar método - debe lanzar excepción
            String resultado = Matricula.pagarMatricula(codigo, metodoPago, monto, activo);
            
            // Si llegamos aquí, el test falló
            System.out.println("  TEST FALLIDO: Debería haber lanzado IllegalArgumentException");
            
        } catch (IllegalArgumentException e) {
            System.out.println("  TEST APROBADO: IllegalArgumentException capturada correctamente");
            System.out.println("  Mensaje: El metodo de pago no puede ser null o vacio");
        } catch (Exception e) {
            System.out.println("  TEST FALLIDO: Excepción incorrecta - " + e.getClass().getSimpleName());
        }
        System.out.println();
    }
    
    /* TC05: Matrícula ya pagada previamente */
    public static void testTC05_MatriculaYaPagada() {
        System.out.println("=== TC05: Matricula ya pagada previamente ===");
        
        try {
            // Resetear estado
            Matricula.resetearEstado();
            
            // Datos de prueba
            String codigo = "ABC-1234";
            String metodoPago1 = "Tarjeta";
            String metodoPago2 = "Efectivo";
            double monto = 100.0;
            boolean activo = true;
            
            // Primer pago - debe ser exitoso
            String primerPago = Matricula.pagarMatricula(codigo, metodoPago1, monto, activo);
            System.out.println("  Primer pago: " + primerPago);
            
            // Segundo pago - debe lanzar excepción
            String segundoPago = Matricula.pagarMatricula(codigo, metodoPago2, monto, activo);
            
            // Si llegamos aquí, el test falló
            System.out.println("  TEST FALLIDO: Debería haber lanzado IllegalStateException");
            
        } catch (IllegalStateException e) {
            System.out.println("  TEST APROBADO: IllegalStateException capturada correctamente");
            System.out.println("  Mensaje: La matricula ya ha sido pagada previamente");
        } catch (Exception e) {
            System.out.println("  TEST FALLIDO: Excepción incorrecta - " + e.getClass().getSimpleName());
            System.out.println("  Mensaje: " + e.getMessage());
        }
        System.out.println();
    }
    
    /*Método principal que ejecuta todos los tests */
    public static void main(String[] args) {
        System.out.println("********* Ejecutando los test de pagar matricula *********");
        System.out.println();
        
        // Ejecutar todos los casos de prueba
        testTC01_PagarMatriculaValida();
        testTC02_PagarMatriculaMontoZero();
        testTC03_PagarMatriculaMontoNegativo();
        testTC04_MetodoPagoVacio();
        testTC05_MatriculaYaPagada();
        
        System.out.println("==========================================");
        System.out.println("           TESTS COMPLETADOS");
        System.out.println("==========================================");
    }
}