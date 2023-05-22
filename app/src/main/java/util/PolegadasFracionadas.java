package util;

public class PolegadasFracionadas {

    String retornoPolegada;


    public static String polegadasFracionadas(double polegadas){

        int parteInteira = (int) polegadas;
        double parteDecimal = polegadas - parteInteira;
        int numerador = (int) (parteDecimal * 16);
        int denominador = 16;

        // Simplificar a fração, caso possível
        while (numerador % 2 == 0 && denominador % 2 == 0) {
            numerador /= 2;
            denominador /= 2;
        }

        if (numerador % 3 == 0 && denominador % 3 == 0) {
            numerador /= 3;
            denominador /= 3;
        }

        if (polegadas > 1){
            return parteInteira + " " + numerador + "/" + denominador ;
        }
        else {
            return  numerador + "/" + denominador ;
        }





    }


}
