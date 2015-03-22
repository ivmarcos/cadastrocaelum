package br.com.caelum.cadastro;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioHelper {

	private Aluno aluno;
	private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoSite;
    private SeekBar campoNota;
    private EditText campoEndereco;
    private ImageView botaoImagem;
    
	public FormularioHelper(FormularioActivity activity) {
		
		campoNome = (EditText) activity.findViewById(R.id.nome);
		campoTelefone = (EditText) activity.findViewById(R.id.telefone);
		campoEndereco = (EditText) activity.findViewById(R.id.endereco);
		campoSite = (EditText) activity.findViewById(R.id.site);
		campoNota = (SeekBar) activity.findViewById(R.id.nota);
		
		aluno = new Aluno();
		
		String nome = campoNome.getText().toString();
		String telefone = campoTelefone.getText().toString();
		String endereco = campoEndereco.getText().toString();
		String site = campoSite.getText().toString();
		Double nota = Double.valueOf(campoNota.getProgress());
		
		aluno.setNome(nome);
		aluno.setTelefone(telefone);
		aluno.setEndereco(endereco);
		aluno.setSite(site);
		aluno.setNota(nota);
	}
	
	public void colocaNoFormulario(Aluno aluno) {
		campoNome.setText(aluno.getNome());
		campoTelefone.setText(aluno.getTelefone());
		campoSite.setText(aluno.getSite());
		campoNota.setProgress(aluno.getNota().intValue());
		campoEndereco.setText(aluno.getEndereco());
        this.aluno = aluno;
    }
	
	public Aluno pegaAlunoDoFormulario() {
		return aluno;
	}
}
