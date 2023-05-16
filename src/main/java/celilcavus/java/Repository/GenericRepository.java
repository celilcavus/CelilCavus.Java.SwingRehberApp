package celilcavus.java.Repository;

import celilcavus.java.Entitys.Person;
import celilcavus.java.Interfaces.IRepository;
import celilcavus.java.SqlHelper.DbContext;
import java.sql.ResultSet;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class GenericRepository implements IRepository<Person> {

    private DbContext context;

    public GenericRepository() {
        context = new DbContext();
    }

    @Override
    public void Add(Person item) {
        try {
            context.pre = context.connect.prepareCall("INSERT INTO persons (Name, LastName, PhoneNumber) values (?,?,?)");
            context.pre.setString(1, item.getName());
            context.pre.setString(2, item.getLastName());
            context.pre.setString(3, item.getPhoneNumber());
            context.pre.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void Remove(int id) {
      try{
           context.pre = context.connect.prepareCall("DELETE FROM persons where id = ?");
           context.pre.setInt(1, id);
           context.pre.executeUpdate();
      }
      catch(Exception ex)
      {
          System.out.println(ex.getMessage());
      }
    }

    @Override
    public void Update(Person item) {
         try {
            context.pre = context.connect.prepareCall("UPDATE persons SET Name = ?, LastName = ?, PhoneNumber = ? where id = ?");
            context.pre.setString(1, item.getName());
            context.pre.setString(2, item.getLastName());
            context.pre.setString(3, item.getPhoneNumber());
            context.pre.setInt(4, item.getId());
            context.pre.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    @Override
    public List<Person> GetAll() {
        List<Person> listPerson = new ArrayList<Person>();
        try {
            context.set = context.state.executeQuery("SELECT * FROM persons");
            while (context.set.next()) {
                Person per = new Person();
                per.id = context.set.getInt("ID");
                per.Name = context.set.getString("Name");
                per.LastName = context.set.getString("LastName");
                per.PhoneNumber = context.set.getString("PhoneNumber");
                listPerson.add(per);
            }
            return listPerson;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public Person GetById(int id) {
        Person per = new Person();
        try {
            var person = context.state = context.connect.prepareCall("SELECT * FROM persons WHERE id = ?");
            context.pre.setInt(1, id);
            context.set = (ResultSet) person;
            while (context.set.next()) {                
               
                per.id = context.set.getInt("ID");
                per.Name = context.set.getString("Name");
                per.LastName = context.set.getString("LastName");
                per.PhoneNumber = context.set.getString("PhoneNumber");
            }
            return per;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
