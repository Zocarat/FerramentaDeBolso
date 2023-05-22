package util;

public class PolegadasFracionadas {

    String retornoPolegada;


    public static String polegadasFracionadas(double milimetros){

        double polegadas = milimetros / 25.4;
        int parteInteira = (int) polegadas;
        double parteDecimal = polegadas - parteInteira;
        int numerador = (int) (parteDecimal * 16);
        int denominador = 16;


        // Simplificar a fração, caso possível
        if (numerador % 2 == 0 && denominador % 2 == 0) {
            numerador /= 2;
            denominador /= 2;
        }

        if (numerador % 3 == 0 && denominador % 3 == 0) {
            numerador /= 3;
            denominador /= 3;
        }


        return parteInteira + " " + numerador + "/" + denominador ;

    }


}
