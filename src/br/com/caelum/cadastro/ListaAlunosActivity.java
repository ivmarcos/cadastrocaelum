package br.com.caelum.cadastro;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;


public class ListaAlunosActivity extends Activity{
	
	private ListView listaAlunos;
	private Aluno alunoSelecionado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.listagem_alunos);

		listaAlunos = (ListView) findViewById(R.id.lista);
		registerForContextMenu(listaAlunos);
		
		this.carregaLista();
		
		listaAlunos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				alunoSelecionado = (Aluno) adapter.getItemAtPosition(position);
				Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
				intent.putExtra(Extras.ALUNO_SELECIONADO, alunoSelecionado);
				startActivity(intent);				
			}
			
		});
		
		listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int position,
					long id) {

				
				return false;
			}
		});
	}
	
	private void carregaLista() {
		AlunoDAO dao = new AlunoDAO(this);
		List<Aluno> alunos = dao.getLista();
		ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
		listaAlunos.setAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();	
		switch (id) {
		case R.id.menu_novo:
			Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
			startActivity(intent);
			return false;
		default :
			return super.onOptionsItemSelected(item);
		}
	}
	
	 @Override
     public void onCreateContextMenu(ContextMenu menu, View v,
         ContextMenuInfo menuInfo) {

         MenuItem ligar = menu.add("Ligar");
         Intent intentLigar  = new Intent(Intent.ACTION_CALL);
         intentLigar.setData(Uri.parse("tel:" + 11559999));
         ligar.setIntent(intentLigar);
         
         
         menu.add("Enviar SMS");
         menu.add("Achar no Mapa");
         menu.add("Navegar no site");

         MenuItem deletar = menu.add("Deletar");
         deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
             @Override
             public boolean onMenuItemClick(MenuItem item) {
                 AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                 dao.deletar(alunoSelecionado);
                 dao.close();

                 carregaLista();
                 return false;
             }
         });

         menu.add("Enviar E-mail");
     }
	
	@Override
	protected void onResume() {
		this.carregaLista();
		super.onResume();
	}
}
