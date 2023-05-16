
package celilcavus.java.Interfaces;

import java.util.List;




public interface IRepository<T> {
    void Add(T item);
    void Remove(int id);
    void Update(T item);
    List<T> GetAll();
    T GetById(int id);
}
