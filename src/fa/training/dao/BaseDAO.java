package fa.training.dao;

public interface BaseDAO <T, K>{
	public void create(T obj);
	public void retrieve(K str);
	public void update(K str);
	public void dalete(K str);
	public void findAll();
}
