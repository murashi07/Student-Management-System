//package org.registration.connection;
//
//import org.hibernate.Session;
//
//public class Connection {
//
//    public  String establishConnection(){
//        try (Session session =
//                HibernateUtil.getSession().openSession()){
//            return "connected";
//
//        }catch(Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
