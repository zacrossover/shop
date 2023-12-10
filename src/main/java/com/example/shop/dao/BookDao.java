package com.example.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.shop.entity.Book;

@Mapper
public interface BookDao {
	/**
	 * 查询上架图书
	 * @return
	 */
    @Select("<script>"
    		+ "SELECT * FROM t_book "
    		+ "<where>"
    		+ "<if test='status != null and status != -1'>"
    		+ " AND status = #{status}"
    		+ "</if>"
    		+ "</where>"
    		+ " ORDER BY id DESC"
    		+ "</script>")
    List<Book> list(Integer status);
    
    /**
     * 按名称模糊查询图书
     * @param bookName
     * @return
     */
    @Select("<script>"
    		+ "SELECT * FROM t_book WHERE book_name LIKE CONCAT('%', #{bookName}, '%') "
    		+ "<if test='status != null and status != -1'>"
    		+ "AND status = #{status}"
    		+ "</if>"
    		+ "</script>")
    List<Book> listByName(String bookName, Integer status);
    
    /**
     * 按分类查询图书
     * @param classification
     * @return
     */
    @Select("<script>"
    		+ "SELECT * FROM t_book WHERE classification = #{classification} "
    		+ "<if test='status != null and status != -1'>"
    		+ "AND status = #{status}"
    		+ "</if>"
    		+ "</script>")
    List<Book> listByClassification(String classification, Integer status);
    
    /**
     * 按id查询图书
     * @param id
     * @return
     */
    @Select("SELECT * FROM t_book WHERE id = #{id}")
    Book getById(int id);
    
    /**
     * 添加图书
     * @param book
     * @return
     */
    @Insert("INSERT INTO t_book (book_no, book_name, classification, price, quantity, description, pictures, status, avg_score) VALUES(#{bookNo}, #{bookName}, #{classification}, #{price}, #{quantity}, #{description}, #{pictures}, #{status},'3.0')")
    int add(Book book);
    
    /**
     * 修改图书
     * @param book
     * @return
     */
    @Update("UPDATE t_book SET book_name = #{bookName}, classification = #{classification}, price = #{price}, description = #{description}, pictures = #{pictures} WHERE id = #{id}")
    int update(Book book);
    
    /**
     * 图书上/下架
     * @param id
     * @param status 1-上架，0-下架
     * @return
     */
    @Update("UPDATE t_book SET status = #{status} WHERE id = #{id}")
    int updateStatusById(int id, int status);
    
    /**
     * 删除图书
     * @param id
     * @return
     */
    @Delete("DELETE FROM t_book WHERE id = #{id}")
    int delete(int id);

    /**
     * 删除图书
     * @param id
     * @return
     */
    @Update("UPDATE t_book SET avg_score = #{score} WHERE id = #{id}")
    int updateAvgScore(int id,float score);

}
