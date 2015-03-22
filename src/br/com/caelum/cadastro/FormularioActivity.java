package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioActivity extends Activity {
	

	private FormularioHelper helper;
	private Aluno alunoParaSerAlterado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		Button button = (Button) findViewById(R.id.botao);
		helper = new FormularioHelper(this);
		
		Intent intent = this.getIntent();
		
		alunoParaSerAlterado = (Aluno) intent.getSerializableExtra(Extras.ALUNO_SELECIONADO);
		
        if (alunoParaSerAlterado != null) {
        	button.setText("Alterar");
        	helper.colocaNoFormulario(alunoParaSerAlterado);
        }
		
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				
				Aluno aluno = helper.pegaAlunoDoFormulario();
				AlunoDAO dao = new AlunoDAO(FormularioActivity.this);
				
				if (aluno.getId() == null) {
					dao.salva(aluno);
				}else {
					dao.altera(aluno);
				}
				dao.close();
				finish();
			}
		});
	}
	
	

	
}
