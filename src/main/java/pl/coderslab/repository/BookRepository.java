package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Category;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByRating(int rating);

    List<Book> findAllByTitle(String title);

    List<Book> findAllByCategory(Category category);

    List<Book> findAllByCategoryId(Long aLong);

    @Query("select b from Book b where b.title =?1")
    List<Book> findAllByTitleByQuery(String title);

    @Query("select b from Book b where b.category =?1")
    List<Book> findAllByCategoryQuery(Category category);
}
