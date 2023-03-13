package lesson6;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Test {
    static SqlSession session;

    @BeforeAll
    static void beforeAll() throws IOException {
        session = null;
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
    }

    @org.junit.jupiter.api.Test
    void ChangeCategory() {
        db.dao.CategoriesMapper categoriesMapper = session.getMapper(db.dao.CategoriesMapper.class);
        db.model.Categories selected = categoriesMapper.selectByPrimaryKey(1L);

        assertThat(selected.getId(), is(1L));
        assertThat(selected.getTitle(), is("Food"));
    }

    @org.junit.jupiter.api.Test
    void ChangeProduct() {
        db.dao.ProductsMapper productsMapper = session.getMapper(db.dao.ProductsMapper.class);
        db.model.Products selected = productsMapper.selectByPrimaryKey(1L);

        assertThat(selected.getId(), is(1L));
        assertThat(selected.getTitle(), is("Milk"));
    }

    @org.junit.jupiter.api.Test
    void CreateProduct() {
        db.dao.ProductsMapper productsMapper = session.getMapper(db.dao.ProductsMapper.class);
        db.model.Products products = new db.model.Products();
        products.setTitle("Fresh Meat");
        productsMapper.insert(products);
        assertThat(products.getTitle(), is("Fresh Meat"));
    }
}
