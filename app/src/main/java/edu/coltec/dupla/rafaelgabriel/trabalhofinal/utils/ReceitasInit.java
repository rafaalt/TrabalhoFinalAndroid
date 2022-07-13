package edu.coltec.dupla.rafaelgabriel.trabalhofinal.utils;

public class ReceitasInit {

    public static String modo = "Bata as claras em neve e reserve.\n" +
            "Misture as gemas, a margarina e o açúcar até obter uma massa homogênea.\n" +
            "Acrescente o leite e a farinha de trigo aos poucos, sem parar de bater.\n" +
            "Por último, adicione as claras em neve e o fermento.\n" +
            "Despeje a massa em uma forma grande de furo central untada e enfarinhada.\n" +
            "Asse em forno médio 180 °C, preaquecido, por aproximadamente 40 minutos ou ao furar o bolo com um garfo, este saia limpo.\n";

    public static final String CREATE_RECEITA1 = "INSERT INTO receitas (id, name, autor, ingredientes, mododepreparo, dificuldade, categorias, fotoReceita)\n" +
            " VALUES (1, \"Bolo Simples\", \"Maria Vechi\", \n" +
            "\"2 xícaras de açucar#3 xícaras de farinha de trigo#4 colheres de sopa com margarina#3 ovos#1 e 1/2 xícara de leite#1 colher de sopa bem cheia de fermento em pó\", \n\"" +
            modo + "\", 3, \"Bolos#Lanches\",\"BoloSimples.jpeg\");";

    public static final String CREATE_RECEITA2 = "INSERT INTO receitas (id, name, autor, ingredientes, mododepreparo, dificuldade, categorias, fotoReceita)\n" +
            " VALUES (2, \"Bolo Simples\", \"Maria Vechi\", \n" +
            "\"2 xícaras de açucar#3 xícaras de farinha de trigo#4 colheres de sopa com margarina#3 ovos#1 e 1/2 xícara de leite#1 colher de sopa bem cheia de fermento em pó\", \n\"" +
            modo + "\", 3, \"Bolos#Lanches\",\"BoloSimples.jpeg\");";

    public static final String CREATE_RECEITA3 = "INSERT INTO receitas (id, name, autor, ingredientes, mododepreparo, dificuldade, categorias, fotoReceita)\n" +
            " VALUES (3, \"Bolo Simpless\", \"Maria Vechafdii\", \n" +
            "\"2 xícaras de açucar#3 xícaras de farinha de trigo#4 colheres de sopa com margarina#3 ovos#1 e 1/2 xícara de leite#1 colher de sopa bem cheia de fermento em pó\", \n\"" +
            modo + "\", 3, \"Bolos#Lanches\",\"BoloSimples.jpeg\");";

}
