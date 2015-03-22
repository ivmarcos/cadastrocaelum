package br.com.caelum.cadastro.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.com.caelum.cadastro.modelo.Aluno;

public class AlunoDAO{
	
	public static final String TABELA = "Alunos"; 
	private final DBHelper helper;

	public AlunoDAO(Context context) {
		helper = new DBHelper(context);
	}


	public void insere(Aluno aluno) {
		ContentValues values = toContentValues(aluno);
         helper.getWritableDatabase().insert(TABELA, null, values);
	}
	
	private ContentValues toContentValues(Aluno aluno) {
	    ContentValues values = new ContentValues();
	    values.put("nome", aluno.getNome());
	    values.put("telefone", aluno.getTelefone());
	    values.put("endereco", aluno.getEndereco());
	    values.put("site", aluno.getSite());
	    values.put("nota", aluno.getNota());
	    values.put("caminhoFoto", aluno.getCaminhoFoto());
	    return values;
	}
	
	public List<Aluno> getLista() {
		String sql  = "SELECT * FROM " + TABELA + ";";
		Cursor c = helper.getReadableDatabase().rawQuery(sql, null);
		List<Aluno> alunos = new ArrayList<Aluno>();
		while (c.moveToNext()) {
			Aluno aluno = new Aluno();
			aluno.setNome(c.getString(c.getColumnIndex("nome")));
			aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
			aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
			aluno.setSite(c.getString(c.getColumnIndex("site")));
			aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
			alunos.add(aluno);
		}
		return alunos;
	}
	
	public void close() {
		helper.close();
	}
	
	public void salva(Aluno aluno) {
		
	}
	
	public void altera(Aluno aluno) {
		ContentValues values = toContentValues(aluno);
		String[] args = { aluno.getId().toString() };                
	    helper.getWritableDatabase().update(TABELA, values, "id=?", args);
	}
	
	public void deletar(Aluno aluno) {
		String[] args = {aluno.getId().toString()};
		helper.getWritableDatabase().delete(TABELA, "id=?", args);
	}

}
