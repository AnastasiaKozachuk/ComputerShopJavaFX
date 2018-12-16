package compShop.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.HibernateException;

import compShop.dao.OrderDAO;
import compShop.dao.impl.OrderDAOImpl;
import compShop.model.ItemInOrder;
import compShop.model.UsersOrder;
import compShop.service.OrderService;
import compShop.util.HibernateUtil;

public class OrderServiceImpl implements OrderService {

    OrderDAO orderDAO = new OrderDAOImpl();

    public Integer saveNewUsersOrder(UsersOrder order) {
        Integer usersOrderCode = null;
        try {
            HibernateUtil.beginTransaction();
            usersOrderCode = orderDAO.saveWithReturn(order);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Saving error.");
            HibernateUtil.rollbackTransaction();
        }
        return usersOrderCode;
    }

    public void updateUsersOrder(UsersOrder order) {
        try {
            HibernateUtil.beginTransaction();
            orderDAO.update(order);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Updating error.");
            HibernateUtil.rollbackTransaction();
        }
    }

    public void deleteUsersOrder(UsersOrder order) {
        try {
            HibernateUtil.beginTransaction();
            orderDAO.delete(order);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Deleting error.");
            HibernateUtil.rollbackTransaction();
        }

    }

    public List<UsersOrder> getAllUsersOrder() {
        List<UsersOrder> orderList = null;
        try {
            HibernateUtil.beginTransaction();
            orderList = orderDAO.findAll(UsersOrder.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Getting error.");
            HibernateUtil.rollbackTransaction();
        }
        return orderList;
    }

    public UsersOrder getOneById(Integer id) {
        UsersOrder order = null;
        try {
            HibernateUtil.beginTransaction();
            order = orderDAO.findByID(UsersOrder.class, id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Getting error.");
            HibernateUtil.rollbackTransaction();
        }
        return order;
    }

    public List<Object[]> getOrderCodeAndCustLogByPaymType(String payment_name) {
        List<Object[]> orderList = null;
        try {
            HibernateUtil.beginTransaction();
            orderList = orderDAO.getOrderCodeAndCustLogByPaymType(payment_name);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Getting error.");
            HibernateUtil.rollbackTransaction();
        }
        return orderList;
    }

    public List<Object[]> getOrderCodeAndCustLogByDelivType(String deliv_name) {
        List<Object[]> orderList = null;
        try {
            HibernateUtil.beginTransaction();
            orderList = orderDAO.getOrderCodeAndCustLogByDelivType(deliv_name);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Getting error.");
            HibernateUtil.rollbackTransaction();
        }
        return orderList;
    }

    public List<Object[]> getOrderCodeAndCustLogByOrdStat(String ordStat_name) {
        List<Object[]> orderList = null;
        try {
            HibernateUtil.beginTransaction();
            orderList = orderDAO.getOrderCodeAndCustLogByOrdStat(ordStat_name);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Getting error.");
            HibernateUtil.rollbackTransaction();
        }
        return orderList;
    }

    public List<Object[]> getNewOrderForManagers() {
        List<Object[]> orderList = null;
        try {
            HibernateUtil.beginTransaction();
            orderList = orderDAO.getNewOrderForManagers();
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Getting error.");
            HibernateUtil.rollbackTransaction();
        }
        return orderList;
    }

    public List<Object[]> getNewOrderForCouriers() {
        List<Object[]> orderList = null;
        try {
            HibernateUtil.beginTransaction();
            orderList = orderDAO.getNewOrderForCouriers();
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Getting error.");
            HibernateUtil.rollbackTransaction();
        }
        return orderList;
    }

    public List<Object[]> getCurierOrders(Integer courier_id) {
        List<Object[]> orderList = null;
        try {
            HibernateUtil.beginTransaction();
            orderList = orderDAO.getCurierOrders(courier_id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Getting error.");
            HibernateUtil.rollbackTransaction();
        }
        return orderList;
    }

    public List<Object[]> getCustomerOrders(String login) {
        List<Object[]> orderList = null;
        try {
            HibernateUtil.beginTransaction();
            orderList = orderDAO.getCustomerOrders(login);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Getting error.");
            HibernateUtil.rollbackTransaction();
        }
        return orderList;
    }

    public Long getCountOfFinishedOrderPerMonth() {
        Long finishedOrders = null;
        try {
            HibernateUtil.beginTransaction();
            finishedOrders = orderDAO.getCountOfFinishedOrderPerMonth();
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Getting error.");
            HibernateUtil.rollbackTransaction();
        }
        return finishedOrders;
    }

    public BigDecimal getEarnedMoneyPerMonth() {
        BigDecimal finishedOrders = null;
        try {
            HibernateUtil.beginTransaction();
            finishedOrders = orderDAO.getEarnedMoneyPerMonth();
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Getting error.");
            HibernateUtil.rollbackTransaction();
        }
        return finishedOrders;
    }

    public List<ItemInOrder> getAllItemOfOneOrder(Integer order_code) {
        List<ItemInOrder> orderList = null;
        try {
            HibernateUtil.beginTransaction();
            orderList = orderDAO.getAllItemOfOneOrder(order_code);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Getting error.");
            HibernateUtil.rollbackTransaction();
        }
        return orderList;
    }

}
