package org.example;

import DB.dao.CategoriesMapper;
import DB.model.Categories;
import DB.model.CategoriesExample;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

public class CtegoriesTest
{
    static SqlSession sqlSession;
    static CategoriesMapper categoriesMapper;
    static int idCategory;
    @SneakyThrows
    @BeforeAll
    static void SetUp()
    {
        String res = "mybatis-config.xml";
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(res));
        sqlSession = sqlSessionFactory.openSession(true);
        categoriesMapper = sqlSession.getMapper(CategoriesMapper.class);
    }

    @Test
    void CategoriesCountTest()
    {

        long cnt = categoriesMapper.countByExample(new CategoriesExample());
        List<Categories> categoriesList = categoriesMapper.selectByExample(new CategoriesExample());
        Assertions.assertEquals(categoriesList.size(),cnt);
    }

    @Test
    void GetCategoryNameTestById()
    {
        Assertions.assertEquals("giraffe",categoriesMapper.selectByPrimaryKey(616).getTitle());
    }
}
