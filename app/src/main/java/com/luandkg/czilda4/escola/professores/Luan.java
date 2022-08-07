package com.luandkg.czilda4.escola.professores;

import com.luandkg.czilda4.escola.CED1_Calendario;
import com.luandkg.czilda4.escola.horario.AtividadeEspecial;
import com.luandkg.czilda4.escola.horario.Horario;
import com.luandkg.czilda4.escola.organizacao.Professor;
import com.luandkg.czilda4.libs.tempo.Calendario;
import com.luandkg.czilda4.libs.tempo.Data;
import com.luandkg.czilda4.libs.tempo.DiaSemanal;
import com.luandkg.czilda4.libs.tempo.TempoEstampa;
import com.luandkg.czilda4.libs.tempo.TempoEstampaDuracao;


public class Luan {

    public static Professor getLuan() {

        Professor eProfessor = new Professor();


        eProfessor.repor("2022_04_09","QUINTA");

        CED1_Calendario cal = new CED1_Calendario();

        eProfessor.adicionar_ferias(Calendario.filtrar(  cal.getAno(),new Data(2022,7,10, DiaSemanal.Domingo),new Data(2022,7,29,DiaSemanal.Domingo)));

        eProfessor.setSiglaComNome("LUAN", "Luan Freitas");

        AtividadeEspecial ca = eProfessor.criarAtividade("TERCA", "CA", "Coordenação de Área", "Estou coordenando em área ....", new TempoEstampa(9, 0), new TempoEstampa(12, 0));
        AtividadeEspecial cc = eProfessor.criarAtividade("QUARTA", "CC", "Coordenação Coletiva", "Estou em coordenação coletiva ....", new TempoEstampa(9, 0), new TempoEstampa(12, 0));
        AtividadeEspecial ci = eProfessor.criarAtividade("QUINTA", "CI", "Coordenação Individual", "Estou coordenando individualmente ....", new TempoEstampa(9, 0), new TempoEstampa(12, 0));

        ca.mostrarIndo("Estou indo para coordenação de área....");
        cc.mostrarIndo("Estou indo para coordenação coletiva ....");
        ci.mostrarIndo("Estou indo para coordenação de individual....");


        eProfessor.definirAlmoco(new TempoEstampa(12, 0), new TempoEstampa(12, 45));

        eProfessor.criarCargaDeTrabalho(Calendario.SEGUNDA, new TempoEstampa(13, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.TERCA, new TempoEstampa(9, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.QUARTA, new TempoEstampa(9, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.QUINTA, new TempoEstampa(9, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.SEXTA, new TempoEstampa(13, 0), new TempoEstampa(18, 0));


        // HORARIOS

        TempoEstampaDuracao PRIMEIRO = new TempoEstampaDuracao(new TempoEstampa(13, 0), new TempoEstampa(13, 50));
        TempoEstampaDuracao SEGUNDO = new TempoEstampaDuracao(new TempoEstampa(13, 50), new TempoEstampa(14, 35));
        TempoEstampaDuracao TERCEIRO = new TempoEstampaDuracao(new TempoEstampa(14, 35), new TempoEstampa(15, 20));

        TempoEstampaDuracao INTERVALO = new TempoEstampaDuracao(new TempoEstampa(15, 20), new TempoEstampa(15, 40));

        TempoEstampaDuracao QUARTO = new TempoEstampaDuracao(new TempoEstampa(15, 40), new TempoEstampa(16, 30));
        TempoEstampaDuracao QUINTO = new TempoEstampaDuracao(new TempoEstampa(16, 30), new TempoEstampa(17, 15));
        TempoEstampaDuracao SEXTO = new TempoEstampaDuracao(new TempoEstampa(17, 15), new TempoEstampa(18, 0));


        // DISCIPLINAS

        String PD = "PD";
        String CN = "CN";


        // SEGUNDA
        eProfessor.definirAulaSegunda_Simples("9E", CN, Horario.entrar(PRIMEIRO), Horario.sair(PRIMEIRO));
        eProfessor.definirAulaSegunda_Simples("9G", CN, Horario.entrar(SEGUNDO), Horario.sair(SEGUNDO));
        eProfessor.definirAulaSegunda_Simples("9D", CN, Horario.entrar(TERCEIRO), Horario.sair(TERCEIRO));
        eProfessor.definirAulaSegunda_Intervalo(Horario.entrar(INTERVALO), Horario.sair(INTERVALO));
        eProfessor.definirAulaSegunda_Simples("9F", CN, Horario.entrar(QUARTO), Horario.sair(QUARTO));
        eProfessor.definirAulaSegunda_Dupla("9C", CN, Horario.entrar(QUINTO), Horario.sair(SEXTO));


        // TERCA
        eProfessor.definirAulaTerca_Simples("9E", CN, Horario.entrar(PRIMEIRO), Horario.sair(PRIMEIRO));
        eProfessor.definirAulaTerca_Simples("9C", CN, Horario.entrar(SEGUNDO), Horario.sair(SEGUNDO));
        eProfessor.definirAulaTerca_Simples("9B", CN, Horario.entrar(TERCEIRO), Horario.sair(TERCEIRO));
        eProfessor.definirAulaTerca_Intervalo(Horario.entrar(INTERVALO), Horario.sair(INTERVALO));
        eProfessor.definirAulaTerca_Simples("9A", CN, Horario.entrar(QUARTO), Horario.sair(QUARTO));
        eProfessor.definirAulaTerca_Simples("9D", CN, Horario.entrar(QUINTO), Horario.sair(QUINTO));
        eProfessor.definirAulaTerca_Simples("9G", CN, Horario.entrar(SEXTO), Horario.sair(SEXTO));


        // QUARTA
        eProfessor.definirAulaQuarta_Simples("9B", CN, Horario.entrar(PRIMEIRO), Horario.sair(PRIMEIRO));
        eProfessor.definirAulaQuarta_Dupla("9D", CN, Horario.entrar(SEGUNDO), Horario.sair(TERCEIRO));
        eProfessor.definirAulaQuarta_Intervalo(Horario.entrar(INTERVALO), Horario.sair(INTERVALO));
        eProfessor.definirAulaQuarta_Dupla("9G", CN, Horario.entrar(QUARTO), Horario.sair(QUINTO));
        eProfessor.definirAulaQuarta_Simples("8D", PD, Horario.entrar(SEXTO), Horario.sair(SEXTO));


        // QUINTA
        eProfessor.definirAulaQuinta_Simples("9F", CN, Horario.entrar(PRIMEIRO), Horario.sair(PRIMEIRO));
        eProfessor.definirAulaQuinta_Dupla("9E", CN, Horario.entrar(SEGUNDO), Horario.sair(TERCEIRO));
        eProfessor.definirAulaQuinta_Intervalo(Horario.entrar(INTERVALO), Horario.sair(INTERVALO));
        eProfessor.definirAulaQuinta_Dupla("9A", CN, Horario.entrar(QUARTO), Horario.sair(QUINTO));
        eProfessor.definirAulaQuinta_Simples("9C", CN, Horario.entrar(SEXTO), Horario.sair(SEXTO));


        // SEXTA
        eProfessor.definirAulaSexta_Simples("9A", CN, Horario.entrar(PRIMEIRO), Horario.sair(PRIMEIRO));
        eProfessor.definirAulaSexta_Dupla("9B", CN, Horario.entrar(SEGUNDO), Horario.sair(TERCEIRO));
        eProfessor.definirAulaSexta_Intervalo(Horario.entrar(INTERVALO), Horario.sair(INTERVALO));
        eProfessor.definirAulaSexta_Simples("7C", PD, Horario.entrar(QUARTO), Horario.sair(QUARTO));
        eProfessor.definirAulaSexta_Dupla("9F", CN, Horario.entrar(QUINTO), Horario.sair(SEXTO));


        eProfessor.nesseDia("TERCA", "9A", 1);
        eProfessor.nesseDia("QUINTA", "9A", 1);
        eProfessor.nesseDia("SEXTA", "9A", 1);


        eProfessor.nesseDia("TERCA", "9B", 1);
        eProfessor.nesseDia("QUARTA", "9B", 1);
        eProfessor.nesseDia("SEXTA", "9B", 1);

        eProfessor.nesseDia("SEGUNDA", "9C", 1);
        eProfessor.nesseDia("TERCA", "9C", 1);
        eProfessor.nesseDia("QUINTA", "9C", 1);

        eProfessor.nesseDia("SEGUNDA", "9D", 1);
        eProfessor.nesseDia("TERCA", "9D", 1);
        eProfessor.nesseDia("QUARTA", "9D", 1);

        eProfessor.nesseDia("SEGUNDA", "9E", 1);
        eProfessor.nesseDia("TERCA", "9E", 1);
        eProfessor.nesseDia("QUINTA", "9E", 1);

        eProfessor.nesseDia("SEGUNDA", "9F", 1);
        eProfessor.nesseDia("QUINTA", "9F", 1);
        eProfessor.nesseDia("SEXTA", "9F", 1);


        eProfessor.nesseDia("SEGUNDA", "9G", 1);
        eProfessor.nesseDia("TERCA", "9G", 1);
        eProfessor.nesseDia("QUARTA", "9G", 1);

        eProfessor.nesseDia("QUARTA", "8D", 1);
        eProfessor.nesseDia("SEXTA", "7C", 1);

        eProfessor.turma_adicionar("9A","CN");
        eProfessor.turma_adicionar("9B","CN");
        eProfessor.turma_adicionar("9C","CN");
        eProfessor.turma_adicionar("9D","CN");
        eProfessor.turma_adicionar("9E","CN");
        eProfessor.turma_adicionar("9F","CN");
        eProfessor.turma_adicionar("9G","CN");

        eProfessor.turma_adicionar("7C","PD");
        eProfessor.turma_adicionar("8D","PD");


        return eProfessor;
    }

}
