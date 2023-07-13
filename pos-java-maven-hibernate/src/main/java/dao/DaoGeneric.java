package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import posjavamavenhibernate.HibernateUtil;

public class DaoGeneric<E> {
	
	
	private EntityManager entityManager = HibernateUtil.getEntityManager();
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	public void salvar(E entidade) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(entidade);
		transaction.commit();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public E pesquisar(E entity) {
		Object id = HibernateUtil.getPrimaryKey(entity);
		E e = (E) entityManager.find(entity.getClass(), id);
		return e;
	}
	
	
	
	
	
	public E pesquisar(Long id, Class<E> entity) {
		E e = (E) entityManager.find(entity, id);
		return e;
	}
	
	
	
	
	
	public E updateMerge(E entity) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E e = entityManager.merge(entity);
		transaction.commit();
		return e;
	}
	
	
	
	
	
	public void deletarPorId(E entidade) {
		try {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Object id = HibernateUtil.getPrimaryKey(entidade);
			if(id != null) {
				entityManager.createQuery("delete from " + entidade.getClass().getName() + " where id = " + id).executeUpdate();
				entityTransaction.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<E> listar(Class<E> entity){
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		List<E> lista = entityManager.createQuery("from "+ entity.getName()).getResultList();
		transaction.commit();
		
		return lista;
		
	}
	

}
