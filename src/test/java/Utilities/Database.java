package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    private static final String url = "jdbc:mysql://ecommercestore-yavaarnosi-e32c.h.aivencloud.com:17827/ecommerce_store?ssl-mode=REQUIRED";
    private static final String user = "avnadmin";
    private static final String password = "AVNS_q332psQ6B3zBF_blqUv";
    private static Connection conn;

    public static void cleanTables() throws SQLException {

        conn = DriverManager.getConnection(url, user, password);

        PreparedStatement user1 = conn.prepareStatement("DELETE FROM user WHERE email_id NOT IN ('demo.admin@demo.com', 'seller@demo.com', 'customer@demo.com') AND seller_id IS NOT NULL");
        PreparedStatement user2 = conn.prepareStatement("DELETE FROM user WHERE email_id NOT IN ('demo.admin@demo.com', 'seller@demo.com', 'customer@demo.com')");
        PreparedStatement address = conn.prepareStatement("DELETE FROM address WHERE id NOT IN (1, 30)");
        PreparedStatement cart = conn.prepareStatement("DELETE FROM cart");
        PreparedStatement orders = conn.prepareStatement("DELETE FROM orders");
        PreparedStatement review = conn.prepareStatement("DELETE FROM review");
        PreparedStatement product = conn.prepareStatement("DELETE FROM product WHERE name NOT IN ('Mobile Phone')");
        PreparedStatement category = conn.prepareStatement("DELETE FROM category WHERE name NOT IN ('Test')");

        orders.executeUpdate();
        review.executeUpdate();
        cart.executeUpdate();
        product.executeUpdate();
        user1.executeUpdate();
        user2.executeUpdate();
        address.executeUpdate();
        category.executeUpdate();

        conn.close();

    }

    public static void cleanCart() throws SQLException {

        conn = DriverManager.getConnection(url, user, password);

        PreparedStatement cart = conn.prepareStatement("DELETE FROM cart");

        cart.executeUpdate();

        conn.close();

    }

}
