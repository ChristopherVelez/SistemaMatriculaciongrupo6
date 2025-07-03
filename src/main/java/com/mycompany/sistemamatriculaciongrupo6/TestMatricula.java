/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemamatriculaciongrupo6;

/* AITB */
public class TestMatricula extends SistemaMatriculacionGrupo6{

    
    public void TC03ListaVacia() {
        try {
            // No agregamos ninguna matrícula -> lista vacía
            consultarMatricula("ABC-1234");
            assert false : "No se lanzó IllegalStateException con lista vacía";
        } catch (IllegalStateException e) {
            assert true;
        }
    }

    public void TC04FormatoInvalido() {
        try {
            consultarMatricula("123-ABC");
            assert false : "No se lanzó IllegalArgumentException por formato inválido";
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    public void TC05PlacaNula() {
        try {
            consultarMatricula(null);
            assert false : "No se lanzó IllegalArgumentException por placa nula";
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    public static void main(String[] args) {
        TestMatricula test = new TestMatricula();
        System.out.println("      Resultado de los test      ");
        try {
            test.TC03ListaVacia();
            System.out.println("TC03: Aprobado");
        } catch (AssertionError e) {
            System.out.println("TC03: Fallido - " + e.getMessage());
        }

        try {
            test.TC05PlacaNula();
            System.out.println("TC04: Aprobado");
        } catch (AssertionError e) {
            System.out.println("TC04: Fallido - " + e.getMessage());
        }

        try {
            test.TC05PlacaNula();
            System.out.println("TC05: Aprobado");
        } catch (AssertionError e) {
            System.out.println("TC05: Fallido - " + e.getMessage());
        }
    }
}