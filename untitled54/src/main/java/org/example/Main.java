package org.example;

import org.example.entity.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (input.equals("creat")) {
            session.beginTransaction();
            String username = scanner.nextLine();
            String password = scanner.nextLine();
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            session.save(user);
            session.getTransaction().commit();
            session.close();
        }
        if(input.equals("delete")) {
            session.beginTransaction();
            int id = scanner.nextInt();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
            session.close();
        }
        if (input.equals("update")) {
            session.beginTransaction();
            String password = scanner.nextLine();
            int id = scanner.nextInt();
            User user = session.get(User.class ,id );
            user.setPassword(password);
            session.getTransaction().commit();
            session.close();
        }
        HibernateUtil.shutdown();
    }
}