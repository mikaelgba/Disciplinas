package br.ufpb.disciplina.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.disciplina.entities.Disciplina;
import br.ufpb.disciplina.services.DisciplinaServices;

@RestController
public class DisciplinaController {

	@Autowired
	private DisciplinaServices ds;
	private int idCount;

	@PostMapping("/v1/api/disciplinas")
	public ResponseEntity<Disciplina> cadastrarDisciplina(@RequestBody Disciplina disci) {
		
		idCount++;
		String nome = disci.getNome();
		double nota = disci.getNota();

		return new ResponseEntity<Disciplina>(ds.cadastrarDisciplina(idCount, nome, nota), HttpStatus.CREATED);
	}

	@GetMapping("/v1/api/disciplinas/{id}")
	public ResponseEntity<Disciplina> consultarDisciplinaPorId(@PathVariable("id") int id) throws Exception {

		if (ds.getDisciplina(id) != null) {

			return new ResponseEntity<Disciplina>(ds.getDisciplina(id), HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/v1/api/disciplinas")
	public ArrayList<Disciplina> consultarTodasDisciplina() {

		return ds.consultarTodasDisciplina();
	}
	
	@GetMapping("/v1/api/disciplinas/ranking")
	public ArrayList<Disciplina> consultarRank() {

		return ds.consultarRank();
	}

	@DeleteMapping("/v1/api/disciplinas/{id}")
	public ResponseEntity<Disciplina> Delete(@PathVariable(value = "id") int id) throws Exception {

		Disciplina pessoa = ds.getDisciplina(id);
		
		if (pessoa != null) {
			
			ds.removerDisciplina(pessoa.getId());
			return new ResponseEntity<>(HttpStatus.OK);
			
		} else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/v1/api/disciplinas/{id}/nome")
	public ResponseEntity <Disciplina> setNomeDisciplinaPorId(@PathVariable(value = "id") @RequestBody int id, String nome) {	
		
		try {
			
			Disciplina dis = ds.setNomeDisciplinaPorId(id, nome);
			return new ResponseEntity<Disciplina>(dis, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/v1/api/disciplinas/{id}/nota")
	public ResponseEntity <Disciplina> setNotaDisciplinaPorId(@PathVariable(value = "id") @RequestBody int id, double nota) {	
		
		try {
			
			Disciplina dis = ds.setNotaDisciplinaPorId(id, nota);
			return new ResponseEntity<Disciplina>(dis, HttpStatus.OK);
			
		} catch (Exception d) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}