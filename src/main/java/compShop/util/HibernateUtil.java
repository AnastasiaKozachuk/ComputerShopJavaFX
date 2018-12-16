package compShop.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import compShop.model.Category;
import compShop.model.Courier;
import compShop.model.Customer;
import compShop.model.Delivery;
import compShop.model.DeliveryType;
import compShop.model.Good;
import compShop.model.ItemInOrder;
import compShop.model.Manager;
import compShop.model.Manufacturer;
import compShop.model.OrderStatus;
import compShop.model.PaymentType;
import compShop.model.Supplier;
import compShop.model.SupplierPhone;
import compShop.model.UsersOrder;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Category.class)
                    .addAnnotatedClass(Courier.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(DeliveryType.class)
                    .addAnnotatedClass(Good.class)
                    .addAnnotatedClass(ItemInOrder.class)
                    .addAnnotatedClass(Manager.class)
                    .addAnnotatedClass(Manufacturer.class)
                    .addAnnotatedClass(UsersOrder.class)
                    .addAnnotatedClass(OrderStatus.class)
                    .addAnnotatedClass(PaymentType.class)
                    .addAnnotatedClass(Supplier.class)
                    .addAnnotatedClass(SupplierPhone.class)
                    .addAnnotatedClass(Delivery.class)
                    .buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session beginTransaction() {
        Session hibernateSession = HibernateUtil.getSession();
        hibernateSession.beginTransaction();
        return hibernateSession;
    }

    public static void commitTransaction() {
        HibernateUtil.getSession().getTransaction().commit();
    }

    public static void closeSession() {
        HibernateUtil.getSession().close();
    }


    public static void rollbackTransaction() {
        HibernateUtil.getSession().getTransaction().rollback();
    }

    public static Session getSession() {
        Session hibernateSession = sessionFactory.getCurrentSession();
        return hibernateSession;
    }

}
