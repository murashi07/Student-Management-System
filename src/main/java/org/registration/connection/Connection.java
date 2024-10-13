package org.registration.connection;

import org.hibernate.Session;
import util.HibernateUtil;

public class Connection {

    public  String establishConnection(){
        try (Session session =
                HibernateUtil.getSession().openSession()){
            return "connected";

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
