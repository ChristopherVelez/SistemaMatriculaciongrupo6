package com.mycompany.sistemamatriculaciongrupo6;

/**
 *
 * @author Pc
 */
public class TestRegistrarPropietario {
    public static void main(String[] args) {

        /* --------------------------------------------------------------
         * 1) Tabla de casos de prueba  (6 columnas exactas por fila)
         * -------------------------------------------------------------- */
        Object[][] casos = {
            { "TC01 – Propietario válido",
              "Ana Pérez",    "0912345678", "Guayaquil", "0987654321", false },

            { "TC02 – Nombre vacío",
              "",             "0912345678", "Guayaquil", "0987654321", true  },

            { "TC03 – Nombre con números",
              "Pedro123",     "0912345678", "Guayaquil", "0987654321", true  },

            { "TC04 – Cédula <10 dígitos",
              "Carlos",       "092345",     "Guayaquil", "0987654321", true  },

            { "TC05 – Cédula con letras",
              "María",        "09A234567",  "Guayaquil", "0987654321", true  }
        };

        /* --------------------------------------------------------------
         * 2) Ejecución automática
         * -------------------------------------------------------------- */
        System.out.println("\n========= PRUEBAS registrarPropietario() (" +
                           casos.length + " casos) =========");

        SistemaMatriculacionGrupo6 sistema = new SistemaMatriculacionGrupo6();

        for (Object[] c : casos) {

            // limpiar lista estática antes de cada iteración
            if (SistemaMatriculacionGrupo6.propietarioslist != null) {
                SistemaMatriculacionGrupo6.propietarioslist.clear();
            }

            String  id        = (String)  c[0];
            String  nombre    = (String)  c[1];
            String  cedula    = (String)  c[2];
            String  direccion = (String)  c[3];
            String  telefono  = (String)  c[4];
            boolean esperaEx  = (Boolean) c[5];

            boolean paso;
            try {
                sistema.registrarPropietario(nombre, cedula, direccion, telefono);
                paso = !esperaEx;      // OK si no se esperaba excepción
            } catch (Exception ex) {
                paso = esperaEx && ex instanceof IllegalArgumentException;
            }

            imprimirResultado(id, paso);
        }
    }

    /* --------------------------------------------------------------
     * 3) Salida uniforme
     * -------------------------------------------------------------- */
    private static void imprimirResultado(String id, boolean ok) {
        System.out.printf("%-40s : %s%n", id, ok ? "OK" : "FALLÓ");
    }
}

