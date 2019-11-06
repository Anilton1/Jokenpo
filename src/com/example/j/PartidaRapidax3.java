package com.example.j;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PartidaRapidax3 extends Activity{
	private Random numeroAleatorio;
	private int jogador, pc;
	TextView player, PC ;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		chamaTorneio();
	}
	public void chamaTorneio() {

		setContentView(R.layout.activity_jokenpo);

		Button voltar = (Button) findViewById(R.id.button4);

		voltar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(PartidaRapidax3.this,
						RequestResponse.class));
				
			}
		});

		player = (TextView) findViewById(R.id.TextView200);
		PC = (TextView) findViewById(R.id.TextView100);
		player.setText("0");
		PC.setText("0");

		numeroAleatorio = new Random();

		Button pedra = (Button) findViewById(R.id.button028);

		pedra.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				findViewById(R.id.imageView1).setBackgroundResource(
						R.drawable.pedra);

				realizaJogadaPC(1);

			}

		});

		Button papel = (Button) findViewById(R.id.button2);

		papel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				findViewById(R.id.imageView1).setBackgroundResource(
						R.drawable.papel);

				realizaJogadaPC(2);

			}

		});
		Button tesoura = (Button) findViewById(R.id.button3);

		tesoura.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				findViewById(R.id.imageView1).setBackgroundResource(
						R.drawable.tesoura);

				realizaJogadaPC(3);

			}

		});
	}

	public void realizaJogadaPC(int jogadaPlayer) {

		int jogadaPC;

		jogadaPC = numeroAleatorio.nextInt(3) + 1;

		if (jogadaPC == 1) {

			findViewById(R.id.imageView3).setBackgroundResource(
					R.drawable.pedra);

			if (jogadaPlayer == 1) {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.empatou);
			} else if (jogadaPlayer == 2) {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.venceu);
				++jogador;
				if (jogador == 3) {
					ganhou();
					}
			}

			else {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.perdeu);
				++pc;
				if(pc == 3){
					perdeu();
				}
			}
		}

		else if (jogadaPC == 2) {

			findViewById(R.id.imageView3).setBackgroundResource(
					R.drawable.papel);

			if (jogadaPlayer == 1) {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.perdeu);
			++pc;
			if(pc == 3){
				perdeu();
			}
			} else if (jogadaPlayer == 2) {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.empatou);
			} else {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.venceu);
				++jogador;
				if (jogador == 3) {
					ganhou();
				}
			}

		}

		else if (jogadaPC == 3) {

			findViewById(R.id.imageView3).setBackgroundResource(
					R.drawable.tesoura);

			if (jogadaPlayer == 1) {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.venceu);
				++jogador;
				if (jogador == 3) {
					ganhou();
				}
			} else if (jogadaPlayer == 2) {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.perdeu);
				++pc;
				if(pc == 3){
					perdeu();
				}
			} else {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.empatou);
			}
		}
		player.setText(""+jogador);
		PC.setText(""+pc);
	}
	
	public void ganhou() {
		jogador=0;
		pc=0;
		AlertDialog.Builder dialogo = new AlertDialog.Builder(PartidaRapidax3.this);
		dialogo.setTitle("Vencedor");
		dialogo.setMessage("Parabéns, você venceu!!!");
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
	}
	
	public void perdeu() {
		jogador=0;
		pc=0;
		AlertDialog.Builder dialogo = new AlertDialog.Builder(PartidaRapidax3.this);
		dialogo.setTitle("Perdedor");
		dialogo.setMessage("Foi mal, você perdeu!!!");
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
	}
	
}
