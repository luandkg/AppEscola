package com.luandkg.czilda4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.luandkg.czilda4.atividades.SobreActivity;
import com.luandkg.czilda4.escola.tempo.BimestreCorrente;
import com.luandkg.czilda4.escola.CED1_Calendario;
import com.luandkg.czilda4.escola.atualizador.Inicializador;
import com.luandkg.czilda4.escola.horario.Avisador;
import com.luandkg.czilda4.escola.Professores;
import com.luandkg.czilda4.libs.sigmacollection.SigmaCollection;
import com.luandkg.czilda4.utils.FS;
import com.luandkg.czilda4.databinding.ActivityDrawlerBinding;
import com.luandkg.czilda4.utils.Internet;
import com.luandkg.czilda4.utils.Notificar;

import java.util.HashSet;

public class ActivityDrawler extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDrawlerBinding binding;
    private Avisador mAvisador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FS.pedirPermissoes(this);
        FS.verifyStoragePermissions(this);
        Internet.pedirPermissoes(this);

        // DEFINIR BIMESTRE ATUAL
        BimestreCorrente.SET(CED1_Calendario.SEGUNDO_BIMESTRE());


        binding = ActivityDrawlerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarActivityDrawler.toolbar);


        // CRIAR BOTAO MAGICO
        //  binding.appBarActivityDrawler.fab.setOnClickListener(new View.OnClickListener() {
        //       @Override
        //      public void onClick(View view) {
        //          Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                   .setAction("Action", null).show();
        //        }
        //  });

        Versionador v = new Versionador();

        System.out.println("VERSAO CORRENTE :: " + v.getVersao());
        System.out.println("\t - Builds     :: " + v.getBuilds());

        System.out.println("\t - Inicio     :: " + v.getIniciado());
        System.out.println("\t - Fim        :: " + v.getUltima());

        //   Inicializador.zerar();

        Local.organizarPastas();

        // Inicializador.zerar();

        Inicializador.init(Professores.getProfessorCorrente().getQuaisTurmas());

        if (Versionador.isTeste()) {
            FakerSchool.init();
        }

        Notificar.criarCanal(getBaseContext(), "AVISOS", "SISTEMA DE AVISOS");


        // PAGINAS DO DRAWLER
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        HashSet<Integer> gaveta = new HashSet<Integer>();

        gaveta.add(R.id.nav_hoje);
        gaveta.add(R.id.nav_atualizacoes);
        gaveta.add(R.id.nav_segundobimestre);
        gaveta.add(R.id.nav_chamads_visualizador_geral);
        gaveta.add(R.id.nav_bimestre);
        gaveta.add(R.id.nav_avaliador_geral);
        gaveta.add(R.id.nav_boletins);
        gaveta.add(R.id.nav_turmas);
        gaveta.add(R.id.nav_resultador);

        mAppBarConfiguration = new AppBarConfiguration.Builder(gaveta).setOpenableLayout(drawer).build();


        // CONTROLADORES DE DRAWLER
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_activity_drawler);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        mAvisador = new Avisador(this.getBaseContext(), Professores.getProfessorCorrente());
        mAvisador.run();

        SigmaCollection.organizar("@ESCOLA::ALUNOS");
        SigmaCollection.organizar("@ESCOLA->CACHE::NOTAS");


        //System.out.println("REDIZZ TO : " + Redizz.to("LUAN ALVES FREITAS"));
        //String voltando = Redizz.to("LUAN ALVES FREITAS");
        //  System.out.println("REDIZZ FROM : " + Redizz.from(voltando));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_drawler, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {

            Intent intent = new Intent(this.getBaseContext(), SobreActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_activity_drawler);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }


}