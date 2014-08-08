package com.teste;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cadastro.Aluno;
import com.cadastro.Curso;
import com.cadastro.Departamento;
import com.cadastro.Disciplina;
import com.cadastro.Pessoa;
import com.cadastro.Professor;
import com.cadastro.SistemaAcademico;
import com.excecoes.IdadeInvalidaException;

public class SistemaTeste {
	
	private SistemaAcademico sistemaAcademico;
	
	@Before
	public void NovoSistemaAcademico(){
		sistemaAcademico = new SistemaAcademico();
	}
	
	@Test
	public void CadastrarSistemaAcademico(){
		sistemaAcademico.setNome("Universidade Federal da Para�ba");
		sistemaAcademico.setEndereco("Cidade Universit�ria, Jo�o Pessoa/PB");
		sistemaAcademico.setCnpj("888.0001.00/00");
		assertEquals("Universidade Federal da Para�ba",sistemaAcademico.getNome());
		assertEquals("Cidade Universit�ria, Jo�o Pessoa/PB",sistemaAcademico.getEndereco());
		assertEquals("888.0001.00/00",sistemaAcademico.getCnpj());
	}

	@Test
	public void cadastrarProfessor(){
		Professor professor1 = new Professor();
		professor1.setNome("Rodrigo Vilar");
		professor1.setIdade(24);
		professor1.setCpf("111.111.111-2");
		professor1.setEndereco("Rua Beira Rio, 123, Centro, Jo�o Pessoa");
		
		List <Professor> professores = sistemaAcademico.getProfessores();
		sistemaAcademico.addProfessor(professor1);
		assertEquals(1, professores.size());
		assertEquals(professor1, professores.get(0));
				
	}
	@Test
	public void cadastrarAluno(){
		Aluno aluno = new Aluno();
		aluno.setNome("Keila");
		aluno.setIdade(19);
		aluno.setCpf("111.222.333-4");
		sistemaAcademico.gerarMatricula(aluno);
		List <Aluno> alunos = sistemaAcademico.getAlunos();
		sistemaAcademico.addAluno(aluno);
		assertEquals("Keil111",sistemaAcademico.gerarMatricula(aluno));
		assertEquals(1, alunos.size());
		assertEquals(aluno, alunos.get(0));
				
	}
	@Test
	public void CadastrarCursos(){
		Curso curso1 = new Curso();
		curso1.setNome("Licenciatura em Ci�ncia da Computa��o");
		curso1.setCargaHoraria(3080);
		Curso curso2 = new Curso();
		curso2.setNome("Sistema de Informa��o");
		curso2.setCargaHoraria(3090);
			
		List <Curso> cursos = sistemaAcademico.getCursos();
		sistemaAcademico.addCurso(curso1);
		sistemaAcademico.addCurso(curso2);
		assertEquals(2,cursos.size());
		assertEquals(curso2,cursos.get(1));
		assertEquals(curso1,cursos.get(0));
		
	}
	
