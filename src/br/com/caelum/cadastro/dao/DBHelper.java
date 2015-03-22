package br.com.caelum.cadastro.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	public DBHelper(Context context) {
		super(context, "CadastroCaelum", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("CadastroCaelum", "Criando tabela....");
		String sql = "" 
				+ "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, " 
                + "nome TEXT UNIQUE NOT NULL, " 
                + "telefone TEXT, " 
                + "endereco TEXT, "
                + "site TEXT, " 
                + "nota REAL, " 
                + "caminhoFoto TEXT);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + AlunoDAO.TABELA;
		db.execSQL(sql);
	}

}
