package edu.coltec.dupla.rafaelgabriel.trabalhofinal.utils;

public class ReceitasInit {

    public static String modo = "Bata as claras em neve e reserve.\n" +
            "Misture as gemas, a margarina e o açúcar até obter uma massa homogênea.\n" +
            "Acrescente o leite e a farinha de trigo aos poucos, sem parar de bater.\n" +
            "Por último, adicione as claras em neve e o fermento.\n" +
            "Despeje a massa em uma forma grande de furo central untada e enfarinhada.\n" +
            "Asse em forno médio 180 °C, preaquecido, por aproximadamente 40 minutos ou ao furar o bolo com um garfo, este saia limpo.\n";

    public static String modo2 = "Em uma panela, aqueça o óleo e refogue a cebola e o pimentão. Deixe cozinhar por 5 minutos.\n" +
            "Acrescente a carne-seca, os tomates, o MAGGI MEU SEGREDO e a água quente.\n" +
            "Deixe cozinhar por cerca de 10 minutos ou até que os vegetais estejam macios.\n" +
            "Polvilhe o coentro e sirva.";

    public static String modo3 = "Coloque a água para ferver\n" +
            "Quando estiver fervendo, coloque o sal e 1 colher de óleo.\n" +
            "Quando o macarrão estiver mole, tire do fogo e escorra a água no escorredor de macarrão.\n" +
            "Lave o macarrão.\n" +
            "Faça o molho de sua preferência em outra panela.\n" +
            "Para o molho: \nColoque o óleo e o alho na panela\n" +
            "Mexa até o alho ficar dourado\n" +
            "Coloque o macarrão, misture e sirva.\n" +
            "Fica muito bom se colocar molho branco.";

    public static final String CREATE_RECEITA1 = "INSERT INTO receitas (id, name, autor, ingredientes, mododepreparo, dificuldade, categorias, fotoReceita)\n" +
            " VALUES (1, \"Bolo Simples\", \"Maria Vechi\", \n" +
            "\"2 xícaras de açucar#3 xícaras de farinha de trigo#4 colheres de sopa com margarina#3 ovos#1 e 1/2 xícara de leite#1 colher de sopa bem cheia de fermento em pó\", \n\"" +
            modo + "\", 3, \"Bolos#Lanches\",\"BoloSimples.jpeg\");";

    public static final String CREATE_RECEITA2 = "INSERT INTO receitas (id, name, autor, ingredientes, mododepreparo, dificuldade, categorias, fotoReceita)\n" +
            " VALUES (2, \"Carne Seca Acebolada\", \"Receitas Nestle\", \n" +
            "\"Meia Colher de sopa de óleo#1 cebola grande em pétalas#1 pimentáo vermelho picado#400g de carne-seca dessalgada, cozida e desfiada" +
            "#2 tomates sem sementes, picados#1 sache de Tomates#\", \n\"" +
            modo2 + "\", 2, \"Carnes\",\"CarneSecaAcebolada.jpeg\");";

    public static final String CREATE_RECEITA3 = "INSERT INTO receitas (id, name, autor, ingredientes, mododepreparo, dificuldade, categorias, fotoReceita)\n" +
            " VALUES (3, \"Macarrao Básico\", \"Kelly/Tudo Gostoso\", \n" +
            "\"500g de macarrao parafuso ou fidelinho#2 litros de água#1 colher de sal#1 colher de sopa de óleo#2 colheres de óleo#6 dentes de alho amassados\",\n\"" +
            modo3 + "\", 1, \"Massas\",\"MacarraoBasico.jpeg\");";

}