	@Test
	public void CadastrarDepartamentos(){
		Departamento departamento1 = new Departamento();
		departamento1.setNome("Centro de Ci�ncias Exatas e Aplicadas");
		Departamento departamento2 = new Departamento();
		departamento2.setNome("Centro de Tecnologia");
		Departamento departamento3 = new Departamento();
		departamento3.setNome("Centro de Inform�tica");
				
		List <Departamento> departamentos = sistemaAcademico.getDepartamentos();
		sistemaAcademico.addDepartamento(departamento1);
		sistemaAcademico.addDepartamento(departamento2);
		sistemaAcademico.addDepartamento(departamento3);
		assertEquals(3,departamentos.size());
		assertEquals(departamento1,departamentos.get(0));
		assertEquals(departamento2,departamentos.get(1));
		assertEquals(departamento3,departamentos.get(2));
		
	}
	@Test
	public void formarTurmaPorDisciplina(){
		Disciplina disciplina1 = new Disciplina();
		disciplina1.setNome("IP");
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Carlos Andr�");
		aluno1.setIdade(20);
		aluno1.setCpf("111.222.333-4");
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Fernanda Karla");
		aluno2.setIdade(30);
		aluno2.setCpf("111.222.325-7");
		Aluno aluno3 = new Aluno();
		aluno3.setNome("Jo�o Felipe");
		aluno3.setIdade(21);
		aluno3.setCpf("111.022.333-8");
		Aluno aluno4 = new Aluno();
		aluno4.setNome("Maria Gla�dia");
		aluno4.setIdade(23);
		aluno4.setCpf("121.002.703-4");
		
		List <Aluno> turmaIP = sistemaAcademico.getAlunos();
		sistemaAcademico.addAluno(aluno1);
		sistemaAcademico.addAluno(aluno2);
		sistemaAcademico.addAluno(aluno3);
		sistemaAcademico.addAluno(aluno4);
		assertEquals(2,turmaIP.indexOf(aluno3));
		assertEquals(0,turmaIP.indexOf(aluno1));
		assertEquals(1,turmaIP.indexOf(aluno2));
		assertEquals(3,turmaIP.indexOf(aluno4));
		assertEquals(4,turmaIP.size());
		
		turmaIP.remove(aluno1);
		
		assertTrue(turmaIP.contains(aluno2));
		assertTrue(turmaIP.contains(aluno3));
		assertTrue(turmaIP.contains(aluno4));
		
		assertFalse(turmaIP.contains(aluno1));
		
	}
	@Test
	public void matricularAlunosEmTurmaETrancar(){
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Roberto Carlos");
		aluno1.setIdade(20);
		aluno1.setCpf("234.555.987-0");
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Raimundo Jos�");
		aluno2.setCpf("255.555.987-0");
		aluno2.setIdade(20);
		
		
		Disciplina disciplina1 = new Disciplina();
		disciplina1.setNome("CALCULO 1");
		disciplina1.setCargaHoraria(90);
		disciplina1.setCreditos(9);
		
		List <Aluno> turmaCALCULO1 = sistemaAcademico.getAlunos();
		sistemaAcademico.addAluno(aluno1);
		sistemaAcademico.addAluno(aluno2);
		assertEquals(2,turmaCALCULO1.size());
		assertTrue(turmaCALCULO1.contains(aluno2));
		
		
		turmaCALCULO1.remove(aluno2);
		assertFalse(turmaCALCULO1.contains(aluno2));

	}
	@Test
	public void TesteSubmeterMediaPorDisciplina(){
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Liviany Reis");
		aluno1.setCpf("1");
		aluno1.setIdade(23);
		Disciplina disciplina1 = new Disciplina();
		disciplina1.setNome("Introdu��o ao Computador");
		disciplina1.setCargaHoraria(60);
		disciplina1.setCreditos(6);
		assertEquals("Disciplina: Introdu��o ao Computador, Nota: 8.0, Situa��o: Aprovado", sistemaAcademico.toString(aluno1,disciplina1,8.0));
		
	}
	
	@Test(expected = com.excecoes.IdadeInvalidaException.class)
	public void ValidarIdadeAluno(){;
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Pedro Nascimento");
		aluno1.setCpf("222.222.333-2");
		aluno1.setIdade(18);
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Pedro Nascimento");
		aluno2.setCpf("222.222.333-2");
		aluno2.setIdade(16);
		List<Aluno> alunos = sistemaAcademico.getAlunos();
		sistemaAcademico.addAluno(aluno1);
		sistemaAcademico.addAluno(aluno2);
		assertEquals("Idade n�o Permitida, por gentileza informe uma idade acima de 18 anos", sistemaAcademico.validarIdade(aluno2));
		assertEquals(1,alunos.size());
	}
	
