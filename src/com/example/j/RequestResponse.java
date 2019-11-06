package com.example.j;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RequestResponse extends Activity {
	int idPartidaRapida;
	private static final String[] PartidaRapida = {"Partida Rápida","x3","x5"};
	ArrayAdapter<String> PartidaRapida_Array;
	Spinner PartidaRapidaSPN;
	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		chamaPrincipal();
	}
	
	
	public  void chamaPrincipal() {

		setContentView(R.layout.activity_tela_principal);

		Button torneio = (Button) findViewById(R.id.button22);
		torneio.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(RequestResponse.this,
						Torneio.class));

			}

		});
		
		PartidaRapida_Array = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, PartidaRapida);
		PartidaRapidaSPN = (Spinner) findViewById(R.id.spinner1);
		PartidaRapidaSPN.setAdapter(PartidaRapida_Array);
		
		Button partidarapida = (Button) findViewById(R.id.button028);
		partidarapida.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				escolhePartidaRapida();
			}

		});
		
		
		Button sair = (Button) findViewById(R.id.button23);

		sair.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);

			}

		});

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.jokenpo, menu);

		return true;

	}
	
	public void escolhePartidaRapida(){
		idPartidaRapida = PartidaRapidaSPN.getSelectedItemPosition();
		
		if(idPartidaRapida == 1){
			startActivity(new Intent(RequestResponse.this,
					PartidaRapidax3.class));
		}else if(idPartidaRapida == 2){
			startActivity(new Intent(RequestResponse.this,
					PartidaRapidax5.class));
			
		}
		else {
			
				AlertDialog.Builder dialogo = new AlertDialog.Builder(RequestResponse.this);
				dialogo.setTitle("Escolha Inválida");
				dialogo.setMessage("Por favor, escolha um modo de partida!");
				dialogo.setNeutralButton("OK", null);
				dialogo.show();
			
		}
		}
}
