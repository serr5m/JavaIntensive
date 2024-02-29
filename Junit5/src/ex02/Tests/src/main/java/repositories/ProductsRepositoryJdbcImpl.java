package repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("id")
                        , resultSet.getString("name")
                        , resultSet.getInt("price")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product(resultSet.getInt("id")
                        , resultSet.getString("name")
                        , resultSet.getInt("price"));
                return Optional.of(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update product set name = ?, price = ? where id = ?")) {
            int id = product.getId();
            String name = product.getName();
            int price = product.getPrice();
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, price);
            preparedStatement.setInt(3, id);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("update " + affectedRows);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void save(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into product(id, name, price) values(?, ?, ?)")) {

            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getPrice());
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("update " + affectedRows);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from product where id = ?")) {
            preparedStatement.setLong(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("delete " + affectedRows + " rows");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
