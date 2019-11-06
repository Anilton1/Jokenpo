package com.example.j;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class Jokenpo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		chamaInicio();
	}
	public void chamaInicio() {

		setContentView(R.layout.activity_tela_inicio);

		Button inicio = (Button) findViewById(R.id.button11);

		inicio.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(Jokenpo.this, RequestResponse.class));
			}
		});

		Button sair = (Button) findViewById(R.id.button12);

		sair.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);

			}

		});

	}

}
