package Dao;

import entity.Lesson01;
import entity.Staff;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author intern2
 */
public class DbHelper {
    SessionFactory sessionFactory ;
    Session session;
    
    public DbHelper() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
        this.session = sessionFactory.openSession();
    }
    
    public void close() {
        session.flush();
        session.close();        
    }
    
    protected void finalize() throws Throwable {
        try{
            super.finalize();
        } finally {
            close();
        }
    }
    
    public List<Lesson01> isMatchAccount(String usr, String pass) throws HibernateException{
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("FROM Lesson01 WHERE userId = :usr and password = :pass");
        query.setString("usr", usr);
        query.setString(("pass"), pass);
        List<Lesson01> list = query.list();
        tx.commit();
        if (list.size() == 1){
            return list;
        }else{
            return null;
        }
    }
    
    public boolean searchData(String usr){
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("FROM Lesson01 WHERE userId = :usr");
        query.setString("usr", usr);
        List list = query.list();
        if (list.size() == 1){
            return true;
        } else {
            return false;
        }
    }
    
    public String searchAllData(String usr, String pass) throws Exception{
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("FROM Lesson01 WHERE userId = :usr and password = :pass");
        query.setString("usr", usr);
        query.setString("pass", pass);
        List list = query.list();
        if (list.size() == 1){
            //System.out.println(list.get(0));
            tx.commit();
            return list.get(0).toString();
        }else{
            tx.commit();
            return null;
        }
    }
    
    public Staff searchStaff(String usr){
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("FROM Staff WHERE id  = :usr");
        query.setString("usr", usr);
        List<Staff> list = query.list();
        tx.commit();
        if (list.size() == 1){
            return list.get(0);
        } else {
            return null;
        }
     
    }
    
    public List<Staff> searchAllStaff() throws HibernateException{
        Transaction tx = session.beginTransaction();
        List<Staff> list = session.createCriteria(Staff.class).list();
        tx.commit();
        return list;
    }
    
    //引数はページ数
    public List<Staff> searchLimitedStaff(int page_num) throws HibernateException{
        Transaction tx = session.beginTransaction();
        int record_num = 20;
        List<Staff> list ;
        Query tmp = session.createQuery("FROM Staff");
        tmp.setFirstResult((page_num-1)*record_num);
        tmp.setMaxResults(record_num);
        list = tmp.list();
        tx.commit();
        return list;
    }
    
    public int howManyColumn() throws HibernateException{
        Transaction tx = session.beginTransaction();
        int num = session.createCriteria(Staff.class).list().size();
        tx.commit();
        return num;
    }

    public void saveOrUpdate(Staff staff) throws HibernateException{      
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(staff);
        tx.commit();      
    }   
    
    public void save(Staff staff) throws HibernateException{
        Transaction tx = session.beginTransaction();
        session.save(staff);
        tx.commit();
    }
    
    public boolean update(Staff staff) throws HibernateException{
        DbHelper dh = new DbHelper();        
        if (dh.searchStaff(staff.getId()) == null){
            return false;
        }        
        Transaction tx = session.beginTransaction();
        String strHql = "UPDATE Staff SET name = :update_name , tel = :update_tel , update_date = :date WHERE id = :update_id";
        Query query = session.createQuery(strHql);
        String usr = staff.getName();
        query.setString("update_name", usr);
        query.setString("update_tel",staff.getTel());
        query.setTimestamp("date",staff.getUpdateDate());
        query.setString("update_id", staff.getId());
        query.executeUpdate();
        //session.createQuery(strHql);
        
        tx.commit();
        return true;
    }
    
    public boolean deleteData(String id) throws HibernateException{  
        DbHelper dh = new DbHelper();
        if (dh.searchStaff(id) == null){
            return false;
        }               
        Staff staff = new Staff();
        staff.setId(id);
        Transaction tx = session.beginTransaction();
        session.delete(staff);
        tx.commit();
        return true;
    }    
}
