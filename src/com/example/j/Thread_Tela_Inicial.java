package com.example.j;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Thread_Tela_Inicial extends Activity {
	protected boolean _active = true;
	protected int _splashTime = 3000;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_inicial);

		Thread splashTread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
					while (_active && (waited < _splashTime)) {
						sleep(100);
						if (_active) {
							waited += 100;
						}
					}
				} catch (InterruptedException e) {

				} finally {
					startActivity(new Intent(Thread_Tela_Inicial.this,
							Thread_Tela_Inicial_2.class));
					finish();
				}
			}
		};
		splashTread.start();
	}
}
