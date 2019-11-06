package com.example.j;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Torneio extends Activity {
	private Random numeroAleatorio;
	private int vitorias = 0;
	@SuppressWarnings("unused")
	private boolean consecutivo = false;
	TextView nome, score;
	SQLiteDatabase bd;
	Cursor resultSQL;

	protected void onCreate(Bundle savedInstanceState) {
		CriaBanco();
		super.onCreate(savedInstanceState);

		chamaTorneio();
	}

	public void chamaTorneio() {

		setContentView(R.layout.activity_jokenpo);

		Button voltar = (Button) findViewById(R.id.button4);

		voltar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(Torneio.this, RequestResponse.class));
			}
		});

		nome = (TextView) findViewById(R.id.TextView200);
		score = (TextView) findViewById(R.id.TextView100);

		nome.setText(retornaNomeVencedor());
		score.setText("" + retornaPontosVencedor());

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
				vitorias = 0;
				consecutivo = false;
			} else if (jogadaPlayer == 2) {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.venceu);
				vitorias++;
				if (vitorias >= 1) {
					consecutivo = true;
					if (vitorias > retornaPontosVencedor()) {
						novoRecord();
						chamaRecorde();
					}
				}
			}

			else {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.perdeu);
				vitorias = 0;
				consecutivo = false;
			}
		}

		else if (jogadaPC == 2) {

			findViewById(R.id.imageView3).setBackgroundResource(
					R.drawable.papel);

			if (jogadaPlayer == 1) {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.perdeu);
				vitorias = 0;
				consecutivo = false;
			} else if (jogadaPlayer == 2) {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.empatou);
				vitorias = 0;
				consecutivo = false;
			} else {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.venceu);
				vitorias++;
				if (vitorias >= 1) {
					consecutivo = true;
					if (vitorias > retornaPontosVencedor()) {
						novoRecord();
						chamaRecorde();
					}
				}
			}

		}

		else if (jogadaPC == 3) {

			findViewById(R.id.imageView3).setBackgroundResource(
					R.drawable.tesoura);

			if (jogadaPlayer == 1) {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.venceu);
				vitorias++;
				if (vitorias >= 1) {
					consecutivo = true;
					if (vitorias > retornaPontosVencedor()) {
						novoRecord();
						chamaRecorde();
					}
				}
			} else if (jogadaPlayer == 2) {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.perdeu);
				vitorias = 0;
				consecutivo = false;
			} else {

				findViewById(R.id.imageView2).setBackgroundResource(
						R.drawable.empatou);
				vitorias = 0;
				consecutivo = false;
			}
		}

	}

	public void CriaBanco() {
		try {
			bd = openOrCreateDatabase("BDJOKENPO", Context.MODE_PRIVATE, null);
			bd.execSQL("CREATE TABLE IF NOT EXISTS Torneio (nome VARCHAR(15) PRIMARY KEY NOT NULL, pontos INTEGER NOT NULL);");

		} catch (Exception e) {
			e.toString();
		}
	}

	public String retornaNomeVencedor() {
		resultSQL = bd.query("Torneio", (new String[] { "nome", "pontos" }),
				null, null, null, null, null);
		resultSQL.moveToFirst();
		String nome = null;
		int pontos = 0;
		while (!resultSQL.isAfterLast()) {
			if (resultSQL.getInt(resultSQL.getColumnIndex("pontos")) > pontos) {
				nome = resultSQL.getString(resultSQL.getColumnIndex("nome"));
			}
			resultSQL.moveToNext();
		}

		return nome;
	}

	public int retornaPontosVencedor() {
		resultSQL = bd.query("Torneio", (new String[] { "nome", "pontos" }),
				null, null, null, null, null);
		resultSQL.moveToFirst();
		int pontos = 0;
		while (!resultSQL.isAfterLast()) {
			if (resultSQL.getInt(resultSQL.getColumnIndex("pontos")) > pontos) {
				pontos = resultSQL.getInt(resultSQL.getColumnIndex("pontos"));
			}
			resultSQL.moveToNext();
		}

		return pontos;
	}

	public void chamaRecorde() {

		setContentView(R.layout.activity_tela_recorde);

		Button OK = (Button) findViewById(R.id.button123);
		OK.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				EditText JOGADOR = (EditText) findViewById(R.id.editText100);
				String nomeTemp = JOGADOR.getText().toString();
				if (nomeTemp.equals("")) {
					chamaRecorde();
					erroMSG();
				} else {
					if (retornaNomeReptido(nomeTemp)) {
						bd.execSQL("DELETE FROM Torneio WHERE nome ='"
								+ nomeTemp + "'");
						bd.execSQL("INSERT INTO Torneio (nome,pontos) VALUES ('"
								+ nomeTemp + "', '" + vitorias + "');");
					} else {
						bd.execSQL("INSERT INTO Torneio (nome,pontos) VALUES ('"
								+ nomeTemp + "', '" + vitorias + "');");
					}

					vitorias = 0;// Reinicializa contagem de vitorias
					chamaTorneio();
				}
			}

		});

	}

	public boolean retornaNomeReptido(String nome) {
		resultSQL = bd.query("Torneio", (new String[] { "nome" }), null, null,
				null, null, null);
		resultSQL.moveToFirst();
		boolean condicao = false;
		while (!resultSQL.isAfterLast()) {
			String nomeTemp = resultSQL.getString(resultSQL
					.getColumnIndex("nome"));
			if (nomeTemp.equals(nome)) {
				condicao = true;
			}
			resultSQL.moveToNext();
		}

		return condicao;
	}

	public void novoRecord() {
		AlertDialog.Builder dialogo = new AlertDialog.Builder(Torneio.this);
		dialogo.setTitle("Novo Recorde");
		dialogo.setMessage("Parabéns, você quebrou o recorde!!!");
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
	}

	public void erroMSG() {
		AlertDialog.Builder dialogo = new AlertDialog.Builder(Torneio.this);
		dialogo.setTitle("NOME VAZIO");
		dialogo.setMessage("Por favor digite um nome!");
		dialogo.setNeutralButton("OK", null);
		dialogo.show();
	}

}
