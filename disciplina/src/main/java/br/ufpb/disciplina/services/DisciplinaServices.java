package br.ufpb.disciplina.services;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import br.ufpb.disciplina.entities.Disciplina;

@Service
public class DisciplinaServices{

	private ArrayList<Disciplina> disc = new ArrayList<Disciplina>();

	public Disciplina cadastrarDisciplina(int id, String nome, double nota) {
		
		Disciplina d = new Disciplina(id, nome, nota);
		disc.add(d);
		return disc.get(disc.size() - 1);
	}

	public boolean removerDisciplina(int id) throws Exception {

		Disciplina delete = getDisciplina(id);
		disc.remove(delete);
		return true;

	}

	public Disciplina getDisciplina(int id) throws Exception {

		Disciplina saida = null;
		
		for (Disciplina d : disc) if (d.getId() == id) saida = d;

		if (saida != null) {
			return saida;

		} else {		
			throw new Exception();
		}
	}

	public ArrayList<Disciplina> consultarTodasDisciplina() {

		ArrayList<Disciplina> discSaida = new ArrayList<Disciplina>();
		
		for (Disciplina a : disc) discSaida.add(a);
		
		return discSaida;
	}

	public Disciplina consultarDisciplinaPorId(int id) throws Exception {
		return getDisciplina(id);
	}

	public ArrayList<Disciplina> getDisc() {
		return disc;
	}

	public Disciplina setNomeDisciplinaPorId(int id, String nome) throws Exception {
		
		Disciplina setNome = getDisciplina(id);
		setNome.setNome(nome);
		return setNome;
	}

	public Disciplina setNotaDisciplinaPorId(int id, double nota) throws Exception {
		
		Disciplina setNota = getDisciplina(id);
		setNota.setNota(nota);
		return setNota;
	}

	public ArrayList<Disciplina> ordenaPorNota() {
		
		ArrayList<Disciplina> saida = getDisc();
		Disciplina a = null, b = null;
		boolean ordenado = false;

		while (ordenado == false) {
			
			ordenado = true;
			
			for (int pos = 0; pos < saida.size(); pos++) {
				
				if (pos + 1 < saida.size()) {
					
					a = saida.get(pos);
					b = saida.get(pos + 1);
					
					if (a.getNota() < b.getNota()) {
						ordenado = false;
						saida.set(pos, b);
						saida.set(pos + 1, a);
						
					} else if (a.getNota() == b.getNota()) {
						
						if (a.getId() < b.getId()) {
							ordenado = false;
							saida.set(pos, b);
							saida.set(pos + 1, a);
						}
					}
				}
			}
		}
		return saida;
	}

	public ArrayList<Disciplina> consultarRank() {
		
		ArrayList<Disciplina> rank = ordenaPorNota();
		return rank;
	}
}