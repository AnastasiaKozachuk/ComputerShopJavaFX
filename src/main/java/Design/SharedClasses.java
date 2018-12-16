package Design;

import Design.Controllers.CourierWindowController;
import Design.Controllers.DirectorWindowController;
import Design.Controllers.ManagerWindowController;
import compShop.model.Courier;
import compShop.model.Customer;
import compShop.model.Manager;
import javafx.scene.control.TableView;

import java.util.List;


public class SharedClasses {

    public static DirectorWindowController director_window;

    public static ManagerWindowController manager_window;

    public static CourierWindowController courier_window;

    public static Manager manager_client;

    public static Courier courier_client;

    public static Customer customer_client;

    public static TableView tableView;

    public static Object[] result_info;

    public static Number number;


}
