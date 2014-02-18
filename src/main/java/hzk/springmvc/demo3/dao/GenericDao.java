package hzk.springmvc.demo3.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {

	/** Persist the newInstance object into database */
	public PK create(T newInstance);

	/**
	 * Retrieve an object that was previously persisted to the database using
	 * the indicated id as primary key
	 */
	public T read(PK id);

	/** Save changes made to a persistent object. */
	public void update(T transientObject);

	/** Remove an object from persistent storage in the database */
	public void delete(T persistentObject);

	public void delete(PK id);

	public List<T> getAll();

	public T getById(PK id);
}