	@Test(expected = com.excecoes.CpfDuplicadoException.class)
	public void cpfDoProfessorDuplicado(){
		Professor professor1 = new Professor();
		professor1.setCpf("258.259.104-36");
		professor1.setIdade(30);
		Professor professor2 = new Professor();
		professor2.setCpf("258.259.104-36");
		professor2.setIdade(46);
		
		List<Professor> professores = sistemaAcademico.getProfessores();
		sistemaAcademico.addProfessor(professor1);
		sistemaAcademico.addProfessor(professor2);
		assertEquals(professor1, sistemaAcademico.getProfessores()); 
		assertEquals("Cpf ja cadastrado", sistemaAcademico.getProfessores());
		assertEquals(1,professores.size());
	}
	@Test(expected = com.excecoes.CpfDuplicadoException.class)
	public void cpfDoAlunoDuplicado(){
		Aluno a1 = new Aluno();
		a1.setNome("Carlos Andr�");
		a1.setIdade(23);
		a1.setCpf("324.444.111-0");
		
		Aluno a2 = new Aluno();
		a2.setNome("Felipe Augusto");
		a2.setIdade(20);
		a2.setCpf("324.444.111-0");
		
		List<Aluno> alunos = sistemaAcademico.getAlunos();
		sistemaAcademico.addAluno(a1);
		sistemaAcademico.addAluno(a2);
		assertEquals(a1, sistemaAcademico.getAlunos());
		assertEquals("Cpf ja cadastrado", sistemaAcademico.getAlunos());
		assertEquals(1,alunos.size());
		
	}
	@Test(expected = com.excecoes.IdadeInvalidaException.class)
	public void validarIdadeProfessor(){
		Professor p1 = new Professor();
		p1.setCpf("111.333.556-1");
		p1.setIdade(14);
		
		assertEquals("Idade n�o Permitida, por gentileza informe uma idade acima de 18 anos", sistemaAcademico.validarIdade(p1));
	}
	@Test
	public void exibirSitua��oDoAlunoEmUmDisciplina(){
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Cristal");
		aluno1.setCpf("1");
		aluno1.setIdade(23);
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Raimundo");
		aluno2.setCpf("2");
		aluno2.setIdade(18);
		Disciplina disciplina1 = new Disciplina();
		disciplina1.setNome("Linguagem de Programa��o");
		disciplina1.setCargaHoraria(60);
		disciplina1.setCreditos(6);
		
		sistemaAcademico.SituacaoAlunoPorDisciplina(aluno1, disciplina1, 9.5);
		sistemaAcademico.SituacaoAlunoPorDisciplina(aluno2, disciplina1, 4.5);
		
		assertEquals("Aprovado",sistemaAcademico.SituacaoAlunoPorDisciplina(aluno1, disciplina1,9.5));
		assertEquals("Reprovado",sistemaAcademico.SituacaoAlunoPorDisciplina(aluno1, disciplina1,4.5));
		
	}

	@Test
	public void submeterMediasDoPeriodo(){
		Aluno a = new Aluno();
		a.setNome("Wendell");
	
		Disciplina d1 = new Disciplina();
		d1.setNome("IP");
		Disciplina d2 = new Disciplina();
		d2.setNome("Calculo1");
		Disciplina d3 = new Disciplina();
		d3.setNome("Matematica Elementar");
		Disciplina d4 = new Disciplina();
		d4.setNome("Filosofia");
		Disciplina d5 = new Disciplina();
		d5.setNome("IC");
		List <String> historico = new ArrayList<>();
		historico.add(a.getNome());
		historico.add(sistemaAcademico.toString(a,d1,8.5));
		historico.add(sistemaAcademico.toString(a,d2,8.0));
		historico.add(sistemaAcademico.toString(a,d3,8.5));
		historico.add(sistemaAcademico.toString(a,d4,9.5));
		historico.add(sistemaAcademico.toString(a,d5,9.0));
		assertEquals(2,historico.indexOf(sistemaAcademico.toString(a,d2,8.0)));
		assertEquals("Disciplina: Filosofia, Nota: 9.5, Situa��o: Aprovado", historico.get(4));
		assertEquals("Disciplina: Calculo1, Nota: 8.0, Situa��o: Aprovado",historico.get(2));
		assertEquals("Disciplina: IP, Nota: 8.5, Situa��o: Aprovado",historico.get(1));
		assertTrue(historico.contains("Wendell"));
		assertEquals(6,historico.size());
	
	   }
	
	@Test
	public void GerarMatriculaAluno(){
		Aluno aluno = new Aluno();
		aluno.setNome("Leandro");
		aluno.setCpf("111222.333-42");
		aluno.setEndereco("Rua das Trincheiras");
		aluno.setIdade(23);
		sistemaAcademico.gerarMatricula(aluno);
		assertEquals("Lean111",sistemaAcademico.gerarMatricula(aluno));
		
	}
	@Test (expected = com.excecoes.ProfessorNomeNuloException.class)
	public void cadastrandoProfessorNulo(){
		Professor professor1 = new Professor();
		Professor professor2 = new Professor();
		professor1.setNome(null);
		professor2.setNome("Jo�o Pedro");
		
		assertEquals("Preencha todos os campos corretamente", sistemaAcademico.validarProfessorNulo(professor1));
		assertEquals(professor2, sistemaAcademico.getProfessores());
	}
	
	}
	
	