package posjavamavenhibernate.testing;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TesteHibernate {
	
	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setIdade(45);
		pessoa.setLogin("binho");
		pessoa.setNome("kleber");
		pessoa.setSenha("sdfwe");
		pessoa.setSobrenome("margarido3");
		pessoa.setEmail("passou@gmail.com");
		
		daoGeneric.salvar(pessoa);
	}
	
	
	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setId(8l);
	
		
		pessoa = daoGeneric.pesquisar(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(1l, UsuarioPessoa.class);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testeUpdate() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(1l, UsuarioPessoa.class);
		
		pessoa.setIdade(99);
		pessoa.setNome("kleber");
		
		pessoa = daoGeneric.updateMerge(pessoa);
		
		System.out.println(pessoa);
		
	}
	
	@Test
	public void testeDeletar() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(3l, UsuarioPessoa.class);
		
		daoGeneric.deletarPorId(pessoa);
		
	}
	
	@Test
	public void testeConsultarUsuario() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> lista = daoGeneric.listar(UsuarioPessoa.class);
		
		for(UsuarioPessoa usuarioPessoa : lista) {
			
			System.out.println(usuarioPessoa);
			System.out.println("============================================================");
		}
		
		
		
	}
	
	@Test
	public void testeCconsultarList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> lista = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa where nome = 'kleber' ").getResultList();
		
		for(UsuarioPessoa usuarioPessoa : lista) {
			
			System.out.println(usuarioPessoa);
			System.out.println("============================================================");
		}
		
		
	}
	
	@Test
	public void testeConsultarListMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		@SuppressWarnings("unchecked")
		List<UsuarioPessoa> lista = daoGeneric.getEntityManager()
		.createQuery("from UsuarioPessoa order by id")
		.setMaxResults(4)
		.getResultList();
		
		for(UsuarioPessoa usuarioPessoa : lista) {
			
			System.out.println(usuarioPessoa);
			System.out.println("============================================================");
		}
	}
		
		@Test
		public void testeQueryConsultarParameter() {
			DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
			
			//or = É SATISFEITO COM UMA CONDIÇÃO TRUE;
			//and = PRECISA SATISFAZER AS DUAS CONDIÇÕES;
			@SuppressWarnings("unchecked")
			List<UsuarioPessoa> lista = daoGeneric.getEntityManager()
			.createQuery("from UsuarioPessoa where nome = :name")
			.setParameter("name", "kleber")
			.getResultList();
			
			for(UsuarioPessoa usuarioPessoa : lista) {
				
				System.out.println(usuarioPessoa);
				System.out.println("============================================================");
			}
		
		
	}
		
		
		@Test
		public void testeQuerySomaIdade() {
			DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
			
			//RETORNA A MÉDIA;
			Double mediaIdade = (Double) daoGeneric.getEntityManager().createQuery("select avg(u.idade) from UsuarioPessoa u ").getSingleResult();
			
			Long somaIdade = (Long) daoGeneric.getEntityManager().createQuery("select sum(u.idade) from UsuarioPessoa u ").getSingleResult();
		
			System.out.println(somaIdade+"  "+mediaIdade);

		}
		
		@Test
		@SuppressWarnings("unchecked")
		public void testeNamedQuery1() {
			DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
			
			List<UsuarioPessoa> lista = daoGeneric.getEntityManager()
					.createNamedQuery("UsuarioPessoa.todos")
					.getResultList();
			
			for(UsuarioPessoa usuarioPessoa : lista) {
				
				System.out.println(usuarioPessoa);
				System.out.println("============================================================");
			}
			
		}
		
		
		@Test
		@SuppressWarnings("unchecked")
		public void testeNamedQuery2() {
			DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
			
			List<UsuarioPessoa> lista = daoGeneric.getEntityManager()
					.createNamedQuery("UsuarioPessoa.buscaNome")
					.setParameter("nome", "kleber")
					.getResultList();
			
			for(UsuarioPessoa usuarioPessoa : lista) {
				
				System.out.println(usuarioPessoa);
				System.out.println("============================================================");
			}
			
		}
		
		
		
		@Test
		@SuppressWarnings("unchecked")
		public void testeSalvarTelefone() {
			
			DaoGeneric daoGeneric = new DaoGeneric();
			
			UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(2L, UsuarioPessoa.class);
			
			TelefoneUser telefoneUser = new TelefoneUser();
			
			telefoneUser.setTelefone("981750448");
			telefoneUser.setTipo("Celular");
			telefoneUser.setUsuarioPessoa(pessoa);
			
			daoGeneric.salvar(telefoneUser);
			
			
			
		}
		
		@Test
		@SuppressWarnings("unchecked")
		public void consultaTelefones() {
			
			DaoGeneric daoGeneric = new DaoGeneric();
			
			UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(1L, UsuarioPessoa.class);
			
		for(TelefoneUser telefone : pessoa.getTelefonesUsers()) {
				
				System.out.println(telefone.getTelefone());
				System.out.println(telefone.getTipo());
				System.out.println(telefone.getUsuarioPessoa().getNome());
				System.out.println("============================================================");
			}
			
		}	
			
		

}
