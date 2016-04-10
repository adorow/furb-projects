
public final class Grupos {

    public static final Grupo GrupoA = new Grupo(
            "Grupo A",
            new Selecao[]{Selecoes.Franca, Selecoes.Mexico, Selecoes.Uruguai, Selecoes.AfricaDoSul},
            new Jogo[]{
                new Jogo(Selecoes.AfricaDoSul, Selecoes.Mexico),
                new Jogo(Selecoes.Uruguai, Selecoes.Franca),
                new Jogo(Selecoes.AfricaDoSul, Selecoes.Uruguai),
                new Jogo(Selecoes.Franca, Selecoes.Mexico),
                new Jogo(Selecoes.Mexico, Selecoes.Uruguai),
                new Jogo(Selecoes.Franca, Selecoes.AfricaDoSul)
            });
    public static final Grupo GrupoB = new Grupo(
            "Grupo B",
            new Selecao[]{Selecoes.Argentina, Selecoes.CoreiaDoSul, Selecoes.Grecia, Selecoes.Nigeria},
            new Jogo[]{new Jogo(Selecoes.CoreiaDoSul, Selecoes.Grecia),
                new Jogo(Selecoes.Argentina, Selecoes.Nigeria),
                new Jogo(Selecoes.Argentina, Selecoes.CoreiaDoSul),
                new Jogo(Selecoes.Grecia, Selecoes.Nigeria),
                new Jogo(Selecoes.Nigeria, Selecoes.CoreiaDoSul),
                new Jogo(Selecoes.Grecia, Selecoes.Argentina)
            });
    public static final Grupo GrupoC = new Grupo("Grupo C", new Selecao[]{Selecoes.Argelia, Selecoes.Eslovenia, Selecoes.EstadosUnidos, Selecoes.Inglaterra},
            new Jogo[]{new Jogo(Selecoes.Inglaterra, Selecoes.EstadosUnidos),
                new Jogo(Selecoes.Argelia, Selecoes.Eslovenia),
                new Jogo(Selecoes.Eslovenia, Selecoes.EstadosUnidos),
                new Jogo(Selecoes.Inglaterra, Selecoes.Argelia),
                new Jogo(Selecoes.Eslovenia, Selecoes.Inglaterra),
                new Jogo(Selecoes.EstadosUnidos, Selecoes.Argelia)});
    public static final Grupo GrupoD = new Grupo("Grupo D", new Selecao[]{Selecoes.Alemanha, Selecoes.Australia, Selecoes.Gana, Selecoes.Servia},
            new Jogo[]{new Jogo(Selecoes.Servia, Selecoes.Gana),
                new Jogo(Selecoes.Alemanha, Selecoes.Australia),
                new Jogo(Selecoes.Alemanha, Selecoes.Servia),
                new Jogo(Selecoes.Gana, Selecoes.Australia),
                new Jogo(Selecoes.Gana, Selecoes.Alemanha),
                new Jogo(Selecoes.Australia, Selecoes.Servia)});
    public static final Grupo GrupoE = new Grupo("Grupo E", new Selecao[]{Selecoes.Camaroes, Selecoes.Dinamarca, Selecoes.Holanda, Selecoes.Japao},
            new Jogo[]{new Jogo(Selecoes.Holanda, Selecoes.Dinamarca),
                new Jogo(Selecoes.Japao, Selecoes.Camaroes),
                new Jogo(Selecoes.Holanda, Selecoes.Japao),
                new Jogo(Selecoes.Camaroes, Selecoes.Dinamarca),
                new Jogo(Selecoes.Dinamarca, Selecoes.Japao),
                new Jogo(Selecoes.Camaroes, Selecoes.Holanda)});
    public static final Grupo GrupoF = new Grupo("Grupo F", new Selecao[]{Selecoes.Eslovaquia, Selecoes.Italia, Selecoes.NovaZelandia, Selecoes.Paraguai},
            new Jogo[]{new Jogo(Selecoes.Eslovaquia, Selecoes.Italia),
                new Jogo(Selecoes.NovaZelandia, Selecoes.Eslovaquia),
                new Jogo(Selecoes.Eslovaquia, Selecoes.Paraguai),
                new Jogo(Selecoes.Italia, Selecoes.NovaZelandia),
                new Jogo(Selecoes.Eslovaquia, Selecoes.Italia),
                new Jogo(Selecoes.Paraguai, Selecoes.NovaZelandia)});
    public static final Grupo GrupoG = new Grupo("Grupo G", new Selecao[]{Selecoes.Brasil, Selecoes.CoreiaDoNorte, Selecoes.CostaDoMarfim, Selecoes.Portugal},
            new Jogo[]{new Jogo(Selecoes.CostaDoMarfim, Selecoes.Portugal),
                new Jogo(Selecoes.Brasil, Selecoes.CoreiaDoNorte),
                new Jogo(Selecoes.Brasil, Selecoes.CostaDoMarfim),
                new Jogo(Selecoes.Portugal, Selecoes.CoreiaDoNorte),
                new Jogo(Selecoes.Portugal, Selecoes.Brasil),
                new Jogo(Selecoes.CoreiaDoNorte, Selecoes.CostaDoMarfim)});
    public static final Grupo GrupoH = new Grupo("Grupo H", new Selecao[]{Selecoes.Chile, Selecoes.Espanha, Selecoes.Honduras, Selecoes.Suica},
            new Jogo[]{new Jogo(Selecoes.Honduras, Selecoes.Chile),
                new Jogo(Selecoes.Espanha, Selecoes.Suica),
                new Jogo(Selecoes.Chile, Selecoes.Suica),
                new Jogo(Selecoes.Espanha, Selecoes.Honduras),
                new Jogo(Selecoes.Chile, Selecoes.Espanha),
                new Jogo(Selecoes.Suica, Selecoes.Honduras)});

    public Grupo getGrupoA() {
        return GrupoA;
    }

    public Grupo getGrupoB() {
        return GrupoB;
    }

    public Grupo getGrupoC() {
        return GrupoC;
    }

    public Grupo getGrupoD() {
        return GrupoD;
    }

    public Grupo getGrupoE() {
        return GrupoE;
    }

    public Grupo getGrupoF() {
        return GrupoF;
    }

    public Grupo getGrupoG() {
        return GrupoG;
    }

    public Grupo getGrupoH() {
        return GrupoH;
    }

    public Grupo[] getGrupos() {
        return new Grupo[] { GrupoA, GrupoB, GrupoC, GrupoD, GrupoE, GrupoF, GrupoG, GrupoH };
    }

    public String salvarAlteracoes() {
        for (Grupo grupo : getGrupos()) {
            grupo.recalcularResultados();
        }
        return "main";
    }

}
