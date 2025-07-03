package com.mycompany.sistemamatriculaciongrupo6;

import java.util.Date;
import java.util.Calendar;

public class TestPago {

    // ✅ Matricula válida utilizando tus constructores reales
    public static Matricula crearMatriculaValida() {
        Propietario propietario = new Propietario(
            "Carlos Herrera",             // nombre sin números
            "0102030405",                 // cédula
            "Av. Siempre Viva 123",       // dirección
            "0991234567"                  // teléfono
        );

        Vehiculo vehiculo = new Vehiculo(
            "ABC-1234",                   // placa
            "Toyota",                     // marca
            "Corolla",                    // modelo
            "Sedán",                      // tipo
            2020,                         // año válido
            propietario                   // propietario válido
        );

        Date fechaRegistro = new Date();  // fecha actual
        boolean estadoPago = true;

        return new Matricula(vehiculo, fechaRegistro, estadoPago);
    }

    

    // PA04: Monto negativo
    public void PA04MontoNegativo() {
        try {
            Matricula matricula = crearMatriculaValida();
            new Pago(matricula, "Tarjeta", -50.0, new Date());
            assert false : "Se esperaba IllegalArgumentException por monto negativo";
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    // PA05: Fecha futura
    public void PA05FechaFutura() {
        try {
            Matricula matricula = crearMatriculaValida();

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 5); // fecha futura
            Date fechaFutura = cal.getTime();

            new Pago(matricula, "Efectivo", 60.0, fechaFutura);
            assert false : "Se esperaba IllegalArgumentException por fecha futura";
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    // Método principal para ejecutar todas las pruebas
    public static void main(String[] args) {
        TestPago test = new TestPago();


        try {
            test.PA04MontoNegativo();
            System.out.println("PA04: Aprobado");
        } catch (AssertionError e) {
            System.out.println("PA04: Fallido - " + e.getMessage());
        }

        try {
            test.PA05FechaFutura();
            System.out.println("PA05: Aprobado");
        } catch (AssertionError e) {
            System.out.println("PA05: Fallido - " + e.getMessage());
        }
    }
}
