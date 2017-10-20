/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Dao.DbHelper;
import entity.Lesson01;
import entity.Staff;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author intern2
 */
public class EmptyJUnitTest {
    
    public EmptyJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("setUpClass");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("tearDownClass");
    }
    
    @Before
    public void setUp() {
        System.out.println("setUp");
    }
    
    @After
    public void tearDown() {
        System.out.println("tearDown");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() throws Exception{
        DbHelper dh = new DbHelper();
        //Date updateDate = new Date();
        for(int i=0; i<100; i++) {
            Staff staff = new Staff();
            staff.setId(String.format("%04d", i));
            staff.setName(String.format("hoge%04d", i));
            staff.setTel(String.format("0200000%04d", i));
            staff.setHireDate(new Date());
            staff.setUpdateDate(new Date());
            dh.saveOrUpdate(staff);
        }
    }
}
